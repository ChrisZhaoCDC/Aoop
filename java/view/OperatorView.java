package view;

import controller.OperatorController;
import model.OPeratorModel;
import util.ColorUtil;
import util.GbuttonUtil;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


//After the observed class is clicked, it will notify the observer
public class OperatorView implements Observer {



    // The view must have model and controller properties
    private  OPeratorModel oPeratorModel;

    private  OperatorController operatorController;

    public  void setoPeratorModel(OPeratorModel oPeratorModel){
        this.oPeratorModel=oPeratorModel;
    }

    public  void setOperatorController(OperatorController operatorController){
        this.operatorController=operatorController;
    }

    public ArrayList<GbuttonUtil> getGbuttons(){
        return  this.gbuttons;
    }


    private  String currentStr="";
    //The coordinates of the upper left corner
 /*   private  final  Integer offsetXrow = 50;
    private  final  Integer offsetYcol = 600;*/

     private  final  Integer offsetXrow = 500;
     private  final  Integer offsetYcol = 30;

    private final  Integer B_W=50;
    private final  Integer B_H=50;
    private  final  Integer INTERVAL=4;
    private  ArrayList<GbuttonUtil> gbuttons;



    //Get the clicked string
    public  String getCurrentStr(){
        return  this.currentStr;
    }

    public void showData(ArrayList<String> data){
        gbuttons=new ArrayList<>();
        int i=1;
        int j;
        for ( j=0;j<data.size();j++){
            int rowX=i*B_W+i*INTERVAL+offsetXrow;
            int colY=j*B_W+j*INTERVAL+offsetYcol;
            GbuttonUtil gbutton=new GbuttonUtil(rowX,colY,B_W,B_H,data.get(j),true);
            gbutton.setForeground(new Color(90,99,118));
            gbutton.setBackground(new Color(220,225,237));
            gbuttons.add(gbutton);
        }
        buttonStyle();
    }
    public  OperatorView( ){

    }


    void clear(){
        for(GbuttonUtil g:gbuttons){
            g.setBackground(ColorUtil.c1);
            g.setForeground(ColorUtil.c2);
            g.ifselect=false;
        }
    }



    void buttonStyle() {
        for (GbuttonUtil button : gbuttons) {
            button.addMouseListener(new MouseAdapter() {
               @Override
                public void mouseEntered(MouseEvent e) {
                   if(!button.ifselect)
                    button.setBackground(new Color(  196,203,221));
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if(!button.ifselect)
                    button.setBackground(new Color(  196,203,221));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(!button.ifselect)
                    button.setBackground(new Color(220, 225, 237));
                }
            });

        }







        for (GbuttonUtil button:gbuttons){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentStr=button.getText();
//                    setChanged();
//                    notifyObservers();
                }
            });
        }

    }





    public void  displayMain(WindowView windowView){
        for (GbuttonUtil button : gbuttons)
            windowView.add(button);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
