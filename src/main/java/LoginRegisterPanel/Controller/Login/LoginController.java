package LoginRegisterPanel.Controller.Login;

import Builder.PopOverBuilder;
import Builder.PopupBuilder;
import Database.Database;
import FileBuilder.FileBuilder;
import LoadFXML.LoadFXML;
import LoginRegisterPanel.Controller.MainController;
import LoginRegisterPanel.Controller.PasswordReminder.PasswordReminderController_Step1;
import LoginRegisterPanel.Controller.Register.RegisterController_Step1;
import LoginRegisterPanel.Initialization.Initialization;
import Service.Person.Person;
import Service.ServiceWindow;
import Statement.Statement;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Jonatan on 2016-12-16.
 */
public class LoginController {

    @FXML
    private Pane pane;
    @FXML
    private JFXComboBox<String> comboLanguages;
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelPassword;
    @FXML
    private JFXCheckBox checkBoxRememberPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnRegister;
    @FXML
    private Hyperlink hyperlinkForgotPassword;
    @FXML
    private Label labelConnectionToServer;
    @FXML
    private Label labelConnectionOK;
    @FXML
    private Label labelConnectionFailed;
    @FXML
    private JFXSpinner loadSpinner;
    @FXML
    private JFXTextField textLogin;
    @FXML
    private JFXPasswordField textPassword;
    @FXML
    private JFXPopup popupMenu;

    private MainController mainController;
    private static Stage mainStage;
    private Locale locale;
    private static String selectedLanguage = null;

    private boolean isPopupInitialization = false;

    static private final String BUNDLE_LOGIN = "ResourceBundle.LoginRegisterReminder.Register";
    static private final String BUNDLE_PASSWORD_REMINDER = "ResourceBundle.LoginRegisterReminder.PasswordReminder";
    static private final String FXML_REGISTER = "/LoginRegisterPanel/Register/Register_Step1.fxml";
    static private final String FXML_PASSWORD_REMINDER = "/LoginRegisterPanel/PasswordReminder/PasswordReminder_Step1.fxml";

    @FXML
    public void initialize() {
        initLanguages();
        loadSpinner.setVisible(true);

        new Thread(() -> {
            if (Database.dbConnect("jdbc:sqlserver://localhost:1433;databaseName=Social Network;selectMethod=cursor", "Admin", "admin1")) {
                loadSpinner.setVisible(false);
                labelConnectionOK.setVisible(true);
                labelConnectionFailed.setVisible(false);
                btnLogin.setDisable(false);
                btnRegister.setDisable(false);
                hyperlinkForgotPassword.setDisable(false);
            } else {
                loadSpinner.setVisible(false);
                labelConnectionFailed.setVisible(true);
                labelConnectionOK.setVisible(false);
                btnLogin.setDisable(true);
                btnRegister.setDisable(true);
                hyperlinkForgotPassword.setDisable(true);
            }
        }).start();

        if (FileBuilder.isFileRememberPassword()) {
            FileBuilder.initDataFromFileRememberPassword(textLogin, textPassword);
        }
    }

    @FXML
    public void login() {
        Person person = Database.login(textLogin.getText(), textPassword.getText());

        if (person != null) {
            if (checkBoxRememberPassword.isSelected()) {
                FileBuilder.createFileRememberPassword(textLogin.getText(), textPassword.getText());
            } else {
                new Statement().showMessageRememberPassword(textLogin.getText(), textPassword.getText());
            }

            mainStage.close();

            new ServiceWindow(person).start();


        } else {
            System.out.print("no");
        }
    }

    @FXML
    public void register() {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_REGISTER, BUNDLE_LOGIN, locale);

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RegisterController_Step1 registerController = loader.getController();
        registerController.setMainController(mainController);
        registerController.setLoginController(this);
        registerController.setLocale(locale);

        mainController.setScreen(pane);
    }

    @FXML
    public void passwordReminder() {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_PASSWORD_REMINDER, BUNDLE_PASSWORD_REMINDER, locale);

        Pane pane = null;

        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PasswordReminderController_Step1 passwordReminderController = loader.getController();
        passwordReminderController.setMainController(mainController);
        passwordReminderController.setMainStage(mainStage);
        passwordReminderController.setLocale(locale);
        passwordReminderController.setLoginController(this);

        mainController.setScreen(pane);
    }

    @FXML
    public void changeLanguage() {
        loadLang(comboLanguages.getSelectionModel().getSelectedItem());
        selectedLanguage = comboLanguages.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void mouseEnteredOnLabelConnectionStatus() {
        PopOverBuilder.showInfoServerStatus(labelConnectionOK, labelConnectionFailed);
    }

    @FXML
    public void showPopupMenu(MouseEvent e) {
        if (!isPopupInitialization) {
            isPopupInitialization = true;
            PopupBuilder.initPopupMenuForLogin(popupMenu, pane, mainController);
        }

        if (e.getButton() == MouseButton.SECONDARY) {
            popupMenu.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, e.getX(), e.getY());
        }
    }


    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    static public void setStage(Stage stage) {
        mainStage = stage;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    private void initLanguages() {
        Initialization.initLanguages(comboLanguages);

        if (selectedLanguage == null) {
            comboLanguages.getSelectionModel().select(0);
        } else {
            comboLanguages.getSelectionModel().select(selectedLanguage);
        }
    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle.LoginRegisterReminder.Login", locale);
        mainController.setLocale(locale);

        labelLogin.setText(bundle.getString("label.login"));
        labelPassword.setText(bundle.getString("label.password"));
        checkBoxRememberPassword.setText(bundle.getString("checkBox.rememberPassword"));
        btnLogin.setText(bundle.getString("button.login"));
        btnRegister.setText(bundle.getString("button.register"));
        hyperlinkForgotPassword.setText(bundle.getString("hyperlink.forgotPassword"));
        labelConnectionToServer.setText(bundle.getString("label.connectionToServer"));
        labelConnectionFailed.setText(bundle.getString("label.connectionFailed"));
        textPassword.setPromptText(bundle.getString("label.password2"));

    }

}
