package Builder;

import Database.Database;
import Service.LoadPage.LoadPage;
import Service.Person.Person;
import Service.Person.PersonOnList;
import Service.Person.PersonOther;
import Validation.Validation;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonatan on 2017-01-29.
 */
public class ContextMenuBuilder {
    static public void initContextMenuForListPersons(ContextMenu contextMenu, ListView<String> listView,
                                                     List<PersonOnList> listPersonOnList, TextField textRecipient,
                                                     Person person) {
        MenuItem btnDelete = new MenuItem("Delete");
        btnDelete.setOnAction((ActionEvent e) -> {
            if (listView.getItems().size() > 0) {
                List<PersonOnList> listOnMoment = new ArrayList<>();

                try {
                    listOnMoment.add(listPersonOnList.get(listView.getSelectionModel().getSelectedIndex()));
                } catch (ArrayIndexOutOfBoundsException e2) {
                    return;
                }

                textRecipient.setText(Validation.deleteLoginsAndEmailsWithTextByID(textRecipient.getText(),
                        listOnMoment));
                if (textRecipient.getText() == null)
                    textRecipient.setText("");
                listPersonOnList.remove(listView.getSelectionModel().getSelectedIndex());
                listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
            }
        });

        MenuItem btnGoToAccount = new MenuItem("Go to account");
        btnGoToAccount.setOnAction((ActionEvent e) -> {
            PersonOther personOther;
            try {
                personOther = Database.getPerson(listPersonOnList.get(listView.getSelectionModel().getSelectedIndex()).getID());
            } catch (ArrayIndexOutOfBoundsException e2) {
                return;
            }
            new LoadPage().loadProfile(personOther, person);
        });

        contextMenu.getItems().addAll(btnDelete, btnGoToAccount);
        listView.setContextMenu(contextMenu);
    }
}