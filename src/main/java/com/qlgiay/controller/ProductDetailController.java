package com.qlgiay.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qlgiay.DAO.ProductDAO;
import com.qlgiay.DAO.ProductDAOImpl;
import com.qlgiay.DB.DBConnect;
import com.qlgiay.model.Product;

@WebServlet("/product-detail/*")
public class ProductDetailController extends HttpServlet {
	
	ProductDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = new ProductDAOImpl(DBConnect.getConn());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		String url[] = request.getPathInfo().split("/");
		
		
		Product product = dao.getProductById(url[1]);
		List<Product> products = dao.getProductRamdom(3);
		
		request.setAttribute("product", product);
		request.setAttribute("products", products);
		request.setAttribute("pages", "product_detail");
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
