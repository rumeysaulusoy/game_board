package application;

import java.awt.Point;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainClassOfGameBoard extends Application {
	
	

    // This enumeration keeps the different tile types.
	public enum TILETYPE {
		StarterVertical, StarterHorizontal, EndVertical, EndHorizontal, EmptyFree, Empty, PipeVerticalStatic,
		PipeHorizontalStatic, PipeVertical, PipeHorizontal, CurvedPipe00, CurvedPipe01, CurvedPipe10, CurvedPipe11, 
		CurvedPipeStatic
	}
	

	// This array keeps level 1 tile types.
	TILETYPE[][] level1 = new TILETYPE[][] {
		new TILETYPE[] { TILETYPE.StarterVertical, TILETYPE.Empty, TILETYPE.Empty, TILETYPE.Empty },
		new TILETYPE[] { TILETYPE.PipeVertical, TILETYPE.Empty, TILETYPE.Empty, TILETYPE.Empty },
		new TILETYPE[] { TILETYPE.PipeVertical, TILETYPE.PipeHorizontal, TILETYPE.Empty, TILETYPE.Empty },
		new TILETYPE[] { TILETYPE.CurvedPipe01, TILETYPE.EmptyFree, TILETYPE.PipeHorizontalStatic, TILETYPE.EndHorizontal }, };
		
	// This array keeps level 2 tile types.
	TILETYPE[][] level2 = new TILETYPE[][] { 
		new TILETYPE[] {TILETYPE.StarterVertical, TILETYPE.Empty, TILETYPE.Empty, TILETYPE.Empty},
		new TILETYPE[] {TILETYPE.EmptyFree, TILETYPE.PipeVertical,TILETYPE.EmptyFree, TILETYPE.EmptyFree},
		new TILETYPE[] {TILETYPE.PipeVertical, TILETYPE.Empty, TILETYPE.PipeHorizontal, TILETYPE.Empty},
		new TILETYPE[] {TILETYPE.CurvedPipe01, TILETYPE.EmptyFree, TILETYPE.PipeHorizontalStatic, TILETYPE.EndHorizontal}
	};
	
	// This array keeps level 3 tile types.
    TILETYPE[][] level3 = new TILETYPE[][] {
		new TILETYPE[] {TILETYPE.StarterVertical, TILETYPE.Empty, TILETYPE.Empty, TILETYPE.Empty},
		new TILETYPE[] {TILETYPE.CurvedPipe01, TILETYPE.PipeVertical, TILETYPE.Empty, TILETYPE.EmptyFree},
		new TILETYPE[] {TILETYPE.PipeVertical, TILETYPE.EmptyFree, TILETYPE.EmptyFree, TILETYPE.EmptyFree},
		new TILETYPE[] {TILETYPE.PipeHorizontal, TILETYPE.Empty, TILETYPE.PipeHorizontalStatic, TILETYPE.EndHorizontal}	
	};
	
	// This array keeps level 4 tile types.
	TILETYPE[][] level4 = new TILETYPE[][] {
		new TILETYPE[] { TILETYPE.StarterVertical, TILETYPE.Empty, TILETYPE.Empty, TILETYPE.Empty },
		new TILETYPE[] { TILETYPE.PipeVerticalStatic, TILETYPE.EmptyFree, TILETYPE.EmptyFree, TILETYPE.EndVertical },
		new TILETYPE[] { TILETYPE.EmptyFree, TILETYPE.PipeHorizontal, TILETYPE.PipeHorizontal, TILETYPE.Empty},
		new TILETYPE[] { TILETYPE.CurvedPipe01, TILETYPE.Empty, TILETYPE.EmptyFree, TILETYPE.CurvedPipe00}
	};
	
	// This array keeps level 5 tile types.
	TILETYPE[][] level5 = new TILETYPE[][] { 
		new TILETYPE[] {TILETYPE.StarterVertical, TILETYPE.Empty, TILETYPE.Empty, TILETYPE.Empty},
		new TILETYPE[] {TILETYPE.EmptyFree,TILETYPE.PipeHorizontal, TILETYPE.EmptyFree, TILETYPE.EndVertical},
		new TILETYPE[] {TILETYPE.CurvedPipeStatic, TILETYPE.PipeVertical, TILETYPE.PipeHorizontal,TILETYPE.Empty},
		new TILETYPE[] {TILETYPE.EmptyFree,TILETYPE.EmptyFree,TILETYPE.EmptyFree,TILETYPE.CurvedPipe00}
	};
    
	private int levelStatus = 0; // This data field indicates level status.
	private boolean hasWon;  // This data field indicates whether level is won or not.
	private int numberOfMoves = 0; // This data field indicates number of moves.
	
	Circle ball = new Circle(50, 50, 13); // This circle creates ball.
	GridPane gpane = new GridPane(); // Creates grid pane.
 
	Label label = new Label( (levelStatus + 1 ) +"");
	Label label3 = new Label (numberOfMoves + "");
	
	
	@Override
	public void start(Stage primaryStage) {

		try {
			
			System.out.println("-------------LEVEL 1------------");			
			GameBoard board = new GameBoard(gpane, level1); // Creates level 1 game board.
			
			// Sets colors of ball goldenrod.
			ball.setFill(Color.GOLDENROD);
			ball.setStroke(Color.GOLDENROD);
			
			
			// Create a border pane.
			BorderPane pane = new BorderPane();
			
            pane.setTop(gpane); // Sets grid pane to top of pane.
            pane.getChildren().add(ball); // Adds ball to pane.
           

            //Create label to show levels and the number of moves. 
            Label label1 = new Label(". Level | "); 
            Label label2 = new Label(" Number of Moves: ");
            
            // Create HBox and adds label to HBox.
            HBox hbox = new HBox();
            hbox.getChildren().addAll(label, label1, label2, label3);
            
            //Set HBox to bottom of pane.
            pane.setBottom(hbox);
            
			Scene scene = new Scene(pane, 400, 415); // Creates scene which height and width are 400.
			primaryStage.setTitle("Game"); // Sets title to stage.
			primaryStage.setScene(scene); // Sets scene to stage.
			primaryStage.show(); // Calls method to show stage.
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	


		 //This method switch level.
	    public void switchLevel() {	    	
		GameBoard board;
		
		if(levelStatus == 1 ) {
			
        if(hasWon) { // If level status is 1 and player has won the level 1, create level 2 game board.
       	    System.out.println("");
        	System.out.println("-------------LEVEL 2------------");	
        	hasWon = false; // Sets false to the hasWon.
        	 board = new GameBoard(gpane, level2); // Creates level 2 game board.
        	 label.textProperty().bind(new SimpleIntegerProperty(levelStatus+1).asString());
            ball.setTranslateX(0); // Sets coordinate x of ball 0 to place to starter tile.
            ball.setTranslateY(0); // Sets coordinate y of ball 0 to place to starter tile.
       
        }
        }
		
		if(levelStatus == 2 ) { // If level status is 2 and player has won the level 2, creates level 3 game board.
       	 if(hasWon) { 
       		 System.out.println("");
       		 System.out.println("-------------LEVEL 3------------");
    		 hasWon = false; // Sets false to the hasWon.
    		 board = new GameBoard(gpane, level3); // Creates level 3 game board.
    		 label.textProperty().bind(new SimpleIntegerProperty(levelStatus+1).asString());
    		 ball.setTranslateX(0); // Sets coordinate x of ball 0 to place to starter tile.
             ball.setTranslateY(0); // Sets coordinate y of ball 0 to place to starter tile.
          
    	 }
		}
		
		if(levelStatus == 3 ) { // If level status is 3 and player has won the level 3, creates level 4 game board.
			
   		 if(hasWon) {
   			 System.out.println("");
   			 System.out.println("-------------LEVEL 4------------");	
			 hasWon = false; // Sets false to the hasWon.
			  board = new GameBoard(gpane, level4); // Creates level 4 game board.
			  label.textProperty().bind(new SimpleIntegerProperty(levelStatus+1).asString());
			  ball.setTranslateX(0); // Sets coordinate x of ball 0 to place to starter tile.
	          ball.setTranslateY(0); // Sets coordinate y of ball 0 to place to starter tile.
	        
		 }
		}
		if(levelStatus == 4) { // If level status is 4 and player has won the level 4, creates level 5 game board.
   		 if(hasWon) { 
   			 System.out.println("");
   			System.out.println("-------------LEVEL 5------------");	
			 hasWon = false; // Sets false to the hasWon.
    		 board = new GameBoard(gpane, level5); // Creates level 5 game board.
    		 label.textProperty().bind(new SimpleIntegerProperty(levelStatus+1).asString());
    		 ball.setTranslateX(0); // Sets coordinate x of ball 0 to place to starter tile.
             ball.setTranslateY(0); // Sets coordinate y of ball 0 to place to starter tile.
        
   		 }

		}
		if(levelStatus == 5) { // If level status is 5 and player has won the level 5, prints message.
   		 if(hasWon) { 

			 System.out.println("CONGRATS YOU WON.");
   			 System.out.println("-----------GAME OVER----------");
   			 
   			 // Creates text to notify game over.
   			 Text text = new Text(); 
   			 String text1 = "GAME OVER" ;
   		     text.setText(text1); 
   		     text.setTranslateX(75); // Sets coordinate x of text.
   	         text.setTranslateY(150); // Sets coordinate y of text.
   	         text.setStyle("-fx-font: 40 arial;"); // Sets font and size of text.
   	         text.setFill(Color.WHITE); // Sets color white to text.
   	         gpane.getChildren().add(text); // Adds text to pane.
   	         
   	         
		 }
		}
		
		
	}
	
	// This method animate the ball.
	public void animateBall() {
	      Path path = new Path(); // Creates path for ball.

	      //Moving to the starting point. 
	      MoveTo moveTo = new MoveTo(50, 50);    
	      
	      if(levelStatus == 0|| levelStatus == 1 || levelStatus == 2) { // If level 1 or 2 or 3, creates the path which the ball goes.
	    	 
	    	  LineTo line1 = new LineTo(50, 350); // Creating 1st line.        
	           LineTo line2 = new LineTo(350 ,350);  // Creating 2nd line.
	          
	      
	           path.getElements().add(moveTo); // Adds moveTo to path.
	           path.getElements().addAll(line1, line2); // Adds line1 and line2 to path.
	           
	      }
	      else if (levelStatus == 3 || levelStatus == 4 ) {  // If level 4 or 5, creates the path which the ball goes.
	    	 
	    	  LineTo line1 = new LineTo(50, 250); // Creating 1st line.        
	          LineTo line2 = new LineTo(350, 250);  // Creating 2nd line.
	          LineTo line3 = new LineTo(350, 150); // Creating 3rd line.
	           
	          path.getElements().add(moveTo);  // Adds moveTo to path.
	          path.getElements().addAll(line1, line2, line3);   // Adds line1,line2 and line3 to path.
	          
	      }
	      // Create the transition.
	      PathTransition pathTransition = new PathTransition(); 
	      
	      // Setting the duration of the transition. 
	      pathTransition.setDuration(Duration.millis(5000));       
	      
	      // Setting the node for the transition. 
	      pathTransition.setNode(ball); 
	      
	      // Setting the path for the transition. 
	      pathTransition.setPath(path); 
	      
	      // Setting the orientation of the path. 
	      pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
	      pathTransition.setCycleCount(1); 
	      
	      // Setting auto reverse value to true. 
	      pathTransition.setAutoReverse(false); 
	      
	      // Playing the animation. 
	      pathTransition.play(); 
	    

         
	}
   
	
	public static void main(String[] args) {	
		launch(args);		
	}
	
    // This class create game board.
	public class GameBoard {

		private Tile[][] board; // A two dimensional array of tiles. 
		private Pane pane; // A pane class to add them. 
		private AvailableMove moves; // This data field keeps moves of tile.
		
		// This method creates game board.
		public GameBoard(Pane pane, TILETYPE[][] tileTypes) { 
			this.pane = pane; // This data fields keeps pane.
			this.board = drawLevel(tileTypes); // Draw the level according to enum tileTypes.	            
		}
		
		// A method for drawing the level, takes the parameter TILETYPE.
		private Tile[][] drawLevel(TILETYPE[][] tileTypes) {
			
			//Creates a two dimensional array of tiles to make the board: 
			Tile[][] board = new Tile[tileTypes.length][tileTypes[0].length]; 
			
			// Creates tile using array.
			for (int y = 0; y < tileTypes.length; y++) {
				for (int x = 0; x < tileTypes[y].length; x++) {
					Tile tile = new Tile(y*4+x, tileTypes[y][x]);
					tile.properties(); // Calls properties method to place tile.
					pane.getChildren().addAll(tile); // Adds tile to the pane.

					tile.setOnMousePressed(e -> handleMousePressed(tile, e)); // If mouse is pressed, calls the method.
					tile.setOnMouseDragged(e -> handleMouseDragged(tile, e)); // If mouse is dragged, calls the method.
					tile.setOnMouseReleased(e -> {	handleMouseReleased(tile, e); // If mouse is released, calls the method.
						});
					board[y][x] = tile; // Place tile to array.
				}
			}
			return board; // Returns game board.
		}

		// This inner class creates object that shows whether tile has move or not.
		public class AvailableMove {
			private int Up; // This data field keeps moving up.
			private int Down; // This data field keeps moving down.
			private int Left; // This data field keeps moving left.
			private int Right; // This data field keeps moving right.
			
			public boolean haveMove() { // This keeps whether tile has move or not.
				return this.getDown() + this.getLeft() + this.getUp() + this.getRight() > 0; // Returns sum of moves is greater than 0.
			}
			public boolean haveMoveXAxis() { // This keeps whether tile has move in x axis or not.
				return this.getLeft() + this.getRight() > 0; // Returns addition of move in the x axis is greater than 0.
			}
			public boolean haveMoveYAxis() {  // This keeps whether tile has move in y axis or not.
				return this.getUp() + this.getDown() > 0; // Returns addition of move in the y axis is greater than 0.
			}
			
			public int getUp() { // Returns moving up.
				return Up;
			}
			public void setUp(int up) {  // Sets moving up.
				Up = up;
			}
			public int getDown() {  // Returns moving down.
				return Down;
			}
			public void setDown(int down) {  // Sets moving down.
				Down = down;
			}
			public int getLeft() { // Returns moving left.
				return Left;
			}
			public void setLeft(int left) {  // Sets moving left.
				Left = left;
			}
			public int getRight() { // Returns moving right.
				return Right;
			}
			public void setRight(int right) {  // Sets moving right.
				Right = right;
			}
			
		
		}


		
		
		// If mouse is pressed, this method runs.
		private void handleMousePressed(Tile tile , MouseEvent e) { 
			
			moves = calculateAvailableMove(tile);  // Calculates available move a tile can make. 
			tile.toFront(); // Brings the tile to front when pressed.
			if (tile.isMovementControl()) {	 // If tile can move, computes start drag coordinates.           
				
				tile.getxValue(); // Gets x coordinate of tile.
				tile.getyValue(); // Gets y coordinate of tile.

				tile.startDragX = e.getSceneX() - tile.getTranslateX(); // Computes x coordinate of start point of moving tile.
				tile.startDragY = e.getSceneY() - tile.getTranslateY(); // Computes y coordinate of start point of moving tile.

			}
		}
		// If mouse is dragged, this method run.
		private void handleMouseDragged(Tile tile, MouseEvent e) {
			// System.out.println("Mouse Dragged");

			if (moves.haveMove() && tile.isMovementControl()) { // If tile can move and has move, computes start drag coordinates.  
				
				double diffX = e.getSceneX() - tile.startDragX; // The new position of tile. 
				double controlX = diffX - tile.getxValue(); // This shows how much the tile changed position. 

				double diffY = e.getSceneY() - tile.startDragY; // The new position of tile. 
				double controlY = diffY - tile.getyValue(); // This shows how much the tile changed position. 

				/*If the tile has empty adjacent tiles at x direction and change of position is more in x direction
				it can go horizontal path. */
				if (moves.haveMoveXAxis() && Math.abs(controlX) >= Math.abs(controlY) ){ 

					// If tile has adjacent empty tile towards right,it can go that way.
					/* But it cannot move past the empty tile. So we use controlX to check this. */
					if (moves.getRight() > 0 && controlX <= 100 && controlX >= 0) {
						tile.setTranslateX(diffX);
						tile.setTranslateY(tile.getyValue());
					}
					
					// If tile has adjacent empty tile towards left,it can go that way.
					/* But it cannot move past the empty tile. So we use controlX to check this. */
					if(moves.getLeft() > 0 && controlX <= 0 && controlX >= -100 ) {
						tile.setTranslateX(diffX); 
						tile.setTranslateY(tile.getyValue());
					}
				}

	             // If the tile has empty adjacent tiles at y direction and change of position is more in y direction
				 //	it can go vertical path. 
				if (moves.haveMoveYAxis() && Math.abs(controlY) >= Math.abs(controlX)) {
					
					// If tile has adjacent empty tile downwards,it can go that way.
					/* But it cannot move past the empty tile. So we use controlY to check this. */
					if(moves.getDown() > 0 && controlY <=  100 && controlY >= 0) {
						tile.setTranslateY(diffY);
						tile.setTranslateX(tile.getxValue());

					}
					
					// If tile has adjacent empty tile upwards,it can go that way.
					/* But it cannot move past the empty tile. So we use controlY to check this. */
					if(moves.getUp() > 0 && controlY <= 0 && controlY >= -100  ) { 
						tile.setTranslateY(diffY); 
						tile.setTranslateX(tile.getxValue());
					}

				}	
				
			}
		}

		// If mouse is released, this method run.
		private void handleMouseReleased(Tile tile, MouseEvent e){
		    
			double moveX = tile.getTranslateX() - tile.getxValue(); // Gets the change of positions in x direction (by subtracting the original x value from the new changing x value)
			double moveY = tile.getTranslateY() - tile.getyValue(); // Gets the change of position in y direction. 
			
			// Used tileDiff values to identify where the empty tile is depending on the change of positions. 
			//(if the tile went 200 pixels to left then the empty tile is -2 to the left.)
			
			// If the change of position is positive then the empty tile is towards the right. if it is negative it is toward the left.
			int tileDiffX = moveX > 0 ? (int)Math.ceil(moveX / 100) : (int)Math.floor(moveX / 100);
			// If the change of position is positive then the empty tile is downward. If it is negative it is upwards. 
			int tileDiffY = moveY > 0 ? (int)Math.ceil(moveY / 100) : (int)Math.floor(moveY / 100);
			
			// If the change in position is smaller than 50, go back to original position.
			if (Math.abs(moveX) < 50)
			{	
				tile.setTranslateX(tile.getxValue());
			}
			// If the change in position is bigger than 50, swap the tiles. 
			else {
				Point tilePosition = getTilePosition(tile.id);
				// By using tileDiff values we calculate the position of empty tile. 
				Tile emptyTile = board[tilePosition.y + tileDiffY][tilePosition.x + tileDiffX];

				// Swap the coordinates. 
				tile.setTranslateX(emptyTile.getxValue());
				emptyTile.setTranslateX(tile.getxValue());
				
				// Change their original tile values(swap operation).
				double xval = tile.getxValue();
				tile.setxValue(emptyTile.getxValue());
				emptyTile.setxValue(xval);
				
				// Change the board. 
				board[tilePosition.y][tilePosition.x] = emptyTile;
				board[tilePosition.y + tileDiffY][tilePosition.x + tileDiffX] = tile;
				
				//Every time a tile moves, control if the level is complete.
				controlLevelCompleted();
			}
			// If the change in position in y direction is smaller than 50, go back to original position.
			if (Math.abs(moveY) < 50) {
				tile.setTranslateY(tile.getyValue());
			}
			// If the change in position is bigger than 50, swap the tiles. 
			else {
				Point tilePosition = getTilePosition(tile.id);
				Tile emptyTile = board[tilePosition.y + tileDiffY][tilePosition.x + tileDiffX];

				// Swap the coordinates. 
				tile.setTranslateY(emptyTile.getyValue());
				emptyTile.setTranslateY(tile.getyValue());
				
				// Change their original tile values(swap operation).
				double yval = tile.getyValue();
				tile.setyValue(emptyTile.getyValue());
				emptyTile.setyValue(yval);
				
				// Change the board. 
				board[tilePosition.y][tilePosition.x] = emptyTile;
				board[tilePosition.y + tileDiffY][tilePosition.x + tileDiffX] = tile;
				
				//Every time a tile moves, control if the level is complete.
				controlLevelCompleted();
			}
			
			
			
		}
		// Calculates available move for the tile. Create a AvailableMove Object. 
		// Get the tile's position using it's id. 
		// Get the empty tile positions of GameBoard. Store them in an array.
		// For checking the right side checks if the tile next to it is emptyFree.
		// (Checks if the point next to tile is in the list of empty tiles.)
		// If it is increment availableMove.direction value by 1.
		// If its not empty break the loop, because there's no need for checking the others. 
		public AvailableMove calculateAvailableMove(Tile tile) {
		
			AvailableMove moves = new AvailableMove();
			
			Point tilePosition = getTilePosition(tile.id);
			ArrayList<Point> emptyTilePositions = getEmptyTilePositions();
		
			
			// Check towards the right side of tile.
			for(int i = tilePosition.x + 1; i < 4; i++) {
				if (isInList(new Point(i, tilePosition.y), emptyTilePositions))
				{
					moves.setRight(moves.getRight() + 1); 
				}
				else {
					break;
				}
			}

			// Check towards the left side of tile.
			for(int i = tilePosition.x - 1; i >= 0; i--) {
				if (isInList(new Point(i, tilePosition.y), emptyTilePositions))
				{
					moves.setLeft(moves.getLeft() + 1); 
				}
				else {
					break;
				}
			}
			
			// Check downwards the left side of tile. 
			for(int i = tilePosition.y + 1; i < 4; i++) {
				if (isInList(new Point(tilePosition.x, i), emptyTilePositions))
				{
					moves.setDown(moves.getDown() + 1); 
				}
				else {
					break;
				}
			}
			// Check upwards the left side of tile. 
			for(int i = tilePosition.y - 1; i >= 0; i--) {
				if (isInList(new Point(tilePosition.x, i), emptyTilePositions))
				{
					moves.setUp(moves.getUp() + 1); 
				}
				else {
					break;
				}
			}
			return moves;
		}
		// This method checks whether a certain point is in a certain ArrayList.
		public boolean isInList(Point point, ArrayList<Point> points) {
			for (int i = 0; i < points.size(); i++) {
				if (point.x == points.get(i).x && point.y == points.get(i).y)
					return true;
			}
			return false;
		}
		
		// Returns the position of a tile according to their tile id.
		public Point getTilePosition(int id) {
			for (int y = 0; y < board.length; y++ )
			{
				for (int x = 0; x < board[y].length; x++) {
					if (board[y][x].id == id)
						return new Point(x, y);
				}
			}
			return null;
		}
		// This method returns the positions of the empty tiles that are in the board.
		public ArrayList<Point> getEmptyTilePositions() {
			ArrayList<Point> emptyTilePos = new ArrayList<Point>();
			for (int y = 0; y < board.length; y++ )
			{
				for (int x = 0; x < board[y].length; x++) {
					if (board[y][x].getType() == TILETYPE.EmptyFree)
						 emptyTilePos.add(new Point(x, y));
				}
			}
			return emptyTilePos;
		}
		// This method returns a list of empty tiles. 
		public ArrayList<Tile> getEmptyTiles() {
			ArrayList<Tile> emptyTiles = new ArrayList<Tile>();
			for (int y = 0; y < board.length; y++ )
			{
				for (int x = 0; x < board[y].length; x++) {
					if (board[y][x].getType() == TILETYPE.EmptyFree)
						emptyTiles.add(board[y][x]);
				}
			}
			return emptyTiles;
		}
		
		

		
		// This method controls level and switch level if previous level is passed.
		public void controlLevelCompleted()  {
	
			// If level is 1, 2 or 3 this checks if the level is completed or not.
			if(levelStatus == 1 || levelStatus == 2 || levelStatus == 0) {
				// If tiles' places is suitable for level passing sequence, switch level.
				if(board[0][0].getType() == TILETYPE.StarterVertical && board[1][0].getType() == TILETYPE.PipeVertical &&
				   board[2][0].getType() == TILETYPE.PipeVertical    && board[3][0].getType() == TILETYPE.CurvedPipe01 &&
				   board[3][1].getType() == TILETYPE.PipeHorizontal  && board[3][2].getType() == TILETYPE.PipeHorizontalStatic &&
				   board[3][3].getType() == TILETYPE.EndHorizontal) {
					 
				      hasWon = true; // Sets true value to hasWon.
				      animateBall(); // Calls animate ball method.			      
				      numberOfMoves++; // Increases number of moves.
					  label3.textProperty().bind(new SimpleIntegerProperty(numberOfMoves).asString());
				       
					  System.out.println("CONGRATULATIONS, YOU PASSED LEVEL " + (levelStatus +1) + "!"); // Prints this message and level status.				      
				      levelStatus++; // Increases the level.		
				      numberOfMoves = 0; // Resets number of move.
				      
				      
				      // This method makes the gameboard wait for 5 seconds when ball move.
				      Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev ->  {
				    	 switchLevel();  // Calls switch level method.
				    	 label3.textProperty().bind(new SimpleIntegerProperty(numberOfMoves).asString());
				         
				      }));
				      timeline.play(); // Plays timeline.
				      
				     
				      
			     }
				else {
					numberOfMoves++; // Increase number of moves.
					label3.textProperty().bind(new SimpleIntegerProperty(numberOfMoves).asString());
				    hasWon = false; // Sets false value to hasWon.
				}
			}
			// If level is 4 this checks if the level is completed or not.
			else if(levelStatus == 3 ) {
				if(board[0][0].getType() == TILETYPE.StarterVertical && board[1][0].getType() == TILETYPE.PipeVerticalStatic &&
				   board[2][0].getType() == TILETYPE.CurvedPipe01   && board[2][1].getType() == TILETYPE.PipeHorizontal &&
				   board[2][2].getType() == TILETYPE.PipeHorizontal  && board[2][3].getType() == TILETYPE.CurvedPipe00 &&
				   board[1][3].getType() == TILETYPE.EndVertical) {
					   
					hasWon = true; // Sets true value to hasWon.
					animateBall(); // Call animate ball method.
					numberOfMoves++; // Increase number of moves.
				     label3.textProperty().bind(new SimpleIntegerProperty(numberOfMoves).asString());
					  System.out.println("CONGRATULATIONS, YOU PASSED LEVEL " + (levelStatus +1) + "!"); // Prints this message and level status.
				     levelStatus++;	// Increase the level.	 
                     numberOfMoves = 0; // Reset number of move.
                     // This method make the gameboard wait for 5 seconds when ball move.
				     Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev ->  {
				    	 switchLevel();   // Calls switch level method.
				      }));
				      timeline.play(); // Play timeline.
				      
				}
				else {
					hasWon = false; // Sets false value to hasWon.
					numberOfMoves++; // Increase number of moves.
					label3.textProperty().bind(new SimpleIntegerProperty(numberOfMoves).asString());
				}
			}
			// If level is 5 this checks if the level is completed or not.
			else if(levelStatus == 4 ) {
				if(board[0][0].getType() == TILETYPE.StarterVertical && board[1][0].getType() == TILETYPE.PipeVertical &&
				   board[2][0].getType() == TILETYPE.CurvedPipeStatic && board[2][1].getType() == TILETYPE.PipeHorizontal &&
				   board[2][2].getType() == TILETYPE.PipeHorizontal && board[2][3].getType() == TILETYPE.CurvedPipe00 &&
				   board[1][3].getType() == TILETYPE.EndVertical) {
					   
					 hasWon = true; // Sets true value to hasWon.
					 animateBall(); // Call animate ball method.
					 numberOfMoves++; // Increase number of moves.
					 label3.textProperty().bind(new SimpleIntegerProperty(numberOfMoves).asString());
				     levelStatus++;	// Increase the level.			
			    	  // This method make the gameboard wait for 5 seconds when ball move.
				     Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev ->  {
				    	 switchLevel();    // Calls switch level method.
				      }));
				      timeline.play(); // Play timeline.
				      
				}
				else {
					hasWon = false; // Sets false value to hasWon.
					numberOfMoves++; // Increase number of moves.
					label3.textProperty().bind(new SimpleIntegerProperty(numberOfMoves).asString());
		
					
				}
			}
			
			
		}
	
		


	}
	
	

	public class Tile extends Pane { // This class creates tiles.
		private int id; // This data field indicates id of tile.
		private boolean movementControl;  // This data field indicates whether tile can move or not.
		private ImageView image;  // This data field keeps image of tile.
		private double xValue; // This data field indicates x coordinate of tile.
		private double yValue; // This data field indicates y coordinate of tile.				
        private double startDragX; 
		private double startDragY;
		private TILETYPE type; // This data field indicates tile type.

		Tile(int id, TILETYPE type) { // This constructor includes the id and type of the device.
			this.id = id; // Keeps id property.
			this.type = type; // Keeps type property.
			this.properties();
			System.out.println("Tile Created | id: " + (this.id +1)+ " move: " +  isMovementControl()); // This prints id of tile.
		}


        // This method creates image of tile and place it.
		public void properties() { 

			// If type is starter, creates image of starter tile and place it.
			if (this.getType() == TILETYPE.StarterVertical || this.getType() == TILETYPE.StarterHorizontal) {
				setMovementControl(false); // Sets false to movement property.
				Image image = new Image("starter.png"); // Creates image of tile.
				ImageView starter = new ImageView(image); // Sets image to image view.
				setImage(starter); // Sets image view to image method.

				// If tile is horizontal, rotates tile.
				if (this.getType() == TILETYPE.StarterHorizontal) {
					starter.setRotate(90); // Rotates 90 degrees image of tile.
					setImage(starter); // Sets image view to image method.
				}
			}

			// If type is end, creates image of end tile and place it.
			else if (this.getType() == TILETYPE.EndVertical || this.getType() == TILETYPE.EndHorizontal) {
				setMovementControl(false); // Sets false to movement property.
				Image image = new Image("end.png"); // Creates image of tile.
				ImageView end = new ImageView(image); // Sets image to image view.
				setImage(end);  // Sets image view to image method.

				// If tile is horizontal, rotates tile.
				if (this.getType() == TILETYPE.EndHorizontal) {
					end.setRotate(90); // Rotates 90 degrees image of tile.
					setImage(end); // Sets image view to image method.
				}
			}

			// If type is emptyfree, creates image of empty free tile and place it.
			else if (this.getType() == TILETYPE.EmptyFree) {

				setMovementControl(true); // Sets true to movement property.
				Image image = new Image("emptyfree.png"); // Creates image of tile.
				ImageView emptyfree = new ImageView(image); // Sets image to image view.
				setImage(emptyfree);  // Sets image view to image method.

			}
			
			// If type is empty, creates image of empty tile and place it.
			else if (this.getType() == TILETYPE.Empty) {
				setMovementControl(true); // Sets true to movement property.
				Image image = new Image("empty.png"); // Creates image of tile.
				ImageView empty = new ImageView(image); // Sets image to image view.
				setImage(empty);  // Sets image view to image method.
			}
 
			// If type is pipestatic, creates image of pipe static tile and place it.
			else if (this.getType() == TILETYPE.PipeVerticalStatic
					|| this.getType() == TILETYPE.PipeHorizontalStatic) {
				setMovementControl(false); // Sets false to movement property.
				Image image = new Image("pipestatic.png"); // Creates image of tile.
				ImageView pipestatic = new ImageView(image); // Sets image to image view.
				setImage(pipestatic);  // Sets image view to image method.

				// If tile is horizontal, rotates tile.
				if (this.getType() == TILETYPE.PipeHorizontalStatic) {
					pipestatic.setRotate(90); // Rotates 90 degrees image of tile.
				}
			}

			// If type is pipe, creates image of pipe tile and place it.
			else if (this.getType() == TILETYPE.PipeVertical || this.getType() == TILETYPE.PipeHorizontal) {

				setMovementControl(true); // Sets true to movement property.
				Image image = new Image("pipe.png"); // Creates image of tile.
				ImageView pipe = new ImageView(image); // Sets image to image view.
				setImage(pipe);  // Sets image view to image method.

				// If tile is horizontal, rotates tile.
				if (this.getType() == TILETYPE.PipeHorizontal) {
					pipe.setRotate(90); // Rotates 90 degrees image of tile.
				}
			}

			// If type is curvedpipe, creates image of curved pipe tile and place it.
			else if (this.getType() == TILETYPE.CurvedPipe00 || 
					this.getType() == TILETYPE.CurvedPipe01 ||
					this.getType() == TILETYPE.CurvedPipe10 ||
					this.getType() == TILETYPE.CurvedPipe11 ) {

				setMovementControl(true); // Sets true to movement property.
				Image image = new Image("curvedpipe.png"); // Creates image of tile.
				ImageView curvedpipe = new ImageView(image); // Sets image to image view.
				setImage(curvedpipe);  // Sets image view to image method.

			
				if (this.getType() == TILETYPE.CurvedPipe11) { //  If the property value of a tile is equal to 11, then it rotates 90 degrees image of tile.
					curvedpipe.setRotate(90); // Rotates 90 degrees image of tile.
				} else if (this.getType() == TILETYPE.CurvedPipe10) {  // If the property value of a tile is equal to 10, then it rotates 180 degrees image of tile.
					curvedpipe.setRotate(180); // Rotates 180 degrees image of tile.
				} else if (this.getType() == TILETYPE.CurvedPipe00) { // If the property value of a tile is equal to 00, then it rotates 270 degrees image of tile.
					curvedpipe.setRotate(270); // Rotates 270 degrees image of tile.
				}
				
			}
			
			// If type is curvedpipestatic, creates image of curved static pipe  static tile and place it.
			else if(this.getType() == TILETYPE.CurvedPipeStatic) {
					Image image = new Image("curvedpipestatic.png"); // Creates image of tile.
					ImageView curvedPipeStatic = new ImageView(image); // Sets image to image view.
					setImage(curvedPipeStatic);	  // Sets image view to image method.
			}

			ImageView image = this.getImage(); 
			image.setFitHeight(100); // Sets 100 to image height.
			image.setFitWidth(100); // Sets 100 to image width.
			this.setHeight(100); 
			this.setWidth(100);
			Point point = positionOfImage(this); // Calls position of image method to place tile.
			this.setTranslateX(point.getX());  // Sets x coordinate of tile.
			this.setTranslateY(point.getY());  // Sets y coordinate of tile.

			setxValue(point.getX()); // Sets x coordinate of tile to x value data field.
			setyValue(point.getY());  // Sets y coordinate of tile to y value data field.

			this.getChildren().add(this.getImage()); // This adds image to pane.
		}

		public Point positionOfImage(Tile gameboard) { // This method places every tile according to the id.

			int x, y;

			x = (int) (gameboard.id % 4) * 100; // This keeps coordinate x of image of tile.
			y = (gameboard.id / 4) * 100; // This keeps coordinate y of image of tile.
			Point point = new Point(x, y); // This create point using coordinates for tile.
			return point; // Return point.

		}

		public ImageView getImage() { // This method return image of tile.
			return image;
		}

		public void setImage(ImageView image) { // This method set image of tile.
			this.image = image;

		}

		public boolean isMovementControl() { // This method returns movement control that controls whether tile can move or not.
			return movementControl;
		}

		public void setMovementControl(boolean movementControl) { //  This method set movement control that controls whether tile can move or not.
			this.movementControl = movementControl;
		}

		public TILETYPE getType() { // This method return type of tile.
			return type;
		}

		public void setType(TILETYPE type) { // This method set type of tile.
			this.type = type;
		}


		public void setId(int id) { // This method set id of tile.
			this.id = id;
		}

		public double getxValue() { // This method return x value of tile.
			return xValue;
		}

		public void setxValue(double xValue) { // This method set x value of tile.
			this.xValue = xValue;
		}

		public double getyValue() { // This method return y value of tile.
			return yValue;
		}

		public void setyValue(double yValue) { // This method set y value of tile.
			this.yValue = yValue;
		}
		

	}

	
}
