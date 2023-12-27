package com.contactUs;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.contactUs.dao.MessageDao;
import com.contactUs.data.DetailsOfMessage;

@WebServlet("/FetchAndStoreData")
public class FetchAndStoreData extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
        MessageDao messageDao = new MessageDao();
        ArrayList<DetailsOfMessage> detailsOfMessage = new ArrayList<>();
        
        try {
            ResultSet resultSet = messageDao.getMessangerData();

            while (resultSet.next()) {
                DetailsOfMessage detailOfSingleMessage = new DetailsOfMessage();
                
                detailOfSingleMessage.setId(resultSet.getInt(1));
                detailOfSingleMessage.setName(resultSet.getString(2));
                detailOfSingleMessage.setEmail(resultSet.getString(3));
                detailOfSingleMessage.setMessage(resultSet.getString(4));
                detailOfSingleMessage.setActive(resultSet.getBoolean(5));

                detailsOfMessage.add(detailOfSingleMessage);
            }

            request.setAttribute("detailsOfMessage", detailsOfMessage);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        HttpSession session=request.getSession();
        
        session.setAttribute("detailsOfMessage", detailsOfMessage);
        response.sendRedirect("./admin/contactus/request/");
    }
}
