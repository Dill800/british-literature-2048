package britlitpackage;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BritLit2048 extends Application {

	/*
	 * 
	 * Beowulf powers of 2
	 * 2: Beowulf & Grendel
	 * 4: Beowulf bringing arm back
	 * 8: Beowulf and Dragon
	 * 16: Mead Hall
	 * 32: Burnt Script
	 * 64: Beowulf & Grendel's Mother
	 * 128: Grendel's Mothers Head
	 * 256: Hrothgar
	 * 512: Beowulf & Dragon
	 * 1024: Grendel
	 * 2048: Beowulf's Death
	 * 
	 * 
	 * 
	 * Gawain:
	 * 2: Gawain & Green Knight
	 * 4: Green Knight
	 * 8: Morgan Le Fay
	 * 16: King Arthur
	 * 32: Lady Bertilak
	 * 64: Green Girdle
	 * 128: Gawain's Sheild
	 * 256: Green Knight Intro
	 * 512: Bertilak's Castle
	 * 1024: Lady Bertilak and Gawain
	 * 2048: Gawain about to be beheaded
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Chaucer:
	 * 2: Chaucer
	 * 4: Sergeant
	 * 8: Clerk
	 * 16: Prioress
	 * 32: Franklin
	 * 64: Wife of Bath
	 * 128: Parson
	 * 256: Knight
	 * 512: Miller
	 * 1024: Yeoman
	 * 2048: Pardoner
	 * 
	 */
	
	Media media;
	MediaPlayer mp;
	
	Stage stage = null;
	
	private final int width = 600;
	private final int height = 750;
	
	Tile[][] board = new Tile[4][4];
	
	public static String selection;
	
	Glow glow;
	Glow nullGlow;
	
	private boolean gameWon = false;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		if(mp == null) {
			media = new Media(getClass().getResource("/resources/music.mp3").toURI().toString());
			mp = new MediaPlayer(media);
			mp.setVolume(.35);
			mp.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					mp.seek(Duration.ZERO);
				}
				
			});
			mp.play();
		}
		
		glow = new Glow();
		glow.setLevel(.5);
		nullGlow = new Glow();
		nullGlow.setLevel(0);
		
		stage.setTitle("Brit Lit 2048");
		this.stage = stage;
		GridPane rootPane = new GridPane();
		rootPane.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		RowConstraints r1 = new RowConstraints();
		r1.setPrefHeight(height - width);
		RowConstraints r2 = new RowConstraints();
		r2.setPrefHeight(width);
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(100);
		rootPane.getColumnConstraints().add(c1);
		rootPane.getRowConstraints().addAll(r1, r2);
		
		Image title = new Image(getClass().getResource("/resources/britlit2048.png").toURI().toString());
		ImageView titleView = new ImageView(title);
		GridPane.setHalignment(titleView,  HPos.CENTER);
		GridPane.setValignment(titleView,  VPos.CENTER);
		
		Image bim = new Image(getClass().getResource("/resources/beowulf.png").toURI().toString());
		ImageView biv = new ImageView(bim);
		Button beowulf = new Button(null, biv);
		beowulf.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		beowulf.setOnAction(e -> {
			selection = "beowulf";
			toGameScene();
		});
		beowulf.setOnMouseEntered(e -> {
			beowulf.setEffect(glow);
		});
		beowulf.setOnMouseExited(e -> {
			beowulf.setEffect(nullGlow);
		});
		Image cim = new Image(getClass().getResource("/resources/chaucer.png").toURI().toString());
		ImageView civ = new ImageView(cim);
		Button chaucer = new Button(null, civ);
		chaucer.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		chaucer.setOnAction(e -> {
			selection = "chaucer";
			toGameScene();
		});
		chaucer.setOnMouseEntered(e -> {
			chaucer.setEffect(glow);
		});
		chaucer.setOnMouseExited(e -> {
			chaucer.setEffect(nullGlow);
		});
		Image gim = new Image(getClass().getResource("/resources/gawain.png").toURI().toString());
		ImageView giv = new ImageView(gim);
		Button sirGawain = new Button(null, giv);
		sirGawain.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		sirGawain.setOnAction(e -> {
			selection = "gawain";
			toGameScene();
		});
		sirGawain.setOnMouseEntered(e -> {
			sirGawain.setEffect(glow);
		});
		sirGawain.setOnMouseExited(e -> {
			sirGawain.setEffect(nullGlow);
		});
		
		VBox choices = new VBox(30);
		choices.setAlignment(Pos.CENTER);
		choices.getChildren().addAll(beowulf, chaucer, sirGawain);
		
		rootPane.add(titleView, 0, 0);
		rootPane.add(choices,  0,  1);
		
		Scene scene = new Scene(rootPane, width, height);
		stage.setScene(scene);
		stage.show();
	}
	
	public void toGameScene() {
		
		Scene scene = stage.getScene();
		
		GridPane rootPane = (GridPane)scene.getRoot();
		
		rootPane.getChildren().clear();
		
		Button restart = new Button("Restart");
		restart.setFont(new Font("Times New Roman", 25));
		GridPane.setHalignment(restart,  HPos.CENTER);
		
		restart.setOnAction(e -> {
			try {
				start(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		GridPane board = new GridPane();
		board.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		board.setPrefWidth(width);
		board.setPrefHeight(width);
		GridPane.setHalignment(board,  HPos.CENTER);
		GridPane.setValignment(board,  VPos.CENTER);
		
		RowConstraints r = new RowConstraints();
		r.setPercentHeight(25);
		ColumnConstraints c = new ColumnConstraints();
		c.setPercentWidth(25);
		board.getRowConstraints().addAll(r, r, r, r);
		board.getColumnConstraints().addAll(c, c, c, c);
		
		// Initialize Board
		initBoard();
		// Update Board
		updateBoard(board);
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			
			switch(key.getCode()) {
			case UP:
				System.out.println("Up Clicked");
				movement("up");
				break;
			case DOWN:
				System.out.println("Down Clicked");
				movement("down");
				break;
			case LEFT:
				System.out.println("Left Clicked");
				movement("left");
				break;
			case RIGHT:
				System.out.println("Right Clicked");
				movement("right");
				break;
			default:
				System.out.println("Wrong Key Pressed, do nothing");
				break;
			}
			
			updateBoard(board);
		});
		
		rootPane.add(restart,  0,  0);
		rootPane.add(board,  0,  1);
		
	}
	
	public void toGameOverScene() throws Exception {
		
		GridPane rootPane = new GridPane();
		rootPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		RowConstraints r1 = new RowConstraints();
		r1.setPercentHeight(50);
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(100);
		rootPane.getColumnConstraints().addAll(c1);
		rootPane.getRowConstraints().addAll(r1, r1);
		
		Image im = new Image(getClass().getResource("/resources/gameOver.png").toURI().toString());
		ImageView iv = new ImageView(im);
		GridPane.setHalignment(iv,  HPos.CENTER);
		GridPane.setValignment(iv,  VPos.CENTER);
		rootPane.add(iv,  0,  0);
		
		Image r = new Image(getClass().getResource("/resources/retry.png").toURI().toString());
		ImageView ri = new ImageView(r);
		Button retry = new Button(null, ri);
		GridPane.setHalignment(retry,  HPos.CENTER);
		GridPane.setValignment(retry,  VPos.CENTER);
		retry.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		retry.setOnAction(e -> {
			try {
				start(this.stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		retry.setOnMouseEntered(e -> {
			retry.setEffect(glow);
		});
		retry.setOnMouseExited(e -> {
			retry.setEffect(nullGlow);
		});
		
		
		FadeTransition f = new FadeTransition(Duration.seconds(3), iv);
		f.setFromValue(0);
		f.setToValue(1);
		f.setCycleCount(1);
		f.play();
		f.setOnFinished(e -> {
			rootPane.add(retry,  0,  1);
		});
		
		Scene scene = new Scene(rootPane, width, height);
		stage.setScene(scene);
		
	}
	
	public void movement(String direction) {
		
		// Before movement 2D array
		Tile[][] oldBoard = new Tile[board.length][board.length];
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[r].length; c++) {
				oldBoard[r][c] = new Tile(this.board[r][c].getPower());
				if(isEmpty(r, c)) {
					oldBoard[r][c] = new Tile();
				}
			}
		}
		
		
		switch(direction) {
		case "right":
			
			//boolean skipRight = false;
			String skipIndexRight = "";
			// Blank In Between Exception
			for(int r = 0; r < board.length; r++) {
				if(sameTile(r, 0, r, 3) && !isEmpty(r, 0) && isEmpty(r, 1) && isEmpty(r, 2)) {
					board[r][3].setPower(board[r][0].getPower() + 1);
					board[r][0] = new Tile();
				}
				if(sameTile(r, 1, r, 3) && !isEmpty(r, 1) && isEmpty(r, 2)) {
					board[r][3].setPower(board[r][1].getPower() + 1);
					board[r][1] = new Tile();
				}
				if(sameTile(r, 0, r, 2) && !isEmpty(r, 0) && isEmpty(r, 1) && !sameTile(r, 0, r, 3)) {
					board[r][2].setPower(board[r][0].getPower() + 1);
					board[r][0] = new Tile();
					skipIndexRight += r;
				}
			}
			
			// Combining Linearly Right
			for(int r = 0; r < board.length && skipIndexRight.indexOf(r + "") == -1; r++) {
				for(int c = board[r].length-1; c > 0; c--) {
					if(!isEmpty(r, c) && !isEmpty(r, c-1)) {
						if(sameTile(r, c, r, c-1)) {
							board[r][c].setPower(board[r][c-1].getPower() + 1);
							board[r][c-1] = new Tile();
						}
					}
				}
			}
			
			//Shifting Linearly Right
			for(int r = 0; r < board.length; r++) {
				for(int c = board[r].length-1; c > 0; c--) {
					if(isEmpty(r, c) && !isEmpty(r, c-1)) {
						board[r][c].setPower(board[r][c-1].getPower());
						board[r][c-1] = new Tile();
						c = board[r].length;
					}
				}
			}
			//skipRight = false;
			break;
			
		case "left":
			
			// Blank In Between Exception
			for(int r = 0; r < board.length; r++) {
				if(sameTile(r, 0, r, 3) && !isEmpty(r, 0) && isEmpty(r, 1) && isEmpty(r, 2)) {
					board[r][3].setPower(board[r][0].getPower() + 1);
					board[r][0] = new Tile();
				}
				if(sameTile(r, 1, r, 3) && !isEmpty(r, 1) && isEmpty(r, 2) && !sameTile(r, 1, r, 0)) {
					board[r][3].setPower(board[r][1].getPower() + 1);
					board[r][1] = new Tile();
				}
				if(sameTile(r, 0, r, 2) && !isEmpty(r, 0) && isEmpty(r, 1)) {
					board[r][2].setPower(board[r][0].getPower() + 1);
					board[r][0] = new Tile();
				}
			}
			
			// Combining Linearly Left
			for(int r = 0; r < board.length; r++) {
				for(int c = 0; c < board[r].length - 1; c++) {
					if(!isEmpty(r, c) && !isEmpty(r, c+1)) {
						if(sameTile(r, c, r, c+1)) {
							board[r][c].setPower(board[r][c+1].getPower() + 1);
							board[r][c+1] = new Tile();
						}
					}
				}
			}
			
			//Shifting Linearly Left
			for(int r = 0; r < board.length; r++) {
				for(int c = 0; c < board[r].length-1; c++) {
					if(isEmpty(r, c) && !isEmpty(r, c+1)) {
						board[r][c].setPower(board[r][c+1].getPower());
						board[r][c+1] = new Tile();
						c = -1;
					}
				}
			}
			break;
			
		case "up":
			
			//boolean skipUp = false;
			String skipIndexUp = "";

			for(int c = 0; c < board.length; c++) {
				if(sameTile(0, c, 3, c) && !isEmpty(0, c) && isEmpty(1, c) && isEmpty(2, c)) {
					board[0][c].setPower(board[3][c].getPower() + 1);
					board[3][c] = new Tile();
				}
				if(sameTile(1, c, 3, c) && !isEmpty(1, c) && isEmpty(2, c) && !sameTile(1, c, 0, c)) {
					board[1][c].setPower(board[3][c].getPower() + 1);
					board[3][c] = new Tile();
					skipIndexUp += c;
				}
				if(sameTile(0, c, 2, c) && !isEmpty(0, c) && isEmpty(1, c)) {
					board[0][c].setPower(board[2][c].getPower() + 1);
					board[2][c] = new Tile();
				}
			}
			
			// Combine Linearly Upward (error prone)
			//for(int c = 0; c < board.length && !skipUp; c++) {
			for(int c = 0; c < board.length && skipIndexUp.indexOf(c + "") == -1; c++) {
				for(int r = 0; r < board[r].length - 1; r++) {
					if(!isEmpty(r, c) && !isEmpty(r+1, c)) {
						if(sameTile(r, c, r+1, c)) {
							board[r][c].setPower(board[r+1][c].getPower() + 1);
							board[r+1][c] = new Tile();
						}
					}
				}
			}
			
			// Shift Linearly Left
			for(int c = 0; c < board.length; c++) {
				for(int r = 0; r < board[r].length - 1; r++) {
					if(isEmpty(r, c) && !isEmpty(r+1, c)) {
						board[r][c].setPower(board[r+1][c].getPower());
						board[r+1][c] = new Tile();
						r = -1;
					}
				}
			}
			break;
			
		case "down":
			
			// Blank In Between Exception
			for(int c = 0; c < board.length; c++) {
				if(sameTile(0, c, 3, c) && !isEmpty(0, c) && isEmpty(1, c) && isEmpty(2, c)) {
					board[0][c].setPower(board[3][c].getPower() + 1);
					board[3][c] = new Tile();
				}
				if(sameTile(1, c, 3, c) && !isEmpty(1, c) && isEmpty(2, c)) {
					board[1][c].setPower(board[3][c].getPower() + 1);
					board[3][c] = new Tile();
				}
				if(sameTile(0, c, 2, c) && !isEmpty(0, c) && isEmpty(1, c) && !sameTile(0, c, 3, c)) {
					board[0][c].setPower(board[2][c].getPower() + 1);
					board[2][c] = new Tile();
				}
			}
			
			// Combine Linearly Downward (error prone)
			for(int c = 0; c < board.length; c++) {
				for(int r = board[c].length-1; r > 0; r--) {
					if(!isEmpty(r, c) && !isEmpty(r-1, c)) {
						if(sameTile(r, c, r-1, c)) {
							board[r][c].setPower(board[r-1][c].getPower() + 1);
							board[r-1][c] = new Tile();
						}
					}
				}
			}
			
			// Combine Linearly Downward
			for(int c = 0; c < board.length; c++) {
				for(int r = board[c].length-1; r > 0; r--) {
					if(isEmpty(r, c) && !isEmpty(r-1, c)) {
						board[r][c].setPower(board[r-1][c].getPower());
						board[r-1][c] = new Tile();
						r = board[c].length;
					}
				}
			}
			
		
		}
		
		if(!boardFull()) {
			if(!sameBoard(oldBoard, this.board)) {
				randomTile();
			}
			
		}
		
	}
	
	public void win() throws Exception {
		
		GridPane rootPane = new GridPane();
		rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		RowConstraints r1 = new RowConstraints();
		r1.setPercentHeight(50);
		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(100);
		rootPane.getColumnConstraints().addAll(c1);
		rootPane.getRowConstraints().addAll(r1, r1);
		
		Image im = new Image(getClass().getResource("/resources/win.png").toURI().toString());
		ImageView iv = new ImageView(im);
		GridPane.setHalignment(iv,  HPos.CENTER);
		GridPane.setValignment(iv,  VPos.CENTER);
		rootPane.add(iv,  0,  0);
		
		Image r = new Image(getClass().getResource("/resources/retry.png").toURI().toString());
		ImageView ri = new ImageView(r);
		Button retry = new Button(null, ri);
		GridPane.setHalignment(retry,  HPos.CENTER);
		GridPane.setValignment(retry,  VPos.CENTER);
		retry.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		retry.setOnAction(e -> {
			try {
				start(this.stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		retry.setOnMouseEntered(e -> {
			retry.setEffect(glow);
		});
		retry.setOnMouseExited(e -> {
			retry.setEffect(nullGlow);
		});
		
		
		FadeTransition f = new FadeTransition(Duration.seconds(3), iv);
		f.setFromValue(0);
		f.setToValue(1);
		f.setCycleCount(1);
		f.play();
		f.setOnFinished(e -> {
			rootPane.add(retry,  0,  1);
		});
		
		Scene scene = new Scene(rootPane, width, height);
		stage.setScene(scene);
		
	}
	
	public void updateBoard(GridPane board) {
		board.getChildren().clear();
		
		for(int i = 0; i < this.board.length; i++) {
			for(int j = 0; j < this.board.length; j++) {
				try {
					board.add(this.board[i][j].getImage(), j, i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		if(gameWon() && gameWon == false) {
			try {
				win();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gameWon = true;
		}
		
		if(gameOver()) {
			
			try {
				toGameOverScene();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void initBoard() {
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = new Tile();
			}
		}
		
		// New random tile with value of 2 or 4
		board[(int)(Math.random() * 4)][(int)(Math.random() * 4)] = new Tile((int)Math.pow(2,  (int)(Math.random() + 1)));
	}
	
	public boolean isEmpty(int row, int col) {
		if(board[row][col].getValue() == 0) {
			return true;
		}
		return false;
	}
	
	public boolean sameTile(int row1, int col1, int row2, int col2) {
		if(board[row1][col1].compareTo(board[row2][col2]) == 1) {
			return true;
		}
		return false;
	}
	
	public void randomTile() {
		int row = (int)(Math.random() * 4);
		int col = (int)(Math.random() * 4);
		int num = (int)(Math.random() * 2) + 1;
		boolean cont = true;
		while(cont) {
			if(isEmpty(row, col)) {
				board[row][col] = new Tile(num);
				cont = false;
			}
			else {
				row = (int)(Math.random() * 4);
				col = (int)(Math.random() * 4);
			}
		}
	}
	
	public boolean boardFull() {
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[r].length; c++) {
				if(isEmpty(r, c)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean gameOver() {
		
		boolean gameOver = true;
		
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[r].length-1; c++) {
				if(sameTile(r, c, r, c+1)) {
					gameOver = false;
				}
			}
		}
		
		for(int c = 0; c < board.length; c++) {
			for(int r = 0; r < board.length-1; r++) {
				if(sameTile(r, c, r+1, c)) {
					gameOver = false;;
				}
			}
		}
		
		
		
		return (gameOver && boardFull());
	}
	
	
	public boolean sameBoard(Tile[][] board1, Tile[][] board2) {

		for(int r = 0; r < board1.length; r++) {
			for(int c = 0; c < board1.length; c++) {
				if(board1[r][c].getValue() != board2[r][c].getValue()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean gameWon() {
			
			for(Tile[] t : this.board) {
				for(Tile e : t) {
					if(e.getValue() == 2048) {
						return true;
					}
				}
			}
			return false;
		}
	
	public static void main(String[] args) {
		launch(args);

	}

}
