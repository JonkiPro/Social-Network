package Service.Controller.Pages.Profile;

import Database.Database;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Service.Person.PersonOther;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Jonatan on 2017-02-27.
 */
public class ProfileController {

    @FXML
    private ImageView avatar;
    @FXML
    private Label textSomeInfoAboutMe;
    @FXML
    private JFXButton btnTimeline, btnInformation, btnFriends, btnActionOnFriends;
    @FXML
    private Label labelName, labelLastName, labelSecondName, labelDateOfBirth, labelLogin, labelEmail;
    @FXML
    private ScrollPane scrollPane;

    private PersonOther personOther;
    private Person yourPerson;

    @FXML
    public void initialize() {
        btnTimeline.setDisable(true);
    }

    public void initAllData() {
        labelName.setText(personOther.getName());
        labelLastName.setText(personOther.getLastName());

        if (personOther.getSecondName() != null && personOther.getSecondName().length() > 0)
            labelSecondName.setText(personOther.getSecondName());
        else {
            labelSecondName.setStyle("-fx-text-fill: red;");
            labelSecondName.setText("empty");
        }

        if (personOther.getDateOfBirth() != null)
            labelDateOfBirth.setText(personOther.getDateOfBirth().toString());
        else {
            labelDateOfBirth.setStyle("-fx-text-fill: red;");
            labelDateOfBirth.setText("empty");
        }

        if (personOther.getAboutMe() != null)
            textSomeInfoAboutMe.setText(personOther.getAboutMe());
        else {
            textSomeInfoAboutMe.setStyle("-fx-text-fill: red;");
            textSomeInfoAboutMe.setText("empty");
        }

        labelLogin.setText(personOther.getLogin());
        labelEmail.setText(personOther.getEmail());

        try {
            avatar.setImage(new Image(Database.getAvatar(personOther.getID())));
        } catch(NullPointerException e) {
            avatar.setImage(new Image("/images/service/person.png"));
        }

        if (Database.getListIDsFriends(yourPerson.getID()).contains(personOther.getID())) {
            initBtnDeleteWithFriends();
        } else if (Database.getListIDsYourInvitations(yourPerson.getID()).contains(personOther.getID())) {
            initBtnDeleteInvitation();
        } else if (Database.getListIDsInvitations(yourPerson.getID()).contains(personOther.getID())) {
            initBtnAccept();
        } else {
            initBtnAddToFriend();
        }
    }

    public void initTimeline() {
        new LoadPage().loadProfileTimeline(personOther, yourPerson, scrollPane);
    }


    @FXML
    public void actionOnBtnFriends() {
        if (btnActionOnFriends.getText().equals("ADD TO FRIENDS")) {
            Database.addInvitedFriends(personOther.getID(), yourPerson.getID(), yourPerson.getInvitedFriends());
            initBtnDeleteInvitation();
        } else if (btnActionOnFriends.getText().equals("ACCEPT")) {
            Database.addFriend(personOther.getID(), yourPerson.getID(), yourPerson.getFriends());
            initBtnDeleteWithFriends();
        } else if (btnActionOnFriends.getText().equals("DELETE INVITATION")) {
            Database.deleteInvitedFriends(personOther.getID(), yourPerson.getID(), yourPerson.getInvitedFriends());
            initBtnAddToFriend();
        } else if (btnActionOnFriends.getText().equals("DELETE WITH FRIENDS")) {
            Database.deleteFriends(personOther.getID(), yourPerson.getID(), yourPerson.getFriends());
            initBtnAddToFriend();
        }
    }

    @FXML
    public void sendMessage() {
        new LoadPage().loadSearchSendMessage(yourPerson, personOther.getID());
    }

    @FXML
    public void showTimeline() {
        btnTimeline.setDisable(true);
        btnInformation.setDisable(false);
        btnFriends.setDisable(false);

        new LoadPage().loadProfile(personOther, yourPerson);
    }

    @FXML
    public void showInformation() {
        btnTimeline.setDisable(false);
        btnInformation.setDisable(true);
        btnFriends.setDisable(false);

        new LoadPage().loadProfileInformation(personOther, scrollPane);
    }

    @FXML
    public void showFriends() {
        btnTimeline.setDisable(false);
        btnInformation.setDisable(false);
        btnFriends.setDisable(true);

        new LoadPage().loadProfileFriends(personOther, scrollPane);
    }

    private void initBtnAddToFriend() {
        btnActionOnFriends.setText("ADD TO FRIENDS");
        btnActionOnFriends.setStyle("-fx-background-color: green;-fx-text-fill: white;");
    }

    private void initBtnAccept() {
        btnActionOnFriends.setText("ACCEPT");
        btnActionOnFriends.setStyle("-fx-background-color: green;-fx-text-fill: white;");
    }

    private void initBtnDeleteInvitation() {
        btnActionOnFriends.setText("DELETE INVITATION");
        btnActionOnFriends.setStyle("-fx-background-color: red;-fx-text-fill: white;");
    }

    private void initBtnDeleteWithFriends() {
        btnActionOnFriends.setText("DELETE WITH FRIENDS");
        btnActionOnFriends.setStyle("-fx-background-color: red;-fx-text-fill: white;");
    }


    public void setScrollPane(Node node) {
        this.scrollPane.setContent(node);
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }


    public void setPersonOther(PersonOther personOther) {
        this.personOther = personOther;
    }

    public void setYourPerson(Person yourPerson) {
        this.yourPerson = yourPerson;
    }

}
