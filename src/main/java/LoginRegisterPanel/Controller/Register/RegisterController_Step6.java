package LoginRegisterPanel.Controller.Register;

import Database.Database;
import LoadFXML.LoadFXML;
import LoginRegisterPanel.Controller.MainController;
import LoginRegisterPanel.Initialization.Initialization;
import LoginRegisterPanel.Person.PersonToRegister;
import Validation.Validation;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Jonatan on 2016-12-16.
 */
public class RegisterController_Step6 {

    @FXML
    private Label labelCAPTCHA;
    @FXML
    private JFXTextField textCAPTCHA;
    @FXML
    private Label labelNOTOK;

    private MainController mainController;
    private RegisterController_Step4 registerController_step4;
    private PersonToRegister person;

    static public Locale locale;

    static private final String BUNDLE_REGISTER = "ResourceBundle.LoginRegisterReminder.Register";
    static private final String FXML_REGISTER_SUCCESSFULLY = "/LoginRegisterPanel/Register/Register_Successfully.fxml";
    static private final String FXML_REGISTER_FAILED = "/LoginRegisterPanel/Register/Register_Failed.fxml";

    @FXML
    public void initialize() {
        Initialization.initCAPTCHA(labelCAPTCHA);
    }

    @FXML
    public void back() {
        registerController_step4.next();
    }

    @FXML
    public void finish() {
        if (!Validation.checkCAPTCHA(labelCAPTCHA.getText(), textCAPTCHA.getText())) {
            labelNOTOK.setVisible(true);
            Initialization.initCAPTCHA(labelCAPTCHA);

            return;
        }

        FXMLLoader loader;
        Pane pane = null;
        if (Database.addPerson(person)) {
            loader = LoadFXML.loadFXML(getClass(), FXML_REGISTER_SUCCESSFULLY, BUNDLE_REGISTER, locale);

            try {
                pane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            RegisterController_Successfully registerController = loader.getController();
            registerController.setMainController(mainController);
            registerController.setLocale(locale);
        } else {
            loader = LoadFXML.loadFXML(getClass(), FXML_REGISTER_FAILED, BUNDLE_REGISTER, locale);

            try {
                pane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            RegisterController_Failed registerController = loader.getController();
            registerController.setMainController(mainController);
        }

        mainController.setScreen(pane);
    }

    @FXML
    public void refresh() {
        textCAPTCHA.setText("");
        labelNOTOK.setVisible(false);
        Initialization.initCAPTCHA(labelCAPTCHA);
    }

    @FXML
    public void backToLogin() {
        mainController.loadPanelLogin();
        Initialization.clearAllDataFromRegistration();
    }


    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setRegisterController_step4(RegisterController_Step4 registerController_step4) {
        this.registerController_step4 = registerController_step4;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setPerson(PersonToRegister person) {
        this.person = person;
    }
}
