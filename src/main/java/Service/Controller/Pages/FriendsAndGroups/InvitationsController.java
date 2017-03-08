package Service.Controller.Pages.FriendsAndGroups;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import Initialization.Initialization;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Service.Person.PersonOther;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jonatan on 2017-02-14.
 */
public class InvitationsController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXMasonryPane masonryPane;
    @FXML
    private JFXComboBox<String> comboSearch;
    @FXML
    private JFXTextField textSearch;
    @FXML
    private JFXButton buttonInvitations;
    @FXML
    private Label labelNumberOfResult, labelNumberOfFriends, labelNumberOfInvitations, labelNumberOfYourInvitations;

    private Person person;

    private Stage stage;

    private List<PersonOther> listInvitations = new ArrayList<>();

    private static JFXMasonryPane copyMasonryPane;
    private static ScrollPane copyScrollPane;

    private PopOver popOverProfile, popOverMenu;

    private int countResultNumberOfFriends = 0;

    @FXML
    public void initialize() {
        Initialization.initOptionsForSearch(comboSearch);
        copyMasonryPane = masonryPane;
        copyScrollPane = scrollPane;
        buttonInvitations.setStyle("-fx-background-color: #E6E6E6;");
        labelNumberOfFriends.setStyle("-fx-background-color: blue;-fx-text-fill: white;-fx-font-size: 15px;");
        labelNumberOfInvitations.setStyle("-fx-background-color: blue;-fx-text-fill: white;-fx-font-size: 15px;");
        labelNumberOfYourInvitations.setStyle("-fx-background-color: blue;-fx-text-fill: white;-fx-font-size: 15px;");
    }

    private void createGridPaneWithProfile(int ID, Image avatar, String nameAndLastName, String login) {
        Pane pane = new Pane();
        ///////////////////////////////////////////////////////////////////////////////////////
        ImageView imageView = new ImageView(avatar);
        imageView.setFitWidth(80);
        imageView.setFitHeight(64);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton buttonNameAndLastName = initButtonNameAndLastNameAndLogin(nameAndLastName, ID, avatar, nameAndLastName);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton buttonLogin = initButtonNameAndLastNameAndLogin(login, ID, avatar, nameAndLastName);
        ///////////////////////////////////////////////////////////////////////////////////////
        VBox vboxMain = new VBox();
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonNameAndLastName, new Separator(), buttonLogin);
        hbox.getChildren().addAll(imageView, vbox);
        HBox.setMargin(vbox, new Insets(0, 0, 0, 10));
        vboxMain.getChildren().add(hbox);
        ///////////////////////////////////////////////////////////////////////////////////////
        HBox hboxFriends = initHBoxWithLabelNumberOfFriends(ID);
        ///////////////////////////////////////////////////////////////////////////////////////
        vboxMain.getChildren().add(hboxFriends);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton buttonAccept = initButtonAccept(ID);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton buttonDecline = initButtonDecline(ID, pane);
        ///////////////////////////////////////////////////////////////////////////////////////
        HBox hboxForButtonAcceptAndDecline = new HBox();
        hboxForButtonAcceptAndDecline.getChildren().addAll(buttonAccept, buttonDecline);
        VBox.setMargin(hboxForButtonAcceptAndDecline, new Insets(0, 0, 0, 55));
        vboxMain.getChildren().addAll(hboxForButtonAcceptAndDecline);
        ///////////////////////////////////////////////////////////////////////////////////////

        pane.getChildren().add(vboxMain);
        Pane paneMain = new Pane(pane);
        paneMain.setStyle("-fx-background-color: #F9F9F9;");
        paneMain.setPrefWidth(200);
        paneMain.setPrefHeight(90);

        paneMain.setOnMouseEntered((MouseEvent e) -> paneMain.setStyle("-fx-background-color: #F1F1F1;"));
        paneMain.setOnMouseExited((MouseEvent e) -> paneMain.setStyle("-fx-background-color: #F9F9F9;"));

        masonryPane.setPrefWidth(stage.getWidth() - 215);
        masonryPane.setHSpacing(30);
        masonryPane.setVSpacing(20);
        masonryPane.getChildren().add(paneMain);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        ++countResultNumberOfFriends;
        labelNumberOfResult.setText(String.valueOf(countResultNumberOfFriends));
    }


    public void initializeListFriends(Person person) {
        listInvitations = Database.getInvitations(person.getID());
        labelNumberOfFriends.setText(String.valueOf(listInvitations.size()));
        for (int i = 0; i < listInvitations.size(); ++i)
            createGridPaneWithProfile(listInvitations.get(i).getID(), listInvitations.get(i).getAvatar(),
                    listInvitations.get(i).getName().trim() + " " + listInvitations.get(i).getLastName().trim(),
                    listInvitations.get(i).getLogin().trim());

        initNumbersOnButtons();
    }

    private void initNumbersOnButtons() {
        labelNumberOfFriends.setText(Database.countFriends(person.getID()));
        labelNumberOfInvitations.setText(String.valueOf(listInvitations.size()));
        labelNumberOfYourInvitations.setText(Database.countYourInvitations(person.getID()));
    }

    @FXML
    public void search() {
        switch (comboSearch.getSelectionModel().getSelectedIndex()) {
            case 0: {
                filterBy();
                break;
            }
            case 1: {
                filterBy();
                break;
            }
        }
    }

    @FXML
    public void keyReleasedOnTextSearch(KeyEvent e) {
        countResultNumberOfFriends = 0;
        labelNumberOfResult.setText(String.valueOf(countResultNumberOfFriends));
        search();
    }

    public void filterBy() {
        masonryPane.getChildren().clear();

        if (comboSearch.getSelectionModel().getSelectedIndex() == 0) {
            for (int i = 0; i < listInvitations.size(); ++i) {
                if (listInvitations.get(i).getName().indexOf(textSearch.getText()) != -1
                        || listInvitations.get(i).getLastName().indexOf(textSearch.getText()) != -1) {
                    createGridPaneWithProfile(listInvitations.get(i).getID(), listInvitations.get(i).getAvatar(),
                            listInvitations.get(i).getName().trim() + " " + listInvitations.get(i).getLastName().trim(),
                            listInvitations.get(i).getLogin().trim());
                }
            }
        } else if (comboSearch.getSelectionModel().getSelectedIndex() == 1) {
            for (int i = 0; i < listInvitations.size(); ++i) {
                if (listInvitations.get(i).getLogin().indexOf(textSearch.getText()) != -1) {
                    createGridPaneWithProfile(listInvitations.get(i).getID(), listInvitations.get(i).getAvatar(),
                            listInvitations.get(i).getName().trim() + " " + listInvitations.get(i).getLastName().trim(),
                            listInvitations.get(i).getLogin().trim());
                }
            }
        }
    }


    private JFXButton initButtonNameAndLastNameAndLogin(String textOnButton, int ID, Image avatar,
                                                        String nameAndLastName) {
        JFXButton button = new JFXButton(textOnButton);
        button.setCursor(Cursor.HAND);
        button.setMaxWidth(150);
        button.setStyle("-fx-font-size: 20px;-fx-background-color: transparent;");
        button.setOnAction((ActionEvent e) -> {
            if (person.getID() == ID) {
                new LoadPage().loadMyProfile(person);
            } else {
                new LoadPage().loadProfile(Database.getPerson(ID), person);
            }

            popOverProfile.hide();
        });
        button.setOnMouseEntered((MouseEvent e) -> {
            if (popOverProfile == null)
                popOverProfile = new PopOver();

            if (popOverMenu != null && popOverMenu.isShowing())
                popOverMenu.hide();

            PopOverBuilder.showBalloonWithProfile(popOverProfile, ID, avatar, nameAndLastName, person);
            popOverProfile.show(button);

            button.setStyle("-fx-font-size: 22px;-fx-background-color: transparent;");
        });
        button.setOnMouseExited((MouseEvent e) -> button.setStyle("-fx-font-size: 20px;-fx-background-color: transparent;"));

        return button;
    }

    private JFXButton initButtonAccept(int ID) {
        JFXButton buttonAccept = new JFXButton("", new ImageView("/images/service/accept.png"));
        buttonAccept.setCursor(Cursor.HAND);
        buttonAccept.setStyle("-fx-background-color: transparent;");
        buttonAccept.setTooltip(new Tooltip("Accept"));
        buttonAccept.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            if (Database.addFriend(person.getID(), ID, person.getFriends())) {
                Database.addLogger(person.getID(), Database.changeIDOnLogin(ID) + " has been added to your friends list.");
                Database.addLogger(ID, person.getLogin() + " has been added to your friends list.");
                if (person.isNotifications())
                    NotificationBuilder.showNotificationSuccessOperation("Accept invitation",
                            "The invitation was accepted.");
            } else {
                if (person.isNotifications())
                    NotificationBuilder.showNotificationSuccessOperation("Accept invitation",
                            "The invitation was not accepted.");
            }
            new LoadPage().loadInvitations(person);
        });
        buttonAccept.setOnMouseEntered((MouseEvent e) -> buttonAccept.setStyle("-fx-background-color: white;"));
        buttonAccept.setOnMouseExited((MouseEvent e) -> buttonAccept.setStyle("-fx-background-color: transparent;"));

        return buttonAccept;
    }

    private JFXButton initButtonDecline(int ID, Pane pane) {
        JFXButton buttonDecline = new JFXButton("", new ImageView("/images/service/decline.png"));
        buttonDecline.setCursor(Cursor.HAND);
        buttonDecline.setStyle("-fx-background-color: transparent;");
        buttonDecline.setTooltip(new Tooltip("Decline"));
        buttonDecline.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            pane.setDisable(true);
            if (Database.declineInivitation(person.getID(), ID)) {
                if (person.isNotifications())
                    NotificationBuilder.showNotificationSuccessOperation("Decline invitation",
                            "The invitation was rejected.");
                labelNumberOfResult.setText(String.valueOf(Integer.parseInt(labelNumberOfResult.getText()) - 1));
                labelNumberOfInvitations.setText(labelNumberOfResult.getText());
            } else {
                if (person.isNotifications())
                    NotificationBuilder.showNotificationSuccessOperation("Decline invitation",
                            "The invitation was not rejected.");
            }
        });
        buttonDecline.setOnMouseEntered((MouseEvent e) -> buttonDecline.setStyle("-fx-background-color: white;"));
        buttonDecline.setOnMouseExited((MouseEvent e) -> buttonDecline.setStyle("-fx-background-color: transparent;"));

        return buttonDecline;
    }

    private HBox initHBoxWithLabelNumberOfFriends(int ID) {
        HBox hbox = new HBox();
        ImageView imageView2 = new ImageView("/images/service/friends.png");
        VBox.setMargin(imageView2, new Insets(0, 0, 0, 10));
        VBox vboxFriends = new VBox(imageView2);
        vboxFriends.setAlignment(Pos.CENTER_LEFT);
        Label label = new Label(Database.countFriends(ID));
        label.setPadding(new Insets(0, 0, 0, 10));
        label.setStyle("-fx-font-size: 17px;");
        VBox vbox2 = new VBox(label);
        vbox2.setAlignment(Pos.CENTER_LEFT);
        JFXButton button5 = initButtonArrowDown(ID);
        HBox.setMargin(button5, new Insets(0, 0, 0, 70));

        hbox.getChildren().addAll(vboxFriends, vbox2, button5);

        return hbox;
    }

    private JFXButton initButtonArrowDown(int ID) {
        JFXButton button = new JFXButton("", new ImageView("/images/service/arrowDown.png"));
        button.setCursor(Cursor.HAND);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction((ActionEvent e) -> {
            if (popOverMenu == null)
                popOverMenu = new PopOver();

            PopOverBuilder.showMenuForProfileInSearch(popOverMenu, person, ID);
            popOverMenu.show(button);
        });
        button.setOnMouseEntered((MouseEvent e) -> button.setStyle("-fx-background-color: white;"));
        button.setOnMouseExited((MouseEvent e) -> button.setStyle("-fx-background-color: transparent;"));

        return button;
    }

    @FXML
    public void btnFriends() {
        new LoadPage().loadFriends(person);
    }

    @FXML
    public void btnYourInvitations() {
        new LoadPage().loadYourInvitations(person);
    }


    public static void setWidthPage(double width) {
        if (copyMasonryPane != null && copyScrollPane != null) {
            copyMasonryPane.setPrefWidth(width - 23 - 195);
            copyScrollPane.setMaxWidth(width - 195);
            copyScrollPane.setMinWidth(width - 195);
            copyScrollPane.setPrefWidth(width - 195);
        }
    }

    public static void setHeightPage(double height) {
        if (copyMasonryPane != null && copyScrollPane != null) {
            copyScrollPane.setPrefHeight(height - 25 - 42 - 65);
            copyScrollPane.setMinHeight(height - 25 - 42 - 65);
            copyScrollPane.setPrefHeight(height - 25 - 42 - 65);
        }
    }

    public static void setWidthAndHeightPage(double width, double height) {
        if (copyMasonryPane != null && copyScrollPane != null) {
            copyMasonryPane.setPrefWidth(width - 23 - 195);
            copyScrollPane.setPrefWidth(width - 195);
            copyScrollPane.setPrefHeight(height - 25 - 42 - 65);
        }
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
