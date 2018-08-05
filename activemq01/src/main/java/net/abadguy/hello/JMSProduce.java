package net.abadguy.hello;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName:
 * @Description: 消息生产者
 * @author:
 * @date: 2018/8/5 9:11
 */

public class JMSProduce {

    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;//默认连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final int SENDNUM=10;


    public static void main(String[] args) {
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection = null;//连接
        Session session;//会话 接收或者发送消息的线程
        Destination destination; //消息的目的地
        MessageProducer messageProducer;//消息生产者

        //实例化连接工厂
        connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);

        try {
            connection=connectionFactory.createConnection();


        connection.start();

        session=connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        destination=session.createQueue("FirstQue1");
        messageProducer=session.createProducer(destination);

        sendMessage(session,messageProducer);
        session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void sendMessage(Session session,MessageProducer producer){
        for(int i=0;i<SENDNUM;i++){
            try {
                TextMessage message=session.createTextMessage("ACTIVEMQ发送的消息"+i);
                System.out.printf("发送消息:"+"ACTIVEMQ发送的消息"+i);
                producer.send(message);
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }
}
