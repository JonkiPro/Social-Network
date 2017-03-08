package Service.Controller.Pages.PostOffice;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Database.Database;
import Initialization.Initialization;
import Service.LoadPage.LoadPage;
import Service.Message.MessageTrash;
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

import java.util.*;

/**
 * Created by Jonatan on 2017-01-29.
 */
public class TrashController {

    @FXML
    private TreeTableView<MessageTrash> treeTableView;
    @FXML
    private JFXButton btnDelete, btnRestore;
    @FXML
    private Button btnThreeDots;
    @FXML
    private JFXTextField textSearch;
    @FXML
    private JFXComboBox<String> comboSearch;

    private Person person;

    // list to download messages from the database
    private ObservableList<TreeItem<MessageTrash>> observableListMessagesWithDatabase;
    // list of sorted messages
    private ObservableList<TreeItem<MessageTrash>> observableListMessages;
    // list to display messages
    private ObservableList<TreeItem<MessageTrash>> observableListShowing;

    private List<CheckBoxTreeTableCell> listCheckBox = new ArrayList<>();
    private List<Integer> listMessagesToDelete = new ArrayList<>();
    private Map<Integer, String> listMessagesToRestore = new HashMap<>();

    private PopOver popOverForButtonTreeDots;
    private JFXButton buttonOnPopOverInThreeDots;

    private int selectedOptionInSearch;

    @FXML
    public void initialize() {
        Initialization.initOptionsForFilterTableTrash(comboSearch);
    }

    public void createTableView() {
        clearColumns();
        createColumns();
        setRoot();
    }

    public void loadMessagesToObservableList() {
        observableListMessagesWithDatabase = FXCollections.observableArrayList(Database.getMessageWithTrash(person.getID()));
        observableListShowing = FXCollections.observableArrayList(observableListMessagesWithDatabase);
    }

    private void setRoot() {
        MessageTrash message = new MessageTrash(1, "", "Topic", "Contents", true, "", "", "");

        TreeItem<MessageTrash> itemRoot = new TreeItem<>(message);

        itemRoot.getChildren().addAll(observableListShowing);

        treeTableView.setRoot(itemRoot);
        treeTableView.setShowRoot(false);
    }

    private void createColumns() {
        TreeTableColumn<MessageTrash, String> senderCol
                = new TreeTableColumn<>("Sender/Recipient");

        TreeTableColumn<MessageTrash, String> topicCol
                = new TreeTableColumn<>("Topic");

        TreeTableColumn<MessageTrash, String> contentsCol
                = new TreeTableColumn<>("Contents");

        TreeTableColumn<MessageTrash, String> previousStatusCol
                = new TreeTableColumn<>("Status");
        previousStatusCol.setStyle("-fx-alignment: center;");

        TreeTableColumn<MessageTrash, Boolean> deleteCol
                = new TreeTableColumn<>("Delete");

        senderCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("senderOrRecipient"));
        topicCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("topic"));
        contentsCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("contents"));
        previousStatusCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("previousStatus"));

        treeTableView.getColumns().addAll(senderCol, topicCol, contentsCol, previousStatusCol, deleteCol);
        treeTableView.setEditable(true);
        treeTableView.setStyle("-fx-font-size: 15px;");

        deleteCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MessageTrash, Boolean>, //
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TreeTableColumn.CellDataFeatures<MessageTrash, Boolean> param) {
                TreeItem<MessageTrash> treeItem = param.getValue();
                MessageTrash emp = treeItem.getValue();

                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(emp.isDelete());

                booleanProp.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                         Boolean newValue) -> {
                    emp.setDelete(newValue);

                    if (newValue) {
                        listMessagesToDelete.add(emp.getID());
                        listMessagesToRestore.put(emp.getID(), emp.getPreviousStatus());
                    } else {
                        listMessagesToDelete.remove((Object) emp.getID());
                        listMessagesToRestore.remove(emp.getID());
                    }

                    if (listMessagesToDelete.size() == 0) {
                        btnDelete.setDisable(true);
                        btnRestore.setDisable(true);
                    } else {
                        btnDelete.setDisable(false);
                        btnRestore.setDisable(false);
                    }
                });
                return booleanProp;
            }
        });

        deleteCol.setCellFactory(new Callback<TreeTableColumn<MessageTrash, Boolean>, TreeTableCell<MessageTrash, Boolean>>() {
            @Override
            public TreeTableCell<MessageTrash, Boolean> call(TreeTableColumn<MessageTrash, Boolean> p) {
                CheckBoxTreeTableCell<MessageTrash, Boolean> cell = new CheckBoxTreeTableCell<MessageTrash, Boolean>();
                listCheckBox.add(cell);
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        treeTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
                                Object newValue) {

                TreeItem<MessageTrash> selectedItem = (TreeItem<MessageTrash>) newValue;
                if (selectedItem.getValue().getPreviousStatus().equals("read")
                        || selectedItem.getValue().getPreviousStatus().equals("received")) {
                    new LoadPage().loadTrashShowMessage(person, new MessageTrash(selectedItem.getValue().getID(),
                            selectedItem.getValue().getSenderOrRecipient(),
                            selectedItem.getValue().getTopic(),
                            selectedItem.getValue().getContents(),
                            selectedItem.getValue().getPreviousStatus(),
                            selectedItem.getValue().getDate(),
                            selectedItem.getValue().getDateRemoved()));
                } else if (selectedItem.getValue().getPreviousStatus().equals("saved")
                        || selectedItem.getValue().getPreviousStatus().equals("sent")) {
                    new LoadPage().loadTrashShowMyMessage(person, new MessageTrash(selectedItem.getValue().getID(),
                            selectedItem.getValue().getSenderOrRecipient(),
                            selectedItem.getValue().getTopic(),
                            selectedItem.getValue().getContents(),
                            selectedItem.getValue().getPreviousStatus(),
                            selectedItem.getValue().getDate(),
                            selectedItem.getValue().getDateRemoved()));
                }
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
        if (Database.constDeleteMessage(listMessagesToDelete)) {
            if (person.isNotifications()) {
                if (listMessagesToDelete.size() == 1)
                    NotificationBuilder.showNotificationSuccessOperation("Delete message",
                            "The message was deleted successfully.");
                else if (listMessagesToDelete.size() > 1)
                    NotificationBuilder.showNotificationSuccessOperation("Delete messages",
                            "The messages was deleted successfully.");
            }
        } else {
            if (person.isNotifications()) {
                if (listMessagesToDelete.size() == 1)
                    NotificationBuilder.showNotificationFailedOperation("Delete message",
                            "The message was not deleted successfully.");
                else if (listMessagesToDelete.size() > 1)
                    NotificationBuilder.showNotificationFailedOperation("Delete messages",
                            "The messages was not deleted successfully.");
            }
        }

        loadMessagesToObservableList();

        if (observableListShowing.size() == 0) {
            btnDelete.setDisable(true);
            btnRestore.setDisable(true);
        }

        if (observableListMessages != null)
            filterSearch();
        else
            createTableView();
    }

    @FXML
    public void restore() {
        Set<Map.Entry<Integer, String>> listMessages = listMessagesToRestore.entrySet();
        for (Map.Entry<Integer, String> message : listMessages) {
            if (message.getValue().equals("received"))
                message.setValue("sent");
        }

        if (Database.restoreMessage(listMessages)) {
            if (person.isNotifications()) {
                if (listMessages.size() == 1)
                    NotificationBuilder.showNotificationSuccessOperation("Restore message",
                            "The message was restored successfully.");
                else if (listMessages.size() > 1)
                    NotificationBuilder.showNotificationSuccessOperation("Restore messages",
                            "The messages was restored successfully .");
            }
        } else {
            if (person.isNotifications()) {
                if (listMessages.size() == 1)
                    NotificationBuilder.showNotificationFailedOperation("Restore message",
                            "The message was not restored successfully.");
                else if (listMessages.size() > 1)
                    NotificationBuilder.showNotificationFailedOperation("Restore messages",
                            "The messages was not restored successfully.");
            }
        }

        loadMessagesToObservableList();

        if (observableListShowing.size() == 0) {
            btnDelete.setDisable(true);
            btnRestore.setDisable(true);
        }

        if (observableListMessages != null)
            filterSearch();
        else
            createTableView();
    }

    @FXML
    public void threeDotsShowMenu() {
        if (popOverForButtonTreeDots == null) {
            popOverForButtonTreeDots = new PopOver();
            buttonOnPopOverInThreeDots = new JFXButton("Empty trash");
            buttonOnPopOverInThreeDots.setOnAction((ActionEvent e) -> {
                List<Integer> listID = new ArrayList<>();

                for (int i = 0; i < observableListMessagesWithDatabase.size(); ++i) {
                    listID.add(observableListMessagesWithDatabase.get(i).getValue().getID());
                }

                if (Database.constDeleteMessage(listID)) {
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationSuccessOperation("Delete messages",
                                "The messages was deleted successfully.");
                } else {
                    if (person.isNotifications())
                        NotificationBuilder.showNotificationFailedOperation("Delete messages",
                                "The messages was not deleted successfully.");
                }

                new LoadPage().loadTrash(person);
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
                filterBy("sender/recipient");
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
                filterBy("sender/recipient");
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
        observableListMessages = Validation.giveMeListTrashMessagesWithGivenIndex(filter,
                textSearch.getText(),
                observableListMessagesWithDatabase);

        observableListShowing = FXCollections.observableArrayList(observableListMessages);
    }


    public void setPerson(Person person) {
        this.person = person;
    }
}
