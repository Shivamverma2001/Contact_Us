package com.contactUs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.contactUs.dao.MessageDao;

/**
 * Servlet implementation class MessageInsert
 */
@WebServlet("/MessageInsert")
public class MessageInsert extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String message=request.getParameter("message");
		
		if(name=="" || email=="" || message=="" || !email.endsWith("@gmail.com")) {
			response.sendRedirect("contactus/index.html");
		}
		else {
			MessageDao messageDao=new MessageDao();
			
			try {
				messageDao.insert(name, email, message,true);
				response.sendRedirect("contactus/");
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

}
