import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

public class Producer {

    public static void main(String[] args) {


        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61618");
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //Destination destination = session.createQueue("enset.queue");

            Destination destination = session.createTopic("enset.queue");

            MessageProducer producer = session.createProducer(destination);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage message = session.createTextMessage();

            message.setText("Hello");

            producer.send(message);

            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
