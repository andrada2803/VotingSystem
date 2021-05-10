import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VotingSystemApp extends Application {

    public static void main(String[] args){

        launch(args);
    }

        @Override
        public void start(Stage primaryStage) throws Exception {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/LogIn.fxml"));
        Parent root = myLoader.load();
        primaryStage.setTitle("Vote");
        Scene scene = new Scene(root, 600, 570);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


    /*public static void main(String[] args) {
        launch(args);


        //testing purposes

    }
*/

