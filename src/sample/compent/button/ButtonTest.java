package sample.compent.button;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Created by 47123 on 2016/7/8.
 */
public class ButtonTest extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("学习BUTTON");

        BorderPane basic = new BorderPane();
        basic.setMinHeight(400);
        basic.setMinWidth(400);
        basic.setCenter(groupsOfButton());

        Scene scene = new Scene(basic);

        stage.setScene(scene);


        stage.show();
    }

    public FlowPane groupsOfButton(){
         FlowPane flowPane = new FlowPane();
         flowPane.setPadding(new Insets(10,10,10,10));
         flowPane.setPrefWidth(300);
         flowPane.setMaxWidth(800);
         flowPane.setVgap(10);
         flowPane.setHgap(10);

         Button buttonNoIcon = new Button("单人影");

         Image image = new Image(ButtonTest.class.getResourceAsStream("ok.png"));
         ImageView imageView = new ImageView( image);
         Button buttonWithIcon = new Button("谢慈文",imageView);
         buttonWithIcon.setGraphicTextGap(20);

         flowPane.getChildren().add(buttonNoIcon);
         flowPane.getChildren().add(buttonWithIcon);
         return flowPane;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
