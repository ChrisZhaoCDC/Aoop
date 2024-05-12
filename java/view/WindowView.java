package view;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class WindowView extends JFrame implements Observer {
     private  final  Integer W_WIDTH = 800;
     private  final  Integer W_HEIGHT = 700;

     public  WindowView(){
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setSize(W_WIDTH, W_HEIGHT);
         // Set the background color of the content panel to white
         getContentPane().setBackground(Color.WHITE);
         // 使用绝对布局
         this.setLayout(null);
         this.setVisible(true);
     }

    @Override
    public void update(Observable o, Object arg) {

    }
}
