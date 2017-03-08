package Service.Controller.Pages.RecentActivity;

import Builder.NotificationBuilder;
import Database.Database;
import FileBuilder.FileBuilder;
import Service.MyLogger.MyLogger;
import Service.Person.Person;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonatan on 2017-03-03.
 */
public class RecentActivityController {

    @FXML
    private JFXListView<JFXButton> listView;

    private Person person;
    private Stage stage;

    private List<MyLogger> listLoggers = new ArrayList<>();
    private List<String> listTextWithButtons = new ArrayList<>();

    public void loadLoggers() {
        listLoggers = Database.getLoggers(person.getID());
    }

    public void loadLoggersToListView() {
        for (MyLogger myLogger : listLoggers) {
            JFXButton button = createButton(myLogger.getDate(), myLogger.getContents());

            listView.getItems().add(button);
            listTextWithButtons.add(button.getText());
        }
    }

    private JFXButton createButton(String date, String contents) {
        JFXButton button = new JFXButton(date + "  -  " + contents);
        button.setId("btnLogger");
        button.setPadding(new Insets(10));
        button.setStyle("-fx-font-size: 24px;");

        return button;
    }

    @FXML
    public void exportToStage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterTXT = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().addAll(extFilterTXT, extFilterPDF);
        File file = fileChooser.showSaveDialog(stage);

        try {
            if (file.createNewFile()) {
                if (FileBuilder.createFileLoggers(file.getAbsolutePath(), listTextWithButtons)) {
                    NotificationBuilder.showNotificationSuccessOperation("Export logger on computer",
                            "The logger was exported successfully.");
                } else {
                    NotificationBuilder.showNotificationFailedOperation("Export logger on computer",
                            "The logger was not exported successfully.");
                }
            } else {
                NotificationBuilder.showNotificationFailedOperation("Export logger on computer",
                        "The logger was not exported successfully.");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e1) { /* empty */ }
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
