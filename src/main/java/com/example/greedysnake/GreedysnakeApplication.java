package com.example.greedysnake;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;
import com.example.greedysnake.controller.GreedySnakeGame;

@SpringBootApplication
public class GreedysnakeApplication {

	public static void main(String[] args) {
		Application.launch(GreedySnakeGame.class, args);
	}
}
