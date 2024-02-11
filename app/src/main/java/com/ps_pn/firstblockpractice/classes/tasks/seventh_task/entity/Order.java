package com.ps_pn.firstblockpractice.classes.tasks.seventh_task.entity;

import java.util.List;

public class Order {

	private List<Product> productsInOrder;
	private int sum;
	private boolean orderIsPaid;
	private Client client;

	public List<Product> getProductsInOrder() {
		return productsInOrder;
	}

	public int getSum() {
		return sum;
	}

	public boolean isOrderIsPaid() {
		return orderIsPaid;
	}

	public Client getClient() {
		return client;
	}

	public Order(List<Product> productsInOrder, int sum, boolean orderIsPaid, Client client) {
		this.productsInOrder = productsInOrder;
		this.sum = sum;
		this.orderIsPaid = orderIsPaid;
		this.client = client;
	}

	@Override
	public String toString() {
		return "Order{" +
				"productsInOrder=" + productsInOrder +
				", sum=" + sum +
				", orderIsPaid=" + orderIsPaid +
				", client=" + client +
				'}';
	}
}
