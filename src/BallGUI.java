import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private int radius = 30;
    private  Text txt = new Text("0");

    @Override
    public void init() throws Exception{
        this.model = new BallModel();
        model.addObserver( this );
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, model.getMaxX(), model.getMaxY());
        stage.setScene(scene);
        stage.setTitle("Bouncing Ball");

        VBox vb = new VBox();

        Button start = new Button("Start");
        start.setOnAction(event -> model.simulateTime());
        vb.getChildren().add(start);

        vb.getChildren().add(txt);

        canvas = new Pane();
        circle = new Circle(radius);
        circle.setCenterX(model.getX());
        circle.setCenterY(model.getY());
        canvas.getChildren().add(circle);

        vb.getChildren().add(canvas);

        scene.setRoot(vb);
        stage.setResizable(true);
        stage.show();

    }

    @Override
    public void update(Observable o, Object arg) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                txt.setText(""+model.getX());
                circle.setCenterX(model.getX());
                circle.setCenterY(model.getY());
                System.out.println(model.getX()+" "+model.getY());
                model.simulateTime();
            }
        }, 0, 50);


        //model.simulateTime();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
