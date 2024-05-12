package controller;

import model.SpecialModel;
import view.SpecialView;
import view.WindowView;

public class SpecialController {
    private SpecialModel specialModel;
    private SpecialView specialView;
    private  WindowView windowView;

    public  SpecialController(SpecialModel specialModel, SpecialView specialView){
        this.specialModel=specialModel;
        this.specialView =specialView;

    }


    public  void setView(){
        this.specialView.setSpecialController(this);
        this.specialView.setSpecialModel(specialModel);
    }


    public  void setWindowView(WindowView windowView){
        this.windowView = windowView;
    }


    public  void showModel(){
        this.specialView.showData(specialModel.getDatas());
        this.specialView.displayMain(windowView);
    }

}
