import java.util.Observable;

/**
 * Created by Lord Daniel on 7/10/2016.
 */
public class BallModel extends Observable {
    private int Xvelocity = 1;
    private int Yvelocity = 1;
    private int x;
    private int y;
    private int maxX = 1000;
    private int maxY = 700;
    private boolean flag = true;

    public BallModel(){
        this.x = 100;
        this.y = 100;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public  int getMaxX(){
        return this.maxX;
    }

    public int getMaxY(){
        return this.maxY;
    }

    public void simulateTime(){
        //update velocity based on position

        //update position based on velocity
        this.x += this.Xvelocity;
        this.y += this.Yvelocity;
        if(flag == true) {
            setChanged();
            notifyObservers();
            flag=false;
        }

    }

}
