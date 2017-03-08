package Service.Controller.Pages.Notes;

import Builder.NotificationBuilder;
import Builder.PopOverBuilder;
import Builder.PopupBuilder;
import Database.Database;
import FileBuilder.FileBuilder;
import Service.LoadPage.LoadPage;
import Service.Notes.Notes;
import Service.Person.Person;
import Validation.Validation;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonatan on 2017-03-01.
 */
public class NotesController {

    @FXML
    private TreeTableView treeTableView;
    @FXML
    private JFXTextField textTitle, textSearchByWord;
    @FXML
    private JFXTextArea textContents;
    @FXML
    private JFXTextField textSearch;
    @FXML
    private JFXButton btnSave, btnDelete, btnSaveOnStage;
    @FXML
    private Button btnThreeDots;
    @FXML
    private Label labelDate;
    @FXML
    private JFXSlider sliderFontSize;
    @FXML
    private TextField textFontSize;

    private Person person;
    private Stage stage;

    // list to download notes from the database
    private ObservableList<TreeItem<Notes>> observableListNotesWithDatabase;
    // list of sorted notes
    private ObservableList<TreeItem<Notes>> observableListNotes;
    // list to display notes
    private ObservableList<TreeItem<Notes>> observableListShowing;

    private PopOver popOverThreeDots;
    private JFXButton btnClear;
    private JFXPopup popupMenu;
    private TreeItem<Notes> notes;

    public void createTableView() {
        clearColumns();
        createColumns();
        setRoot();
    }

    public void loadMessagesToObservableList() {
        observableListNotesWithDatabase = FXCollections.observableArrayList(Database.getNotes(person.getID()));
        observableListShowing = FXCollections.observableArrayList(observableListNotesWithDatabase);
    }

    private void setRoot() {
        Notes notes = new Notes(1, 1, "Topic", "Contents", "Date");

        TreeItem<Notes> itemRoot = new TreeItem<Notes>(notes);

        itemRoot.getChildren().addAll(observableListShowing);

        treeTableView.setRoot(itemRoot);
        treeTableView.setShowRoot(false);
    }

    private void createColumns() {
        TreeTableColumn<Notes, String> titleCol
                = new TreeTableColumn<Notes, String>("Title");

        titleCol.setStyle("-fx-alignment: center;");

        titleCol.setCellValueFactory(new TreeItemPropertyValueFactory<Notes, String>("title"));

        treeTableView.getColumns().add(titleCol);
        treeTableView.setStyle("-fx-font-size: 15px;");

        treeTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
                                Object newValue) {

                TreeItem<Notes> selectedItem = (TreeItem<Notes>) newValue;
                try {
                    textTitle.setText(selectedItem.getValue().getTitle());
                    textContents.setText(selectedItem.getValue().getContents());
                    labelDate.setText(selectedItem.getValue().getDate());
                } catch (NullPointerException e) { /* empty */ }

                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                btnSaveOnStage.setDisable(false);
            }

        });
    }

    private void clearColumns() {
        treeTableView.getColumns().clear();
    }

    @FXML
    public void keyReleasedInTextSearch() {
        search();

        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        btnSaveOnStage.setDisable(true);
    }

    @FXML
    public void search() {
        observableListNotes = Validation.giveMeListNotesWithGivenIndex(textSearch.getText(),
                observableListNotesWithDatabase);

        observableListShowing = FXCollections.observableArrayList(observableListNotes);

        createTableView();
    }

    @FXML
    public void save() {
        if (textTitle.getText().length() > 0) {
            if (textContents.getText().length() > 0) {
                TreeItem<Notes> notes = (TreeItem) treeTableView.getSelectionModel().getSelectedItem();

                if (Database.updateNotes(notes.getValue().getID(), textTitle.getText(), textContents.getText())) {
                    NotificationBuilder.showNotificationSuccessOperation("Update notes",
                            "The notes was updated successfully.");
                    new LoadPage().loadNotes(person);
                } else {
                    NotificationBuilder.showNotificationFailedOperation("Update notes",
                            "The notes was not updated successfully.");
                }
            } else {
                PopOverBuilder.showStatementValidation(textContents, "Please enter some text...");
            }
        } else {
            PopOverBuilder.showStatementValidation(textTitle, "Please enter some text...");
        }
    }

    @FXML
    public void addNew() {
        if (textTitle.getText().length() > 0) {
            if (textContents.getText().length() > 0) {
                if (Database.addNotes(person.getID(), textTitle.getText(), textContents.getText())) {
                    NotificationBuilder.showNotificationSuccessOperation("Add new notes",
                            "The notes was added successfully.");
                    new LoadPage().loadNotes(person);
                } else {
                    NotificationBuilder.showNotificationFailedOperation("Add new notes",
                            "The notes was not added successfully.");
                }
            } else {
                PopOverBuilder.showStatementValidation(textContents, "Please enter some text...");
            }
        } else {
            PopOverBuilder.showStatementValidation(textTitle, "Please enter some text...");
        }
    }

    @FXML
    public void delete() {
        TreeItem<Notes> notes = (TreeItem) treeTableView.getSelectionModel().getSelectedItem();

        if (Database.deleteNotes(notes.getValue().getID())) {
            NotificationBuilder.showNotificationSuccessOperation("Delete notes",
                    "The notes was deleted successfully.");
            new LoadPage().loadNotes(person);
        } else {
            NotificationBuilder.showNotificationFailedOperation("Delete notes",
                    "The notes was not deleted successfully.");
        }
    }

    @FXML
    public void searchByWord() {
        if (textContents.getText().indexOf(textSearchByWord.getText()) != -1) {
            textContents.selectRange(textContents.getText().indexOf(textSearchByWord.getText()),
                    (textContents.getText().indexOf(textSearchByWord.getText()) + textSearchByWord.getLength()));
        }
    }

    @FXML
    public void threeDotsShowMenu() {
        if (popOverThreeDots == null) {
            popOverThreeDots = new PopOver();

            btnClear = new JFXButton("Clear list");
            btnClear.setOnAction((ActionEvent e) -> {
                List<Integer> listIDsNotes = new ArrayList<>();
                for (TreeItem<Notes> notes : observableListNotesWithDatabase) {
                    Integer integer = notes.getValue().getID();
                    listIDsNotes.add(integer);
                }
                if (Database.clearListNotes(listIDsNotes)) {
                    NotificationBuilder.showNotificationSuccessOperation("Clear list",
                            "The list notes was cleared successfully.");
                    new LoadPage().loadNotes(person);
                } else {
                    NotificationBuilder.showNotificationFailedOperation("Clear list",
                            "The list notes was not cleared successfully.");
                }
            });
        }

        PopOverBuilder.showMenuForButtonThreeDots(popOverThreeDots, btnClear, btnThreeDots);
    }

    @FXML
    public void rightClickOnTreeTableView(MouseEvent e) {
        notes = (TreeItem) treeTableView.getSelectionModel().getSelectedItem();

        if (popupMenu == null) {
            popupMenu = new JFXPopup();

            JFXButton btnDeleteOnPopupMenu = initBtnDeleteOnPopupMenu();
            JFXButton btnSaveOnComputerOnPopupMenu = initBtnSaveOnComputerOnPopupMenu();

            List<JFXButton> listButtons = new ArrayList<>();
            listButtons.add(btnDeleteOnPopupMenu);
            listButtons.add(btnSaveOnComputerOnPopupMenu);

            PopupBuilder.initPopupMenu(popupMenu, treeTableView, listButtons);
        }

        if (e.getButton() == MouseButton.SECONDARY && notes != null) {
            popupMenu.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, e.getX(), e.getY());
        }
    }

    private JFXButton initBtnDeleteOnPopupMenu() {
        JFXButton btnDelete = new JFXButton("Delete");
        btnDelete.setStyle("-fx-background-color: white;");
        btnDelete.setOnAction((ActionEvent e) -> {
            popupMenu.close();

            if (Database.deleteNotes(notes.getValue().getID())) {
                NotificationBuilder.showNotificationSuccessOperation("Delete notes",
                        "The notes was deleted successfully.");
                new LoadPage().loadNotes(person);
            } else {
                NotificationBuilder.showNotificationFailedOperation("Delete notes",
                        "The notes was not deleted successfully.");
            }
        });

        return btnDelete;
    }

    private JFXButton initBtnSaveOnComputerOnPopupMenu() {
        JFXButton btnSaveOnComputerOnPopupMenu = new JFXButton("Save notes on computer");
        btnSaveOnComputerOnPopupMenu.setStyle("-fx-background-color: white;");
        btnSaveOnComputerOnPopupMenu.setOnAction((ActionEvent e) -> {
            popupMenu.close();

            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(stage);

            try {
                if (file.createNewFile()) {
                    if (FileBuilder.createFileNotes(file.getAbsolutePath(), notes.getValue().getTitle(),
                            notes.getValue().getContents(), notes.getValue().getDate())) {
                        NotificationBuilder.showNotificationSuccessOperation("Save notes on computer",
                                "The notes was saved successfully.");
                    } else {
                        NotificationBuilder.showNotificationFailedOperation("Save notes on computer",
                                "The notes was not saved successfully.");
                    }
                } else {
                    NotificationBuilder.showNotificationFailedOperation("Save notes on computer",
                            "The file was not created successfully.");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (NullPointerException e1) { /* empty */ }
        });

        return btnSaveOnComputerOnPopupMenu;
    }

    @FXML
    public void saveOnStage() {
        notes = (TreeItem) treeTableView.getSelectionModel().getSelectedItem();

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterTXT = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().addAll(extFilterTXT, extFilterPDF);
        File file = fileChooser.showSaveDialog(stage);

        try {
            if (file.createNewFile()) {
                if (FileBuilder.createFileNotes(file.getAbsolutePath(), notes.getValue().getTitle(),
                        notes.getValue().getContents(), notes.getValue().getDate())) {
                    NotificationBuilder.showNotificationSuccessOperation("Save notes on computer",
                            "The notes was saved successfully.");
                } else {
                    NotificationBuilder.showNotificationFailedOperation("Save notes on computer",
                            "The notes was not saved successfully.");
                }
            } else {
                NotificationBuilder.showNotificationFailedOperation("Save notes on computer",
                        "The file was not created successfully.");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e1) { /* empty */ }
    }

    @FXML
    public void loadWithStage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterTXT = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().addAll(extFilterTXT, extFilterPDF);

        File file = fileChooser.showOpenDialog(stage);

        String[] tabTitleAndContents = FileBuilder.loadNotes(file);

        if (tabTitleAndContents != null) {
            textTitle.setText(tabTitleAndContents[0]);
            textContents.setText(tabTitleAndContents[1]);
            NotificationBuilder.showNotificationSuccessOperation("Load notes with stage",
                    "The file was loaded successfully.");
        } else {
            NotificationBuilder.showNotificationFailedOperation("Load notes with stage",
                    "The file was not loaded successfully.");
        }
    }

    public void initReactionOnSlider() {
        sliderFontSize.setValue(15);
        textContents.setStyle("-fx-font-size: 15px;");
        sliderFontSize.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            textContents.setStyle("-fx-font-size:" + sliderFontSize.getValue() + "px;");
            textFontSize.setText(String.valueOf((int) sliderFontSize.getValue()));
        });
        textFontSize.setText(String.valueOf((int) sliderFontSize.getValue()));
    }

    @FXML
    public void keyReleasedInTextFontSize() {
        try {
            if (Double.valueOf(textFontSize.getText()) > 100) {
                PopOverBuilder.showStatementValidation(textFontSize, "Max 100");
            }
        } catch (NumberFormatException e) {
            textFontSize.setText(String.valueOf((int) sliderFontSize.getValue()));
            return;
        }

        sliderFontSize.setValue(Double.valueOf(textFontSize.getText()));
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
