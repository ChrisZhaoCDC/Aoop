package model;

import java.util.ArrayList;
import java.util.Observable;

public class SpecialModel  extends Observable implements  ISpecial{

    public   final  static String backSpace="← BackSpace";
    public  final  static String enter="Enter";

    private  ArrayList<String> datas = new ArrayList<String>();
    public SpecialModel(){
        datas.add(backSpace);
        datas.add(enter);
    }
    @Override
    public ArrayList<String> getDatas() {
        return datas;
    }

    @Override
    public void notifyStart() {
        setChanged();
        notifyObservers();
    }
}
