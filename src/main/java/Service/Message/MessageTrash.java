package Service.Message;

/**
 * Created by Jonatan on 2017-02-02.
 */
public class MessageTrash {
    private int ID;
    private String senderOrRecipient;
    private String topic;
    private String contents;
    private boolean delete;
    private String previousStatus;
    private String date;
    private String dateRemoved;

    // load to table
    public MessageTrash(int ID, String senderOrRecipient, String topic, String contents, boolean delete,
                          String previousStatus, String date, String dateRemoved) {
        this.ID = ID;
        this.senderOrRecipient = senderOrRecipient;
        this.topic = topic;
        this.contents = contents;
        this.delete = delete;
        this.previousStatus = previousStatus;
        this.date = date;
        this.dateRemoved = dateRemoved;
    }

    // for show
    public MessageTrash(int ID, String senderOrRecipient, String topic, String contents, String previousStatus,
                        String date, String dateRemoved) {
        this.ID = ID;
        this.senderOrRecipient = senderOrRecipient;
        this.topic = topic;
        this.contents = contents;
        this.previousStatus = previousStatus;
        this.date = date;
        this.dateRemoved = dateRemoved;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSenderOrRecipient() {
        return senderOrRecipient;
    }

    public void setSenderOrRecipient(String senderOrRecipient) {
        this.senderOrRecipient = senderOrRecipient;
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

    public String getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(String previousStatus) {
        this.previousStatus = previousStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateRemoved() {
        return dateRemoved;
    }

    public void setDateRemoved(String dateRemoved) {
        this.dateRemoved = dateRemoved;
    }

}
