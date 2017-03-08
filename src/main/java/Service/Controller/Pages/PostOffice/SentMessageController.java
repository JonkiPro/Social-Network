package Service.Controller.Pages.PostOffice;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import Initialization.Initialization;
import Service.LoadPage.LoadPage;
import Service.Message.MessageSent;
import Service.Person.Person;
import Validation.Validation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;
import org.controlsfx.control.PopOver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jonatan on 2017-01-29.
 */
public class SentMessageController {

    @FXML
    private TreeTableView<MessageSent> treeTableView;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private Button btnThreeDots;
    @FXML
    private JFXTextField textSearch;
    @FXML
    private JFXComboBox<String> comboSearch;

    private Person person;

    // list to download messages from the database
    private ObservableList<TreeItem<MessageSent>> observableListMessagesWithDatabase;
    // list of sorted messages
    private ObservableList<TreeItem<MessageSent>> observableListMessages;
    // list to display messages
    private ObservableList<TreeItem<MessageSent>> observableListShowing;

    private Map<Integer, String> listMessagesToDelete = new HashMap<>();

    private PopOver popOverForButtonTreeDots;
    private JFXButton buttonOnPopOverInThreeDots;

    private int selectedOptionInSearch;

    @FXML
    public void initialize() {
        Initialization.initOptionsForFilterTableSentWorkingCopy(comboSearch);
    }

    public void createTableView() {
        clearColumns();
        createColumns();
        setRoot();
    }

    public void loadMessagesToObservableList() {
        observableListMessagesWithDatabase = FXCollections.observableArrayList(Database.getSentMessage(person.getID()));
        observableListShowing = FXCollections.observableArrayList(observableListMessagesWithDatabase);
    }

    private void setRoot() {
        MessageSent message = new MessageSent(1, "", "Topic", "Contents", true, "", "");

        TreeItem<MessageSent> itemRoot = new TreeItem<>(message);

        itemRoot.getChildren().addAll(observableListShowing);

        treeTableView.setRoot(itemRoot);
        treeTableView.setShowRoot(false);
    }

    private void createColumns() {
        TreeTableColumn<MessageSent, String> senderCol
                = new TreeTableColumn<>("Recipient");

        TreeTableColumn<MessageSent, String> topicCol
                = new TreeTableColumn<>("Topic");

        TreeTableColumn<MessageSent, String> contentsCol
                = new TreeTableColumn<>("Contents");

        TreeTableColumn<MessageSent, String> dateCol
                = new TreeTableColumn<>("Date");
        dateCol.setStyle("-fx-alignment: center;");

        TreeTableColumn<MessageSent, Boolean> deleteCol
                = new TreeTableColumn<MessageSent, Boolean>("Delete");

        senderCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("recipient"));
        topicCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("topic"));
        contentsCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("contents"));
        dateCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("postDate"));

        treeTableView.getColumns().addAll(senderCol, topicCol, contentsCol, dateCol, deleteCol);
        treeTableView.setEditable(true);
        treeTableView.setStyle("-fx-font-size: 15px;");

        deleteCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MessageSent, Boolean>, //
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TreeTableColumn.CellDataFeatures<MessageSent, Boolean> param) {
                TreeItem<MessageSent> treeItem = param.getValue();
                MessageSent emp = treeItem.getValue();

                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(emp.isDelete());

                booleanProp.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                         Boolean newValue) -> {
                    emp.setDelete(newValue);

                    if (newValue)
                        listMessagesToDelete.put(emp.getID(), emp.getStatus());
                    else
                        listMessagesToDelete.remove(emp.getID());

                    if (listMessagesToDelete.size() == 0)
                        btnDelete.setDisable(true);
                    else
                        btnDelete.setDisable(false);
                });
                return booleanProp;
            }
        });

        deleteCol.setCellFactory(new Callback<TreeTableColumn<MessageSent, Boolean>, TreeTableCell<MessageSent, Boolean>>() {
            @Override
            public TreeTableCell<MessageSent, Boolean> call(TreeTableColumn<MessageSent, Boolean> p) {
                CheckBoxTreeTableCell<MessageSent, Boolean> cell = new CheckBoxTreeTableCell<MessageSent, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        treeTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
                                Object newValue) {

                TreeItem<MessageSent> selectedItem = (TreeItem<MessageSent>) newValue;
                new LoadPage().loadSentMessageShowMessage(person, new MessageSent(selectedItem.getValue().getID(),
                        selectedItem.getValue().getRecipient(),
                        selectedItem.getValue().getTopic(),
                        selectedItem.getValue().getContents(),
                        selectedItem.getValue().getStatus(),
                        selectedItem.getValue().getPostDate()));
                // do what ever you want
            }

        });

        deleteCol.setSortable(false);
    }

    private void clearColumns() {
        treeTableView.getColumns().clear();
    }

    @FXML
    public void delete() {
        Set<Map.Entry<Integer, String>> listMessages = listMessagesToDelete.entrySet();

        if (Database.deleteMessage(listMessages)) {
            if (person.isNotifications()) {
                if (listMessages.size() == 1)
                    NotificationBuilder.showNotificationSuccessOperation("Delete message",
                            "The message was deleted successfully.");
                else if (listMessages.size() > 1)
                    NotificationBuilder.showNotificationSuccessOperation("Delete messages",
                            "The messages was deleted successfully.");
            }
        } else {
            if (person.isNotifications()) {
                if (listMessages.size() == 1)
                    NotificationBuilder.showNotificationFailedOperation("Delete message",
                            "The message was not deleted successfully.");
                else if (listMessages.size() > 1)
                    NotificationBuilder.showNotificationFailedOperation("Delete messages",
                            "The messages was not deleted successfully.");
            }
        }

        loadMessagesToObservableList();

        if (observableListShowing.size() == 0)
            btnDelete.setDisable(true);

        if (observableListMessages != null)
            filterSearch();
        else
            createTableView();
    }

    @FXML
    public void threeDotsShowMenu() {
        if (popOverForButtonTreeDots == null) {
            popOverForButtonTreeDots = new PopOver();
            buttonOnPopOverInThreeDots = new JFXButton("Delete all messages");
            buttonOnPopOverInThreeDots.setOnAction((ActionEvent e) -> {
                for (int i = 0; i < observableListMessagesWithDatabase.size(); ++i) {
                    listMessagesToDelete.put(observableListMessagesWithDatabase.get(i).getValue().getID(),
                            observableListMessagesWithDatabase.get(i).getValue().getStatus());
                }
                Set<Map.Entry<Integer, String>> listMessages = listMessagesToDelete.entrySet();

                if (Database.deleteMessage(listMessages)) {
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationSuccessOperation("Delete messages",
                                "The messages was deleted successfully.");
                } else {
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationFailedOperation("Delete messages",
                                "The messages was not deleted successfully.");
                }

                new LoadPage().loadSentMessage(person);
            });
        }

        PopOverBuilder.showMenuForButtonThreeDots(popOverForButtonTreeDots, buttonOnPopOverInThreeDots,
                btnThreeDots);
    }

    @FXML
    public void changeOptionSearch() {
        search();
    }

    @FXML
    public void search() {
        switch (comboSearch.getSelectionModel().getSelectedIndex()) {
            case 0: {
                selectedOptionInSearch = 0;
                filterBy("all");
                break;
            }
            case 1: {
                selectedOptionInSearch = 1;
                filterBy("recipient");
                break;
            }
            case 2: {
                selectedOptionInSearch = 2;
                filterBy("topic");
                break;
            }
            case 3: {
                selectedOptionInSearch = 3;
                filterBy("contents");
                break;
            }
        }

        createTableView();
    }

    @FXML
    public void keyReleasedInTextSearch() {
        search();
    }

    private void filterSearch() {
        switch (selectedOptionInSearch) {
            case 0: {
                filterBy("all");
                break;
            }
            case 1: {
                filterBy("recipient");
                break;
            }
            case 2: {
                filterBy("topic");
                break;
            }
            case 3: {
                filterBy("contents");
                break;
            }
        }

        createTableView();
    }

    public void filterBy(String filter) {
        observableListMessages = Validation.giveMeListSentMessagesWithGivenIndex(filter,
                textSearch.getText(),
                observableListMessagesWithDatabase);

        observableListShowing = FXCollections.observableArrayList(observableListMessages);
    }


    public void setPerson(Person person) {
        this.person = person;
    }
}

