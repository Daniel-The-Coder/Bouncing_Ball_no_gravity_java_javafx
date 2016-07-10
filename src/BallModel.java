import java.util.Observable;

/**
 * Created by Lord Daniel on 7/10/2016.
 */
public class BallModel extends Observable {
    private int Xvelocity = 5;
    private int Yvelocity = 5;
    private int x;
    private int y;
    private int maxX = 1000;
    private int maxY = 700;
    private boolean flag = true;
    public int radius = 30;

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
        //X velocity
        if(this.x + this.radius >= this.getMaxX()){
            this.Xvelocity *= -1;
        }
        else if(this.x - this.radius <= 0){
            this.Xvelocity *= -1;
        }
        //Y velocity
        if(this.y + 2*this.radius >= this.getMaxY()){
            this.Yvelocity *= -1;
        }
        else if(this.y - this.radius <= 0){
            this.Yvelocity *= -1;
        }

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
