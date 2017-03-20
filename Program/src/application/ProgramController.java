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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ProgramController implements Initializable{
	
	//private int toggleLeft = 0;
	
	String lastClicked = "";
	
	String sidebarTable = "course";
	
	@FXML
	private AnchorPane mainPane;
	
	@FXML
	private SplitPane divider;
	
	@FXML
	private Text title_leftPane, courseNameDisplay, courseIdText, lectureNumberText;
	
	@FXML
	private Button btn_leftPane, sidebarNextButton, sidebarBackButton, submitAnswer;
	
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
	private TableColumn<Question, String> questionTableQuestion; 
	
	
	@FXML
	private TextField lectureIDInput, lectureNameInput, topicNameInput, topicNumberInput, search_leftPane, answerInput;

	
	// Initializes the program by showing the correct table (CourseTable in the sidebar)
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lectureTable.setVisible(false);
		updateCourseTable();
	}
	
	@FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        //Stage popupStage = new Stage();
        //dialog.initOwner(mainPane.getScene().getWindow());
        //dialog.setTitle("Wololololo");
        //dialog.setHeaderText("Use this dialog to do incredible things");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("PopUpWindow.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        /*if(result.isPresent() && result.get() == ButtonType.OK) {
            PopupController controller = fxmlLoader.getController();
            /*TodoItem newItem = controller.processResults();
            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            todoListView.getSelectionModel().select(newItem);
            System.out.println("OK pressed");
        } else {
            System.out.println("Cancel pressed");
        }*/
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
	private void updateLectureTable(ObservableList<Lecture> lectureList){
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
	private void updateTopicTable(ObservableList<Topic> topicList){
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
	
	
	// I think this is unneeded, but I'll keep it just in case.
	/*
	// Method for going back and forth between showing courses or lectures in the sidebar	
	@FXML
	private void subjectNext(ActionEvent e) throws IOException{
		// If we're showing courses, toggleLeft = 0
		if (toggleLeft == 0){
			if(courseTable.getSelectionModel().getSelectedItem() != null){
				toggleLeft = 1;
				updateLectureTable(Database.lectures(courseTable.getSelectionModel().getSelectedItem().getCourseID()));
				courseTable.setVisible(false);
				lectureTable.setVisible(true);
				title_leftPane.setText("Find lecture");
				btn_leftPane.setText("Back");
				courseNameDisplay.setText(courseTable.getSelectionModel().getSelectedItem().getCourseCode());
			}else{
				System.out.println("Lag en eller annen feilmelding her som ber brukeren velge ett emne");
			}
		}
		// If the courses are not showing, the lectures are. toggleLeft = 1
		else{
			toggleLeft = 0;
			lastClicked = "";
			updateTopicTable(Database.Topic("empty"));
			lectureTable.setVisible(false);
			courseTable.setVisible(true);
			title_leftPane.setText("Find course");
			btn_leftPane.setText("Next");
			courseNameDisplay.setText("Course");
		}
	}*/
	
	/*
	 * Show topics after clicking a lecture
	 */
	/*
	@FXML
	private void displayTopics() throws IOException{
		if(lectureTable.getSelectionModel().getSelectedItem() != null & lectureTable.getSelectionModel().getSelectedItem().getlectureNumber() != lastClicked){
			lastClicked = lectureTable.getSelectionModel().getSelectedItem().getlectureNumber();
			updateTopicTable(Database.Topic(lectureTable.getSelectionModel().getSelectedItem().getLectureID()));
		}
	}*/
	
	
	/*
	 * LECTURE ADD - DELETE - UPDATE
	 */
	
	@FXML
	private void addLecture(){
		String id = lectureIDInput.getText();
		String name = lectureNameInput.getText();
		String courseID = courseTable.getSelectionModel().getSelectedItem().getCourseID();
		
		Lecture lecture = new Lecture(null, courseID, id, name);
		Database.createLecture(lecture);
		lectureIDInput.setText("");
		lectureNameInput.setText("");
		updateLectureTable(Database.lectures(courseID));
	}
	
	@FXML
	private void editLecture(){
		String id = lectureTable.getSelectionModel().getSelectedItem().getLectureID();
		String name = lectureTable.getSelectionModel().getSelectedItem().getlectureName();
		String courseID = courseTable.getSelectionModel().getSelectedItem().getCourseID();
		
		Lecture lecture = new Lecture(null, courseID, id, name);
		Database.editLecture(lecture);
		lectureIDInput.setText(id);
		lectureNameInput.setText(name);
		updateLectureTable(Database.lectures(courseID));
	}
	
	@FXML
	private void deleteLecture(){
		Lecture lecture = lectureTable.getSelectionModel().getSelectedItem();
		String courseID = courseTable.getSelectionModel().getSelectedItem().getCourseID();
		Database.deleteLecture(lecture);
		updateLectureTable(Database.lectures(courseID));
	}
	
	/*
	 *  TOPIC ADD - DELETE - UPDATE
	 */
	@FXML
	private void addTopic(){
		String number = topicNumber.getText();
		String name = topicNameInput.getText();
		String lectureID = lectureTable.getSelectionModel().getSelectedItem().getLectureID();
		
		Topic topic = new Topic(null, lectureID, number , name);
		Database.createTopic(topic);
		topicNumber.setText("");
		topicNameInput.setText("");
		updateTopicTable(Database.Topic(lectureID));
	}
	
	@FXML
	private void editTopic(){
		String number = topicTable.getSelectionModel().getSelectedItem().getTopicNumber();
		String name = topicTable.getSelectionModel().getSelectedItem().getTopicName();
		String topicID = topicTable.getSelectionModel().getSelectedItem().getLectureID();
		
		Topic topic = new Topic(null, topicID, number , name);
		Database.editTopic(topic);
		topicNumber.setText(number);
		topicNameInput.setText(name);
		updateTopicTable(Database.Topic(topicID));
	}
	
	@FXML
	private void deleteTopic(){
		Topic topic = topicTable.getSelectionModel().getSelectedItem();
		String lectureID = lectureTable.getSelectionModel().getSelectedItem().getLectureID();
		Database.deleteTopic(topic);
		updateTopicTable(Database.Topic(lectureID));

	}
	
	/*
	 * QUESTION SECTION - Display questions
	 */
	
	// Method for filling the table in the "Lecture" tab with topics
	private void updateQuestionTable(ObservableList<Question> questionList){
		// 0. Initialize the columns.
		questionTableQuestion.setCellValueFactory(cellData -> cellData.getValue().questionNameProperty());
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
		String questionID = questionTable.getSelectionModel().getSelectedItem().getquestionID();
		String topicID = topicTable.getSelectionModel().getSelectedItem().getTopicID();
		String answerQuestion =  answerInput.getText();
		//String userID = questionTable.getSelectionModel().getSelectedItem.getuserID();
		
		//Får en SQLExcepetion: Field "userID" doesn't have a default value....
		
		Question question = new Question(questionID, topicID, null, null ,answerQuestion);
		Database.answerQuestion(question);
		answerInput.setText("");
	}
	
	/*
	 * SIDEBAR BACK - NEXT BUTTONS
	 */
	@FXML
	private void nextButton(){
		if(sidebarTable == "course"){
			if(courseTable.getSelectionModel().getSelectedItem() != null){
				search_leftPane.clear();
				sidebarTable = "lecture";
				updateLectureTable(Database.lectures(courseTable.getSelectionModel().getSelectedItem().getCourseID()));
				courseTable.setVisible(false);
				lectureTable.setVisible(true);
				title_leftPane.setText("Lectures");
				courseIdText.setText(courseTable.getSelectionModel().getSelectedItem().getCourseCode());
			}
		}else if(sidebarTable == "lecture"){
			if(lectureTable.getSelectionModel().getSelectedItem() != null){
				search_leftPane.clear();
				sidebarTable = "topic";
				updateTopicTable(Database.Topic(lectureTable.getSelectionModel().getSelectedItem().getLectureID()));
				lectureTable.setVisible(false);
				topicTable.setVisible(true);
				title_leftPane.setText("Topics");
				lectureNumberText.setText(lectureTable.getSelectionModel().getSelectedItem().getlectureNumber());
			}
		}
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
		}
		enableDisableButton();
	}
	
	private void enableDisableButton(){
		if(sidebarTable == "topic"){
			sidebarNextButton.setDisable(true);
		}else if(sidebarTable == "course"){
			sidebarBackButton.setDisable(true);
		}else{
			sidebarNextButton.setDisable(false);
			sidebarBackButton.setDisable(false);
		}
	}
	
	/*
	 * SIDEBAR ADD - EDIT - DELETE
	 */
}