package model;

import java.util.Observable;




public class PromptModel  extends Observable implements IPrompt{
    private  Message message;
    public  void setMessage(Message message){
        this.message=message;
    }
    public Message getMessage(){
        return  message;
    }
    @Override
    public void notifyStart() {
        setChanged();
        notifyObservers();
    }
}
