package com.contactUs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.contactUs.dao.MessageDao;


@WebServlet("/ToggleActiveServlet")
public class ToggleActiveServlet extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int messageId = Integer.parseInt(request.getParameter("id"));

		MessageDao messageDao = new MessageDao();
	    try {
	        messageDao.changeTypeOfMessage(messageId);
	        
	        RequestDispatcher requestDispatcher=request.getRequestDispatcher("FetchAndStoreData");
	        requestDispatcher.forward(request, response);
	        
	    } catch (Exception exception) {
	        exception.printStackTrace();
	    }

	}

}
