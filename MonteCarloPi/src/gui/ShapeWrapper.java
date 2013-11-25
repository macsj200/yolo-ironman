package gui;

import java.awt.Color;
import java.awt.Shape;

public class ShapeWrapper{
	public static final Color defaultColor = Color.black;
	
	private Shape shape = null;
	private Color color = null;
	
	public ShapeWrapper(Shape shape, Color color){
		this.setShape(shape);
		this.setColor(color);
	}
	
	public ShapeWrapper(Shape shape){
		this.setShape(shape);
		this.setColor(defaultColor);
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
