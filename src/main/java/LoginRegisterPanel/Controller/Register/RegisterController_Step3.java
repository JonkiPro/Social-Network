package LoginRegisterPanel.Controller.Register;

import Builder.PopOverBuilder;
import Builder.PopupBuilder;
import Database.Database;
import LoadFXML.LoadFXML;
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

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Jonatan on 2016-12-16.
 */
public class RegisterController_Step3 {

    @FXML
    private Pane pane;
    @FXML
    private Label labelOK;
    @FXML
    private Label labelNOTOK;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXTextField textEmail;
    @FXML
    private Label labelEmailExists;
    @FXML
    private JFXButton btnNext;
    @FXML
    private Label labelStep;
    @FXML
    private JFXPopup popupMenu;

    private MainController mainController;
    private RegisterController_Step1 registerController_step1;
    private RegisterController_Step2 registerController_step2;
    private Locale locale;
    private PersonToRegister person;

    private boolean isPopupInitialization = false;

    static private final String BUNDLE_REGISTER = "ResourceBundle.LoginRegisterReminder.Register";
    static private final String FXML_REGISTER = "/LoginRegisterPanel/Register/Register_Step4.fxml";

    @FXML
    public void initialize() {
        textEmail.textProperty().bindBidirectional(person.emailProperty());
    }

    @FXML
    public void back() {
        registerController_step1.next();
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

        RegisterController_Step4 registerController = loader.getController();
        registerController.setMainController(mainController);
        registerController.setRegisterController_step2(registerController_step2);
        registerController.setRegisterController_step3(this);
        registerController.setLocale(locale);

        registerController.setPerson(person);

        mainController.setScreen(pane);
    }

    @FXML
    public void backToLogin() {
        mainController.loadPanelLogin();
        Initialization.clearAllDataFromRegistration();
    }

    @FXML
    public boolean checkCorrectness() {
        if (Validation.checkEmail(textEmail.getText())) {
            if (Database.checkEmail(textEmail.getText())) {
                labelOK.setVisible(true);
                labelNOTOK.setVisible(false);
                labelEmailExists.setVisible(false);
                btnClear.setVisible(false);

                btnNext.setDisable(false);
            } else {
                labelNOTOK.setVisible(true);
                labelOK.setVisible(false);
                new Thread(() -> {
                    labelEmailExists.setVisible(true);
                    try {
                        Thread.sleep(3000);

                        labelEmailExists.setVisible(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }).start();
                btnClear.setVisible(true);

                btnNext.setDisable(true);

                return false;
            }
        } else {
            if (!Validation.checkLengthEmail(textEmail.getText())) {
                PopOverBuilder.showStatementValidation(textEmail, "Wrong length!");
            } else {
                PopOverBuilder.showStatementValidation(textEmail, "Wrong syntax!");
            }
            labelNOTOK.setVisible(true);
            labelOK.setVisible(false);
            labelEmailExists.setVisible(false);
            btnClear.setVisible(true);

            btnNext.setDisable(true);

            return false;
        }

        return true;
    }

    @FXML
    public void clear() {
        PersonToRegister.emailProperty().set("");
        labelNOTOK.setVisible(false);
        labelEmailExists.setVisible(false);
        btnClear.setVisible(false);
    }

    @FXML
    public void mouseEnteredOnLabelStep() {
        PopOverBuilder.showStepsForRegistration(labelStep, 3);
    }

    @FXML
    public void showPopupMenu(MouseEvent e) {
        if (!isPopupInitialization) {
            isPopupInitialization = true;
            PopupBuilder.initPopupMenuForRegister(popupMenu, pane, registerController_step2);
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

    public void setRegisterController_step1(RegisterController_Step1 registerController_step1) {
        this.registerController_step1 = registerController_step1;
    }

    public void setRegisterController_step2(RegisterController_Step2 registerController_step2) {
        this.registerController_step2 = registerController_step2;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setPerson(PersonToRegister person) {
        this.person = person;
    }
}
