package model;// NumberleModel.java
import java.util.ArrayList;
import java.util.Random;
import java.util.Observable;

public class NumberleModel extends Observable implements INumberleModel {

    private ArrayList<String> datas;


    //Add all data, as it is a numeric keypad, there is a numeric input of 0-9
    public NumberleModel(){
        datas=new ArrayList<>();
       int i;
       for (i=0;i<10;i++)
           datas.add(String.valueOf(i));
    }


    //Get all the data read
    public ArrayList<String> getDatas(){
        return datas;
    }



    @Override
    public void notifyStart() {
        setChanged();
        notifyObservers();
    }
}
