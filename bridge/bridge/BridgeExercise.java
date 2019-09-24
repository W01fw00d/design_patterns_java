package bridge;

interface Shape
{
	public String toString();
}

interface Renderer
{
	public String whatToRenderAs();
}

class VectorRenderer implements Renderer
{
	@Override
	public String whatToRenderAs() {
		return "lines";
	}
}

class RasterRenderer implements Renderer
{
	@Override
	public String whatToRenderAs() {
		return "pixels";
	}
}

class Triangle implements Shape
{
	private Renderer renderer;
	
	public Triangle(Renderer renderer)
	{
		this.renderer = renderer;
	}

	@Override
	public String toString() {
		return "Drawing Triangle as " + renderer.whatToRenderAs();
	}
}

class Square implements Shape
{
	private Renderer renderer;
	
	public Square(Renderer renderer)
	{
		this.renderer = renderer;
	}

	@Override
	public String toString() {
		return "Drawing Square as " + renderer.whatToRenderAs();
	}
}


class BridgeExerciseDemo
{
	public static void main(String[] args) throws Exception
	{
		System.out.println(
			new Triangle(new VectorRenderer()).toString()
		);
		System.out.println(
			new Square(new RasterRenderer()).toString()
		); 
	}
}
