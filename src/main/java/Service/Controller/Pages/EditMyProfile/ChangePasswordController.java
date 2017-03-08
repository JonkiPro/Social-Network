package Service.Controller.Pages.EditMyProfile;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.PopOver;

/**
 * Created by Jonatan on 2017-01-24.
 */
public class ChangePasswordController {

    @FXML
    private JFXPasswordField textOldPassword, textNewPassword, textRepeatNewPassword;
    @FXML
    private JFXTextField textNewPasswordAfterShow, textRepeatNewPasswordAfterShow;
    @FXML
    private Label labelStrengthPassword;
    @FXML
    private JFXButton btnChange;
    @FXML
    private ImageView imageViewQuestionMark;

    private PopOver popOver;

    private Person person;

    private int numberOfCharactersInPassword;
    private int strengthPassword;
    private char characterForException;

    @FXML
    public void initialize() {
        labelStrengthPassword.visibleProperty().bind(Bindings.isNotEmpty(textNewPassword.textProperty()));
        btnChange.disableProperty().bind(Bindings.isEmpty(textRepeatNewPassword.textProperty()));
    }

    @FXML
    public void change() {
        if (person.getPassword().equals(textOldPassword.getText())) {
            if (Validation.checkPassword(textNewPassword.getText())) {
                if (Validation.checkRepeatPassword(textNewPassword.getText(), textRepeatNewPassword.getText())) {
                    if (Database.changePassword(person.getID(), textNewPassword.getText())) {
                        person.setPassword(textNewPassword.getText());
                        Database.addLogger(person.getID(), "Changed password.");
                        if (person.isNotifications())
                            NotificationBuilder.showNotificationSuccessOperation("Change password",
                                    "Password has been changed successfully.");

                        new LoadPage().loadGridPaneSuccessEdition(person);
                    }
                } else {
                    PopOverBuilder.showStatementValidation(textRepeatNewPassword, "Passwords are not the same!");
                }
            } else {
                PopOverBuilder.showStatementValidation(textNewPassword, "Wrong length!");
            }
        } else {
            PopOverBuilder.showStatementValidation(textOldPassword, "The password is incorrect!");
        }
    }

    @FXML
    public void back() {
        new LoadPage().loadEditMyProfile(person);
    }

    @FXML
    public void keyPressedInTextPassword(KeyEvent e) {
        if (textNewPassword.getText().length() == 0) {
            numberOfCharactersInPassword = 0;
            strengthPassword = 0;
        } else if (textNewPassword.getText().length() == 1) {
            numberOfCharactersInPassword = 1;
            strengthPassword = 1;
        }

        try {
            strengthPassword = Validation.setPointValueForPassword(textNewPassword.getText() + e.getText());

            characterForException = e.getText().charAt(0);

            ++numberOfCharactersInPassword;
        } catch (StringIndexOutOfBoundsException e1) {
            if (e.getCode() == KeyCode.BACK_SPACE) {
                --numberOfCharactersInPassword;

                if (numberOfCharactersInPassword > 0) {
                    strengthPassword = Validation.setPointValueForPassword(textNewPassword.getText().substring(0, textNewPassword.getText().length() - 1));

                    if (numberOfCharactersInPassword == 0) {
                        strengthPassword = 0;

                        textRepeatNewPassword.setDisable(true);
                    }
                }
            }
        }

        if (numberOfCharactersInPassword >= 6)
            textRepeatNewPassword.setDisable(false);
        else
            textRepeatNewPassword.setDisable(true);

        if (strengthPassword > 0 && strengthPassword < 15) {
            labelStrengthPassword.setText("easy");
            labelStrengthPassword.setStyle("-fx-text-fill: red;");
        } else if (strengthPassword >= 15 && strengthPassword < 30) {
            labelStrengthPassword.setText("medium");
            labelStrengthPassword.setStyle("-fx-text-fill: orange;");
        } else if (strengthPassword >= 30) {
            labelStrengthPassword.setText("strong");
            labelStrengthPassword.setStyle("-fx-text-fill: green;");
        }
    }

    @FXML
    public void showPassword() {
        textNewPasswordAfterShow.setVisible(true);
        textRepeatNewPasswordAfterShow.setVisible(true);
        textNewPassword.setVisible(false);
        textRepeatNewPassword.setVisible(false);
    }

    @FXML
    public void hiddenPassword() {
        textNewPassword.setVisible(true);
        textRepeatNewPassword.setVisible(true);
        textNewPasswordAfterShow.setVisible(false);
        textRepeatNewPasswordAfterShow.setVisible(false);
    }

    @FXML
    public void showInfo() {
        if (popOver == null)
            popOver = new PopOver();

        PopOverBuilder.showInfoForEditPassword(popOver, imageViewQuestionMark);
    }

    @FXML
    public void clear() {
        textOldPassword.setText("");
        textNewPassword.setText("");
        textRepeatNewPassword.setText("");
    }

    @FXML
    public void openQuestionAndAnswer() {
        new LoadPage().loadChangeQuestionAndAnswer(person);
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void bindPerson() {
        textNewPasswordAfterShow.textProperty().bind(textNewPassword.textProperty());
        textRepeatNewPasswordAfterShow.textProperty().bind(textRepeatNewPassword.textProperty());
    }
}
