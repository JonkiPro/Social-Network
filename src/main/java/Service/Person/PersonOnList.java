package Service.Person;

/**
 * Created by Jonatan on 2017-01-29.
 */

/*
 * Use in SendMessageController.
 * I don`t inherit Person, because I don`t want to use the variable properties.
 */

public class PersonOnList {
    private int ID;
    private String login;
    private String name;
    private String lastName;
    private String email;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
