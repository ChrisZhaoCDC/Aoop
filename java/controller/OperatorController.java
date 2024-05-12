package controller;

import model.OPeratorModel;
import view.OperatorView;
import view.WindowView;

public class OperatorController {

    private OPeratorModel oPeratorModel;
    private OperatorView operatorView;

    private WindowView windowView;

    public OperatorController(OPeratorModel oPeratorModel, OperatorView operatorView) {
        this.oPeratorModel = oPeratorModel;
        this.operatorView = operatorView;
    }



    //Controller Set Model and Controller for View Settings
    public void setView(){
        this.operatorView.setOperatorController(this);
        this.operatorView.setoPeratorModel(this.oPeratorModel);
    }

    public  void setWindowView(WindowView windowView){
        this.windowView = windowView;
    }

    public  void showModel(){
        this.operatorView.showData(oPeratorModel.getDatas());
        this.operatorView.displayMain(windowView);
    }

}
