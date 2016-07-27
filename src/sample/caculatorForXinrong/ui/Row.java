package sample.caculatorForXinrong.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Created by 47123 on 2016/7/27.
 */
public class Row {

    private Label label;

    private TextField textField;

    private HBox hBox;

    public Row(String name,String value){
        label = new Label(name);
        textField = new TextField(value);
        hBox = new HBox();
        hBox.getChildren().addAll(label,textField);
    }

    public Row(String name,String value,int hgap){
        label = new Label(name);
        textField = new TextField(value);
        hBox = new HBox();
        hBox.setSpacing(hgap);
        hBox.getChildren().addAll(label,textField);
    }

    public Row(String name, String value, int hgap, Pos alignment){
        label = new Label(name);
        textField = new TextField(value);
        hBox = new HBox();
        hBox.setSpacing(hgap);
        hBox.setAlignment(alignment);
        hBox.getChildren().addAll(label,textField);
    }

    public HBox getRow() {
        return hBox;
    }

}
