package cv.frameworkers.tourdraft.base;

import cv.frameworkers.tourdraft.AppMain;

/**
 * Created by AnaxAndrade on 10-07-2016.
 */
public abstract class ChildScreenController {

    protected RootScreenController root;

    protected AppMain main;

    public void setScreenParent(RootScreenController root){
        this.root = root;
    }

    public RootScreenController getBaseScreenController(){
        return this.root;
    }

    public AppMain getAppMain() {
        return main;
    }

    public void setAppMain(AppMain main) {
        this.main = main;
    }

    public void onResume(){}
}
