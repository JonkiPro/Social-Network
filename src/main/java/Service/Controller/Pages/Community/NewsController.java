package Service.Controller.Pages.Community;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Service.Post.Post;
import Validation.Validation;
import com.jfoenix.controls.JFXButton;
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
import org.controlsfx.control.PopOver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jonatan on 2017-03-03.
 */
public class NewsController {

    @FXML
    private VBox vboxForPosts;

    private Person person;

    private Map<Integer, Boolean> postsThatYouLike = new HashMap<>();
    private List<Integer> IDsPosts = new ArrayList<>();
    private List<Integer> reportsPost = new ArrayList<>();

    private PopOver popOverProfile, popOverForThreeDots, popOverForReport;

    public void initPosts() {
        vboxForPosts.getChildren().clear();

        reportsPost = Database.getReportsPost();
        loadPosts();
    }

    private void loadPosts() {
        List<Post> listPosts;

        IDsPosts = Database.getNumberOfAllPosts();
        listPosts = Database.getPosts(IDsPosts);

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
                hboxForAuthor.getChildren().add(imageViewAuthor);

                JFXButton buttonAuthor = new JFXButton(Database.changeIDOnNameAndLastName(post.getID_Author()));
                buttonAuthor.setId("btnAuthor");
                buttonAuthor.setCursor(Cursor.HAND);
                buttonAuthor.setOnAction((ActionEvent e) -> {
                    if (person.getID() == post.getID_Author()) {
                        new LoadPage().loadMyProfile(person);
                    } else {
                        new LoadPage().loadProfile(Database.getPerson(post.getID_Author()), person);
                    }

                    popOverProfile.hide();
                });
                final Image avatarAuthor = imageViewAuthor.getImage();
                buttonAuthor.setOnMouseEntered((MouseEvent e) -> {
                    if (popOverProfile == null)
                        popOverProfile = new PopOver();

                    PopOverBuilder.showBalloonWithProfile(popOverProfile, post.getID_Author(),
                            avatarAuthor, buttonAuthor.getText(), person);

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
                    hboxForAuthor.getChildren().add(imageViewRecipient);

                    JFXButton buttonRecipient = new JFXButton(Database.changeIDOnNameAndLastName(post.getID_Recipient()));
                    buttonRecipient.setId("btnAuthor");
                    buttonRecipient.setCursor(Cursor.HAND);
                    buttonRecipient.setOnAction((ActionEvent e) -> {
                        if (person.getID() == post.getID_Recipient()) {
                            new LoadPage().loadMyProfile(person);
                        } else {
                            new LoadPage().loadProfile(Database.getPerson(post.getID_Recipient()), person);
                        }

                        popOverProfile.hide();
                    });
                    final Image avatarRecipient = imageViewRecipient.getImage();
                    buttonRecipient.setOnMouseEntered((MouseEvent e) -> {
                        if (popOverProfile == null)
                            popOverProfile = new PopOver();

                        PopOverBuilder.showBalloonWithProfile(popOverProfile, post.getID_Recipient(),
                                avatarRecipient, buttonRecipient.getText(), person);

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
        this.vboxForPosts.getChildren().add(hboxOnPostAndButton);
        this.vboxForPosts.setPadding(new Insets(0, 0, 0, 160));
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
                System.out.println(post.getWhoLikes());
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

            listNewPosts = Database.getPosts(IDsPosts);

            int count = 0;
            for (int i = 0; i < listNewPosts.size(); ++i) {
                addPost(listNewPosts.get(i));
                if (count == 9)
                    loadPaneWithButtonMorePostsIfPostsIsMoreThan10();
                ++count;
            }

        });
        button.setOnMouseEntered((MouseEvent e) ->
            button.setStyle("-fx-font-size: 17px;-fx-background-color: #E9E9E9;")
        );
        button.setOnMouseExited((MouseEvent e) ->
            button.setStyle("-fx-font-size: 17px;-fx-background-color: transparent;")
        );


        vbox.getChildren().add(button);
        VBox.setMargin(vbox, new Insets(0, 0, 10, 0));
        this.vboxForPosts.getChildren().add(vbox);
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
