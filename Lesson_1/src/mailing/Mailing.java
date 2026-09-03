package mailing;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Mailing {
    public static void main(String[] args) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");

        String username = "omonovkamron77@gmail.com";
        String password = "canqrlhjavbgnnoc";

        Session session = getSession(props, username, password);
        Message message = new MimeMessage(session);
        message.setSubject("Kamronga xabar");
//        message.setText("Salom Kamron");
        message.setContent("<h1 style=\"color:red;\"> My red message <h1>", "text/html;");
        message.setFrom(new InternetAddress(username));
        String receiver = "abduvakilovf@gmail.com";
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        Transport.send(message);

        System.out.println("Sent message successfully....");

    }

    private static Session getSession(Properties props, String username, String password) {
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}
