package com.contactUs;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.contactUs.dao.MessageDao;


@WebFilter("/AdminLogin")
public class AdminLogin extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse) response;
		
		String adminEmail=req.getParameter("adminemail");	
		String adminPassword=req.getParameter("pass");
		
		MessageDao messageDao=new MessageDao();
		try {
			if(messageDao.check(adminEmail, adminPassword)) {
				HttpSession session=req.getSession();
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("/FetchAndStoreData");
				
				session.setAttribute("adminLogin", true);
				requestDispatcher.forward(req, res);
			}else {
			    res.sendRedirect("./admin/login/");
			}
		} catch (Exception exception) {
			System.out.println(exception);		
		}
	}


}
