package Service.Controller.Pages.EditMyProfile;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.controlsfx.control.PopOver;

/**
 * Created by Jonatan on 2017-01-23.
 */
public class ChangeNameAndLastNameController {

    @FXML
    private JFXTextField textYourName, textYourSecondName, textYourLastName;
    @FXML
    private JFXTextField textName, textSecondName, textLastName;
    @FXML
    private JFXToggleButton isSelectedName, isSelectedSecondName, isSelectedLastName;
    @FXML
    private JFXButton btnChange;
    @FXML
    private ImageView imageViewQuestionMark;

    private PopOver popOver;

    private Person person;

    @FXML
    public void isSelectedName() {
        if (isSelectedName.isSelected()) {
            textName.setDisable(false);
            btnChange.setDisable(false);
        } else {
            textName.setText("");
            textName.setDisable(true);
            if (!isSelectedSecondName.isSelected() && !isSelectedLastName.isSelected())
                btnChange.setDisable(true);
        }
    }

    @FXML
    public void isSelectedSecondName() {
        if (isSelectedSecondName.isSelected()) {
            textSecondName.setDisable(false);
            btnChange.setDisable(false);
        } else {
            textSecondName.setText("");
            textSecondName.setDisable(true);
            if (!isSelectedName.isSelected() && !isSelectedLastName.isSelected())
                btnChange.setDisable(true);
        }
    }

    @FXML
    public void isSelectedLastName() {
        if (isSelectedLastName.isSelected()) {
            textLastName.setDisable(false);
            btnChange.setDisable(false);
        } else {
            textLastName.setText("");
            textLastName.setDisable(true);
            if (!isSelectedName.isSelected() && !isSelectedSecondName.isSelected())
                btnChange.setDisable(true);
        }
    }

    @FXML
    public void change() {
        if (isSelectedName.isSelected()) {
            if (Validation.checkLengthName(textName.getText())) {
                if (Validation.findSpecialCharacter(textName.getText())) {
                    PopOverBuilder.showStatementValidation(textName, "You can't use special characters!");

                    return;
                }
            } else {
                PopOverBuilder.showStatementValidation(textName, "Wrong legth!");

                return;
            }
        }

        if (isSelectedSecondName.isSelected()) {
            if (Validation.checkLengthSecondName(textSecondName.getText())) {
                if (Validation.findSpecialCharacter(textSecondName.getText())) {
                    PopOverBuilder.showStatementValidation(textSecondName, "You can't use special characters!");

                    return;
                }
            } else {
                PopOverBuilder.showStatementValidation(textSecondName, "Wrong legth!");

                return;
            }
        }

        if (isSelectedLastName.isSelected()) {
            if (Validation.checkLengthLastName(textLastName.getText())) {
                if (Validation.findSpecialCharacter(textLastName.getText())) {
                    PopOverBuilder.showStatementValidation(textLastName, "You can't use special characters!");

                    return;
                }
            } else {
                PopOverBuilder.showStatementValidation(textLastName, "Wrong legth!");

                return;
            }
        }


        if (isSelectedName.isSelected()) {
            if (Database.changeName(person.getID(), textName.getText())) {
                person.setName(textName.getText());
                Database.addLogger(person.getID(), "Changed name.");
                if (person.isNotifications())
                    NotificationBuilder.showNotificationSuccessOperation("Change name",
                            "Name has been changed successfully.");
            }
        }

        if (isSelectedSecondName.isSelected()) {
            if (Database.changeSecondName(person.getID(), textSecondName.getText())) {
                person.setSecondName(textSecondName.getText());
                Database.addLogger(person.getID(), "Changed second name.");
                if (person.isNotifications())
                    NotificationBuilder.showNotificationSuccessOperation("Change second name",
                            "Second name has been changed successfully.");
            }
        }

        if (isSelectedLastName.isSelected()) {
            if (Database.changeLastName(person.getID(), textLastName.getText())) {
                person.setLastName(textLastName.getText());
                Database.addLogger(person.getID(), "Changed last name.");
                if (person.isNotifications())
                    NotificationBuilder.showNotificationSuccessOperation("Change last name",
                            "Last name has been changed successfully.");
            }
        }

        if (isSelectedName.isSelected() || isSelectedSecondName.isSelected() || isSelectedLastName.isSelected())
            new LoadPage().loadGridPaneSuccessEdition(person);
    }

    @FXML
    public void back() {
        new LoadPage().loadEditMyProfile(person);
    }

    @FXML
    public void showInfo() {
        if (popOver == null)
            popOver = new PopOver();

        PopOverBuilder.showInfoForEditNameSecondNameLastName(popOver, imageViewQuestionMark);
    }

    @FXML
    public void clear() {
        textName.setText("");
        textSecondName.setText("");
        textLastName.setText("");
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void bindPerson() {
        textYourName.textProperty().bind(person.nameProperty());
        textYourSecondName.textProperty().bind(person.secondNameProperty());
        textYourLastName.textProperty().bind(person.lastNameProperty());
    }

}
