package LoginRegisterPanel.Controller.PasswordReminder;

import Builder.NotificationBuilder;
import LoginRegisterPanel.Controller.MainController;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by Jonatan on 2017-01-07.
 */
public class PasswordReminderController_Successfully {

    private MainController mainController;
    private Stage mainStage;

    @FXML
    public void initialize() {
        NotificationBuilder.showNotificationChangePasswordSuccessfully(PasswordReminderController_Step3.locale);
    }

    @FXML
    public void backToLogin() {
        mainController.loadPanelLogin();
    }



    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
