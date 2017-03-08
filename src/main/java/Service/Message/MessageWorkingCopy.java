package Service.Message;

/**
 * Created by Jonatan on 2017-02-01.
 */
public class MessageWorkingCopy {
    private int ID;
    private String recipient;
    private String topic;
    private String contents;
    private boolean delete;
    private String status;
    private String saveDate;

    // load to table
    public MessageWorkingCopy(int ID, String recipient, String topic, String contents, boolean delete, String status,
                              String saveDate) {
        this.ID = ID;
        this.recipient = recipient;
        this.topic = topic;
        this.contents = contents;
        this.delete = delete;
        this.status = status;
        this.saveDate = saveDate;
    }

    // for show message and edit
    public MessageWorkingCopy(int ID, String recipient, String topic, String contents, String status,
                              String saveDate) {
        this.ID = ID;
        this.recipient = recipient;
        this.topic = topic;
        this.contents = contents;
        this.status = status;
        this.saveDate = saveDate;
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

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }
}
