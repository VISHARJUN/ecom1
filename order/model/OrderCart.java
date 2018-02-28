package com.ecomz.order.model;

import java.util.ArrayList;
import com.ecomz.dal.OrderDAL;

public class OrderCart {
	ArrayList Cart = new ArrayList();
	
	 public void addCartItem(String OrderId, String ProductId,
			 String UnitCost, String Quantity) {
			   double subtotalCost = 0.0;
			   double totalCost = 0.0;
			   double unitCost = 0.0;
			   int quantity = 0;
			   double discount = 0.0;
			   OrderItem cartItem = new OrderItem();
			   
			   try {
			    unitCost = Double.parseDouble(UnitCost);
			    quantity = Integer.parseInt(Quantity);
			    discount = cartItem.getDiscount();
			    if(quantity>0) {
			     subtotalCost = unitCost*quantity;
			     totalCost = subtotalCost - (discount/100)*subtotalCost;
			     cartItem.setOrderId(OrderId);
			     cartItem.setProductId(ProductId);
			     cartItem.setQuantity(quantity);
			     cartItem.setUnitCost(unitCost);
			     cartItem.setTotalCost(totalCost);
			     cartItem.setDiscount(discount);
			     Cart.add(cartItem);
			     calculateCartTotal();
			     OrderDAL.createOrderItem(cartItem);
			    }
			     
			   } catch (Exception e) {
			    System.out.println("Error while adding item "+e.getMessage());
			    e.printStackTrace();
			   }
			  }
	 
	 public void updateCartItem(String OrderId, String ProductId, String Quantity, String UnitCost) {
		  double subtotalCost = 0.0;
		  double totalCost = 0.0;
		  double unitCost = 0.0;
		  double discount = 0.0;
		  int quantity = 0;
		  
		  OrderItem cartItem = null;
		  try {
		   quantity = Integer.parseInt(Quantity);
		   discount = cartItem.getDiscount();
		   if(quantity>0) {
		    unitCost = cartItem.getUnitCost();
		    subtotalCost = unitCost*quantity;
		    totalCost = subtotalCost - (discount/100)*subtotalCost;
		     cartItem.setOrderId(OrderId);
		     cartItem.setProductId(ProductId);
		     cartItem.setQuantity(quantity);
		     cartItem.setUnitCost(unitCost);
		     cartItem.setTotalCost(totalCost);
		     cartItem.setDiscount(discount);
		    calculateCartTotal();
		    OrderDAL.modifyOrderItem(cartItem);
		   }
		  } catch (Exception e) {
		   System.out.println("Error while updating cart "+e.getMessage());
		   e.printStackTrace();
		  }
	 }
		  
		  public void deleteCartItem(String ItemIndex) {
			  int itemIndex = 0;
			  try {
			   itemIndex = Integer.parseInt(ItemIndex);
			   Cart.remove(itemIndex - 1);
			   calculateCartTotal();
			  } catch(Exception e) {
			   System.out.println("Error while deleting cart item: "+e.getMessage());
			   e.printStackTrace(); 
			  }
			 }
		  
		  
		  public void calculateCartTotal() {
			  double orderTotal = 0;
			  for(int i=0;i<Cart.size();i++) {
			   OrderItem cartItem = (OrderItem) Cart.get(i);
			   orderTotal+=cartItem.getTotalCost();
			    
			  }
}
	 }
