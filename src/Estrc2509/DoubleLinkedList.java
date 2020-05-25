package Estrc2509;

import java.util.Random;

public class DoubleLinkedList<T> {

	DoubleNode<T> head = null;
	DoubleNode<T> tail = null;
	
	public DoubleLinkedList() {
		this.head = null;
		this.tail = null;
	}
	
	public void PushFront(T key) {
		DoubleNode<T> node2 = new DoubleNode<>(key);
		
		if(this.head == null) {
			this.tail = node2;
			this.head = tail;
		}
		else {
			node2.setNext(this.head);
			node2.setPrev(null);
			this.head = node2;
		this.head.getNext().setPrev(this.head);
		}
		if(this.tail == null) {
			this.tail = this.head;
		}
		
	}
	
	public  void PushBack(T key) {
		DoubleNode<T> node2 = new DoubleNode<>(key);
		node2.setNext(null);
		node2.setPrev(null);
		
		if(this.head == null) {
			this.tail = node2;
			this.head = tail;
		}
		else {
			this.tail.setNext(node2);
			node2.setPrev(this.tail);
			this.tail = node2;
		}
	}
	
	
	public void PoPBack() {
		if(this.head ==null) {
			System.out.println("Error!! Empty List");
			return;
		}
		if(this.head == this.tail){
			this.tail = null;
			this.head = tail;
		}
		else {
//			
			this.tail.getPrev().setNext(null);
			this.tail = this.tail.getPrev();
		}
	}
	
	
	public DoubleNode<T> FindByKey(T key){
		DoubleNode<T> dn = null;
		DoubleNode<T> p = this.head;
		
		while(p!=null) {
			if(p.getKey() == key) {
				dn = p;
				break;
			}
			
			p = p.getNext();
		}
		return dn;
	}

	public void Pop(T key) {
		if(this.head == null) {
			return;
		}
		
		if(this.head == this.tail) {
			this.head = this.tail = null;
		}
		else if (head.getKey() == key) {
			head = head.getNext();
		}
		else {
			DoubleNode<T> before = head;
			DoubleNode<T> p = head.getNext();
			
			while(p!=tail && p.getKey() !=key){
				before = before.getNext();
//				
			}
			
			if(p!=null) {
				before.setNext(p.getNext());
				
				if(p == tail) {
					tail = before;
				}
			}
		}
		
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void DisplayList() {
		if(this.head == null) {
			System.out.println("Lista Vacia...");
		}
		
		DoubleNode<T> p = this.head;
		while (p!=null) {
			System.out.println(p.getKey());
			p = p.getNext();
		}
	}
	
	public void AddAfter(DoubleNode<T> node, T key) {
		DoubleNode<T> node2 = new DoubleNode<T>(key);
		
		node2.setNext(node.getNext());
		node2.setPrev(node.getPrev());
		node.setPrev(node2);
	
		if(node2.getNext() != null) {
			node2.getNext().setPrev(node2);
		}
		
		if(this.tail == node) {
			this.tail = node2;
		}
		
	}
	
	public void AddBefore(DoubleNode<T> node, T key) {
		DoubleNode<T> node2 = new DoubleNode<T>(key);
		
		node2.setNext(node);
		node2.setPrev(node.getPrev());
		node.setPrev(node2);
		
		if(node2.getPrev() != null) {
			node2.getPrev().setNext(node2);
		}
		
		if(this.head == node) {
			this.head = node2;
		}
		
	}
	
	public void find(T k) {
		if(this.head == this.tail) {
			System.out.println("Lista Vacia...");
			return;
		}
		DoubleNode<T>p=this.head;
		while(p.getNext() != null) {
			if(p.getKey() == k) {
				System.out.println("Encontrado!");
				return;
			}
			p = p.getNext();
		}
		System.out.println("No encontrado.");
		return;
	}
	
}
