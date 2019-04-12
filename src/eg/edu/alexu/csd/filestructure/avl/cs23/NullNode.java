package eg.edu.alexu.csd.filestructure.avl.cs23;

public class NullNode<T> extends Node {
	private final int height = 0;

	@Override
	public int getHeight(){
		return this.height;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "NullNode";
	}
	
	
}
