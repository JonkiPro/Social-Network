package Service.Controller.Pages.PostOffice;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import FileBuilder.FileBuilder;
import Service.LoadPage.LoadPage;
import Service.Message.MessageReadAndReceived;
import Service.Person.Person;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jonatan on 2017-01-31.
 */
public class ShowMessageController {

    @FXML
    private JFXButton btnSender;
    @FXML
    private Label labelDate;
    @FXML
    private JFXTextField textTopic, textSearchByWord;
    @FXML
    private JFXTextArea textContents;
    @FXML
    private JFXSlider sliderFontSize;
    @FXML
    private TextField textFontSize;

    private Person person;
    private Stage stage;
    private MessageReadAndReceived message;

    private PopOver popOverProfile;

    @FXML
    public void reply() {
        new LoadPage().loadReplyToMessage(person, message);
    }

    @FXML
    public void delete() {
        Database.deleteMessage(message.getID(), message.getStatus());

        if (message.getStatus().equals("read"))
            new LoadPage().loadReadMessage(person);
        else if (message.getStatus().equals("sent"))
            new LoadPage().loadReceivedMessage(person);
    }

    @FXML
    public void back() {
        if (message.getStatus().equals("read"))
            new LoadPage().loadReadMessage(person);
        else if (message.getStatus().equals("sent"))
            new LoadPage().loadReceivedMessage(person);
    }

    public void initialization() {
        btnSender.setText(message.getSender());
        textTopic.setText(message.getTopic());
        textContents.setText(message.getContents());
        labelDate.setText(message.getDateReceived());
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
    public void goToProfileSender() {
        new LoadPage().loadProfile(Database.getPerson(Database.changeLoginOnID(message.getSender())), person);
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

    @FXML
    public void showProfileSender() {
        if (popOverProfile == null)
            popOverProfile = new PopOver();

        Image avatarSender;
        try {
            avatarSender = new Image(Database.getAvatar(Database.changeLoginOnID(message.getSender())));
        } catch( NullPointerException e) {
            avatarSender = new Image("/images/service/person.png");
        }

        PopOverBuilder.showBalloonWithProfile(popOverProfile, Database.changeLoginOnID(message.getSender()),
                avatarSender, Database.changeIDOnNameAndLastName(Database.changeLoginOnID(message.getSender())),
                person);

        popOverProfile.show(btnSender);
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMessage(MessageReadAndReceived messageReadAndReceived) {
        this.message = messageReadAndReceived;
    }
}
