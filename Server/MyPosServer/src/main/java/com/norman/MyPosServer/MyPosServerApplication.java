package com.norman.MyPosServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.Scanner;

@SpringBootApplication
public class MyPosServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(MyPosServerApplication.class, args);

		Scanner cmd = new Scanner(System.in);
		boolean running = true;

		while(running) {
			switch(cmd.nextLine()) {
				case "help":
					System.out.println("TODO: Help commands");
					break;
				case "quit":
					System.out.println("Stopping server...");
					running = false;
					break;
				default:
					System.out.println("Invalid command");
					break;
			}
		}

		cmd.close();
		app.close();
	}

}
