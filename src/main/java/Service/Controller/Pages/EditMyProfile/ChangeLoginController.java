package Service.Controller.Pages.EditMyProfile;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.controlsfx.control.PopOver;

/**
 * Created by Jonatan on 2017-01-23.
 */
public class ChangeLoginController {

    @FXML
    private JFXTextField textYourLogin, textLogin;
    @FXML
    private JFXButton btnChange;
    @FXML
    private Label labelOK;
    @FXML
    private ImageView imageViewQuestionMark;

    private PopOver popOver;

    private Person person;

    @FXML
    public boolean checkCorrectness() {
        if (Validation.checkLogin(textLogin.getText())) {
            if (Database.checkLogin(textLogin.getText())) {
                btnChange.setDisable(false);
                labelOK.setVisible(true);
            } else {
                PopOverBuilder.showStatementValidation(textLogin, "Login exist in the database!");
                btnChange.setDisable(true);
                labelOK.setVisible(false);

                return false;
            }
        } else {
            if (!Validation.checkLengthLogin(textLogin.getText())
                    && !Validation.checkFirstCharacterLogin(textLogin.getText())) {
                PopOverBuilder.showStatementValidation(textLogin, "Wrong length and wrong syntax!");
            } else if (!Validation.checkLengthLogin(textLogin.getText())) {
                PopOverBuilder.showStatementValidation(textLogin, "Wrong length!");
            } else if (!Validation.checkFirstCharacterLogin(textLogin.getText())) {
                PopOverBuilder.showStatementValidation(textLogin, "Wrong syntax!");
            } else if (Validation.isComma(textLogin.getText())) {
                PopOverBuilder.showStatementValidation(textLogin, "Wrong syntax. You can`t use comma(,)!");
            }
            btnChange.setDisable(true);
            labelOK.setVisible(false);

            return false;
        }

        return true;
    }

    @FXML
    public void change() {
        if (!checkCorrectness()) {
            btnChange.setDisable(true);
        } else {
            if (Database.changeLogin(person.getID(), textLogin.getText())) {
                person.setLogin(textLogin.getText());
                Database.addLogger(person.getID(), "Changed login.");
                if (person.isNotifications())
                    NotificationBuilder.showNotificationSuccessOperation("Change login",
                            "Login has been changed successfully.");

                new LoadPage().loadGridPaneSuccessEdition(person);
            }
        }
    }

    @FXML
    public void back() {
        new LoadPage().loadEditMyProfile(person);
    }

    @FXML
    public void showInfo() {
        if (popOver == null)
            popOver = new PopOver();

        PopOverBuilder.showInfoForEditLogin(popOver, imageViewQuestionMark);
    }

    @FXML
    public void clear() {
        textLogin.setText("");
        if (labelOK.isVisible())
            labelOK.setVisible(false);
        if (!btnChange.isDisable())
            btnChange.setDisable(true);
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void bindPerson() {
        textYourLogin.textProperty().bind(person.loginProperty());
    }

}
