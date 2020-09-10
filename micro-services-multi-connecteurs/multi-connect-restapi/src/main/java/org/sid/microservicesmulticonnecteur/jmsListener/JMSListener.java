package org.sid.microservicesmulticonnecteur.jmsListener;

import lombok.AllArgsConstructor;
import org.sid.microservicesmulticonnecteur.Model.Etudiant;
import org.sid.microservicesmulticonnecteur.repository.EtudiantRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;

@Component
@AllArgsConstructor
public class JMSListener {

    private EtudiantRepository etudiantRepository;

    @JmsListener(destination = "scolarite.queue")
    public void receive (Message message) throws Exception
    {
        if(message instanceof TextMessage)
        {
            String contenu = ((TextMessage) message).getText();
            System.out.println("******************************");
            System.out.println("Reception du message " + contenu);
            System.out.println("******************************");
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(contenu.split("_")[0]);
            etudiant.setPrenom(contenu.split("_")[1]);
            etudiantRepository.save(etudiant);
        }
    }
}
