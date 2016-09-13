import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.animation.*;
import javafx.util.*;
import javafx.geometry.*;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.lang.Object;

public class lab1analogclock extends Application{
	public static void main (String [] args)
	{
		launch(args);
		
	}

	@Override public void start(Stage primaryStage) throws Exception
	{
		//Create fixed parts of clock
		int radius = 300;
		int centerx = 360;
		int centery = 360;
		int dashlength = 50;
		int shortdashlength = 10;
		int hourhandlength = 150;
		int minutehandlength = 200;
		int secondhandlength = 250;
		
		Color transparent = Color.TRANSPARENT;
		Color black = Color.BLACK;
		Color cyan = Color.CYAN;
		
		Circle clock = new Circle(centerx, centery, radius);//Create circle
		clock.setFill(transparent);
		clock.setStroke(black);
		
		Line marks[] = new Line[12];//Create marks on clock edge
		for (int i = 0; i < marks.length; i++)
		{
			marks[i] = new Line();
			marks[i].setStartX(Math.cos(Math.PI/6 * i) * radius + centerx);
			marks[i].setStartY(Math.sin(Math.PI/6 * i) * radius + centery);
			marks[i].setEndX(Math.cos(Math.PI/6 * i) * (radius - dashlength) + centerx);
			marks[i].setEndY(Math.sin(Math.PI/6 * i) * (radius - dashlength) + centery);
		}
		
		Line minimarks[] = new Line[60];//Create tiny marks
		for (int s = 0; s < minimarks.length; s++)
		{
			minimarks[s] = new Line();
			minimarks[s].setStartX(Math.cos(Math.PI/30 * s) * radius + centerx);
			minimarks[s].setStartY(Math.sin(Math.PI/30 * s) * radius + centery);
			minimarks[s].setEndX(Math.cos(Math.PI/30 * s) * (radius - shortdashlength) + centerx);
			minimarks[s].setEndY(Math.sin(Math.PI/30 * s) * (radius - shortdashlength) + centery);
		}
		
		//Create the moving parts of the clock
		
		Line secondhand = new Line();//Create hands
		Line minutehand = new Line();
		Line hourhand = new Line();
		secondhand.setStroke(cyan);

		AnimationTimer timer = new AnimationTimer()//Increments clock
		{
			@Override
			public void handle(long arg0)
			{
				Calendar calendar = Calendar.getInstance();
				
				int second = calendar.get(SECOND);
				int minute = calendar.get(MINUTE) + second/60;
				int hour = calendar.get(HOUR) + minute/60;
				
				secondhand.setStartX(centerx);
				secondhand.setStartY(centery);
				secondhand.setEndX(-Math.sin(Math.PI * second / 30) * secondhandlength + centerx);
				secondhand.setEndY(-Math.cos(Math.PI * second / 30) * secondhandlength + centery);
				
				minutehand.setStartX(centerx);
				minutehand.setStartY(centery);
				minutehand.setEndX(-Math.sin(Math.PI * minute / 30) * minutehandlength + centerx);
				minutehand.setEndY(-Math.cos(Math.PI * minute / 30) * minutehandlength + centery);
				
				hourhand.setStartX(centerx);
				hourhand.setStartY(centery);
				hourhand.setEndX(-Math.sin(Math.PI * hour / 30) * hourhandlength + centerx);
				hourhand.setEndY(-Math.cos(Math.PI * hour / 30) * hourhandlength + centery);
			}
		};
		timer.start();
		
		//Add things to window
		
		Group root = new Group();
		root.getChildren().addAll(clock);//Add circle
		for (int a = 0; a < marks.length; a++)//Add big marks
		{
			root.getChildren().addAll(marks[a]);
		}
		for (int b = 0; b < minimarks.length; b++)//Add tiny marks
		{
			root.getChildren().addAll(minimarks[b]);
		}
		root.getChildren().addAll(hourhand, minutehand, secondhand);//Add hands
		
		Scene scene = new Scene(root, 720, 720);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Clock");
		primaryStage.show();
	}
}
/*THINGS LEFT TO DO
 * Fix x and y values of hands
 */
