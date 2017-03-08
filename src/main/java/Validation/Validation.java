package Validation;

/**
 * static public boolean checkLogin(String login);
 * static public boolean checkLengthLogin(String login);
 * static public boolean checkFirstCharacterLogin(String login);
 * static public boolean checkEmail(String email);
 * static public boolean checkPassword(String password);
 * static public boolean checkRepeatPassword(String password, String repeatPassword);
 * static public boolean checkLengthName(String name);
 * static public boolean checkQuestion(String question, String answer);
 * static public boolean checkLengthQuestion(String question);
 * static public boolean checkAnswer(String question, String answer);
 * static public boolean checkLengthAnswer(String answer)
 * static public boolean isComma(String text);
 * static public boolean checkCAPTCHA(String CAPTCHA, String yourCAPTCHA);
 * static public int setPointValueForCharacter(char oldChar, char newChar);
 * static public int setPointValueForPassword(String password);
 * static public String changePasswordForStars(String password);
 * static public <V>boolean isTextOnList(String text, List<V> list);
 * static public boolean isIDPersonOnList(List<PersonOnList> list, PersonOnList personOnList);
 * static public String deleteLoginsAndEmailsWithTextByID(String text, List<PersonOnList> listPersonOnList);
 * static public String findLoginThatDoesNotExistInDatabase(String textWithCommas);
 * static public String findOnListYourLogin(Person person, String textWithCommas) ;
 * static public String findRepetitionInTextWithCommas(String textWithCommas) {;
 * static public boolean isYourTextInTextWithCommas(String yourText, String textWithCommas);
 * static public int countWordsInTextWithCommas(String textWithCommas) {;
 * static public String changeTextWithCommasOnTextWithPersonID(String textWithCommas);
 * static public <V>boolean changeTextOnList(String text, List<V> list);
 * static public ObservableList<TreeItem<MessageReadAndReceived>> giveMeListReadReceivedMessagesWithGivenIndex(String option, String index,ObservableList<TreeItem<MessageReadAndReceived>> observableList);
 * static public ObservableList<TreeItem<MessageSent>> giveMeListSentMessagesWithGivenIndex(String option, String index,ObservableList<TreeItem<MessageSent>> observableList)
 * static public ObservableList<TreeItem<MessageWorkingCopy>> giveMeListWorkingCopyWithGivenIndex(String option, String index,ObservableList<TreeItem<MessageWorkingCopy>> observableList)
 * static public ObservableList<TreeItem<MessageTrash>> giveMeListTrashMessagesWithGivenIndex(String option, String index,ObservableList<TreeItem<MessageTrash>> observableList)
 * static public String deleteMyTextWithTextWithCommas(String myText, String textWithCommas);
 * static public List<Integer> separateTextOnListIDs(String textWithCommas);
 * static public String changeListIntegerOnTextWithCommasWithMyInteger(int ID, List<Integer> list);
 * static public String changeListIntegersOnTextWithCommas(List<Integer> list);
 * static public ObservableList<TreeItem<Notes>> giveMeListNotesWithGivenIndex(String index, ObservableList<TreeItem<Notes>> observableList);
 */

import Database.Database;
import Service.Message.MessageReadAndReceived;
import Service.Message.MessageSent;
import Service.Message.MessageTrash;
import Service.Message.MessageWorkingCopy;
import Service.Notes.Notes;
import Service.Person.Person;
import Service.Person.PersonOnList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Jonatan on 2016-12-22.
 */
public class Validation {

    ////////////////////////////////////// LOGIN ////////////////////////////////////////////
    static public boolean checkLogin(String login) {
        if (login.length() < 6 || login.length() > 36) {
            return false;
        } else if (!Character.isLetter(login.charAt(0))) {
            return false;
        } else if (login.indexOf(",") != -1) {
            return false;
        }

        return true;
    }

    static public boolean checkLengthLogin(String login) {
        if (login.length() < 6 || login.length() > 36) {
            return false;
        }

        return true;
    }

    static public boolean checkFirstCharacterLogin(String login) {
        try {
            if (!Character.isLetter(login.charAt(0))) {
                return false;
            }
        } catch (StringIndexOutOfBoundsException e) {
            return false;
        }

        return true;
    }

    ////////////////////////////////////// E-MAIL /////////////////////////////////////////
    static public boolean checkEmail(String email) {
        if (email.indexOf("@") > email.lastIndexOf(".")
                || email.lastIndexOf(".") > email.length() - 2
                || email.indexOf("@") == -1
                || email.indexOf(".") == -1
                || email.indexOf("@") + 1 == email.indexOf(".")) {
            return false;
        } else if (email.length() > 255) {
            return false;
        }

        return true;
    }

    static public boolean checkLengthEmail(String email) {
        if (email.length() > 255)
            return false;

        return true;
    }

    ////////////////////////////////////// PASSWORD //////////////////////////////////////
    static public boolean checkPassword(String password) {
        if (password.length() < 6 || password.length() > 36)
            return false;

        return true;
    }

    static public boolean checkRepeatPassword(String password, String repeatPassword) {
        if (!(password.equals(repeatPassword))) {
            return false;
        }

        return true;
    }

    ////////////////////////////////////// NAME //////////////////////////////////////////
    static public boolean checkLengthName(String name) {
        if (name.length() < 1 || name.length() > 255)
            return false;

        return true;
    }

    ////////////////////////////////////// SECOND NAME ///////////////////////////////////
    static public boolean checkLengthSecondName(String secondName) {
        if (secondName.length() > 255)
            return false;

        return true;
    }

    ////////////////////////////////////// LAST NAME /////////////////////////////////////
    static public boolean checkLengthLastName(String lastName) {
        if (lastName.length() > 255)
            return false;

        return true;
    }

    ////////////////////////////////////// QUESTION //////////////////////////////////////
    static public boolean checkQuestion(String question, String answer) {
        if (question.length() == 0 && answer.length() > 0) {
            return false;
        }

        return true;
    }

    static public boolean checkLengthQuestion(String question) {
        if (question.length() > 255) {
            return false;
        }

        return true;
    }

    ////////////////////////////////////// ANSWER ////////////////////////////////////////
    static public boolean checkAnswer(String question, String answer) {
        if (question.length() > 0 && answer.length() == 0) {
            return false;
        }

        return true;
    }

    static public boolean checkLengthAnswer(String answer) {
        if (answer.length() > 255) {
            return false;
        }

        return true;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////


    static public boolean isComma(String text) {
        if (text.indexOf(",") != -1) {
            return true;
        }

        return false;
    }

    static public boolean findSpecialCharacter(String text) {
        int count = 0;

        for (int i = 0; i < text.length(); ++i) {
            if (Character.isLetter(text.charAt(i)) || Character.isDigit(text.charAt(i)))
                count++;
        }

        if (count == text.length())
            return false;

        return true;
    }

    static public boolean checkCAPTCHA(String CAPTCHA, String yourCAPTCHA) {
        if (!(CAPTCHA.equals(yourCAPTCHA))) {
            return false;
        }

        return true;
    }

    static public int setPointValueForCharacter(char oldChar, char newChar) {
        if (Character.isLetter(oldChar)) {
            if (Character.isLetter(newChar)) {
                return 1;
            } else if (Character.isDigit(newChar)) {
                return 2;
            } else if ((!Character.isLetter(newChar)) && !(Character.isDigit(newChar))) {
                return 3;
            }
        } else if (Character.isDigit(oldChar)) {
            if (Character.isDigit(newChar)) {
                return 1;
            } else if (Character.isLetter(newChar)) {
                return 2;
            } else if (!Character.isDigit(newChar) && !Character.isLetter(newChar)) {
                return 3;
            }
        } else if (!Character.isLetter(oldChar) && !Character.isDigit(oldChar)) {
            if (!Character.isLetter(newChar) && !Character.isDigit(newChar)) {
                return 1;
            } else if (Character.isDigit(newChar)) {
                return 2;
            } else if (Character.isLetter(newChar)) {
                return 2;
            }
        }

        return 0;
    }

    static public int setPointValueForPassword(String password) {
        int strengthPassword = 2;

        if (password.length() > 0) {
            for (int i = 0, j = 1; j < password.length(); ++i, ++j) {
                if (Character.isLetter(password.charAt(i))) {
                    if (Character.isLetter(password.charAt(j))) {
                        strengthPassword += 1;
                    } else if (Character.isDigit(password.charAt(j))) {
                        strengthPassword += 2;
                    } else if ((!Character.isLetter(password.charAt(j))) && !(Character.isDigit(password.charAt(j)))) {
                        strengthPassword += 3;
                    }
                } else if (Character.isDigit(password.charAt(i))) {
                    if (Character.isDigit(password.charAt(j))) {
                        strengthPassword += 1;
                    } else if (Character.isLetter(password.charAt(j))) {
                        strengthPassword += 2;
                    } else if (!Character.isDigit(password.charAt(j)) && !Character.isLetter(password.charAt(j))) {
                        strengthPassword += 3;
                    }
                } else if (!Character.isLetter(password.charAt(i)) && !Character.isDigit(password.charAt(i))) {
                    if (!Character.isLetter(password.charAt(j)) && !Character.isDigit(password.charAt(j))) {
                        strengthPassword += 1;
                    } else if (Character.isDigit(password.charAt(j))) {
                        strengthPassword += 2;
                    } else if (Character.isLetter(password.charAt(j))) {
                        strengthPassword += 2;
                    }
                }
            }
        }

        return strengthPassword;
    }

    static public String changePasswordForStars(String password) {
        String starsPassword = null;

        for (int i = 0; i < password.length(); ++i) {
            if (i == 0) {
                starsPassword = "*";
                continue;
            }

            if (i == password.length() - 1) {
                starsPassword += password.charAt(password.length() - 1);
                break;
            }

            starsPassword += "*";
        }

        return starsPassword;
    }

    static public <V> boolean isTextOnList(String text, List<V> list) {
        for (int i = 0; i < list.size(); ++i) {
            try {
                if (list.get(i).toString().toLowerCase().trim().equals(text.toLowerCase().trim()))
                    return true;
            } catch (NullPointerException e) {
                return false;
            }
        }

        return false;
    }

    static public boolean isIDPersonOnList(List<PersonOnList> list, PersonOnList personOnList) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getID() == personOnList.getID())
                return true;
        }

        return false;
    }

    static public String deleteLoginsAndEmailsWithTextByID(String textWithCommas, List<PersonOnList> listPersonOnList) {
        List<String> list = new ArrayList<String>();
        StringTokenizer token = new StringTokenizer(textWithCommas, ",");

        while (token.hasMoreElements())
            list.add(token.nextToken());

        List<PersonOnList> list2 = new ArrayList<PersonOnList>();

        for (int i = 0; i < list.size(); ++i) {
            list2.add(Database.changeOnPersonOnList(list.get(i).trim()));
        }

        for (int i = 0; i < list2.size(); ++i) {
            for (int j = 0; j < listPersonOnList.size(); ++j) {
                if (list2.get(i) != null) {
                    if (list2.get(i).getID() == listPersonOnList.get(j).getID()) {
                        list2.remove(i);
                        list.remove(i);
                    }
                }
            }
        }

        String textToReturn = null;

        for (int i = 0; i < list.size(); ++i) {
            if (i == 0)
                textToReturn = list.get(i).trim();
            else
                textToReturn += " , " + list.get(i).trim();
        }

        return textToReturn;
    }

    static public String findLoginThatDoesNotExistInDatabase(String textWithCommas) {
        StringTokenizer token = new StringTokenizer(textWithCommas, ",");
        List<String> listLogins = new ArrayList<String>();
        PersonOnList personOnList;

        while (token.hasMoreElements())
            listLogins.add(token.nextToken());

        for (int i = 0; i < listLogins.size(); ++i) {
            personOnList = Database.changeOnPersonOnList(listLogins.get(i));
            if (personOnList == null)
                return listLogins.get(i).trim();
        }

        return null;
    }

    static public String findOnListYourLogin(Person person, String textWithCommas) {
        StringTokenizer token = new StringTokenizer(textWithCommas, ",");
        String textWithToken = null;

        while (token.hasMoreElements()) {
            textWithToken = token.nextToken();
            if (person.getLogin().toLowerCase().trim().equals(textWithToken.toLowerCase().trim()))
                return textWithToken;
        }

        return null;
    }

    static public String findRepetitionInTextWithCommas(String textWithCommas) {
        StringTokenizer token = new StringTokenizer(textWithCommas, ",");
        List<String> list = new ArrayList<String>();

        while (token.hasMoreElements())
            list.add(token.nextToken());

        for (int i = 0; i < list.size(); ++i) {
            for (int j = i + 1; j < list.size(); ++j) {
                if (list.get(i).toLowerCase().trim().equals(list.get(j).toLowerCase().trim()))
                    return list.get(j).toString();
            }
        }

        return null;
    }

    static public boolean isYourTextInTextWithCommas(String yourText, String textWithCommas) {
        try {
            StringTokenizer token = new StringTokenizer(textWithCommas, ",");

            while (token.hasMoreElements()) {
                if (yourText.toLowerCase().trim().equals(token.nextToken().toLowerCase().trim()))
                    return true;
            }
        } catch (NullPointerException e) {
            return false;
        }

        return false;
    }

    static public int countWordsInTextWithCommas(String textWithCommas) {
        StringTokenizer token = new StringTokenizer(textWithCommas, ",");
        int count = 0;

        while (token.hasMoreElements()) {
            token.nextToken();
            count++;
        }

        return count;
    }

    static public String changeTextWithCommasOnTextWithPersonID(String textWithCommas) {
        String textPersonsID = null;
        int count = 0;

        StringTokenizer token = new StringTokenizer(textWithCommas, ",");

        while (token.hasMoreElements()) {
            if (count == 0)
                textPersonsID = String.valueOf(Database.changeLoginOnID(token.nextToken()));
            else
                textPersonsID += " , " + String.valueOf(Database.changeLoginOnID(token.nextToken()));

            count++;
        }

        return textPersonsID;
    }

    static public <V> boolean changeTextOnList(String text, List<V> list) {
        try {
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i).toString().trim().equals(text.trim()))
                    return true;
            }
        } catch (NullPointerException e) {
            return false;
        }


        return false;
    }

    static public ObservableList<TreeItem<MessageReadAndReceived>> giveMeListReadReceivedMessagesWithGivenIndex(String option, String index,
                                                                                                                ObservableList<TreeItem<MessageReadAndReceived>> observableList) {
        ObservableList<TreeItem<MessageReadAndReceived>> truncatedList = FXCollections.observableArrayList();

        for (int i = 0; i < observableList.size(); ++i) {
            if (option.equals("all")) {
                if (observableList.get(i).getValue().getSender().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
                else if (observableList.get(i).getValue().getTopic().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
                else if (observableList.get(i).getValue().getContents().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("recipient")) {
                if (observableList.get(i).getValue().getSender().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("topic")) {
                if (observableList.get(i).getValue().getTopic().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("contents")) {
                if (observableList.get(i).getValue().getContents().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            }
        }

        return truncatedList;
    }

    static public ObservableList<TreeItem<MessageSent>> giveMeListSentMessagesWithGivenIndex(String option, String index,
                                                                                             ObservableList<TreeItem<MessageSent>> observableList) {
        ObservableList<TreeItem<MessageSent>> truncatedList = FXCollections.observableArrayList();

        for (int i = 0; i < observableList.size(); ++i) {
            if (option.equals("all")) {
                if (observableList.get(i).getValue().getRecipient().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
                else if (observableList.get(i).getValue().getTopic().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
                else if (observableList.get(i).getValue().getContents().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("recipient")) {
                if (observableList.get(i).getValue().getRecipient().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("topic")) {
                if (observableList.get(i).getValue().getTopic().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("contents")) {
                if (observableList.get(i).getValue().getContents().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            }
        }

        return truncatedList;
    }

    static public ObservableList<TreeItem<MessageWorkingCopy>> giveMeListWorkingCopyWithGivenIndex(String option, String index,
                                                                                                   ObservableList<TreeItem<MessageWorkingCopy>> observableList) {
        ObservableList<TreeItem<MessageWorkingCopy>> truncatedList = FXCollections.observableArrayList();

        for (int i = 0; i < observableList.size(); ++i) {
            if (option.equals("all")) {
                if (observableList.get(i).getValue().getRecipient().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
                else if (observableList.get(i).getValue().getTopic().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
                else if (observableList.get(i).getValue().getContents().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("recipient")) {
                if (observableList.get(i).getValue().getRecipient().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("topic")) {
                if (observableList.get(i).getValue().getTopic().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("contents")) {
                if (observableList.get(i).getValue().getContents().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            }
        }

        return truncatedList;
    }

    static public ObservableList<TreeItem<MessageTrash>> giveMeListTrashMessagesWithGivenIndex(String option, String index,
                                                                                               ObservableList<TreeItem<MessageTrash>> observableList) {
        ObservableList<TreeItem<MessageTrash>> truncatedList = FXCollections.observableArrayList();

        for (int i = 0; i < observableList.size(); ++i) {
            if (option.equals("all")) {
                if (observableList.get(i).getValue().getSenderOrRecipient().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
                else if (observableList.get(i).getValue().getTopic().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
                else if (observableList.get(i).getValue().getContents().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("sender/recipient")) {
                if (observableList.get(i).getValue().getSenderOrRecipient().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("topic")) {
                if (observableList.get(i).getValue().getTopic().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            } else if (option.equals("contents")) {
                if (observableList.get(i).getValue().getContents().indexOf(index) != -1)
                    truncatedList.add(observableList.get(i));
            }
        }

        return truncatedList;
    }

    static public String deleteMyTextWithTextWithCommas(String myText, String textWithCommas) {
        StringTokenizer token = new StringTokenizer(textWithCommas, ",");
        List<String> list = new ArrayList<String>();
        String newText = null;

        while (token.hasMoreElements()) {
            list.add(token.nextToken().trim());
        }

        list.remove(myText.trim());

        Iterator<String> listIterator = list.iterator();

        int i = 0;
        while (listIterator.hasNext()) {
            if (i == 0) {
                newText = listIterator.next();
                ++i;
                continue;
            } else
                newText += " , " + listIterator.next();
            ++i;
        }

        return newText;
    }

    static public List<Integer> separateTextOnListIDs(String textWithCommas) {
        List<Integer> list = new ArrayList<Integer>();

        try {
            StringTokenizer token = new StringTokenizer(textWithCommas, " , ");

            while (token.hasMoreElements())
                list.add(Integer.parseInt(token.nextToken().trim()));
        } catch (NullPointerException e) {
            return null;
        }

        return list;
    }

    static public String changeListIntegerOnTextWithCommasWithMyInteger(int ID, List<Integer> list) {
        String textWithCommas = null;
        list.add(ID);

        for (int i = 0; i < list.size(); ++i) {
            if (i == 0)
                textWithCommas = String.valueOf(list.get(i));
            else
                textWithCommas += " , " + list.get(i);
        }

        return textWithCommas;
    }

    static public String changeListIntegersOnTextWithCommas(List<Integer> list) {
        String textWithCommas = null;

        for (int i = 0; i < list.size(); ++i) {
            if (i == 0)
                textWithCommas = String.valueOf(list.get(i)).trim();
            else
                textWithCommas += " , " + String.valueOf(list.get(i)).trim();
        }

        return textWithCommas;
    }

    static public ObservableList<TreeItem<Notes>> giveMeListNotesWithGivenIndex(String index,
                                                                                ObservableList<TreeItem<Notes>> observableList) {
        ObservableList<TreeItem<Notes>> truncatedList = FXCollections.observableArrayList();

        for (int i = 0; i < observableList.size(); ++i) {
            if (observableList.get(i).getValue().getTitle().indexOf(index) != -1)
                truncatedList.add(observableList.get(i));
        }

        return truncatedList;
    }
}
