package com.gtwo.hotelreservation.method;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.net.MalformedURLException;
import java.util.Properties;

public class EmailSender {
    public static void sendEmail(String email, String name, String checkinDate , String checkoutDate, String roomCategory) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jabbourproject@gmail.com", "evqa qmhx gakk twfz");
            }
        });

        try {
            Message mimeMessage = new MimeMessage(session);

            mimeMessage.setFrom(new InternetAddress("jabbourproject@gmail.com"));

            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            mimeMessage.setSubject("confirmation of hotel reservation");

            mimeMessage.setText("Hello Mrs/Mss "+name+
                    "\n I hope this email finds you well. We are delighted to inform you that your recent reservation at Hotelier  has been successfully confirmed! We truly appreciate your choice in selecting us for your upcoming stay.\n" +
                    "\n" +
                    "Reservation Details:\n" +
                    "\n" +
                    "Check-in Date: "+checkinDate+"\n" +
                    "Check-out Date: "+checkoutDate+"\n" +
                    "Room Type: "+roomCategory+"\n" +
                    "\n" +
                    "\n" +
                    "  We want to ensure that your stay with us is nothing short of exceptional. Our team is committed to providing you with top-notch service and a comfortable environment throughout your visit. Should you have any special requests or specific requirements, please feel free to let us know, and we will do our utmost to accommodate them.\n" +
                    "\n" +
                    "  Before your arrival, here are a few details to make your stay even more enjoyable:\n" +
                    "\n" +
                    "Check-in Time: 09:00\n" +
                    "Check-out Time: 10:00\n" +
                    "Parking Information: with you reservation you reserved it too\n" +
                    "Wi-Fi Access: jbr@15447\n" +
                    "\n" +
                    "  Additionally, our concierge team is available to assist you with any local recommendations, transportation arrangements, or reservations you may need during your stay.\n" +
                    "\n" +
                    "  As a token of our appreciation, we have attached a special discount voucher for use at our on-site restaurant or spa. We hope you take advantage of this offer to enhance your experience with us.\n" +
                    "\n" +
                    "  If you have any questions or if there's anything specific you'd like us to arrange for you before your arrival, please do not hesitate to contact us at jabbourproject@gmail.com.\n" +
                    "\n" +
                    "  We look forward to welcoming you to Hotelier and ensuring that your time with us is truly memorable.\n" +
                    "\n" +
                    "  Safe travels, and we can't wait to make your stay extraordinary!\n" +
                    "\n" +
                    "Warm regards,\n" +
                    "\n" +
                    "Jabbour Lahoucine\n" +
                    "Hotel's Manager\n" +
                    "Hotelier\n" +
                    "Jabbour.lahoucine@gmail.com" );

            Transport.send(mimeMessage);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
