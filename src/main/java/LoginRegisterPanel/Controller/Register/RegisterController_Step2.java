package LoginRegisterPanel.Controller.Register;

import Builder.PopOverBuilder;
import Builder.PopupBuilder;
import LoadFXML.LoadFXML;
import LoginRegisterPanel.Controller.Login.LoginController;
import LoginRegisterPanel.Controller.MainController;
import LoginRegisterPanel.Initialization.Initialization;
import LoginRegisterPanel.Person.PersonToRegister;
import Validation.Validation;
import com.jfoenix.controls.*;
import javafx.beans.binding.Bindings;
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
public class RegisterController_Step2 {

    @FXML
    private Pane pane;
    @FXML
    private JFXTextField textName;
    @FXML
    private JFXTextField textLastName;
    @FXML
    private JFXDatePicker dateOfBirth;
    @FXML
    private JFXComboBox comboSex;
    @FXML
    private JFXButton btnNext;
    @FXML
    private Label labelStep;
    @FXML
    private JFXPopup popupMenu;

    private MainController mainController;
    private LoginController loginController;
    private RegisterController_Step1 registerController_step1;
    private Locale locale;
    private PersonToRegister person;

    private boolean isPopupInitialization = false;

    static private final String BUNDLE_REGISTER = "ResourceBundle.LoginRegisterReminder.Register";
    static private final String FXML_REGISTER = "/LoginRegisterPanel/Register/Register_Step3.fxml";

    @FXML
    public void initialize() {
        Initialization.initSex(comboSex);

        btnNext.disableProperty().bind(Bindings.isEmpty(textName.textProperty()));

        textName.textProperty().bindBidirectional(person.nameProperty());
        textLastName.textProperty().bindBidirectional(person.lastNameProperty());
        dateOfBirth.valueProperty().bindBidirectional(person.dateOfBirthProperty());
        comboSex.valueProperty().bindBidirectional(person.sexProperty());
    }

    @FXML
    public void back() {
        loginController.register();
    }

    @FXML
    public void next() {
        if (textName.getText() != null) {
            if (Validation.checkLengthLastName(textName.getText())) {
                if (Validation.findSpecialCharacter(textName.getText())) {
                    PopOverBuilder.showStatementValidation(textName, "You can't use special characters!");

                    return;
                }
            } else {
                PopOverBuilder.showStatementValidation(textLastName, "Wrong legth!");

                return;
            }
        }
        if (textLastName.getText() != null) {
            if (Validation.checkLengthLastName(textLastName.getText())) {
                if (Validation.findSpecialCharacter(textLastName.getText())) {
                    PopOverBuilder.showStatementValidation(textLastName, "You can't use special characters!");

                    return;
                }
            } else {
                PopOverBuilder.showStatementValidation(textLastName, "Wrong legth!");

                return;
            }
        }

        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_REGISTER, BUNDLE_REGISTER, locale);

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RegisterController_Step3 registerController = loader.getController();
        registerController.setMainController(mainController);
        registerController.setRegisterController_step1(registerController_step1);
        registerController.setRegisterController_step2(this);
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
    public void mouseEnteredOnLabelStep() {
        PopOverBuilder.showStepsForRegistration(labelStep, 2);
    }

    @FXML
    public void showPopupMenu(MouseEvent e) {
        if (!isPopupInitialization) {
            isPopupInitialization = true;
            PopupBuilder.initPopupMenuForRegister(popupMenu, pane, registerController_step1);
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

    public void setRegisterController_step1(RegisterController_Step1 registerController_step1) {
        this.registerController_step1 = registerController_step1;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setPerson(PersonToRegister person) {
        this.person = person;
    }
}
