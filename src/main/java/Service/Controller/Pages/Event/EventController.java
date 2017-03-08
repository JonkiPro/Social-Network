package Service.Controller.Pages.Event;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import Service.Event.Event;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import com.jfoenix.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonatan on 2017-03-03.
 */
public class EventController {

    @FXML
    private JFXTextField textTitle;
    @FXML
    private JFXDatePicker datePickerStart;
    @FXML
    private JFXDatePicker datePickerTimeStart;
    @FXML
    private JFXDatePicker datePickerFinish;
    @FXML
    private JFXDatePicker datePickerTimeFinish;
    @FXML
    private JFXTextField textLocalization;
    @FXML
    private JFXTextArea textDescription;
    @FXML
    private VBox vboxForEvents;
    @FXML
    private JFXCheckBox checkBoxAll, checkBoxOnlyMeAndMyFriends;

    private Person person;

    private List<Integer> IDsYourPosts = new ArrayList<>();

    @FXML
    void add() {
        String timeStart, dateFinish, timeFinish;
        try {
            timeStart = datePickerTimeStart.getTime().toString();
        } catch (NullPointerException e) {
            timeStart = "";
        }
        try {
            dateFinish = datePickerFinish.getValue().toString();
        } catch (NullPointerException e) {
            dateFinish = "";
        }
        try {
            timeFinish = datePickerTimeFinish.getTime().toString();
        } catch (NullPointerException e) {
            timeFinish = "";
        }

        if (textTitle.getText().length() > 0) {
            try {
                if (datePickerStart.getValue().toString().length() > 0) {
                    if (textLocalization.getText().length() > 0) {
                        if (textDescription.getText().length() > 0) {
                            if (Database.addEvent(person.getID(),
                                    textTitle.getText(),
                                    datePickerStart.getValue().toString(),
                                    timeStart,
                                    dateFinish,
                                    timeFinish,
                                    textLocalization.getText(),
                                    textDescription.getText())) {

                                Database.addLogger(person.getID(), "You have added a new event.");

                                if (person.isNotifications()) {
                                    NotificationBuilder.showNotificationSuccessOperation("Add event",
                                            "The event was added successfully!");
                                }

                                new LoadPage().loadEvent(person);
                            } else {
                                if (person.isNotifications()) {
                                    NotificationBuilder.showNotificationFailedOperation("Add event",
                                            "The event was not added successfully!");
                                }
                            }
                        } else {
                            PopOverBuilder.showStatementValidation(textDescription, "Please enter some text...");
                        }
                    } else {
                        PopOverBuilder.showStatementValidation(textLocalization, "Please enter some text...");
                    }
                }
            } catch (NullPointerException e) {
                PopOverBuilder.showStatementValidation(datePickerStart, "Please enter date...");
            }
        } else {
            PopOverBuilder.showStatementValidation(textTitle, "Please enter some text...");
        }
    }

    public void initIDsEvents() {
        IDsYourPosts = Database.getNumberOfAllEvents();
    }

    public void initEvents() {
        vboxForEvents.getChildren().clear();

        loadEvents();
    }

    private void loadEvents() {
        List<Event> listEvents;

        listEvents = Database.getEvents(IDsYourPosts);

        int count = 0;
        for (int i = 0; i < listEvents.size(); ++i) {
            addEventToVBox(listEvents.get(i));
            if (count == 9)
                loadPaneWithButtonMorePostsIfPostsIsMoreThan10();
            ++count;
        }
    }

    private void addEventToVBox(Event event) {
        VBox vboxEvent = new VBox();
        vboxEvent.setMinWidth(400);

        {
            Label labelTitle = new Label("Title:  " + event.getTitle());
            labelTitle.setStyle("-fx-font-size: 24px;");
            VBox.setMargin(labelTitle, new Insets(0, 0, 0, 5));

            vboxEvent.getChildren().add(labelTitle);
        }
        {
            Label labelLocalization = new Label("Localization:  " + event.getLocalization());
            labelLocalization.setStyle("-fx-font-size: 17px;");
            VBox.setMargin(labelLocalization, new Insets(0, 0, 0, 5));

            vboxEvent.getChildren().add(labelLocalization);
        }
        {
            TextArea textDescription = new TextArea(event.getDescription());
            textDescription.setEditable(false);
            textDescription.setWrapText(true);
            textDescription.setMinHeight(100);
            VBox.setMargin(textDescription, new Insets(5, 5, 0, 5));

            vboxEvent.getChildren().add(textDescription);
        }
        {
            HBox hbox = new HBox();

            Label labelDateStart = new Label("Start:  " + event.getDateStart());
            labelDateStart.setStyle("-fx-font-size: 13px;");
            HBox.setMargin(labelDateStart, new Insets(5, 0, 0, 5));
            Label labelTimeStart = new Label("  " + event.getTimeStart());
            labelTimeStart.setStyle("-fx-font-size: 13px;");
            HBox.setMargin(labelTimeStart, new Insets(5, 0, 0, 5));

            hbox.getChildren().addAll(labelDateStart, labelTimeStart);
            vboxEvent.getChildren().add(hbox);
        }
        {
            HBox hbox = new HBox();

            Label labelDateFinish = new Label("Finish:  " + event.getDateFinish());
            labelDateFinish.setStyle("-fx-font-size: 13px;");
            HBox.setMargin(labelDateFinish, new Insets(0, 0, 0, 5));
            Label labelTimeFinish = new Label("  " + event.getTimeFinish());
            labelTimeFinish.setStyle("-fx-font-size: 13px;");
            HBox.setMargin(labelTimeFinish, new Insets(0, 0, 0, 5));

            hbox.getChildren().addAll(labelDateFinish, labelTimeFinish);
            vboxEvent.getChildren().add(hbox);
        }
        {
            HBox hboxRate = new HBox();
            {
                Rating ratingEvent = new Rating();
                StringProperty myRate = new SimpleStringProperty();
                StringProperty rateEventProperty = new SimpleStringProperty();

                VBox vboxForMyRate = new VBox();
                {
                    Label labelEventRate = new Label();
                    HBox hbox = new HBox();
                    {
                        Label labelYourRate = new Label("Your rate:");
                        labelYourRate.setStyle("-fx-font-size: 13px;");
                        HBox.setMargin(labelYourRate, new Insets(0, 0, 0, 60));

                        try {
                            myRate.set(String.valueOf(Float.valueOf(Database.getMyRateForEvent(event.getID(), person.getID())).toString().substring(0, String.valueOf(Database.getRateOfEvent(event.getID())).indexOf(".") + 3)));
                        } catch (StringIndexOutOfBoundsException e) {
                            myRate.set(String.valueOf(Database.getMyRateForEvent(event.getID(), person.getID())));
                        }
                        labelEventRate.textProperty().bindBidirectional(myRate);

                        labelEventRate.setStyle("-fx-font-size: 13px;");
                        HBox.setMargin(labelEventRate, new Insets(0, 0, 0, 5));

                        hbox.getChildren().addAll(labelYourRate, labelEventRate);
                    }
                    Rating rating = new Rating();
                    rating.setRating(Database.getMyRateForEvent(event.getID(), person.getID()));
                    rating.setUpdateOnHover(true);
                    rating.setPartialRating(true);
                    VBox.setMargin(rating, new Insets(0, 0, 0, 5));
                    rating.setOnMouseClicked((MouseEvent e) -> {
                        if (Database.addRateOnEvent(event.getID(), person.getID(), (float) rating.getRating())) {
                            NotificationBuilder.showNotificationSuccessOperation("Add rate",
                                    "Yor rate has been added!");

                            ratingEvent.setRating(Database.getRateOfEvent(event.getID()));

                            try {
                                myRate.set(String.valueOf(Float.valueOf(Database.getMyRateForEvent(event.getID(), person.getID())).toString().substring(0, String.valueOf(Database.getRateOfEvent(event.getID())).indexOf(".") + 3)));
                                rateEventProperty.set(String.valueOf(Float.valueOf(Database.getRateOfEvent(event.getID())).toString().substring(0, String.valueOf(Database.getRateOfEvent(event.getID())).indexOf(".") + 3)));
                            } catch (StringIndexOutOfBoundsException e2) {
                                myRate.set(String.valueOf(Database.getMyRateForEvent(event.getID(), person.getID())));
                                rateEventProperty.set(String.valueOf(Database.getRateOfEvent(event.getID())));
                            }
                        } else {
                            NotificationBuilder.showNotificationFailedOperation("Add rate",
                                    "Yor rate has been not added!");
                        }
                    });

                    vboxForMyRate.getChildren().addAll(hbox, rating);
                }
                VBox vboxYorRate = new VBox();
                {
                    Label labelEventRate = new Label();
                    HBox hbox = new HBox();
                    {
                        Label labelRate = new Label("Rate:");
                        labelRate.setStyle("-fx-font-size: 13px;");
                        HBox.setMargin(labelRate, new Insets(0, 0, 0, 100));

                        try {
                            rateEventProperty.set(String.valueOf(Float.valueOf(Database.getRateOfEvent(event.getID())).toString().substring(0, String.valueOf(Database.getRateOfEvent(event.getID())).indexOf(".") + 3)));
                        } catch (StringIndexOutOfBoundsException e) {
                            rateEventProperty.set(String.valueOf(Database.getRateOfEvent(event.getID())));
                        }
                        labelEventRate.textProperty().bindBidirectional(rateEventProperty);

                        labelEventRate.setStyle("-fx-font-size: 13px;");
                        HBox.setMargin(labelEventRate, new Insets(0, 0, 0, 5));

                        hbox.getChildren().addAll(labelRate, labelEventRate);
                    }
                    ratingEvent.setRating(Database.getRateOfEvent(event.getID()));
                    ratingEvent.setPartialRating(true);
                    ratingEvent.setDisable(true);
                    VBox.setMargin(ratingEvent, new Insets(0, 0, 0, 37));

                    vboxYorRate.getChildren().addAll(hbox, ratingEvent);
                }

                VBox.setMargin(hboxRate, new Insets(15, 0, 10, 0));
                hboxRate.getChildren().addAll(vboxForMyRate, vboxYorRate);
            }

            vboxEvent.getChildren().add(hboxRate);
        }

        vboxEvent.setStyle("-fx-border-style: solid;-fx-background-color: white;");
        VBox.setMargin(vboxEvent, new Insets(0, 0, 20, 50));
        vboxForEvents.getChildren().add(vboxEvent);
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

            List<Event> listNewEvents;

            listNewEvents = Database.getEvents(IDsYourPosts);

            int count = 0;
            for (int i = 0; i < listNewEvents.size(); ++i) {
                addEventToVBox(listNewEvents.get(i));
                if (count == 9)
                    loadPaneWithButtonMorePostsIfPostsIsMoreThan10();
                ++count;
            }

        });
        button.setOnMouseEntered((MouseEvent e) -> button.setStyle("-fx-font-size: 17px;-fx-background-color: #E9E9E9;"));
        button.setOnMouseExited((MouseEvent e) -> button.setStyle("-fx-font-size: 17px;-fx-background-color: transparent;"));


        vbox.getChildren().add(button);
        VBox.setMargin(vbox, new Insets(0, 0, 10, 50));
        this.vboxForEvents.getChildren().add(vbox);
    }

    @FXML
    public void checkBoxAll() {
        IDsYourPosts = Database.getNumberOfAllEvents();

        checkBoxAll.setDisable(true);
        checkBoxOnlyMeAndMyFriends.setDisable(false);
        checkBoxOnlyMeAndMyFriends.setSelected(false);

        initEvents();
    }

    @FXML
    public void checkBoxOnlyMeAndMyFriends() {
        IDsYourPosts = Database.getNumberOfEventsMyAndMyFriends(person.getID(), person.getFriends());

        checkBoxOnlyMeAndMyFriends.setDisable(true);
        checkBoxAll.setDisable(false);
        checkBoxAll.setSelected(false);

        initEvents();
    }


    public void setPerson(Person person) {
        this.person = person;
    }
}
