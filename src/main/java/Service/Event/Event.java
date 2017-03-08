package Service.Event;

/**
 * Created by Jonatan on 2017-03-03.
 */
public class Event {
    private int ID;
    private int ID_Author;
    private String title;
    private String dateStart;
    private String timeStart;
    private String dateFinish;
    private String timeFinish;
    private String localization;
    private String description;

    public Event() {}

    public Event(String title, String dateStart, String timeStart, String dateFinish, String timeFinish,
                 String localization, String description) {
        this.title = title;
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.dateFinish = dateFinish;
        this.timeFinish = timeFinish;
        this.localization = localization;
        this.description = description;
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

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(String timeFinish) {
        this.timeFinish = timeFinish;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
