package Service.Message;

/**
 * Created by Jonatan on 2017-02-02.
 */
public class MessageReadAndReceived {
    private int ID;
    private String sender;
    private String topic;
    private String contents;
    private boolean delete;
    private String status;
    private String dateReceived;

    // load to table
    public MessageReadAndReceived(int ID, String sender, String topic, String contents, boolean delete, String status,
                                  String dateReceived) {
        this.ID = ID;
        this.sender = sender;
        this.topic = topic;
        this.contents = contents;
        this.delete = delete;
        this.status = status;
        this.dateReceived = dateReceived;
    }

    // for show and reply
    public MessageReadAndReceived(int ID, String sender, String topic, String contents, String status,
                                  String dateReceived) {
        this.ID = ID;
        this.sender = sender;
        this.topic = topic;
        this.contents = contents;
        this.status = status;
        this.dateReceived = dateReceived;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

}
