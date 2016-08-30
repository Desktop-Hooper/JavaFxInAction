package sample.caculatorForXinrong.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.caculatorForXinrong.domain.FilePathConfig;
import sample.caculatorForXinrong.util.PropertiesUtil;


import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by 47123 on 2016/7/27.
 */
public class YearRatePane{

    private static YearRatePane instance = null;

    private VBox resultPane;

    private VBox rightPart;

    private List<String> yearRates;

    private List<String> toDelRate;

    private Label  errorMsg = new Label();

    private Button newBtn = new Button("添加");

    private Button saveBtn = new Button("保存");

    private Button backBtn = new Button("返回");

    private final int rowSpacing = 30;

    private final String rowName = "年华收益率";

    private YearRatePane(){
        loadProperties();
        buildFace();
        registerNewRowEvent(newBtn);
        registerSaveEvent(saveBtn);
    }

    public static YearRatePane getInstance(){
        if(instance == null){
            instance = new YearRatePane();
            return  instance;
        }else{
            return  instance;
        }
    }

    public void buildFace(){
        resultPane = new VBox();
        resultPane.getStyleClass().add("xinrong-background");
        resultPane.setAlignment(Pos.CENTER);
        resultPane.setSpacing(15);

        int i = 1;
        for(String val:yearRates){
            Label label = new Label();
            label.getStyleClass().add("year-rate-del");
            Row row = RowFactory.createRow(rowName,val,rowSpacing);
            row.getRow().getChildren().add(label);
            registerRowDelEvent(label,row);
            resultPane.getChildren().add(row.getRow());
            i++;
        }

        rightPart = new VBox();
        newBtn.setPrefWidth(80);
        saveBtn.setPrefWidth(80);
        backBtn.setPrefWidth(80);
        errorMsg.getStyleClass().add("error-msg");
        rightPart.setPrefWidth(200);
        rightPart.getStyleClass().add("right-pane");
        rightPart.getChildren().addAll(errorMsg,newBtn,saveBtn,backBtn);
    }

    public void loadProperties() {
        List<String> ratios = PropertiesUtil.getPropertiesKeyToList(FilePathConfig.getYearRatio());
        yearRates = ratios;
    }

    public static void main(String[] args){
        YearRatePane yearRatePane = YearRatePane.getInstance();
        yearRatePane.loadProperties();
    }

    DropShadow shadow = new DropShadow();

    private void registerRowDelEvent(final Label label, final Row row){
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resultPane.getChildren().remove(row.getRow());
            }
        });

        label.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                label.setEffect(shadow);
            }
        });

        label.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
              label.setEffect(null);
            }
        });
    }

    private void registerNewRowEvent(final Button newBtn){
        newBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(resultPane.getChildren().size() < 9){
                    Row row = RowFactory.createRow(rowName,"0.00",rowSpacing);

                    Label label = new Label();
                    label.getStyleClass().add("year-rate-del");
                    row.getRow().getChildren().add(label);

                    registerRowDelEvent(label,row);

                    resultPane.getChildren().add(row.getRow());
                }else{
                    errorMsg.setText("弄这么多干嘛");
                }
            }
        });
    }

    private void registerExitEvent(){

    }

    private void registerSaveEvent(final Button saveBtn){
        saveBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("保存成功");
                alert.setHeaderText("Information Alert");
                String s ="This is an example of JavaFX 8 Dialogs... ";
                alert.setContentText(s);
                alert.show();

            }
        });
    }

    public VBox getResultPane() {
        return resultPane;
    }

    public VBox getRightPart() {
        return rightPart;
    }
}
