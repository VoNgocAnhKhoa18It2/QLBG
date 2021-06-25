package com.qlgiay.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qlgiay.DAO.ProductDAO;
import com.qlgiay.DAO.ProductDAOImpl;
import com.qlgiay.DB.DBConnect;
import com.qlgiay.model.Cart;
import com.qlgiay.model.Category;
import com.qlgiay.model.Product;

@WebServlet(urlPatterns = {"/product","/cart"})
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = new ProductDAOImpl(DBConnect.getConn());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = request.getServletPath();
		switch (url) {
			case "/product":
				productPage(request,response);
				break;
			case "/cart":
				cartPage(request,response);
				break;
		}
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void cartPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ArrayList<Cart> listCart = (ArrayList<Cart>) session.getAttribute("cart");
		List<Product> products = new ArrayList();
		if (listCart != null && listCart.size() > 0) {
			String in = listCart.toString();
			in = in.substring(1,in.length() - 1);
			
			products = dao.getProductFromCart(in);
		}
		request.setAttribute("pages", "cart");
		request.setAttribute("products", products);
	}

	private void productPage(HttpServletRequest request, HttpServletResponse response) {
		List<Product> products = dao.getProduct();
		List<Category> categories = dao.getCategory();
		
		request.setAttribute("pages", "product");
		request.setAttribute("products", products);
		request.setAttribute("categories", categories);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
}
