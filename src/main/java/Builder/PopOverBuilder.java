package Builder;

import Database.Database;
import Service.Controller.Pages.FriendsAndGroups.FriendsController;
import Service.Controller.Pages.FriendsAndGroups.InvitationsController;
import Service.Controller.Pages.FriendsAndGroups.YourInvitationsController;
import Service.Controller.PermanentContainers.ContainerOnTitlebarController;
import Service.Controller.PermanentContainers.ContainerOnTopController;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Service.Person.PersonOther;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by Jonatan on 2017-01-09.
 */
public class PopOverBuilder {
    static public void showInfoForRegisterLogin(PopOver popOver, Node node) {
        VBox vbox = new VBox();

        Label label = new Label("min. 6 character");
        label.setStyle("-fx-font-size: 12;");
        vbox.getChildren().add(label);
        label = new Label("max. 36 character");
        label.setStyle("-fx-font-size: 12;");
        vbox.getChildren().add(label);
        label = new Label("Login can`t start with a digit.");
        label.setStyle("-fx-font-size: 12;");
        vbox.getChildren().add(label);

        vbox.setPadding(new Insets(5, 10, 5, 10));

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);

        popOver.show(node);
    }

    static public void showInfoForRegisterPassword(PopOver popOver, Node node) {
        VBox vbox = new VBox();

        Label label = new Label("min. 6 character");
        label.setStyle("-fx-font-size: 12;");
        vbox.getChildren().add(label);
        label = new Label("max. 36 character");
        label.setStyle("-fx-font-size: 12;");
        vbox.getChildren().add(label);

        vbox.setMaxHeight(30);
        vbox.setPadding(new Insets(5, 10, 5, 10));

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);

        popOver.show(node);
    }

    static public void showInfoForRegisterQuestionAndAnswer(PopOver popOver, Node node) {
        Label label = new Label("max. 255 character");
        label.setStyle("-fx-font-size: 12;");
        label.setPadding(new Insets(5));

        popOver.setContentNode(label);
        popOver.setDetachable(false);

        popOver.show(node);
    }

    static public void showInfoForEditNameSecondNameLastName(PopOver popOver, ImageView imageView) {
        VBox vbox = new VBox();

        Label label = new Label("Name(obligatory):");
        label.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label(" - min. 1 character");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label("All:");
        label.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        label.setPadding(new Insets(10, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label(" - max. 255 characters");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label(" - You can't use special characters!");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);

        popOver.show(imageView);
    }

    static public void showInfoForEditLogin(PopOver popOver, ImageView imageView) {
        VBox vbox = new VBox();

        Label label = new Label("Login:");
        label.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label(" - min. 6 character");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label(" - max. 36 characters");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label(" - You can't use comma(,)!");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);

        popOver.show(imageView);
    }

    static public void showInfoForEditPassword(PopOver popOver, ImageView imageView) {
        VBox vbox = new VBox();

        Label label = new Label("Password:");
        label.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label(" - min. 6 character");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label(" - max. 36 characters");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);

        popOver.show(imageView);
    }

    static public void showInfoForEditQuestionAndAnswer(PopOver popOver, ImageView imageView) {
        VBox vbox = new VBox();

        Label label = new Label("All:");
        label.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label(" - max. 255 characters");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label("There is no need to enter both!");
        label.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        label.setPadding(new Insets(10, 5, 5, 5));
        vbox.getChildren().add(label);

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);

        popOver.show(imageView);
    }

    static public void showInfoForSendMessage(PopOver popOver, ImageView imageView) {
        VBox vbox = new VBox();

        Label label = new Label("Max. 24 recipients!");
        label.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label(" - in recipients are logins!");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        label = new Label(" - on list are names and last names!");
        label.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().add(label);

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);

        popOver.show(imageView);
    }

    static public void showStepsForRegistration(Node node, int indexStep) {
        PopOver popOver = new PopOver();
        VBox vbox = new VBox();

        Label[] label = {new Label("Login"), new Label("Personal data"), new Label("E-mail"),
                new Label("Password"), new Label("Leading question")};

        for (int i = 0; i < label.length; ++i) {
            if (i < indexStep) {
                label[i].setStyle("-fx-background-color: green;");
                label[i].setPadding(new Insets((10)));
                label[i].setTextFill(Paint.valueOf("white"));
            } else {
                label[i].setDisable(true);
                label[i].setPadding(new Insets((8)));
                label[i].setFont(Font.font(18));
            }
        }

        for (int i = 0; i < label.length; ++i)
            vbox.getChildren().add(label[i]);

        vbox.setPadding(new Insets(5, 10, 5, 10));

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);

        popOver.show(node);
    }

    static public void showStepsForChangePassword(Node node, int indexStep) {
        PopOver popOver = new PopOver();
        VBox vbox = new VBox();

        Label[] label = {new Label("Login"), new Label("Question / Year"), new Label("Password")};

        for (int i = 0; i < label.length; ++i) {
            if (i < indexStep) {
                label[i].setStyle("-fx-background-color: green;");
                label[i].setPadding(new Insets((10)));
                label[i].setTextFill(Paint.valueOf("white"));
            } else {
                label[i].setDisable(true);
                label[i].setPadding(new Insets((8)));
                label[i].setFont(Font.font(18));
            }
        }

        for (int i = 0; i < label.length; ++i)
            vbox.getChildren().add(label[i]);

        vbox.setPadding(new Insets(5, 10, 5, 10));

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);

        popOver.show(node);
    }

    static public void showStatementValidation(Node node, String statement) {
        PopOver popOver = new PopOver();
        Group group = new Group();

        Label label = new Label(statement);
        label.setStyle("-fx-text-fill: red; -fx-font-size: 15px;");
        label.setPadding(new Insets(5));

        group.getChildren().add(label);

        popOver.setContentNode(label);
        popOver.setDetachable(false);

        popOver.show(node);
    }

    static public void showInfoServerStatus(Label OK, Label FAILED) {
        PopOver popOver = new PopOver();
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        String status = null;

        if (OK.isVisible())
            status = "OK";
        else if (FAILED.isVisible())
            status = "FAILED";


        Label label = new Label("Server status: " + status);
        vbox.getChildren().add(label);
        label = new Label("Database name: " + Database.getDatabaseName());
        vbox.getChildren().add(label);
        try {
            label = new Label("Your local IPv4: " + Inet4Address.getLocalHost().getHostAddress());
            vbox.getChildren().add(label);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        URL whatismyip;
        try {
            whatismyip = new URL("http://checkip.amazonaws.com");

            BufferedReader in;
            try {
                in = new BufferedReader(new InputStreamReader(
                        whatismyip.openStream()));

                String ip;

                ip = in.readLine();

                label = new Label("Your external IP: " + ip);
                vbox.getChildren().add(label);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);

        if (OK.isVisible())
            popOver.show(OK);
        else if (FAILED.isVisible())
            popOver.show(FAILED);
    }


    private static boolean isFullscreen = false;

    static public void showSettings(PopOver popOver, Node node, Stage stage, Person person) {

        VBox vbox = new VBox();

        {
            HBox hbox = new HBox();

            JFXToggleButton btnNotifications = new JFXToggleButton();
            btnNotifications.setText("Notifications");
            btnNotifications.setSelected(true);
            btnNotifications.setOnAction((event) -> {
                if (btnNotifications.isSelected()) {
                    person.setNotifications(true);
                } else {
                    person.setNotifications(false);
                }
            });
            hbox.getChildren().add(btnNotifications);

            Button btnSound = new Button();
            if (person.isSound()) {
                btnSound.setGraphic(new ImageView("/images/service/sound.png"));
            } else {
                btnSound.setGraphic(new ImageView("/images/service/noSound.png"));
            }
            btnSound.setStyle("-fx-background-color: transparent;");
            btnSound.setPadding(new Insets(15, 0, 0, 13));
            btnSound.setCursor(Cursor.HAND);
            btnSound.setOnAction((event) -> {
                if (person.isSound()) {
                    person.setSound(false);
                    btnSound.setGraphic(new ImageView("/images/service/noSound.png"));
                } else {
                    person.setSound(true);
                    btnSound.setGraphic(new ImageView("/images/service/sound.png"));
                }
            });
            hbox.getChildren().add(btnSound);

            Button btnFullscreen = new Button();
            btnFullscreen.setGraphic(new ImageView("/images/service/fullscreen.png"));
            btnFullscreen.setStyle("-fx-background-color: transparent;");
            btnFullscreen.setPadding(new Insets(17, 7, 0, 10));
            btnFullscreen.setCursor(Cursor.HAND);
            btnFullscreen.setOnAction((event) -> {
                if (!isFullscreen) {
                    isFullscreen = true;
                    stage.setFullScreen(true);
                    ContainerOnTitlebarController.getCopyHbox().setMaxWidth(stage.getWidth());
                    ContainerOnTopController.getCopyAnchorPane().setMaxWidth(stage.getWidth());
                    {
                        FriendsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                        InvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                        YourInvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                    }
                } else {
                    isFullscreen = false;
                    stage.setFullScreen(false);
                    {
                        FriendsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                        InvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                        YourInvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                    }
                }
            });
            hbox.getChildren().add(btnFullscreen);

            vbox.getChildren().add(hbox);
        }

        {
            HBox hbox = new HBox();

            JFXToggleButton btnResizeWindow = new JFXToggleButton();
            btnResizeWindow.setText("Resize window");
            btnResizeWindow.setOnAction((event) -> {
                if (btnResizeWindow.isSelected()) {
                    person.setResizeWindow(true);
                } else {
                    person.setResizeWindow(false);
                }
            });
            hbox.getChildren().add(btnResizeWindow);

            Button btnResizeToDefault = new Button();
            btnResizeToDefault.setGraphic(new ImageView("/images/service/resizeToDefault.png"));
            btnResizeToDefault.setStyle("-fx-background-color: transparent;");
            btnResizeToDefault.setPadding(new Insets(17, 7, 0, 20));
            btnResizeToDefault.setCursor(Cursor.HAND);
            btnResizeToDefault.setOnAction((event) -> {
                stage.setWidth(1000);
                stage.setHeight(695);
                ContainerOnTitlebarController.getCopyHbox().setMaxWidth(stage.getWidth());
                ContainerOnTopController.getCopyAnchorPane().setMaxWidth(stage.getWidth());
                {
                    FriendsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                    InvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                    YourInvitationsController.setWidthAndHeightPage(stage.getWidth(), stage.getHeight());
                }
            });
            hbox.getChildren().add(btnResizeToDefault);

            vbox.getChildren().add(hbox);
        }

        JFXButton btnEditMyProfile = new JFXButton("Edit my profile");
        btnEditMyProfile.setCursor(Cursor.HAND);
        btnEditMyProfile.setOnAction((event) -> {
            new LoadPage().loadEditMyProfile(person);

            popOver.hide();
        });
        vbox.getChildren().add(btnEditMyProfile);

        Separator separator = new Separator(Orientation.HORIZONTAL);
        vbox.getChildren().add(separator);

        JFXButton btnSettings = new JFXButton("Settings");
        btnSettings.setCursor(Cursor.HAND);
        vbox.getChildren().add(btnSettings);

        separator = new Separator(Orientation.HORIZONTAL);
        vbox.getChildren().add(separator);

        btnSettings = new JFXButton("Help");
        btnSettings.setCursor(Cursor.HAND);
        vbox.getChildren().add(btnSettings);

        btnSettings = new JFXButton("Technical Support");
        btnSettings.setCursor(Cursor.HAND);
        vbox.getChildren().add(btnSettings);

        separator = new Separator(Orientation.HORIZONTAL);
        vbox.getChildren().add(separator);

        btnSettings = new JFXButton("Log out");
        btnSettings.setPadding(new Insets(5, 80, 10, 80));
        btnSettings.setStyle("-fx-font-size: 15px;");
        btnSettings.setCursor(Cursor.HAND);
        vbox.getChildren().add(btnSettings);


        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);

        popOver.show(node);
    }


    static public void showProfile(PopOver popOver, Node node, Person person) {

        VBox vbox = new VBox();

        HBox hbox = new HBox();

        ImageView imageView = new ImageView();
        imageView.setFitWidth(80);
        imageView.setFitHeight(64);
        imageView.imageProperty().bind(person.avatarProperty());
        hbox.getChildren().add(imageView);

        Label label = new Label();
        label.textProperty().bind(Bindings.concat(person.nameProperty(), " ", person.lastNameProperty()));
        label.setStyle("-fx-font-size: 15px;");
        label.setMaxWidth(140);
        label.setPadding(new Insets(20, 5, 5, 5));
        hbox.getChildren().add(label);

        vbox.getChildren().add(hbox);


        hbox = new HBox();

        label = new Label();
        label.setGraphic(new ImageView("/images/service/friends.png"));
        label.setPadding(new Insets(10, 5, 5, 5));
        hbox.getChildren().add(label);

        label = new Label();
        label.textProperty().bind(person.numberOfFriendsProperty());
        label.setPadding(new Insets(16, 0, 0, 5));
        label.setStyle("-fx-font-size: 15px;");
        hbox.getChildren().add(label);

        label = new Label();
        label.setGraphic(new ImageView("/images/service/group.png"));
        label.setPadding(new Insets(11, 5, 5, 5));
        hbox.getChildren().add(label);

        label = new Label();
        label.textProperty().bind(person.numberOfInvitationsProperty());
        label.setPadding(new Insets(16, 0, 0, 5));
        label.setStyle("-fx-font-size: 15px;");
        hbox.getChildren().add(label);

        label = new Label();
        label.setGraphic(new ImageView("/images/service/smallEye.png"));
        label.setPadding(new Insets(12, 5, 5, 5));
        hbox.getChildren().add(label);

        label = new Label();
        label.textProperty().bind(person.numberOfYourInvitationsProperty());
        label.setPadding(new Insets(16, 5, 0, 5));
        label.setStyle("-fx-font-size: 15px;");
        hbox.getChildren().add(label);

        vbox.getChildren().add(hbox);


        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);

        popOver.show(node);
    }

    static public void showMenuForButtonThreeDots(PopOver popOver, JFXButton button,
                                                  Button buttonThreeDots) {
        Group group = new Group();

        button.setStyle("-fx-font-size: 15px;");

        group.getChildren().add(button);

        popOver.setContentNode(group);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);

        popOver.show(buttonThreeDots);
    }

    static public void showMenuForProfileInSearch(PopOver popOver, Person person, int recipientID) {
        VBox vbox = new VBox();
        JFXButton button = new JFXButton("Go to account");
        button.setPadding(new Insets(5, 5, 5, 5));
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction((ActionEvent e) -> {
            if (person.getID() == recipientID) {
                new LoadPage().loadMyProfile(person);
            } else {
                new LoadPage().loadProfile(Database.getPerson(recipientID), person);
            }

            popOver.hide();
        });
        button.setOnMouseEntered((MouseEvent e) -> button.setStyle("-fx-background-color: #DCDCDC"));
        button.setOnMouseExited((MouseEvent e) -> button.setStyle("-fx-background-color: white"));
        VBox.setMargin(button, new Insets(5));
        JFXButton button2 = new JFXButton("Send message");
        button2.setPadding(new Insets(5, 5, 5, 5));
        button2.setStyle("-fx-background-color: transparent;");
        button2.setOnAction((ActionEvent e) -> {
            new LoadPage().loadSearchSendMessage(person, recipientID);
            popOver.hide();
        });
        button2.setOnMouseEntered((MouseEvent e) -> button2.setStyle("-fx-background-color: #DCDCDC"));
        button2.setOnMouseExited((MouseEvent e) -> button2.setStyle("-fx-background-color: white"));
        VBox.setMargin(button2, new Insets(5));

        vbox.getChildren().addAll(button, button2);

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);

        System.out.println(recipientID);
    }

    static public void showBalloonWithProfile(PopOver popOver, int recipientID, Image image,
                                              String nameAndLastName, Person person) {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(80);
        imageView.setFitHeight(64);
        JFXButton button = new JFXButton(nameAndLastName);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction((ActionEvent e) -> {
            if (person.getID() == recipientID) {
                new LoadPage().loadMyProfile(person);
            } else {
                new LoadPage().loadProfile(Database.getPerson(recipientID), person);
            }

            popOver.hide();
        });
        button.setOnMouseEntered((MouseEvent e) -> button.setStyle("-fx-background-color: #DCDCDC"));
        button.setOnMouseExited((MouseEvent e) -> button.setStyle("-fx-background-color: white"));
        HBox.setMargin(button, new Insets(10, 0, 0, 10));

        hbox.getChildren().addAll(imageView, button);

        JFXButton button2 = new JFXButton("Go to account");
        button2.setPadding(new Insets(5, 5, 5, 5));
        button2.setStyle("-fx-background-color: transparent;");
        button2.setOnAction((ActionEvent e) -> {
            if (person.getID() == recipientID) {
                new LoadPage().loadMyProfile(person);
            } else {
                new LoadPage().loadProfile(Database.getPerson(recipientID), person);
            }

            popOver.hide();
        });
        button2.setOnMouseEntered((MouseEvent e) -> button2.setStyle("-fx-background-color: #DCDCDC"));
        button2.setOnMouseExited((MouseEvent e) -> button2.setStyle("-fx-background-color: white"));
        VBox.setMargin(button, new Insets(5));
        JFXButton button3 = new JFXButton("Send message");
        button3.setPadding(new Insets(5, 5, 5, 5));
        button3.setStyle("-fx-background-color: transparent;");
        button3.setOnAction((ActionEvent e) -> {
            new LoadPage().loadSearchSendMessage(person, recipientID);
            popOver.hide();
        });
        button3.setOnMouseEntered((MouseEvent e) -> button3.setStyle("-fx-background-color: #DCDCDC"));
        button3.setOnMouseExited((MouseEvent e) -> button3.setStyle("-fx-background-color: white"));
        VBox.setMargin(button3, new Insets(5));

        hbox2.getChildren().addAll(button2, button3);

        vbox.getChildren().addAll(hbox, hbox2);

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);
    }

    static public void showMenuForProfileInFriends(PopOver popOver, Person person, int recipientID) {
        VBox vbox = new VBox();
        {
            JFXButton buttonGoToAccount = new JFXButton("Go to account");
            buttonGoToAccount.setPadding(new Insets(5, 5, 5, 5));
            buttonGoToAccount.setStyle("-fx-background-color: transparent;");
            buttonGoToAccount.setStyle("-fx-background-color: transparent;");
            buttonGoToAccount.setOnAction((ActionEvent e) -> {
                if (person.getID() == recipientID) {
                    new LoadPage().loadMyProfile(person);
                } else {
                    new LoadPage().loadProfile(Database.getPerson(recipientID), person);
                }

                popOver.hide();
            });
            buttonGoToAccount.setOnMouseEntered((MouseEvent e) -> buttonGoToAccount.setStyle("-fx-background-color: #DCDCDC"));
            buttonGoToAccount.setOnMouseExited((MouseEvent e) -> buttonGoToAccount.setStyle("-fx-background-color: white"));
            VBox.setMargin(buttonGoToAccount, new Insets(5));

            vbox.getChildren().add(buttonGoToAccount);
        }
        {
            JFXButton buttonSendMessage = new JFXButton("Send message");
            buttonSendMessage.setPadding(new Insets(5, 5, 5, 5));
            buttonSendMessage.setStyle("-fx-background-color: transparent;");
            buttonSendMessage.setOnAction((ActionEvent e) -> {
                new LoadPage().loadSearchSendMessage(person, recipientID);
                popOver.hide();
            });
            buttonSendMessage.setOnMouseEntered((MouseEvent e) -> buttonSendMessage.setStyle("-fx-background-color: #DCDCDC"));
            buttonSendMessage.setOnMouseExited((MouseEvent e) -> buttonSendMessage.setStyle("-fx-background-color: white"));
            VBox.setMargin(buttonSendMessage, new Insets(5));

            vbox.getChildren().add(buttonSendMessage);
        }
        {
            JFXButton buttonDeleteWithFriends = new JFXButton("Delete with friends");
            buttonDeleteWithFriends.setPadding(new Insets(5, 5, 5, 5));
            buttonDeleteWithFriends.setStyle("-fx-background-color: transparent;");
            buttonDeleteWithFriends.setOnAction((ActionEvent e) -> {
                if (Database.deleteFriends(recipientID, person.getID(), person.getFriends())) {
                    Database.addLogger(person.getID(), "You have removed " + Database.changeIDOnLogin(recipientID ) + " with your friends.");
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationSuccessOperation("Remove from friends",
                                "A friend has been removed.");
                } else {
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationFailedOperation("Remove from friends",
                                "A friend has not been removed.");
                }
                new LoadPage().loadFriends(person);
                popOver.hide();
            });
            buttonDeleteWithFriends.setOnMouseEntered((MouseEvent e) -> buttonDeleteWithFriends.setStyle("-fx-background-color: #DCDCDC"));
            buttonDeleteWithFriends.setOnMouseExited((MouseEvent e) -> buttonDeleteWithFriends.setStyle("-fx-background-color: white"));
            VBox.setMargin(buttonDeleteWithFriends, new Insets(5));

            vbox.getChildren().add(buttonDeleteWithFriends);
        }

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
    }


    static public void showMenuForThreeDotsForPost(PopOver popOverForTreeDots, PopOver popOverForReport, int IDPost,
                                                   boolean youLikeItPost, JFXButton btnNotLikeIt) {
        VBox vbox = new VBox();

        JFXButton btnReportThisPost = new JFXButton("Report this post");
        btnReportThisPost.setOnMouseEntered((MouseEvent e) -> btnReportThisPost.setStyle("-fx-background-color: #E9E9E9;"));
        btnReportThisPost.setOnMouseExited((MouseEvent e) -> btnReportThisPost.setStyle("-fx-background-color: transparent;"));

        btnReportThisPost.setOnAction((ActionEvent e) -> {
            VBox vboxForReport = new VBox();
            vboxForReport.setMinWidth(200);
            vboxForReport.setMaxWidth(200);

            JFXTextArea textArea = new JFXTextArea();
            textArea.setWrapText(true);
            textArea.setMinHeight(100);
            textArea.setMaxHeight(100);
            vboxForReport.getChildren().add(textArea);

            JFXButton report = new JFXButton("Report");
            report.setStyle("-fx-background-color: red;-fx-text-fill: white;");
            report.setOnAction((ActionEvent e2) -> {
                if (Database.sendReportPost(IDPost, textArea.getText())) {
                    NotificationBuilder.showNotificationSuccessOperation("Send report",
                            "The report has been sent.");
                    popOverForReport.hide();
                } else {
                    NotificationBuilder.showNotificationFailedOperation("Send report",
                            "The report has been not sent.");
                }
            });
            report.setOnMouseEntered((MouseEvent e2) ->
                    report.setStyle("-fx-background-color: #FF9933;-fx-text-fill: white;")
            );
            report.setOnMouseExited((MouseEvent e2) ->
                    report.setStyle("-fx-background-color: red;-fx-text-fill: white;")
            );
            VBox.setMargin(report, new Insets(10, 0, 7, 70));
            vboxForReport.getChildren().add(report);

            popOverForReport.setContentNode(vboxForReport);
            popOverForReport.setDetachable(false);
            popOverForReport.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);

            popOverForReport.show(btnReportThisPost);
        });

        vbox.getChildren().addAll(btnReportThisPost, new Separator());

        if (youLikeItPost)
            vbox.getChildren().add(btnNotLikeIt);

        popOverForTreeDots.setContentNode(vbox);
        popOverForTreeDots.setDetachable(false);
        popOverForTreeDots.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);
    }

    static public void showMoreForComment(PopOver popOver, String text) {
        VBox vbox = new VBox();

        TextArea textArea = new TextArea(text);
        textArea.setWrapText(true);
        textArea.setMinWidth(400);
        textArea.setMinHeight(200);
        textArea.setMaxWidth(400);
        textArea.setMaxHeight(200);

        JFXSlider sliderSize = new JFXSlider();
        sliderSize.setValue(15);
        textArea.setStyle("-fx-font-size: 15px;");
        sliderSize.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) ->
                textArea.setStyle("-fx-font-size:" + sliderSize.getValue() + "px;")
        );

        vbox.getChildren().addAll(textArea, sliderSize);

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);
    }

    static public void showBalloonWithProfileForTimelineInPostOnProfilePersonOther(PopOver popOver, Image image,
                                                                                   String nameAndLastName,
                                                                                   PersonOther personOther, Person yourPerson) {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(80);
        imageView.setFitHeight(64);
        JFXButton button = new JFXButton(nameAndLastName);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction((ActionEvent e) -> {
            if (personOther.getID() == yourPerson.getID()) {
                new LoadPage().loadMyProfile(yourPerson);
            } else {
                new LoadPage().loadProfile(Database.getPerson(personOther.getID()), yourPerson);
            }

            popOver.hide();
        });
        button.setOnMouseEntered((MouseEvent e) -> button.setStyle("-fx-background-color: #DCDCDC"));
        button.setOnMouseExited((MouseEvent e) -> button.setStyle("-fx-background-color: white"));
        HBox.setMargin(button, new Insets(10, 0, 0, 10));

        hbox.getChildren().addAll(imageView, button);

        JFXButton button2 = new JFXButton("Go to account");
        button2.setPadding(new Insets(5, 5, 5, 5));
        button2.setStyle("-fx-background-color: transparent;");
        button2.setOnAction((ActionEvent e) -> {
            if (personOther.getID() == yourPerson.getID()) {
                new LoadPage().loadMyProfile(yourPerson);
            } else {
                new LoadPage().loadProfile(Database.getPerson(personOther.getID()), yourPerson);
            }

            popOver.hide();
        });
        button2.setOnMouseEntered((MouseEvent e) -> button2.setStyle("-fx-background-color: #DCDCDC"));
        button2.setOnMouseExited((MouseEvent e) -> button2.setStyle("-fx-background-color: white"));
        VBox.setMargin(button, new Insets(5));
        JFXButton button3 = new JFXButton("Send message");
        button3.setPadding(new Insets(5, 5, 5, 5));
        button3.setStyle("-fx-background-color: transparent;");
        button3.setOnAction((ActionEvent e) -> {
            new LoadPage().loadSearchSendMessage(yourPerson, personOther.getID());
            popOver.hide();
        });
        button3.setOnMouseEntered((MouseEvent e) -> button3.setStyle("-fx-background-color: #DCDCDC"));
        button3.setOnMouseExited((MouseEvent e) -> button3.setStyle("-fx-background-color: white"));
        VBox.setMargin(button3, new Insets(5));

        hbox2.getChildren().addAll(button2, button3);

        vbox.getChildren().addAll(hbox, hbox2);

        popOver.setContentNode(vbox);
        popOver.setDetachable(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);
    }

}
