import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSClient extends Application {

    private MessageProducer producer;
    private MessageConsumer consumer;
    private Session session;
    private Connection connection;
    private ObservableList<String> observableList;
    private ListView<String> listView;


    public static void main(String[] args) {
        Application.launch(JMSClient.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialisation();
        primaryStage.setTitle("Client JMS");

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));

        Label labelNom = new Label("Nom:");
        TextField textFieldNom = new TextField();

        Label labelPrenom = new Label("Prenom:");
        TextField textFieldPrenom = new TextField();

        Button buttonEnvoyer = new Button("Envoyer");

        gridPane.add(labelNom, 0, 0);
        gridPane.add(textFieldNom, 1, 0);
        gridPane.add(labelPrenom, 0, 1);
        gridPane.add(textFieldPrenom, 1, 1);
        gridPane.add(buttonEnvoyer, 0, 2);

        // ListView to show received message
        //observableList = FXCollections.observableArrayList();
         listView = new ListView<>();

        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(listView);

        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        buttonEnvoyer.setOnAction(e -> {
            String nom = textFieldNom.getText();
            String prenom = textFieldPrenom.getText();
            TextMessage message = null;
            try {
                message = session.createTextMessage();
                message.setText(nom+"_"+prenom);
                producer.send(message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
    }


    public void initialisation() {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61618");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destinationProducer = session.createQueue("scolarite.queue");
            producer = session.createProducer(destinationProducer);

            Destination destinationConsumer = session.createQueue("eco.rep");
            consumer = session.createConsumer(destinationConsumer);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message instanceof TextMessage)
                    {
                        try {
                            String contenu = ((TextMessage) message).getText();
                            listView.getItems().add(contenu);
                            //observableList.add(contenu);
                        } catch (Exception e) {
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
