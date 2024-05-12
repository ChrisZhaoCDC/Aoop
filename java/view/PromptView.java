package view;


import model.PromptModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PromptView  implements Observer {

    private  static  int windowWidth = 300; // Window width
    private  static   int windowHeight = 100; // Window height


    public  void showData(String text)
    {

        JFrame frame = new JFrame("Example of Rectangle");
        // Set window size and position
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get screen size
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;
        frame.setBounds(x, y, windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setForeground(Color.black);


        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(Color.WHITE);
                g2d.setComposite(AlphaComposite.Src);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                g2d.setColor(Color.black);

                g2d.setColor(Color.BLACK);
                float borderWidth = 1;
                g2d.setStroke(new BasicStroke(borderWidth));
                g2d.draw(new RoundRectangle2D.Float(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth, 20, 20));
                 g2d.setColor(Color.BLACK);

                Font font = new Font("Arial", Font.BOLD, 20);
                g2d.setFont(font);
                ArrayList<String> textList=new ArrayList<>();
                String[] words = text.split("\\s+");
                FontMetrics fm = g2d.getFontMetrics(font);
                String message="";
                for (int i=0;i<words.length;i++) {
                    message=message+words[i]+" ";
                    int x =  fm.stringWidth(message) ;
                    if(x>getWidth()-60){
                        textList.add(message);
                        message="";
                    }
                    if(i==words.length-1)
                        textList.add(message);
                }
                int offsetY=(getHeight()-textList.size()*fm.getHeight())/2;
                int i;
                for(i=0;i<textList.size();i++){
                    String text1=textList.get(i);
                    int x = (getWidth() - fm.stringWidth(text1)) / 2;
//                    g2d.drawString(text1, x,y*10 );
                    g2d.drawString(text1,  x,offsetY+fm.getHeight()*(i+1) );
                }
                g2d.dispose();
            }
        };



        frame.add(panel);


        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                frame.setShape(new RoundRectangle2D.Float(0, 0, frame.getWidth(), frame.getHeight(), 20,20));
            }
        });
        frame.setVisible(true);
        try {
            Thread.sleep(1000);
            frame.setVisible(false);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void update(Observable o, Object arg) {
        if( o instanceof PromptModel){
            PromptModel promptModel = (PromptModel) o;
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    showData(promptModel.getMessage().getMessage());
                }
            });
            thread.start();

        }
    }

}
