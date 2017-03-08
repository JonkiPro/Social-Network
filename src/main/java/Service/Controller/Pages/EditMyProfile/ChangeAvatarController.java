package Service.Controller.Pages.EditMyProfile;

import Builder.NotificationBuilder;
import Database.Database;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jonatan on 2017-03-03.
 */
public class ChangeAvatarController {

    @FXML
    private ImageView oldAvatar, newAvatar;

    private Person person;
    private Stage stage;

    private File fileImage;

    public void initAvatar() {
        oldAvatar.setImage((Image)person.getAvatar());
    }

    @FXML
    public void changeAvatar() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        fileImage = fileChooser.showOpenDialog(stage);

        try {
            BufferedImage bufferedImage = ImageIO.read(fileImage);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            newAvatar.setImage(image);
        } catch (IOException e) {
            fileImage = null;
        } catch (IllegalArgumentException e) {
            fileImage = null;
        }
    }

    @FXML
    public void change() {
        if(fileImage != null) {
            Database.changeAvatar(person.getID(), fileImage);
            Database.addLogger(person.getID(), "Changed avatar.");
            if (person.isNotifications()) {
                NotificationBuilder.showNotificationSuccessOperation("Change avatar",
                        "Avatar has been changed successfully.");
            }
        } else {
            if (person.isNotifications()) {
                NotificationBuilder.showNotificationFailedOperation("Change avatar",
                        "Avatar has been changed successfully.");
            }
            return;
        }

        oldAvatar.setImage(new Image(Database.getAvatar(person.getID())));
        person.setAvatar(new Image(Database.getAvatar(person.getID())));
    }

    @FXML
    public void back() {
        new LoadPage().loadOtherData(person);
    }

    @FXML
    public void clear() {
        newAvatar.setImage(new Image("/images/service/uploadImage.png"));
    }

    @FXML
    public void showInfo() {

    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
