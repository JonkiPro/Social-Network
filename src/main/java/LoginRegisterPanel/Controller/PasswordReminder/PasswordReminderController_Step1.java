package LoginRegisterPanel.Controller.PasswordReminder;

import Builder.DialogBuilder;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Jonatan on 2017-01-06.
 */
public class PasswordReminderController_Step1 {

    @FXML
    private Pane pane;
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXTextField textLogin;
    @FXML
    private Label labelLoginNotExists;
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

    static public int ID;

    private boolean isPopupInitialization = false;

    static private final String BUNDLE_PASSWORD_REMINDER = "ResourceBundle.LoginRegisterReminder.PasswordReminder";
    static private final String FXML_PASSWORD_REMINDER = "/LoginRegisterPanel/PasswordReminder/PasswordReminder_Step2.fxml";

    @FXML
    public void initialize() {
        DialogBuilder.showDialogForChangePassword(stackPane);
    }

    @FXML
    public void back() {
        mainController.loadPanelLogin();
    }

    @FXML
    public void next() {
        ID = Database.findIDForLogin(textLogin.getText());

        if (ID == 0) {
            labelLoginNotExists.setVisible(true);
            btnClear.setVisible(true);

            return;
        } else {
            labelLoginNotExists.setVisible(false);
            btnClear.setVisible(false);
        }

        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_PASSWORD_REMINDER, BUNDLE_PASSWORD_REMINDER, locale);

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PasswordReminderController_Step2 passwordReminderController = loader.getController();
        passwordReminderController.setMainController(mainController);
        passwordReminderController.setMainStage(mainStage);
        passwordReminderController.setLocale(locale);
        passwordReminderController.setLoginController(loginController);
        passwordReminderController.setPasswordReminderController_step1(this);
        passwordReminderController.setID(ID);

        mainController.setScreen(pane);
    }

    @FXML
    public void clear() {
        btnClear.setVisible(false);
        textLogin.setText("");
        labelLoginNotExists.setVisible(false);
    }

    @FXML
    public void mouseEnteredOnLabelStep() {
        PopOverBuilder.showStepsForChangePassword(labelStep, 1);
    }

    @FXML
    public void showPopupMenu(MouseEvent e) {
        if (!isPopupInitialization) {
            isPopupInitialization = true;
            PopupBuilder.initPopupMenuForChangePassword(popupMenu, pane, loginController);
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
}
