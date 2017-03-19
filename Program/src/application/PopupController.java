package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopupController implements Initializable{
	
	private Boolean submitClicked = false;
	
	@FXML
	private Text popupTitle, popupTopText, popupBotText;
	
	@FXML
	private TextField popupTopInput, popupBotInput;
	
	@FXML
	private Button popupSubmit;
	
	@FXML
	private Stage popupStage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Sup");
	}
	
	public void changeText(String s){
		if(s == "course"){
			popupTitle.setText("Course");
		}
	}
	
	public Boolean isSubmitClicked(){
		return submitClicked;
	}
	
	public void setStage(Stage popupStage){
		this.popupStage = popupStage;
	}
	
	public void setLecture(Lecture lecture){
		popupTitle.setText("LOL");
	}
	
}
