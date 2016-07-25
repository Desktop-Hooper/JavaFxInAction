package sample.caculatorForXinrong.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * Created by 47123 on 2016/7/25.
 */
public class InputPane {

    VBox vBox = new VBox();

    private InputPane instance = null;

    private InputPane(){};

    public InputPane getInstance(){
        if(instance == null){
            return  new InputPane();
        }else{
            return  instance;
        }
    }
}
