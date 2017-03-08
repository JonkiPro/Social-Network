package Service.Person;

import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonatan on 2017-01-11.
 */
public class Person {

    protected int ID;
    protected StringProperty login = new SimpleStringProperty("");
    private StringProperty password = new SimpleStringProperty("");
    protected StringProperty email = new SimpleStringProperty("");
    protected StringProperty name = new SimpleStringProperty("");
    protected StringProperty secondName = new SimpleStringProperty("");
    protected StringProperty lastName = new SimpleStringProperty("");
    protected StringProperty sex = new SimpleStringProperty("");
    private StringProperty leadingQuestion = new SimpleStringProperty("");
    private StringProperty answer = new SimpleStringProperty("");
    protected ObjectProperty dateOfBirth = new SimpleObjectProperty<LocalDate>();
    protected StringProperty aboutMe = new SimpleStringProperty("");

    protected ObjectProperty avatar = new SimpleObjectProperty<Image>();

    protected StringProperty numberOfFriends = new SimpleStringProperty("");
    protected StringProperty numberOfYourInvitations = new SimpleStringProperty("");
    protected StringProperty numberOfInvitations = new SimpleStringProperty("");

    protected List<Integer> friends = new ArrayList<Integer>();
    private List<Integer> invitedFriends  = new ArrayList<Integer>();

    private boolean notifications = true;
    private boolean sound = true;
    private boolean resizeWindow = false;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public StringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getSex() {
        return sex.get();
    }

    public StringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getLeadingQuestion() {
        return leadingQuestion.get();
    }

    public StringProperty leadingQuestionProperty() {
        return leadingQuestion;
    }

    public void setLeadingQuestion(String leadingQuestion) {
        this.leadingQuestion.set(leadingQuestion);
    }

    public String getAnswer() {
        return answer.get();
    }

    public StringProperty answerProperty() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }

    public Object getDateOfBirth() {
        return dateOfBirth.get();
    }

    public ObjectProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Object dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getAboutMe() {
        return aboutMe.get();
    }

    public StringProperty aboutMeProperty() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe.set(aboutMe);
    }

    public Object getAvatar() {
        return avatar.get();
    }

    public ObjectProperty avatarProperty() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar.set(avatar);
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean isResizeWindow() {
        return resizeWindow;
    }

    public void setResizeWindow(boolean resizeWindow) {
        this.resizeWindow = resizeWindow;
    }

    public String getNumberOfFriends() {
        return numberOfFriends.get();
    }

    public StringProperty numberOfFriendsProperty() {
        return numberOfFriends;
    }

    public void setNumberOfFriends(String numberOfFriends) {
        this.numberOfFriends.set(numberOfFriends);
    }

    public String getNumberOfYourInvitations() {
        return numberOfYourInvitations.get();
    }

    public StringProperty numberOfYourInvitationsProperty() {
        return numberOfYourInvitations;
    }

    public void setNumberOfYourInvitations(String numberOfYourInvitations) {
        this.numberOfYourInvitations.set(numberOfYourInvitations);
    }

    public String getNumberOfInvitations() {
        return numberOfInvitations.get();
    }

    public StringProperty numberOfInvitationsProperty() {
        return numberOfInvitations;
    }

    public void setNumberOfInvitations(String numberOfInvitations) {
        this.numberOfInvitations.set(numberOfInvitations);
    }

    public List<Integer> getFriends() {
        return friends;
    }

    public void setFriends(List<Integer> friends) {
        this.friends = friends;
    }

    public void createImplementsForListFriends() {
        friends = new ArrayList<Integer>();
    }

    public List<Integer> getInvitedFriends() {
        return invitedFriends;
    }

    public void setInvitedFriends(List<Integer> invitedFriends) {
        this.invitedFriends = invitedFriends;
    }

    public void createImplementsForListInvitedFriends() {
        invitedFriends = new ArrayList<Integer>();
    }


}
