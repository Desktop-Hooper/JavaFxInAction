package sample.caculatorForXinrong.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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

    private YearRatePane(){
        loadProperties();

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
        for(String val:yearRates){

        }
    }

    public void loadProperties() {
        List<String> ratios = PropertiesUtil.getPropertiesKeyToList(FilePathConfig.getYearRatio());
        yearRates = ratios;
    }

    public static void main(String[] args){
        YearRatePane yearRatePane = YearRatePane.getInstance();
        yearRatePane.loadProperties();
    }

    private void rigsterRowDelEvent(Button btn,Row row){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }
}
