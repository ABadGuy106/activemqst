package net.abadguy.hello.lisenter;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName:
 * @Description: 使用监听的方式获取消息队列
 * @author:
 * @date: 2018/8/5 9:47
 */

public class JMSConsumerLisenter {

    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;//默认连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args){
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection = null;//连接
        Session session;//会话 接收或者发送消息的线程
        Destination destination; //消息的目的地
        MessageConsumer consumer;//消息消费者

        //实例化连接工厂
        connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);

        try {
            connection=connectionFactory.createConnection();


            connection.start();

            session=connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            destination=session.createQueue("FirstQue1");
            consumer=session.createConsumer(destination);
            consumer.setMessageListener(new Lisenter());//注册消息监听



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
}
