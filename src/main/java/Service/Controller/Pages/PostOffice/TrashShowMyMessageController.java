package Service.Controller.Pages.PostOffice;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import FileBuilder.FileBuilder;
import Service.LoadPage.LoadPage;
import Service.Message.MessageTrash;
import Service.Person.Person;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jonatan on 2017-01-31.
 */
public class TrashShowMyMessageController {

    @FXML
    private Label labelStatus, labelDate, labelDateRemoved;
    @FXML
    private JFXTextField textRecipients, textTopic, textSearchByWord;
    @FXML
    private JFXTextArea textContents;
    @FXML
    private JFXSlider sliderFontSize;
    @FXML
    private TextField textFontSize;

    private Person person;
    private Stage stage;
    private MessageTrash message;

    @FXML
    public void restore() {
        if (message.getPreviousStatus().equals("received"))
            message.setPreviousStatus("sent");

        if (Database.restoreMessage(message.getID(), message.getPreviousStatus())) {
            if (person.isNotifications())
                NotificationBuilder.showNotificationSuccessOperation("Restore message",
                        "The message was restored successfully.");
        } else {
            if (person.isNotifications())
                NotificationBuilder.showNotificationFailedOperation("Restore message",
                        "The message was not restored successfully.");
        }

        new LoadPage().loadTrash(person);
    }

    @FXML
    public void delete() {
        if (Database.constDeleteMessage(message.getID())) {
            if (person.isNotifications())
                NotificationBuilder.showNotificationSuccessOperation("Delete message",
                        "The message was deleted successfully.");
        } else {
            if (person.isNotifications())
                NotificationBuilder.showNotificationFailedOperation("Delete message",
                        "The message was not deleted successfully.");
        }

        new LoadPage().loadTrash(person);
    }

    @FXML
    public void back() {
        new LoadPage().loadTrash(person);
    }

    public void initialization() {
        textRecipients.setText(message.getSenderOrRecipient());
        textTopic.setText(message.getTopic());
        textContents.setText(message.getContents());
        labelStatus.setText(message.getPreviousStatus());
        labelDate.setText(message.getDate());
        labelDateRemoved.setText(message.getDateRemoved());
    }

    @FXML
    public void searchByWord() {
        if (textContents.getText().indexOf(textSearchByWord.getText()) != -1) {
            textContents.selectRange(textContents.getText().indexOf(textSearchByWord.getText()),
                    (textContents.getText().indexOf(textSearchByWord.getText()) + textSearchByWord.getLength()));
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
                if (FileBuilder.createFileMessageWithTrash(file.getAbsolutePath(), textTopic.getText(),
                        textContents.getText(), labelStatus.getText(), labelDate.getText(),
                        labelDateRemoved.getText())) {
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

    public void initReactionOnSlider() {
        sliderFontSize.setValue(15);
        textContents.setStyle("-fx-font-size: 15px;");
        sliderFontSize.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            textContents.setStyle("-fx-font-size:" + sliderFontSize.getValue() + "px;");
            textFontSize.setText(String.valueOf((int) sliderFontSize.getValue()));
        });
        textFontSize.setText(String.valueOf((int) sliderFontSize.getValue()));
    }

    @FXML
    public void keyReleasedInTextFontSize() {
        try {
            if (Double.valueOf(textFontSize.getText()) > 100) {
                PopOverBuilder.showStatementValidation(textFontSize, "Max 100");
            }
        } catch (NumberFormatException e) {
            textFontSize.setText(String.valueOf((int) sliderFontSize.getValue()));
            return;
        }

        sliderFontSize.setValue(Double.valueOf(textFontSize.getText()));
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMessage(MessageTrash messageTrash) {
        this.message = messageTrash;
    }
}
