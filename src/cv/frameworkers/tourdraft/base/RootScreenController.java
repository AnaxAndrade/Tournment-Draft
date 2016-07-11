package cv.frameworkers.tourdraft.base;

import cv.frameworkers.tourdraft.AppMain;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.HashMap;

/**
 * Criado por by AnaxAndrade on 10-07-2016.
 * Inspirado em ScreensFamework de Angela Caicedo
 */
public class RootScreenController extends StackPane {
    private HashMap<String, Node> screens = new HashMap<>();
    private HashMap<String, ChildScreenController> screensControllers = new HashMap<>();

    AppMain mainApp;

    public RootScreenController() {

    }

    //Add the screen to the collection
    public void addScreen(String name, Node screen, ChildScreenController controller) {
        screens.put(name, screen);
        screensControllers.put(name, controller);
    }

    //Returns the Node with the appropriate name
    public Node getScreen(String name) {
        return screens.get(name);
    }

    //Loads the fxml file, add the screen to the screens collection and
    //finally injects the screenPane to the controller.
    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(AppMain.class.getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ChildScreenController myScreenControler = ((ChildScreenController) myLoader.getController());
            myScreenControler.setScreenParent(this);
            myScreenControler.setAppMain(mainApp);
            addScreen(name, loadScreen, myScreenControler);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //This method tries to displayed the screen with a predefined name.
    //First it makes sure the screen has been already loaded.  Then if there is more than
    //one screen the new screen is been added second, and then the current screen is removed.
    // If there isn't any screen being displayed, the new screen is just added to the root.
    public boolean setScreen(final String name) {
        if (screens.get(name) != null) {   //screen loaded
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {    //if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(500), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                getChildren().remove(0);                    //remove the displayed screen
                                getChildren().add(0, screens.get(name));     //add the screen
                                screensControllers.get(name).onResume();
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));       //no one else been displayed, then just show
                screensControllers.get(name).onResume();
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");

            return false;
        }

    }

    public void setMainApp(AppMain appMain){
        this.mainApp = appMain;
    }

    //This method will remove the screen with the given name from the collection of screens
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }
}
