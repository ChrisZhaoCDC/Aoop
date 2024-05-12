import controller.*;
import model.*;
import view.*;
import javax.swing.*;
//https://numberle.org
public class GUIApp {
    static WindowView windowView;
    static GuessButtonGroupView guessButtonGroupView;
    static  NumberleView numberleView;
    static     NumberleModel modelNumber;
    static  NumberleController controller;
    static   OPeratorModel oPeratorModel;
    static  OperatorView operatorView;
    static   OperatorController operatorController;
    static   SpecialModel specialModel;
    static     SpecialView specialView;
    static    SpecialController specialController;
    static   ExpressionModel expressionModel;
    static GuessModel guessModel;
    static  GuessController guessController;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        createAndShowGUI();
                    }
                }
        );
    }

    //https://numberle.org/
    public static void createAndShowGUI() {
          //Main window initialization
         windowView = new WindowView();
         //Assert 1 that the main window exists
         assert  windowView!=null:"The main window does not exist";
        //number
         modelNumber = new NumberleModel();
         assert  modelNumber!=null:"The digital model does not exist";
         numberleView=new NumberleView(modelNumber);
         assert  modelNumber!=null:"The digital view does not exist";
         controller = new NumberleController(modelNumber);
         assert  controller!=null:"The digital controller cannot be empty";
         controller.setView(numberleView);
         numberleView.setShow(windowView);
         //Operational symbol
         oPeratorModel=new OPeratorModel();
         operatorView =new OperatorView();
         operatorController=new OperatorController(oPeratorModel,operatorView);
         operatorController.setView();  //Set the view and controller properties for the mode
         operatorController.setWindowView(windowView);  //The controller obtains other views and displays them in the main window view
         operatorController.showModel();  //Display Model
         //other symbol
         specialModel=new SpecialModel();
         specialView=new SpecialView();
         specialController=new SpecialController(specialModel,specialView);
         specialController.setWindowView(windowView);
         specialController.setView();
         specialController.showModel();
         //guess num
         guessModel=new GuessModel();
         expressionModel=new ExpressionModel();
         guessButtonGroupView=new GuessButtonGroupView();
         guessController=new GuessController(guessModel,guessButtonGroupView);
         guessController.setWindowView(windowView);
         guessController.setView();
         guessController.setExpressionModel(expressionModel);
         guessController.setNumberleView(numberleView);
         guessController.setOperatorView(operatorView);
         guessController.setSpecialView(specialView);
         guessController.showModel();
         guessController.addObserver();
         guessController.excuteObserver();
         windowView.repaint();
    }
}