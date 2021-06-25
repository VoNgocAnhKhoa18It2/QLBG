package com.qlgiay.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qlgiay.DAO.AjaxDAO;
import com.qlgiay.DAO.AjaxDAOImpl;
import com.qlgiay.DAO.UserDAO;
import com.qlgiay.DAO.UserDAOImpl;
import com.qlgiay.DB.DBConnect;
import com.qlgiay.model.Cart;
import com.qlgiay.model.Product;

@WebServlet(urlPatterns = { "/check-email", "/product-category", "/add-cart", "/empty-cart", "/edit-cart",
		"/add-cart-detail" })
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AjaxDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = new AjaxDAOImpl(DBConnect.getConn());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getServletPath();
		switch (url) {
		case "/empty-cart":
			emptyCart(request, response);
			break;
		}
	}

	private void emptyCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		session.removeAttribute("cart");

		response.getWriter().append(0 + "," + 0);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getServletPath();
		switch (url) {
		case "/check-email":
			checkEamil(request, response);
			break;
		case "/product-category":
			getProductFromCategory(request, response);
			break;
		case "/add-cart":
			addCart(request, response);
			break;
		case "/edit-cart":
			editCart(request, response);
			break;
		case "/add-cart-detail":
			editCartDetail(request, response);
			break;
		default:
			break;
		}
	}

	private void editCartDetail(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			int gia = Integer.parseInt(request.getParameter("total").toString());
			int qty = Integer.parseInt(request.getParameter("qty").toString());
			int sl = 0;
			int sum = 0;
			HttpSession session = request.getSession();
			ArrayList<Cart> listCart = (ArrayList<Cart>) session.getAttribute("cart");

			if (!id.equals("*")) {
				if (listCart != null) {
					boolean add = false;
					for (Cart cart : listCart) {
						if (cart.getId().equals(id)) {
							if (qty+cart.getSl() <= 10) {
								cart.setSl(cart.getSl() + qty);
							}
							add = true;
							break;
						}
					}

					if (!add) {
						listCart.add(new Cart(id, qty, gia));
					}
				} else {
					ArrayList<Cart> cart = new ArrayList<Cart>();
					cart.add(new Cart(id, qty, gia));
					listCart = cart;
				}
				session.setAttribute("cart", listCart);
			}

			if (listCart != null) {
				for (Cart cart : listCart) {
					sl = sl + cart.getSl();
					sum = sum + cart.getGia() * cart.getSl();
				}
			}

			response.getWriter().append(sl + "," + sum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void editCart(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			int qty = Integer.parseInt(request.getParameter("qty").toString());
			int sl = 0;
			int sum = 0;
			int total = 0;
			HttpSession session = request.getSession();
			ArrayList<Cart> listCart = (ArrayList<Cart>) session.getAttribute("cart");

			if (listCart != null) {
				for (Cart cart : listCart) {
					if (cart.getId().equals(id)) {
						if (qty == 0) {
							listCart.remove(cart);
						} else {
							if (qty <= 10 && qty > 0) {
								cart.setSl(qty);
							}
							total = cart.getGia() * cart.getSl();
						}
						break;
					}
				}
			}
			session.setAttribute("cart", listCart);

			if (listCart != null) {
				for (Cart cart : listCart) {
					sl = sl + cart.getSl();
					sum = sum + cart.getGia() * cart.getSl();
				}
			}

			response.getWriter().append(sl + "," + sum + "," + total);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addCart(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			int gia = Integer.parseInt(request.getParameter("gia").toString());
			int sl = 0;
			int sum = 0;
			HttpSession session = request.getSession();
			ArrayList<Cart> listCart = (ArrayList<Cart>) session.getAttribute("cart");

			if (!id.equals("*")) {
				if (listCart != null) {
					boolean add = false;
					for (Cart cart : listCart) {
						if (cart.getId().equals(id)) {
							if (gia == 0) {
								listCart.remove(cart);
							} else {
								if (cart.getSl()+1 <= 10) {
									cart.setSl(cart.getSl() + 1);
								}
							}
							add = true;
							break;
						}
					}

					if (!add) {
						listCart.add(new Cart(id, 1, gia));
					}
				} else {
					ArrayList<Cart> cart = new ArrayList<Cart>();
					cart.add(new Cart(id, 1, gia));
					listCart = cart;
				}
				session.setAttribute("cart", listCart);
			}

			if (listCart != null) {
				for (Cart cart : listCart) {
					sl = sl + cart.getSl();
					sum = sum + cart.getGia() * cart.getSl();
				}
			}

			response.getWriter().append(sl + "," + sum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getProductFromCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id_dm");
			List<Product> list = dao.getProductForCategoru(id);
			request.setAttribute("products", list);

			request.getRequestDispatcher("/ajax/product.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkEamil(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		boolean result = dao.checkEmail(email);
		response.getWriter().append(String.valueOf(result));
	}

}
