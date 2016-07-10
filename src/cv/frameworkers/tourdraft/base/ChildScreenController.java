package cv.frameworkers.tourdraft.base;

/**
 * Created by AnaxAndrade on 10-07-2016.
 */
public abstract class ChildScreenController {

    protected RootScreenController root;

    public void setScreenParent(RootScreenController root){
        this.root = root;
    }

    public RootScreenController getBaseScreenController(){
        return this.root;
    }
}
