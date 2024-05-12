package model;

import java.util.ArrayList;
import java.util.Observable;

public class OPeratorModel  extends  Observable implements IOPeratorModel  {

    private  ArrayList<String> datas = new ArrayList<String>();

    public OPeratorModel(){
        datas.add("+");
        datas.add("-");
        datas.add("*");
        datas.add("/");
        datas.add("=");
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
