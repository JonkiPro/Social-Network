package Service.Controller.Pages.PostOffice;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import FileBuilder.FileBuilder;
import Service.LoadPage.LoadPage;
import Service.Message.MessageSent;
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
public class SentMessageShowMessageController {

    @FXML
    private Label labelDate;
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
    private MessageSent message;

    @FXML
    public void sendAgain() {
        new LoadPage().loadSentMessageEditMessage(person, message);
    }

    @FXML
    public void delete() {
        if (Database.deleteMessage(message.getID(), message.getStatus())) {
            if (person.isNotifications())
                NotificationBuilder.showNotificationSuccessOperation("Delete message",
                        "The message was deleted successfully.");
        } else {
            if (person.isNotifications())
                NotificationBuilder.showNotificationFailedOperation("Delete message",
                        "The message was not deleted successfully.");
        }

        new LoadPage().loadSentMessage(person);
    }

    @FXML
    public void back() {
        new LoadPage().loadSentMessage(person);
    }

    public void initialization() {
        textRecipients.setText(message.getRecipient());
        textTopic.setText(message.getTopic());
        textContents.setText(message.getContents());
        labelDate.setText(message.getPostDate());
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
                if (FileBuilder.createFileMessage(file.getAbsolutePath(), textTopic.getText(),
                        textContents.getText(), labelDate.getText())) {
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

    public void setMessage(MessageSent messageSent) {
        this.message = messageSent;
    }
}
