import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

/**
 * Created by Lord Daniel on 7/10/2016.
 */
public class BallsGUI extends Application implements Observer{
    private BallsModel model;
    private Pane canvas;
    private Circle circle1;
    private Circle circle2;
    private Scene scene;
    private  Text txt1 = new Text("");
    private  Text txt2 = new Text("");

    @Override
    public void init() throws Exception{
        this.model = new BallsModel();
        model.addObserver( this );
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        scene = new Scene(root, model.getMaxX(), model.getMaxY());
        stage.setScene(scene);
        stage.setTitle("Bouncing Ball");

        VBox vb = new VBox();

        HBox hb = new HBox();
        Button start = new Button("Start");
        start.setOnAction(event -> model.simulateTime());
        hb.getChildren().add(start);

        Button decrVel = new Button("- velocity");
        decrVel.setOnAction(event -> model.decrVel());
        hb.getChildren().add(decrVel);

        Button incrVel = new Button("+ velocity");
        incrVel.setOnAction(event -> model.incrVel());
        hb.getChildren().add(incrVel);

        vb.getChildren().add(hb);
        vb.getChildren().add(txt1);
        vb.getChildren().add(txt2);

        System.out.println(model.getX1()+" "+model.getX2()+"\n"+model.getY1()+" "+model.getY2());

        canvas = new Pane();
        circle1 = new Circle(model.radius);
        circle1.setCenterX(model.getX1());
        circle1.setCenterY(model.getY1());
        circle1.setFill(Paint.valueOf("#ff0000"));

        circle2 = new Circle(model.radius);
        circle2.setCenterX(model.getX2());
        circle2.setCenterY(model.getY2());
        circle2.setFill(Paint.valueOf("#0000ff"));

        canvas.getChildren().add(circle1);
        canvas.getChildren().add(circle2);

        vb.getChildren().add(canvas);

        scene.setRoot(vb);
        stage.setResizable(false);
        stage.show();

    }

    @Override
    public void update(Observable o, Object arg) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                txt1.setText("Ball 1:  X: "+model.getX1()+"; Y: "+model.getY1()+";  X velocity: "+model.Xvelocity1+"; Y velocity: "+model.Yvelocity1);
                txt2.setText("Ball 2:  X: "+model.getX2()+"; Y: "+model.getY2()+";  X velocity: "+model.Xvelocity2+"; Y velocity: "+model.Yvelocity2);

                circle1.setCenterX(model.getX1());
                circle1.setCenterY(model.getY1());
                circle1.setFill(model.getColor1());

                circle2.setCenterX(model.getX2());
                circle2.setCenterY(model.getY2());
                circle2.setFill(model.getColor2());

                model.simulateTime();

                try{
                    Thread.sleep(model.waitTime);
                }
                catch (InterruptedException e){

                }
            }
        }, 0, 5);



    }

    public static void main(String[] args) {
        launch(args);
    }
}
