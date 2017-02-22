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
	private ListView<String> list_leftPane;
	
	@FXML
	private SplitPane divider;
	
	@FXML
	private Text title_leftPane;
	
	@FXML
	private Button btn_leftPane;
	
	private int toggleLeft = 0; 
	
	@FXML
	private TextField search_leftPane;
	
	@FXML
	private TableView<Course> courseTable;
	
	@FXML
	private TableColumn<Course, String> courseID;
	
	@FXML
	private TableColumn<Course, String> courseName;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
