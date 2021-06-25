package com.qlgiay.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qlgiay.DAO.UserDAO;
import com.qlgiay.DAO.UserDAOImpl;
import com.qlgiay.DB.DBConnect;
import com.qlgiay.model.User;

@WebServlet(urlPatterns = { "/login", "/logout", "/register" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = new UserDAOImpl(DBConnect.getConn());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getServletPath();
		if (url.equals("/logout")) {
			HttpSession session = request.getSession();
			session.removeAttribute("userobj");
		}

		if (url.equals("/register")) {
			request.setAttribute("pages", "signup");
		} else {
			request.setAttribute("pages", "login");
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = request.getServletPath();
		switch (url) {
		case "/login":
			loginForm(request, response);
			break;
		case "/register":
			registerForm(request, response);
			break;

		default:
			loginForm(request, response);
			break;
		}
	}

	private void registerForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			String birth = request.getParameter("birth");
			String gender = request.getParameter("gender");
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			
			User us=new User();
			us.setName(name);
			us.setEmail(email);
			us.setGioiTinh(gender);
			us.setPassword(pass);
			us.setBirth(birth);
			HttpSession session=request.getSession();
			
			boolean k = dao.userRegister(us);
			if (k) {
				//System.out.println("User register Suscess..");
				session.setAttribute("succMsg","Register Suscessfully..");
				response.sendRedirect("./login");
			} else {
				//System.out.println("Something wrong on sever");
				session.setAttribute("failedMsg","Something wrong on sever");
				response.sendRedirect("./register");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loginForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if ("admin".equals(email) && "123".equals(password)) {
				User us = new User();
				us.setName("Admin");
				session.setAttribute("userobj", us);
				response.sendRedirect("admin/home.jsp");
			} else {
				User us = dao.login(email, password);
				if (us != null) {
					session.setAttribute("userobj", us);
					session.setAttribute("succMsg", "Login Success");
					response.sendRedirect("./");
				} else {
					session.setAttribute("failedMsg", "Email & password don't exit");
					response.sendRedirect("./login");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
