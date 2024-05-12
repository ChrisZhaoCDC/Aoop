package model;

import java.awt.*;
import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.Observable;

public class GuessModel extends Observable implements IGuessModel {
    public static enum Model {
        INPUT,
        BACKSPACE,
        RESTART,

        CLICK,//You can click the restart button now

        ENTER, //Enter confirmation
        NONOE  //Empty without making judgments
    }

    public int i;
    public ArrayList<CHAR_STATUS> states;
    //Current calculation expression
   String currentExpression="";
   //Current number of guesses
   Integer time=1;
   public  void clear(){
       ifCorrect=false;
       ifOver=false;
       time=1;
       currentExpression="";
   }
   //Is it possible to restart


   //if correct
   public  boolean ifCorrect=false;

   //if over
   public  boolean ifOver=false;


    private  Model model;
    //Set whether the current mode is input mode or other similar modes
    public  void setModel(Model model){
        this.model=model;
    }

    public Model getModel(){
        return  model;
    }

   @Override
   public  void notifyStart()
   {
       setChanged();
       notifyObservers();
   }


   //Execute the next judgment
   public  boolean increaseTime(){
       if(time+1>6)
       {
           //Mark End
           ifOver=true;
           return  false;
       }
       time++;
       //Perform the next guessing reset
       currentExpression="";
       return true;
   }

   public  boolean ifEqual(){
       char[] charArray = currentExpression.toCharArray();
       for(char c : charArray){
          if(c=='=')
              return true;
       }
       return  false;
   }

   //Is there an arithmetic symbol present
   public  boolean ifOperator(){
       char[] charArray = currentExpression.toCharArray();
       char name[]={'+','-','*','/'};
       for(char c : charArray){
           for (int i=0;i<name.length;i++){
               if(c==name[i])
                   return  true;
           }
       }
       return  false;
   }


    public  boolean ifOperator(String currentExpression){
        char[] charArray = currentExpression.toCharArray();
        char name[]={'+','-','*','/'};

        for(char c : charArray){
            for (int i=0;i<name.length;i++){
                if(c==name[i])
                    return  true;
            }
        }
        return  false;
    }




    public   Integer getTime(){
        return  this.time;
    }

   public  GuessModel(){

   }



   public  boolean ifEmpty(){
       if(currentExpression.length()==0)
           return  true;
       return false;
   }

   public  boolean ifFull(){
       if(currentExpression.length()==7)
           return  true;
       return false;
   }


   public void push(String str){
       currentExpression+=str;
   }



   public  String getCurrentExpression(){
       return  this.currentExpression;
   }


  public void pop(){
       if(currentExpression.length()>0)
           currentExpression=currentExpression.substring(0, currentExpression.length() - 1);
   }
}
