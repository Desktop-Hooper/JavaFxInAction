package sample.caculatorForXinrong.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 *
 * Created by 47123 on 2016/7/14.
 */
public class Home extends Application{


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(Home.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane border = new BorderPane();
        border.setPrefHeight(500);
        border.setPrefWidth(700);


        border.setRight(rightPart());

        Scene scene = new Scene(border);
        scene.getStylesheets().add("sample/caculatorForXinrong/ui/style.css");
        stage.setScene(scene);

        Image image = new Image(Home.class.getResourceAsStream("../resource/icon/favicons.png"));
        stage.getIcons().add(image);
        stage.setTitle("投资与转让计算器v2.0");
        stage.show();
    }


    public BorderPane rightPart(){
        BorderPane borderPane = new BorderPane();
        borderPane.setMaxWidth(300);
        borderPane.setCenter(inputPane());
        borderPane.setBottom(loadSubmitButton());

        //bottom
        return  borderPane;
    }

    public FlowPane inputPane(){
        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(15,0,5,10));
        flow.setId("input-pane");
        flow.setVgap(10);
        flow.setHgap(10);
        flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.setPrefWidth(100);
        flow.setMaxWidth(300);

        loadInputNode(flow);


        return flow;
    }

    public HBox loadSubmitButton(){
        HBox submitB = new HBox();
        Button submitBtn = new Button("投资");
        Button rechargeBtn = new Button("转让");
        Button resetBtn = new Button("重置");

        submitB.getChildren().addAll(submitBtn,rechargeBtn,resetBtn);

        return submitB;
    }

    public void loadInputNode(Pane pane){
        Label investMoneyLB = new Label("投资金额:");
        TextField investMoneyTF = new TextField ();
        investMoneyTF.setMaxWidth(80);
        HBox investMoneyHB = new HBox();
        investMoneyHB.getChildren().addAll(investMoneyLB, investMoneyTF);
        investMoneyHB.setSpacing(3);

        Label monthLB = new Label("项目月数:");
        TextField monthTF = new TextField ();
        monthTF.setMaxWidth(80);
        HBox monthHB = new HBox();
        monthHB.getChildren().addAll(monthLB, monthTF);
        monthHB.setSpacing(3);

        Label dayLB = new Label("项目天数:");
        TextField dayTF = new TextField ();
        dayTF.setMaxWidth(80);
        HBox dayHB = new HBox();
        dayHB.getChildren().addAll(dayLB, dayTF);
        dayHB.setSpacing(3);

        Label vipLB = new Label("VIP等级:");
        TextField vipTF = new TextField ();
        vipTF.setMaxWidth(80);
        HBox vipHB = new HBox();
        vipHB.getChildren().addAll(vipLB, vipTF);
        vipHB.setSpacing(8);//

        Label yearRateLB = new Label("年化率:");
        ChoiceBox yearRateCB = new ChoiceBox();
        yearRateCB.setTooltip(new Tooltip("选择年化收益率"));
        yearRateCB.setScaleX(1.1);
        yearRateCB.setItems(FXCollections.observableArrayList(0.132,0.25));
        yearRateCB.setMaxWidth(90);
        HBox yearRateHB = new HBox();
        yearRateHB.getChildren().addAll(yearRateLB, yearRateCB);
        yearRateHB.setSpacing(20);//


        HBox doubleScoreHB = new HBox();
        ToggleButton doubleScoreTGB = new ToggleButton("双倍积分");
        doubleScoreTGB.setUserData(Color.LIGHTGREEN);
        doubleScoreTGB.setSelected(false);
        doubleScoreTGB.setId("double-toggle");
        doubleScoreHB.setMargin(doubleScoreTGB,new Insets(0, 0, 0, 60));
        doubleScoreHB.getChildren().add(doubleScoreTGB);
//        HBox separatorHB = new HBox();
//        Separator separator = new Separator();
//        separator.setPrefWidth(100);
//        separator.setOrientation(Orientation.HORIZONTAL);
//        separatorHB.getChildren().add(separator);


        Label holdDayLB = new Label("持有天数:");
        TextField holdDayTF = new TextField ();
        holdDayTF.setMaxWidth(80);
        HBox holdDayHB = new HBox();
        holdDayHB.getChildren().addAll(holdDayLB, holdDayTF);
        holdDayHB.setSpacing(3);//

        
        pane.getChildren().add(investMoneyHB);
        pane.getChildren().add(monthHB);
        pane.getChildren().add(dayHB);
        pane.getChildren().add(vipHB);
        pane.getChildren().add(yearRateHB);
        pane.getChildren().add(doubleScoreHB);
//        pane.getChildren().add(separatorHB);
        pane.getChildren().add(holdDayHB);
    }
}
