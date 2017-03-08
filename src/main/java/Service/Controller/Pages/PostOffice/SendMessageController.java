package Service.Controller.Pages.PostOffice;

import Builder.ContextMenuBuilder;
import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import FileBuilder.FileBuilder;
import Initialization.Initialization;
import Service.Message.Message;
import Service.Message.MessageSent;
import Service.Message.MessageWorkingCopy;
import Service.Person.Person;
import Service.Person.PersonOnList;
import Validation.Validation;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonatan on 2017-01-28.
 */
public class SendMessageController {

    @FXML
    private TextField textRecipients, textTopic, textSearchPerson;
    @FXML
    private JFXTextArea textContents;
    @FXML
    private JFXListView<String> listPersons;
    @FXML
    private JFXComboBox<String> comboSettingsSearch;
    @FXML
    private Label labelStatusMessage;
    @FXML
    private ImageView imageViewQuestionMark;
    @FXML
    private MaskerPane maskerPane;

    private Person person;
    private Stage stage;
    private List<PersonOnList> listPersonOnList = new ArrayList<>();

    private AutoCompletionBinding<String> autoCompletionBinding;

    private PopOver popOver = new PopOver();
    private ContextMenu contextMenu = new ContextMenu();

    private boolean showPopOverOnStart = true;

    @FXML
    public void initialize() {
        Initialization.initOptionsForSendingMessage(comboSettingsSearch);
        autoCompletionBinding = TextFields.bindAutoCompletion(textSearchPerson, Database.loadLoginsAndEmails());
    }

    public void initContexMenu() {
        ContextMenuBuilder.initContextMenuForListPersons(contextMenu, listPersons, listPersonOnList,
                textRecipients, person);
    }

    @FXML
    public void send() {
        if (Validation.countWordsInTextWithCommas(textRecipients.getText()) > 24) {
            PopOverBuilder.showStatementValidation(textRecipients, "Max. 24 recipienist!");

            return;
        } else if (textRecipients.getText().length() == 0) {
            PopOverBuilder.showStatementValidation(textRecipients, "Please input...");

            return;
        } else if (textTopic.getText().length() == 0) {
            PopOverBuilder.showStatementValidation(textTopic, "Please input...");

            return;
        }

        String text = Validation.findLoginThatDoesNotExistInDatabase(textRecipients.getText());

        if (text != null) {
            PopOverBuilder.showStatementValidation(textRecipients, "This person (" + text + ") does not exist!");

            return;
        } else if ((text = Validation.findOnListYourLogin(person, textRecipients.getText())) != null) {
            PopOverBuilder.showStatementValidation(textRecipients, "(" + text + ") This is you!");

            return;
        } else if ((text = Validation.findRepetitionInTextWithCommas(textRecipients.getText())) != null) {
            PopOverBuilder.showStatementValidation(textRecipients, "(" + text + ") This is repeat!");

            return;
        }

        text = Validation.changeTextWithCommasOnTextWithPersonID(textRecipients.getText());

        Message message = new Message(person.getID(), text, textTopic.getText(), textContents.getText(), "sent");

        //maskerPane.setText("Posting...");
        //showMaskerPaneForSaved(-1);
        if (Database.sendMessage(message)) {
            Database.addLogger(person.getID(), "You sent a message to " + Database.changeIDOnLogin(Integer.parseInt(message.getID_Recipients())) + ".");
            if (person.isNotifications())
                NotificationBuilder.showNotificationSuccessOperation("Send message",
                        "The message was sent successfully.");
        } else {
            labelStatusMessage.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 13px;");
            labelStatusMessage.setText("The message was not sent!");

            if (person.isNotifications())
                NotificationBuilder.showNotificationFailedOperation("Send message",
                        "The message was not sent successfully.");

            maskerPane.setVisible(false);
        }
    }

    @FXML
    public void save() {
        if (textTopic.getText().length() == 0) {
            PopOverBuilder.showStatementValidation(textTopic, "Please input...");

            return;
        }

        Message message = new Message(person.getID(), textRecipients.getText(), textTopic.getText(), textContents.getText(), "saved");

        maskerPane.setText("Saving...");
        showMaskerPaneForSaved(-1);
        if (Database.saveMessage(message)) {
            labelStatusMessage.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 14px;");
            labelStatusMessage.setText("The message was saved!");

            if (person.isNotifications())
                NotificationBuilder.showNotificationSuccessOperation("Save message",
                        "The message was saved successfully.");

            maskerPane.setText("Saved");
            showMaskerPaneForSaved(1);
        } else {
            labelStatusMessage.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 13px;");
            labelStatusMessage.setText("The message was not saved!");

            if (person.isNotifications())
                NotificationBuilder.showNotificationSuccessOperation("Save message",
                        "The message was not saved successfully.");

            maskerPane.setVisible(false);
        }
    }

    @FXML
    public void addPersonToList() {
        if (textSearchPerson.getText().length() == 0) {
            PopOverBuilder.showStatementValidation(textSearchPerson, "Please input...");

            return;
        } else if (Database.checkLogin(textSearchPerson.getText()) && Database.checkEmail(textSearchPerson.getText())) {
            PopOverBuilder.showStatementValidation(textSearchPerson, "This person does not exist!");

            return;
        } else if (listPersonOnList.size() == 24) {
            PopOverBuilder.showInfoForSendMessage(popOver, imageViewQuestionMark);
            PopOverBuilder.showStatementValidation(textRecipients, "Max. 24 recipienist!");

            return;
        }

        PersonOnList personOnList = Database.changeOnPersonOnList(textSearchPerson.getText());

        if (Validation.isIDPersonOnList(listPersonOnList, personOnList)) {
            PopOverBuilder.showStatementValidation(textSearchPerson, "This person is on the list!");

            return;
        } else if (Validation.isYourTextInTextWithCommas(personOnList.getLogin(), textRecipients.getText())) {
            PopOverBuilder.showStatementValidation(textSearchPerson, "This person is in recipients!");

            return;
        } else if (personOnList.getID() == person.getID()) {
            PopOverBuilder.showStatementValidation(textSearchPerson, "This is you!");

            return;
        }

        listPersonOnList.add(personOnList);

        listPersons.getItems().add(personOnList.getName() + " " + personOnList.getLastName());

        if (textRecipients.getText().length() == 0)
            textRecipients.setText(personOnList.getLogin().trim());
        else if (textRecipients.getText().length() > 0)
            textRecipients.setText(textRecipients.getText() + " , " + personOnList.getLogin().trim());

        textSearchPerson.setText("");
    }

    @FXML
    public void setToSearch() {
        switch (comboSettingsSearch.getSelectionModel().getSelectedIndex()) {
            case 0: {
                autoCompletionBinding.dispose();
                autoCompletionBinding = TextFields.bindAutoCompletion(textSearchPerson, Database.loadLoginsAndEmails());

                break;
            }
            case 1: {
                autoCompletionBinding.dispose();
                autoCompletionBinding = TextFields.bindAutoCompletion(textSearchPerson, Database.loadLogins());

                break;
            }
            case 2: {
                autoCompletionBinding.dispose();
                autoCompletionBinding = TextFields.bindAutoCompletion(textSearchPerson, Database.loadEmails());

                break;
            }
        }
    }

    @FXML
    public void updateList() {
        String text = Validation.findLoginThatDoesNotExistInDatabase(textRecipients.getText());

        if (text != null) {
            PopOverBuilder.showStatementValidation(textRecipients, "This person (" + text + ") does not exist!");

            return;
        } else if ((text = Validation.findOnListYourLogin(person, textRecipients.getText())) != null) {
            PopOverBuilder.showStatementValidation(textRecipients, "(" + text + ") This is you!");

            return;
        } else if ((text = Validation.findRepetitionInTextWithCommas(textRecipients.getText())) != null) {
            PopOverBuilder.showStatementValidation(textRecipients, "(" + text + ") This is repeat!");

            return;
        } else if (Validation.countWordsInTextWithCommas(textRecipients.getText()) > 24) {
            PopOverBuilder.showStatementValidation(textRecipients, "Max. 24 recipienist!");

            return;
        } else {
            Initialization.initJFXListViewPersonsOnList(listPersons, textRecipients.getText());
            Initialization.initListPersonOnList(listPersonOnList, textRecipients.getText());
        }
    }

    @FXML
    public void clear() {
        listPersons.getItems().clear();

        textRecipients.setText(Validation.deleteLoginsAndEmailsWithTextByID(textRecipients.getText(), listPersonOnList));

        if (textRecipients.getText() == null)
            textRecipients.setText("");

        listPersonOnList.clear();
    }

    @FXML
    public void showInfo() {
        PopOverBuilder.showInfoForSendMessage(popOver, imageViewQuestionMark);
    }

    @FXML
    public void showPopOverWhenMouseOnGridPane() {
        if (showPopOverOnStart)
            PopOverBuilder.showInfoForSendMessage(popOver, imageViewQuestionMark);
        showPopOverOnStart = false;
    }

    @FXML
    public void loadMessage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterTXT = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().addAll(extFilterTXT, extFilterPDF);

        File file = fileChooser.showOpenDialog(stage);

        String[] tabTitleAndContents = FileBuilder.loadMessage(file);

        if (tabTitleAndContents != null) {
            textTopic.setText(tabTitleAndContents[0]);
            textContents.setText(tabTitleAndContents[1]);
            NotificationBuilder.showNotificationSuccessOperation("Load message with stage",
                    "The message was loaded successfully.");
        } else {
            NotificationBuilder.showNotificationFailedOperation("Load message with stage",
                    "The message was not loaded successfully.");
        }
    }

    @FXML
    public void saveOnStage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterTXT = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().addAll(extFilterTXT, extFilterPDF);
        File file = fileChooser.showSaveDialog(stage);

        try {
            if (file.createNewFile()) {
                if (FileBuilder.createFileMessageWithSend(file.getAbsolutePath(), textTopic.getText(),
                        textContents.getText())) {
                    NotificationBuilder.showNotificationSuccessOperation("Save message on computer",
                            "The message was saved successfully.");
                } else {
                    NotificationBuilder.showNotificationFailedOperation("Save message on computer",
                            "The message was not saved successfully.");
                }
            } else {
                NotificationBuilder.showNotificationFailedOperation("Save message on computer",
                        "The file was not created successfully.");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e1) { /* empty */ }
    }


    private void showMaskerPaneForSaved(int progress) {
        maskerPane.setProgress(progress);
        maskerPane.setVisible(true);

        if (maskerPane.getProgress() == 1) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                maskerPane.setVisible(false);
            }).start();
        }
    }


    // METHOD FOR WORKING COPY EDIT
    public void setTextFieldsForWorkingCopy(MessageWorkingCopy message) {
        textRecipients.setText(message.getRecipient());
        textTopic.setText(message.getTopic());
        textContents.setText(message.getContents());
    }

    // METHOD FOR SENT MESSAGE SEND AGAIN
    public void setTextFieldsForSentMessage(MessageSent message) {
        textRecipients.setText(message.getRecipient());
        textTopic.setText(message.getTopic());
        textContents.setText(message.getContents());
    }

    // METHOD FOR SENT MESSAGE SEND AGAIN
    public void setTextFieldsForSearch(int ID) {
        textRecipients.setText(Database.changeIDOnLogin(ID));
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
