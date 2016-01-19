package tass;

import java.io.IOException;

import tass.controller.Controller;
import tass.view.View;

public class Tass {

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.setView(new View());
		try {
			controller.start();
		} catch (IOException e) {
			System.err.println("Unable to start application");
			e.printStackTrace();
		}
	}

}
