package Service.Message;

/**
 * Created by Jonatan on 2017-01-29.
 */
public class Message {
    private Integer ID_Sender;
    private String ID_Recipients;
    private String topic;
    private String contents;
    private String status;

    public Message(int ID_Sender, String ID_Recipients, String topic, String contents, String status) {
        this.ID_Sender = ID_Sender;
        this.ID_Recipients = ID_Recipients;
        this.topic = topic;
        this.contents = contents;
        this.status = status;
    }

    public Integer getID_Sender() {
        return ID_Sender;
    }

    public void setID_Sender(int ID_Sender) {
        this.ID_Sender = ID_Sender;
    }

    public String getID_Recipients() {
        return ID_Recipients;
    }

    public void setID_Recipients(String ID_Recipients) {
        this.ID_Recipients = ID_Recipients;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
