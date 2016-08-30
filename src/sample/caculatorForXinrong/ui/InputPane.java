package sample.caculatorForXinrong.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.caculatorForXinrong.core.Calculator;
import sample.caculatorForXinrong.domain.*;

import java.math.BigDecimal;

/**
 * Created by 47123 on 2016/7/25.
 */
public class InputPane{

    private static InputPane instance = null;

    private BorderPane pane;

    private TextField investMoneyTF;

    private TextField monthTF;

    private TextField dayTF;

    private TextField vipTF;

    private ChoiceBox yearRateCB;

    private ToggleButton doubleScoreTGB;

    private TextField holdMonthTF;

    private TextField holdDayTF;

    private Label errorLB;

    private Button submitBtn = new Button("投资查询");
    private Button rechargeBtn = new Button("转让查询");
    private Button resetBtn = new Button("清空输入");

    private InvestingInput investingInput;
    private RechargeInput rechargeInput;

    private InputPane(){
         buildFace();
         registerEvent();
    }

    public static InputPane getInstance(){
        if(instance == null){
            instance = new InputPane();
             return  instance;
        }else{
            return  instance;
        }
    }

    private void buildFace(){
        VBox textFieldPane = new VBox();
        textFieldPane.setId("input-vbox");
        textFieldPane.setAlignment(Pos.CENTER);
        textFieldPane.setSpacing(20);

        Label investMoneyLB = new Label("投资金额:");
        investMoneyTF = new TextField ();
        investMoneyTF.setMaxWidth(80);
        HBox investMoneyHB = new HBox();
        investMoneyHB.getChildren().addAll(investMoneyLB, investMoneyTF);
        investMoneyHB.setSpacing(3);
        investMoneyHB.setAlignment(Pos.CENTER);

        Label monthLB = new Label("项目月数:");
        monthTF = new TextField ();
        monthTF.setMaxWidth(80);
        HBox monthHB = new HBox();
        monthHB.getChildren().addAll(monthLB, monthTF);
        monthHB.setSpacing(3);
        monthHB.setAlignment(Pos.CENTER);

        Label dayLB = new Label("项目天数:");
        dayTF = new TextField ();
        dayTF.setMaxWidth(80);
        dayTF.setText("0");
        HBox dayHB = new HBox();
        dayHB.getChildren().addAll(dayLB, dayTF);
        dayHB.setSpacing(3);
        dayHB.setAlignment(Pos.CENTER);

        Label vipLB = new Label("VIP等级:");
        vipTF = new TextField ();
        vipTF.setMaxWidth(80);
        vipTF.setText("1");
        HBox vipHB = new HBox();
        vipHB.getChildren().addAll(vipLB, vipTF);
        vipHB.setSpacing(8);//
        vipHB.setAlignment(Pos.CENTER);

        Label yearRateLB = new Label("年化率:");
        yearRateCB = new ChoiceBox();
        yearRateCB.setTooltip(new Tooltip("选择年化收益率"));
        yearRateCB.setScaleX(1.1);
        yearRateCB.setItems(FXCollections.observableArrayList(0.132,0.25));
        yearRateCB.setMaxWidth(90);
        yearRateCB.getSelectionModel().select(0.25);//默认第一个
        HBox yearRateHB = new HBox();
        yearRateHB.getChildren().addAll(yearRateLB, yearRateCB);
        yearRateHB.setSpacing(20);//
        yearRateHB.setAlignment(Pos.CENTER);

        HBox doubleScoreHB = new HBox();
        doubleScoreTGB = new ToggleButton("双倍积分");
        doubleScoreTGB.setUserData(Color.LIGHTGREEN);
        doubleScoreTGB.setSelected(false);
        doubleScoreTGB.setId("double-toggle");
        doubleScoreTGB.setTooltip(new Tooltip("投资时是否为双倍赠送积分"));
        doubleScoreHB.setMargin(doubleScoreTGB,new Insets(0, 0, 0, 60));
        doubleScoreHB.getChildren().add(doubleScoreTGB);
        doubleScoreHB.setAlignment(Pos.CENTER);

        Label holdMonthLB = new Label("持有月数:");
        holdMonthTF = new TextField ("0");
        holdMonthTF.setMaxWidth(80);
        HBox holdMonthHB = new HBox();
        holdMonthHB.getChildren().addAll(holdMonthLB, holdMonthTF);
        holdMonthHB.setSpacing(3);//
        holdMonthHB.setAlignment(Pos.CENTER);

        Label holdDayLB = new Label("持有天数:");
        holdDayTF = new TextField ("0");
        holdDayTF.setMaxWidth(80);
        HBox holdDayHB = new HBox();
        holdDayHB.getChildren().addAll(holdDayLB, holdDayTF);
        holdDayHB.setSpacing(3);//
        holdDayHB.setAlignment(Pos.CENTER);

        errorLB = new Label();
        errorLB.getStyleClass().add("error-msg");

        HBox separatorHB = new HBox();
        Separator separator = new Separator();
        separator.setPrefWidth(240);
        separator.setOrientation(Orientation.HORIZONTAL);
        separatorHB.getChildren().add(separator);
        separatorHB.setAlignment(Pos.CENTER);

        textFieldPane.getChildren().add(investMoneyHB);
        textFieldPane.getChildren().add(monthHB);
        textFieldPane.getChildren().add(dayHB);
        textFieldPane.getChildren().add(vipHB);
        textFieldPane.getChildren().add(yearRateHB);
        textFieldPane.getChildren().add(doubleScoreHB);
        textFieldPane.getChildren().add(separatorHB);
        textFieldPane.getChildren().add(holdMonthHB);
        textFieldPane.getChildren().add(holdDayHB);
        textFieldPane.getChildren().add(errorLB);

        FlowPane buttonPane = new FlowPane();
        buttonPane.setAlignment(Pos.BASELINE_CENTER);
        buttonPane.setPadding(new Insets(10,10,20,10));
        buttonPane.setHgap(10);
        buttonPane.setVgap(20);
        buttonPane.setPrefWrapLength(70);
        buttonPane.setPrefWidth(70);
        buttonPane.setMaxWidth(300);
        buttonPane.getChildren().addAll(submitBtn,rechargeBtn,resetBtn);
        
        pane = new BorderPane();
        pane.setId("input-pane");
        pane.setMaxWidth(260);
        pane.setCenter(textFieldPane);
        pane.setBottom(buttonPane);
    }

    public Pane getPane() {
        return pane;
    }

    public RechargeInput getRechargeInput() { return rechargeInput; }

    public InvestingInput getInvestingInput() { return investingInput; }

    public void visible(boolean onOff){
        instance.getPane().setVisible(onOff);
    }

    private void registerEvent(){
        registerInvestSubmitEvent();
        registerRechargeSubmitEvent();
        registerClearSubmitEvent();
        NumericTextField();
    }

    private void registerInvestSubmitEvent(){
        submitBtn.setOnAction(new EventHandler(){
            public void handle(Event e){
                ValidMessage valid = validInvestInput();
                if(!valid.getStatus()){
                    errorLB.setText(valid.getMsg());
                    return;
                }else{
                    errorLB.setText("");
                    investingInput = transFormToInvestingInput();
                    InvestingResult investingResult = Calculator.caculateInvest(investingInput.getMoney(),investingInput.getMonth(),
                            investingInput.getDay(),investingInput.getVip(),investingInput.getYearRate(),investingInput.isDoubleScore());
                    ResultPane.getInstance().insertInvestResult(investingResult);
                }
            }
        });
    }

    private void registerRechargeSubmitEvent(){
        rechargeBtn.setOnAction(new EventHandler(){
            public void handle(Event e){
                ValidMessage valid = validRechargeInput();
                if(!valid.getStatus()){
                    errorLB.setText(valid.getMsg());
                    return;
                }else{
                    errorLB.setText("");
                    rechargeInput = transFormToRechargeInput();
                    RechargeResult rechargeResult = Calculator.caculateRecharge(rechargeInput.getMoney(),rechargeInput.getMonth(),rechargeInput.getDay(),rechargeInput.getHandingMonth()
                    ,rechargeInput.getHandingDay(),rechargeInput.getVip(),rechargeInput.getYearRate(),rechargeInput.isDoubleScore());
                    ResultPane.getInstance().insertRechargeResult(rechargeResult);
                }
            }
        });
    }

    private void registerClearSubmitEvent(){
        resetBtn.setOnAction(new EventHandler(){
            public void handle(Event e){
                investMoneyTF.clear();
                monthTF.clear();
                dayTF.setText("0");
                vipTF.setText("1");
                holdMonthTF.setText("0");
                holdDayTF.setText("0");
                doubleScoreTGB.setSelected(false);
                errorLB.setText("");
            }
        });
    }

    private ValidMessage validInvestInput(){
        ValidMessage validMessage = new ValidMessage();
        validMessage.setStatus(true);
        if( "".equals(investMoneyTF.getText())){
            validMessage.setStatus(false);
            validMessage.setMsg("(*@ο@*)没输入投资金额，科科");
            return  validMessage;
        }
        if(!dayTF.getText().matches("^(0?[0-9]|1[0-9]|2[0-9]|3[0-1])$")){
            validMessage.setStatus(false);
            validMessage.setMsg("(*@ο@*)项目天数不对奥，只能是0-31，科科");
            return  validMessage;
        }
        if(!vipTF.getText().matches("^[1-9]$")){
            validMessage.setStatus(false);
            validMessage.setMsg("凸(艹皿艹 )VIP等级只有1-9，这你都不知道");
            return  validMessage;
        }
        if(yearRateCB.getValue() == null){
            validMessage.setStatus(false);
            validMessage.setMsg("Excuse me??年化率还没选呀");
            return  validMessage;
        }
        return  validMessage;
    }

    private ValidMessage validRechargeInput(){
        ValidMessage validMessage = validInvestInput();
        if (!validMessage.getStatus()){
            return validMessage;
        }else{
            if(!holdDayTF.getText().matches("^(0?[0-9]|1[0-9]|2[0-9]|3[0-1])$")){
                validMessage.setStatus(false);
                validMessage.setMsg("(#‵′)持有天数不对奥，只能是0-31，科科");
                return  validMessage;
            }
            if(holdDayTF.getText().matches("^[0]$") && holdMonthTF.getText().matches("^[0]$")){
                validMessage.setStatus(false);
                validMessage.setMsg("(#‵′)持有月数,天数都为0，那我就不算了");
                return  validMessage;
            }
            if((Integer.valueOf(monthTF.getText()) * 30 + Integer.valueOf(dayTF.getText())) < (Integer.valueOf(holdMonthTF.getText()) * 30 + Integer.valueOf(holdDayTF.getText())) ){
                validMessage.setStatus(false);
                validMessage.setMsg("(#‵′)持有时间大于项目时间????");
                return  validMessage;
            }
            return validMessage;
        }
    }

    private InvestingInput transFormToInvestingInput(){
        InvestingInput investingInput = new InvestingInput();
        investingInput.setMoney(new BigDecimal(investMoneyTF.getText()));
        investingInput.setMonth(Integer.valueOf(monthTF.getText()));
        investingInput.setDay(Integer.valueOf(dayTF.getText()));
        investingInput.setVip(Integer.valueOf(vipTF.getText()));
        investingInput.setYearRate((double)yearRateCB.getValue());
        investingInput.setDoubleScore(doubleScoreTGB.isSelected());
        return investingInput;
    }

    private RechargeInput transFormToRechargeInput(){
        RechargeInput rechargeInput = new RechargeInput();
        rechargeInput.setMoney(new BigDecimal(investMoneyTF.getText()));
        rechargeInput.setMonth(Integer.valueOf(monthTF.getText()));
        rechargeInput.setDay(Integer.valueOf(dayTF.getText()));
        rechargeInput.setVip(Integer.valueOf(vipTF.getText()));
        rechargeInput.setYearRate((double)yearRateCB.getValue());
        rechargeInput.setDoubleScore(false);
        rechargeInput.setHandingMonth(Integer.valueOf(holdMonthTF.getText()));
        rechargeInput.setHandingDay(Integer.valueOf(holdDayTF.getText()));
        return rechargeInput;
    }

    private void NumericTextField(){
        investMoneyTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    investMoneyTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        monthTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    monthTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        dayTF .textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    dayTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        vipTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    vipTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        holdDayTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    holdDayTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        holdMonthTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    holdMonthTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
