package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class ProgramController {
	
	@FXML
	private ListView list_leftPane;
	
	@FXML
	private Text title_leftPane;
	
	@FXML
	private Button btn_leftPane;
	
	private int toggleLeft = 0; 
	
	
	@FXML
	private void subjectNext(ActionEvent e) throws IOException{
		if (toggleLeft == 0){
			toggleLeft = 1;
			title_leftPane.setText("Find lecture");
			btn_leftPane.setText("Back");
			//System.out.println(Database.test2());
		}else{
			toggleLeft = 0;
			title_leftPane.setText("Find subject");
			btn_leftPane.setText("Next");
		}
	}
}
