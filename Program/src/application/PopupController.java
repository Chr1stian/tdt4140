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
	
	private String lectureID, courseID, topicID;
	
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
	
	public void editLecture(Lecture lecture){
		courseID = lecture.getCourseID();
		lectureID = lecture.getLectureID();
		popupTitle.setText("Edit lecture");
		popupTopText.setText("Lecture number");
		popupTopInput.setText(lecture.getlectureNumber());
		popupTopInput.setPromptText("Lecture number");
		popupBotText.setText("Lecture name");
		popupBotInput.setText(lecture.getlectureName());
		popupBotInput.setPromptText("Lecture name");
	}
	
	public void editTopic(Topic topic){
		lectureID = topic.getLectureID();
		topicID = topic.getTopicID();
		popupTitle.setText("Edit topic");
		popupTopText.setText("Topic number");
		popupTopInput.setText(topic.getTopicNumber());
		popupTopInput.setPromptText("Topic number");
		popupBotText.setText("Topic name");
		popupBotInput.setText(topic.getTopicName());
		popupBotInput.setPromptText("Topic name");
	}
	
	public void addLecture(String number){
		courseID = number;
		popupTitle.setText("Add lecture");
		popupTopText.setText("Lecture number");
		popupTopInput.setPromptText("Lecture number");
		popupBotText.setText("Lecture name");
		popupBotInput.setPromptText("Lecture name");
	}
	
	public void addTopic(String number){
		lectureID = number;
		popupTitle.setText("Add topic");
		popupTopText.setText("Topic number");
		popupTopInput.setPromptText("Topic number");
		popupBotText.setText("Topic name");
		popupBotInput.setPromptText("Topic name");
	}
	
	@FXML
	public void submit(){
		if(validateNumber() && validateName()){
			String number = popupTopInput.getText();
			String name = popupBotInput.getText();
			popupTopInput.setText("");
			popupBotInput.setText("");
			popupSubmit.defaultButtonProperty().bind(popupSubmit.focusedProperty());
			if(popupTitle.getText() == "Add lecture"){
				Lecture lecture = new Lecture(null, courseID, number, name);
				Database.createLecture(lecture);
			}else if(popupTitle.getText() == "Add topic"){
				Topic topic = new Topic(null, lectureID, number, name);
				Database.createTopic(topic);
			}else if(popupTitle.getText() == "Edit lecture"){
				Lecture lecture = new Lecture(lectureID, courseID, number, name);
				Database.editLecture(lecture);
			}else if(popupTitle.getText() == "Edit topic"){
				Topic topic = new Topic(topicID, lectureID, number, name);
				Database.editTopic(topic);
			}else{
				System.out.println("Matcher ingen????");
			}
			submitClicked = true;
		    popupStage.close();
		}else{
			System.out.println("Ikke godkjent");
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
