package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.Invite;
import com.cassianoalves.quotes.model.User;
import com.cassianoalves.quotes.model.UserConfirmation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * Component de e-mail
 * TODO: Tornar envio de e-mails assíncrono!!!
 */
@Component
public class EmailComponentImpl implements EmailComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailComponentImpl.class);

    @Value("${quotes.web.root.url:http://localhost:9000/#}")
    private String webAppRoot;

    @Autowired
    private MailSender mailSender;
    @Autowired
    private ErrorComponent errorComponent;

    @Override
    public void sendInvite(Invite invite) {
        LOGGER.info("Sending invite: " + invite);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Você foi convidado por " + invite.getHostUserName() + " para participar do \"The Quotes\"");
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
            throw errorComponent.getComponentException(ex);
        }
    }

    @Override
    public void sendConfirmation(UserConfirmation userConfirmation) {
        LOGGER.info("Sending confirmation: " + userConfirmation);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Confirmação de e-mail do \"The Quotes\"");
        msg.setTo(userConfirmation.getUser().getEmail());
        msg.setText("Você se inscreveu no The Quotes. Confirme seu e-mail através desse link: \n" +
                webAppRoot +
                "/confirm/" + userConfirmation.getId() + "\n\n" +
                "Bem-vindo!!!");
        try{
            this.mailSender.send(msg);
            LOGGER.info("Mail sent - userConfirmation: " + userConfirmation);
        }
        catch (MailException ex) {
            LOGGER.error("Error sending userConfirmation e-mail", ex);
            throw errorComponent.getComponentException(ex);
        }
    }

    @Override
    public void sendDataChange(User user, boolean passwordChanged) {
        LOGGER.info("Sending UserDataChange: {}, passwd {} changed", user, passwordChanged ? "was" : "not");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("\"The Quotes\" - alteração de dados");
        msg.setTo(user.getEmail());
        msg.setText("Os dados da sua conta " +
                "foram alterados. Os dados atuais são: \n" +
                "Nome: " + user.getName() + "\n" +
                "E-mail: " + user.getEmail() + "\n" +
                "Senha: * " + (passwordChanged ? "alterada" : "mantida") + " *\n" +
                "\n" +
                "\"The Quotes\" Master");
        try{
            this.mailSender.send(msg);
            LOGGER.info("Mail sent - UserDataChange: {}, passwd {} changed", user, passwordChanged ? "was" : "not");
        }
        catch (MailException ex) {
            LOGGER.error("Error sending UserDataChange e-mail", ex);
            throw errorComponent.getComponentException(ex);
        }
    }

    public void setWebAppRoot(String webAppRoot) {
        this.webAppRoot = webAppRoot;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
}
