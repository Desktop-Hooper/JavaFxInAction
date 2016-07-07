package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        stage.setTitle("Hello World");
//        stage.setScene(new Scene(root, 300, 275));
//        stage.show();
        initUI(stage);
    }

    public void initUI(Stage stage){
        Button btn = new Button();
        btn.setText("Quit");
        btn.setOnAction(new EventHandler(){
            public void handle(Event e){
                System.out.println(
                        "Anonymous class handler. Sign in clicked."+ e.toString());
                Platform.exit();
            }
        });

        HBox root = new HBox();
        root.setPadding(new Insets(25));
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 280, 200);

        stage.setTitle("Quit button");
        stage.setScene(scene);
        stage.show();
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
