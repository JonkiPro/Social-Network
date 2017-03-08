package Service.LoadPage;

import LoadFXML.LoadFXML;
import Service.Controller.Pages.Community.NewsController;
import Service.Controller.Pages.EditMyProfile.*;
import Service.Controller.Pages.Event.EventController;
import Service.Controller.Pages.FriendsAndGroups.FriendsController;
import Service.Controller.Pages.FriendsAndGroups.InvitationsController;
import Service.Controller.Pages.FriendsAndGroups.YourInvitationsController;
import Service.Controller.Pages.Home.HomeController;
import Service.Controller.Pages.Home.ShowMoreController;
import Service.Controller.Pages.Notes.NotesController;
import Service.Controller.Pages.PostOffice.*;
import Service.Controller.Pages.Profile.*;
import Service.Controller.Pages.RecentActivity.RecentActivityController;
import Service.Controller.Pages.Search.SearchController;
import Service.Controller.ServiceWindowController;
import Service.Message.MessageReadAndReceived;
import Service.Message.MessageSent;
import Service.Message.MessageTrash;
import Service.Message.MessageWorkingCopy;
import Service.Person.Person;
import Service.Person.PersonOther;
import Service.Post.Post;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Jonatan on 2017-01-22.
 */
public class LoadPage {

    private static ServiceWindowController serviceWindowController;
    private static Stage stage;

    static private final String FXML_EDIT_MY_PROFILE = "/Service/Pages/EditMyProfile/EditMyProfile.fxml";
    static private final String FXML_CHANGE_NAME_AND_LAST_NAME = "/Service/Pages/EditMyProfile/ChangeNameAndLastName.fxml";
    static private final String FXML_CHANGE_LOGIN = "/Service/Pages/EditMyProfile/ChangeLogin.fxml";
    static private final String FXML_CHANGE_PASSWORD = "/Service/Pages/EditMyProfile/ChangePassword.fxml";
    static private final String FXML_CHANGE_EMAIL = "/Service/Pages/EditMyProfile/ChangeEmail.fxml";
    static private final String FXML_CHANGE_QUESTION_AND_ANSWER = "/Service/Pages/EditMyProfile/ChangeQuestionAndAnswer.fxml";
    static private final String FXML_CHANGE_OTHER = "/Service/Pages/EditMyProfile/ChangeOther.fxml";
    static private final String FXML_GRID_PANE_SUCCESS_EDITION = "/Service/Pages/EditMyProfile/GridPaneSuccessEdition.fxml";
    static private final String FXML_OTHER_DATA = "/Service/Pages/EditMyProfile/OtherData.fxml";
    static private final String FXML_CHANGE_ABOUT_ME = "/Service/Pages/EditMyProfile/ChangeAboutMe.fxml";
    static private final String FXML_CHANGE_AVATAR = "/Service/Pages/EditMyProfile/ChangeAvatar.fxml";


    static private final String FXML_MY_PROFILE = "/Service/Pages/Profile/MyProfile.fxml";
        static private final String FXML_MY_PROFILE_TIMELINE = "/Service/Pages/Profile/MyProfileTimeline.fxml";
        static private final String FXML_MY_PROFILE_INFORMATION = "/Service/Pages/Profile/MyProfileInformation.fxml";
        static private final String FXML_MY_PROFILE_FRIENDS = "/Service/Pages/Profile/MyProfileFriends.fxml";
    static private final String FXML_PROFILE = "/Service/Pages/Profile/Profile.fxml";
        static private final String FXML_PROFILE_TIMELINE = "/Service/Pages/Profile/ProfileTimeline.fxml";
        static private final String FXML_PROFILE_INFORMATION= "/Service/Pages/Profile/ProfileInformation.fxml";
        static private final String FXML_PROFILE_FRIENDS = "/Service/Pages/Profile/ProfileFriends.fxml";


    static private final String FXML_READ_MESSAGE = "/Service/Pages/PostOffice/ReadMessage.fxml";
    static private final String FXML_RECEIVED_MESSAGE = "/Service/Pages/PostOffice/ReceivedMessage.fxml";
    static private final String FXML_SENT_MESSAGE = "/Service/Pages/PostOffice/SentMessage.fxml";
           static private final String FXML_SENT_MESSAGE_SHOW_MESSAGE = "/Service/Pages/PostOffice/SentMessageShowMessage.fxml";
    static private final String FXML_SEND_MESSAGE = "/Service/Pages/PostOffice/SendMessage.fxml";
    static private final String FXML_WORKING_COPY = "/Service/Pages/PostOffice/WorkingCopy.fxml";
           static private final String FXML_WORKING_COPY_SHOW_MESSAGE = "/Service/Pages/PostOffice/WorkingCopyShowMessage.fxml";
    static private final String FXML_TRASH = "/Service/Pages/PostOffice/Trash.fxml";
           static private final String FXML_TRASH_SHOW_MESSAGE = "/Service/Pages/PostOffice/TrashShowMessage.fxml";
           static private final String FXML_TRASH_SHOW_MY_MESSAGE = "/Service/Pages/PostOffice/TrashShowMyMessage.fxml";
    static private final String FXML_SHOW_MESSAGE = "/Service/Pages/PostOffice/ShowMessage.fxml";
    static private final String FXML_REPLY_TO_MESSAGE = "/Service/Pages/PostOffice/ReplyToMessage.fxml";


    static private final String FXML_NEWS = "/Service/Pages/Community/News.fxml";


    static private final String FXML_SEARCH = "/Service/Pages/Search/Search.fxml";


    static private final String FXML_FRIENDS = "/Service/Pages/FriendsAndGroups/Friends.fxml";
    static private final String FXML_YOUR_INVITATIONS = "/Service/Pages/FriendsAndGroups/YourInvitations.fxml";
    static private final String FXML_INVITATIONS = "/Service/Pages/FriendsAndGroups/Invitations.fxml";


    static private final String FXML_HOME = "/Service/Pages/Home/Home.fxml";
    static private final String FXML_SHOW_MORE = "/Service/Pages/Home/ShowMore.fxml";


    static private final String FXML_NOTES = "/Service/Pages/Notes/Notes.fxml";


    static private final String FXML_RECENT_ACTIVITY = "/Service/Pages/RecentActivity/RecentActivity.fxml";


    static private final String FXML_EVENT = "/Service/Pages/Event/Event.fxml";


    public void loadEditMyProfile(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_EDIT_MY_PROFILE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        EditMyProfileController editMyProfileController = loader.getController();
        editMyProfileController.setPerson(person);
        editMyProfileController.bindPerson();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadChangeNameAndLastName(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_CHANGE_NAME_AND_LAST_NAME);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ChangeNameAndLastNameController changeNameAndLastNameController = loader.getController();
        changeNameAndLastNameController.setPerson(person);
        changeNameAndLastNameController.bindPerson();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadChangeLogin(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_CHANGE_LOGIN);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ChangeLoginController changeLoginController = loader.getController();
        changeLoginController.setPerson(person);
        changeLoginController.bindPerson();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadChangePassword(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_CHANGE_PASSWORD);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ChangePasswordController changePasswordController = loader.getController();
        changePasswordController.setPerson(person);
        changePasswordController.bindPerson();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadChangeEmail(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_CHANGE_EMAIL);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ChangeEmailController changeEmailController = loader.getController();
        changeEmailController.setPerson(person);
        changeEmailController.bindPerson();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadChangeQuestionAndAnswer(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_CHANGE_QUESTION_AND_ANSWER);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ChangeQuestionAndAnswerController changeQuestionAndAnswerController = loader.getController();
        changeQuestionAndAnswerController.setPerson(person);
        changeQuestionAndAnswerController.bindPerson();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadChangeOther(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_CHANGE_OTHER);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ChangeOtherController changeOtherController = loader.getController();
        changeOtherController.setPerson(person);
        changeOtherController.bindPerson();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadOtherData(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_OTHER_DATA);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        OtherDataController otherDataController = loader.getController();
        otherDataController.setPerson(person);
        otherDataController.bindPerson();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadChangeAboutMe(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_CHANGE_ABOUT_ME);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ChangeAboutMeController changeAboutMeController = loader.getController();
        changeAboutMeController.setPerson(person);
        changeAboutMeController.bindPerson();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadChangeAvatar(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_CHANGE_AVATAR);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ChangeAvatarController changeAvatarController = loader.getController();
        changeAvatarController.setPerson(person);
        changeAvatarController.setStage(stage);
        changeAvatarController.initAvatar();

        serviceWindowController.setCenter((Pane)node);
    }


    public void loadGridPaneSuccessEdition(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_GRID_PANE_SUCCESS_EDITION);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        GridPaneSuccessEditionController gridPaneSuccessEditionController = loader.getController();
        gridPaneSuccessEditionController.setPerson(person);

        serviceWindowController.setCenter((Pane)node);
    }




    public void loadMyProfile(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_MY_PROFILE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        MyProfileController myProfileController = loader.getController();
        myProfileController.setPerson(person);
        myProfileController.setStage(stage);
        myProfileController.initAllData();
        myProfileController.initTimeline();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadMyProfileTimeline(Person person, ScrollPane scrollPane) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_MY_PROFILE_TIMELINE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        MyProfileTimelineController myProfileTimelineController = loader.getController();
        myProfileTimelineController.setPerson(person);
        myProfileTimelineController.setStage(stage);
        myProfileTimelineController.initPosts();

        scrollPane.setContent((Pane)node);
    }

    public void loadMyProfileInformation(Person person, ScrollPane scrollPane) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_MY_PROFILE_INFORMATION);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        MyProfileInformationController myProfileInformationController = loader.getController();
        myProfileInformationController.setPerson(person);
        myProfileInformationController.initData();

        scrollPane.setContent((Pane)node);
    }

    public void loadMyProfileFriends(Person person, ScrollPane scrollPane) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_MY_PROFILE_FRIENDS);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        MyProfileFriendsController myProfileFriendsController = loader.getController();
        myProfileFriendsController.setPerson(person);
        myProfileFriendsController.setStage(stage);
        myProfileFriendsController.initializeListFriends();

        scrollPane.setContent((Pane)node);
    }

    public void loadProfile(PersonOther person, Person yourPerson) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_PROFILE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ProfileController profileController = loader.getController();
        profileController.setPersonOther(person);
        profileController.setYourPerson(yourPerson);
        profileController.initAllData();
        profileController.initTimeline();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadProfileTimeline(PersonOther personOther, Person yourPerson, ScrollPane scrollPane) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_PROFILE_TIMELINE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ProfileTimelineController profileTimelineController = loader.getController();
        profileTimelineController.setPersonOther(personOther);
        profileTimelineController.setYourPerson(yourPerson);
        profileTimelineController.initPosts();

        scrollPane.setContent((Pane)node);
    }

    public void loadProfileInformation(PersonOther personOther, ScrollPane scrollPane) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_PROFILE_INFORMATION);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ProfileInformationController profileInformationController = loader.getController();
        profileInformationController.setPersonOther(personOther);
        profileInformationController.initData();

        scrollPane.setContent((Pane)node);
    }

    public void loadProfileFriends(PersonOther personOther, ScrollPane scrollPane) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_PROFILE_FRIENDS);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ProfileFriendsController profileFriendsController = loader.getController();
        profileFriendsController.setPersonOther(personOther);
        profileFriendsController.setStage(stage);
        profileFriendsController.initializeListFriends();

        scrollPane.setContent((Pane)node);
    }




    public void loadReadMessage(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_READ_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ReadMessageController readMessageController = loader.getController();
        readMessageController.setPerson(person);
        readMessageController.loadMessagesToObservableList();
        readMessageController.createTableView();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadReceivedMessage(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_RECEIVED_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ReceivedMessageController receivedMessageController = loader.getController();
        receivedMessageController.setPerson(person);
        receivedMessageController.loadMessagesToObservableList();
        receivedMessageController.createTableView();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadSentMessage(Person person) {
            FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_SENT_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        SentMessageController sentMessageController = loader.getController();
        sentMessageController.setPerson(person);
        sentMessageController.loadMessagesToObservableList();
        sentMessageController.createTableView();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadSentMessageShowMessage(Person person, MessageSent message) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_SENT_MESSAGE_SHOW_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        SentMessageShowMessageController sentMessageShowMessageController = loader.getController();
        sentMessageShowMessageController.setPerson(person);
        sentMessageShowMessageController.setStage(stage);
        sentMessageShowMessageController.setMessage(message);
        sentMessageShowMessageController.initialization();
        sentMessageShowMessageController.initReactionOnSlider();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadSentMessageEditMessage(Person person, MessageSent message) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_SEND_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }


        SendMessageController sendMessageController = loader.getController();
        sendMessageController.setPerson(person);
        sendMessageController.setTextFieldsForSentMessage(message);

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadSendMessage(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_SEND_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        SendMessageController sendMessageController = loader.getController();
        sendMessageController.setPerson(person);
        sendMessageController.setStage(stage);
        sendMessageController.initContexMenu();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadWorkingCopy(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_WORKING_COPY);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        WorkingCopyController workingCopyController = loader.getController();
        workingCopyController.setPerson(person);
        workingCopyController.loadMessagesToObservableList();
        workingCopyController.createTableView();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadWorkingCopyShowMessage(Person person, MessageWorkingCopy message) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_WORKING_COPY_SHOW_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        WorkingCopyShowMessageController workingCopyShowMessageController = loader.getController();
        workingCopyShowMessageController.setPerson(person);
        workingCopyShowMessageController.setStage(stage);
        workingCopyShowMessageController.setMessage(message);
        workingCopyShowMessageController.initialization();
        workingCopyShowMessageController.initReactionOnSlider();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadWorkingCopyEditMessage(Person person, MessageWorkingCopy message) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_SEND_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }


        SendMessageController sendMessageController = loader.getController();
        sendMessageController.setPerson(person);
        sendMessageController.setTextFieldsForWorkingCopy(message);

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadTrash(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_TRASH);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        TrashController trashController = loader.getController();
        trashController.setPerson(person);
        trashController.loadMessagesToObservableList();
        trashController.createTableView();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadTrashShowMessage(Person person, MessageTrash message) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_TRASH_SHOW_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        TrashShowMessageController trashShowMessageController = loader.getController();
        trashShowMessageController.setPerson(person);
        trashShowMessageController.setStage(stage);
        trashShowMessageController.setMessage(message);
        trashShowMessageController.initialization();
        trashShowMessageController.initReactionOnSlider();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadTrashShowMyMessage(Person person, MessageTrash message) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_TRASH_SHOW_MY_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        TrashShowMyMessageController trashShowMyMessageController = loader.getController();
        trashShowMyMessageController.setPerson(person);
        trashShowMyMessageController.setStage(stage);
        trashShowMyMessageController.setMessage(message);
        trashShowMyMessageController.initialization();
        trashShowMyMessageController.initReactionOnSlider();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadShowMessage(Person person, MessageReadAndReceived message) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_SHOW_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ShowMessageController showMessageController = loader.getController();
        showMessageController.setPerson(person);
        showMessageController.setStage(stage);
        showMessageController.setMessage(message);
        showMessageController.initialization();
        showMessageController.initReactionOnSlider();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadReplyToMessage(Person person, MessageReadAndReceived message) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_REPLY_TO_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ReplyToMessageController replyToMessageController = loader.getController();
        replyToMessageController.setPerson(person);
        replyToMessageController.setStage(stage);
        replyToMessageController.setMessage(message);
        replyToMessageController.initialization();

        serviceWindowController.setCenter((Pane)node);
    }




    public void loadNews(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_NEWS);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        NewsController newsController = loader.getController();
        newsController.setPerson(person);
        newsController.initPosts();

        serviceWindowController.setCenter((Pane)node);
    }




    public void loadSearch(Person person, String text) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_SEARCH);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        SearchController searchController = loader.getController();
        searchController.setPerson(person);
        searchController.setTextField(text);
        searchController.initPersonsByNameAndLastNameAndLogin();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadSearchSendMessage(Person person, int ID) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_SEND_MESSAGE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }


        SendMessageController sendMessageController = loader.getController();
        sendMessageController.setPerson(person);
        sendMessageController.setTextFieldsForSearch(ID);

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadSearchGoToProfileFriends(PersonOther personOther, Person yourPerson) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_PROFILE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ProfileController profileController = loader.getController();
        profileController.setPersonOther(personOther);
        profileController.setYourPerson(yourPerson);
        profileController.initAllData();
        profileController.initTimeline();

        serviceWindowController.setCenter((Pane)node);

        loader = LoadFXML.loadFXML(getClass(), FXML_PROFILE_FRIENDS);

        node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ProfileFriendsController profileFriendsController = loader.getController();
        profileFriendsController.setPersonOther(personOther);
        profileFriendsController.setStage(stage);
        profileFriendsController.initializeListFriends();

        profileController.getScrollPane().setContent((Pane)node);
    }




    public void loadFriends(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_FRIENDS);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        FriendsController friendsController = loader.getController();
        friendsController.setPerson(person);
        friendsController.setStage(stage);
        friendsController.initializeListFriends(person);

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadYourInvitations(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_YOUR_INVITATIONS);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        YourInvitationsController yourInvitationsController = loader.getController();
        yourInvitationsController.setPerson(person);
        yourInvitationsController.setStage(stage);
        yourInvitationsController.initializeListFriends(person);

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadInvitations(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_INVITATIONS);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        InvitationsController invitationsController = loader.getController();
        invitationsController.setPerson(person);
        invitationsController.setStage(stage);
        invitationsController.initializeListFriends(person);

        serviceWindowController.setCenter((Pane)node);
    }




    public void loadHome(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_HOME);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        HomeController homeController = loader.getController();
        homeController.setPerson(person);
        homeController.setStage(stage);
        homeController.initIDsYourPosts();
        homeController.initPosts();

        serviceWindowController.setCenter((Pane)node);
    }

    public void loadShowMore(Person person, Post post) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_SHOW_MORE);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ShowMoreController showMoreController = loader.getController();
        showMoreController.setPerson(person);
        showMoreController.setScene(new Scene((AnchorPane)node));
        showMoreController.setText(post.getText());
        showMoreController.setIDPost(post.getID());
        showMoreController.loadComment();
        showMoreController.show();
    }




    public void loadNotes(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_NOTES);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        NotesController notesController = loader.getController();
        notesController.setPerson(person);
        notesController.setStage(stage);
        notesController.loadMessagesToObservableList();
        notesController.createTableView();
        notesController.initReactionOnSlider();

        serviceWindowController.setCenter((Pane)node);
    }




    public void loadRecentActivity(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_RECENT_ACTIVITY);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        RecentActivityController recentActivityController = loader.getController();
        recentActivityController.setPerson(person);
        recentActivityController.setStage(stage);
        recentActivityController.loadLoggers();
        recentActivityController.loadLoggersToListView();

        serviceWindowController.setCenter((Pane)node);
    }




    public void loadEvent(Person person) {
        FXMLLoader loader = LoadFXML.loadFXML(getClass(), FXML_EVENT);

        Node node = null;
        try {
            node = loader.load();
        } catch(IOException e) {
            e.printStackTrace();
        }

        EventController eventController = loader.getController();
        eventController.setPerson(person);
        eventController.initIDsEvents();
        eventController.initEvents();

        serviceWindowController.setCenter((Pane)node);
    }



    public static void setServiceWindowController(ServiceWindowController serviceWindowController) {
        LoadPage.serviceWindowController = serviceWindowController;
    }

    public static void setStage(Stage stage) {
        LoadPage.stage = stage;
    }

}
