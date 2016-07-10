import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.paint.Paint;

/**
 * Created by Lord Daniel on 7/10/2016.
 */
public class BallsModel extends Observable {
    public int Xvelocity1 = 10;
    public int Yvelocity1 = 10;
    public int Xvelocity2 = -10;
    public int Yvelocity2 = 10;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int maxX = 700;
    private int maxY = 500;
    private boolean flag = true;
    public int radius = 30;
    private int colorIndex1;
    private int colorIndex2;
    public int waitTime;

    private ArrayList<Paint> colors;

    public BallsModel(){
        this.waitTime=20;
        this.x1 = 200;
        this.y1 = 100;
        this.x2 = 150;
        this.y2 = 200;
        this.colorIndex1 = 0;
        this.colorIndex2 = 3;

        this.colors = new ArrayList<>();
        colors.add(Paint.valueOf("#ff0000"));
        colors.add(Paint.valueOf("#0000ff"));
        colors.add(Paint.valueOf("#912cee"));
        colors.add(Paint.valueOf("#00ff00"));
        colors.add(Paint.valueOf("#ff4500"));
        colors.add(Paint.valueOf("#7171c6"));
    }

    public int getX1(){
        return this.x1;
    }

    public int getY1(){
        return this.y1;
    }

    public int getX2(){
        return this.x2;
    }

    public int getY2(){
        return this.y2;
    }

    public  int getMaxX(){
        return this.maxX;
    }

    public int getMaxY(){
        return this.maxY;
    }

    public Paint getColor1(){
        return this.colors.get(this.colorIndex1);
    }

    public Paint getColor2(){
        return this.colors.get(this.colorIndex2);
    }

    private void nextColor1(){
        if(this.colorIndex1 == this.colors.size()-1){
            this.colorIndex1 = 0;
        }
        else{
            this.colorIndex1++;
        }
    }

    private void nextColor2(){
        if(this.colorIndex2 == this.colors.size()-1){
            this.colorIndex2 = 0;
        }
        else{
            this.colorIndex2++;
        }
    }

    public void decrVel(){
        waitTime++;
    }

    public void incrVel(){
        if(this.waitTime>0){
            waitTime--;
        }
    }


    public void simulateTime(){
        //Collision with wall
        //Ball 1
        //update velocity based on position
        //X velocity
        if(this.x1 + this.radius >= this.getMaxX()){
            this.Xvelocity1 *= -1;
            nextColor1();
        }
        else if(this.x1 - this.radius <= 0){
            this.Xvelocity1 *= -1;
            nextColor1();
        }
        //Y velocity
        if(this.y1 + 2*this.radius >= this.getMaxY()){
            this.Yvelocity1 *= -1;
            nextColor1();
        }
        else if(this.y1 - this.radius <= 0){
            this.Yvelocity1 *= -1;
            nextColor1();
        }

        //update position based on velocity
        this.x1 += this.Xvelocity1;
        this.y1 += this.Yvelocity1;

        //Ball 2
        //update velocity based on position
        //X velocity
        if(this.x2 + this.radius >= this.getMaxX()){
            this.Xvelocity2 *= -1;
            nextColor2();
        }
        else if(this.x2 - this.radius <= 0){
            this.Xvelocity2 *= -1;
            nextColor2();
        }
        //Y velocity
        if(this.y2 + 2*this.radius >= this.getMaxY()){
            this.Yvelocity2 *= -1;
            nextColor2();
        }
        else if(this.y2 - this.radius <= 0){
            this.Yvelocity2 *= -1;
            nextColor2();
        }

        //update position based on velocity
        this.x2 += this.Xvelocity2;
        this.y2 += this.Yvelocity2;
        if(flag == true) {
            setChanged();
            notifyObservers();
            flag=false;
        }

        //Collision between balls

    }

}
