package com.ps_pn.firstblockpractice.training.practise_task.first_task;

public class Main {

	public static void main(String[] args) {
		Runnable myClosure = ()-> System.out.println("I love java");

		myClosure.run();


		Main.repeatTask(10,myClosure);

	}
	public static void repeatTask (int times, Runnable task){
		for (int i = 0; i < times; i++) {
			task.run();
		}
	}

}
