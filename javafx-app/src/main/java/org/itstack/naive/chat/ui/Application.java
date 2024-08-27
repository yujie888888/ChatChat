package org.itstack.naive.chat.ui;

import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.itstack.naive.chat.ui.view.chat.element.group_bar_friend.ElementFriendLuckUser;
import org.itstack.naive.chat.ui.view.chat.ChatController;
import org.itstack.naive.chat.ui.view.chat.IChatEvent;
import org.itstack.naive.chat.ui.view.chat.IChatMethod;
import org.itstack.naive.chat.ui.view.login.ILoginMethod;
import org.itstack.naive.chat.ui.view.login.LoginController;
import java.util.Date;

/**
 * 窗口          Stage
 * -场景       Scene
 * -布局     stackPane
 * -控件   Button
 */
public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        IChatMethod chat = new ChatController(new IChatEvent() {
            @Override
            public void doQuit() {
                System.out.println("Exit");
            }

            @Override
            public void doSendMsg(String userId, String talkId, Integer talkType, String msg, Integer msgType, Date msgDate) {
                System.out.println("Send Message");
                System.out.println("userId：" + userId);
                System.out.println("talkType[0Friends/1Group]：" + talkType);
                System.out.println("talkId：" + talkId);
                System.out.println("msg：" + msg);
                System.out.println("msgType[0Text/1Emoji]：" + msgType);
            }

            @Override
            public void doEventAddTalkUser(String userId, String userFriendId) {
                System.out.println("Fill to chat window [Friends] userFriendId: " + userFriendId);
            }

            @Override
            public void doEventAddTalkGroup(String userId, String groupId) {
                System.out.println("Fill to chat window [group] groupId: " + groupId);
            }

            @Override
            public void doEventDelTalkUser(String userId, String talkId) {
                System.out.println("Delete dialog box: " + talkId);
            }

            @Override
            public void addFriendLuck(String userId, ListView<Pane> listView) {
                System.out.println("New Friends");
                // 添加朋友
                listView.getItems().add(new ElementFriendLuckUser("1000005", "Amy", "05_50", 0).pane());
                listView.getItems().add(new ElementFriendLuckUser("1000006", "Bob", "06_50", 1).pane());
                listView.getItems().add(new ElementFriendLuckUser("1000007", "Alexa", "07_50", 2).pane());
            }

            @Override
            public void doFriendLuckSearch(String userId, String text) {
                System.out.println("Search Friends: " + text);
            }

            @Override
            public void doEventAddLuckUser(String userId, String friendId) {
                System.out.println("Add New Friends: " + friendId);
            }
        });
        chat.doShow();
        chat.setUserInfo("1000001", "Lion", "02_50");

        // 模拟测试
        chat.addTalkBox(-1, 0, "1000004", "Rabbbit", "04_50", null, null, false);
        chat.addTalkMsgUserLeft("1000004", "Text1", 0, new Date(), true, false, true);
        chat.addTalkMsgUserLeft("1000004", "f_23", 1, new Date(), true, false, true);
        chat.addTalkMsgRight("1000004", "Text2", 0, new Date(), true, true, false);
        chat.addTalkBox(-1, 0, "1000002", "Hammer", "03_50", "Text3", new Date(), false);
        chat.addTalkMsgUserLeft("1000002", "text4", 0, new Date(), true, false, true);
        chat.addTalkMsgRight("1000002", "text5", 0, new Date(), true, true, false);

        // 群组
        chat.addFriendGroup("5307397", "gruop1", "group_1");
        chat.addFriendGroup("5307392", "gruop2", "group_2");
        chat.addFriendGroup("5307399", "gruop3", "group_3");

        // 群组 - 对话框
        chat.addTalkBox(0, 1, "5307397", "group1", "group_1", "", new Date(), true);
        chat.addTalkMsgRight("5307397", "user4", 0, new Date(), true, true, false);
        chat.addTalkMsgRight("5307397", "f_14", 1, new Date(), true, true, false);
        chat.addTalkMsgGroupLeft("5307397", "1000002", "Lion", "01_50", "user1", 0, new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000003", "Hammer", "03_50", "user2", 0, new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "Rabbbit", "04_50", "user3", 0, new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "Rabbbit", "04_50", "f_25", 1, new Date(), true, false, true);

        // 好友
        chat.addFriendUser(false, "1000004", "Rabbbit", "04_50");
        chat.addFriendUser(false, "1000001", "Lion", "02_50");
        chat.addFriendUser(false, "1000002", "Hammer", "03_50");
        chat.addFriendUser(true, "1000003", "Lucy", "01_50");
        s
        ILoginMethod login = new LoginController((userId, userPassword) -> {
            System.out.println("login userId：" + userId + "userPassword：" + userPassword);
        }, chat);
        // login.doShow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}