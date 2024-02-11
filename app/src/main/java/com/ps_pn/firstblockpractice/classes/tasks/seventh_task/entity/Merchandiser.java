package com.ps_pn.firstblockpractice.classes.tasks.seventh_task.entity;

import java.util.List;
import java.util.Random;

public class Merchandiser {

	private String name;

	public Merchandiser(String name) {
		this.name = name;
	}

	public void setProductsInformation(List<Product> products) {
		for (Product product : products) {
			product.setName("someName");
			product.setPrice(new Random().nextInt(1000));
		}
	}

	public void registerSailing(Order order, Magazine magazine) {
		Client client = order.getClient();
		if (magazine.getBlackList().contains(client)) {
			return;
		}
		if (!order.isOrderIsPaid()) {
			magazine.getBlackList().add(order.getClient());
		} else {
			magazine.getSellingList().add(new Selling(order, this));
			magazine.getCurrentProducts().removeAll(order.getProductsInOrder());
		}
	}

	@Override
	public String toString() {
		return "Merchandiser{" +
				"name='" + name + '\'' +
				'}';
	}
}
