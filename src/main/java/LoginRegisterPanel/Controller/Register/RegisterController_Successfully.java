package LoginRegisterPanel.Controller.Register;

import Builder.NotificationBuilder;
import LoginRegisterPanel.Controller.MainController;
import LoginRegisterPanel.Initialization.Initialization;
import javafx.fxml.FXML;

import java.util.Locale;

/**
 * Created by Jonatan on 2016-12-16.
 */
public class RegisterController_Successfully {

    private MainController mainController;
    private Locale locale;

    @FXML
    public void initialize() {
        NotificationBuilder.showNotificationRegistrationSuccessfully(RegisterController_Step6.locale);
    }

    @FXML
    public void backToLogin() {
        mainController.loadPanelLogin();
        Initialization.clearAllDataFromRegistration();
    }



    public MainController getMainController() { return mainController; }

    public void setMainController(MainController mainController) { this.mainController = mainController; }

    public void setLocale(Locale locale) { this.locale = locale; }
}
