package com.gais.GmaillDemo.gmail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
@RestController
@RequestMapping("api/gmail")
public class EmailController {
    @GetMapping("/sendMail/{receiver}")
    public ResponseEntity<String> getAllAvailableTimingsForBatch(@PathVariable(name="receiver") String receiver) {
        String sender="abc@gmail.com";
        String password="xyz";
        Properties properties=new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender,password);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(receiver));
            message.setSubject("message from gais");
            String msg="welcome to gais";

            MimeBodyPart mimeBodyPart=new MimeBodyPart();
            mimeBodyPart.setContent(msg,"text/html");

            Multipart multipart=new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            MimeBodyPart attchedBodyPart=new MimeBodyPart();
            attchedBodyPart.attachFile(new File("C:/Company/Phrases of Dissagrreging.jpg"));
            multipart.addBodyPart(attchedBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException | IOException e) {throw new RuntimeException(e);}

        return new ResponseEntity<>("send message successfully",HttpStatus.OK);
    }
}
