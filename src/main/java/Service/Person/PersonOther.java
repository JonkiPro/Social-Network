package Service.Person;


import javafx.scene.image.Image;

/**
 * Created by Jonatan on 2017-02-05.
 */

/*
 * Use in SearchController and ProfileController.
 */

public class PersonOther extends Person {
    private Image avatar;

    public PersonOther() {}

    public PersonOther(int ID, String name, String lastName, String login, Image avatar) {
        super.ID = ID;
        super.name.set(name);
        super.lastName.set(lastName);
        super.login.set(login);
        this.avatar = avatar;
    }

    @Override
    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

}
