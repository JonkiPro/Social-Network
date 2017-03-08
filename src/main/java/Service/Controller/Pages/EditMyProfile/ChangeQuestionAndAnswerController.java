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
 * Created by Jonatan on 2017-01-24.
 */
public class ChangeQuestionAndAnswerController {

    @FXML
    private JFXTextField textYourQuestion, textYourAnswer, textQuestion, textAnswer;
    @FXML
    private JFXToggleButton isSelectedQuestion, isSelectedAnswer;
    @FXML
    private JFXButton btnChange;
    @FXML
    private ImageView imageViewQuestionMark;

    private PopOver popOver;

    private Person person;

    @FXML
    public void isSelectedQuestion() {
        if (isSelectedQuestion.isSelected()) {
            textQuestion.setDisable(false);
            btnChange.setDisable(false);
        } else {
            textQuestion.setText("");
            textQuestion.setDisable(true);
            if (!isSelectedAnswer.isSelected())
                btnChange.setDisable(true);
        }
    }

    @FXML
    public void isSelectedAnswer() {
        if (isSelectedAnswer.isSelected()) {
            textAnswer.setDisable(false);
            btnChange.setDisable(false);
        } else {
            textAnswer.setText("");
            textAnswer.setDisable(true);
            if (!isSelectedQuestion.isSelected())
                btnChange.setDisable(true);
        }
    }

    @FXML
    public void change() {
        if (isSelectedQuestion.isSelected()) {
            if (Validation.checkLengthQuestion(textQuestion.getText())) {
                if (Database.changeQuestion(person.getID(), textQuestion.getText())) {
                    person.setLeadingQuestion(textQuestion.getText());
                    Database.addLogger(person.getID(), "Changed question.");
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationSuccessOperation("Change question",
                                "Question has been changed successfully");
                }
            } else {
                PopOverBuilder.showStatementValidation(textQuestion, "Wrong length!");

                return;
            }
        }

        if (isSelectedAnswer.isSelected()) {
            if (Validation.checkLengthAnswer(textAnswer.getText())) {
                if (Database.changeAnswer(person.getID(), textAnswer.getText())) {
                    person.setAnswer(textAnswer.getText());
                    Database.addLogger(person.getID(), "Changed answer.");
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationSuccessOperation("Change answer",
                                "Answer has been changed successfully");
                }
            } else {
                PopOverBuilder.showStatementValidation(textAnswer, "Wrong length!");

                return;
            }
        }

        if (isSelectedQuestion.isSelected() || isSelectedAnswer.isSelected())
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

        PopOverBuilder.showInfoForEditQuestionAndAnswer(popOver, imageViewQuestionMark);
    }

    @FXML
    public void clear() {
        textQuestion.setText("");
        textAnswer.setText("");
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void bindPerson() {
        textYourQuestion.textProperty().bind(person.leadingQuestionProperty());
        textYourAnswer.textProperty().bind(person.answerProperty());
    }
}
