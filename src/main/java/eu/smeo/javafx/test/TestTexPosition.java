package eu.smeo.javafx.test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by smeo on 1/7/15.
 */
public class TestTexPosition extends Application {

    private Line line;

    public static void main(String[] args) {
        Application.launch(args);
    }


    private Scene getScene() {
        Group group = new Group();

        Text text1 = genText(0,0,20);
        printProps(text1);

        Text text2 = genText(0,20,20);
        printProps(text2);

        Text text3 = genText(20,40,20);
        printProps(text3);

        Text text4 = genText(0,60,20);
        printProps(text4);

        Text text5 = genText(text4.getLayoutBounds().getWidth()/2.0,80,20);
        printProps(text5);

        Text text6 = genText(text4.getLayoutBounds().getWidth(),100,20);
        printProps(text6);

        Pane gridPane = paintGrid(640, 480);
        group.getChildren().addAll(gridPane, text1, text2, text3, text4, text5, text6);
        Scene scene = new Scene(group, 640, 480);

        return scene;
    }

    private Text genText(double x, double y, double fontsize) {
        Text text = new Text(x,y, "Pos: " + x +"/"+y+" fontsize: " + fontsize);
        text.setFont(Font.font("Verdana", fontsize));
        return text;
    }


    private Pane paintGrid(double width, double height){
        double lineDistanceX = 10;
        double lineDistanceY = 10;

        Pane gridPane = new Pane();
        gridPane.getChildren().clear();

        for (int i = 0; i < (width / lineDistanceX); i++){
            Line newLine = new Line(i * lineDistanceX, 0, i * lineDistanceX, height);
            newLine.setStroke(Color.GRAY);
            gridPane.getChildren().add(newLine);
        }
        for (int i = 0; i < (height / lineDistanceY); i++){
            Line newLine = new Line(0, i * lineDistanceY, width, i * lineDistanceY);
            newLine.setStroke(Color.GRAY);

            gridPane.getChildren().add(newLine);
        }
        return gridPane;
    }
    private void printProps(Text text) {
        System.out.println("'"+text.getText()+"' - x: " + text.getX() + " y: " + text.getY() + " w: " + text.getLayoutBounds().getWidth() + " h: " + text.getLayoutBounds().getHeight());
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(getScene());
        stage.show();
    }
}