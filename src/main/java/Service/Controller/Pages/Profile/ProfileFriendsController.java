package Service.Controller.Pages.Profile;

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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonatan on 2017-02-27.
 */
public class ProfileFriendsController {

    @FXML
    private JFXMasonryPane masonryPane;
    @FXML
    private JFXComboBox<String> comboSearch;
    @FXML
    private JFXTextField textSearch;
    @FXML
    private Label labelNumberOfResult;

    private Person person;

    private Stage stage;

    private List<PersonOther> listFriends = new ArrayList<>();

    private static JFXMasonryPane copyMasonryPane;

    private PopOver popOverProfile;

    private int countResultNumberOfFriends = 0;

    @FXML
    public void initialize() {
        Initialization.initOptionsForSearch(comboSearch);
        copyMasonryPane = masonryPane;
    }

    private void createGridPaneWithProfile(int ID, Image avatar, String nameAndLastName, String login) {
        ImageView imageView = new ImageView(avatar);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton buttonNameAndLastName = initButtonNameAndLastNameAndLogin(nameAndLastName, ID, avatar, nameAndLastName);
        ///////////////////////////////////////////////////////////////////////////////////////
        JFXButton buttonLogin = initButtonNameAndLastNameAndLogin(login, ID, avatar, nameAndLastName);
        ///////////////////////////////////////////////////////////////////////////////////////
        VBox vboxMain = new VBox();
        ///////////////////////////////////////////////////////////////////////////////////////
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

        Pane pane = new Pane(vboxMain);
        pane.setStyle("-fx-background-color: #F9F9F9;");
        pane.setPrefWidth(200);
        pane.setPrefHeight(80);

        pane.setOnMouseEntered((MouseEvent e) -> pane.setStyle("-fx-background-color: #F1F1F1;"));
        pane.setOnMouseExited((MouseEvent e) -> pane.setStyle("-fx-background-color: #F9F9F9;"));

        masonryPane.setPrefWidth(stage.getWidth() - 191 - 219);
        masonryPane.setStyle("-fx-border-style: solid;");
        masonryPane.getChildren().add(pane);

        ++countResultNumberOfFriends;
        labelNumberOfResult.setText(String.valueOf(countResultNumberOfFriends));
    }


    public void initializeListFriends() {
        listFriends = Database.getFriends(person.getID());
        for (int i = 0; i < listFriends.size(); ++i)
            createGridPaneWithProfile(listFriends.get(i).getID(), (Image)listFriends.get(i).getAvatar(),
                    listFriends.get(i).getName().trim() + " " + listFriends.get(i).getLastName().trim(),
                    listFriends.get(i).getLogin().trim());
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
    public void keyReleasedOnTextSearch() {
        countResultNumberOfFriends = 0;
        labelNumberOfResult.setText(String.valueOf(countResultNumberOfFriends));
        search();
    }

    public void filterBy() {
        masonryPane.getChildren().clear();

        if (comboSearch.getSelectionModel().getSelectedIndex() == 0) {
            for (int i = 0; i < listFriends.size(); ++i) {
                if (listFriends.get(i).getName().toLowerCase().trim().indexOf(textSearch.getText().toLowerCase().trim()) != -1
                        || listFriends.get(i).getLastName().toLowerCase().trim().indexOf(textSearch.getText().toLowerCase().trim()) != -1) {
                    createGridPaneWithProfile(listFriends.get(i).getID(), (Image)listFriends.get(i).getAvatar(),
                            listFriends.get(i).getName().trim() + " " + listFriends.get(i).getLastName().trim(),
                            listFriends.get(i).getLogin().trim());
                }
            }
        } else if (comboSearch.getSelectionModel().getSelectedIndex() == 1) {
            for (int i = 0; i < listFriends.size(); ++i) {
                if (listFriends.get(i).getLogin().toLowerCase().trim().indexOf(textSearch.getText().toLowerCase().trim()) != -1) {
                    createGridPaneWithProfile(listFriends.get(i).getID(), (Image)listFriends.get(i).getAvatar(),
                            listFriends.get(i).getName().trim() + " " + listFriends.get(i).getLastName().trim(),
                            listFriends.get(i).getLogin().trim());
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

            PopOverBuilder.showBalloonWithProfile(popOverProfile, ID, avatar, nameAndLastName, person);
            popOverProfile.show(button);

            button.setStyle("-fx-font-size: 22px;-fx-background-color: transparent;");
        });
        button.setOnMouseExited((MouseEvent e) -> button.setStyle("-fx-font-size: 20px;-fx-background-color: transparent;"));

        return button;
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

        hbox.getChildren().addAll(vboxFriends, vbox2);

        return hbox;
    }

//
//    public static void setWidthPage(double width) {
//        if (copyMasonryPane != null && copyScrollPane != null) {
//            copyMasonryPane.setPrefWidth(width - 23 - 195);
//            copyScrollPane.setMaxWidth(width - 195);
//            copyScrollPane.setMinWidth(width - 195);
//            copyScrollPane.setPrefWidth(width - 195);
//        }
//    }
//
//    public static void setHeightPage(double height) {
//        if (copyMasonryPane != null && copyScrollPane != null) {
//            copyScrollPane.setPrefHeight(height - 25 - 42 - 65);
//            copyScrollPane.setMinHeight(height - 25 - 42 - 65);
//            copyScrollPane.setPrefHeight(height - 25 - 42 - 65);
//        }
//    }
//
//    public static void setWidthAndHeightPage(double width, double height) {
//        if (copyMasonryPane != null && copyScrollPane != null) {
//            copyMasonryPane.setPrefWidth(width - 23 - 195);
//            copyScrollPane.setPrefWidth(width - 195);
//            copyScrollPane.setPrefHeight(height - 25 - 42 - 65);
//        }
//    }

    public void setPersonOther(Person person) {
        this.person = person;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
