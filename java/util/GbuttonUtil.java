package util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.Observable;
import java.util.Observer;

public class GbuttonUtil extends JButton implements Observer {

    public  boolean ifselect=false;
    //Border colors include rounded corners
    private  Color  circularborder=Color.white;


    public  void  setCircularborder(Color circularborder){
        this.circularborder = circularborder;
    }


    public GbuttonUtil(){
        setContentAreaFilled(false); // Set button transparency
    }

    public Timer timer;

    private static boolean isTimerStopped = false;

    public void showExtend(int i)
    {
        GbuttonUtil gbuttonUtil=this;
        Integer sourceWidth=gbuttonUtil.getWidth();
        Integer sourceHeight=gbuttonUtil.getHeight();
        Point topLeftCorner = gbuttonUtil.getLocation();
        Integer xCoordinate = (int) topLeftCorner.getX();
        Integer yCoordinate = (int) topLeftCorner.getY();
        final Integer[] width = {sourceWidth / 2};
//        final Integer[] height = {sourceHeight / 2};
        final Integer[] height = {0};
        final Integer[] offsetX = {sourceWidth / 2};
        final Integer[] offsetY = {sourceHeight / 2};

        isTimerStopped=false;
        final int[] b = {0};
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


               // width[0] +=8;
                width[0]=sourceWidth+1;
                height[0] +=8;
                offsetX[0]=xCoordinate+(sourceWidth-width[0])/2;
                offsetY[0]=yCoordinate+(sourceHeight-height[0])/2;
                if(width[0]>sourceWidth && height[0]>sourceHeight){
                    gbuttonUtil.setBounds(xCoordinate,yCoordinate,sourceWidth,sourceHeight);
                   // gbuttonUtil.revalidate();
                   // gbuttonUtil.repaint();
                    timer.stop();
                    isTimerStopped=true;

                    return;
                }
                gbuttonUtil.setBounds(offsetX[0],offsetY[0],width[0],height[0]);
              //  gbuttonUtil.revalidate();
               // gbuttonUtil.repaint();
            }
        });
        timer.start();

    }


    public void grow(){

        GbuttonUtil gbuttonUtil=this;
        Integer sourceWidth=gbuttonUtil.getWidth();
        Integer sourceHeight=gbuttonUtil.getHeight();

        Point topLeftCorner = gbuttonUtil.getLocation();
        Integer xCoordinate = (int) topLeftCorner.getX();
        Integer yCoordinate = (int) topLeftCorner.getY();


        final Integer[] width = {sourceWidth / 2};
        final Integer[] height = {sourceHeight / 2};

        final Integer[] offsetX = {sourceWidth / 2};
        final Integer[] offsetY = {sourceHeight / 2};


         timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                width[0] +=8;
                height[0] +=8;
                offsetX[0]=xCoordinate+(sourceWidth-width[0])/2;
                offsetY[0]=yCoordinate+(sourceWidth-height[0])/2;


                if(width[0]>sourceWidth && height[0]>sourceHeight){
                    gbuttonUtil.setBounds(xCoordinate,yCoordinate,sourceWidth,sourceHeight);
                    gbuttonUtil.revalidate();
                    gbuttonUtil.repaint();



                    timer.stop();
                    return;
                }

                gbuttonUtil.setBounds(offsetX[0],offsetY[0],width[0],height[0]);

                gbuttonUtil.revalidate();
                gbuttonUtil.repaint();
            }
        });
        timer.start();
    }



    public GbuttonUtil(int xRow, int yCol, int width, int height, String value, boolean ifClick){
        super(value);
        setOpaque(false);
        setContentAreaFilled(false); // Set button transparency
        this.setBounds(yCol,xRow,width,height);
        this.setEnabled(ifClick);
        this.setBackground(Color.WHITE);
        this.setFont(new Font("Arial", Font.BOLD, 20)); // Set font to Arabic, bold, font size 16
        this.setForeground(Color.BLACK); // Set the font color to black
        LineBorder whiteBorder = new LineBorder(Color.WHITE);
        this.setBorder(whiteBorder);
        this.setMargin(new Insets(0, 0, 0, 0)); // Set the margin of the button to 0

     this.setUI(new BasicButtonUI() {
            @Override
            protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
                AbstractButton b = (AbstractButton) c;
                g.setColor(b.getForeground());
                // Draw text centered
                int x = (c.getWidth() - SwingUtilities.computeStringWidth(b.getFontMetrics(b.getFont()), text)) / 2;
                int y = textRect.y + b.getFontMetrics(b.getFont()).getAscent();
                g.drawString(text, x, y);
            }
        });
    }




    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D roundRect = new RoundRectangle2D.Double(1, 1, getWidth()-3, getHeight()-3, 10, 10);
        // Fill button background color
        g2.setColor(getBackground());
        g2.fill(roundRect);
        // Draw button border
        g2.setColor(circularborder);
        g2.setStroke(new BasicStroke(2)); // Set line thickness
        g2.draw(roundRect);
        super.paintComponent(g);
    }
    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint border
    }
    @Override
    public void update(Observable o, Object arg) {


    }
}
