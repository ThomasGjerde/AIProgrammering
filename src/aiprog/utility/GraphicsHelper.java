package aiprog.utility;

import java.awt.Color;
import java.util.Random;

public class GraphicsHelper {
	public static Color getRandomColor(){
		Random random = new Random();
		Color retColor = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
		return retColor;
	}
}
