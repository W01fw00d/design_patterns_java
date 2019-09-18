package prototype_pattern;

class Point
{
  public int x, y;
  
  public Point(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
}

class Line
{
  public Point start, end;
  
  public Line(Point start, Point end)
  {
    this.start = start;
    this.end = end;
  }

  public Line deepCopy()
  {
    return new Line(
    		new Point(this.start.x, this.start.y),
    		new Point(this.end.x, this.end.y)
    );
  }
}

class DeepCopyExerciseDemo
{
	public static void main(String[] args) throws Exception
	{
		Line line1 = new Line(
				new Point(1, 2),
				new Point(1, 2)
		);
		
		Line line2 = line1.deepCopy();
		line2.start.x = 2;
		
		System.out.println(line1.start.x);
		System.out.println(line2.start.x);
	}
}
