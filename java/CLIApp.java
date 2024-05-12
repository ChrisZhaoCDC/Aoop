import model.ExpressionModel;
import model.GuessModel;

import java.util.Scanner;

public class CLIApp {
    public static void main(String[] args) {
        // Create a Scanner object
        Scanner scanner = new Scanner(System.in);
        ExpressionModel expressionModel = new ExpressionModel();
        expressionModel.read();
        expressionModel.randomExtraction();
        GuessModel guessModel=new GuessModel();


             while (true){
                 System.out.println("Welcome to Numberle Game");
                 System.out.println("Target Equation:"+expressionModel.getCurrentData());
                 System.out.println("Remaining attempts:"+(7-guessModel.getTime()));
                System.out.println("Please enter your guess:");
               String inputString = scanner.nextLine();
               if(inputString.equals(expressionModel.getCurrentData())){
                   System.out.println("You won!");
                   System.out.println("Do you want to play again?(Y/N)");
                   String inputString2 = scanner.nextLine();
                   if(inputString2.equals("Y")){
                       expressionModel.randomExtraction();
                       guessModel.clear();
                       continue;
                   }else{
                      return;
                   }
               }else {
                   System.out.println("Not valid.Enter your guess again");
                   guessModel.increaseTime();
                   if(guessModel.getTime()==1)
                       return;
               }

           }


    }
}
