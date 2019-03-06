package com.biller.webapp.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 */

/**
 * @author Dushantha
 * @Project Test
 * @Date Oct 22, 2018
 */
public class EmailSend {

    public static void sendEmail(String paz,String user_name , String email) throws AddressException, MessagingException {

        String temp = "<html>\n" +
                "\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"main-mail\" style=\"  font-family: sans-serif;background-color: #ffffff; padding: 30px;margin: 30px; box-shadow: 0px 0px 6px 2px rgba(96, 125, 139, 0.33), inset 0px -10px 0 #607D8B;width: 50%;\">\n" +
                "        <div class=\"header-mail\" style=\"  border-bottom: 1px solid gray;padding-bottom: 9px;color: #607D8B;font-size: 15px;font-weight: 600;\">\n" +
                "            Dear <span>[username]</span>\n" +
                "        </div>\n" +
                "        <div class=\"content-mail\">\n" +
                "            <div class=\"topCont-mail\">\n" +
                "                <p>It's to inform that administrator has created a new user account for your.</p>\n" +
                "            </div>\n" +
                "            <div class=\"passowrd-mail\">\n" +
                "                <p>your password - <span style=\"   font-weight: bold;color: #ffffff;background-color: #009688;padding: 5px;\">[pass]</span></p>\n" +
                "            </div>\n" +
                "            <div class=\"bottom-mail\">\n" +
                "                <p>Welcome to GEO Location Tracking Application.\n" +
                "                   \n" +
                "                </p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"footer-mail\">\n" +
                "            <p style=\" border-top: 1px solid gray;padding-top: 9px;color: #607D8B;font-size: 15px;font-weight: 600;\">Regards,</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        String mzg = temp.replace("[username]", user_name).replace("[pass]", paz);
        System.out.println(mzg);


        // Sender's email ID needs to be mentioned
        String from = "slgayan1@gmail.com";
        String pass = "Face1911";
        // Recipient's email ID needs to be mentioned.
        String to = email;

        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from, "support@geo-fence.com"));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setContent(mzg, "text/html");

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
