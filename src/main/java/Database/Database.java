package Database;

import LoginRegisterPanel.Person.PersonToRegister;
import Service.Comment.Comment;
import Service.Event.Event;
import Service.Message.*;
import Service.MyLogger.MyLogger;
import Service.Notes.Notes;
import Service.Person.Person;
import Service.Person.PersonOnList;
import Service.Person.PersonOther;
import Service.Post.Post;
import Validation.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * public static boolean dbConnect(String db_connect_string, String db_userid, String db_password);
 * public static boolean addPerson(PersonToRegister person);
 * public static Person login(String login, String password);
 * public static List<String> loadLogins();
 * public static List<String> loadEmails();
 * public static List<String> loadNames();
 * public static List<String> loadLastNames();
 * public static List<String> loadNamesAndLastNames();
 * public static List<String> loadLoginsAndEmails();
 * public static String loadLeadingQuestion(int ID);
 * public static InputStream getAvatar(int ID);
 * public static List<PersonOther> loadNamesAndLastNamesAndLoginByText(int yourID, String text);
 * public static List<PersonOther> loadNamesAndLastNamesByText(String text);
 * public static List<PersonOther> loadLoginsByText(String text);
 * public static int findIDForLogin(String login);
 * public static boolean checkLogin(String login);
 * public static boolean checkEmail(String email);
 * public static boolean checkAnswer(int ID, String answer);
 * public static boolean checkYear(int ID, String year);
 * public static boolean changeName(int ID, String name);
 * public static boolean changeSecondName(int ID, String secondName);
 * public static boolean changeLastName(int ID, String lastName);
 * public static boolean changeLogin(int ID, String login);
 * public static boolean changePassword(int ID, String password);
 * public static boolean changeEmail(int ID, String email);
 * public static boolean changeQuestion(int ID, String question);
 * public static boolean changeAnswer(int ID, String answer);
 * public static boolean changeSex(int ID, String sex);
 * public static boolean changeDateOfBirth(int ID, String dateOfBirth);
 * public static boolean changeAboutMe(int ID, String aboutMe);
 * public static boolean changeAvatar(int ID, File file);
 * public static PersonOnList changeOnPersonOnList(String loginOrEmail);
 * public static int changeLoginOnID(String login);
 * public static int changeIDOnLogin(int ID);
 * public static String changeIDOnNameAndLastName(int ID);
 * private static int setIDMessage();
 * public static boolean sendMessage(Message message);
 * public static boolean saveMessage(Message message);
 * public static boolean setOnReadMessage(int ID);
 * public static boolean deleteMessage(Set<Map.Entry<Integer, String>> mapMessageToDelete);
 * public static boolean deleteMessage(int ID, String status);
 * public static boolean constDeleteMessage(List<Integer> listMessageToDelete);
 * public static boolean constDeleteMessage(int ID);
 * public static PersonOnList changeOnPersonOnList(String loginOrEmail);
 * public static ObservableList<TreeItem<MessageReceivedAndRead>> getRead(int ID);
 * public static ObservableList<TreeItem<MessageReceivedAndRead>> getReceivedMessage(int ID);
 * public static ObservableList<TreeItem<MessageSent>> getSentMessage(int ID);
 * public static ObservableList<TreeItem<MesssageWorkingCopy>> getWorkingCopyMessage(int ID);
 * public static ObservableList<TreeItem<MessageTrash>> getMessageWithTrash(int ID);
 * public static boolean restoreMessage(Set<Map.Entry<Integer, String>> listMessageToRestore);
 * public static boolean restoreMessage(int ID, String status);
 * public static List<PersonOther> getFriends(int yourID);
 * public static List<Integer> getListIDsFriends(int yourID);
 * public static List<PersonOther> getYourInvitations(int yourID);
 * public static List<Integer> getListIDsYourInvitations(int yourID);
 * public static List<PersonOther> getInvitations(int yourID);
 * public static List<Integer> getListIDsInvitations(int yourID);
 * public static void addInvitedFriends(int ID, int yourID, List<Integer> listYourFriends);
 * public static void deleteInvitedFriends(int ID, int yourID, List<Integer> listYourFriends);
 * public static void deleteFriends(int ID, int yourID, List<Integer> listYourFriends);
 * public static String countFriends(int ID);
 * public static String countYourInvitations(int ID);
 * public static String countInvitations(int ID);
 * public static boolean declineInivitation();
 * private static int setIDPost();
 * public static boolean addPost(int yourID, String text, boolean postOnMyBoard);
 * public static boolean addPostOnBoardPersonOther(int yourID, int recipientID, String text, boolean postOnMyBoard);
 * public static List<Integer> getNumberOfPosts(int ID, List<Integer> listFriends);
 * public static List<Integer> getNumberOfPostsForPerson(int ID);
 * public static List<Integer> getNumberOfPostsMyFriends(List<Integer> listFriends);
 * public static List<Integer> getNumberOfPostsForPersonOnBoard(int ID);
 * public static List<Integer> getNumberOfAllPosts();
 * public static List<Post> getPosts(List<Integer> listIDsPosts);
 * public static boolean likePost(int IDPost, int yourID);
 * public static boolean unlikePost(int IDPost, int yourID);
 * public static int getNumberOfLikes(int IDPost);
 * public static List<Integer> getWhoLikes(int IDPost);
 * private static int setIDReport();
 * public static boolean sendReportPost(int IDPost, String reason);
 * public static List<Integer> getReportsPost();
 * private static int setIDComment();
 * public static boolean addComment(int yourID, int ID_Post, String text);
 * public static List<Comment> getComments(int IDPost);
 * public static PersonOther getPerson(int ID);
 * private static int setIDNotes();
 * public static boolean addNotes(int yourID, String title, String contents);
 * public static boolean deleteNotes(int IDNotes);
 * public static boolean clearListNotes(List<Integer> lisIDsNotes);
 * public static boolean updateNotes(int ID, String title, String contents);
 * public static ObservableList<TreeItem<Notes>> getNotes(int ID);
 * private static int setIDLogger();
 * public static boolean addLogger(int yourID, String contents);
 * public static List<MyLogger> getLoggers(int ID);
 * private static int setIDEvent();
 * public static boolean addEvent(String title, String dateStart, String timeStart, String dateFinish, String timeFinish, String localization, String description);
 * public static List<Integer> getNumberOfEventsMyAndMyFriends(int ID, List<Integer> listFriends);
 * private static List<Integer> getNumberOfEventsForPerson(int ID);
 * public static List<Integer> getNumberOfAllEvents()
 * public static List<Event> getEvents(List<Integer> listIDsEvents);
 * private static int setIDRateOnEvent();
 * public static boolean addRateOnEvent(int IDEvent, int yourID, float rate);
 * private static boolean changeRateOnEvent(int IDEvent, int yourID, float rate);
 * public static float getRateOfEvent(int IDEvent);
 * private static float getMyRateForEvent(int IDEvent, int yourID);
 * public static String getDatabaseName();
 */

/**
 * Created by Jonatan on 2016-12-31.
 */
public class Database {
    public Database() {
    }

    protected static Connection conn;
    protected static Statement statement;
    protected static PreparedStatement preparedStatement;
    protected static String queryString;

    public static boolean dbConnect(String db_connect_string, String db_userid, String db_password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
            statement = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean addPerson(PersonToRegister person) {
        int count;

        try {
            try {
                queryString = "SELECT MAX(ID) ID FROM Persons";
                ResultSet rs = statement.executeQuery(queryString);
                rs.next();
                count = Integer.parseInt(rs.getString("ID")) + 1;
            } catch (NumberFormatException e) {
                count = 1;
            }

            preparedStatement = conn.prepareStatement("INSERT INTO Persons(ID, Login, Password, Email, Name, Last_name, Date_of_birth, Sex, Leading_question, Answer) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, count);
            preparedStatement.setString(2, person.getLogin());
            preparedStatement.setString(3, person.getPassword());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, person.getName());
            preparedStatement.setString(6, person.getLastName());
            preparedStatement.setObject(7, person.getDateOfBirth());
            preparedStatement.setString(8, person.getSex());
            preparedStatement.setString(9, person.getLeadingQuestion());
            preparedStatement.setString(10, person.getAnswer());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static Person login(String login, String password) {
        queryString = "SELECT ID, Login, Password, Email, Name, Second_name, Last_name, Date_of_birth, Sex, Leading_question, Answer, About_me, Friends, Invited_friends FROM Persons";
        try {
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next()) {
                if (login.toLowerCase().trim().equals(rs.getString("Login").toLowerCase().trim())) {
                    if (password.trim().equals(rs.getString("Password").trim())) {
                        Person person = new Person();

                        person.setLogin(rs.getString("Login").trim());
                        person.setPassword(rs.getString("Password").trim());
                        person.setEmail(rs.getString("Email").trim());

                        try {
                            person.setID(Integer.valueOf(rs.getString("ID")));
                        } catch (NullPointerException e) {
                            person.setID(0);
                        }
                        try {
                            person.setName(rs.getString("Name").trim());
                        } catch (NullPointerException e) {
                            person.setName(null);
                        }
                        try {
                            person.setSecondName(rs.getString("Second_name").trim());
                        } catch (NullPointerException e) {
                            person.setSecondName(null);
                        }
                        try {
                            person.setLastName(rs.getString("Last_name").trim());
                        } catch (NullPointerException e) {
                            person.setLastName(null);
                        }
                        try {
                            person.setDateOfBirth(rs.getString("Date_of_birth").trim());
                        } catch (NullPointerException e) {
                            person.setDateOfBirth(null);
                        }
                        try {
                            person.setSex(rs.getString("Sex").trim());
                        } catch (NullPointerException e) {
                            person.setSex(null);
                        }
                        try {
                            person.setLeadingQuestion(rs.getString("Leading_question").trim());
                        } catch (NullPointerException e) {
                            person.setLeadingQuestion(null);
                        }
                        try {
                            person.setAnswer(rs.getString("Answer").trim());
                        } catch (NullPointerException e) {
                            person.setAnswer(null);
                        }
                        try {
                            person.setAboutMe(rs.getString("About_me").trim());
                        } catch (NullPointerException e) {
                            person.setAboutMe(null);
                        }
                        List<Integer> listFriends = new ArrayList<>();
                        try {
                            StringTokenizer token = new StringTokenizer(rs.getString("Friends").trim(), ",");
                            while (token.hasMoreElements())
                                listFriends.add(Integer.parseInt(token.nextToken().trim()));
                            person.setFriends(listFriends);
                        } catch (NullPointerException e) {
                            person.setFriends(listFriends);
                        }
                        List<Integer> listInvitedFriends = new ArrayList<>();
                        try {

                            StringTokenizer token = new StringTokenizer(rs.getString("Invited_friends").trim(), ",");
                            while (token.hasMoreElements())
                                listInvitedFriends.add(Integer.parseInt(token.nextToken().trim()));
                            person.setInvitedFriends(listInvitedFriends);
                        } catch (NullPointerException e) {
                            person.setInvitedFriends(listInvitedFriends);
                        }
                        try {
                            person.setNumberOfFriends(Database.countFriends(person.getID()));
                        } catch (NullPointerException e) {
                            person.setNumberOfFriends(null);
                        }
                        try {
                            person.setNumberOfInvitations(Database.countInvitations(person.getID()));
                        } catch (NullPointerException e) {
                            person.setNumberOfInvitations(null);
                        }
                        try {
                            person.setNumberOfYourInvitations(Database.countYourInvitations(person.getID()));
                        } catch (NullPointerException e) {
                            person.setNumberOfYourInvitations(null);
                        }
                        try {
                            person.setAvatar(new Image(Database.getAvatar(person.getID())));
                        } catch (NullPointerException e) {
                            person.setAvatar(new Image("/images/service/person.png"));
                        }

                        return person;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }

        return null;
    }

    public static List<String> loadLogins() {
        List<String> listLogins = new ArrayList<>();

        try {
            queryString = "SELECT Login from Persons";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                listLogins.add(rs.getString("Login").trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return listLogins;
        }

        return listLogins;
    }

    public static List<String> loadEmails() {
        List<String> listEmails = new ArrayList<>();

        try {
            queryString = "SELECT Email from Persons";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                listEmails.add(rs.getString("Email").trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return listEmails;
        }

        return listEmails;
    }


    public static List<String> loadNames() {
        List<String> listNames = new ArrayList<>();

        try {
            queryString = "SELECT Name from Persons";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                listNames.add(rs.getString("Name").trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listNames;
    }


    public static List<String> loadLastNames() {
        List<String> listLastNames = new ArrayList<>();

        try {
            queryString = "SELECT Last_name from Persons";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                listLastNames.add(rs.getString("Last_name").trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listLastNames;
    }


    public static List<String> loadNamesAndLastNames() {
        List<String> listNamesAndLastNames = new ArrayList<>();

        try {
            queryString = "SELECT Name, Last_name from Persons";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                listNamesAndLastNames.add(rs.getString("Name").trim() + " " + rs.getString("Last_name").trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listNamesAndLastNames;
    }


    public static List<String> loadLoginsAndEmails() {
        List<String> list = new ArrayList<>();

        try {
            queryString = "SELECT Login, Email from Persons";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                list.add(rs.getString("Login").trim());
                list.add(rs.getString("Email").trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }

        return list;
    }

    public static String loadLeadingQuestion(int ID) {
        try {
            queryString = "SELECT Leading_question from Persons WHERE ID = " + ID;
            ResultSet rs = statement.executeQuery((queryString));
            rs.next();

            return rs.getString("Leading_question");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static InputStream getAvatar(int ID) {
        InputStream is;
        try {
            queryString = "SELECT Avatar from Persons WHERE ID = " + ID;
            ResultSet rs = statement.executeQuery((queryString));
            rs.next();

            is = rs.getBinaryStream("Avatar");
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }

        return is;
    }

    public static List<PersonOther> loadNamesAndLastNamesAndLoginByText(int yourID, String text) {
        List<PersonOther> list = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT ID, Name, Last_name, Login FROM Persons");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (Integer.parseInt(rs.getString("ID").trim()) == yourID)
                    continue;

                if (rs.getString("Name").toLowerCase().trim().indexOf(text.toLowerCase().trim()) != -1
                        || rs.getString("Last_name").toLowerCase().trim().indexOf(text.toLowerCase().trim()) != -1
                        || rs.getString("Login").toLowerCase().trim().indexOf(text.toLowerCase().trim()) != -1) {
                    Image avatar;
                    try {
                        avatar = new Image(getAvatar(rs.getInt("ID")));
                    } catch (NullPointerException e) {
                        avatar = new Image("/images/service/person.png");
                    }
                    list.add(new PersonOther(Integer.parseInt(rs.getString("ID").trim()), rs.getString("Name").trim(),
                            rs.getString("Last_name").trim(), rs.getString("Login").trim(), avatar));
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }

        return list;
    }

    public static List<PersonOther> loadNamesAndLastNamesByText(int yourID, String text) {
        List<PersonOther> list = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT ID, Name, Last_name, Login FROM Persons");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (Integer.parseInt(rs.getString("ID").trim()) == yourID)
                    continue;

                if (rs.getString("Name").toLowerCase().trim().indexOf(text.toLowerCase().trim()) != -1
                        || rs.getString("Last_name").toLowerCase().trim().indexOf(text.toLowerCase().trim()) != -1) {
                    Image avatar;
                    try {
                        avatar = new Image(getAvatar(rs.getInt("ID")));
                    } catch (NullPointerException e) {
                        avatar = new Image("/images/service/person.png");
                    }
                    list.add(new PersonOther(Integer.parseInt(rs.getString("ID").trim()), rs.getString("Name").trim(),
                            rs.getString("Last_name").trim(), rs.getString("Login").trim(), avatar));
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }

        return list;
    }

    public static List<PersonOther> loadLoginsByText(int yourID, String text) {
        List<PersonOther> list = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT ID, Name, Last_name, Login FROM Persons");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (Integer.parseInt(rs.getString("ID").trim()) == yourID)
                    continue;

                if (rs.getString("Login").toLowerCase().trim().indexOf(text.toLowerCase().trim()) != -1) {
                    Image avatar;
                    try {
                        avatar = new Image(getAvatar(rs.getInt("ID")));
                    } catch (NullPointerException e) {
                        avatar = new Image("/images/service/person.png");
                    }
                    list.add(new PersonOther(Integer.parseInt(rs.getString("ID").trim()), rs.getString("Name").trim(),
                            rs.getString("Last_name").trim(), rs.getString("Login").trim(), avatar));
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }

        return list;
    }

    public static int findIDForLogin(String login) {
        int ID;

        try {
            queryString = "SELECT ID, Login from Persons";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                if (login.toLowerCase().trim().equals(rs.getString("Login").toLowerCase().trim())) {
                    ID = Integer.parseInt(rs.getString("ID"));
                    return ID;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return 0;
        }

        return 0;
    }

    public static boolean checkLogin(String login) {
        try {
            queryString = "SELECT Login from Persons";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                if (login.toLowerCase().trim().equals(rs.getString("Login").toLowerCase().trim())) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean checkEmail(String email) {
        try {
            queryString = "SELECT Email from Persons";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                if (email.toLowerCase().trim().equals(rs.getString("Email").toLowerCase().trim())) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean checkAnswer(int ID, String answer) {
        boolean result;
        try {
            queryString = "SELECT Answer from Persons WHERE ID = " + ID;
            ResultSet rs = statement.executeQuery((queryString));
            rs.next();

            if (answer.toLowerCase().trim().equals(rs.getString("Answer").toLowerCase().trim()))
                result = true;
            else
                result = false;

            return result;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public static boolean checkYear(int ID, String year) {
        boolean result;
        try {
            queryString = "SELECT Date_of_birth from Persons WHERE ID = " + ID;
            ResultSet rs = statement.executeQuery((queryString));
            rs.next();

            StringTokenizer token = new StringTokenizer(rs.getString("Date_of_birth"), "-");
            String yearOfBirth;
            yearOfBirth = token.nextToken();

            if (year.trim().equals(yearOfBirth.trim()) || yearOfBirth.trim().equals("null"))
                result = true;
            else
                result = false;

            return result;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public static boolean changeName(int ID, String name) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Name = ? WHERE ID = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean changeSecondName(int ID, String secondName) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Second_name = ? WHERE ID = ?");
            preparedStatement.setString(1, secondName);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean changeLastName(int ID, String lastName) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Last_name = ? WHERE ID = ?");
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean changeLogin(int ID, String login) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Login = ? WHERE ID = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean changePassword(int ID, String password) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Password = ? WHERE ID = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean changeEmail(int ID, String email) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Email = ? WHERE ID = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }


    public static boolean changeQuestion(int ID, String question) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Leading_question = ? WHERE ID = ?");
            preparedStatement.setString(1, question);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean changeAnswer(int ID, String answer) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Answer = ? WHERE ID = ?");
            preparedStatement.setString(1, answer);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean changeSex(int ID, String sex) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Sex = ? WHERE ID = ?");
            preparedStatement.setString(1, sex);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean changeDateOfBirth(int ID, Object dateOBirth) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Date_of_birth = ? WHERE ID = ?");
            preparedStatement.setObject(1, dateOBirth);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean changeAboutMe(int ID, String aboutMe) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET About_me = ? WHERE ID = ?");
            preparedStatement.setObject(1, aboutMe);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean changeAvatar(int ID, File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Avatar = ? WHERE ID = ?");
            preparedStatement.setBinaryStream(1, fis, (int) file.length());
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static PersonOnList changeOnPersonOnList(String loginOrEmail) {
        PersonOnList personOnList = new PersonOnList();

        try {
            queryString = "SELECT ID, Login, Email, Name, Last_name from Persons";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                if (loginOrEmail.toLowerCase().trim().equals(rs.getString("Login").toLowerCase().trim())) {
                    personOnList.setID(Integer.parseInt(rs.getString("ID").trim()));
                    personOnList.setLogin(rs.getString("Login").trim());
                    personOnList.setName(rs.getString("Name").trim());
                    personOnList.setLastName(rs.getString("Last_name").trim());
                    personOnList.setEmail(rs.getString("Email").trim());

                    return personOnList;
                }

                if (loginOrEmail.toLowerCase().trim().equals(rs.getString("Email").toLowerCase().trim())) {
                    personOnList.setID(Integer.parseInt(rs.getString("ID").trim()));
                    personOnList.setLogin(rs.getString("Login").trim());
                    personOnList.setName(rs.getString("Name").trim());
                    personOnList.setLastName(rs.getString("Last_name").trim());
                    personOnList.setEmail(rs.getString("Email").trim());

                    return personOnList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }

        return null;
    }

    public static int changeLoginOnID(String login) {
        try {
            queryString = "SELECT ID, Login from Persons";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                if (login.toLowerCase().trim().equals(rs.getString("Login").toLowerCase().trim())) {
                    return Integer.parseInt(rs.getString("ID").trim());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static String changeIDOnLogin(int ID) {
        try {
            preparedStatement = conn.prepareStatement("SELECT Login from Persons WHERE ID = ?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            return rs.getString("Login").trim();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String changeIDOnNameAndLastName(int ID) {
        try {
            preparedStatement = conn.prepareStatement("SELECT Name, Last_name from Persons WHERE ID = ?");
            preparedStatement.setInt(1, ID);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            return rs.getString("Name").trim() + " " + rs.getString("Last_name").trim();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String changeTextWithIDsSeparatedCommasOnLogins(String textWithIDsSeparatedCommas) {
        String text = null;

        try {
            StringTokenizer token = new StringTokenizer(textWithIDsSeparatedCommas, " , ");

            while (token.hasMoreElements()) {
                if (text == null) {
                    text = changeIDOnLogin(Integer.parseInt(token.nextToken()));
                } else
                    text += " , " + changeIDOnLogin(Integer.parseInt(token.nextToken()));
            }
        } catch (NullPointerException e) {
            return "";
        }

        return text;
    }

    private static int setIDMessage() {
        int count = 0;

        try {
            queryString = "SELECT MAX(ID) ID FROM Messages";
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();
            count = Integer.parseInt(rs.getString("ID")) + 1;
        } catch (NumberFormatException e) {
            count = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static boolean sendMessage(Message message) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO Messages(ID, ID_Sender, ID_Recipients, Topic, Contents, Status, Date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, setIDMessage());
            preparedStatement.setInt(2, message.getID_Sender());
            preparedStatement.setString(3, message.getID_Recipients());
            preparedStatement.setString(4, message.getTopic().trim());
            preparedStatement.setString(5, message.getContents().trim());
            preparedStatement.setString(6, message.getStatus());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY  HH:mm:ss", Locale.getDefault());
            preparedStatement.setString(7, dateFormat.format(new Date()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean saveMessage(Message message) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO Messages(ID, ID_Sender, Saved_Recipients, Topic, Contents, Status, Date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, setIDMessage());
            preparedStatement.setInt(2, message.getID_Sender());
            preparedStatement.setString(3, message.getID_Recipients());
            preparedStatement.setString(4, message.getTopic().trim());
            preparedStatement.setString(5, message.getContents().trim());
            preparedStatement.setString(6, message.getStatus());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY  HH:mm:ss", Locale.getDefault());
            preparedStatement.setString(7, dateFormat.format(new Date()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean setOnReadMessage(int ID) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Messages SET Status = ? WHERE ID = ?");
            preparedStatement.setString(1, "read");
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean deleteMessage(Set<Map.Entry<Integer, String>> mapMessageToDelete) {
        try {
            for (Map.Entry<Integer, String> message : mapMessageToDelete) {
                preparedStatement = conn.prepareStatement("UPDATE Messages SET Status = ? WHERE ID = ?");
                preparedStatement.setString(1, "trash");
                preparedStatement.setInt(2, message.getKey());

                preparedStatement.executeUpdate();

                preparedStatement = conn.prepareStatement("UPDATE Messages SET Previous_status = ? WHERE ID = ?");
                preparedStatement.setString(1, message.getValue());
                preparedStatement.setInt(2, message.getKey());

                preparedStatement.executeUpdate();

                preparedStatement = conn.prepareStatement("UPDATE Messages SET Date_removed = ? WHERE ID = ?");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY  HH:mm:ss");
                preparedStatement.setString(1, sdf.format(new Date()));
                preparedStatement.setInt(2, message.getKey());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean deleteMessage(int ID, String status) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Messages SET Status = ? WHERE ID = ?");
            preparedStatement.setString(1, "trash");
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("UPDATE Messages SET Previous_status = ? WHERE ID = ?");
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("UPDATE Messages SET Date_removed = ? WHERE ID = ?");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY  HH:mm:ss");
            preparedStatement.setString(1, sdf.format(new Date()));
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean constDeleteMessage(List<Integer> listMessageToDelete) {
        try {
            for (Integer ID : listMessageToDelete) {
                preparedStatement = conn.prepareStatement("DELETE FROM Messages WHERE ID = ?");
                preparedStatement.setInt(1, ID);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean constDeleteMessage(int ID) {
        try {
            preparedStatement = conn.prepareStatement("DELETE FROM Messages WHERE ID = ?");
            preparedStatement.setInt(1, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static ObservableList<TreeItem<MessageReadAndReceived>> getReadMessage(int ID) {
        ObservableList<TreeItem<MessageReadAndReceived>> observableList = FXCollections.observableArrayList();
        MessageReadAndReceived message;
        TreeItem<MessageReadAndReceived> treeItem;

        String login;

        try {
            queryString = "SELECT ID, ID_Sender, ID_Recipients, Topic, Contents, Status, Date from Messages WHERE Status = 'read'";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                if (Validation.isYourTextInTextWithCommas(String.valueOf(ID), rs.getString("ID_Recipients"))) {
                    login = changeIDOnLogin(Integer.valueOf(rs.getString("ID_Sender")));
                    message = new MessageReadAndReceived(rs.getInt("ID"),
                            login,
                            rs.getString("Topic").trim(),
                            rs.getString("Contents").trim(),
                            false,
                            rs.getString("Status").trim(),
                            rs.getString("Date").trim());

                    treeItem = new TreeItem<>(message);

                    observableList.add(treeItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return observableList;
        }

        return observableList;
    }

    public static ObservableList<TreeItem<MessageReadAndReceived>> getReceivedMessage(int ID) {
        ObservableList<TreeItem<MessageReadAndReceived>> observableList = FXCollections.observableArrayList();
        MessageReadAndReceived message;
        TreeItem<MessageReadAndReceived> treeItem;

        String login;

        try {
            queryString = "SELECT ID, ID_Sender, ID_Recipients, Topic, Contents, Status, Date from Messages WHERE Status = 'sent'";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                if (Validation.isYourTextInTextWithCommas(String.valueOf(ID), rs.getString("ID_Recipients"))) {
                    login = changeIDOnLogin(Integer.valueOf(rs.getString("ID_Sender")));
                    message = new MessageReadAndReceived(rs.getInt("ID"),
                            login,
                            rs.getString("Topic").trim(),
                            rs.getString("Contents").trim(),
                            false,
                            rs.getString("Status").trim(),
                            rs.getString("Date").trim());

                    treeItem = new TreeItem<>(message);

                    observableList.add(treeItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return observableList;
        }

        return observableList;
    }

    public static ObservableList<TreeItem<MessageSent>> getSentMessage(int ID) {
        ObservableList<TreeItem<MessageSent>> observableList = FXCollections.observableArrayList();
        MessageSent message;
        TreeItem<MessageSent> treeItem;

        try {
            queryString = "SELECT ID, ID_Sender, ID_Recipients, Topic, Contents, Status, Date from Messages WHERE Status = 'sent'";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                if (ID == rs.getInt("ID_Sender")) {
                    message = new MessageSent(rs.getInt("ID"),
                            changeTextWithIDsSeparatedCommasOnLogins(rs.getString("ID_Recipients").trim()),
                            rs.getString("Topic").trim(),
                            rs.getString("Contents").trim(),
                            false,
                            rs.getString("Status").trim(),
                            rs.getString("Date").trim());

                    treeItem = new TreeItem<>(message);

                    observableList.add(treeItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return observableList;
        }

        return observableList;
    }

    public static ObservableList<TreeItem<MessageWorkingCopy>> getWorkingCopyMessage(int ID) {
        ObservableList<TreeItem<MessageWorkingCopy>> observableList = FXCollections.observableArrayList();
        MessageWorkingCopy message;
        TreeItem<MessageWorkingCopy> treeItem;

        try {
            queryString = "SELECT ID, ID_Sender, Saved_Recipients, Topic, Contents, Status, Date from Messages WHERE Status = 'saved'";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                if (ID == rs.getInt("ID_Sender")) {
                    message = new MessageWorkingCopy(rs.getInt("ID"),
                            rs.getString("Saved_Recipients").trim(),
                            rs.getString("Topic").trim(),
                            rs.getString("Contents").trim(),
                            false,
                            rs.getString("Status").trim(),
                            rs.getString("Date").trim());

                    treeItem = new TreeItem<>(message);

                    observableList.add(treeItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return observableList;
        }

        return observableList;
    }

    public static ObservableList<TreeItem<MessageTrash>> getMessageWithTrash(int ID) {
        ObservableList<TreeItem<MessageTrash>> observableList = FXCollections.observableArrayList();
        MessageTrash message = null;
        TreeItem<MessageTrash> treeItem;

        String login, recipients;

        try {
            queryString = "SELECT ID, ID_Sender, ID_Recipients, Saved_Recipients, Topic, Contents, Status, Previous_status, Date, Date_removed from Messages WHERE Status = 'trash'";
            ResultSet rs = statement.executeQuery((queryString));

            while (rs.next()) {
                if (Validation.isYourTextInTextWithCommas(String.valueOf(ID), rs.getString("ID_Recipients"))) {
                    login = changeIDOnLogin(Integer.valueOf(rs.getString("ID_Sender")));
                    if (rs.getString("Previous_status").trim().equals("read")) {
                        message = new MessageTrash(rs.getInt("ID"),
                                login,
                                rs.getString("Topic").trim(),
                                rs.getString("Contents").trim(),
                                false,
                                rs.getString("Previous_status").trim(),
                                rs.getString("Date").trim(),
                                rs.getString("Date_removed").trim());
                    } else if (rs.getString("Previous_status").trim().equals("sent")) {
                        message = new MessageTrash(rs.getInt("ID"),
                                login,
                                rs.getString("Topic").trim(),
                                rs.getString("Contents").trim(),
                                false,
                                "received",
                                rs.getString("Date").trim(),
                                rs.getString("Date_removed").trim());
                    }

                    treeItem = new TreeItem<>(message);

                    observableList.add(treeItem);
                } else if (rs.getInt("ID_Sender") == ID) {
                    if (rs.getString("Previous_status").trim().equals("sent")) {
                        recipients = changeTextWithIDsSeparatedCommasOnLogins(rs.getString("ID_Recipients").trim());

                        message = new MessageTrash(rs.getInt("ID"),
                                recipients,
                                rs.getString("Topic").trim(),
                                rs.getString("Contents").trim(),
                                false,
                                rs.getString("Previous_status").trim(),
                                rs.getString("Date").trim(),
                                rs.getString("Date_removed").trim());
                    } else if (rs.getString("Previous_status").trim().equals("saved")) {
                        message = new MessageTrash(rs.getInt("ID"),
                                rs.getString("Saved_Recipients").trim(),
                                rs.getString("Topic").trim(),
                                rs.getString("Contents").trim(),
                                false,
                                rs.getString("Previous_status").trim(),
                                rs.getString("Date").trim(),
                                rs.getString("Date_removed").trim());
                    }

                    treeItem = new TreeItem<>(message);

                    observableList.add(treeItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return observableList;
        }

        return observableList;
    }

    public static boolean restoreMessage(Set<Map.Entry<Integer, String>> listMessageToRestore) {
        try {
            for (Map.Entry<Integer, String> map : listMessageToRestore) {
                preparedStatement = conn.prepareStatement("UPDATE Messages SET Status = ? WHERE ID = ?");
                preparedStatement.setString(1, map.getValue());
                preparedStatement.setInt(2, map.getKey());

                preparedStatement.executeUpdate();

                preparedStatement = conn.prepareStatement("UPDATE Messages SET Previous_status = ? WHERE ID = ?");
                preparedStatement.setString(1, "");
                preparedStatement.setInt(2, map.getKey());

                preparedStatement.executeUpdate();

                preparedStatement = conn.prepareStatement("UPDATE Messages SET Date_removed = ? WHERE ID = ?");
                preparedStatement.setString(1, "");
                preparedStatement.setInt(2, map.getKey());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean restoreMessage(int ID, String status) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Messages SET Status = ? WHERE ID = ?");
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("UPDATE Messages SET Previous_status = ? WHERE ID = ?");
            preparedStatement.setString(1, "");
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("UPDATE Messages SET Date_removed = ? WHERE ID = ?");
            preparedStatement.setString(1, "");
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static List<PersonOther> getFriends(int yourID) {
        List<PersonOther> list = new ArrayList<>();

        try {
            queryString = "SELECT Friends FROM Persons WHERE ID = " + yourID;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();

            PersonOther person;
            StringTokenizer token;
            try {
                token = new StringTokenizer(rs.getString("Friends"), " , ");
            } catch (NullPointerException e) {
                return list;
            }

            while (token.hasMoreElements()) {
                person = new PersonOther();
                int ID = Integer.parseInt(token.nextToken().trim());
                queryString = "SELECT ID, Name, Last_name, Login FROM Persons WHERE ID = " + ID;
                rs = statement.executeQuery(queryString);
                rs.next();
                try {
                    person.setID(rs.getInt("ID"));
                    person.setName(rs.getString("Name").trim());
                    person.setLastName(rs.getString("Last_name").trim());
                    person.setLogin(rs.getString("Login").trim());

                    person.setAvatar(new Image(getAvatar(rs.getInt("ID"))));
                } catch (NullPointerException e) {
                    person.setAvatar(new Image("/images/service/person.png"));
                }

                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }


        return list;
    }

    public static List<Integer> getListIDsFriends(int yourID) {
        List<Integer> list = new ArrayList<>();

        try {
            queryString = "SELECT Friends FROM Persons WHERE ID = " + yourID;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();
            try {
                StringTokenizer token = new StringTokenizer(rs.getString("Friends"), " , ");

                while (token.hasMoreElements()) {
                    list.add(Integer.parseInt(token.nextToken()));
                }
            } catch (NullPointerException e) {
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }

        return list;
    }

    public static List<PersonOther> getYourInvitations(int yourID) {
        List<PersonOther> list = new ArrayList<>();

        try {
            queryString = "SELECT Invited_friends FROM Persons WHERE ID = " + yourID;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();

            PersonOther person;
            StringTokenizer token;
            try {
                token = new StringTokenizer(rs.getString("Invited_friends"), " , ");
            } catch (NullPointerException e) {
                return list;
            }

            while (token.hasMoreElements()) {
                person = new PersonOther();
                int ID = Integer.parseInt(token.nextToken().trim());
                queryString = "SELECT ID, Name, Last_name, Login FROM Persons WHERE ID = " + ID;
                rs = statement.executeQuery(queryString);
                rs.next();
                try {
                    person.setID(rs.getInt("ID"));
                    person.setName(rs.getString("Name").trim());
                    person.setLastName(rs.getString("Last_name").trim());
                    person.setLogin(rs.getString("Login").trim());

                    person.setAvatar(new Image(getAvatar(rs.getInt("ID"))));
                } catch (NullPointerException e) {
                    person.setAvatar(new Image("/images/service/person.png"));
                }

                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }


        return list;
    }

    public static List<Integer> getListIDsYourInvitations(int yourID) {
        List<Integer> list = new ArrayList<>();

        try {
            queryString = "SELECT Invited_friends FROM Persons WHERE ID = " + yourID;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();
            try {
                StringTokenizer token = new StringTokenizer(rs.getString("Invited_friends"), " , ");

                while (token.hasMoreElements()) {
                    list.add(Integer.parseInt(token.nextToken()));
                }
            } catch (NullPointerException e) {
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }

        return list;
    }

    public static List<PersonOther> getInvitations(int yourID) {
        List<PersonOther> list = new ArrayList<>();

        try {
            queryString = "SELECT ID, Name, Last_name, Login, Invited_friends, Avatar FROM Persons";
            ResultSet rs = statement.executeQuery(queryString);

            PersonOther person;
            while (rs.next()) {
                person = new PersonOther();
                if (Validation.isYourTextInTextWithCommas(String.valueOf(yourID),
                        rs.getString("Invited_friends"))) {

                    person.setID(rs.getInt("ID"));
                    person.setName(rs.getString("Name").trim());
                    person.setLastName(rs.getString("Last_name").trim());
                    person.setLogin(rs.getString("Login").trim());

                    Image image = null;
                    try {
                        image = new Image(rs.getBinaryStream("Avatar"));
                    } catch (NullPointerException e) { /* empty */ }

                    if (image == null) {
                        person.setAvatar(new Image("/images/service/person.png"));
                    } else {
                        System.out.println("1");
                        System.out.println("2");
                        person.setAvatar(image);
                        System.out.println("3");
                    }

                    list.add(person);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }

        return list;
    }

    public static List<Integer> getListIDsInvitations(int yourID) {
        List<Integer> list = new ArrayList<>();

        try {
            queryString = "SELECT ID, Name, Last_name, Login, Invited_friends FROM Persons";
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next()) {
                if (Validation.isYourTextInTextWithCommas(String.valueOf(yourID),
                        rs.getString("Invited_friends"))) {
                    list.add(Integer.parseInt(rs.getString("ID")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }

        return list;
    }

    public static boolean addFriend(int yourID, int ID, List<Integer> listYourFriends) {
        listYourFriends.add(ID);

        try {
            queryString = "SELECT Friends FROM Persons WHERE ID = " + yourID;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();

            preparedStatement = conn.prepareStatement("UPDATE Persons SET Friends = ? WHERE ID = ?");
            if (rs.getString("Friends") != null || rs.getString("Friends").equals(null))
                preparedStatement.setString(1, String.valueOf(ID));
            else
                preparedStatement.setString(1, rs.getString("Friends") + " , " + String.valueOf(ID));
            preparedStatement.setInt(2, yourID);

            preparedStatement.executeUpdate();


            queryString = "SELECT Friends FROM Persons WHERE ID = " + ID;
            rs = statement.executeQuery(queryString);
            rs.next();

            preparedStatement = conn.prepareStatement("UPDATE Persons SET Friends = ? WHERE ID = ?");
            if (rs.getString("Friends") == null || rs.getString("Friends").equals(null))
                preparedStatement.setString(1, String.valueOf(yourID));
            else
                preparedStatement.setString(1, rs.getString("Friends") + " , " + String.valueOf(yourID));
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();

            declineInivitation(yourID, ID);
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean addInvitedFriends(int ID, int yourID, List<Integer> listYourInvitations) {
        listYourInvitations.add(ID);
        String textWitdIDs = null;

        for (int i = 0; i < listYourInvitations.size(); ++i) {
            if (textWitdIDs == null)
                textWitdIDs = String.valueOf(listYourInvitations.get(i));
            else
                textWitdIDs += " , " + listYourInvitations.get(i);
        }

        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Invited_friends = ? WHERE ID = ?");
            preparedStatement.setString(1, textWitdIDs);
            preparedStatement.setInt(2, yourID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean deleteInvitedFriends(int ID, int yourID, List<Integer> listYourInvitations) {
        listYourInvitations.remove((Object) ID);
        String textWitdIDs = null;

        for (int i = 0; i < listYourInvitations.size(); ++i) {
            if (textWitdIDs == null)
                textWitdIDs = String.valueOf(listYourInvitations.get(i));
            else
                textWitdIDs += " , " + listYourInvitations.get(i);
        }

        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Invited_friends = ? WHERE ID = ?");
            preparedStatement.setString(1, textWitdIDs);
            preparedStatement.setInt(2, yourID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean deleteFriends(int ID, int yourID, List<Integer> listYourFriends) {
        listYourFriends.remove((Object) ID);
        String textWitdIDs = null;

        for (int i = 0; i < listYourFriends.size(); ++i) {
            if (textWitdIDs == null)
                textWitdIDs = String.valueOf(listYourFriends.get(i));
            else
                textWitdIDs += " , " + listYourFriends.get(i);
        }

        try {
            preparedStatement = conn.prepareStatement("UPDATE Persons SET Friends = ? WHERE ID = ?");
            preparedStatement.setString(1, textWitdIDs);
            preparedStatement.setInt(2, yourID);

            preparedStatement.executeUpdate();

            queryString = "SELECT Friends FROM Persons WHERE ID = " + ID;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();

            preparedStatement = conn.prepareStatement("UPDATE Persons SET Friends = ? WHERE ID = ?");
            preparedStatement.setString(1,
                    Validation.deleteMyTextWithTextWithCommas(String.valueOf(yourID), rs.getString("Friends")));
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static String countFriends(int ID) {
        int count = 0;
        try {
            queryString = "SELECT Friends FROM Persons WHERE ID = " + ID;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();

            try {
                StringTokenizer token = new StringTokenizer(rs.getString("Friends"), " , ");

                while (token.hasMoreElements()) {
                    ++count;
                    token.nextToken();
                }
            } catch (NullPointerException e) {
                count = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return "";
        }

        return String.valueOf(count);
    }

    public static String countYourInvitations(int ID) {
        int count = 0;
        try {
            queryString = "SELECT Invited_friends FROM Persons WHERE ID = " + ID;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();

            try {
                StringTokenizer token = new StringTokenizer(rs.getString("Invited_friends"), " , ");

                while (token.hasMoreElements()) {
                    ++count;
                    token.nextToken();
                }
            } catch (NullPointerException e) {
                count = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return "";
        }

        return String.valueOf(count);
    }

    public static String countInvitations(int ID) {
        int count = 0;
        try {
            queryString = "SELECT Invited_friends FROM Persons";
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next()) {
                if (Validation.isYourTextInTextWithCommas(String.valueOf(ID),
                        rs.getString("Invited_friends"))) {
                    ++count;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return "";
        }

        return String.valueOf(count);
    }

    public static boolean declineInivitation(int yourID, int ID) {
        try {
            queryString = "SELECT Invited_friends FROM Persons WHERE ID = " + ID;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();

            String textWithIDs = Validation.deleteMyTextWithTextWithCommas(String.valueOf(yourID),
                    rs.getString("Invited_friends").trim());

            if (textWithIDs == null || textWithIDs.equals(null)) {
                textWithIDs = "";
            }

            queryString = "UPDATE Persons SET Invited_friends = '" + textWithIDs + "' WHERE ID = " + ID;
            statement.executeUpdate(queryString);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    private static int setIDPost() {
        int count = 0;

        try {
            queryString = "SELECT MAX(ID) ID FROM Posts";
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();
            count = Integer.parseInt(rs.getString("ID")) + 1;
        } catch (NumberFormatException e) {
            count = 1;
        } catch (SQLException e) {
            e.printStackTrace();

            return count;
        }

        return count;
    }

    public static boolean addPost(int yourID, String text, boolean postOnMyBoard) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO Posts(ID, ID_Author, Text, On_my_board, Date) " +
                    "VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, setIDPost());
            preparedStatement.setInt(2, yourID);
            preparedStatement.setString(3, text);
            if (postOnMyBoard)
                preparedStatement.setInt(4, 1);
            else
                preparedStatement.setInt(4, 0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY  HH:mm:ss", Locale.getDefault());
            preparedStatement.setString(5, dateFormat.format(new Date()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean addPostOnBoardPersonOther(int yourID, int recipientID, String text, boolean postOnMyBoard) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO Posts(ID, ID_Author, ID_Recipient, Text, On_my_board, Date) " +
                    "VALUES (?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, setIDPost());
            preparedStatement.setInt(2, yourID);
            preparedStatement.setInt(3, recipientID);
            preparedStatement.setString(4, text);
            if (postOnMyBoard)
                preparedStatement.setInt(5, 1);
            else
                preparedStatement.setInt(5, 0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY  HH:mm:ss", Locale.getDefault());
            preparedStatement.setString(6, dateFormat.format(new Date()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static List<Integer> getNumberOfPosts(int ID, List<Integer> listFriends) {
        List<Integer> list = new ArrayList<>();

        list.addAll(getNumberOfPostsForPerson(ID));

        for (int i = 0; i < listFriends.size(); ++i) {
            List<Integer> listPostsOfFriends;
            listPostsOfFriends = getNumberOfPostsForPerson(listFriends.get(i));

            for (int j = 0; j < listPostsOfFriends.size(); ++j) {
                if (!(list.contains(listPostsOfFriends.get(j))))
                    list.add(listPostsOfFriends.get(j));
            }
        }

        Collections.sort(list);

        return list;
    }

    public static List<Integer> getNumberOfPostsForPerson(int ID) {
        List<Integer> list = new ArrayList<>();
        try {
            queryString = "SELECT ID FROM Posts WHERE ID_Author = " + ID + " OR ID_Recipient = " + ID;
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next())
                list.add(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Collections.sort(list);

        return list;
    }

    public static List<Integer> getNumberOfPostsMyFriends(List<Integer> listFriends) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < listFriends.size(); ++i) {
            List<Integer> listPostsOfFriends;
            listPostsOfFriends = getNumberOfPostsForPerson(listFriends.get(i));

            for (int j = 0; j < listPostsOfFriends.size(); ++j) {
                if (!(list.contains(listPostsOfFriends.get(j))))
                    list.add(listPostsOfFriends.get(j));
            }
        }

        Collections.sort(list);

        return list;
    }

    public static List<Integer> getNumberOfPostsForPersonOnBoard(int ID) {
        List<Integer> list = new ArrayList<>();
        try {
            queryString = "SELECT ID FROM Posts WHERE ID_Author = " + ID + " OR ID_Recipient = " + ID + " AND On_my_board = 1";
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next())
                list.add(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Collections.sort(list);

        return list;
    }

    public static List<Integer> getNumberOfAllPosts() {
        List<Integer> list = new ArrayList<>();
        try {
            queryString = "SELECT ID FROM Posts";
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next())
                list.add(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Collections.sort(list);

        return list;
    }

    public static List<Post> getPosts(List<Integer> listIDsPosts) {
        List<Post> list = new ArrayList<>();
        int count = listIDsPosts.size() - 1;

        for (int i = 0; i < 10; ++i) {
            try {
                if (listIDsPosts.size() == 0)
                    break;

                queryString = "SELECT ID, ID_Author, ID_Recipient, Text, On_my_board, Date, Number_of_likes, Who_likes, Visible FROM Posts WHERE ID = " + listIDsPosts.get(count);
                ResultSet rs = statement.executeQuery(queryString);
                rs.next();

                if (rs.getRow() == 0)
                    break;

                if (rs.getString("Visible") == null
                        || rs.getString("Visible").equals("true")) {
                    Post post = new Post();
                    post.setID(rs.getInt("ID"));
                    post.setID_Author(rs.getInt("ID_Author"));
                    post.setID_Recipient(rs.getInt("ID_Recipient"));
                    post.setText(rs.getString("Text"));
                    if (rs.getInt("On_my_board") == 1 && rs.getString("ID_Recipient") != null)
                        post.setOnMyBoard(true);
                    else
                        post.setOnMyBoard(false);
                    post.setDate(rs.getString("Date"));
                    post.setNumberOfLikes(rs.getInt("Number_of_likes"));
                    List<Integer> listWhoLikes = Validation.separateTextOnListIDs(rs.getString("Who_likes"));
                    if (listWhoLikes != null)
                        post.setWhoLikes(listWhoLikes);
                    else {
                        List<Integer> listWhoLikesWhenIsNull = new ArrayList<>();
                        post.setWhoLikes(listWhoLikesWhenIsNull);
                    }

                    list.add(post);
                }

                listIDsPosts.remove(listIDsPosts.size() - 1);
                --count;
            } catch (SQLException e) {
                e.printStackTrace();

                return list;
            }
        }

        return list;
    }

    public static boolean likePost(int IDPost, int yourID) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Posts SET Number_of_likes = ? WHERE ID = ?");
            preparedStatement.setInt(1, (getNumberOfLikes(IDPost) + 1));
            preparedStatement.setInt(2, IDPost);

            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("UPDATE Posts SET Who_likes = ? WHERE ID = ?");
            preparedStatement.setString(1, Validation.changeListIntegerOnTextWithCommasWithMyInteger(yourID,
                    getWhoLikes(IDPost)));
            preparedStatement.setInt(2, IDPost);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean unlikePost(int IDPost, int yourID) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Posts SET Number_of_likes = ? WHERE ID = ?");
            preparedStatement.setInt(1, (getNumberOfLikes(IDPost) - 1));
            preparedStatement.setInt(2, IDPost);

            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("UPDATE Posts SET Who_likes = ? WHERE ID = ?");
            preparedStatement.setString(1, Validation.deleteMyTextWithTextWithCommas(String.valueOf(yourID),
                    Validation.changeListIntegersOnTextWithCommas(getWhoLikes(IDPost))));
            preparedStatement.setInt(2, IDPost);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static int getNumberOfLikes(int IDPost) {
        try {
            queryString = "SELECT Number_of_likes FROM Posts WHERE ID = " + IDPost;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();

            return rs.getInt("Number_of_likes");
        } catch (SQLException e) {
            e.printStackTrace();

            return 0;
        }
    }

    public static List<Integer> getWhoLikes(int IDPost) {
        List<Integer> listWhoLikes;

        try {
            queryString = "SELECT Who_likes FROM Posts WHERE ID = " + IDPost;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();

            listWhoLikes = Validation.separateTextOnListIDs(rs.getString("Who_likes"));

            if (listWhoLikes == null)
                listWhoLikes = new ArrayList<>();

            return listWhoLikes;
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }

    private static int setIDReport() {
        int count = 0;

        try {
            queryString = "SELECT MAX(ID) ID FROM Reports";
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();
            count = Integer.parseInt(rs.getString("ID")) + 1;
        } catch (NumberFormatException e) {
            count = 1;
        } catch (SQLException e) {
            e.printStackTrace();

            return count;
        }

        return count;
    }

    public static boolean sendReportPost(int IDPost, String reason) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO Reports(ID, ID_Post, Reason, Date) " +
                    "VALUES (?, ?, ?, ?)");

            preparedStatement.setInt(1, setIDReport());
            preparedStatement.setInt(2, IDPost);
            preparedStatement.setString(3, reason);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY  HH:mm:ss", Locale.getDefault());
            preparedStatement.setString(4, dateFormat.format(new Date()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static List<Integer> getReportsPost() {
        List<Integer> list = new ArrayList<>();

        try {
            queryString = "SELECT ID, ID_Post FROM Reports";
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next()) {
                list.add(rs.getInt("ID_Post"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }

        return list;
    }

    private static int setIDComment() {
        int count = 0;

        try {
            queryString = "SELECT MAX(ID) ID FROM Comments";
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();
            count = Integer.parseInt(rs.getString("ID")) + 1;
        } catch (NumberFormatException e) {
            count = 1;
        } catch (SQLException e) {
            e.printStackTrace();

            return count;
        }

        return count;
    }

    public static boolean addComment(int yourID, int ID_Post, String text) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO Comments(ID, ID_Post, ID_Author, Contents, Date) " +
                    "VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, setIDComment());
            preparedStatement.setInt(2, ID_Post);
            preparedStatement.setInt(3, yourID);
            preparedStatement.setString(4, text);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY  HH:mm:ss", Locale.getDefault());
            preparedStatement.setString(5, dateFormat.format(new Date()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static List<Comment> getComments(int IDPost) {
        List<Comment> list = new ArrayList<>();

        try {
            queryString = "SELECT ID, ID_Post, ID_Author, Contents, Date FROM Comments WHERE ID_Post = " + IDPost;
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next()) {
                list.add(new Comment(rs.getInt("ID"), rs.getInt("ID_Post"),
                        rs.getInt("ID_Author"), rs.getString("Contents"),
                        rs.getString("Date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }

        return list;
    }

    public static PersonOther getPerson(int ID) {
        queryString = "SELECT ID, Login, Password, Email, Name, Second_name, Last_name, Date_of_birth, Sex, Leading_question, Answer, About_me, Friends, Invited_friends FROM Persons WHERE ID = " + ID;
        try {
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next()) {
                PersonOther person = new PersonOther();

                person.setLogin(rs.getString("Login").trim());
                person.setEmail(rs.getString("Email").trim());

                try {
                    person.setID(Integer.valueOf(rs.getString("ID")));
                } catch (NullPointerException e) {
                    person.setID(0);
                }
                try {
                    person.setName(rs.getString("Name").trim());
                } catch (NullPointerException e) {
                    person.setName(null);
                }
                try {
                    person.setSecondName(rs.getString("Second_name").trim());
                } catch (NullPointerException e) {
                    person.setSecondName(null);
                }
                try {
                    person.setLastName(rs.getString("Last_name").trim());
                } catch (NullPointerException e) {
                    person.setLastName(null);
                }
                try {
                    person.setDateOfBirth(rs.getDate("Date_of_birth").toLocalDate());
                } catch (NullPointerException e) {
                    person.setDateOfBirth("");
                }
                try {
                    person.setSex(rs.getString("Sex").trim());
                } catch (NullPointerException e) {
                    person.setSex(null);
                }
                try {
                    person.setAboutMe(rs.getString("About_me").trim());
                } catch (NullPointerException e) {
                    person.setAboutMe(null);
                }
                List<Integer> listFriends = new ArrayList<>();
                try {

                    StringTokenizer token = new StringTokenizer(rs.getString("Friends").trim(), ",");
                    while (token.hasMoreElements())
                        listFriends.add(Integer.parseInt(token.nextToken().trim()));
                    person.setFriends(listFriends);
                } catch (NullPointerException e) {
                    person.setFriends(listFriends);
                }

                return person;
            }


        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }

        return null;
    }

    private static int setIDNotes() {
        int count = 0;

        try {
            queryString = "SELECT MAX(ID) ID FROM Notes";
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();
            count = Integer.parseInt(rs.getString("ID")) + 1;
        } catch (NumberFormatException e) {
            count = 1;
        } catch (SQLException e) {
            e.printStackTrace();

            return count;
        }

        return count;
    }

    public static boolean addNotes(int yourID, String title, String contents) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO Notes(ID, ID_Author, Title, Contents, Date) " +
                    "VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, setIDNotes());
            preparedStatement.setInt(2, yourID);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, contents);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY  HH:mm:ss", Locale.getDefault());
            preparedStatement.setString(5, dateFormat.format(new Date()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean deleteNotes(int IDNotes) {
        try {
            preparedStatement = conn.prepareStatement("DELETE FROM Notes WHERE ID = ?");
            preparedStatement.setInt(1, IDNotes);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean clearListNotes(List<Integer> lisIDsNotes) {
        try {
            for (Integer ID : lisIDsNotes) {
                preparedStatement = conn.prepareStatement("DELETE FROM Notes WHERE ID = ?");
                preparedStatement.setInt(1, ID);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean updateNotes(int ID, String title, String contents) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE Notes SET Title = ? WHERE ID = ?");
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("UPDATE Notes SET Contents = ? WHERE ID = ?");
            preparedStatement.setString(1, contents);
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("UPDATE Notes SET Date = ? WHERE ID = ?");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY  HH:mm:ss", Locale.getDefault());
            preparedStatement.setString(1, dateFormat.format(new Date()));
            preparedStatement.setInt(2, ID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static ObservableList<TreeItem<Notes>> getNotes(int ID) {
        ObservableList<TreeItem<Notes>> observableList = FXCollections.observableArrayList();
        Notes notes;
        TreeItem<Notes> treeItem;

        try {
            queryString = "SELECT ID, ID_Author, Title, Contents, Date FROM Notes WHERE ID_Author = " + ID;
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next()) {
                notes = new Notes(rs.getInt("ID"),
                        rs.getInt("ID_Author"),
                        rs.getString("Title").trim(),
                        rs.getString("Contents").trim(),
                        rs.getString("Date").trim());

                treeItem = new TreeItem<>(notes);

                observableList.add(treeItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return observableList;
        }

        return observableList;
    }

    private static int setIDLogger() {
        int count = 0;

        try {
            queryString = "SELECT MAX(ID) ID FROM Logger";
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();
            count = Integer.parseInt(rs.getString("ID")) + 1;
        } catch (NumberFormatException e) {
            count = 1;
        } catch (SQLException e) {
            e.printStackTrace();

            return count;
        }

        return count;
    }

    public static boolean addLogger(int yourID, String contents) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO Logger(ID, ID_Author, Contents, Date) " +
                    "VALUES (?, ?, ?, ?)");

            preparedStatement.setInt(1, setIDLogger());
            preparedStatement.setInt(2, yourID);
            preparedStatement.setString(3, contents);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY  HH:mm:ss", Locale.getDefault());
            preparedStatement.setString(4, dateFormat.format(new Date()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static List<MyLogger> getLoggers(int ID) {
        List<MyLogger> listLoggers = new ArrayList<>();
        try {
            queryString = "SELECT Contents, Date FROM Logger WHERE ID_Author = " + ID;
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next()) {
                listLoggers.add(new MyLogger(rs.getString("Contents"),
                        rs.getString("Date")));
            }

            Collections.reverse(listLoggers);
        } catch (SQLException e) {
            e.printStackTrace();

            return listLoggers;
        }

        return listLoggers;
    }

    private static int setIDEvent() {
        int count = 0;

        try {
            queryString = "SELECT MAX(ID) ID FROM Events";
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();
            count = Integer.parseInt(rs.getString("ID")) + 1;
        } catch (NumberFormatException e) {
            count = 1;
        } catch (SQLException e) {
            e.printStackTrace();

            return count;
        }

        return count;
    }

    public static boolean addEvent(int ID_Author, String title, String dateStart, String timeStart,
                                   String dateFinish, String timeFinish, String localization,
                                   String description) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO Events(ID, ID_Author, Title, Localization, Description, Date_start, Time_start, Date_finish, Time_finish, Rate, Number_of_ratings, Who_rating) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, setIDEvent());
            preparedStatement.setInt(2, ID_Author);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, localization);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, dateStart);
            preparedStatement.setString(7, timeStart);
            preparedStatement.setString(8, dateFinish);
            preparedStatement.setString(9, timeFinish);
            preparedStatement.setInt(10, 0);
            preparedStatement.setInt(11, 0);
            preparedStatement.setString(12, "");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static List<Integer> getNumberOfEventsMyAndMyFriends(int ID, List<Integer> listFriends) {
        List<Integer> list = new ArrayList<>();

        list.addAll(getNumberOfEventsForPerson(ID));

        for (int i = 0; i < listFriends.size(); ++i) {
            List<Integer> listPostsOfFriends;
            listPostsOfFriends = getNumberOfEventsForPerson(listFriends.get(i));

            for (int j = 0; j < listPostsOfFriends.size(); ++j) {
                if (!(list.contains(listPostsOfFriends.get(j))))
                    list.add(listPostsOfFriends.get(j));
            }
        }

        Collections.sort(list);

        return list;
    }

    private static List<Integer> getNumberOfEventsForPerson(int ID) {
        List<Integer> list = new ArrayList<>();
        try {
            queryString = "SELECT ID FROM Events WHERE ID_Author = " + ID;
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next())
                list.add(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<Integer> getNumberOfAllEvents() {
        List<Integer> list = new ArrayList<>();
        try {
            queryString = "SELECT ID FROM Events";
            ResultSet rs = statement.executeQuery(queryString);

            while (rs.next())
                list.add(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();

            return list;
        }

        Collections.sort(list);

        return list;
    }

    public static List<Event> getEvents(List<Integer> listIDsEvents) {
        List<Event> list = new ArrayList<>();
        int count = listIDsEvents.size() - 1;

        for (int i = 0; i < 10; ++i) {
            try {
                if (listIDsEvents.size() == 0)
                    break;

                queryString = "SELECT ID, ID_Author, Title, Localization, Description, Date_start, Time_start, Date_finish, Time_finish FROM Events WHERE ID = " + listIDsEvents.get(count);
                ResultSet rs = statement.executeQuery(queryString);
                rs.next();

                if (rs.getRow() == 0)
                    break;

                Event event = new Event();
                event.setID(rs.getInt("ID"));
                event.setID_Author(rs.getInt("ID_Author"));
                event.setTitle(rs.getString("Title"));
                event.setLocalization(rs.getString("Localization"));
                event.setDescription(rs.getString("Description"));
                event.setDateStart(rs.getString("Date_start"));
                event.setTimeStart(rs.getString("Time_start"));
                event.setDateFinish(rs.getString("Date_finish"));
                event.setTimeFinish(rs.getString("Time_finish"));

                list.add(event);

                listIDsEvents.remove(listIDsEvents.size() - 1);
                --count;
            } catch (SQLException e) {
                e.printStackTrace();

                return list;
            }
        }

        return list;
    }

    private static int setIDRateOnEvent() {
        int count = 0;

        try {
            queryString = "SELECT MAX(ID) ID FROM Ratings_of_events";
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();
            count = Integer.parseInt(rs.getString("ID")) + 1;
        } catch (NumberFormatException e) {
            count = 1;
        } catch (SQLException e) {
            e.printStackTrace();

            return count;
        }

        return count;
    }

    public static boolean addRateOnEvent(int IDEvent, int yourID, float rate) {
        try {
            queryString = "SELECT Who_rating FROM Events WHERE ID = " + IDEvent;
            ResultSet rs = statement.executeQuery(queryString);
            rs.next();

            if (Validation.isYourTextInTextWithCommas(String.valueOf(yourID),
                    rs.getString("Who_rating"))) {
                changeRateOnEvent(IDEvent, yourID, rate);

                return true;
            }

            {
                queryString = "SELECT Rate, Number_of_ratings, Who_rating FROM Events WHERE ID = " + IDEvent;
                rs = statement.executeQuery(queryString);
                rs.next();

                float newRate = rs.getFloat("Rate") + rate;
                int newNumberOfRating = rs.getInt("Number_of_ratings") + 1;

                if (rs.getString("Who_rating") == null
                        || rs.getString("Who_rating").equals("")) {
                    queryString = "UPDATE Events SET Who_rating = '" + yourID + "' WHERE ID = " + IDEvent;
                    statement.executeUpdate(queryString);
                } else {
                    preparedStatement = conn.prepareStatement("UPDATE Events SET Who_rating = ? WHERE ID = ?");

                    preparedStatement.setString(1, rs.getString("Who_rating") + " , " + yourID);
                    preparedStatement.setInt(2, IDEvent);

                    preparedStatement.executeUpdate();
                }
                queryString = "UPDATE Events SET Rate = " + newRate / newNumberOfRating + " WHERE ID = " + IDEvent;
                statement.executeUpdate(queryString);
                queryString = "UPDATE Events SET Number_of_ratings = " + newNumberOfRating + " WHERE ID = " + IDEvent;
                statement.executeUpdate(queryString);
            }
            {
                preparedStatement = conn.prepareStatement("INSERT INTO Ratings_of_events(ID, ID_Event, ID_Author, Rate) " +
                        "VALUES (?, ?, ?, ?)");

                preparedStatement.setInt(1, setIDRateOnEvent());
                preparedStatement.setInt(2, IDEvent);
                preparedStatement.setInt(3, yourID);
                preparedStatement.setFloat(4, rate);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    private static boolean changeRateOnEvent(int IDEvent, int yourID, float rate) {
        try {
            preparedStatement = conn.prepareStatement("SELECT Rate, Number_of_ratings, Who_rating FROM Events WHERE ID = " + IDEvent);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            float newRate;
            newRate = ((rs.getFloat("Rate") - getMyRateForEvent(IDEvent, yourID)) + rate);
            int newNumberOfRating = rs.getInt("Number_of_ratings");

            queryString = "UPDATE Events SET Rate = " + (newRate / newNumberOfRating) + " WHERE ID = " + IDEvent;
            statement.executeUpdate(queryString);

            preparedStatement = conn.prepareStatement("UPDATE Ratings_of_events SET Rate = ? WHERE ID = ? AND ID_Author = ?");

            preparedStatement.setFloat(1, newRate);
            preparedStatement.setInt(2, IDEvent);
            preparedStatement.setInt(3, yourID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static float getRateOfEvent(int IDEvent) {
        ResultSet rs;
        try {
            queryString = "SELECT Rate FROM Events WHERE ID = " + IDEvent;
            rs = statement.executeQuery(queryString);
            rs.next();

            return rs.getFloat("Rate");
        } catch (SQLException e) {
            e.printStackTrace();

            return 0f;
        }
    }

    public static float getMyRateForEvent(int IDEvent, int yourID) {
        ResultSet rs;
        try {
            queryString = "SELECT Rate FROM Ratings_of_events WHERE ID = " + IDEvent + " AND ID_Author = " + yourID;
            rs = statement.executeQuery(queryString);
            rs.next();

            if(rs.getRow() == 0)
                return 0f;

            return rs.getFloat("Rate");
        } catch (SQLException e) {
            e.printStackTrace();

            return 0f;
        }
    }

    public static String getDatabaseName() {
        try {
            return conn.getCatalog();
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }


}
