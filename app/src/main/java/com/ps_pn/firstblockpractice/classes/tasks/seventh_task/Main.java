package com.ps_pn.firstblockpractice.classes.tasks.seventh_task;


/*
      VII

      Задача на взаимодействие между классами. Разработать систему «Интернет-магазин».
      Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
      Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
     */


import com.ps_pn.firstblockpractice.classes.tasks.seventh_task.entity.Client;
import com.ps_pn.firstblockpractice.classes.tasks.seventh_task.entity.Magazine;
import com.ps_pn.firstblockpractice.classes.tasks.seventh_task.entity.Merchandiser;
import com.ps_pn.firstblockpractice.classes.tasks.seventh_task.entity.Order;
import com.ps_pn.firstblockpractice.classes.tasks.seventh_task.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {


	public static void main(String[] args) {
		Client client = new Client();
		Client client2 = new Client();

		Merchandiser merchandiser = new Merchandiser("Balabolov");
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());
		products.add(new Product());
		products.add(new Product());
		products.add(new Product());

		merchandiser.setProductsInformation(products);

		Magazine magazine = new Magazine(products);

		Order order1 = client.makeOrder(magazine);
		Order order2= client2.makeOrder(magazine);

		merchandiser.registerSailing(order1,magazine);
		merchandiser.registerSailing(order2,magazine);

		System.out.println(magazine.getSellingList());

	}


}
