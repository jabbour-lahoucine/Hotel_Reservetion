package com.gtwo.hotelreservation;

import dao.reservationDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CheckAvailabilityServlet")
public class CheckAvailabilityServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkIn = request.getParameter("checkInDate");
        String checkOut = request.getParameter("checkOutDate");
        String categoryId = request.getParameter("categoryId");

        reservationDao dao = new reservationDao();
        boolean isAvailable = dao.isRoomAvailable(categoryId, checkIn, checkOut);

        if(isAvailable) {
            request.getRequestDispatcher("/booking.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/errorV1.jsp").forward(request, response);
        }
    }
}
