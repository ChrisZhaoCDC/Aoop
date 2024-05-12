package view;// NumberleView.java
import model.NumberleModel;
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



public class NumberleView extends Observable implements Observer {
    private  String currentStr="";

   /* private  final  Integer offsetXrow = 50;
    private  final  Integer offsetYcol = 600;*/


     private  final  Integer offsetXrow = 500;
     private  final  Integer offsetYcol = 30;
    private final  Integer B_W=50;
    private final  Integer B_H=50;
    private  final  Integer INTERVAL=4;

    private NumberleModel model;

    private  ArrayList<String> datas;


    private  ArrayList<GbuttonUtil> gbuttons;

    public void setShow(ArrayList<String> datas){
    this.datas=datas;
    }




    public NumberleView(NumberleModel model){
        this.model=model;
        setShow( this.model.getDatas());
        setInit();
    }


    public  ArrayList<GbuttonUtil> getGbuttons(){
        return  gbuttons;
    }


    void setInit(){
        gbuttons=new ArrayList<>();
        int i=0;
        int j;
        for ( j=0;j<10;j++){
            int rowX=i*B_W+i*INTERVAL+offsetXrow;
            int colY=j*B_W+j*INTERVAL+offsetYcol;
            GbuttonUtil gbutton=new GbuttonUtil(rowX,colY,B_W,B_H,String.valueOf(j),true);
            gbutton.setBackground(ColorUtil.c1);
            gbutton.setForeground(ColorUtil.c2);
            gbuttons.add(gbutton);
        }
        buttonStyle();//Set button style
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
                    button.setBackground(new Color(  196,203,221)); // Set the background color to purple when the mouse enters
                }



                @Override
                public void mousePressed(MouseEvent e) {
                    if(!button.ifselect)
                    button.setBackground(new Color(  196,203,221)); // Set the background color to purple when the mouse enters
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(!button.ifselect)
                    button.setBackground(new Color(220, 225, 237)); // Restore default background color when mouse leaves
                }
            });

        }


        for (GbuttonUtil button:gbuttons){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentStr=button.getText();
                    setChanged(); // The setting status has changed
                    notifyObservers(); // Notify observers
                }
            });
        }

    }


    public void setShow(WindowView windowView){
        for (GbuttonUtil button : gbuttons)
            windowView.add(button);

    }
    public void initializeFrame() {

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("asdsad");
    }
}