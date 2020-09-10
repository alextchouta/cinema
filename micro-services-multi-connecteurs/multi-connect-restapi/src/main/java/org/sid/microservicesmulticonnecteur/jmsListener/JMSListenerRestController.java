package org.sid.microservicesmulticonnecteur.jmsListener;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class JMSListenerRestController {
    private JmsTemplate jmsTemplate;

    // http://localhost:8080/sendMessage?message=Hallo
    @GetMapping("/sendMessage")
    public void send(@RequestParam(value = "message") String message)
    {
        jmsTemplate.convertAndSend("eco.rep",message);
    }
}
