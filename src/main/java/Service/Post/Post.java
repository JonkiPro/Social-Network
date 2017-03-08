package Service.Post;

import java.util.List;

/**
 * Created by Jonatan on 2017-02-22.
 */
public class Post {
    private int ID;
    private int ID_Author;
    private int ID_Recipient;
    private String text;
    private boolean onMyBoard;
    private String date;
    private int numberOfLikes;

    private List<Integer> whoLikes;

    public Post(){}
    public Post(int ID, int ID_Author, int ID_Recipient, String text, boolean onMyBoard, String date,
                int numberOfLikes, List<Integer> whoLikes) {
        this.ID = ID;
        this.ID_Author = ID_Author;
        this.ID_Recipient = ID_Recipient;
        this.text = text;
        this.onMyBoard = onMyBoard;
        this.date = date;
        this.numberOfLikes = numberOfLikes;
        this.whoLikes = whoLikes;
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

    public int getID_Recipient() {
        return ID_Recipient;
    }

    public void setID_Recipient(int ID_Recipient) {
        this.ID_Recipient = ID_Recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isOnMyBoard() {
        return onMyBoard;
    }

    public void setOnMyBoard(boolean onMyBoard) {
        this.onMyBoard = onMyBoard;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public List<Integer> getWhoLikes() {
        return whoLikes;
    }

    public void setWhoLikes(List<Integer> whoLikes) {
        this.whoLikes = whoLikes;
    }
}
