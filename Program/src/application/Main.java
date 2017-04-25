package application;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application{
	private Stage primaryStage;
	private AnchorPane mainLayout;

	@Override
	public void start(Stage primaryStage) throws Exception{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Prodo");
		showMain();
	}
	
	private void showMain() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Program.fxml"));
		loader.setLocation(Main.class.getResource("/application/Program.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		ProgramController controller = loader.getController();
		controller.setMain(this);
	}
	
	public boolean showPopup(Lecture lecture, Topic topic, String lecOrTop, String number) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Popup.fxml"));

            // Create the dialog Stage.
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.initOwner(primaryStage);
            
            Scene popupScene = new Scene(loader.load());
            popupStage.setScene(popupScene);

            // Set the object into the controller.
            PopupController controller = loader.getController();
            controller.setStage(popupStage);
            if(lecture != null){
            	controller.editLecture(lecture);
            	popupStage.setTitle("Edit lecture");
            }else if(topic != null){
            	controller.editTopic(topic);
            	popupStage.setTitle("Edit topic");
            }else{
            	if(lecOrTop == "lecture"){
            		controller.addLecture(number);
            		popupStage.setTitle("Add lecture");
            	}else{
            		controller.addTopic(number);
            		popupStage.setTitle("Add topic");
            	}
            }

            // Show the dialog and wait until the user closes it
            popupStage.showAndWait();

            return controller.isSubmitClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public Stage getStage(){
        return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
