package com.qlgiay.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qlgiay.DAO.ProductDAO;
import com.qlgiay.DAO.ProductDAOImpl;
import com.qlgiay.DB.DBConnect;
import com.qlgiay.model.Product;

@WebServlet("")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ProductDAO productDAO = new ProductDAOImpl(DBConnect.getConn());
			
			List<Product> list = productDAO.getProductRamdom(6);
			
			request.setAttribute("pages", "home");
			request.setAttribute("list", list);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
