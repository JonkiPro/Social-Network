package Builder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Created by Jonatan on 2017-01-10.
 */
public class DialogBuilder {
    static public void showDialogForRegistration(StackPane stackPane) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Register"));
        dialogLayout.setBody(new Text("Thank you for registering on our site. \n You will be able to meet new people."));

        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("OK");

        EventHandler<ActionEvent> actionEvent = (ActionEvent e) -> {
            dialog.close();
            stackPane.setVisible(false);
        };

        button.addEventHandler(ActionEvent.ACTION, actionEvent);

        dialogLayout.setActions(button);
        dialog.show();
    }

    static public void showDialogForChangePassword(StackPane stackPane) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Change password"));
        dialogLayout.setBody(new Text("If you forgot your password,\n you can change here."));

        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("OK");

        EventHandler<ActionEvent> actionEvent = (ActionEvent e) -> {
            dialog.close();
            stackPane.setVisible(false);
        };

        button.addEventHandler(ActionEvent.ACTION, actionEvent);

        dialogLayout.setActions(button);
        dialog.show();
    }
}
