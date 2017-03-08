package Service.Message;

/**
 * Created by Jonatan on 2017-02-02.
 */
public class MessageSent {
    private int ID;
    private String recipient;
    private String topic;
    private String contents;
    private boolean delete;
    private String status;
    private String postDate;

    // load to table
    public MessageSent(int ID, String recipient, String topic, String contents, boolean delete, String status,
                       String postDate) {
        this.ID = ID;
        this.recipient = recipient;
        this.topic = topic;
        this.contents = contents;
        this.delete = delete;
        this.status = status;
        this.postDate = postDate;
    }

    // for show and send again
    public MessageSent(int ID, String recipient, String topic, String contents, String status, String postDate) {
        this.ID = ID;
        this.recipient = recipient;
        this.topic = topic;
        this.contents = contents;
        this.status = status;
        this.postDate = postDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

}
