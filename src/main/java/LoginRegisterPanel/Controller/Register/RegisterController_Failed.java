package LoginRegisterPanel.Controller.Register;

import Builder.NotificationBuilder;
import LoginRegisterPanel.Controller.MainController;
import LoginRegisterPanel.Initialization.Initialization;
import javafx.fxml.FXML;

/**
 * Created by Jonatan on 2016-12-16.
 */
public class RegisterController_Failed {

    private MainController mainController;

    @FXML
    public void initialize() {
        NotificationBuilder.showNotificationRegistrationFailed(RegisterController_Step6.locale);
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

}
