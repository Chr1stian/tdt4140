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
	
	public boolean showPopup(Lecture lecture, Topic topic, String LorT) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Popup.fxml"));

            // Create the dialog Stage.
            Stage popupStage = new Stage();
            popupStage.setTitle("Edit lecture");
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.initOwner(primaryStage);
            
            Scene popupScene = new Scene(loader.load());
            popupStage.setScene(popupScene);

            // Set the person into the controller.
            PopupController controller = loader.getController();
            controller.setStage(popupStage);
            if(lecture != null){
            	controller.setLecture(lecture);
            }else if(topic != null){
            	controller.setTopic(topic);
            }else{
            	if(LorT == "lecture"){
            		controller.addLecture();
            	}else{
            		controller.addTopic();
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
	/*
    Stage popupStage = new Stage();
    //dialog.initOwner(mainPane.getScene().getWindow());
    //dialog.setTitle("Wololololo");
    //dialog.setHeaderText("Use this dialog to do incredible things");
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(Main.class.getResource("Popup.fxml"));
    try {
        //dialog.getDialogPane().setContent(fxmlLoader.load());
    	Scene popupScene = new Scene(fxmlLoader.load());
    	popupStage.setScene(popupScene);

    } catch(IOException e) {
        System.out.println("Couldn't load the dialog");
        e.printStackTrace();
        return;
    }
    //PopupController.changeText(sidebarTable);
    popupStage.showAndWait();
}*/


	
	public Stage getStage(){
        return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
