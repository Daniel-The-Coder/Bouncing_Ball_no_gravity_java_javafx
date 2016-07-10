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
public class BallGUI extends Application implements Observer{
    private BallModel model;
    private Pane canvas;
    private Circle circle;
    private Scene scene;
    private  Text txt = new Text("0");

    @Override
    public void init() throws Exception{
        this.model = new BallModel();
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
        vb.getChildren().add(txt);

        canvas = new Pane();
        circle = new Circle(model.radius);
        circle.setCenterX(model.getX());
        circle.setCenterY(model.getY());
        circle.setFill(Paint.valueOf("#ff0000"));
        canvas.getChildren().add(circle);

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
                txt.setText("X: "+model.getX()+"; Y: "+model.getY()+";   speed: "+model.speed+";   X velocity: "+model.Xdirection*model.speed+"; Y velocity: "+model.Ydirection*model.speed);
                circle.setCenterX(model.getX());
                circle.setCenterY(model.getY());
                circle.setFill(model.getColor());
                System.out.println(model.getX()+" "+model.getY());
                model.simulateTime();
            }
        }, 0, 20);



    }

    public static void main(String[] args) {
        launch(args);
    }
}
