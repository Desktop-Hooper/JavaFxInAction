package sample.caculatorForXinrong.ui;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.Transition;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.caculatorForXinrong.domain.InvestingInput;
import sample.caculatorForXinrong.domain.InvestingResult;
import sample.caculatorForXinrong.domain.RechargeResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 47123 on 2016/7/26.
 */
public class ResultPane {

    private static ResultPane instance = null;

    private BorderPane pane;

    private FormulaPane formulaPane = null;

    private VBox childrenPaneOfResult;

    private HBox childrenPaneOfButton;

    private Label incomeLB;

    private Label serverLB;

    private Label actualIncomeLB;

    private Label scoreLB;

    private Label growScoreLB;

    private Label actualIncomeRateLB;

    private Label actualIncomeRateWithRewardLB;

    private Label rechargeServerLB;

    private Label scoreRefundLB;

    private Label rechargeActualIncomeLB;

    private Label actualIncomeRateWithRewardWithoutServerLB;

    private Button formulaButton;

    private ToggleButton animateButton;

    private ResultPane(){
        buildFace();
        registerAnimateEvent();
        registerFormulaEvent();
    }

    public static ResultPane getInstance(){
        if(instance == null){
            instance = new ResultPane();
            return  instance;
        }else{
            return  instance;
        }
    }

    private void buildFace(){
        childrenPaneOfResult = new VBox();
        childrenPaneOfResult.setId("result-value-pane");
        childrenPaneOfResult.getStyleClass().add("xinrong-background");
        childrenPaneOfResult.setAlignment(Pos.CENTER);
        childrenPaneOfResult.setSpacing(15);

        Label incomeLBPre = new Label("投资收益:");
        incomeLB = new Label("--");
        incomeLB.getStyleClass().add("result-value");
        HBox incomeHB = new HBox();
        incomeHB.getChildren().addAll(incomeLBPre, incomeLB);
        incomeHB.setSpacing(3);
        incomeHB.setAlignment(Pos.CENTER);

        Label serverLBPre = new Label("投资服务费:");
        serverLB = new Label("--");
        serverLB.getStyleClass().add("result-value");
        HBox serverHB = new HBox();
        serverHB.getChildren().addAll(serverLBPre, serverLB);
        serverHB.setSpacing(3);
        serverHB.setAlignment(Pos.CENTER);

        Label actualIncomeLBPre = new Label("投资实际收益:");
        actualIncomeLB = new Label("--");
        actualIncomeLB.getStyleClass().add("result-value");
        HBox actualIncomeHB = new HBox();
        actualIncomeHB.getChildren().addAll(actualIncomeLBPre, actualIncomeLB);
        actualIncomeHB.setSpacing(3);
        actualIncomeHB.setAlignment(Pos.CENTER);

        Label scoreLBPre = new Label("所获积分:");
        scoreLB = new Label("--");
        scoreLB.getStyleClass().add("result-value");
        HBox scoreHB = new HBox();
        scoreHB.getChildren().addAll(scoreLBPre, scoreLB);
        scoreHB.setSpacing(3);
        scoreHB.setAlignment(Pos.CENTER);

        Label growScoreLBPre = new Label("获得成长值:");
        growScoreLB = new Label("--");
        growScoreLB.getStyleClass().add("result-value");
        HBox growScoreHB = new HBox();
        growScoreHB.getChildren().addAll(growScoreLBPre, growScoreLB);
        growScoreHB.setSpacing(3);
        growScoreHB.setAlignment(Pos.CENTER);

        Label actualIncomeRateLBPre = new Label("实际年化收益:");
        actualIncomeRateLB = new Label("--");
        actualIncomeRateLB.getStyleClass().add("result-value");
        HBox actualIncomeRateHB = new HBox();
        actualIncomeRateHB.getChildren().addAll(actualIncomeRateLBPre, actualIncomeRateLB);
        actualIncomeRateHB.setSpacing(3);
        actualIncomeRateHB.setAlignment(Pos.CENTER);

        Label actualIncomeRateWithRewardLBPre = new Label("实际年化收益(含礼金):");
        actualIncomeRateWithRewardLB = new Label("--");
        actualIncomeRateWithRewardLB.getStyleClass().add("result-value");
        HBox actualIncomeRateWithRewardHB = new HBox();
        actualIncomeRateWithRewardHB.getChildren().addAll(actualIncomeRateWithRewardLBPre, actualIncomeRateWithRewardLB);
        actualIncomeRateWithRewardHB.setSpacing(3);
        actualIncomeRateWithRewardHB.setAlignment(Pos.CENTER);

        Label actualIncomeRateWithRewardWithoutServerLBPre = new Label("实际年化收益(含礼金不含服务费):");
        actualIncomeRateWithRewardWithoutServerLB = new Label("--");
        actualIncomeRateWithRewardWithoutServerLB.getStyleClass().add("result-value");
        HBox actualIncomeRateWithRewardWithoutServerHB = new HBox();
        actualIncomeRateWithRewardWithoutServerHB.getChildren().addAll(actualIncomeRateWithRewardWithoutServerLBPre, actualIncomeRateWithRewardWithoutServerLB);
        actualIncomeRateWithRewardWithoutServerHB.setSpacing(3);
        actualIncomeRateWithRewardWithoutServerHB.setAlignment(Pos.CENTER);

        Label rechargeServerLBPre = new Label("转让服务费:");
        rechargeServerLB = new Label("--");
        rechargeServerLB.getStyleClass().add("result-value");
        HBox rechargeServerHB = new HBox();
        rechargeServerHB.getChildren().addAll(rechargeServerLBPre, rechargeServerLB);
        rechargeServerHB.setSpacing(3);
        rechargeServerHB.setAlignment(Pos.CENTER);

        Label scoreRefundLBPre = new Label("积分补偿金:");
        scoreRefundLB = new Label("--");
        scoreRefundLB.getStyleClass().add("result-value");
        HBox scoreRefundHB = new HBox();
        scoreRefundHB.getChildren().addAll(scoreRefundLBPre, scoreRefundLB);
        scoreRefundHB.setSpacing(3);
        scoreRefundHB.setAlignment(Pos.CENTER);

        Label rechargeActualIncomeLBPre = new Label("转让后最终收益:");
        rechargeActualIncomeLB = new Label("--");
        rechargeActualIncomeLB.getStyleClass().add("result-value");
        HBox rechargeActualIncomeHB = new HBox();
        rechargeActualIncomeHB.getChildren().addAll(rechargeActualIncomeLBPre, rechargeActualIncomeLB);
        rechargeActualIncomeHB.setSpacing(3);
        rechargeActualIncomeHB.setAlignment(Pos.CENTER);

        childrenPaneOfResult.getChildren().addAll(incomeHB,serverHB,actualIncomeHB,scoreHB,growScoreHB,actualIncomeRateHB,actualIncomeRateWithRewardHB,actualIncomeRateWithRewardWithoutServerHB
            ,rechargeServerHB,scoreRefundHB,rechargeActualIncomeHB);

        HBox childrenPaneOfButton = new HBox();
        animateButton = new ToggleButton("呼吸效果");
        animateButton.getStyleClass().add("result-button");
        animateButton.setSelected(true);
        formulaButton = new Button("公式查询");
        formulaButton.getStyleClass().add("result-button");
        childrenPaneOfButton.setPadding(new Insets(20,10,20,10));
        childrenPaneOfButton.setAlignment(Pos.BASELINE_RIGHT);
        childrenPaneOfButton.setSpacing(6);
        childrenPaneOfButton.getChildren().addAll(formulaButton,animateButton);

        pane = new BorderPane();
        pane.setId("result-pane");
        pane.setCenter(childrenPaneOfResult);
        pane.setBottom(childrenPaneOfButton);
    }

    public Pane getPane() {
        return pane;
    }
    
    public void insertInvestResult(InvestingResult investingResult){
        initFormulaPane();
         incomeLB.setText(investingResult.getIncome());
         serverLB.setText(investingResult.getServerCharger());
         actualIncomeLB.setText(investingResult.getActualIncome());
         scoreLB.setText(investingResult.getScore());
         growScoreLB.setText(investingResult.getGrowScore());
         actualIncomeRateLB.setText(investingResult.getActualYearIncome());
         actualIncomeRateWithRewardLB.setText(investingResult.getActualYearIncomeWithReward());
        rechargeServerLB.setText("--");
        scoreRefundLB.setText("--");
        rechargeActualIncomeLB.setText("--");
        actualIncomeRateWithRewardWithoutServerLB.setText("--");
        formulaPane.insertInvestResult(investingResult);
    }
    
    public void insertRechargeResult(RechargeResult rechargeResult){
        initFormulaPane();
        insertInvestResult(rechargeResult);
        rechargeServerLB.setText(rechargeResult.getRechargeServerCharge());
        scoreRefundLB.setText(rechargeResult.getScoreRefund());
        rechargeActualIncomeLB.setText(rechargeResult.getIncomeAfterRecharge());
        actualIncomeRateWithRewardWithoutServerLB.setText(rechargeResult.getActualYearIncomeWithoutServer());
        formulaPane.insertRechargeResult(rechargeResult);
    }

    private void registerAnimateEvent(){
        final List<ScaleTransition> scaleTransitions = addScaleAnimation(childrenPaneOfResult);
        final Transition fade = addFadeAnimation(childrenPaneOfResult);
        animateButton.selectedProperty().addListener((new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1){
                    for(ScaleTransition scaleTransition:scaleTransitions){
                        scaleTransition.play();
                    }
                    fade.play();
                }else{
                    for(ScaleTransition scaleTransition:scaleTransitions){
                        scaleTransition.stop();
                    }
                    fade.stop();
                }
            }
        }));
    }

    private void registerFormulaEvent(){
        formulaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                initFormulaPane();
                childrenPaneOfResult.setVisible(false);
                childrenPaneOfButton = (HBox) pane.getBottom();
                childrenPaneOfButton.setVisible(false);
                formulaPane.formulaButtonPane.setVisible(true);
                formulaPane.formulaPane.setVisible(true);
                pane.setCenter(formulaPane.getPane().getCenter());
                pane.setBottom(formulaPane.getPane().getBottom());
            }
        });
    }

    private FadeTransition addFadeAnimation(Node node){
        FadeTransition fadeTransition =
                new FadeTransition(Duration.millis(4000), node);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.75f);
        fadeTransition.setCycleCount(-1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        return fadeTransition;
    }

    private List<ScaleTransition> addScaleAnimation(Pane pane){
        List<ScaleTransition> animations = new ArrayList<>();
       for(Node node : pane.getChildren()) {
           ScaleTransition scaleTransition =
                   new ScaleTransition(Duration.millis(6000), node);
           scaleTransition.setFromX(1);
           scaleTransition.setFromY(1);
           scaleTransition.setToX(1.1);
           scaleTransition.setToY(1.2);
           scaleTransition.setCycleCount(-1);
           scaleTransition.setAutoReverse(true);
           scaleTransition.play();
           animations.add(scaleTransition);
       }
        return animations;
    }

    private void initFormulaPane(){
        if(formulaPane == null){
            formulaPane = new ResultPane.FormulaPane();
        }
    }

    private class FormulaPane{

        private BorderPane formulaRootPane;

        private GridPane formulaPane;

        private HBox formulaButtonPane;

        private Label incomeLB;

        private Label serverLB;

        private Label actualIncomeLB;

        private Label scoreLB;

        private Label growScoreLB;

        private Label actualIncomeRateLB;

        private Label actualIncomeRateWithRewardLB;

        private Label rechargeServerLB;

        private Label scoreRefundLB;

        private Label rechargeActualIncomeLB;

        private Label actualIncomeRateWithRewardWithoutServerLB;

        private Button formulaExitButton;

        public FormulaPane(){
            buildFace();
            registerFormulaExitEvent();
        }

        private void buildFace(){
            formulaPane = new GridPane();
            formulaPane.setId("formula-pane");
            formulaPane.getStyleClass().add("xinrong-background");
            formulaPane.setHgap(15);
            formulaPane.setVgap(15);
            formulaPane.setAlignment(Pos.CENTER);
            formulaPane.setPadding(new Insets(0,0,0,40));

            Label incomeLBPre = new Label("投资收益:");
            incomeLBPre.setMinWidth(80);
            incomeLB = new Label("--");
            incomeLB.getStyleClass().add("result-value");
            incomeLB.setWrapText(true);
            incomeLB.setMaxWidth(500);
            formulaPane.addRow(0,incomeLBPre,incomeLB);


            Label serverLBPre = new Label("投资服务费:");
            serverLB = new Label("--");
            serverLB.getStyleClass().add("result-value");
            serverLB.setWrapText(true);
            formulaPane.addRow(1,serverLBPre,serverLB);


            Label actualIncomeLBPre = new Label("投资实际收益:");
            actualIncomeLB = new Label("--");
            actualIncomeLB.getStyleClass().add("result-value");
            actualIncomeLB.setWrapText(true);
            formulaPane.addRow(2,actualIncomeLBPre,actualIncomeLB);


            Label scoreLBPre = new Label("所获积分:");
            scoreLB = new Label("--");
            scoreLB.getStyleClass().add("result-value");
            scoreLB.setWrapText(true);
            formulaPane.addRow(3,scoreLBPre,scoreLB);


            Label growScoreLBPre = new Label("获得成长值:");
            growScoreLB = new Label("--");
            growScoreLB.getStyleClass().add("result-value");
            growScoreLB.setWrapText(true);
            formulaPane.addRow(4,growScoreLBPre,growScoreLB);


            Label actualIncomeRateLBPre = new Label("实际年化收益:");
            actualIncomeRateLB = new Label("--");
            actualIncomeRateLB.getStyleClass().add("result-value");
            actualIncomeRateLB.setWrapText(true);
            formulaPane.addRow(5,actualIncomeRateLBPre,actualIncomeRateLB);


            Label actualIncomeRateWithRewardLBPre = new Label("实际年化收益(含礼金):");
            actualIncomeRateWithRewardLB = new Label("--");
            actualIncomeRateWithRewardLB.getStyleClass().add("result-value");
            actualIncomeRateWithRewardLB.setWrapText(true);
            actualIncomeRateWithRewardLB.setMaxWidth(500);
            formulaPane.addRow(6,actualIncomeRateWithRewardLBPre,actualIncomeRateWithRewardLB);


            Label actualIncomeRateWithRewardWithoutServerLBPre = new Label("实际年化收益(含礼金不含服务费):");
            actualIncomeRateWithRewardWithoutServerLB = new Label("--");
            actualIncomeRateWithRewardWithoutServerLB.getStyleClass().add("result-value");
            actualIncomeRateWithRewardWithoutServerLB.setWrapText(true);
            actualIncomeRateWithRewardWithoutServerLB.setMaxWidth(500);
            formulaPane.addRow(7,actualIncomeRateWithRewardWithoutServerLBPre,actualIncomeRateWithRewardWithoutServerLB);


            Label rechargeServerLBPre = new Label("转让服务费:");
            rechargeServerLB = new Label("--");
            rechargeServerLB.getStyleClass().add("result-value");
            rechargeServerLB.setWrapText(true);
            formulaPane.addRow(8,rechargeServerLBPre,rechargeServerLB);


            Label scoreRefundLBPre = new Label("积分补偿金:");
            scoreRefundLB = new Label("--");
            scoreRefundLB.getStyleClass().add("result-value");
            scoreRefundLB.setWrapText(true);
            scoreRefundLB.setMaxWidth(500);
            formulaPane.addRow(9,scoreRefundLBPre,scoreRefundLB);


            Label rechargeActualIncomeLBPre = new Label("转让后最终收益:");
            rechargeActualIncomeLB = new Label("--");
            rechargeActualIncomeLB.getStyleClass().add("result-value");
            rechargeActualIncomeLB.setWrapText(true);
            formulaPane.addRow(10,rechargeActualIncomeLBPre,rechargeActualIncomeLB);

            formulaButtonPane = new HBox();
            formulaButtonPane.setAlignment(Pos.BASELINE_RIGHT);
            formulaButtonPane.setPadding(new Insets(20,10,20,10));

            formulaExitButton = new Button("返回");
            formulaButtonPane.getStyleClass().add("result-button");
            formulaButtonPane.getChildren().add(formulaExitButton);


            formulaRootPane = new BorderPane();
            formulaRootPane.setPrefWidth(1920);
            formulaRootPane.setCenter(formulaPane);
            formulaRootPane.setBottom(formulaButtonPane);
        }

        private void registerFormulaExitEvent(){
            formulaExitButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    formulaButtonPane.setVisible(false);
                    formulaPane.setVisible(false);
                    ResultPane.this.childrenPaneOfResult.setVisible(true);
                    ResultPane.this.childrenPaneOfButton.setVisible(true);
                    pane.setCenter(ResultPane.this.childrenPaneOfResult);
                    pane.setBottom(ResultPane.this.childrenPaneOfButton);
                }
            });
        }

        public void insertInvestResult(InvestingResult investingResult){
            incomeLB.setText(investingResult.getIncomeInfo());
            serverLB.setText(investingResult.getServerChargerInfo());
            actualIncomeLB.setText(investingResult.getActualIncomeInfo());
            scoreLB.setText(investingResult.getScoreInfo());
            growScoreLB.setText(investingResult.getGrowScoreInfo());
            actualIncomeRateLB.setText(investingResult.getActualYearIncomeInfo());
            actualIncomeRateWithRewardLB.setText(investingResult.getActualYearIncomeWithRewardInfo());
            rechargeServerLB.setText("--");
            scoreRefundLB.setText("--");
            rechargeActualIncomeLB.setText("--");
            actualIncomeRateWithRewardWithoutServerLB.setText("--");

        }

        public void insertRechargeResult(RechargeResult rechargeResult){
            insertInvestResult(rechargeResult);
            rechargeServerLB.setText(rechargeResult.getRechargeServerChargeInfo());
            scoreRefundLB.setText(rechargeResult.getScoreRefundInfo());
            rechargeActualIncomeLB.setText(rechargeResult.getIncomeAfterRechargeInfo());
            actualIncomeRateWithRewardWithoutServerLB.setText(rechargeResult.getActualYearIncomeWithoutServerInfo());
        }

        public BorderPane getPane() {
            return formulaRootPane;
        }
    }
}
