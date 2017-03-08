package Initialization;

import Database.Database;
import Service.Person.PersonOnList;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.scene.control.ComboBox;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jonatan on 2017-01-26.
 */
public class Initialization {
    static public void initSex(ComboBox<String> comboSex) {
        comboSex.getItems().addAll("", "male", "female");
    }

    static public void initOptionsForSendingMessage(ComboBox<String> comboSex) {
        comboSex.getItems().addAll("all", "login", "email");
        comboSex.getSelectionModel().select("all");
    }

    static public void initJFXListViewPersonsOnList(JFXListView<String> listView, String textWithCommas) {
        listView.getItems().clear();
        StringTokenizer token = new StringTokenizer(textWithCommas, ",");
        PersonOnList personOnList;

        while (token.hasMoreElements()) {
            personOnList = Database.changeOnPersonOnList(token.nextToken());
            listView.getItems().add(personOnList.getName() + " " + personOnList.getLastName());
        }
    }

    static public void initListPersonOnList(List<PersonOnList> listPersonOnList, String textWithCommas) {
        listPersonOnList.clear();
        StringTokenizer token = new StringTokenizer(textWithCommas, ",");
        PersonOnList personOnList;

        while (token.hasMoreElements()) {
            personOnList = Database.changeOnPersonOnList(token.nextToken());
            listPersonOnList.add(personOnList);
        }
    }

    static public void initOptionsForFilterTableSentWorkingCopy(JFXComboBox<String> comboWithSentOrWorkingCopy) {
        comboWithSentOrWorkingCopy.getItems().addAll("all", "recipient", "topic", "contents");
        comboWithSentOrWorkingCopy.getSelectionModel().select("all");
    }

    static public void initOptionsForFilterTableReadReceived(JFXComboBox<String> comboWithSentOrWorkingCopy) {
        comboWithSentOrWorkingCopy.getItems().addAll("all", "sender", "topic", "contents");
        comboWithSentOrWorkingCopy.getSelectionModel().select("all");
    }

    static public void initOptionsForFilterTableTrash(JFXComboBox<String> comboWithSentOrWorkingCopy) {
        comboWithSentOrWorkingCopy.getItems().addAll("all", "sender/recipient", "topic", "contents");
        comboWithSentOrWorkingCopy.getSelectionModel().select("all");
    }

    static public void initOptionsForSearch(JFXComboBox<String> comboSearch) {
        comboSearch.getItems().addAll("all", "name/last name", "login");
        comboSearch.getSelectionModel().select("all");
    }
}
