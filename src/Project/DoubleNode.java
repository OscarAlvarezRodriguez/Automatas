package Project;

//Primary Key will be String (q0,q1,q2)
public class DoubleNode<String> {

	private String key;
	private DoubleNode<String> Child;
	private DoubleNode<String> Father;

	
	public DoubleNode(String key) {
		this.key = key;
		this.Child = null;
		this.Father = null;
	}

	public DoubleNode(String key, DoubleNode<String> next ,DoubleNode<String> prev) {
		this.key = key;
		this.Child = next;
		this.Father = prev;
	}

	public void setNext(DoubleNode<String> next) {
		this.Child = next;
	}
	
	public void setPrev(DoubleNode<String> prev) {
		this.Father = prev;
	}

	public String getKey() {
		return this.key;
	}

	public DoubleNode<String> getNext() {
		return this.Child;
	}

	public DoubleNode<String> getPrev() {
		return this.Father;
	}
	
}
