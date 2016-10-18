import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.event.*;


public class lab2sudoku extends Application{
	
	int placeholder = 1;
	Button[][] Spaces = new Button[9][9];

	public static void main (String [] args)
	{
		
		launch(args);
	}

	@Override public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setTitle("Sudoku Window"); //Give the stage a title

		Button[] Buttons = new Button[9];//Buttons for creating numbers
		for (int i = 0; i < 9; i++)
		{
			Buttons[i] = new Button ();
			Buttons[i].setText(Integer.toString(i + 1));
			Buttons[i].relocate(250 + (i * 30), 50);
			int a = i;
			Buttons[i].setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event)
				{
					 placeholder = a + 1;
				}
			});
		}
		
		for (int i = 0; i < 9; i++)//Creates buttons in boxes and placeholder for solving
		{
			for (int j = 0; j < 9; j++)
			{
				Spaces [i][j] = new Button();
				Spaces [i][j].setMinSize(30, 30);
				Spaces [i][j].relocate(160 + (50 * i), 160 + (50 * j));
				int a = i;
				int b = j;
				Spaces [i][j].setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						if(placeholder == 0)//Makes remove button work
						{
							Spaces [a][b].setText("");
						}
						else if(checker (a, b, placeholder) == true)
						{
							Spaces [a][b].setText(Integer.toString(placeholder));
						}
					}
				});
			}
		}
		
		Button Reset = new Button ();//Changes all values to zero
		Reset.setText("Reset");
		Reset.relocate(500, 100);
		Reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)
			{
				for (int i = 0; i < 9; i++)
				{
					for (int j = 0; j < 9; j++)
					{
						Spaces [i][j].setText("");
					}
				}
			}
		});
		
		Button Remove = new Button ();//Removes individual numbers
		Remove.setText("Remove");
		Remove.relocate(550, 100);
		Remove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)
			{
				placeholder = 0;
			}
		});
		
		Button Go = new Button ();//Solves sudoku
		Go.setText("Go");
		Go.relocate(350, 100);
		Go.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)
			{
				solve(0, 0);
			}
		});
		
		int linestart = 150;
		int lineend = 600;
		double thicklinewidth = 3.0;
		
		Line horizontallines[] = new Line[10];//Draw horizontal lines
		for (int i = 0; i < horizontallines.length; i++)
		{
			horizontallines[i] = new Line();
			
			if(i % 3 == 0)
			{
				horizontallines[i].setStrokeWidth(thicklinewidth);
			}
			
			horizontallines[i].setStartX(linestart);
			horizontallines[i].setStartY(linestart + (i  * 50));
			horizontallines[i].setEndX(lineend);
			horizontallines[i].setEndY(linestart + (i * 50));
		}
		
		Line verticallines[] = new Line[10];//Draw vertical lines
		for (int i = 0; i < verticallines.length; i++)
		{
			verticallines[i] = new Line();

			if(i % 3 == 0)
			{
				verticallines[i].setStrokeWidth(thicklinewidth);
			}
			
			verticallines[i].setStartX(linestart + (i * 50));
			verticallines[i].setStartY(linestart);
			verticallines[i].setEndX(linestart + (i * 50));
			verticallines[i].setEndY(lineend);
		}
		
		Group root = new Group();//Add stuff to window
		for (int i = 0; i < horizontallines.length; i++)
		{
			root.getChildren().addAll(horizontallines[i]);
		}
		for (int i = 0; i < verticallines.length; i++)
		{
			root.getChildren().addAll(verticallines[i]);
		}
		for (int i = 0; i < Buttons.length; i++)
		{
			root.getChildren().addAll(Buttons[i]);
		}
		for (int i = 0; i < Spaces.length; i++)
		{
			for (int j = 0; j < Spaces.length; j++)
			{
				root.getChildren().addAll(Spaces[i][j]);
			}
		}
		root.getChildren().addAll(Reset, Go, Remove);
		Scene scene = new Scene(root, 750, 750);//Create window
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sudoku");
		primaryStage.show();
	}
	
	public int buttontoint(Button Button)//Turns text in button to integer
	{
		String text = Button.getText();
		if(text.equals(""))
		{
			return 0;
		}
		return Integer.parseInt(text);
		
	}
	
	public boolean checker(int row, int col, int val)//Duplicate checker
	{
		for(int i = 0; i < 9; i++)
		{
			if (val == buttontoint(Spaces[row][i]))
			{
				return false;
			}
			if (val == buttontoint(Spaces[i][col]))
			{
				return false;
			}
		}
		
		int boxrow = 3 * (row / 3);//Rounds down, finds top left corner of each box
		int boxcol = 3 * (col / 3);
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(val == buttontoint(Spaces[boxrow + i][boxcol + j]))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean solve(int row, int col)
	{
		if (col >= Spaces.length)
		{
			col = 0;
			row++;
		}
		
		if (row >= Spaces.length)
		{
			return true;
		}

		else
		{
			if(buttontoint(Spaces[row][col]) != 0)
			{
				return solve(row, col + 1);
			}
			else
			{
				for(int i = 0; i < 9; i++)
				{
					if(checker(row, col, i + 1))
					{
						Spaces[row][col].setText(Integer.toString(i + 1));
					
						if(solve(row, col + 1))
						{
							return true;
						}
						else
						{
							Spaces[row][col].setText("");
						}
					}
				}
				
				return false;
			}
		}
		
		
	}
}
