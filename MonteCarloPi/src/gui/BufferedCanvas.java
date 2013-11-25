package gui;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class BufferedCanvas extends BufferedImage{
	//BufferedCanvas

	private ArrayList<ShapeWrapper> elements = null;
	//ArrayList of ShapeWrapper objects
	//Holds everything on the screen

	Graphics2D g2 = null;
	//Graphics2D object used for drawing primitives
	//This is the primary object, don't use directly for drawing
	//Instead clone and dispose of clone
	
	private Color background = null;
	//Background of canvas

	public BufferedCanvas(int width, int height, int imageType) {
		//Primary constructor
		//IDK what imageType is, it's required for BufferedImage
		
		super(width, height, imageType);
		//Call BufferedImage constructor
		
		elements = new ArrayList<ShapeWrapper>();
		//Create the ArrayList to hold ShapeWrapper objects

		background = Color.white;
		//Set background to white by default
		//Retrieved to set color of eraser
		
		g2 = createGraphics();
		//Create a Graphics2D object for the BufferedImage
		
		g2.setBackground(background);
		//Set the background of the BufferedImage
		
		threadedRender();
		//Do initial rendering work
	}

	public void threadedRender(){
		//Wrapper, just invokes singleThreadRender with invokeLater
		
		SwingUtilities.invokeLater(new Runnable(){
			//Necessary framework for Swing concurrency
			public void run(){
				singleThreadRender();
				//Primary rendering logic
			}
		});
	}
	
	public Color getBackground(){
		//Get the background color
		//Used for eraser color
		
		return background;
	}
	
	private void singleThreadRender(){
		Graphics2D g2d = (Graphics2D) g2.create();
		//Make a new context so we can dispose when finished

		if(elements.size() == 0){
			//If elements is empty clear the canvas
			g2d.clearRect(0, 0, getWidth(), getHeight());
		}

		for(int i = 0; i < elements.size(); i = i + 1){
			//Iterate through the ArrayList and paint every element
			//GOT to be a better (more efficient) way to do this

			g2d.setColor(elements.get(i).getColor());
			//Set the current brush color

			g2d.fill(elements.get(i).getShape());
			//Fill a Shape object
		}
		g2d.dispose();
		//Close the graphics object
	}

	public void addShapeWrapper(ShapeWrapper shapeWrapper){
		//Add a shapeWrapper to the ArrayList
		elements.add(shapeWrapper);
	}

	public void resetElements() {
		//Empties the elements ArrayList thereby clearing the screen
		//Called when clear button is pressed

		elements.clear();
		//Possibly inefficient...

		threadedRender();
	}
}
