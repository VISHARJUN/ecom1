package com.ecomz.dal;

import java.sql.SQLException;
import java.sql.Statement;

import com.ecomz.order.model.OrderItem;

public class OrderDAL {
	public static void createOrderItem(OrderItem model) {
		try {
			String insertQuery = "insert into order_item values('" + model.getOrderId() + "','" + model.getProductId()
					+ "','" + model.getQuantity() + "','" + model.getUnitCost() + "','" + model.getTotalCost() + "','"
					+ model.getDiscount() + "')";
			int rows = MasterDAL.insertRow(insertQuery);
			if (rows > 0) {
				System.out.println("Item added to the cart successfully");
			}
		} catch (Exception exception) {
			System.err.println("OrderItemDAL:createOrderItem(OrderItem model):" + exception);
		}
	}

	  public static void modifyOrderItem(OrderItem model) {
		try {
			 String updateQuery = "update order_item set product_id= '" + model.getProductId() + "', quantity = '" + model.getQuantity() + "' , unit_cost = '" + model.getUnitCost() +"', total_cost = '" + model.getTotalCost() + "',discount = '" + model.getDiscount() + "'where order_id = '" + model.getOrderId() + "'";
			MasterDAL.updateRow(updateQuery);
			//Use if to have your own message.
		} catch (Exception exception) {
			System.err.println("OrderItemDAL:modifyOrderItem(OrderItem model):" + exception);
		}
	} 

	public static void removeOrderItem(OrderItem model) {
		try {
			String deleteQuery = "delete from order_item where order_id = " + model.getOrderId() + "";
			MasterDAL.deleteRow(deleteQuery);
			// Use if to have your own message.
		} catch (Exception exception) {
			System.err.println("OrderItemDAL:removeOrderItem:" + exception);
		}
	}
}
