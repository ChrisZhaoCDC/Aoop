import controller.GuessController;
import model.ExpressionModel;
import org.junit.Test;
import view.GuessButtonGroupView;
import view.WindowView;

import javax.swing.*;
import java.awt.*;

public class UnitTest {




    //main window test
    @Test
    public  void  test01(){
        WindowView windowView=new WindowView();
        while (true){
          //wait close
        }
    }



    //file load test
    @Test
    public  void  test02(){
        ExpressionModel expressionModel=new ExpressionModel();
        expressionModel.read();
        expressionModel.randomExtraction();
        for (int i=0;i< expressionModel.getDatas().size();i++){
            System.out.println(expressionModel.getDatas().get(i));
        }
    }


    //guess display test
    @Test
    public  void  test03(){
        WindowView windowView=new WindowView();
        GuessButtonGroupView guessButtonGroupView=new GuessButtonGroupView();
        guessButtonGroupView.showData();
        guessButtonGroupView.displayMain(windowView);
        windowView.repaint();
        while (true){

        }
    }



    @Test
    public  void  test04(){
        int x = 10;
        //
        assert x > 0 : "x should be greater than 0";
        System.out.println("x is a positive number");
    }


    @Test
    public  void  test05(){
        int x = 10;
        //
        assert x< 0 : "x should be greater than 0";
        System.out.println("x is a positive number");
    }



    //Pop up rectangle test

    @Test
    public  void test06(){
        JFrame frame = new JFrame("Example of Rectangle");
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE); // set wiht
                g.fillRect(50, 50, 200, 100); // draw rectangle
                g.setColor(Color.BLACK); // set text black
                g.drawString("Hello", 100, 100); // show hello
            }
        };

        try {
            panel.repaint();
            Thread.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }



}
