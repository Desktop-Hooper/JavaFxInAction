package sample.caculatorForXinrong.ui;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * Created by 47123 on 2016/7/14.
 */
public class Home extends Application{

    private BorderPane homePane;

    private ResultPane homePaneCenter;

    private InputPane homePaneRight;

    private MenuItem yearRate = new MenuItem("投资年化利率");
    private MenuItem vipRate = new MenuItem("VIP等级系数");
    private MenuItem serverRate = new MenuItem("投资服务费率");
    private MenuItem offRate = new MenuItem("积品汇折扣");
    private MenuItem scaleRate = new MenuItem("小数位精度");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(Home.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        homePaneRight = InputPane.getInstance();
        homePaneCenter = ResultPane.getInstance();

        YearRatePane yearRatePane = YearRatePane.getInstance();

        homePane = new BorderPane();
        homePane.setPrefWidth(0);
        homePane.setPrefHeight(2000);
        homePane.setRight(homePaneRight.getPane());
        homePane.setCenter(homePaneCenter.getPane());

        Scene scene = new Scene(new VBox(),700,500);
        scene.getStylesheets().add("sample/caculatorForXinrong/ui/style.css");

        ((VBox) scene.getRoot()).getChildren().addAll(loadMenuBar(),homePane);
        stage.setScene(scene);

        Image image = new Image(Home.class.getResourceAsStream("../resource/icon/favicons.png"));
        stage.getIcons().add(image);
        stage.setTitle("投资与转让计算器v2.0");
        stage.show();
    }

    public MenuBar loadMenuBar(){
        MenuBar menuBar = new MenuBar();

        menuBar.setId("menuBar");

        // --- Menu File
        Menu menuSetting = new Menu("费率设置");
        menuSetting.getItems().addAll(yearRate,vipRate,serverRate,offRate,scaleRate);
        // --- Menu Edit
        Menu menuAbout = new Menu("关于");

        menuBar.getMenus().addAll(menuSetting, menuAbout);

        return menuBar;
    }
}
