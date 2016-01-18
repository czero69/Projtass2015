package tass;

import tass.controller.Controller;
import tass.view.View;

public class Tass {

	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.setView(new View());
		controller.start();
	}

}
