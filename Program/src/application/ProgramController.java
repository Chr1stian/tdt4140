package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ProgramController implements Initializable{
	
	private String sidebarTable = "course";
	
	private Main main;
	
	@FXML
	private AnchorPane mainPane;
	
	@FXML
	private SplitPane divider;
	
	@FXML
	private TabPane tabLvl1, tabLvl2;
	
	@FXML
	private Text title_leftPane, courseNameDisplay, courseIdText, lectureNumberText, lectureVotes, courseVotes, courseRatingText, 
	lectureRatingText, courseRatingVotes, lectureRatingVotes, courseNotRated, lectureNotRated, feedbackTitle1, feedbackTitle2,
	questionTitle1, questionTitle2;
	
	@FXML
	private Label questionText;
	
	@FXML
	private Text statisticsOther1, statisticsOther2, statisticsOther3, statisticsOther4, statisticsOther5, statisticsOther6,
	statisticsOther7, statisticsOther8, statisticsOther9, statisticsOther10, statisticsOther11, statisticsOther12, statisticsOther13;
	
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
	private TableColumn<Question, String> questionTableQuestion, questionTableAnswer, questionTableRating; 
	
	
	@FXML
	private TableView<Rating> feedbackLectureTable;
	
	@FXML
	private TableColumn<Rating, String> feedbackTableLectureNum, feedbackTableLectureCol, feedbackTableLectureRating, feedbackTableLectureVotes;
	
	@FXML
	private TableView<Rating> feedbackTopicTable;
	
	@FXML
	private TableColumn<Rating, String> feedbackTableTopicNum, feedbackTableTopicCol, feedbackTableTopicRating, feedbackTableTopicVotes;
	
	@FXML
	private TableView<Rating> statisticsTable;
	
	@FXML
	private TableColumn<Rating, String> statisticsTableLecture, statisticsTableTopic, statisticsTableRating, statisticsTableVotes;
	
	@FXML
	private TextField lectureIDInput, lectureNameInput, topicNameInput, topicNumberInput, search_leftPane;

	@FXML
	private TextArea answerInput;
	
	@FXML
	private HBox star0, star0half, star1, star1half, star2, star2half, star3, star3half, star4, star4half, star5;
	
	@FXML
	private HBox star01, star0half1, star11, star1half1, star21, star2half1, star31, star3half1, star41, star4half1, star51;
	
	@FXML
	private StackPane feedbackTables, questionTableContainer;
	
	
	// Initializes the program by showing the correct table (CourseTable in the sidebar)
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateCourseTable();
	}
	
	 public void setMain(Main main) {
	    this.main = main;
	 }
	
	 
	 // ------------SIDEBAR------------
	 
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
		
		// 6. Make it possible to doubleclick rows in the table as an alternative to clicking the next-button
		courseTable.setRowFactory( tv -> {
		    TableRow<Course> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            nextButton();
		        }
		    });
		    return row ;
		});
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
		
		// 6. Make it possible to doubleclick rows in the table as an alternative to clicking the next-button
		lectureTable.setRowFactory( tv -> {
		    TableRow<Lecture> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            nextButton();
		        }
		    });
		    return row ;
		});
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
			if(lectureTable.getSelectionModel().getSelectedItem() != null){
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
		}
		else if(sidebarTable == "topic"){
			if(topicTable.getSelectionModel().getSelectedItem() != null){
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
	}
	
	/*
	 * SIDEBAR BACK - NEXT BUTTONS
	 */
	
	@FXML
	private void nextButton(){
		// If going from course to lecture
		if(sidebarTable == "course"){
			// If you actually selected an item in the table
			if(courseTable.getSelectionModel().getSelectedItem() != null){
				// Sidebar
				String courseID = courseTable.getSelectionModel().getSelectedItem().getCourseID();
				search_leftPane.clear();
				sidebarTable = "lecture";
				updateLectureTable(Database.lectures(courseID));
				courseTable.setVisible(false);
				lectureTable.setVisible(true);
				title_leftPane.setText("Lectures");
				courseIdText.setText(courseTable.getSelectionModel().getSelectedItem().getCourseCode());

				// Question tab
				String titleString = courseTable.getSelectionModel().getSelectedItem().getCourseCode() + ": " + courseTable.getSelectionModel().getSelectedItem().getCourseName();
				questionTitle1.setText(titleString);
				updateQuestionTable(Database.Question(courseID, "course"));
				answerInput.setText("");
				questionText.setText("Select a question from the list above");
				
				// Feedback tab
				feedbackTitle1.setText(titleString);
				updateFeedbackTableLecture(Database.lectureRating(courseID));
				updateCourseRating(Database.courseAvgRating(courseID));
				courseVotes.setVisible(true);
				courseRatingText.setVisible(true);
				courseRatingVotes.setVisible(true);
				
				// Statistics tab
				updateStatTable(Database.badTopics(courseID));
				updateOtherStats(Database.getStats(courseID));
			}
		// If going from lecture to topic
		}else if(sidebarTable == "lecture"){
			// If you actually selected an item in the table
			if(lectureTable.getSelectionModel().getSelectedItem() != null){
				// Sidebar
				search_leftPane.clear();
				sidebarTable = "topic";
				updateTopicTable(Database.topics(lectureTable.getSelectionModel().getSelectedItem().getLectureID()));
				lectureTable.setVisible(false);
				topicTable.setVisible(true);
				title_leftPane.setText("Topics");
				lectureNumberText.setText(lectureTable.getSelectionModel().getSelectedItem().getlectureNumber());

				// Question tab
				String titleText = "Lecture " + lectureTable.getSelectionModel().getSelectedItem().getlectureNumber() + ": " + lectureTable.getSelectionModel().getSelectedItem().getlectureName();
				questionTableContainer.setStyle("-fx-padding: 40-0-0-0");
				questionTitle2.setText(titleText);
				updateQuestionTable(Database.Question(lectureTable.getSelectionModel().getSelectedItem().getLectureID(), "lecture"));
				answerInput.setText("");
				questionText.setText("Select a question from the list above");
				
				// Feedback tab
				feedbackTables.setStyle("-fx-padding: 40-0-0-0");
				feedbackTitle2.setText(titleText);
				updateFeedbackTableTopic(Database.topicRating(lectureTable.getSelectionModel().getSelectedItem().getLectureID()));
				updateLectureRating(Database.lectureAvgRating(lectureTable.getSelectionModel().getSelectedItem().getLectureID()));
				feedbackLectureTable.setVisible(false);
				feedbackTopicTable.setVisible(true);
				lectureVotes.setVisible(true);
				lectureRatingText.setVisible(true);
				lectureRatingVotes.setVisible(true);
			}
		}
		// Disable and enable the correct buttons
		enableDisableButton();
	}
	
	@FXML
	private void backButton(){
		search_leftPane.clear();
		// If going from topic to lecture
		if(sidebarTable == "topic"){
			// Sidebar
			sidebarTable = "lecture";
			lectureNumberText.setText("Not selected");
			title_leftPane.setText("Lectures");
			topicTable.setVisible(false);
			lectureTable.setVisible(true);
			
			// Question tab
			questionTableContainer.setStyle("-fx-padding: 0-0-0-0");
			questionTitle2.setText("");
			updateQuestionTable(Database.Question(courseTable.getSelectionModel().getSelectedItem().getCourseID(), "course"));
			answerInput.setText("");
			questionText.setText("Select a question from the list above");
			
			// Feedback tab
			feedbackTables.setStyle("-fx-padding: 0-0-0-0");
			feedbackTitle2.setText("");
			feedbackTopicTable.setVisible(false);
			feedbackLectureTable.setVisible(true);
			hideLectureStars();
			lectureVotes.setVisible(false);
			lectureRatingText.setVisible(false);
			lectureRatingVotes.setVisible(false);
			lectureNotRated.setVisible(false);
			
		// If going from lecture to course
		}else if(sidebarTable == "lecture"){
			// Sidebar
			sidebarTable = "course";
			title_leftPane.setText("Courses");
			courseIdText.setText("Not selected");
			lectureTable.setVisible(false);
			courseTable.setVisible(true);
			
			// Question tab
			questionTitle1.setText("No course selected");
			updateQuestionTable(Database.Question("empty", null));
			answerInput.setText("");
			questionText.setText("Select a question from the list above");
			
			// Feedback tab
			feedbackTitle1.setText("No course selected");
			hideCourseStars();
			courseVotes.setVisible(false);
			courseRatingText.setVisible(false);
			courseRatingVotes.setVisible(false);
			courseNotRated.setVisible(false);
			updateFeedbackTableLecture(Database.lectureRating("empty"));
			
			// Statistics tab
			updateStatTable(Database.badTopics("empty"));
			resetOtherStats();
		}
		enableDisableButton();
	}
	
	// Enables and disables back, next, add, edit and delete buttons
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

	

	/*
	 * ------------QUESTION TAB------------
	 */
	
	// Method for finding questions from the clicked topic
	@FXML
	private void displayQuestions() throws IOException{
		if(sidebarTable == "topic"){
			if(courseTable.getSelectionModel().getSelectedItem() != null){
				updateQuestionTable(Database.Question(topicTable.getSelectionModel().getSelectedItem().getTopicID(), "topic"));
			}
		}
	}
	
	// Method for filling the table in the "Lecture" tab with topics
	private void updateQuestionTable(ObservableList<Question> questionList){
		// 0. Initialize the columns.
		questionTableQuestion.setCellValueFactory(cellData -> cellData.getValue().questionProperty());
		questionTableAnswer.setCellValueFactory(cellData -> cellData.getValue().answerProperty());
		questionTableRating.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
		// 1. Add data to the table.
		questionTable.setItems(questionList);
	}
	
	@FXML
	private void displayAnswer(){
		if(questionTable.getSelectionModel().getSelectedItem() != null){
			String question = questionTable.getSelectionModel().getSelectedItem().getQuestion();
			String answer = questionTable.getSelectionModel().getSelectedItem().getAnswer();
			questionText.setText(question);
			if(answer.matches("Not answered yet...")){
				answerInput.setText("");
			}else{
				answerInput.setText(answer);
			}
		}
	}
	
	
	//Answering questions asked from students
	@FXML
	private void answerQuestion(){
		String answer =  answerInput.getText();
		if(questionTable.getSelectionModel().getSelectedItem() != null){
			Question question = questionTable.getSelectionModel().getSelectedItem();
			Database.answerQuestion(question, answer);
			answerInput.setText("");
			try {
				if(sidebarTable == "lecture"){
					updateQuestionTable(Database.Question(courseTable.getSelectionModel().getSelectedItem().getCourseID(), "course"));
				}else if(sidebarTable == "topic"){
					updateQuestionTable(Database.Question(lectureTable.getSelectionModel().getSelectedItem().getLectureID(), "lecture"));
				}else{
					displayQuestions();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
		
	// ------------FEEDBACK TAB------------
	
	// Method for filling the table in the "Feedback" tab with lectures
	private void updateFeedbackTableLecture(ObservableList<Rating> ratingList){
		// 0. Initialize the columns.
		feedbackTableLectureNum.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
		feedbackTableLectureCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		feedbackTableLectureRating.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
		feedbackTableLectureVotes.setCellValueFactory(cellData -> cellData.getValue().votesProperty());
		
		// 1. Add data to the table.
		feedbackLectureTable.setItems(ratingList);
	}
	
	// Updates average course rating
	private void updateCourseRating(ArrayList<String> rating){
		hideCourseStars();
		courseNotRated.setVisible(false);
		if(Integer.parseInt(rating.get(1)) != 0){
			double avgRating = Double.parseDouble(rating.get(0));
			avgRating = roundToHalf(avgRating);
			showCourseStars(avgRating);
		}else{
			courseNotRated.setVisible(true);
		}
		courseVotes.setText(rating.get(1));
	}
	
	// Method for filling the table in the "Feedback" tab with topics
	private void updateFeedbackTableTopic(ObservableList<Rating> ratingList){
		// 0. Initialize the columns.
		feedbackTableTopicNum.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
		feedbackTableTopicCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		feedbackTableTopicRating.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
		feedbackTableTopicVotes.setCellValueFactory(cellData -> cellData.getValue().votesProperty());
		
		// 1. Add data to the table.
		feedbackTopicTable.setItems(ratingList);
	}
	
	//  Updates average lecture rating
	private void updateLectureRating(ArrayList<String> rating){
		hideLectureStars();
		lectureNotRated.setVisible(false);
		if(Integer.parseInt(rating.get(1)) != 0){
			double avgRating = Double.parseDouble(rating.get(0));
			avgRating = roundToHalf(avgRating);
			showLectureStars(avgRating);
		}else{
			lectureNotRated.setVisible(true);
		}
		lectureVotes.setText(rating.get(1));
	}
	
	public static double roundToHalf(double d) {
	    return Math.round(d * 2) / 2.0;
	}
	
	// Show correct amount of course stars
	private void showCourseStars(double stars){
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
	
	// Show correct amount of stars for lecture average
	private void showLectureStars(double stars){
		if(stars == 0){
			star01.setVisible(true);
		}else if(stars == 0.5){
			star01.setVisible(true);
			star0half1.setVisible(true);
		}else if(stars == 1){
			star01.setVisible(true);
			star11.setVisible(true);
		}else if(stars == 1.5){
			star01.setVisible(true);
			star1half1.setVisible(true);
		}else if(stars == 2){
			star01.setVisible(true);
			star21.setVisible(true);
		}else if(stars == 2.5){
			star01.setVisible(true);
			star2half1.setVisible(true);
		}else if(stars == 3){
			star01.setVisible(true);
			star31.setVisible(true);
		}else if(stars == 3.5){
			star01.setVisible(true);
			star3half1.setVisible(true);
		}else if(stars == 4){
			star01.setVisible(true);
			star41.setVisible(true);
		}else if(stars == 4.5){
			star01.setVisible(true);
			star4half1.setVisible(true);
		}else if(stars == 5){
			star01.setVisible(true);
			star51.setVisible(true);
		}
	}
	
	// Hide course stars
	private void hideCourseStars(){
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
	
	// Hide lecture stars
	private void hideLectureStars(){
		star01.setVisible(false);
		star0half1.setVisible(false);
		star11.setVisible(false);
		star1half1.setVisible(false);
		star21.setVisible(false);
		star2half1.setVisible(false);
		star31.setVisible(false);
		star3half1.setVisible(false);
		star41.setVisible(false);
		star4half1.setVisible(false);
		star51.setVisible(false);
	}
	
	
	// ------------STATISTICS TAB------------
	
	// Updates the "below 3 star"-table in the statistics tab
	private void updateStatTable(ObservableList<Rating> ratingList){
		// 0. Initialize the columns.
		statisticsTableLecture.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
		statisticsTableTopic.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		statisticsTableRating.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
		statisticsTableVotes.setCellValueFactory(cellData -> cellData.getValue().votesProperty());
		
		// 1. Add data to the table.
		statisticsTable.setItems(ratingList);
	}
	
	// Updates text in the "other stats" tab under statistics
	private void updateOtherStats(ArrayList<String> list){
		statisticsOther1.setText(list.get(0));
		statisticsOther2.setText(list.get(1));
		statisticsOther3.setText(list.get(2));
		statisticsOther4.setText(list.get(3));
		statisticsOther5.setText(list.get(4));
		statisticsOther6.setText(list.get(5));
		statisticsOther7.setText(list.get(6));
		statisticsOther8.setText(list.get(7));
		statisticsOther9.setText(list.get(8));
		statisticsOther10.setText(list.get(9));
		statisticsOther11.setText(list.get(10));
		statisticsOther12.setText(list.get(11));
		statisticsOther13.setText(list.get(12));
	}
	
	private void resetOtherStats(){
		statisticsOther1.setText("");
		statisticsOther2.setText("");
		statisticsOther3.setText("");
		statisticsOther4.setText("");
		statisticsOther5.setText("");
		statisticsOther6.setText("");
		statisticsOther7.setText("");
		statisticsOther8.setText("");
		statisticsOther9.setText("");
		statisticsOther10.setText("");
		statisticsOther11.setText("");
		statisticsOther12.setText("");
		statisticsOther13.setText("");
	}
	
	// ------------REFRESH BUTTON------------
	@FXML
	private void refresh(){
		// If we're in the "questions"-tab
		if(tabLvl1.getSelectionModel().getSelectedItem().getText().equals("Questions")){
			// If we're looking at questions from the whole course
			if(sidebarTable == "lecture"){
				updateQuestionTable(Database.Question(courseTable.getSelectionModel().getSelectedItem().getCourseID(), "course"));
			}
			// If we're looking at the questions from a lecture
			else if(sidebarTable == "topic"){
				updateQuestionTable(Database.Question(lectureTable.getSelectionModel().getSelectedItem().getLectureID(), "lecture"));
			}
		// If we're in the "feedback"-tab
		}else if(tabLvl1.getSelectionModel().getSelectedItem().getText().equals("Feedback")){
			// If we're looking at feedback for the whole course
			if(sidebarTable == "lecture"){
				updateFeedbackTableLecture(Database.lectureRating(courseTable.getSelectionModel().getSelectedItem().getCourseID()));
				updateCourseRating(Database.courseAvgRating(courseTable.getSelectionModel().getSelectedItem().getCourseID()));
			}
			// If we're looking at feedback for a lecture
			else if(sidebarTable == "topic"){
				updateFeedbackTableLecture(Database.lectureRating(courseTable.getSelectionModel().getSelectedItem().getCourseID()));
				updateCourseRating(Database.courseAvgRating(courseTable.getSelectionModel().getSelectedItem().getCourseID()));
				updateFeedbackTableTopic(Database.topicRating(lectureTable.getSelectionModel().getSelectedItem().getLectureID()));
				updateLectureRating(Database.lectureAvgRating(lectureTable.getSelectionModel().getSelectedItem().getLectureID()));
			}
		// If we're in the "statistics"-tab
		}else{
			// If we're in the "low rated topics"-tab
			updateStatTable(Database.badTopics(courseTable.getSelectionModel().getSelectedItem().getCourseID()));
			updateOtherStats(Database.getStats(courseTable.getSelectionModel().getSelectedItem().getCourseID()));
		}
	}
	
	
	
}