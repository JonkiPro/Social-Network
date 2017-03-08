package LoginRegisterPanel.Controller.Register;

import Builder.DialogBuilder;
import Builder.PopOverBuilder;
import Builder.PopupBuilder;
import Database.Database;
import LoadFXML.LoadFXML;
import LoginRegisterPanel.Controller.Login.LoginController;
import LoginRegisterPanel.Controller.MainController;
import LoginRegisterPanel.Initialization.Initialization;
import LoginRegisterPanel.Person.PersonToRegister;
import Validation.Validation;
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
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Jonatan on 2016-12-16.
 */
public class RegisterController_Step1 {

    @FXML
    private Pane pane;
    @FXML
    private StackPane stackPane;
    @FXML
    private Label labelOK;
    @FXML
    private Label labelNOTOK;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXTextField textLogin;
    @FXML
    private Label labelLoginExists;
    @FXML
    private JFXButton btnNext;
    @FXML
    private Label labelStep;
    @FXML
    private JFXPopup popupMenu;

    private MainController mainController;
    private LoginController loginController;
    private Locale locale;
    private PersonToRegister person = new PersonToRegister();

    private PopOver popOver;

    private boolean isPopupInitialization = false;

    static private final String BUNDLE_REGISTER = "ResourceBundle.LoginRegisterReminder.Register";
    static private final String FXML_REGISTER = "/LoginRegisterPanel/Register/Register_Step2.fxml";

    @FXML
    public void initialize() {
        textLogin.textProperty().bindBidirectional(person.loginProperty());
        DialogBuilder.showDialogForRegistration(stackPane);
    }

    @FXML
    public void back() {
        mainController.loadPanelLogin();
        Initialization.clearAllDataFromRegistration();
    }

    @FXML
    public void next() {
        if (!checkCorrectness()) {
            return;
        }

        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_REGISTER, BUNDLE_REGISTER, locale);

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RegisterController_Step2 registerController = loader.getController();
        registerController.setMainController(mainController);
        registerController.setLoginController(loginController);
        registerController.setRegisterController_step1(this);
        registerController.setLocale(locale);

        registerController.setPerson(person);

        mainController.setScreen(pane);
    }

    @FXML
    public void showInfo() {
        if (popOver == null)
            popOver = new PopOver();

        PopOverBuilder.showInfoForRegisterLogin(popOver, textLogin);
    }

    @FXML
    public void mouseEnteredOnLabelStep() {
        PopOverBuilder.showStepsForRegistration(labelStep, 1);
    }

    @FXML
    public boolean checkCorrectness() {
        if (Validation.checkLogin(textLogin.getText())) {
            if (Database.checkLogin(textLogin.getText())) {
                labelOK.setVisible(true);
                labelNOTOK.setVisible(false);
                labelLoginExists.setVisible(false);
                btnClear.setVisible(false);

                btnNext.setDisable(false);
            } else {
                labelNOTOK.setVisible(true);
                labelOK.setVisible(false);
                new Thread(() -> {
                    labelLoginExists.setVisible(true);
                    try {
                        Thread.sleep(3000);

                        labelLoginExists.setVisible(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }).start();
                btnClear.setVisible(true);
                btnNext.setDisable(true);

                return false;
            }
        } else {
            if (!Validation.checkLengthLogin(textLogin.getText())
                    && !Validation.checkFirstCharacterLogin(textLogin.getText())) {
                PopOverBuilder.showStatementValidation(textLogin, "Wrong length and wrong syntax.");
            } else if (!Validation.checkLengthLogin(textLogin.getText())) {
                PopOverBuilder.showStatementValidation(textLogin, "Wrong length.");
            } else if (!Validation.checkFirstCharacterLogin(textLogin.getText())) {
                PopOverBuilder.showStatementValidation(textLogin, "Wrong syntax.");
            } else if (Validation.isComma(textLogin.getText())) {
                PopOverBuilder.showStatementValidation(textLogin, "Comma in the login. Wrong syntax.");
            }

            labelNOTOK.setVisible(true);
            labelOK.setVisible(false);
            labelLoginExists.setVisible(false);
            btnClear.setVisible(true);

            btnNext.setDisable(true);

            return false;
        }

        return true;
    }

    @FXML
    public void clear() {
        PersonToRegister.loginProperty().set("");
        labelNOTOK.setVisible(false);
        labelLoginExists.setVisible(false);
        btnClear.setVisible(false);
    }

    @FXML
    public void showPopupMenu(MouseEvent e) {
        if (!isPopupInitialization) {
            isPopupInitialization = true;
            PopupBuilder.initPopupMenuForRegister(popupMenu, pane, loginController);
        }

        if (e.getButton() == MouseButton.SECONDARY)
            popupMenu.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, e.getX(), e.getY());
    }


    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
