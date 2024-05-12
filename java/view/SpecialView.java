package view;

import controller.SpecialController;
import model.SpecialModel;
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

public class SpecialView implements Observer {

    private  String currentStr="";

    private  final  Integer offsetXrow = 500;
//    private  final  Integer offsetYcol = 870;


    private  final  Integer offsetYcol = 300;
    private final  Integer B_W=140;
    private final  Integer B_H=50;
    private  final  Integer INTERVAL=4;
    private  ArrayList<GbuttonUtil> gbuttons;


    private SpecialModel specialModel;

    public void setSpecialModel(SpecialModel specialModel) {
        this.specialModel = specialModel;
    }

    public void setSpecialController(SpecialController specialController) {
        this.specialController = specialController;
    }

    private SpecialController specialController;


    public ArrayList<GbuttonUtil> getGbuttons(){
        return  this.gbuttons;
    }


    public  String getCurrentStr(){
        return  this.currentStr;
    }
    public void showData(ArrayList<String> data){
        gbuttons=new ArrayList<>();
        int i=1;
        int j;
        int rowX=i*50+i*INTERVAL+offsetXrow;
        for ( j=0;j<data.size();j++){
            int colY=j*B_W+j*INTERVAL+offsetYcol;
            GbuttonUtil gbutton = null;

            if(j==0)
                gbutton= new GbuttonUtil(rowX,colY,B_W,B_H,data.get(j),true);
            if(j==1)
                gbutton=  new GbuttonUtil(rowX,colY,B_W-20,B_H,data.get(j),true);
            gbutton.setForeground(new Color(90,99,118));
            gbutton.setBackground(new Color(220,225,237));
            gbuttons.add(gbutton);
        }
        buttonStyle();
    }


    void buttonStyle() {
        for (GbuttonUtil button : gbuttons) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(  196,203,221));

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    button.setBackground(new Color(  196,203,221));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(220, 225, 237));
                }
            });

        }



        for (GbuttonUtil button:gbuttons){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentStr=button.getText();
                 /*   setChanged(); //
                    notifyObservers(); */
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
