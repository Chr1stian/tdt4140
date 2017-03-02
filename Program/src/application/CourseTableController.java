package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


/**
 * View-Controller for the Course table.
 * 
 * @author Trygve Karlsrud
 */
public class CourseTableController {
	
	@FXML
	private TextField search_leftPane;
	@FXML
	private TableView<Course> courseTable;
	@FXML
	private TableColumn<Course, String> courseID;
	@FXML
	private TableColumn<Course, String> courseName;

	private ObservableList<Course> masterData = FXCollections.observableArrayList();

	/**
	 * Test data since while we figure out the connection to the database
	 */
//	public CourseTableController() {
//		masterData.add(new Course("TDT4140", "Software Engineering"));
//		masterData.add(new Course("TDT4145", "Databases"));
//		masterData.add(new Course("TDT4180", "Human-Computer Interaction"));
//	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 * Initializes the table columns and sets up sorting and filtering.
	 */
	@FXML
	private void initialize() {
		// 0. Initialize the columns.
		courseID.setCellValueFactory(cellData -> cellData.getValue().courseIDProperty());
		courseName.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Course> filteredData = new FilteredList<>(masterData, p -> true);
		
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
}