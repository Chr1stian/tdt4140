package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController {
	//@FXML
	//private Button btn_login;
	
	//enter fxml and java code for a login function here
	@FXML
	private void login(ActionEvent e) throws IOException{
		System.out.println("Du har trykket login");
		
		//System.out.println(Database.loadSingleValue("SELECT * FROM bruker", ""));
		System.out.println(Database.test());
	}
}
