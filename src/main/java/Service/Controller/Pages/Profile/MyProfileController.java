package Service.Controller.Pages.Profile;

import Builder.NotificationBuilder;
import Database.Database;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import com.jfoenix.controls.JFXButton;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jonatan on 2017-02-25.
 */
public class MyProfileController {

    @FXML
    private ImageView avatar;
    @FXML
    private Label textSomeInfoAboutMe;
    @FXML
    private JFXButton btnTimeline, btnInformation, btnFriends;
    @FXML
    private Label labelName, labelLastName, labelSecondName, labelDateOfBirth, labelLogin, labelEmail;
    @FXML
    private ScrollPane scrollPane;

    private Person person;
    private Stage stage;

    @FXML
    public void initialize() {
        btnTimeline.setDisable(true);
    }

    public void initAllData() {
        labelName.setText(person.getName());
        labelLastName.setText(person.getLastName());

        if (person.getSecondName() != null && person.getSecondName().length() > 0)
            labelSecondName.setText(person.getSecondName());
        else {
            labelSecondName.setStyle("-fx-text-fill: red;");
            labelSecondName.setText("empty");
        }

        if (person.getDateOfBirth() != null)
            labelDateOfBirth.setText(person.getDateOfBirth().toString());
        else {
            labelDateOfBirth.setStyle("-fx-text-fill: red;");
            labelDateOfBirth.setText("empty");
        }

        if (person.getAboutMe() != null)
            textSomeInfoAboutMe.setText(person.getAboutMe());
        else {
            textSomeInfoAboutMe.setStyle("-fx-text-fill: red;");
            textSomeInfoAboutMe.setText("empty");
        }

        labelLogin.setText(person.getLogin());
        labelEmail.setText(person.getEmail());

        avatar.imageProperty().bind(person.avatarProperty());
    }

    public void initTimeline() {
        new LoadPage().loadMyProfileTimeline(person, scrollPane);
    }

    @FXML
    public void edit() {
        new LoadPage().loadEditMyProfile(person);
    }

    @FXML
    public void changeAvatar() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        File file = fileChooser.showOpenDialog(stage);

        try {
            Database.changeAvatar(person.getID(), file);

            Database.addLogger(person.getID(), "Changed avatar.");

            if (person.isNotifications()) {
                NotificationBuilder.showNotificationSuccessOperation("Change avatar",
                        "Avatar has been changed successfully.");
            }

            person.setAvatar(new Image(Database.getAvatar(person.getID())));
        } catch (NullPointerException e) { /* empty */ } catch (IllegalArgumentException e) { /* empty */ }
    }

    @FXML
    public void editInfoAboutMe() {
        new LoadPage().loadChangeAboutMe(person);
    }

    @FXML
    public void showTimeline() {
        btnTimeline.setDisable(false);
        btnInformation.setDisable(true);
        btnFriends.setDisable(false);

        new LoadPage().loadMyProfile(person);
    }

    @FXML
    public void showInformation() {
        btnTimeline.setDisable(false);
        btnInformation.setDisable(true);
        btnFriends.setDisable(false);

        new LoadPage().loadMyProfileInformation(person, scrollPane);
    }

    @FXML
    public void showFriends() {
        btnTimeline.setDisable(false);
        btnInformation.setDisable(false);
        btnFriends.setDisable(true);

        new LoadPage().loadMyProfileFriends(person, scrollPane);
    }


    public void setScrollPane(Node node) {
        this.scrollPane.setContent(node);
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
