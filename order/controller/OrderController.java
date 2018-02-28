package com.ecomz.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecomz.order.model.OrderCart;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String choice = request.getParameter("select");
		  if(choice!=null && !choice.equals("")) {
		   if(choice.equals("Add")) {
		    addCart(request);
		   } else if (choice.equals("Update")) {
		    updateCart(request);
		   } else if (choice.equals("Delete")) {
		    deleteCart(request);
		   }
		  }
		 }


protected void addCart(HttpServletRequest request) {
	  HttpSession session = request.getSession();
	  String strorderId = request.getParameter("orderid");
	  String strproductId = request.getParameter("productid");
	  String strunitCost = request.getParameter("unitcost");
	  String strquantity = request.getParameter("quantity");
	   
	  OrderCart cart = null;
	   
	  Object objCart = session.getAttribute("cart");
	 
	  if(objCart!=null) {
	   cart = (OrderCart) objCart ;
	  } else {
	   cart = new OrderCart();
	   session.setAttribute("cart",cart);
	  }
	  cart.addCartItem(strorderId, strproductId, strunitCost, strquantity);
}	

protected void updateCart(HttpServletRequest request) {
	
	  HttpSession session = request.getSession();
	  String strorderId = request.getParameter("orderid");
	  String strproductId = request.getParameter("productid");
	  String strQuantity = request.getParameter("quantity");
	  String strunitCost = request.getParameter("unitcost");
	 
	  OrderCart cart = null;
	   
	  Object objCart = session.getAttribute("cart");
	  if(objCart!=null) {
	   cart = (OrderCart) objCart ;
	  } else {
	   cart = new OrderCart();
	  }
	  cart.updateCartItem(strorderId,strproductId,strQuantity,strunitCost);
	 }

protected void deleteCart(HttpServletRequest request) {
	  HttpSession session = request.getSession(); 

	  String strItemIndex = request.getParameter("itemIndex");
	  OrderCart cart = null;
	   
	  Object objCart = session.getAttribute("cart");
	  if(objCart!=null) {
	   cart = (OrderCart) objCart ;
	  } else {
	   cart = new OrderCart();
	  }
	  cart.deleteCartItem(strItemIndex);
	 }
}
