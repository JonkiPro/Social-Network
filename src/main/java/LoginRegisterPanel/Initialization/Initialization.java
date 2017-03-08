package LoginRegisterPanel.Initialization;

import LoginRegisterPanel.Controller.Register.RegisterController_Step4;
import LoginRegisterPanel.Person.PersonToRegister;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.Random;

/**
 * Created by Jonatan on 2016-12-22.
 */
public class Initialization {
    static public void initLanguages(ComboBox comboBox) {
        comboBox.getItems().addAll("EN", "PL");
    }

    static public void initSex(ComboBox comboBox) {
        comboBox.getItems().addAll("", "male", "female");
        comboBox.getSelectionModel().select(0);
    }

    static public void initCAPTCHA(Label label) {
        String text = null;
        char[] characters = new char[6];
        Random random = new Random();
        int randomNumber;

        for(int i = 0; i < 6; i++) {
            if(i == 0)
                text = "";

            randomNumber = random.nextInt(3);

            if(randomNumber == 0) {
                characters[i] = (char)(48+random.nextInt(10));
            } else if(randomNumber == 1) {
                characters[i] = (char)(65+random.nextInt(25));
            } else if(randomNumber == 2) {
                characters[i] = (char)(97+random.nextInt(25));
            }

            text+=characters[i];
        }
        label.setText(text);
    }

    static public void clearAllDataFromRegistration() {
        PersonToRegister.loginProperty().set("");
        PersonToRegister.emailProperty().set("");
        PersonToRegister.nameProperty().set("");
        PersonToRegister.lastNameProperty().set("");
        PersonToRegister.dateOfBirthProperty().set(null);
        PersonToRegister.sexProperty().set("");
        PersonToRegister.passwordProperty().set("");
        PersonToRegister.leadingQuestionProperty().set("");
        PersonToRegister.answerProperty().set("");
        RegisterController_Step4.givenRepeatPassword.set("");
        RegisterController_Step4.numberOfCharactersInPassword = 0;
    }
}
