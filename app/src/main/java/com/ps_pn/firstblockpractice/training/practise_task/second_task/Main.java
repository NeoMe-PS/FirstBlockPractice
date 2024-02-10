package com.ps_pn.firstblockpractice.training.practise_task.second_task;

import static com.ps_pn.firstblockpractice.training.practise_task.second_task.Directions.DOWN;
import static com.ps_pn.firstblockpractice.training.practise_task.second_task.Directions.LEFT;
import static com.ps_pn.firstblockpractice.training.practise_task.second_task.Directions.RIGHT;
import static com.ps_pn.firstblockpractice.training.practise_task.second_task.Directions.UP;

import java.util.Arrays;

public class Main {


	public static void main(String[] args) {

		Main main = new Main();
		main.moveToTreasure();
	}

	public int[] moveToTreasure() {
		int[] location = new int[]{0, 0};
		Directions[] treasureMap = new Directions[]{
				UP, UP, LEFT, DOWN, LEFT, DOWN, DOWN, RIGHT, RIGHT, DOWN, RIGHT};
		for (Directions direction : treasureMap) {
			moveTo(direction, location);
			System.out.println(Arrays.toString(location));
		}

		return location;
	}

	public int[] moveTo(Directions direction,int [] location) {
		switch (direction) {
			case RIGHT:
				location[1] += 1;
				break;
			case DOWN:
				location[0] -= 1;
				break;
			case LEFT:
				location[1] -= 1;
				break;
			case UP:
				location[0] += 1;
		}
		return location;
	}
}
