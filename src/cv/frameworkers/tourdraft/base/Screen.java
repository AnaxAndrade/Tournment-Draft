package cv.frameworkers.tourdraft.base;

/**
 * Created by AnaxAndrade on 10-07-2016.
 */
public class Screen {
    private String ID;
    private String FILE;

    public Screen(String ID, String FILE) {
        this.ID = ID;
        this.FILE = FILE;
    }

    public Screen() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFILE() {
        return FILE;
    }

    public void setFILE(String FILE) {
        this.FILE = FILE;
    }
}
