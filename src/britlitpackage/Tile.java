package britlitpackage;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Tile implements Comparable<Tile> {

	private int value;
	private int power;
	
	public Tile() {
		this.value = 0;
		this.power = 0;
	}
	
	public Tile(int power) {
		this.value = (int)Math.pow(2, power);
		this.power = power;
	}
	
	public int getValue() {
		return this.value;
	}

	@Override
	public int compareTo(Tile tile) {
		if(this.getValue() == tile.getValue()) {
			return 1;
		}
		return 0;
	}
	
	public int getPower() {
		return this.power;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setPower(int power) {
		this.power = power;
		setValue((int)(Math.pow(2,  power)));
	}
	
//	public ImageView getImage() throws Exception {
//		Image image = new Image(getClass().getResource("/resources/" + BritLit2048.selection + this.value + ".jpg").toURI().toString());
//		ImageView imageView = new ImageView(image);
//		
//		
//		
//		return imageView;
//	}
	
	public StackPane getImage() throws Exception {
		
		if(this.value == 0) {
			return new StackPane();
		}
		
		Image image = new Image(getClass().getResource("/resources/" + BritLit2048.selection + this.value + ".jpg").toURI().toString());
		//Image image = new Image(getClass().getResource("/resources/chaucer2.jpg").toURI().toString());
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(147);
		GridPane.setHalignment(imageView, HPos.CENTER);
		GridPane.setValignment(imageView,  VPos.CENTER);
		
		Label l = new Label(this.value + "");
		//l.setOpacity(.6);
		if(this.value == 0) {
			l.setText(null);
		}
		l.setFont(new Font("Arial Black", 50));
		l.setTextFill(Color.rgb(value % 250, value % 250, value % 250));
		
		switch(power) {
		case 1:
			l.setTextFill(Color.DARKGREEN);
			break;
		case 2:
			l.setTextFill(Color.RED);
			break;
		case 3:
			l.setTextFill(Color.BLUE);
			break;
		case 4:
			l.setTextFill(Color.ORANGE);
			break;
		case 5:
			l.setTextFill(Color.BROWN);
			break;
		case 6:
			l.setTextFill(Color.YELLOW);
			break;
		case 7:
			l.setTextFill(Color.PURPLE);
			break;
		case 8:
			l.setTextFill(Color.DARKGOLDENROD);
			break;
		case 9:
			l.setTextFill(Color.LIGHTSKYBLUE);
			break;
		case 10:
			l.setTextFill(Color.ORCHID);
			break;
		case 11:
			l.setTextFill(Color.STEELBLUE);
			break;
		
		}
		
		GridPane.setHalignment(l, HPos.CENTER);
		GridPane.setValignment(l,  VPos.CENTER);
		
		StackPane p = new StackPane();
		p.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		p.getChildren().addAll(imageView, l);
		
		return p;
	}
	
	
	
}
