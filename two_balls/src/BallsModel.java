import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import javafx.scene.paint.Paint;

/**
 * Created by Lord Daniel on 7/10/2016.
 */
public class BallsModel extends Observable {
    private int speed = 4;
    public double Xvelocity1 = speed;
    public double Yvelocity1 = speed;
    public double Xvelocity2 = -1*speed;
    public double Yvelocity2 = speed;
    private int x1 = 0;
    private int y1 = 0;
    private int x2 = 0;
    private int y2 = 0;
    private int maxX = 700;
    private int maxY = 500;
    private boolean flag = true;
    public int radius = 40;
    private int colorIndex1;
    private int colorIndex2;
    public int waitTime;

    private ArrayList<Paint> colors;

    public BallsModel(){
        this.waitTime=20;
        Random r = new Random();
        double d = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
        while (d < radius*2 + 20) { // to make sure balls are at least 20 units away from each other
            this.x1 = r.nextInt(maxX - radius - 20 - radius - 20) + radius + 20;
            this.y1 = r.nextInt(maxY - radius - 20 - radius - 20) + radius + 20;
            this.x2 = r.nextInt(maxX - radius - 20 - radius - 20) + radius + 20;
            this.y2 = r.nextInt(maxY - radius - 20 - radius - 20) + radius + 20;
            d = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
        }
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
        if(waitTime<900) {
            waitTime++;
        }
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
        double d = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
        if(d - 2*this.radius < 1){
            System.out.println("Collision!");
            double th;
            if(x1==x2){
                th = Math.asin(1);
            }
            else {
                th = Math.atan((y2 - y1) / (x2 - x1));
            }
            Xvelocity1 = Xvelocity1*Math.pow(Math.sin(th),2) - (Yvelocity2*Math.sin(th) + Xvelocity2*Math.cos(th))*Math.cos(th);
            Xvelocity2 = Xvelocity2*Math.pow(Math.sin(th),2) - (Yvelocity1*Math.sin(th) + Xvelocity1*Math.cos(th))*Math.cos(th);
            Yvelocity1 = Yvelocity1*Math.pow(Math.cos(th),2) - (Yvelocity2*Math.sin(th) + Xvelocity2*Math.cos(th))*Math.sin(th);
            Yvelocity2 = Yvelocity2*Math.pow(Math.cos(th),2) - (Yvelocity1*Math.sin(th) + Xvelocity1*Math.cos(th))*Math.sin(th);
        }

    }

}
