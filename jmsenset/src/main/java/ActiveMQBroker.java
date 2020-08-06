import org.apache.activemq.broker.BrokerService;

/*
* Creer un Broker embarque
* pour cela il faut importer 2 dependencies dans le pom.xml
*
* */
public class ActiveMQBroker {

    public static void main(String[] args) {
        BrokerService brokerService = new BrokerService();
        try {
            brokerService.addConnector("tcp://0.0.0.0:61618");
            brokerService.setPersistent(false);
            brokerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
