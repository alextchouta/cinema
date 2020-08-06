import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

public class JMSChat extends Application {

    private MessageProducer messageProducer;
    private Session session;
    private String codeUser;


    public static void main(String[] args) {
        Application.launch(JMSChat.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JMS Chat");
        BorderPane borderPane = new BorderPane();

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        hBox.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label labelCode =  new Label("Code:");
        TextField textFieldCode = new TextField("C1");
        textFieldCode.setPromptText("Code");

        Label labelHost =  new Label("Host:");
        TextField textFieldHost = new TextField("localhost");
        textFieldHost.setPromptText("Host");

        Label labelPort =  new Label("Port:");
        TextField textFieldPort = new TextField("61618");
        textFieldPort.setPromptText("Port");

        Button buttonConnecter = new Button("Connecter");

        hBox.getChildren().add(labelCode);
        hBox.getChildren().add(textFieldCode);

        hBox.getChildren().add(labelHost);
        hBox.getChildren().add(textFieldHost);

        hBox.getChildren().add(labelPort);
        hBox.getChildren().add(textFieldPort);

        hBox.getChildren().add(buttonConnecter);

        borderPane.setTop(hBox);

        VBox vBox = new VBox();

        // la grille de panel dans le panel vertical
        GridPane gridPane =  new GridPane();
        Label labelTo= new Label("To:");
        TextField textFieldTo = new TextField("C1"); textFieldTo.setPrefWidth(250);

        Label labelMessage= new Label("Message:");
        TextArea textAreaMessage = new TextArea();
        textAreaMessage.setPrefWidth(250);
        Button buttonEnvoyer = new Button("Envoyer");

        Label labelImage = new Label("Image");

        File f = new File("images");
        ObservableList<String> observableListImages = FXCollections.observableArrayList(f.list());
        ComboBox<String> comboxImages = new ComboBox<>(observableListImages);
        comboxImages.getSelectionModel().select(0);

        Button buttonEnvoyerImage = new Button("Envoyer Image");

        gridPane.setPadding(new Insets(10));
        textAreaMessage.setPrefRowCount(1);
        gridPane.setVgap(10); gridPane.setHgap(10);
        gridPane.add(labelTo,0,0);
        gridPane.add(textFieldTo,1,0);

        gridPane.add(labelMessage,0,1);
        gridPane.add(textAreaMessage,1,1);

        gridPane.add(buttonEnvoyer,2,1);

        gridPane.add(labelImage,0,2);

        gridPane.add(comboxImages,1,2);
        gridPane.add(buttonEnvoyerImage,2,2);

        // le panel horizontal dans le panel vertical
        HBox hBox1 = new HBox();

        ObservableList<String> observableListMessages = FXCollections.observableArrayList();
        ListView<String> listViewMessages = new ListView<>(observableListMessages);

        File f2 = new File("images/"+comboxImages.getSelectionModel().getSelectedItem());
        Image image = new Image(f2.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(320);
        imageView.setFitHeight(240);

        hBox1.setPadding(new Insets(10));
        hBox1.setSpacing(10);

        hBox1.getChildren().add(listViewMessages);
        hBox1.getChildren().add(imageView);

        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(hBox1);

        borderPane.setCenter(vBox);



        Scene scene = new Scene(borderPane, 800,500);

        primaryStage.setScene(scene);
        primaryStage.show();

        comboxImages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                File f3 = new File("images/"+newValue);
                Image image = new Image(f3.toURI().toString());
                imageView.setImage(image);
            }
        });

        buttonEnvoyer.setOnAction(e ->{
            try {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(textAreaMessage.getText());
                textMessage.setStringProperty("code", textFieldTo.getText());
                messageProducer.send(textMessage);
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        });

        buttonEnvoyerImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    StreamMessage streamMessage = session.createStreamMessage();
                    streamMessage.setStringProperty("code", textFieldTo.getText());
                    File fileToStream = new File("images/"+comboxImages.getSelectionModel().getSelectedItem());
                    FileInputStream fis = new FileInputStream(fileToStream);
                    byte [] data = new byte[(int) fileToStream.length()];

                    fis.read(data);

                    streamMessage.writeString(comboxImages.getSelectionModel().getSelectedItem());
                    streamMessage.writeInt(data.length);
                    streamMessage.writeBytes(data);

                    messageProducer.send(streamMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        buttonConnecter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                try {
                    codeUser = textFieldCode.getText();
                    String host = textFieldHost.getText();
                    int port = Integer.parseInt(textFieldPort.getText());
                    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://"+ host + ":"+ port);
                    Connection connection = connectionFactory.createConnection();
                    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                    Destination destination = session.createTopic("enset.chat");

                    MessageConsumer consumer = session.createConsumer(destination, "code='"+textFieldTo.getText()+"'");

                    messageProducer = session.createProducer(destination);
                    messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                    // subscribe to topic and wait for message
                    consumer.setMessageListener(new MessageListener() {
                        public void onMessage(Message message) {

                                try {
                                    if(message instanceof TextMessage) {
                                        TextMessage textMessage = (TextMessage) message;
                                        System.out.println("Reception du message:" + " " + textMessage.getText());
                                        observableListMessages.add(textMessage.getText());
                                    }else if(message instanceof StreamMessage){
                                        StreamMessage streamMessage = (StreamMessage) message;
                                        String nomPhoto = streamMessage.readString();
                                        observableListMessages.add("Reception d une Photo" + nomPhoto);
                                        int sizePhoto = streamMessage.readInt();
                                        byte[] data = new byte[sizePhoto];
                                        streamMessage.readBytes(data);
                                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);

                                        Image image = new Image(byteArrayInputStream);
                                        imageView.setImage(image);
                                    }
                                } catch (JMSException e) {
                                    e.printStackTrace();
                                }

                        }
                    });
                    connection.start();
                    hBox.setDisable(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
