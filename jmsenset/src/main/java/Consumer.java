import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

public class Consumer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Code:");
        String code = scanner.nextLine();


        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61618");
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //Destination destination = session.createQueue("enset.queue");

            Destination destination = session.createTopic("enset.queue");

            MessageConsumer consumer = session.createConsumer(destination);

            // subscribe to topic and wait for message
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                   if(message instanceof TextMessage){
                       TextMessage textMessage = (TextMessage) message;
                       try {
                           System.out.println("Reception du message:" + " "+ textMessage.getText());
                       } catch (JMSException e) {
                           e.printStackTrace();
                       }
                   }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
