package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CanvasPanel extends JPanel{
	private ShapeWrapper shapeWrapper = null;
	//Currently loaded ShapeWrapper object
	
	private BufferedCanvas bufferedCanvas = null;
	//Use a buffered image to render stuff
	
	private static final int defaultWidth = 500;
	private static final int defaultHeight = 500;
	
	private int width;
	private int height;

	CanvasPanel(){
		this(defaultWidth, defaultHeight);
	}
	
	CanvasPanel(int width, int height){
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));
		
		bufferedCanvas = new BufferedCanvas(width, 
				height, BufferedImage.TYPE_INT_ARGB);
		//Make a new BufferedImage for to render stuff
	}

	public void loadShapeWrapper(ShapeWrapper shapeWrapper){
		//Load in a new shapeWrapper
		setShapeWrapper(shapeWrapper);
		bufferedCanvas.addShapeWrapper(shapeWrapper);
	}
	
	private void setShapeWrapper(ShapeWrapper shapeWrapper){
		this.shapeWrapper = shapeWrapper;
	}
	
	public void rerender(){
		//Redraw everything
		
		bufferedCanvas.threadedRender();
		revalidate();
		repaint();
	}
	
	public void resetElements(){
		//Clear the canvas of all elements
		//Empties the ArrayList
		
		bufferedCanvas.resetElements();
		revalidate();
		repaint();
	}

	public ShapeWrapper getShapeWrapper(){
		//Get the current shapeWrapper
		return shapeWrapper;
	}

	public void paintComponent(Graphics g){
		//Override paintComponent() method to paint BufferedCanvas
		
		super.paintComponent(g);
		//Let the normal paintComponent() method do its thing
		
		Graphics2D g2d = (Graphics2D) g.create();
		//Make a new context
		
		g2d.drawImage(bufferedCanvas, 0, 0, null);
		//Write the image
		
		g2d.dispose();
		//We're all done with that
	}
	
	public BufferedCanvas getBufferedCanvas(){
		//Get a reference to the BufferedCanvas object behind this panel
		
		return bufferedCanvas;
	}
}