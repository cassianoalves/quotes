package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.model.ContactMessage;
import com.cassianoalves.quotes.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ContactComponentImpl implements ContactComponent {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ContactComponentImpl.class);
    @Value("${quotes.master.email}")
    private String masterEmail;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private ErrorComponent errorComponent;


    @Override
    public void sendContactMessageToMasterMail(ContactMessage contactMessage) {
        LOGGER.info("Sending contact message: " + contactMessage);

        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (details instanceof User) {
            contactMessage.setLoggedUser((User) details);
        }

        String email = contactMessage.getLoggedUser() == null ?
                (contactMessage.getVisitorEmail() == null ? "anônimo" : contactMessage.getVisitorEmail()) :
                contactMessage.getLoggedUser().getEmail();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Contato - " + contactMessage.getType() + " - " + email);
        msg.setTo(masterEmail);
        msg.setText("Mensagem:\n" +
                contactMessage.getMessage());
        try{
            this.mailSender.send(msg);
            LOGGER.info("Contact Mail sent");
        }
        catch (MailException ex) {
            LOGGER.error("Error sending contact e-mail", ex);
            throw errorComponent.getComponentException(ex);
        }
    }
}
