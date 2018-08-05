package net.abadguy.hello.lisenter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @ClassName:
 * @Description: 消息监听
 * @author:
 * @date: 2018/8/5 9:49
 */

public class Lisenter implements MessageListener {
    public void onMessage(Message message) {
        try {
            System.out.println("收到的消息"+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
