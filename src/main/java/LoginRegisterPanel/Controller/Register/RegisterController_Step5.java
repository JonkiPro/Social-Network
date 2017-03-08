package LoginRegisterPanel.Controller.Register;

import Builder.PopOverBuilder;
import Builder.PopupBuilder;
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
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Jonatan on 2016-12-16.
 */
public class RegisterController_Step5 {

    @FXML
    private Pane pane;
    @FXML
    private Label labelInfo;
    @FXML
    private JFXTextField textQuestion;
    @FXML
    private JFXTextField textAnswer;
    @FXML
    private Label labelNOTOK1;
    @FXML
    private Label labelNOTOK2;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnNext;
    @FXML
    private Label labelStep;
    @FXML
    private JFXPopup popupMenu;

    private MainController mainController;
    private RegisterController_Step3 registerController_step3;
    private RegisterController_Step4 registerController_step4;
    private Locale locale;
    private PersonToRegister person;

    private PopOver popOver, popOver2;

    private boolean isPopupInitialization = false;

    static private final String BUNDLE_REGISTER = "ResourceBundle.LoginRegisterReminder.Register";
    static private final String FXML_REGISTER = "/LoginRegisterPanel/Register/Register_Step6.fxml";

    @FXML
    public void initialize() {
        textQuestion.textProperty().bindBidirectional(person.leadingQuestionProperty());
        textAnswer.textProperty().bindBidirectional(person.answerProperty());
    }

    @FXML
    public void back() {
        registerController_step3.next();
    }

    @FXML
    public void next() {
        if (!Validation.checkQuestion(textQuestion.getText(), textAnswer.getText())
                || !Validation.checkLengthQuestion(textQuestion.getText())) {
            if (!Validation.checkQuestion(textQuestion.getText(), textAnswer.getText())) {
                PopOverBuilder.showStatementValidation(textQuestion, "Enter your question!");
            } else if (!Validation.checkLengthQuestion(textQuestion.getText())) {
                PopOverBuilder.showStatementValidation(textQuestion, "Wrong length!");
            }
            labelNOTOK1.setVisible(true);
            labelNOTOK2.setVisible(false);
            btnClear.setVisible(true);

            return;
        } else if (!Validation.checkAnswer(textQuestion.getText(), textAnswer.getText())
                || !Validation.checkLengthAnswer(textAnswer.getText())) {
            if (!Validation.checkAnswer(textQuestion.getText(), textAnswer.getText())) {
                PopOverBuilder.showStatementValidation(textAnswer, "Enter your answer!");
            } else if (!Validation.checkLengthAnswer(textAnswer.getText())) {
                PopOverBuilder.showStatementValidation(textAnswer, "Wrong length!");
            }
            labelNOTOK2.setVisible(true);
            labelNOTOK1.setVisible(false);
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

        RegisterController_Step6 registerController = loader.getController();
        registerController.setMainController(mainController);
        registerController.setRegisterController_step4(registerController_step4);
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
        if (popOver2 == null)
            popOver2 = new PopOver();

        PopOverBuilder.showInfoForRegisterQuestionAndAnswer(popOver, textQuestion);
        PopOverBuilder.showInfoForRegisterQuestionAndAnswer(popOver2, textAnswer);
        labelInfo.setVisible(true);
    }

    @FXML
    public void hiddenInfo() {
        labelInfo.setVisible(false);
    }

    @FXML
    public void clear() {
        PersonToRegister.leadingQuestionProperty().set("");
        PersonToRegister.answerProperty().set("");
        labelNOTOK1.setVisible(false);
        labelNOTOK2.setVisible(false);
        btnClear.setVisible(false);
    }

    @FXML
    public void mouseEnteredOnLabelStep() {
        PopOverBuilder.showStepsForRegistration(labelStep, 5);
    }

    @FXML
    public void showPopupMenu(MouseEvent e) {
        if (!isPopupInitialization) {
            isPopupInitialization = true;
            PopupBuilder.initPopupMenuForRegister(popupMenu, pane, registerController_step4);
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

    public void setRegisterController_step3(RegisterController_Step3 registerController_step3) {
        this.registerController_step3 = registerController_step3;
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
