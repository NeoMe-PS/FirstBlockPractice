package com.ps_pn.firstblockpractice.classes.tasks.seventh_task.entity;

public class Selling {

	private Order order;

	private Merchandiser merchandiser;

	public Selling(Order order, Merchandiser merchandiser) {
		this.order = order;
		this.merchandiser = merchandiser;
	}

	public Merchandiser getMerchandiser() {
		return merchandiser;
	}

	public Order getOrder() {
		return order;
	}

	@Override
	public String toString() {
		return "Selling{" +
				"order=" + order +
				", merchandiser=" + merchandiser +
				'}';
	}
}
