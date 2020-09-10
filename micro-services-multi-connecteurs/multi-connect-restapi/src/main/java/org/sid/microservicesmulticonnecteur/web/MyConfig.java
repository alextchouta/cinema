package org.sid.microservicesmulticonnecteur.web;

import org.glassfish.jersey.server.ResourceConfig;
import org.sid.microservicesmulticonnecteur.webserviceRmi.IEtudiantRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class MyConfig {

    // deployer le Webservice base sur JAXRS
    // @Bean
    public ResourceConfig getJersey() {
        ResourceConfig config = new ResourceConfig();
        config.register(EtudiantRestControllerWithJAXRS.class);
        return config;
    }

    // deployer le Webservice base sur SOAP
    //@Bean
    public SimpleJaxWsServiceExporter serviceExporter() {
        SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
        exporter.setBaseAddress("http://0.0.0.0:8585/service");
        return exporter;
    }

    @Bean
    @Autowired
    public RmiServiceExporter serviceExporter(IEtudiantRemote etudiantRemote) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setRegistryPort(1099);
        rmiServiceExporter.setServiceName("ETUDIANT");
        rmiServiceExporter.setService(etudiantRemote);

        return rmiServiceExporter;
    }
}
