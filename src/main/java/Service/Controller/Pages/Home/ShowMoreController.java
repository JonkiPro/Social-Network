package Service.Controller.Pages.Home;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import Service.Comment.Comment;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import com.jfoenix.controls.JFXSlider;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.PopOver;

import java.util.List;

/**
 * Created by Jonatan on 2017-02-25.
 */
public class ShowMoreController extends Stage {

    @FXML
    private TextArea textArea;
    @FXML
    private TextField textFieldComment;
    @FXML
    private VBox vbox;
    @FXML
    private JFXSlider sliderSize;

    private Person person;

    private double oldPositionStageX, oldPositionStageY;

    private int IDPost;

    private PopOver popOverMoreComment;

    @FXML
    public void initialize() {
        initStyle(StageStyle.UNDECORATED);
        initModality(Modality.APPLICATION_MODAL);

        initReactionOnSlider();
    }

    @FXML
    public void exit() {
        this.close();
    }

    @FXML
    public void keyEnterOnTextFieldComment(KeyEvent e) {
        if (textFieldComment.getText().length() > 0) {
            if (e.getCode() == KeyCode.ENTER) {
                if (Database.addComment(person.getID(), IDPost, textFieldComment.getText())) {
                    NotificationBuilder.showNotificationSuccessOperation("Add comment",
                            "Your comment has been added.");
                    textFieldComment.setText("");

                    loadComment();
                } else if (Database.addComment(person.getID(), IDPost, textFieldComment.getText())) {
                    NotificationBuilder.showNotificationFailedOperation("Add comment",
                            "Your comment has not been added.");
                }
            }
        }
    }

    public void loadComment() {
        vbox.getChildren().clear();

        List<Comment> listComments = Database.getComments(IDPost);

        for (int i = 0; i < listComments.size(); ++i) {
            addComment(listComments.get(i).getID_Author(), listComments.get(i).getContents(),
                    listComments.get(i).getDate());
        }
    }

    private void addComment(int ID_Autor, String contents, String date) {
        VBox vbox = new VBox();
        VBox.setMargin(vbox, new Insets(0, 0, 0, 5));

        Hyperlink hyperlinkNameAndLastName = new Hyperlink(Database.changeIDOnNameAndLastName(ID_Autor));
        hyperlinkNameAndLastName.setOnAction((ActionEvent e) -> {
            if (person.getID() == ID_Autor) {
                new LoadPage().loadMyProfile(person);
                close();
            } else {
                new LoadPage().loadProfile(Database.getPerson(ID_Autor), person);
                close();
            }
        });
        Label labelDate = new Label("Date: " + date);
        Label labelContents = new Label(contents);
        labelContents.setOnMouseEntered((MouseEvent e) -> {
            if (popOverMoreComment == null)
                popOverMoreComment = new PopOver();

            PopOverBuilder.showMoreForComment(popOverMoreComment, labelContents.getText());

            popOverMoreComment.show(labelContents);
        });
        labelContents.setMaxWidth(400);
        labelContents.setWrapText(true);

        vbox.getChildren().addAll(hyperlinkNameAndLastName, labelDate, labelContents, new Separator());
        this.vbox.getChildren().add(vbox);
    }

    private void initReactionOnSlider() {
        sliderSize.setValue(15);
        textArea.setStyle("-fx-font-size: 15px;");
        sliderSize.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) ->
                textArea.setStyle("-fx-font-size:" + sliderSize.getValue() + "px;")
        );
    }


    @FXML
    public void mousePressed(MouseEvent e) {
        oldPositionStageX = e.getX();
        oldPositionStageY = e.getY();
    }

    @FXML
    public void mouseDragged(MouseEvent e) {
        double newPositionStageX = e.getScreenX();
        double newPositionStageY = e.getScreenY();

        this.setX(newPositionStageX - oldPositionStageX);
        this.setY(newPositionStageY - oldPositionStageY);
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public void setIDPost(int IDPost) {
        this.IDPost = IDPost;
    }
}
