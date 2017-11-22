/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.url.problema3;

/**
 *
 * @author ROG
 */
public abstract class ImplLeakyStack<E> implements LeakyStack<E>{

    private static class Node<E>{
		private E element;
		private Node<E> next;
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
		}
		public E getElement() {
			return element;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
	}
 private int Size;
 private Node<E> tail = null;
 private int count =0;
   ImplLeakyStack( int size)
   {
       Size = size;//size of the stack
    // animal to be inserted
   }
    
    @Override
    public int size() {
        return Size-count; // the total of space left
    }

    @Override
    public boolean isEmpty() {
       return Size == 0;
    }

    @Override
    public E saveHistory(E e) {
        if(Size ==  count) {
            undo();
            count--;
            saveHistory(e);        
        }
        else if ( count ==1)
        {
            tail = new Node<>(e, null);
			tail.setNext(tail);
                        count++;
                        
            
        }
        else  {
			Node<E> newest = new Node<>(e, tail.getNext());
			tail.setNext(newest);
                        count++;
                       
		}
		return e ;
    }

    @Override
    public E actual() {
        
       return tail.getElement();
       
    }

    @Override
    public E undo() {
        if(isEmpty()) throw new IllegalArgumentException("Stack Vacio");
		Node<E> head = tail.getNext();
		if(head == tail) tail = null;
		else tail.setNext(head.getNext());
		count--;
		return head.getElement();
    }
    
}
