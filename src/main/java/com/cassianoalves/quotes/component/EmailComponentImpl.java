package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.Invite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailComponentImpl implements EmailComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailComponentImpl.class);
    private String webAppRoot;

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendInvite(Invite invite) {
        LOGGER.info("Sending invite: " + invite);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(invite.getGuestEmail());
        msg.setText("Você foi convidado a participar do The Quotes por " +
            invite.getHostUserName() + "!\n\n" +
            "Para entrar, acesse: \n" +
            webAppRoot +
            "/signup?invite=" + invite.getId() + "\n\n" +
            "Bem-vindo!!!");
        try{
            this.mailSender.send(msg);
            LOGGER.info("Mail sent - invite: " + invite);
        }
        catch (MailException ex) {
            LOGGER.error("Error sending invite e-mail", ex);
            throw new ComponentException("Error sending invite e-mail " + invite,ex);
        }
    }

    public void setWebAppRoot(String webAppRoot) {
        this.webAppRoot = webAppRoot;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
}
