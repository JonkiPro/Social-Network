package Service.Notes;

/**
 * Created by Jonatan on 2017-03-01.
 */
public class Notes {
    int ID;
    int ID_Author;
    String title;
    String contents;
    String date;

    public Notes(int ID, int ID_Author, String title, String contents, String date) {
        this.ID = ID;
        this.ID_Author = ID_Author;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_Author() {
        return ID_Author;
    }

    public void setID_Author(int ID_Author) {
        this.ID_Author = ID_Author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
