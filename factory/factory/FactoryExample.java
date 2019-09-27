package factory;

class Point
{
	private double x, y;
	
	private Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static class Factory
	{
		public static Point newCartesianPoint(double x, double y)
		{
			return new Point(x, y);
		}

		public static Point newPolarPoint(double rho, double theta)
		{
			return new Point(
					rho*Math.cos(theta), 
					rho*Math.sin(theta)
			);
		}
	}
}



class Demo
{
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Point point = Point.Factory.newPolarPoint(2, 3);
		
		// System.out.println("Hello world");
	}
}