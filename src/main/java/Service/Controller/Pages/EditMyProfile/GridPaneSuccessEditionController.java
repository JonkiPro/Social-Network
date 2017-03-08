package Service.Controller.Pages.EditMyProfile;

import Service.LoadPage.LoadPage;
import Service.Person.Person;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Jonatan on 2017-01-26.
 */
public class GridPaneSuccessEditionController extends AnchorPane {

    private Person person;

    @FXML
    public void back() {
        new LoadPage().loadEditMyProfile(person);
    }


    public void setPerson(Person person) {
        this.person = person;
    }

}
