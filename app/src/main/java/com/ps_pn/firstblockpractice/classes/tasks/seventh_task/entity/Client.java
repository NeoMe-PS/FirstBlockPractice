package com.ps_pn.firstblockpractice.classes.tasks.seventh_task.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {


	private List<Product> productsInOrder = new ArrayList<>();

	public Order makeOrder(Magazine magazine) {
		for (int i = 0; i < 3; i++) {
			int size = magazine.getCurrentProducts().size();
			Product randomProduct = magazine.getCurrentProducts().get(new Random().nextInt(size));
			productsInOrder.add(randomProduct);
		}
		int summaryPrice = payOrder();
		boolean isPaid = new Random().nextBoolean();
		return new Order(productsInOrder, summaryPrice, isPaid, this);
	}

	private int payOrder() {
		return productsInOrder.stream().mapToInt(Product::getPrice).sum();
	}
}
