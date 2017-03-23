package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ProgramController implements Initializable{
	
	//private int toggleLeft = 0;
	
	private String lastClicked = "";
	
	private String sidebarTable = "course";
	
	private Main main;
	
	@FXML
	private AnchorPane mainPane;
	
	@FXML
	private SplitPane divider;
	
	@FXML
	private Text title_leftPane, courseNameDisplay, courseIdText, lectureNumberText, lectureVotes, courseVotes;
	
	@FXML
	private Button btn_leftPane, sidebarNextButton, sidebarBackButton, sidebarAdd, sidebarEdit, sidebarDelete, submitAnswer, deleteButton;
	
	@FXML
	private TableView<Course> courseTable;
	
	@FXML
	private TableColumn<Course, String> courseCode, courseName;
	
	@FXML
	private TableView<Lecture> lectureTable;
	
	@FXML
	private TableColumn<Lecture, String> lectureNumber, lectureName;
	
	@FXML
	private TableView<Topic> topicTable;
	
	@FXML
	private TableColumn<Topic, String> topicNumber, topicName;
	
	@FXML
	private TableView<Question> questionTable;
	
	@FXML
	private TableColumn<Question, String> questionTableQuestion, questionTableAnswer; 
	
	
	@FXML
	private TableView<Rating> feedbackLectureTable, feedbackTopicTable;
	
	@FXML
	private TableColumn<Rating, String> feedbackTableLectureNum, feedbackTableLectureCol, feedbackTableLectureRating, feedbackTableLectureVotes;
	
	@FXML
	private TextField lectureIDInput, lectureNameInput, topicNameInput, topicNumberInput, search_leftPane, answerInput;

	@FXML
	private HBox star0, star0half, star1, star1half, star2, star2half, star3, star3half, star4, star4half, star5;
	
	// Initializes the program by showing the correct table (CourseTable in the sidebar)
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateCourseTable();
	}
	
	 public void setMain(Main main) {
	    this.main = main;
	 }
	
	 @FXML
	 public void edit(){
		 if(sidebarTable == "lecture"){
			 if(lectureTable.getSelectionModel().getSelectedItem() != null){
				 String courseID = courseTable.getSelectionModel().getSelectedItem().getCourseID();
				 main.showPopup(lectureTable.getSelectionModel().getSelectedItem(), null, "", "");
				 updateLectureTable(Database.lectures(courseID));
			 }
		 }else if(sidebarTable == "topic"){
			 if(topicTable.getSelectionModel().getSelectedItem() != null){
				 String lectureID = lectureTable.getSelectionModel().getSelectedItem().getLectureID();
				 main.showPopup(null, topicTable.getSelectionModel().getSelectedItem(), "", "");
				 updateTopicTable(Database.topics(lectureID));
			 }
		 }
	 }
	 
	 @FXML
	 public void add(){
		 if(sidebarTable == "lecture"){
			 String courseID = courseTable.getSelectionModel().getSelectedItem().getCourseID();
			 main.showPopup(null, null, sidebarTable, courseID);
			 updateLectureTable(Database.lectures(courseID));
		 }else if(sidebarTable == "topic"){
			 String lectureID = lectureTable.getSelectionModel().getSelectedItem().getLectureID();
			 main.showPopup(null, null, sidebarTable, lectureID);
			 updateTopicTable(Database.topics(lectureID));
		 }
	 }
	
	/*
	 * FILL SIDEBAR TABLE WITH COURSES - LECTURES - TOPICS
	 * ADD THE ABILITY TO SEARCH IN THE TABLES
	 */
	// Method for filling the table in the sidebar with courses
	private void updateCourseTable(){
		// 0. Initialize the columns.
		courseCode.setCellValueFactory(cellData -> cellData.getValue().courseCodeProperty());
		courseName.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Course> filteredData = new FilteredList<>(Database.courses(), p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search_leftPane.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Course -> {
				// If filter text is empty, display all Courses.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare course id and course name of every Course with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Course.getCourseCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches course ID.
				} else if (Course.getCourseName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches course name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Course> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(courseTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		courseTable.setItems(sortedData);
	}
	
	// Method for filling the table in the sidebar with lectures (Same idea as updateCourseTable)
	public void updateLectureTable(ObservableList<Lecture> lectureList){
		// 0. Initialize the columns.
		lectureNumber.setCellValueFactory(cellData -> cellData.getValue().lectureNumberProperty());
		lectureName.setCellValueFactory(cellData -> cellData.getValue().lectureNameProperty());
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Lecture> filteredData = new FilteredList<>(lectureList, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search_leftPane.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Lecture -> {
				// If filter text is empty, display all Courses.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare course id and course name of every Course with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Lecture.getlectureNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches course ID.
				} else if (Lecture.getlectureName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches course name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Lecture> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(lectureTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		lectureTable.setItems(sortedData);
	}

	
	// Method for filling the table in sidebar with topics
	public void updateTopicTable(ObservableList<Topic> topicList){
		// 0. Initialize the columns.
		topicNumber.setCellValueFactory(cellData -> cellData.getValue().topicNumberProperty());
		topicName.setCellValueFactory(cellData -> cellData.getValue().topicNameProperty());
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Topic> filteredData = new FilteredList<>(topicList, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search_leftPane.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Topic -> {
				// If filter text is empty, display all Courses.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare course id and course name of every Course with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Topic.getTopicNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches course ID.
				} else if (Topic.getTopicName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches course name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Topic> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(topicTable.comparatorProperty());
		
		// 5. Add data to the table.
		topicTable.setItems(topicList);
	}
	
	/*
	 * Makes it possible to delete the selected Lecture or Topic
	 */
	
	@FXML
	private void deleteItem(){
		
		if(sidebarTable == "lecture"){
		
			Lecture lecture = lectureTable.getSelectionModel().getSelectedItem();
			String courseID = courseTable.getSelectionModel().getSelectedItem().getCourseID();
		
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete Lecture");
			alert.setHeaderText("Are you sure you want to delete the selected lecture?"); 
		
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				// ... user chose OK
				lecture = lectureTable.getSelectionModel().getSelectedItem();
				courseID = courseTable.getSelectionModel().getSelectedItem().getCourseID();
				Database.deleteLecture(lecture);
				updateLectureTable(Database.lectures(courseID));
			} else {
				alert.close();// ... user chose CANCEL or closed the dialog
			}
		}
		else if(sidebarTable == "topic"){
			
		    	Topic topic = topicTable.getSelectionModel().getSelectedItem();
		    	String lectureID = lectureTable.getSelectionModel().getSelectedItem().getLectureID();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Delete Topic");
				alert.setHeaderText("Are you sure you want to delete the selected topic?");


				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
				    // ... user chose OK
			    	topic = topicTable.getSelectionModel().getSelectedItem();
			    	lectureID = lectureTable.getSelectionModel().getSelectedItem().getLectureID();
		        	Database.deleteTopic(topic);
		        	updateTopicTable(Database.topics(lectureID));
				} else {
					alert.close();// ... user chose CANCEL or closed the dialog
				}
			}
		}
	

	/*
	 * QUESTION SECTION - Display questions
	 */
	
	// Method for filling the table in the "Lecture" tab with topics
	private void updateQuestionTable(ObservableList<Question> questionList){
		// 0. Initialize the columns.
		questionTableQuestion.setCellValueFactory(cellData -> cellData.getValue().questionProperty());
		questionTableAnswer.setCellValueFactory(cellData -> cellData.getValue().answerProperty());
		// 1. Add data to the table.
		questionTable.setItems(questionList);
	}
	
	// Method for finding questions from the clicked topic
	@FXML
	private void displayQuestions() throws IOException{
		if(sidebarTable == "topic"){
			if(topicTable.getSelectionModel().getSelectedItem() != null){
				updateQuestionTable(Database.Question(topicTable.getSelectionModel().getSelectedItem().getTopicID()));
			}
		}
	}
	
	//Answering questions asked from students
	@FXML
	private void answerQuestion(){
		String answer =  answerInput.getText();
		Question question = questionTable.getSelectionModel().getSelectedItem();
		Database.answerQuestion(question, answer);
		answerInput.setText("");
		try {
			displayQuestions();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * SIDEBAR BACK - NEXT BUTTONS
	 */
	@FXML
	private void nextButton(){
		// If the sidebar table shows courses
		if(sidebarTable == "course"){
			// If you actually selected an item in the table
			if(courseTable.getSelectionModel().getSelectedItem() != null){
				search_leftPane.clear();
				sidebarTable = "lecture";
				updateLectureTable(Database.lectures(courseTable.getSelectionModel().getSelectedItem().getCourseID()));
				updateFeedbackTable(Database.lectureRating(courseTable.getSelectionModel().getSelectedItem().getCourseID()));
				updateCourseRating(Database.courseAvgRating(courseTable.getSelectionModel().getSelectedItem().getCourseID()));
				courseVotes.setVisible(true);
				courseTable.setVisible(false);
				lectureTable.setVisible(true);
				title_leftPane.setText("Lectures");
				courseIdText.setText(courseTable.getSelectionModel().getSelectedItem().getCourseCode());
			}
		// If the sidebar table shows lectures
		}else if(sidebarTable == "lecture"){
			// If you actually selected an item in the table
			if(lectureTable.getSelectionModel().getSelectedItem() != null){
				search_leftPane.clear();
				sidebarTable = "topic";
				updateTopicTable(Database.topics(lectureTable.getSelectionModel().getSelectedItem().getLectureID()));
				lectureTable.setVisible(false);
				topicTable.setVisible(true);
				title_leftPane.setText("Topics");
				lectureNumberText.setText(lectureTable.getSelectionModel().getSelectedItem().getlectureNumber());
			}
		}
		// Disable and enable the correct buttons
		enableDisableButton();
	}
	
	@FXML
	private void backButton(){
		search_leftPane.clear();
		if(sidebarTable == "topic"){
			sidebarTable = "lecture";
			lectureNumberText.setText("Not selected");
			title_leftPane.setText("Lectures");
			topicTable.setVisible(false);
			lectureTable.setVisible(true);
			updateQuestionTable(Database.Question("empty"));
			
		}else if(sidebarTable == "lecture"){
			sidebarTable = "course";
			title_leftPane.setText("Courses");
			courseIdText.setText("Not selected");
			lectureTable.setVisible(false);
			courseTable.setVisible(true);
			updateQuestionTable(Database.Question("empty"));
			hideStars();
			courseVotes.setVisible(false);
		}
		enableDisableButton();
	}
	
	private void enableDisableButton(){
		if(sidebarTable == "topic"){
			sidebarNextButton.setDisable(true);
			
		}else if(sidebarTable == "course"){
			sidebarBackButton.setDisable(true);
			sidebarAdd.setDisable(true);
			sidebarEdit.setDisable(true);
			sidebarDelete.setDisable(true);
		}else{
			sidebarNextButton.setDisable(false);
			sidebarBackButton.setDisable(false);
			sidebarAdd.setDisable(false);
			sidebarEdit.setDisable(false);
			sidebarDelete.setDisable(false);
		}
	}
	
	// FEEDBACK TAB
	
	// Method for filling the table in the "Feedback" tab with topics
	private void updateFeedbackTable(ObservableList<Rating> ratingList){
		// 0. Initialize the columns.
		feedbackTableLectureNum.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
		feedbackTableLectureCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		feedbackTableLectureRating.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
		feedbackTableLectureVotes.setCellValueFactory(cellData -> cellData.getValue().votesProperty());
		
		// 1. Add data to the table.
		feedbackLectureTable.setItems(ratingList);
	}
	
	private void updateCourseRating(ArrayList<String> rating){
		double avgRating = Double.parseDouble(rating.get(0));
		avgRating = roundToHalf(avgRating);
		showStars(avgRating);
		courseVotes.setText(rating.get(1));
	}
	
	public static double roundToHalf(double d) {
	    return Math.round(d * 2) / 2.0;
	}
	
	// Show correct amount of stars
	private void showStars(double stars){
		if(stars == 0){
			star0.setVisible(true);
		}else if(stars == 0.5){
			star0.setVisible(true);
			star0half.setVisible(true);
		}else if(stars == 1){
			star0.setVisible(true);
			star1.setVisible(true);
		}else if(stars == 1.5){
			star0.setVisible(true);
			star1half.setVisible(true);
		}else if(stars == 2){
			star0.setVisible(true);
			star2.setVisible(true);
		}else if(stars == 2.5){
			star0.setVisible(true);
			star2half.setVisible(true);
		}else if(stars == 3){
			star0.setVisible(true);
			star3.setVisible(true);
		}else if(stars == 3.5){
			star0.setVisible(true);
			star3half.setVisible(true);
		}else if(stars == 4){
			star0.setVisible(true);
			star4.setVisible(true);
		}else if(stars == 4.5){
			star0.setVisible(true);
			star4half.setVisible(true);
		}else if(stars == 5){
			star0.setVisible(true);
			star5.setVisible(true);
		}
	}
	
	// Hide all stars
	private void hideStars(){
		star0.setVisible(false);
		star0half.setVisible(false);
		star1.setVisible(false);
		star1half.setVisible(false);
		star2.setVisible(false);
		star2half.setVisible(false);
		star3.setVisible(false);
		star3half.setVisible(false);
		star4.setVisible(false);
		star4half.setVisible(false);
		star5.setVisible(false);
	}
	
}