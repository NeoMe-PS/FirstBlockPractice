package com.ps_pn.firstblockpractice.classes.tasks.seventh_task.entity;

import java.util.ArrayList;
import java.util.List;

public class Magazine {

	private List<Client> blackList = new ArrayList<>();
	private List<Product> currentProducts;
	private List<Selling> sellingList = new ArrayList<>();

	public List<Selling> getSellingList() {
		return sellingList;
	}

	public Magazine(List<Product> currentProducts) {
		this.currentProducts = currentProducts;
	}

	public List<Client> getBlackList() {
		return blackList;
	}

	public List<Product> getCurrentProducts() {
		return currentProducts;
	}
}
