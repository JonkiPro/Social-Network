package Service.Comment;

/**
 * Created by Jonatan on 2017-02-25.
 */
public class Comment {
    private int ID;
    private int ID_Post;
    private int ID_Author;
    private String contents;
    private String date;

    public Comment(int ID, int ID_Post, int ID_Author, String contents, String date) {
        this.ID = ID;
        this.ID_Post = ID_Post;
        this.ID_Author = ID_Author;
        this.contents = contents;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_Post() {
        return ID_Post;
    }

    public void setID_Post(int ID_Post) {
        this.ID_Post = ID_Post;
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
