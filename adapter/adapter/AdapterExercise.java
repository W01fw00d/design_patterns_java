package adapter;

class Square
{
  public int side;

  public Square(int side)
  {
    this.side = side;
  }
}

interface Rectangle
{
  int getWidth();
  int getHeight();

  default int getArea()
  {
    return getWidth() * getHeight();
  }
}

class SquareToRectangleAdapter implements Rectangle
{
	private int side;
	
  public SquareToRectangleAdapter(Square square)
  {
    this.side = square.side;
  }

	@Override
	public int getWidth() {
		return this.side;
	}
	
	@Override
	public int getHeight() {
		return this.side;
	}
}
