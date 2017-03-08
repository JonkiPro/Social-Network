package FileBuilder;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Jonatan on 2017-01-14.
 */
public class FileBuilder {
    static public boolean isFileRememberPassword() {
        File file = new File("rememberPassword.txt");

        if(file.exists())
            return true;

        return false;
    }

    static public void createFileRememberPassword(String login, String password) {
        try {
            Path path = Paths.get("rememberPassword.txt");
            Writer writer = new BufferedWriter(new FileWriter(path.toFile()));

            writer.write("Login: " + login + "\r");
            writer.write("\n" + "Password: " + password);

            writer.close();

            Files.setAttribute(path, "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void initDataFromFileRememberPassword(JFXTextField textLogin, JFXPasswordField textPassword) {
        File file = new File("rememberPassword.txt");
        try {
            Scanner input = new Scanner(file);
            String line = null;

            while(input.hasNext()) {
                line = input.next();

                if(line.equals("Login:")) {
                    textLogin.setText(input.next());
                } else if(line.equals("Password:")) {
                    textPassword.setText(input.next());
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            textLogin.setText("");
            textPassword.setText("");
        }
    }

    static public boolean createFileMessage(String path, String topic, String contents, String date) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write("Topic: " + topic + "\r");
            bw.write("\nContents: " + contents + "\r");
            bw.write("\nDate: " + date + "\r");

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    static public boolean createFileMessageWithSend(String path, String topic, String contents) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write("Topic: " + topic + "\r");
            bw.write("\nContents: " + contents + "\r");

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    static public boolean createFileMessageWithTrash(String path, String topic, String contents, String status,
                                                     String date, String dateRemoved) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write("Topic: " + topic + "\r");
            bw.write("\nContents: " + contents + "\r");
            bw.write("\nStatus: " + status + "\r");
            bw.write("\nDate: " + date + "\r");
            bw.write("\nDate removed: " + dateRemoved + "\r");

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    static public List<String> loadTextToMessage(String path) {
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while(br.ready()) {
                list.add(br.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return null;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }

        return list;
    }

    static public String[] loadMessage(File file) {
        String[] tabTopicAndContents;

        try {
            Scanner input = new Scanner(file);

            tabTopicAndContents = new String[2];

            while(input.hasNext()) {
                if(input.next().equals("Topic:")) {
                    tabTopicAndContents[0] = input.nextLine();
                }
                if(input.next().equals("Contents:")) {
                    tabTopicAndContents[1] = input.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return null;
        } catch (NullPointerException e) {
            return null;
        } catch (NoSuchElementException e) {
            return null;
        }

        return tabTopicAndContents;
    }

    static public boolean createFileNotes(String path, String title, String contents, String date) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write("Title: " + title + "\r");
            bw.write("\nContents: " + contents + "\r");
            bw.write("\nDate: " + date + "\r");

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    static public String[] loadNotes(File file) {
        String[] tabTitleAndContents;

        try {
            Scanner input = new Scanner(file);

            tabTitleAndContents = new String[2];

            while(input.hasNext()) {
                if(input.next().equals("Title:")) {
                    tabTitleAndContents[0] = input.nextLine();
                }
                if(input.next().equals("Contents:")) {
                    tabTitleAndContents[1] = input.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return null;
        } catch (NullPointerException e) {
            return null;
        } catch (NoSuchElementException e) {
            return null;
        }

        return tabTitleAndContents;
    }

    static public String loadTextToPost(String path) {
        String text = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while(br.ready()) {
                if(text == null)
                    text = br.readLine();
                else
                    text += br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return null;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }

        return text;
    }

    static public boolean createFileLoggers(String path, List<String> list) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(list.get(0) + "\r");
            for(int i = 1; i < list.size(); ++i) {
                bw.write("\n" + list.get(i) + "\r");
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }
}
