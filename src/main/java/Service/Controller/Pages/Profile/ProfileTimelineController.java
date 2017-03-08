package Service.Controller.Pages.Profile;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import FileBuilder.FileBuilder;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Service.Person.PersonOther;
import Service.Post.Post;
import Validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jonatan on 2017-02-27.
 */

public class ProfileTimelineController {

    @FXML
    private VBox vbox;

    private PersonOther person;
    private Person yourPerson;
    private Stage stage;

    private Map<Integer, Boolean> postsThatYouLike = new HashMap<>();
    private List<Integer> IDsYourPosts = new ArrayList<>();
    private List<Integer> reportsPost = new ArrayList<>();

    private PopOver popOverProfile, popOverForThreeDots, popOverForReport;

    public void initPosts() {
        vbox.getChildren().clear();

        IDsYourPosts = Database.getNumberOfPostsForPersonOnBoard(person.getID());

        loadCreateNewPost();
        reportsPost = Database.getReportsPost();
        loadPosts();
    }

    private void loadCreateNewPost() {
        VBox vbox = new VBox();
        vbox.setStyle("-fx-border-style: solid;");
        vbox.setMinWidth(400);
        vbox.setMaxWidth(400);
        vbox.setMinHeight(170);
        vbox.setMaxHeight(170);

        JFXTextArea textArea = new JFXTextArea();
        textArea.setMinHeight(100);
        textArea.setMaxHeight(100);
        vbox.getChildren().add(textArea);

        HBox hbox = new HBox();

        JFXButton button = new JFXButton("SEND");
        button.setId("btnSend");
        button.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            if (textArea.getText().length() == 0) {
                PopOverBuilder.showStatementValidation(textArea, "Please enter some text...");
            } else {
                Database.addPostOnBoardPersonOther(yourPerson.getID(), this.person.getID(), textArea.getText(),
                                                                            true);

                NotificationBuilder.showNotificationSuccessOperation("Send post",
                        "Your post has been added!");
            }
            new LoadPage().loadProfile(this.person, yourPerson);
        });
        HBox.setMargin(button, new Insets(10, 0, 0, 150));
        hbox.getChildren().add(button);

        JFXButton btnUploadText = new JFXButton("", new ImageView("/images/service/uploadSmall.png"));
        btnUploadText.setId("btnUploadText");
        btnUploadText.setCursor(Cursor.HAND);
        btnUploadText.setTooltip(new Tooltip("Upload text"));
        btnUploadText.setPadding(new Insets(3));
        btnUploadText.setOnAction((ActionEvent e) ->
                uploadText(textArea)
        );
        HBox.setMargin(btnUploadText, new Insets(35, 0, 0, 120));
        hbox.getChildren().add(btnUploadText);

        vbox.getChildren().add(hbox);
        VBox.setMargin(vbox, new Insets(0, 0, 30, 0));
        this.vbox.getChildren().add(vbox);
        this.vbox.setPadding(new Insets(0, 0, 0, 40));
    }

    private void loadPosts() {
        List<Post> listPosts;

        listPosts = Database.getPosts(IDsYourPosts);

        int count = 0;
        for (int i = 0; i < listPosts.size(); ++i) {
            addPost(listPosts.get(i));
            if (count == 9)
                loadPaneWithButtonMorePostsIfPostsIsMoreThan10();
            ++count;
        }
    }

    private void addPost(Post post) {
        HBox hboxOnPostAndButton = new HBox();
        {
            VBox vbox = new VBox();
            for (int i = 0; i < reportsPost.size(); ++i) {
                if (reportsPost.get(i) == post.getID())
                    vbox.setStyle("-fx-border-style: solid;-fx-background-color: #F7CACA;");
                else
                    vbox.setStyle("-fx-border-style: solid;-fx-background-color: white;");
            }
            vbox.setMinWidth(400);
            vbox.setMaxWidth(400);
            vbox.setMinHeight(260);
            vbox.setMaxHeight(260);

            {
                HBox hboxForAuthor = new HBox();

                ImageView imageViewAuthor;
                try {
                    imageViewAuthor = new ImageView(new Image(Database.getAvatar(post.getID_Author())));
                } catch(NullPointerException e) {
                    imageViewAuthor = new ImageView(new Image("/images/service/person.png"));
                }
                imageViewAuthor.setFitWidth(40);
                imageViewAuthor.setFitHeight(40);
                imageViewAuthor.setCursor(Cursor.HAND);
                hboxForAuthor.getChildren().add(imageViewAuthor);

                JFXButton buttonAuthor = new JFXButton(Database.changeIDOnNameAndLastName(post.getID_Author()));
                buttonAuthor.setId("btnAuthor");
                buttonAuthor.setCursor(Cursor.HAND);
                buttonAuthor.setOnAction((ActionEvent e) -> {
                    if (post.getID_Author() == yourPerson.getID()) {
                        new LoadPage().loadMyProfile(yourPerson);
                    } else {
                        new LoadPage().loadProfile(Database.getPerson(post.getID_Author()), yourPerson);
                    }

                    popOverProfile.hide();
                });
                final Image avatarAuthor = imageViewAuthor.getImage();
                buttonAuthor.setOnMouseEntered((MouseEvent e) -> {
                    if (popOverProfile == null)
                        popOverProfile = new PopOver();

                    PopOverBuilder.showBalloonWithProfileForTimelineInPostOnProfilePersonOther(popOverProfile,
                            avatarAuthor, buttonAuthor.getText(), person, yourPerson);

                    popOverProfile.show(buttonAuthor);
                });
                buttonAuthor.setPadding(new Insets(1));
                HBox.setMargin(buttonAuthor, new Insets(4));
                hboxForAuthor.getChildren().add(buttonAuthor);

                if (post.isOnMyBoard()) {
                    ImageView imageViewArrow = new ImageView("images/service/arrowOnPostToRecipient.png");
                    HBox.setMargin(imageViewArrow, new Insets(5, 0, 0, 5));
                    hboxForAuthor.getChildren().add(imageViewArrow);

                    ImageView imageViewRecipient;
                    try {
                        imageViewRecipient = new ImageView(new Image(Database.getAvatar(post.getID_Recipient())));
                    } catch(NullPointerException e) {
                        imageViewRecipient = new ImageView(new Image("/images/service/person.png"));
                    }
                    imageViewRecipient.setFitWidth(40);
                    imageViewRecipient.setFitHeight(40);
                    HBox.setMargin(imageViewRecipient, new Insets(0, 0, 0, 5));
                    imageViewRecipient.setCursor(Cursor.HAND);
                    hboxForAuthor.getChildren().add(imageViewRecipient);

                    JFXButton buttonRecipient = new JFXButton(Database.changeIDOnNameAndLastName(post.getID_Recipient()));
                    buttonRecipient.setId("btnAuthor");
                    buttonRecipient.setCursor(Cursor.HAND);
                    buttonRecipient.setOnAction((ActionEvent e) -> {
                        if (post.getID_Author() == yourPerson.getID()) {
                            new LoadPage().loadMyProfile(yourPerson);
                        } else {
                            new LoadPage().loadProfile(Database.getPerson(post.getID_Recipient()), yourPerson);
                        }

                        popOverProfile.hide();
                    });
                    final Image avatarRecipient = imageViewRecipient.getImage();
                    buttonRecipient.setOnMouseEntered((MouseEvent e) -> {
                        if (popOverProfile == null)
                            popOverProfile = new PopOver();

                        PopOverBuilder.showBalloonWithProfileForTimelineInPostOnProfilePersonOther(popOverProfile,
                                avatarRecipient, buttonRecipient.getText(), person, yourPerson);

                        popOverProfile.show(buttonRecipient);
                    });
                    buttonRecipient.setPadding(new Insets(1));
                    HBox.setMargin(buttonRecipient, new Insets(4));
                    hboxForAuthor.getChildren().add(buttonRecipient);
                }

                vbox.getChildren().addAll(hboxForAuthor, new Separator());
            }

            {
                Label label = new Label(post.getText());
                label.setId("labelText");
                label.setMaxWidth(400);
                label.setMaxHeight(140);
                label.setWrapText(true);
                VBox.setMargin(label, new Insets(5, 10, 5, 10));
                vbox.getChildren().add(label);

                if (label.getText().length() > 299) {
                    Hyperlink btnShowMore = new Hyperlink("Show more...");
                    btnShowMore.setOnAction((ActionEvent e) ->
                            new LoadPage().loadShowMore(person, post)
                    );
                    VBox.setMargin(btnShowMore, new Insets(0, 0, 0, 320));
                    vbox.getChildren().add(btnShowMore);
                } else {
                    if (label.getText().length() < 50) {
                        label.setMaxHeight(69);
                        vbox.setMinHeight(50 + 69);
                        vbox.setMaxHeight(50 + 69);
                    } else if (label.getText().length() < 100) {
                        label.setMaxHeight(90);
                        vbox.setMinHeight(50 + 90);
                        vbox.setMaxHeight(50 + 90);
                    } else if (label.getText().length() < 150) {
                        label.setMaxHeight(111);
                        vbox.setMinHeight(50 + 111);
                        vbox.setMaxHeight(50 + 111);
                    } else if (label.getText().length() < 200) {
                        label.setMaxHeight(140);
                        vbox.setMinHeight(50 + 140);
                        vbox.setMaxHeight(50 + 140);
                    } else if (label.getText().length() < 250) {
                        label.setMaxHeight(153);
                        vbox.setMinHeight(50 + 153);
                        vbox.setMaxHeight(50 + 153);
                    } else if (label.getText().length() < 299) {
                        label.setMaxHeight(181);
                        vbox.setMinHeight(50 + 181);
                        vbox.setMaxHeight(50 + 181);
                    }
                }

                vbox.getChildren().add(new Separator());
            }

            {
                HBox hbox = new HBox();

                VBox vboxOnBtnLike = new VBox();
                JFXButton btnLike = new JFXButton("Like it!" + " (" + post.getNumberOfLikes() + ")",
                        new ImageView("images/service/like.png"));
                btnLike.setId("btnLike");
                btnLike.setCursor(Cursor.HAND);
                btnLike.setOnAction((ActionEvent e) -> {
                    btnLike.setDisable(true);
                    if (Database.likePost(post.getID(), person.getID())) {
                        post.setNumberOfLikes(Database.getNumberOfLikes(post.getID()));
                        btnLike.setText("Like it!" + " (" + post.getNumberOfLikes() + ")");
                        post.setWhoLikes(Database.getWhoLikes(post.getID()));
                        NotificationBuilder.showNotificationSuccessOperation("Like post",
                                "You would like this post!");
                    } else {
                        NotificationBuilder.showNotificationFailedOperation("Like post",
                                "Error. Please try again later.");
                    }
                    postsThatYouLike.put(post.getID(), true);
                });
                if (Validation.isTextOnList(String.valueOf(person.getID()), post.getWhoLikes())) {
                    btnLike.setDisable(true);
                    postsThatYouLike.put(post.getID(), true);
                } else {
                    postsThatYouLike.put(post.getID(), false);
                }
                btnLike.setPadding(new Insets(2));
                VBox.setMargin(btnLike, new Insets(1, 0, 0, 4));
                vboxOnBtnLike.getChildren().add(btnLike);
                hbox.getChildren().add(vboxOnBtnLike);

                JFXButton buttonThreeDots = new JFXButton("", new ImageView("images/service/threeDotsOnPost.png"));
                buttonThreeDots.setId("btnThreeDots");
                buttonThreeDots.setCursor(Cursor.HAND);
                buttonThreeDots.setOnAction((ActionEvent e) -> {
                    if (popOverForThreeDots == null) {
                        popOverForThreeDots = new PopOver();
                        popOverForReport = new PopOver();
                    }

                    PopOverBuilder.showMenuForThreeDotsForPost(popOverForThreeDots, popOverForReport, post.getID(),
                            postsThatYouLike.get(post.getID()), initBtnNotLikeIt(btnLike, post));

                    popOverForThreeDots.show(buttonThreeDots);
                });
                HBox.setMargin(buttonThreeDots, new Insets(4, 0, 0, 6));
                hbox.getChildren().add(buttonThreeDots);

                VBox vboxOnDate = new VBox();
                Label labelDate = new Label(post.getDate());
                VBox.setMargin(labelDate, new Insets(9, 0, 0, 130));
                vboxOnDate.getChildren().add(labelDate);
                hbox.getChildren().add(vboxOnDate);

                vbox.getChildren().add(hbox);
            }

            hboxOnPostAndButton.getChildren().add(vbox);
        }

        {
            JFXButton buttonPreview = new JFXButton("", new ImageView("images/eye.png"));
            buttonPreview.setId("btnPreview");
            buttonPreview.setCursor(Cursor.HAND);
            buttonPreview.setTooltip(new Tooltip("Preview"));
            buttonPreview.setOnAction((ActionEvent e) ->
                    new LoadPage().loadShowMore(person, post)
            );
            hboxOnPostAndButton.getChildren().add(buttonPreview);
        }

        VBox.setMargin(hboxOnPostAndButton, new Insets(0, 0, 20, 0));
        this.vbox.getChildren().add(hboxOnPostAndButton);
    }

    private JFXButton initBtnNotLikeIt(JFXButton btnLike, Post post) {
        JFXButton buttonNotLikeIt = new JFXButton("Not like it!");
        buttonNotLikeIt.setOnAction((ActionEvent e) -> {
            btnLike.setDisable(false);
            popOverForThreeDots.hide();
            postsThatYouLike.put(post.getID(), false);
            if (Database.unlikePost(post.getID(), person.getID())) {
                post.setNumberOfLikes(Database.getNumberOfLikes(post.getID()));
                btnLike.setText("Like it!" + " (" + post.getNumberOfLikes() + ")");
                post.setWhoLikes(Database.getWhoLikes(post.getID()));
                NotificationBuilder.showNotificationSuccessOperation("Unlike post",
                        "The removal of this post with your favorite has been completed successfully.");
            } else {
                NotificationBuilder.showNotificationFailedOperation("Unlike post",
                        "The removal of this post with your favorite has not been completed successfully.");
            }
        });
        buttonNotLikeIt.setOnMouseEntered((MouseEvent e) -> buttonNotLikeIt.setStyle("-fx-background-color: #E9E9E9;"));
        buttonNotLikeIt.setOnMouseExited((MouseEvent e) -> buttonNotLikeIt.setStyle("-fx-background-color: transparent;"));

        return buttonNotLikeIt;
    }

    private void loadPaneWithButtonMorePostsIfPostsIsMoreThan10() {
        VBox vbox = new VBox();
        vbox.setStyle("-fx-border-style: solid;");
        vbox.setMinWidth(400);
        vbox.setMaxWidth(400);
        vbox.setMinHeight(50);
        vbox.setMaxHeight(50);

        JFXButton button = new JFXButton("MORE POSTS...");
        button.setStyle("-fx-font-size: 17px;");
        button.setPadding(new Insets(10, 155, 7, 130));
        VBox.setMargin(button, new Insets(2));
        button.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            vbox.setVisible(false);

            List<Post> listNewPosts;

            listNewPosts = Database.getPosts(IDsYourPosts);

            int count = 0;
            for (int i = 0; i < listNewPosts.size(); ++i) {
                addPost(listNewPosts.get(i));
                if (count == 9)
                    loadPaneWithButtonMorePostsIfPostsIsMoreThan10();
                ++count;
            }

        });
        button.setOnMouseEntered((MouseEvent e) -> button.setStyle("-fx-font-size: 17px;-fx-background-color: #E9E9E9;"));
        button.setOnMouseExited((MouseEvent e) -> button.setStyle("-fx-font-size: 17px;-fx-background-color: transparent;"));


        vbox.getChildren().add(button);
        VBox.setMargin(vbox, new Insets(0, 0, 10, 0));
        this.vbox.getChildren().add(vbox);
    }


    private void uploadText(JFXTextArea textContents) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterTXT = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().addAll(extFilterTXT, extFilterPDF);

        File file = fileChooser.showOpenDialog(stage);

        String text;
        try {
            text = FileBuilder.loadTextToPost(file.getAbsolutePath());
        } catch (NullPointerException e) {
            return;
        }

        if (text != null) {
            textContents.setText(text);
            NotificationBuilder.showNotificationSuccessOperation("Load text with stage",
                    "The text was loaded successfully.");
        } else {
            NotificationBuilder.showNotificationFailedOperation("Load text with stage",
                    "The text was not loaded successfully.");
        }
    }


    public void setPersonOther(PersonOther person) {
        this.person = person;
    }

    public void setYourPerson(Person yourPerson) {
        this.yourPerson = yourPerson;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
