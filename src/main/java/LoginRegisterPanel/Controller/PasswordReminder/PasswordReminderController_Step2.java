package LoginRegisterPanel.Controller.PasswordReminder;

import Builder.PopOverBuilder;
import Builder.PopupBuilder;
import Database.Database;
import LoadFXML.LoadFXML;
import LoginRegisterPanel.Controller.Login.LoginController;
import LoginRegisterPanel.Controller.MainController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Jonatan on 2017-01-06.
 */
public class PasswordReminderController_Step2 {

    @FXML
    private Pane pane;
    @FXML
    private JFXTextField textQuestion;
    @FXML
    private JFXTextField textAnswer;
    @FXML
    private JFXTextField textYearOfBirth;
    @FXML
    private Label labelInvalidData;
    @FXML
    private JFXButton btnClear;
    @FXML
    private Label labelStep;
    @FXML
    private JFXPopup popupMenu;

    private MainController mainController;
    private LoginController loginController;
    private Stage mainStage;
    private Locale locale;
    private PasswordReminderController_Step1 passwordReminderController_step1;
    private int ID;

    private boolean isPopupInitialization = false;

    static private final String BUNDLE_PASSWORD_REMINDER = "ResourceBundle.LoginRegisterReminder.PasswordReminder";
    static private final String FXML_PASSWORD_REMINDER = "/LoginRegisterPanel/PasswordReminder/PasswordReminder_Step3.fxml";

    @FXML
    public void initialize() {
        textQuestion.setText(Database.loadLeadingQuestion(PasswordReminderController_Step1.ID));
    }

    @FXML
    public void back() {
        loginController.passwordReminder();
    }

    @FXML
    public void next() {
        if (!Database.checkAnswer(ID, textAnswer.getText()) || !Database.checkYear(ID, textYearOfBirth.getText())) {
            btnClear.setVisible(true);
            labelInvalidData.setVisible(true);

            return;
        } else {
            btnClear.setVisible(false);
            labelInvalidData.setVisible(false);
        }

        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_PASSWORD_REMINDER, BUNDLE_PASSWORD_REMINDER, locale);

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PasswordReminderController_Step3 passwordReminderController = loader.getController();
        passwordReminderController.setMainController(mainController);
        passwordReminderController.setMainStage(mainStage);
        passwordReminderController.setLocale(locale);
        passwordReminderController.setPasswordReminderController_step1(passwordReminderController_step1);
        passwordReminderController.setPasswordReminderController_step2(this);
        passwordReminderController.setID(ID);

        mainController.setScreen(pane);
    }

    @FXML
    public void clear() {
        btnClear.setVisible(false);
        labelInvalidData.setVisible(false);
        textAnswer.setText("");
        textYearOfBirth.setText("");
    }

    @FXML
    public void backToLogin() {
        mainController.loadPanelLogin();
    }

    @FXML
    public void mouseEnteredOnLabelStep() {
        PopOverBuilder.showStepsForChangePassword(labelStep, 2);
    }

    @FXML
    public void showPopupMenu(MouseEvent e) {
        if (!isPopupInitialization) {
            isPopupInitialization = true;
            PopupBuilder.initPopupMenuForChangePassword(popupMenu, pane, passwordReminderController_step1);
        }

        if (e.getButton() == MouseButton.SECONDARY)
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

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setPasswordReminderController_step1(PasswordReminderController_Step1 passwordReminderController_step1) {
        this.passwordReminderController_step1 = passwordReminderController_step1;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
