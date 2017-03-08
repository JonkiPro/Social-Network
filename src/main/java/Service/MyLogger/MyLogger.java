package Service.MyLogger;

/**
 * Created by Jonatan on 2017-03-03.
 */
public class MyLogger {
    private int ID_Author;
    private String contents;
    private String date;

    // load
    public MyLogger(String contents, String date) {
        this.contents = contents;
        this.date = date;
    }

    public int getID_Author() {
        return ID_Author;
    }

    public void setID_Author(int ID_Author) {
        this.ID_Author = ID_Author;
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
