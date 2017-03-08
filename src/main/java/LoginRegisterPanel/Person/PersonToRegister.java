package LoginRegisterPanel.Person;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/**
 * Created by Jonatan on 2016-12-29.
 */
public class PersonToRegister {

    private static StringProperty login = new SimpleStringProperty("");
    private static StringProperty password = new SimpleStringProperty("");
    private static StringProperty email = new SimpleStringProperty("");
    private static StringProperty name = new SimpleStringProperty("");
    private static StringProperty lastName = new SimpleStringProperty("");
    private static StringProperty sex = new SimpleStringProperty("");
    private static StringProperty leadingQuestion = new SimpleStringProperty("");
    private static StringProperty answer = new SimpleStringProperty("");
    private static ObjectProperty dateOfBirth = new SimpleObjectProperty<LocalDate>();

    public static String getLogin() {
        return login.get();
    }

    public static StringProperty loginProperty() {
        return login;
    }

    public static void setLogin(String login) {
        PersonToRegister.login.set(login);
    }

    public static String getPassword() {
        return password.get();
    }

    public static StringProperty passwordProperty() {
        return password;
    }

    public static void setPassword(String password) {
        PersonToRegister.password.set(password);
    }

    public static String getEmail() {
        return email.get();
    }

    public static StringProperty emailProperty() {
        return email;
    }

    public static void setEmail(String email) {
        PersonToRegister.email.set(email);
    }

    public static String getName() {
        return name.get();
    }

    public static StringProperty nameProperty() {
        return name;
    }

    public static void setName(String name) {
        PersonToRegister.name.set(name);
    }

    public static String getLastName() {
        return lastName.get();
    }

    public static StringProperty lastNameProperty() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        PersonToRegister.lastName.set(lastName);
    }

    public static String getSex() {
        return sex.get();
    }

    public static StringProperty sexProperty() {
        return sex;
    }

    public static void setSex(String sex) {
        PersonToRegister.sex.set(sex);
    }

    public static String getLeadingQuestion() {
        return leadingQuestion.get();
    }

    public static StringProperty leadingQuestionProperty() {
        return leadingQuestion;
    }

    public static void setLeadingQuestion(String leadingQuestion) {
        PersonToRegister.leadingQuestion.set(leadingQuestion);
    }

    public static String getAnswer() {
        return answer.get();
    }

    public static StringProperty answerProperty() {
        return answer;
    }

    public static void setAnswer(String answer) {
        PersonToRegister.answer.set(answer);
    }

    public static Object getDateOfBirth() {
        return dateOfBirth.get();
    }

    public static ObjectProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public static void setDateOfBirth(Object dateOfBirth) {
        PersonToRegister.dateOfBirth.set(dateOfBirth);
    }
}
