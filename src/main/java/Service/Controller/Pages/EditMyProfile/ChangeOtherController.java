package Service.Controller.Pages.EditMyProfile;

import Builder.NotificationBuilder;
import Database.Database;
import Initialization.Initialization;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;

/**
 * Created by Jonatan on 2017-01-24.
 */
public class ChangeOtherController {

    @FXML
    private JFXTextField textYourSex, textYourDateOfBirth;
    @FXML
    private JFXComboBox<String> comboBoxSex;
    @FXML
    private JFXDatePicker dateOfBirth;
    @FXML
    private JFXToggleButton isSelectedSex, isSelectedDateOfBirth;
    @FXML
    private JFXButton btnChange;

    private Person person;

    @FXML
    public void initialize() {
        Initialization.initSex(comboBoxSex);
    }

    @FXML
    public void isSelectedSex() {
        if (isSelectedSex.isSelected()) {
            comboBoxSex.setDisable(false);
            btnChange.setDisable(false);
        } else {
            comboBoxSex.getSelectionModel().select("");
            comboBoxSex.setDisable(true);
            if (!isSelectedDateOfBirth.isSelected())
                btnChange.setDisable(true);
        }
    }

    @FXML
    public void isSelectedDateOfBirth() {
        if (isSelectedDateOfBirth.isSelected()) {
            dateOfBirth.setDisable(false);
            btnChange.setDisable(false);
        } else {
            dateOfBirth.setValue(null);
            dateOfBirth.setDisable(true);
            if (!isSelectedSex.isSelected())
                btnChange.setDisable(true);
        }
    }

    @FXML
    public void change() {
        if (isSelectedSex.isSelected()) {
            if (Database.changeSex(person.getID(), comboBoxSex.getSelectionModel().getSelectedItem())) {
                person.setSex(comboBoxSex.getSelectionModel().getSelectedItem());
                Database.addLogger(person.getID(), "Changed sex.");
                if (person.isNotifications())
                    NotificationBuilder.showNotificationSuccessOperation("Change sex",
                            "Sex has been changed successfully");
            }
        }
        if (isSelectedDateOfBirth.isSelected()) {
            if (Database.changeDateOfBirth(person.getID(), dateOfBirth.getValue())) {
                person.setDateOfBirth(dateOfBirth.getValue().toString());
                Database.addLogger(person.getID(), "Changed date of birth.");
                if (person.isNotifications())
                    NotificationBuilder.showNotificationSuccessOperation("Change date of birth",
                            "Date of birth has been changed successfully");
            }
        }

        if (isSelectedSex.isSelected() || isSelectedDateOfBirth.isSelected())
            new LoadPage().loadGridPaneSuccessEdition(person);
    }

    @FXML
    public void back() {
        new LoadPage().loadEditMyProfile(person);
    }

    @FXML
    public void clear() {
        comboBoxSex.getSelectionModel().select("");
        dateOfBirth.setValue(null);
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void bindPerson() {
        textYourSex.textProperty().bind(person.sexProperty());
        textYourDateOfBirth.textProperty().bind(person.dateOfBirthProperty());
    }
}
