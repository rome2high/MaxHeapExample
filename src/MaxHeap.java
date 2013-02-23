import java.util.*;

public class MaxHeap {
 protected  BTNode  root;
 protected  int  size; 
 private Queue wkqueue;//used to keep track of last node      
 
 public MaxHeap(){
  root = null; 
  size = 0;
  wkqueue = new Queue();
 }

 public int size() {return  size;}

 public boolean isEmpty() {
  return (root == null);        
 }
  
 public boolean isRoot(BTNode v) {
  return  (v == root());
 } 

 public BTNode root(){
  if(!isEmpty())
   return  root;
  else return null;
 }
  
 public boolean hasParent(BTNode v) {
     if(v.getParent() == null)
      return false;
     else return true;
 }
      
 public BTNode parent(BTNode v){
  if(hasParent(v))
   return v.getParent();
  else return null;
 }

 public boolean isLeaf(BTNode v) {
  return (!hasLeft(v) && !hasRight(v));
 }
  
 public boolean hasLeft(BTNode v) {
     return  (v.getLeft() != null);
 }
     
 public BTNode left(BTNode v){
  if(hasLeft(v))
   return v.getLeft();
  else return null;
 }

 public boolean hasRight(BTNode v) {
     return  (v.getRight() != null);
 }
     
 public BTNode right(BTNode v){
  if(hasRight(v))
   return v.getRight();
  else return null;
 }
       
 public boolean insert(int e) {
  BTNode n = new BTNode(e);
  BTNode current, parent;
     if(isEmpty()) {
      root = n; 
      wkqueue.enque(n); 
     }
     else if(size == 1){
      BTNode tmpn = (BTNode)wkqueue.peek();
      tmpn.setLeft(n);
   n.setParent(tmpn);
      wkqueue.enque(n);
      if(tmpn.element() < n.element()){
       swapValue(tmpn, n);
      }
     }
     else if(size == 2){
      BTNode tmpn = (BTNode)wkqueue.deque();
      tmpn.setRight(n);
      n.setParent(tmpn);
      wkqueue.enque(n);
      if(tmpn.element() < n.element()){
       swapValue(tmpn, n);
      }
     }
     else{
      BTNode tmpn = (BTNode)wkqueue.peek();
      if(tmpn.getLeft() == null){
       tmpn.setLeft(n);
       n.setParent(tmpn);
       wkqueue.enque(n);
      }
      else{
       tmpn = (BTNode)wkqueue.deque();
       tmpn.setRight(n);
       n.setParent(tmpn);
       wkqueue.enque(n);
      }
      //do a heapup restoration
      if(n.element() > tmpn.element()){
       swapValue(n, tmpn);
       do {
           current = tmpn;
           parent = tmpn.getParent();
        if(current.element() > parent.element()){	//infinite loop
         swapValue(current, parent);
        }
       }while(parent != root);
      }
     }
  size = size + 1;
     return true;
 }
   
 public void swapValue(BTNode c, BTNode p){
  int tmp = c.element();
  c.setElement(p.element());
  p.setElement(tmp);
 }

 public BTNode deleteRoot() { 
  //To be finished
	 if(isEmpty()) {
		   return null;
		  }
		  else if(size == 1){//one node
		   BTNode n = new BTNode(root().element());
		   root = null;
		   size = 0;
		   return n;
		  }
		  else{
			  if(size <= 3){
				  wkqueue.numOfNodes = wkqueue.rear -1;	//bypass size flag in dequerear()
			  }
		   BTNode n = new BTNode(root().element());
		   BTNode tmpNode = (BTNode)wkqueue.dequerear();
		   root().setElement(tmpNode.element());
		   BTNode parent = tmpNode.getParent();
		   if(parent.getLeft() == tmpNode){
		    parent.setLeft(null);
		   }
		   else {
		    parent.setRight(null);
		   }
		       BTNode current = root();
		       //do a heapdown restoration
		       while(current != null) {
		        BTNode lChild = current.getLeft();
		        BTNode rChild = current.getRight();
		        if(lChild != null &&  rChild != null){
		        	if(current.element() >= lChild.element() &&
        				current.element() >= rChild.element()){
		        		break;
		        	}
		        	else if(lChild.element() >= rChild.element()){
		        		if(current.element() <= lChild.element()){
		        			swapValue(current, lChild);
		        			current = lChild;
		        		}
		        	}
					else if(rChild.element() >= lChild.element()){
						if(current.element() <= rChild.element()){
							swapValue(current, rChild);
							current = rChild;
						}
					}
		        }
		        else if(lChild == null && rChild != null){
		        	if(current.element() <= rChild.element()){
		        		swapValue(current, rChild);
		        		current = rChild;
		        	}
		        }
		        else if(lChild != null && rChild == null){
		        	if(current.element() <= lChild.element()){
		        		swapValue(current, lChild); 
		        		current = lChild;
		        	}
		        	else {
		        		break;
		        	}
		        }
		        else if(lChild == null && rChild == null){
		        	current = null;
		        }
		       } //while
	       size = size - 1;
	       return n;
	  }
	}
}
