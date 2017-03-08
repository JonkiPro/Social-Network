package Builder;

import LoginRegisterPanel.Controller.Login.LoginController;
import LoginRegisterPanel.Controller.MainController;
import LoginRegisterPanel.Controller.PasswordReminder.PasswordReminderController_Step1;
import LoginRegisterPanel.Controller.PasswordReminder.PasswordReminderController_Step2;
import LoginRegisterPanel.Controller.Register.RegisterController_Step1;
import LoginRegisterPanel.Controller.Register.RegisterController_Step2;
import LoginRegisterPanel.Controller.Register.RegisterController_Step3;
import LoginRegisterPanel.Controller.Register.RegisterController_Step4;
import LoginRegisterPanel.Person.PersonToRegister;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Created by Jonatan on 2017-01-12.
 */
public class PopupBuilder {
    static public void initPopupMenuForLogin(JFXPopup popupMenu, Node node, MainController mainController) {
        Button btnRefresh = new Button("Refresh");
        btnRefresh.setStyle("-fx-background-color: white;");
        btnRefresh.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                mainController.loadPanelLogin();
                popupMenu.close();
            }
        });

        VBox vbox = new VBox(btnRefresh);

        popupMenu.setContent(vbox);
        popupMenu.setSource(node);
    }

    static public <V> void initPopupMenuForRegister(JFXPopup popupMenu, Node node, V object) {
        Button btnRefresh = new Button("Refresh");
        btnRefresh.setStyle("-fx-background-color: white;");
        btnRefresh.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            if (object instanceof LoginController) {
                ((LoginController) object).register();
                PersonToRegister.loginProperty().set("");
            } else if (object instanceof RegisterController_Step1) {
                ((RegisterController_Step1) object).next();
                PersonToRegister.nameProperty().set("");
                PersonToRegister.lastNameProperty().set("");
                PersonToRegister.dateOfBirthProperty().set(null);
                PersonToRegister.sexProperty().set("");
            } else if (object instanceof RegisterController_Step2) {
                ((RegisterController_Step2) object).next();
                PersonToRegister.emailProperty().set("");
            } else if (object instanceof RegisterController_Step3) {
                ((RegisterController_Step3) object).next();
                PersonToRegister.passwordProperty().set("");
                RegisterController_Step4.givenRepeatPassword.set("");
            } else if (object instanceof RegisterController_Step4) {
                ((RegisterController_Step4) object).next();
                PersonToRegister.leadingQuestionProperty().set("");
                PersonToRegister.answerProperty().set("");
            }

            popupMenu.close();
        });

        VBox vbox = new VBox(btnRefresh);

        popupMenu.setContent(vbox);
        popupMenu.setSource(node);
    }

    static public <V> void initPopupMenuForChangePassword(JFXPopup popupMenu, Node node, V object) {
        Button btnRefresh = new Button("Refresh");
        btnRefresh.setStyle("-fx-background-color: white;");
        btnRefresh.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            if (object instanceof LoginController) {
                ((LoginController) object).passwordReminder();
                PersonToRegister.loginProperty().set("");
            } else if (object instanceof PasswordReminderController_Step1) {
                ((PasswordReminderController_Step1) object).next();
            } else if (object instanceof PasswordReminderController_Step2) {
                ((PasswordReminderController_Step2) object).next();
            }

            popupMenu.close();
        });

        VBox vbox = new VBox(btnRefresh);

        popupMenu.setContent(vbox);
        popupMenu.setSource(node);
    }

    static public void initPopupMenu(JFXPopup popupMenu, Node node, List<JFXButton> listButtons) {
        VBox vbox = new VBox();

        for (JFXButton button : listButtons)
            vbox.getChildren().add(button);

        popupMenu.setContent(vbox);
        popupMenu.setSource(node);
    }
}
