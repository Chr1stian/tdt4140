package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ProgramController implements Initializable{
	
	@FXML
	private SplitPane divider;
	
	@FXML
	private Text title_leftPane, courseNameDisplay;
	
	@FXML
	private Button btn_leftPane;
	
	private int toggleLeft = 0; 
	
	String lastClicked = "";
	
	@FXML
	private TextField search_leftPane;
	
	@FXML
	private TableView<Course> courseTable;
	
	@FXML
	private TableColumn<Course, String> courseID;
	
	@FXML
	private TableColumn<Course, String> courseName;
	
	@FXML
	private TableView<Lecture> lectureTable;
	
	@FXML
	private TableColumn<Lecture, String> lectureNumber;
	
	@FXML
	private TableColumn<Lecture, String> lectureName;
	
	@FXML
	private TableView<Topic> lectureCourseTable;
	
	@FXML
	private TableColumn<Topic, String> lectureCourseNumber;
	
	@FXML
	private TableColumn<Topic, String> lectureCourseTopic;
	
	@FXML
	private TextField lectureIDInput;
	
	@FXML
	private TextField lectureNameInput;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lectureTable.setVisible(false);
		updateCourseTable();
	}
	
	// Method for filling the leftside table with courses
	private void updateCourseTable(){
		// 0. Initialize the columns.
		courseID.setCellValueFactory(cellData -> cellData.getValue().courseIDProperty());
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
				
				if (Course.getcourseID().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches course ID.
				} else if (Course.getcourseName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
	
	// Method for filling the leftside table with lectures
	private void updateLectureTable(ObservableList<Lecture> lectureList){
		// 0. Initialize the columns.
		// Må kanskje ha en ny tabell?
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

	
	// Method for filling the leftside table with lectures
	private void updateLectureCourseTable(ObservableList<Topic> topicList){
		// 0. Initialize the columns.
		// Må kanskje ha en ny tabell?
		lectureCourseNumber.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
		lectureCourseTopic.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Topic> filteredData = new FilteredList<>(topicList, p -> true);
		
		/*// Don't need search for this table
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
		});*/
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Topic> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(lectureCourseTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		lectureCourseTable.setItems(sortedData);
	}
	
	@FXML
	private void subjectNext(ActionEvent e) throws IOException{
		//updateLectureTable(Database.lectures(courseTable.getSelectionModel().getSelectedItem().getDBID()));
		//Database.lectures(courseTable.getSelectionModel().getSelectedItem().getDBID());
		if (toggleLeft == 0){
			if(courseTable.getSelectionModel().getSelectedItem() != null){
				toggleLeft = 1;
				updateLectureTable(Database.lectures(courseTable.getSelectionModel().getSelectedItem().getDBID()));
				courseTable.setVisible(false);
				lectureTable.setVisible(true);
				title_leftPane.setText("Find lecture");
				btn_leftPane.setText("Back");
				courseNameDisplay.setText(courseTable.getSelectionModel().getSelectedItem().getcourseID());
			}else{
				System.out.println("Lag en eller annen feilmelding her som ber brukeren velge ett emne");
			}
		}else{
			toggleLeft = 0;
			lastClicked = "";
			updateLectureCourseTable(Database.Topic("empty"));
			lectureTable.setVisible(false);
			courseTable.setVisible(true);
			//updateCourseTable();
			title_leftPane.setText("Find course");
			btn_leftPane.setText("Next");
			courseNameDisplay.setText("Course");
		}
	}
	
	@FXML
	private void displayTopics() throws IOException{
		if(lectureTable.getSelectionModel().getSelectedItem() != null & lectureTable.getSelectionModel().getSelectedItem().getlectureNumber() != lastClicked){
			lastClicked = lectureTable.getSelectionModel().getSelectedItem().getlectureNumber();
			updateLectureCourseTable(Database.Topic(lectureTable.getSelectionModel().getSelectedItem().getLectureID()));
		}
	}
	
	@FXML
	private void addLecture(){
		String id = lectureIDInput.getText();
		String name = lectureNameInput.getText();
		String courseID = courseTable.getSelectionModel().getSelectedItem().getDBID();
		
		Lecture lecture = new Lecture(null, courseID, id, name);
		Database.createLecture(lecture);
	}
}
