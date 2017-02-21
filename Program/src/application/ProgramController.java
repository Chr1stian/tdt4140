package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.text.Text;

public class ProgramController implements Initializable{
	
	@FXML
	private ListView<String> list_leftPane;
	
	@FXML
	private SplitPane divider;
	
	@FXML
	private Text title_leftPane;
	
	@FXML
	private Button btn_leftPane;
	
	private int toggleLeft = 0; 
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//List<String> values = Arrays.asList("one", "two", "three");
		//list_leftPane.setItems(FXCollections.observableList(values));
		list_leftPane.setItems(FXCollections.observableList(Database.courses()));
	}
	
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
