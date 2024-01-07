package com.gtwo.hotelreservation;


import com.google.zxing.WriterException;
import com.gtwo.hotelreservation.method.EmailSender;
import com.gtwo.hotelreservation.method.PdfGenerator;
import dao.ClientDao;
import dao.reservationDao;
import entities.Client;
import entities.RoomCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/reservation")
    public class ReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkInStr = request.getParameter("checkInDate");
        String checkOutStr = request.getParameter("checkOutDate");
        String categoryId = request.getParameter("roomCategory");
        String email = request.getParameter("email");
        String identification = request.getParameter("identification");
        String phoneNum = request.getParameter("phoneNum");
        String fullName = request.getParameter("fullName");

        try {
            // Check room availability
            reservationDao reservationDao = new reservationDao();
                if (reservationDao.isRoomAvailable(categoryId, checkInStr, checkOutStr)) {
                    ClientDao clientDao = new ClientDao();
                    Client existingClient = clientDao.getClientByEmail(email);
                    long clientId;

                    if (existingClient == null) {
                        Client newClient = new Client();
                        newClient.setEmail(email);
                        newClient.setIdentification(identification);
                        newClient.setPhoneNumber(phoneNum);
                        newClient.setFullName(fullName);

                        clientId = clientDao.insertClient(newClient);
                    } else {
                        clientId = existingClient.getId();
                    }


                    reservationDao.insertReservation(checkInStr, checkOutStr, fullName, email, Integer.parseInt(categoryId), identification, phoneNum);

                    //Pdf document
                    String roomCategory;
                    switch (categoryId){
                        case "1":
                            roomCategory = "King room";
                            break;
                        case "2":
                            roomCategory = "Single room";
                            break;
                        case "3":
                            roomCategory = "Double room";
                            break;
                        case "4":
                            roomCategory = "Suite room";
                            break;
                        default:
                            roomCategory = "no specified";
                            break;
                    }
                    PdfGenerator.generatePdf(fullName,email,identification,phoneNum,roomCategory,checkInStr,checkOutStr);
                    EmailSender.sendEmail(email,fullName,checkInStr,checkOutStr,roomCategory);
                    // Set a success message
                    request.setAttribute("message", "Reservation successful!");
                    request.setAttribute("return_msg", "true");
                    request.getRequestDispatcher("/success.jsp").forward(request, response);

                } else {
                    // Room is not available
                    request.setAttribute("message", "Room is not available for the selected dates.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error processing the reservation.");

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }

    }

