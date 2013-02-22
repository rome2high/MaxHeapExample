public class BTNode {
 private  int element;
 private int x; //introduced Particularly for graphic drawing
 private int y; //introduced Particularly for graphic drawing
    private BTNode left, right, parent;//parent helps graphic drawing

    public BTNode(){
     element = 0;
     x = y = -1;
     parent = null;
     left = null;
     right = null;
 }
    
    public BTNode(int el){
     element = el;
     x = y = -1;
     parent = null;
     left = null;
     right = null;
 }
    
    public BTNode(int e, BTNode p, BTNode l, BTNode r){
        element = e;
        parent = p;
        left = l;
        right = r;
    }

    public int element()  {return  element;}

    public void  setX(int aX) {x = aX;}
    
    public void  setY(int aY) {y = aY;}
    
    public int  getX() {return  x;}
   
    public int  getY() {return  y;}
    
    public void  setElement(int e) {element = e;}
        
    public BTNode  getLeft() {return  left;}

    public void  setLeft(BTNode n) {left = n;}

    public BTNode  getRight() {return  right;}
 
    public void  setRight(BTNode n) {right = n;}

    public BTNode  getParent() {return  parent;}

    public void  setParent(BTNode n) {parent = n;}
}
