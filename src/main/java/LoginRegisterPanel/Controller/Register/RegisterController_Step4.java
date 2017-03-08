package LoginRegisterPanel.Controller.Register;

import Builder.PopOverBuilder;
import Builder.PopupBuilder;
import LoadFXML.LoadFXML;
import LoginRegisterPanel.Controller.MainController;
import LoginRegisterPanel.Initialization.Initialization;
import LoginRegisterPanel.Person.PersonToRegister;
import Validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Jonatan on 2016-12-16.
 */
public class RegisterController_Step4 {

    @FXML
    private Pane pane;
    @FXML
    private Label labelStrengthPassword;
    @FXML
    private JFXPasswordField textPassword;
    @FXML
    private JFXPasswordField textRepeatPassword;
    @FXML
    private JFXTextField textPasswordAfterShow;
    @FXML
    private JFXTextField textRepeatPasswordAfterShow;
    @FXML
    private JFXButton btnNext;
    @FXML
    private Label labelNOTOK1;
    @FXML
    private Label labelNOTOK2;
    @FXML
    private JFXButton btnClear;
    @FXML
    private Label labelStep;
    @FXML
    private JFXPopup popupMenu;

    private MainController mainController;
    private RegisterController_Step2 registerController_step2;
    private RegisterController_Step3 registerController_step3;
    private Locale locale;
    private PersonToRegister person;

    private PopOver popOver;

    private boolean isPopupInitialization = false;

    public static int numberOfCharactersInPassword = 0;
    public static int strengthPassword = 0;
    public static StringProperty givenRepeatPassword = new SimpleStringProperty("");
    private static char characterForException;

    static private final String BUNDLE_REGISTER = "ResourceBundle.LoginRegisterReminder.Register";
    static private final String FXML_REGISTER = "/LoginRegisterPanel/Register/Register_Step5.fxml";

    @FXML
    public void initialize() {
        btnNext.disableProperty().bind(Bindings.isEmpty(textRepeatPassword.textProperty()));
        labelStrengthPassword.visibleProperty().bind(Bindings.isNotEmpty(textPassword.textProperty()));

        textPassword.textProperty().bindBidirectional(person.passwordProperty());
        textRepeatPassword.textProperty().bindBidirectional(givenRepeatPassword);

        textPasswordAfterShow.textProperty().bindBidirectional(person.passwordProperty());
        textRepeatPasswordAfterShow.textProperty().bindBidirectional(givenRepeatPassword);

        if (givenRepeatPassword != null) {
            textRepeatPassword.setDisable(false);
        }

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

        if (textPassword.getText().length() == 0) {
            numberOfCharactersInPassword = 0;
            strengthPassword = 0;

            textRepeatPassword.setDisable(true);
        }
    }

    @FXML
    public void back() {
        registerController_step2.next();
    }

    @FXML
    public void next() {
        if (Validation.checkPassword(textPassword.getText())) {
            if (!Validation.checkRepeatPassword(textPassword.getText(), textRepeatPassword.getText())) {
                PopOverBuilder.showStatementValidation(textRepeatPassword, "Passwords are not the same!");
                labelNOTOK2.setVisible(true);
                labelNOTOK1.setVisible(false);
                btnClear.setVisible(true);

                return;
            }
        } else {
            PopOverBuilder.showStatementValidation(textPassword, "Wrong length!");
            labelNOTOK1.setVisible(true);
            labelNOTOK2.setVisible(false);
            btnClear.setVisible(true);

            return;
        }

        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_REGISTER, BUNDLE_REGISTER, locale);

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RegisterController_Step5 registerController = loader.getController();
        registerController.setMainController(mainController);
        registerController.setRegisterController_step3(registerController_step3);
        registerController.setRegisterController_step4(this);
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
    public void showInfo() {
        if (popOver == null)
            popOver = new PopOver();

        PopOverBuilder.showInfoForRegisterPassword(popOver, textPassword);
    }

    @FXML
    public void clear() {
        PersonToRegister.passwordProperty().set("");
        givenRepeatPassword.set("");
        btnClear.setVisible(false);
        textRepeatPassword.setDisable(true);
        labelNOTOK1.setVisible(false);
        labelNOTOK2.setVisible(false);
    }

    @FXML
    public void keyPressedInTextPassword(KeyEvent e) {
        if (textPassword.getText().length() == 0) {
            numberOfCharactersInPassword = 0;
            strengthPassword = 0;
        } else if (textPassword.getText().length() == 1) {
            numberOfCharactersInPassword = 1;
            strengthPassword = 1;
        }

        try {
            strengthPassword = Validation.setPointValueForPassword(textPassword.getText() + e.getText());

            characterForException = e.getText().charAt(0);

            ++numberOfCharactersInPassword;
        } catch (StringIndexOutOfBoundsException e1) {
            if (e.getCode() == KeyCode.BACK_SPACE) {
                --numberOfCharactersInPassword;

                if (numberOfCharactersInPassword > 0) {
                    strengthPassword = Validation.setPointValueForPassword(textPassword.getText().substring(0, textPassword.getText().length() - 1));

                    if (numberOfCharactersInPassword == 0) {
                        strengthPassword = 0;

                        textRepeatPassword.setDisable(true);
                    }
                }
            }
        }

        if (numberOfCharactersInPassword >= 6)
            textRepeatPassword.setDisable(false);
        else
            textRepeatPassword.setDisable(true);

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
        textPasswordAfterShow.setVisible(true);
        textRepeatPasswordAfterShow.setVisible(true);
        textPassword.setVisible(false);
        textRepeatPassword.setVisible(false);
    }

    @FXML
    public void hiddenPassword() {
        textPassword.setVisible(true);
        textRepeatPassword.setVisible(true);
        textPasswordAfterShow.setVisible(false);
        textRepeatPasswordAfterShow.setVisible(false);
    }

    @FXML
    public void mouseEnteredOnLabelStep() {
        PopOverBuilder.showStepsForRegistration(labelStep, 4);
    }

    @FXML
    public void showPopupMenu(MouseEvent e) {
        if (!isPopupInitialization) {
            isPopupInitialization = true;
            PopupBuilder.initPopupMenuForRegister(popupMenu, pane, registerController_step3);
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

    public void setRegisterController_step2(RegisterController_Step2 registerController_step2) {
        this.registerController_step2 = registerController_step2;
    }

    public void setRegisterController_step3(RegisterController_Step3 registerController_step3) {
        this.registerController_step3 = registerController_step3;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setPerson(PersonToRegister person) {
        this.person = person;
    }
}
