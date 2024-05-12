package model;



public enum Message {
    NO_EQUAL("No equal \"=\" sign"), //No equal sign
    NO_OPERATOR("There must be at least one sign +-*/\n"),//No operation symbols
    SHORT("Too short"),  //Input too short
    WRONGW("The left side is not equal to the right"), //Error or unequal results
    COLLECT(""),  //Correct but not the answer
    SUCCESS("Success"),//Correct and the answer is correct
    OVER("GAME OVER");
    private String message;
    Message(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}