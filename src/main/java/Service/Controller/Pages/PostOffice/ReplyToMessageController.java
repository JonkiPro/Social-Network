package Service.Controller.Pages.PostOffice;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import FileBuilder.FileBuilder;
import Service.LoadPage.LoadPage;
import Service.Message.Message;
import Service.Message.MessageReadAndReceived;
import Service.Person.Person;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.io.File;
import java.util.List;

/**
 * Created by Jonatan on 2017-01-31.
 */
public class ReplyToMessageController {

    @FXML
    private JFXButton btnSender;
    @FXML
    private JFXTextField textTopic;
    @FXML
    private JFXTextArea textContents, textYourContents;

    private Person person;
    private Stage stage;
    private MessageReadAndReceived message;

    private PopOver popOverProfile;

    @FXML
    public void reply() {
        if(textYourContents.getText().length() == 0) {
            PopOverBuilder.showStatementValidation(textYourContents, "Please input...");

            return;
        }

        if (Database.sendMessage(new Message(person.getID(),
                String.valueOf(Database.changeLoginOnID(message.getSender())),
                message.getTopic(),
                textYourContents.getText(),
                "sent"))) {
            Database.addLogger(person.getID(), "You sent a message to " + message.getSender() + ".");

            if (person.isNotifications())
                NotificationBuilder.showNotificationSuccessOperation("Send message",
                        "The message was sent successfully.");
        }


        if (message.getStatus().equals("read"))
            new LoadPage().loadReadMessage(person);
        else if (message.getStatus().equals("sent")) {
            new LoadPage().loadReceivedMessage(person);
        }
    }

    @FXML
    public void back() {
        if (message.getStatus().equals("read"))
            new LoadPage().loadReadMessage(person);
        else if (message.getStatus().equals("sent")) {
            new LoadPage().loadReceivedMessage(person);
        }
    }

    public void initialization() {
        btnSender.setText(message.getSender());
        textTopic.setText(message.getTopic());
        textContents.setText(message.getContents());
    }

    @FXML
    public void loadMessage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterTXT = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().addAll(extFilterTXT, extFilterPDF);

        File file = fileChooser.showOpenDialog(stage);

        try {
            List<String> listTextMessage = FileBuilder.loadTextToMessage(file.getAbsolutePath());
            for (int i = 0; i < listTextMessage.size(); ++i) {
                if (i == 0)
                    textYourContents.setText(listTextMessage.get(i));
                else
                    textYourContents.setText(textYourContents.getText() + "\n" + listTextMessage.get(i));
            }
            NotificationBuilder.showNotificationSuccessOperation("Load message",
                    "The message was loaded successfully.");
        } catch (NullPointerException e) { /* empty */ }
    }

    @FXML
    public void showProfileSender() {
        if (popOverProfile == null)
            popOverProfile = new PopOver();

        Image avatarSender;
        try {
            avatarSender = new Image(Database.getAvatar(Database.changeLoginOnID(message.getSender())));
        } catch (NullPointerException e) {
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
