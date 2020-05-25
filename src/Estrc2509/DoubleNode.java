package Estrc2509;

public class DoubleNode<T> {

	private T key;
	private DoubleNode<T> next;
	private DoubleNode<T> prev;

	
	public DoubleNode(T key) {
		this.key = key;
		this.next = null;
		this.prev = null;
	}

	public DoubleNode(T key, DoubleNode<T> next ,DoubleNode<T> prev) {
		this.key = key;
		this.next = next;
		this.prev = prev;
	}

	public void setNext(DoubleNode<T> next) {
		this.next = next;
	}
	
	public void setPrev(DoubleNode<T> prev) {
		this.prev = prev;
	}

	public T getKey() {
		return this.key;
	}

	public DoubleNode<T> getNext() {
		return this.next;
	}

	public DoubleNode<T> getPrev() {
		return this.prev;
	}
	
}
