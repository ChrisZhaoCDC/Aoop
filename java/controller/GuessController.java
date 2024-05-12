package controller;

import model.*;
import util.GbuttonUtil;
import util.InfixExpressionEvaluator;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuessController {
    private PromptModel promptModel=new PromptModel();
    private  PromptView promptView=new PromptView();
    private NumberleView numberleView;
    private  WindowView windowView;
    private GuessModel guessModel;

    private GuessButtonGroupView guessButtonGroupView;

    private  ExpressionModel expressionModel;
    private OperatorView operatorView;
    private  SpecialView specialView;


    
    public  void setSpecialView(SpecialView specialView)
    {
        this.specialView=specialView;
    }
    public  void setOperatorView(OperatorView operatorView){
        this.operatorView=operatorView;
    }
    public  GuessController(GuessModel guessModel,GuessButtonGroupView guessButtonGroupView){
        this.guessModel=guessModel;
        this.guessButtonGroupView=guessButtonGroupView;
        //重启按钮
    }




    public  void setNumberleView(NumberleView numberleView){
        this.numberleView=numberleView;
    }
    public  void setView(){
        this.guessButtonGroupView.setGuessController(this);
        this.guessButtonGroupView.setGuessModel(guessModel);


    }
    public  void setWindowView(WindowView windowView){
        this.windowView = windowView;
    }
    public  void showModel(){
        this.guessButtonGroupView.showData();
        this.guessButtonGroupView.displayMain(windowView);
    }
    public  void setExpressionModel(ExpressionModel expressionModel){

        this.expressionModel=expressionModel;
        this.expressionModel.read();
        this.expressionModel.randomExtraction();


    }
    public  void  addObserver()
    {
        guessModel.addObserver(guessButtonGroupView);

        //Pop up window
        promptModel.addObserver(promptView);

    }

   //Execute observation. After each button click, the model, as the observed object, will notify the observer to view
   public  void excuteObserver(){

       this.guessButtonGroupView.setNumberleView(numberleView);
       this.guessButtonGroupView.setOperatorView(operatorView);

      ArrayList<GbuttonUtil> getGbuttons=numberleView.getGbuttons();
      for(GbuttonUtil g:getGbuttons){   // click number
           g.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   if (guessModel.ifOver) {
                       promptModel.setMessage(Message.OVER);
                       promptModel.notifyStart();
                       return;
                   }

                   if(guessModel.ifFull())
                   {
                       return;
                   }
                   guessModel.push(g.getText());
                   guessModel.setModel(GuessModel.Model.INPUT);//Set the mode to input mode
                   guessModel.notifyStart();
               }
           });
       }




       ArrayList<GbuttonUtil> getGbuttons1=operatorView.getGbuttons();
       for(GbuttonUtil g:getGbuttons1) {
           g.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {


                   if (guessModel.ifOver) {
                       promptModel.setMessage(Message.OVER);
                       promptModel.notifyStart();
                       return;
                   }

                   String  getC=g.getText();
                   //Unable to add when the string is full
                   if(guessModel.ifFull())
                   {
                       return;
                   }

                   guessModel.push(getC);
                   guessModel.setModel(GuessModel.Model.INPUT); //Set the mode to input mode
                   guessModel.notifyStart();
               }
           });
       }
       ArrayList<GbuttonUtil> getGbuttons2=specialView.getGbuttons();
       for(GbuttonUtil g:getGbuttons2) {
           g.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {

                   if (guessModel.ifOver) {
                       promptModel.setMessage(Message.OVER);
                       promptModel.notifyStart();
                       return;
                   }
                   String currentStr = g.getText();

                   if (currentStr.equals(SpecialModel.backSpace)) {//If it is the backspace key
                       if(guessModel.ifEmpty())
                           return;

                       guessModel.setModel(GuessModel.Model.BACKSPACE);
                       guessModel.notifyStart();
                       guessModel.pop();
                       return;
                   }

                   if (!guessModel.ifFull())   //Return if the word is not yet full
                   {
                       promptModel.setMessage(Message.SHORT);
                       promptModel.notifyStart();
                       return;
                   }

                   //Determine if there are assignment symbols present
                   if(!guessModel.ifEqual()){
                       promptModel.setMessage(Message.NO_EQUAL);
                       promptModel.notifyStart();
                       return;
                   }

                   //Determine if there are arithmetic symbols present
                   if(!guessModel.ifOperator()){
                       promptModel.setMessage(Message.NO_OPERATOR);
                       promptModel.notifyStart();
                       return;
                   }

                    GuessButtonGroupView gg1=guessButtonGroupView;

                   //Determine whether arithmetic expressions are legal
                   String text=guessModel.getCurrentExpression();
                   String m[]=text.split("=");
                   if(m.length>2||m.length<1){
                       promptModel.setMessage(Message.WRONGW);
                       promptModel.notifyStart();
                       return;
                   }
                   String express;
                   String result;
                   if(guessModel.ifOperator(m[0])){
                       express=m[0];
                       result=m[1];
                   } else {
                       express=m[1];
                       result=m[0];
                   }
                   InfixExpressionEvaluator expressionEvaluatorUtil=new InfixExpressionEvaluator();
                   if(!expressionEvaluatorUtil.ifLegal(express)){
                       promptModel.setMessage(Message.WRONGW);
                       promptModel.notifyStart();
                       return;

                   }



                   //Determine if the operation result is correct
                   if(!result.equals(String.valueOf(expressionEvaluatorUtil.result))){
                       promptModel.setMessage(Message.WRONGW);
                       promptModel.notifyStart();
                       return;
                   }


                   //Update the status of each character
                   //Set the status of characters
                   guessModel.states=expressionModel.getStates(guessModel.getCurrentExpression());
                   guessModel.setModel(GuessModel.Model.ENTER);
                   guessModel.notifyStart();
                   guessModel.increaseTime();
                   if(text.equals(expressionModel.getCurrentData())){  //The game is over
                       guessModel.ifOver=true;
                       promptModel.setMessage(Message.SUCCESS);
                       promptModel.notifyStart();
                   }

                   if(guessModel.getTime()==2){  //Notification of modifications that can be clicked
                       guessModel.setModel(GuessModel.Model.CLICK);
                       guessModel.notifyStart();
                       guessModel.setModel(GuessModel.Model.NONOE);
                   }

               }
           });
       }


      guessButtonGroupView.getRestart().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {  //Restart
               guessModel.clear();
               expressionModel.randomExtraction();
               guessModel.setModel(GuessModel.Model.RESTART);
               guessModel.notifyStart();
               guessModel.setModel(GuessModel.Model.NONOE);
           }
       });


   }




}
