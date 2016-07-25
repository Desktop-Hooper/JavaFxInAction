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


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(Home.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane border = new BorderPane();
        border.setPrefWidth(0);
        border.setPrefHeight(2000);
        border.setRight(rightPart());

        Scene scene = new Scene(new VBox(),700,500);
        scene.getStylesheets().add("sample/caculatorForXinrong/ui/style.css");

        ((VBox) scene.getRoot()).getChildren().addAll(loadMenuBar(),border);
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
        MenuItem yearRate = new MenuItem("投资年化利率");
        MenuItem vipRate = new MenuItem("VIP等级系数");
        MenuItem serverRate = new MenuItem("投资服务费率");
        MenuItem offRate = new MenuItem("积品汇折扣");
        MenuItem scaleRate = new MenuItem("小数位精度");
        menuSetting.getItems().addAll(yearRate,vipRate,serverRate,offRate,scaleRate);
        // --- Menu Edit
        Menu menuAbout = new Menu("关于");

        menuBar.getMenus().addAll(menuSetting, menuAbout);

        return menuBar;
    }

    public Pane rightPart(){
        BorderPane rightPartBorderPane = new BorderPane();
        rightPartBorderPane.setId("input-pane");
        rightPartBorderPane.setMaxWidth(260);
        rightPartBorderPane.setCenter(inputPane());
        rightPartBorderPane.setBottom(loadSubmitButton());

        return  rightPartBorderPane;
    }


    public Pane inputPane(){
        VBox vBox = new VBox();
        vBox.setId("input-vbox");
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        loadInputNode(vBox);

        return vBox;
    }

    public Pane loadSubmitButton(){
        Button submitBtn = new Button("投资查询");
        Button rechargeBtn = new Button("转让查询");
        Button resetBtn = new Button("清空输入");

        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.BASELINE_CENTER);
        flowPane.setPadding(new Insets(10,10,20,10));
        flowPane.setHgap(15);
        flowPane.setVgap(20);
        flowPane.setPrefWrapLength(70);
        flowPane.setPrefWidth(70);
        flowPane.setMaxWidth(300);
        flowPane.getChildren().addAll(submitBtn,rechargeBtn,resetBtn);

        return flowPane;
    }

    public void loadInputNode(Pane pane){
        Label investMoneyLB = new Label("投资金额:");
        TextField investMoneyTF = new TextField ();
        investMoneyTF.setMaxWidth(80);
        HBox investMoneyHB = new HBox();
        investMoneyHB.getChildren().addAll(investMoneyLB, investMoneyTF);
        investMoneyHB.setSpacing(3);
        investMoneyHB.setAlignment(Pos.CENTER);

        Label monthLB = new Label("项目月数:");
        TextField monthTF = new TextField ();
        monthTF.setMaxWidth(80);
        HBox monthHB = new HBox();
        monthHB.getChildren().addAll(monthLB, monthTF);
        monthHB.setSpacing(3);
        monthHB.setAlignment(Pos.CENTER);

        Label dayLB = new Label("项目天数:");
        TextField dayTF = new TextField ();
        dayTF.setMaxWidth(80);
        HBox dayHB = new HBox();
        dayHB.getChildren().addAll(dayLB, dayTF);
        dayHB.setSpacing(3);
        dayHB.setAlignment(Pos.CENTER);

        Label vipLB = new Label("VIP等级:");
        TextField vipTF = new TextField ();
        vipTF.setMaxWidth(80);
        HBox vipHB = new HBox();
        vipHB.getChildren().addAll(vipLB, vipTF);
        vipHB.setSpacing(8);//
        vipHB.setAlignment(Pos.CENTER);

        Label yearRateLB = new Label("年化率:");
        ChoiceBox yearRateCB = new ChoiceBox();
        yearRateCB.setTooltip(new Tooltip("选择年化收益率"));
        yearRateCB.setScaleX(1.1);
        yearRateCB.setItems(FXCollections.observableArrayList(0.132,0.25));
        yearRateCB.setMaxWidth(90);
        HBox yearRateHB = new HBox();
        yearRateHB.getChildren().addAll(yearRateLB, yearRateCB);
        yearRateHB.setSpacing(20);//
        yearRateHB.setAlignment(Pos.CENTER);

        HBox doubleScoreHB = new HBox();
        ToggleButton doubleScoreTGB = new ToggleButton("双倍积分");
        doubleScoreTGB.setUserData(Color.LIGHTGREEN);
        doubleScoreTGB.setSelected(false);
        doubleScoreTGB.setId("double-toggle");
        doubleScoreHB.setMargin(doubleScoreTGB,new Insets(0, 0, 0, 60));
        doubleScoreHB.getChildren().add(doubleScoreTGB);
        doubleScoreHB.setAlignment(Pos.CENTER);

        Label holdMonthLB = new Label("持有月数:");
        TextField holdMonthTF = new TextField ();
        holdMonthTF.setMaxWidth(80);
        HBox holdMonthHB = new HBox();
        holdMonthHB.getChildren().addAll(holdMonthLB, holdMonthTF);
        holdMonthHB.setSpacing(3);//
        holdMonthHB.setAlignment(Pos.CENTER);

        Label holdDayLB = new Label("持有天数:");
        TextField holdDayTF = new TextField ();
        holdDayTF.setMaxWidth(80);
        HBox holdDayHB = new HBox();
        holdDayHB.getChildren().addAll(holdDayLB, holdDayTF);
        holdDayHB.setSpacing(3);//
        holdDayHB.setAlignment(Pos.CENTER);

        HBox separatorHB = new HBox();
        Separator separator = new Separator();
        separator.setPrefWidth(240);
        separator.setOrientation(Orientation.HORIZONTAL);
        separatorHB.getChildren().add(separator);
        separatorHB.setAlignment(Pos.CENTER);

        pane.getChildren().add(investMoneyHB);
        pane.getChildren().add(monthHB);
        pane.getChildren().add(dayHB);
        pane.getChildren().add(vipHB);
        pane.getChildren().add(yearRateHB);
        pane.getChildren().add(doubleScoreHB);
        pane.getChildren().add(separatorHB);
        pane.getChildren().add(holdMonthHB);
        pane.getChildren().add(holdDayHB);
    }
}
