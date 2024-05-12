package model;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Random;
import java.util.ArrayList;



public class ExpressionModel  extends Observable implements IExpressionModel {



    private static String filePath = "equations.txt"; //Replace with your text file path
    private ArrayList<String > datas;
    private String currentData;
    public  String getCurrentData(){
        return  this.currentData;
    }

    public  ArrayList<String> getDatas(){
        return  this.datas;
     }




    public ExpressionModel(){

    }
    public void read(){
        datas=new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                datas.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  ArrayList<CHAR_STATUS> getStates(String message){
        ArrayList<CHAR_STATUS> states=new ArrayList<>();
        for(int i=0;i<message.length();i++){
            if( message.charAt(i)==currentData.charAt(i)){
                states.add(CHAR_STATUS.CORRECT);
                continue;
            }
            boolean ifFind=false;
            for(int j=0;j<currentData.length();j++){
                if( message.charAt(i)==currentData.charAt(j)){
                    states.add(CHAR_STATUS.EXIST); //Existence but position mismatch
                    ifFind=true;
                    break;
                }
            }
            //
            if(!ifFind){
                states.add(CHAR_STATUS.NORMAL);//The location does not exist
            }
        }
        return states;

    }


   public void randomExtraction(){
       //Create a Random object
        Random random = new Random();
       //Generate random numbers and print them
        int randomNumber = random.nextInt(datas.size()); //Generate random numbers between 0 and 10
        this.currentData=datas.get(randomNumber);
        System.out.println(this.currentData);
    }


    @Override
    public void notifyStart() {
        setChanged();
        notifyObservers();
    }
}
