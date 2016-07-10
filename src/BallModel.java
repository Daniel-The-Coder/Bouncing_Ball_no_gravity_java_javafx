import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.paint.Paint;

/**
 * Created by Lord Daniel on 7/10/2016.
 */
public class BallModel extends Observable {
    public int Xdirection = 1;
    public int Ydirection = 1;
    public int speed = 8;
    private int x;
    private int y;
    private int maxX = 1000;
    private int maxY = 700;
    private boolean flag = true;
    public int radius = 30;
    private int colorIndex;

    private ArrayList<Paint> colors;

    public BallModel(){
        this.x = 100;
        this.y = 100;
        this.colorIndex = 0;

        this.colors = new ArrayList<>();
        colors.add(Paint.valueOf("#ff0000"));
        colors.add(Paint.valueOf("#0000ff"));
        colors.add(Paint.valueOf("#912cee"));
        colors.add(Paint.valueOf("#00ff00"));
        colors.add(Paint.valueOf("#ff4500"));
        colors.add(Paint.valueOf("#7171c6"));
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

    public Paint getColor(){
        return this.colors.get(this.colorIndex);
    }

    private void nextColor(){
        if(this.colorIndex == this.colors.size()-1){
            this.colorIndex = 0;
        }
        else{
            this.colorIndex++;
        }
    }

    public void decrVel(){
        if(this.speed > 0){
             speed--;
        }
    }

    public void incrVel(){
        speed++;
    }

    public void simulateTime(){
        //update velocity based on position
        //X velocity
        if(this.x + this.radius >= this.getMaxX()){
            this.Xdirection *= -1;
            nextColor();
        }
        else if(this.x - this.radius <= 0){
            this.Xdirection *= -1;
            nextColor();
        }
        //Y velocity
        if(this.y + 2*this.radius >= this.getMaxY()){
            this.Ydirection *= -1;
            nextColor();
        }
        else if(this.y - this.radius <= 0){
            this.Ydirection *= -1;
            nextColor();
        }

        //update position based on velocity
        this.x += this.Xdirection*speed;
        this.y += this.Ydirection*speed;
        if(flag == true) {
            setChanged();
            notifyObservers();
            flag=false;
        }

    }

}
