package Service.Controller.Pages.Search;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import Initialization.Initialization;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Service.Person.PersonOther;
import Validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.PopOver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonatan on 2017-02-05.
 */
public class SearchController {

    @FXML
    private VBox vboxMyFriends, vboxAllPeople;
    @FXML
    private JFXTextField textSearch;
    @FXML
    private JFXComboBox<String> comboSearch;

    private Person person;

    private List<PersonOther> listPersons = new ArrayList<PersonOther>();

    private PopOver popOverMenu, popOverProfile;

    @FXML
    public void initialize() {
        Initialization.initOptionsForSearch(comboSearch);
    }

    @FXML
    public void search() {
        vboxAllPeople.getChildren().clear();
        vboxMyFriends.getChildren().clear();

        if (comboSearch.getSelectionModel().getSelectedIndex() == 0)
            initPersonsByNameAndLastNameAndLogin();
        if (comboSearch.getSelectionModel().getSelectedIndex() == 1)
            initPersonsByNameAndLastName();
        if (comboSearch.getSelectionModel().getSelectedIndex() == 2)
            initPersonsByLogin();
    }

    private void createGridPaneWithProfile(int ID, Image avatar, String nameAndLastName, String login) {
        ///////////////////////////////////////////////////////////////////////////////////////
        ImageView imageView = new ImageView(avatar);
        imageView.setCursor(Cursor.HAND);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton button = initButtonNameAndLastNameAndLogin(nameAndLastName, ID, avatar, nameAndLastName);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton button2 = initButtonNameAndLastNameAndLogin(login, ID, avatar, nameAndLastName);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton button3 = initButtogGoToAccount(ID);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton button4 = initButtonOperationInFriends(ID);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton button5 = initButtonArrowDown(ID);
        ///////////////////////////////////////////////////////////////////////////////////////
        Separator separator = new Separator(Orientation.HORIZONTAL);
        ///////////////////////////////////////////////////////////////////////////////////////
        Separator separator2 = new Separator(Orientation.HORIZONTAL);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton buttonNumberOfFriends = initButtonNumberOfFriends(ID);
        ///////////////////////////////////////////////////////////////////////////////////////

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #F9F9F9;");
        gridPane.addColumn(0, imageView);
        gridPane.addColumn(1, button, separator, button2, separator2, button3);
        gridPane.addColumn(2, button4);
        gridPane.add(button5, 2, 4);
        gridPane.add(buttonNumberOfFriends, 0, 4);

        gridPane.setOnMouseEntered((MouseEvent e) ->
                gridPane.setStyle("-fx-background-color: #F1F1F1;")
        );
        gridPane.setOnMouseExited((MouseEvent e) -> {
            gridPane.setStyle("-fx-background-color: #F9F9F9;");
            button5.setStyle("-fx-background-color: transparent;");
        });

        GridPane.setMargin(button3, new Insets(0, 0, 10, 0));

        ColumnConstraints columnConstraints = new ColumnConstraints(100);
        gridPane.getColumnConstraints().add(columnConstraints);

        columnConstraints = new ColumnConstraints(200);
        gridPane.getColumnConstraints().add(columnConstraints);

        columnConstraints = new ColumnConstraints(80);
        gridPane.getColumnConstraints().add(columnConstraints);

        RowConstraints rowConstraints = new RowConstraints(50);
        gridPane.getRowConstraints().add(rowConstraints);

        rowConstraints = new RowConstraints(10);
        gridPane.getRowConstraints().add(rowConstraints);

        rowConstraints = new RowConstraints(30);
        gridPane.getRowConstraints().add(rowConstraints);

        rowConstraints = new RowConstraints(20);
        gridPane.getRowConstraints().add(rowConstraints);

        GridPane.setRowSpan(imageView, 4);
        GridPane.setRowSpan(button4, 4);

        GridPane.setValignment(button, VPos.BOTTOM);
        GridPane.setValignment(separator, VPos.TOP);

        GridPane.setValignment(buttonNumberOfFriends, VPos.CENTER);

        GridPane.setValignment(button2, VPos.BOTTOM);
        GridPane.setValignment(separator2, VPos.TOP);
        GridPane.setValignment(button5, VPos.BOTTOM);

        GridPane.setHalignment(button3, HPos.CENTER);
        GridPane.setHalignment(button4, HPos.CENTER);
        GridPane.setHalignment(button5, HPos.CENTER);


        if (button4.getText().equals("Remove\nfrom\nfriends"))
            vboxMyFriends.getChildren().add(gridPane);
        else if (button4.getText().equals("ADD\nTO\nFRIENDS") || button4.getText().equals("Delete\ninvitation"))
            vboxAllPeople.getChildren().add(gridPane);
    }

    public void initPersonsByNameAndLastNameAndLogin() {
        listPersons = Database.loadNamesAndLastNamesAndLoginByText(person.getID(), textSearch.getText());
        for (int i = 0; i < listPersons.size(); ++i)
            createGridPaneWithProfile(listPersons.get(i).getID(), listPersons.get(i).getAvatar(),
                    listPersons.get(i).getName() + " " + listPersons.get(i).getLastName(),
                    listPersons.get(i).getLogin());
    }

    private void initPersonsByNameAndLastName() {
        listPersons = Database.loadNamesAndLastNamesByText(person.getID(), textSearch.getText());
        for (int i = 0; i < listPersons.size(); ++i)
            createGridPaneWithProfile(listPersons.get(i).getID(), listPersons.get(i).getAvatar(),
                    listPersons.get(i).getName() + " " + listPersons.get(i).getLastName(),
                    listPersons.get(i).getLogin());
    }

    private void initPersonsByLogin() {
        listPersons = Database.loadLoginsByText(person.getID(), textSearch.getText());
        for (int i = 0; i < listPersons.size(); ++i)
            createGridPaneWithProfile(listPersons.get(i).getID(), listPersons.get(i).getAvatar(),
                    listPersons.get(i).getName() + " " + listPersons.get(i).getLastName(),
                    listPersons.get(i).getLogin());
    }


    private JFXButton initButtonNameAndLastNameAndLogin(String textOnButton, int ID, Image avatar,
                                                        String nameAndLastName) {
        JFXButton button = new JFXButton(textOnButton);
        button.setCursor(Cursor.HAND);
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

    private JFXButton initButtonNumberOfFriends(int ID) {
        JFXButton buttonNumberOfFriends = new JFXButton(Database.countFriends(ID), new ImageView("/images/service/friends.png"));
        buttonNumberOfFriends.setId("btnNumberOfFriends");
        buttonNumberOfFriends.setCursor(Cursor.HAND);
        buttonNumberOfFriends.setPadding(new Insets(3));
        GridPane.setMargin(buttonNumberOfFriends, new Insets(0, 0, 0, 15));
        buttonNumberOfFriends.setOnAction((ActionEvent e) ->
                new LoadPage().loadSearchGoToProfileFriends(Database.getPerson(ID), person)
        );

        return buttonNumberOfFriends;
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

    private JFXButton initButtogGoToAccount(int ID) {
        JFXButton button = new JFXButton("GO TO ACCOUNT");
        button.setStyle("-fx-font-size: 20px;");
        button.setCursor(Cursor.HAND);
        button.setOnAction((ActionEvent e) ->
                new LoadPage().loadProfile(Database.getPerson(ID), person)
        );

        return button;
    }

    private JFXButton initButtonOperationInFriends(int ID) {
        JFXButton button = new JFXButton();
        if (Validation.changeTextOnList(String.valueOf(ID), person.getInvitedFriends())) {
            button.setText("Delete\ninvitation");
            button.setStyle("-fx-background-color: #FF6666;");
        } else if (Validation.changeTextOnList(String.valueOf(ID), person.getFriends())) {
            button.setText("Remove\nfrom\nfriends");
            button.setStyle("-fx-background-color: #FF6666;");
        } else {
            button.setText("ADD\nTO\nFRIENDS");
            button.setStyle("-fx-background-color: #00FF33;");
        }
        button.setCursor(Cursor.HAND);
        button.setMinHeight(75);
        button.setTextAlignment(TextAlignment.CENTER);
        button.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            if (button.getText().equals("ADD\nTO\nFRIENDS")) {
                button.setText("Delete\ninvitation");
                if (Database.addInvitedFriends(ID, person.getID(), person.getInvitedFriends())) {
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationSuccessOperation("Add to friends",
                                "The invitation has been sent.");
                } else {
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationFailedOperation("Add to friends",
                                "The invitation has not been sent.");
                }
                button.setStyle("-fx-background-color: #FF6666;");
            } else if (button.getText().equals("Remove\nfrom\nfriends")) {
                button.setText("ADD\nTO\nFRIENDS");
                if (Database.deleteFriends(ID, person.getID(), person.getFriends())) {
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationSuccessOperation("Remove from friends",
                                "A friend has been removed.");
                } else {
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationFailedOperation("Remove from friends",
                                "A friend has not been removed.");
                }
                button.setStyle("-fx-background-color: #00FF33;");
            } else {
                button.setText("ADD\nTO\nFRIENDS");
                if (Database.deleteInvitedFriends(ID, person.getID(), person.getInvitedFriends())) {
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationSuccessOperation("Delete invitation",
                                "The invitation has been removed.");
                } else {
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationFailedOperation("Delete invitation",
                                "The invitation has not been removed.");
                }
                button.setStyle("-fx-background-color: #00FF33;");
            }
        });

        return button;
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setTextField(String text) {
        textSearch.setText(text);
    }
}
