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

public class lab2sudoku extends Application{
	public static void main (String [] args)
	{
		launch(args);
	}

	@Override public void start(Stage primaryStage) throws Exception
	{
		
		
		
		Group root = new Group();
		
		Scene scene = new Scene(root, 720, 720);//Create window
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sudoku");
		primaryStage.show();
	}
}
