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
		popupTitle.setText("Edit lecture");
		popupTopText.setText("Topic number");
		popupTopInput.setText(lecture.getlectureNumber());
		popupTopInput.setPromptText("Lecture number");
		popupBotText.setText("Topic name");
		popupBotInput.setText(lecture.getlectureName());
		popupBotInput.setPromptText("Lecture name");
	}
	
	public void setTopic(Topic topic){
		popupTitle.setText("Edit topic");
		popupTopText.setText("Topic number");
		popupTopInput.setText(topic.getTopicNumber());
		popupTopInput.setPromptText("Topic number");
		popupBotText.setText("Topic name");
		popupBotInput.setText(topic.getTopicName());
		popupBotInput.setPromptText("Topic name");
	}
	
	public void addLecture(){
		popupTitle.setText("Add lecture");
		popupTopText.setText("Lecture number");
		popupTopInput.setPromptText("Lecture number");
		popupBotText.setText("Lecture name");
		popupBotInput.setPromptText("Lecture name");
	}
	
	public void addTopic(){
		popupTitle.setText("Add topic");
		popupTopText.setText("Topic number");
		popupTopInput.setPromptText("Topic number");
		popupBotText.setText("Topic name");
		popupBotInput.setPromptText("Topic name");
	}
	
	@FXML
	public void submit(){
		System.out.println(popupTitle.getText());
		if(validateNumber()){
			System.out.println("Riktig nummerformat");
		}else{
			System.out.println("Feil nummerformat");
		}
		if(validateName()){
			System.out.println("Riktig navnformat");
		}else{
			System.out.println("Feil navnformat");
		}
	}
	
	private Boolean validateNumber(){
		if(popupTopInput.getText().matches("[1-9][0-9]*") && popupTopInput.getText().length() <= 11 && !popupTopInput.getText().isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	private Boolean validateName(){
		if(popupBotInput.getText().length() <= 60 && !popupBotInput.getText().isEmpty()){
			return true;
		}else{
			return false;
		}
	}
}
