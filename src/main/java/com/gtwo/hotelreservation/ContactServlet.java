package com.gtwo.hotelreservation;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;


import java.io.IOException;
import java.util.Properties;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Message: " + message);

        sendEmail(name, email, message);

        request.getRequestDispatcher("/success.jsp").forward(request, response);
    }

    private void sendEmail(String name, String email, String message) {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication("jabbourproject@gmail.com", "evqa qmhx gakk twfz");
            }
        });

        try {
            Message mimeMessage = new MimeMessage(session);

            mimeMessage.setFrom(new InternetAddress(email));

            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse("jabbour.lahoucine@gmail.com"));

            mimeMessage.setSubject("New Contact Form Submission");

            mimeMessage.setText("Name: " + name + "\nEmail: " + email + "\nMessage: " + message);

            Transport.send(mimeMessage);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
