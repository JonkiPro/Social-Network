package Builder;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Jonatan on 2017-01-08.
 */
public class NotificationBuilder {
    static public void showNotificationRegistrationSuccessfully(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle.LoginRegisterReminder.Notification", locale);

        Image image = new Image("/images/successfulRegister.png");

        Notifications notificationBuilder = Notifications.create()
                .title(bundle.getString("notificationSuccessfullyRegistration.title"))
                .text(bundle.getString("notificationSuccessfullyRegistration.text"))
                .graphic(new ImageView(image))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    static public void showNotificationRegistrationFailed(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle.LoginRegisterReminder.Notification", locale);

        Image image = new Image("/images/failedRegister.png");

        Notifications notificationBuilder = Notifications.create()
                .title(bundle.getString("notificationFailedRegistration.title"))
                .text(bundle.getString("notificationFailedRegistration.text"))
                .graphic(new ImageView(image))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    static public void showNotificationChangePasswordSuccessfully(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundle.LoginRegisterReminder.Notification", locale);

        Image image = new Image("/images/successfulRegister.png");

        Notifications notificationBuilder = Notifications.create()
                .title(bundle.getString("notificationSuccessfullyChangePassword.title"))
                .text(bundle.getString("notificationSuccessfullyChangePassword.text"))
                .graphic(new ImageView(image))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    static public void showNotificationSuccessOperation(String title, String text) {
        Image image = new Image("/images/service/success.png");

        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(new ImageView(image))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    static public void showNotificationFailedOperation(String title, String text) {
        Image image = new Image("/images/service/failed.png");

        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(new ImageView(image))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
}
