package LoginRegisterPanel.Controller.PasswordReminder;

import Builder.PopOverBuilder;
import Builder.PopupBuilder;
import Database.Database;
import LoadFXML.LoadFXML;
import LoginRegisterPanel.Controller.MainController;
import Validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Jonatan on 2017-01-07.
 */
public class PasswordReminderController_Step3 {

    @FXML
    Pane pane;
    @FXML
    JFXPasswordField textNewPassword;
    @FXML
    JFXPasswordField textRepeatNewPassword;
    @FXML
    JFXTextField textNewPasswordAfterShow;
    @FXML
    JFXTextField textRepeatNewPasswordAfterShow;
    @FXML
    Label labelStrengthPassword;
    @FXML
    Label labelNOTOK1;
    @FXML
    Label labelNOTOK2;
    @FXML
    JFXButton btnClear;
    @FXML
    JFXButton btnChange;
    @FXML
    Label labelStep;
    @FXML
    JFXPopup popupMenu;

    private MainController mainController;
    private Stage mainStage;
    private PasswordReminderController_Step1 passwordReminderController_step1;
    private PasswordReminderController_Step2 passwordReminderController_step2;
    private int ID;

    static public Locale locale;

    private PopOver popOver;

    private boolean isPopupInitialization = false;

    public int numberOfCharactersInPassword = 0;
    public int strengthPassword = 0;
    public char characterForException;

    static private final String BUNDLE_PASSWORD_REMINDER = "ResourceBundle.LoginRegisterReminder.PasswordReminder";
    static private final String FXML_PASSWORD_REMINDER = "/LoginRegisterPanel/PasswordReminder/PasswordReminder_Successfully.fxml";

    @FXML
    public void initialize() {
        textNewPassword.textProperty().bindBidirectional(textNewPasswordAfterShow.textProperty());
        textRepeatNewPassword.textProperty().bindBidirectional(textRepeatNewPasswordAfterShow.textProperty());
        labelStrengthPassword.visibleProperty().bind(Bindings.isNotEmpty(textNewPassword.textProperty()));
        btnChange.disableProperty().bind(Bindings.isEmpty(textRepeatNewPassword.textProperty()));
    }

    @FXML
    public void back() {
        passwordReminderController_step1.next();
    }

    @FXML
    public void change() {
        if(Validation.checkPassword(textNewPassword.getText())) {
            if(Validation.checkRepeatPassword(textNewPassword.getText(), textRepeatNewPassword.getText()) == false) {
                PopOverBuilder.showStatementValidation(textRepeatNewPassword, "Passwords are not the same.");
                labelNOTOK2.setVisible(true);
                labelNOTOK1.setVisible(false);
                btnClear.setVisible(true);

                return;
            }
        } else {
            PopOverBuilder.showStatementValidation(textNewPassword, "Wrong length.");
            labelNOTOK1.setVisible(true);
            labelNOTOK2.setVisible(false);
            btnClear.setVisible(true);

            return;
        }

        if(Database.changePassword(ID, textNewPassword.getText()) == false) {

        } else {
            FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_PASSWORD_REMINDER, BUNDLE_PASSWORD_REMINDER, locale);

            Pane pane = null;
            try {
                pane = loader.load();
            } catch(IOException e) {
                e.printStackTrace();
            }

            PasswordReminderController_Successfully passwordReminderController = loader.getController();
            passwordReminderController.setMainController(mainController);
            passwordReminderController.setMainStage(mainStage);

            mainController.setScreen(pane);
        }
    }

    @FXML
    public void clear() {
        btnClear.setVisible(false);
        textNewPassword.setText("");
        textRepeatNewPassword.setText("");
        labelNOTOK1.setVisible(false);
        labelNOTOK2.setVisible(false);
    }

    @FXML
    public void showPassword() {
        textNewPassword.setVisible(false);
        textRepeatNewPassword.setVisible(false);
        textNewPasswordAfterShow.setVisible(true);
        textRepeatNewPasswordAfterShow.setVisible(true);
    }

    @FXML
    public void hiddenPassword() {
        textNewPassword.setVisible(true);
        textRepeatNewPassword.setVisible(true);
        textNewPasswordAfterShow.setVisible(false);
        textRepeatNewPasswordAfterShow.setVisible(false);
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
        } catch(StringIndexOutOfBoundsException e1) {
            if(e.getCode() == KeyCode.BACK_SPACE) {
                --numberOfCharactersInPassword;

                if(numberOfCharactersInPassword > 0) {
                    strengthPassword=Validation.setPointValueForPassword(textNewPassword.getText().substring(0, textNewPassword.getText().length()-1));

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

        if(strengthPassword > 0 && strengthPassword < 15) {
            labelStrengthPassword.setText("easy");
            labelStrengthPassword.setStyle("-fx-text-fill: red;");
        } else if(strengthPassword >= 15 && strengthPassword < 30) {
            labelStrengthPassword.setText("medium");
            labelStrengthPassword.setStyle("-fx-text-fill: orange;");
        } else if(strengthPassword >= 30) {
            labelStrengthPassword.setText("strong");
            labelStrengthPassword.setStyle("-fx-text-fill: green;");
        }
    }

    @FXML
    public void backToLogin() {
        mainController.loadPanelLogin();
    }

    @FXML
    public void showInfo() {
        if(popOver == null)
            popOver = new PopOver();

        PopOverBuilder.showInfoForRegisterPassword(popOver, textNewPassword);
    }

    @FXML
    public void mouseEnteredOnLabelStep() {
        PopOverBuilder.showStepsForChangePassword(labelStep, 3);
    }


    @FXML
    public void showPopupMenu(MouseEvent e) {
        if(isPopupInitialization == false) {
            isPopupInitialization = true;
            PopupBuilder.initPopupMenuForChangePassword(popupMenu, pane, passwordReminderController_step2);
        }

        if(e.getButton() == MouseButton.SECONDARY)
            popupMenu.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, e.getX(), e.getY());
    }



    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setPasswordReminderController_step1(PasswordReminderController_Step1 passwordReminderController_step1) {
        this.passwordReminderController_step1 = passwordReminderController_step1;
    }
    public void setPasswordReminderController_step2(PasswordReminderController_Step2 passwordReminderController_step2) {
        this.passwordReminderController_step2 = passwordReminderController_step2;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
