package view;

import controller.GuessController;
import model.CHAR_STATUS;
import model.GuessModel;
import util.ColorUtil;
import util.GbuttonUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;



public class GuessButtonGroupView implements Observer {

    private GuessModel guessModel;
    private GuessController guessController;

    private NumberleView numberleView;
    private  OperatorView operatorView;
    private GbuttonUtil restartView;



   public  void  setNumberleView(NumberleView numberleView){
       this.numberleView=numberleView;
   }


    public  void setOperatorView(OperatorView operatorView){
        this.operatorView=operatorView;
    }
    public  void setGuessModel(GuessModel guessModel){
        this.guessModel=guessModel;

    }

    public  void setGuessController(GuessController guessController){
        this.guessController=guessController;
    }


    private  final  Integer offsetXrow = 50;
    private  final  Integer offsetYcow = 50;

    private final  Integer B_W=60;
    private final  Integer B_H=60;
    private  final  Integer INTERVAL=10;

    WindowView windowView;
    private  ArrayList<String> datas;
    private ArrayList<GbuttonUtil> gbuttons;
    void setData(ArrayList<String> datas){
        this.datas=datas;
    }



    public GbuttonUtil getRestart(){
        return this.restartView;
    }


    public GuessButtonGroupView()
    {
        restartView=new GbuttonUtil(200,600,100,50,"restart",false);
        setRestartHide(restartView);
    }

    public  void showData(){
        int i;
        int j;
        gbuttons=new ArrayList<>();
        for(i=0;i<6;i++)
        {
            for (j=0;j<7;j++){
                int rowX=i*B_W+i*INTERVAL+offsetXrow;
                int colY=j*B_H+j*INTERVAL+offsetYcow;
                GbuttonUtil button = new GbuttonUtil(rowX,colY,B_W,B_H,"",false);
                button.setCircularborder(ColorUtil.c3);
                button.setBackground(ColorUtil.c4);
                button.setFont(new Font("Arial", Font.BOLD, 30)); // Set font to Arabic, bold, font size 16
                gbuttons.add(button);
            }
        }
    }




    public void  displayMain(WindowView windowView){
        for (GbuttonUtil button : gbuttons)
            windowView.add(button);
        windowView.add(restartView);

    }


    public  ArrayList<GbuttonUtil> getGbuttons() {
        return gbuttons;
    }



    //0-6   7-13
    public void showGuess(int time, String message){

    int lastIndex= message.length()-1;

    char lastChar = message.charAt(lastIndex);
    int index=(time-1)*7+lastIndex;
    GbuttonUtil gbuttonView=gbuttons.get(index);
    gbuttonView.setCircularborder(ColorUtil.c5);
    gbuttonView.setText(String.valueOf(lastChar));
    gbuttonView.grow();
    }

    public  void showExpandOne(GuessModel guessModel){
        int i=guessModel.i;
        String message=guessModel.getCurrentExpression();
        int time=guessModel.getTime();
        ArrayList<CHAR_STATUS > s=guessModel.states;
        int index=(time-1)*7+i;
        GbuttonUtil gbuttonView=gbuttons.get(index);
        Color c = Color.GRAY;
        if(s.get(i)==CHAR_STATUS.CORRECT){
            c=Color.GREEN;
        }
        if(s.get(i)==CHAR_STATUS.EXIST){
            c=Color.ORANGE;
        }
        if(s.get(i)==CHAR_STATUS.NORMAL){
            c=Color.GRAY;
        }
        gbuttonView.setText(String.valueOf(message.charAt(i)));
        gbuttonView.setForeground(Color.WHITE);
        gbuttonView.setBackground(c);
        gbuttonView.setCircularborder(c);
//        gbuttonView.revalidate();
//        gbuttonView.repaint();
    }



        public  void showExpandAll(int time,String message,GuessModel guessModel){



        int i;
        ArrayList<CHAR_STATUS > s=guessModel.states;
        numberleView.clear();
        operatorView.clear();
        for (i=0;i<message.length();i++){
            int index=(time-1)*7+i;
            GbuttonUtil gbuttonView=gbuttons.get(index);
            Color c = Color.GRAY;
            if(s.get(i)==CHAR_STATUS.CORRECT){
               c=Color.GREEN;
            }
            if(s.get(i)==CHAR_STATUS.EXIST){
                c=Color.ORANGE;
            }
            if(s.get(i)==CHAR_STATUS.NORMAL){
                c=Color.GRAY;
            }
            gbuttonView.setText(String.valueOf(message.charAt(i)));
            gbuttonView.setForeground(Color.WHITE);
            gbuttonView.setBackground(c);
            gbuttonView.setCircularborder(c);
            int b=i;
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(b*100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gbuttonView.showExtend(b);
                }
            });
            thread.start();


            ArrayList<GbuttonUtil> gbuttonUtils=numberleView.getGbuttons();
            for (GbuttonUtil g : gbuttonUtils) {
                if(g.getText().equals(String.valueOf(message.charAt(i)))){
                    g.setForeground(Color.white);
                    g.setBackground(c);
                    g.ifselect=true;
                }
            }
            gbuttonUtils=operatorView.getGbuttons();
            for (GbuttonUtil g : gbuttonUtils) {
                if(g.getText().equals(String.valueOf(message.charAt(i)))){
                    g.setForeground(Color.white);
                    g.setBackground(c);
                    g.ifselect=true;
                }
            }

        }



    }




    public  void clearGuess(int time,String message){
        int lastIndex= message.length()-1;
        int index=(time-1)*7+lastIndex;
        GbuttonUtil gbuttonView=gbuttons.get(index);
        gbuttonView.setCircularborder(ColorUtil.c3);
        gbuttonView.setText("");
    }

    public static  void showMessage(String message){
        // 显示消息框
        JOptionPane.showMessageDialog(null, message, "message", JOptionPane.INFORMATION_MESSAGE);
    }



    void clearComponent(){
       for(GbuttonUtil g:gbuttons){
           g.setCircularborder(ColorUtil.c3);
           g.setForeground(Color.black);
           g.setText("");
           g.setBackground(ColorUtil.c4);
       }
      numberleView.clear();
      operatorView.clear();
    }
    @Override
    public void update(Observable o, Object arg) {

           if(o instanceof  GuessModel) {
               GuessModel g = (GuessModel) o;
               GuessModel.Model model = guessModel.getModel();

               if (model == GuessModel.Model.CLICK) { //If it is input mode
                   setRestartClick(restartView);
               }


               if (model == GuessModel.Model.RESTART) { //If it is input mode
                   setRestartHide(restartView);
                   clearComponent();
               }


               if (model == GuessModel.Model.INPUT) { //If it is input mode
               showGuess(g.getTime(),g.getCurrentExpression());
               }


               if (model == GuessModel.Model.BACKSPACE) { //If it is backspace mode
                  clearGuess(g.getTime(),g.getCurrentExpression());
               }

               if (model == GuessModel.Model.ENTER) { //If it is in enter mode
//                    showExpandOne(guessModel);
                   showExpandAll(g.getTime(),g.getCurrentExpression(),g);
               }


           }

    }


    public  void setRestartHide(GbuttonUtil restartView){
        restartView.setForeground(Color.GRAY);
        restartView.setEnabled(false);
    }


    public  void setRestartClick(GbuttonUtil restartView){
        restartView.setForeground(Color.BLACK);
        restartView.setEnabled(true);
    }
}
