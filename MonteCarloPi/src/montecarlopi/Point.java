package montecarlopi;

public class Point {
	private double x,y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double x(){
		return x;
	}
	
	public double y(){
		return y;
	}
	
	public String toString(){
		return String.format("(%f,%f)", x, y);
	}
}
