package mailing;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SmtpMailing {
    public static void main(String[] args) throws MessagingException {

        Properties props = getProperties();


        String username = "ec8b72143569b1";
        String password = "ba4f8959f92fc6";

        Session session = getSession(props, username, password);

        Message message = new MimeMessage(session);
//        message.setText("Salom Kamron");
//        message.setContent("<h1 style=\"color:red;\"> My red message <h1>", "text/html;");


        message.setSubject("Kamronga xabar");
        Multipart multipart = new MimeMultipart();
        BodyPart attachment = new MimeBodyPart();
        message.setContent("<h1 style=\"color:red;\"> My red message <h1>", "text/html;");
        attachment.setFileName("cv.txt");
        attachment.setDataHandler(new DataHandler(new FileDataSource("cv.txt")));
        multipart.addBodyPart(attachment);
        message.setContent(multipart);

        message.setFrom(new InternetAddress(username));
        String receiver = "abduvakilovf@gmail.com";
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        Transport.send(message);

        System.out.println("Sent message successfully....");


    }

    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        return props;
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
