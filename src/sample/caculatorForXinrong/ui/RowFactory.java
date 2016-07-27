package sample.caculatorForXinrong.ui;

import javafx.geometry.Pos;


/**
 * Created by 47123 on 2016/7/27.
 */
public class RowFactory {

    public static Row createRow(String name,String value){
        return new Row(name,value);
    }

    public static Row createRow(String name,String value,int hGap){
        return new Row(name,value,hGap);
    }

    public static Row createRow(String name,String value,int hGap,Pos alignment){
        return new Row(name,value,hGap,alignment);
    }
}
