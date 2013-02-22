import java.awt.*;

public class DrawTree extends Canvas {
 private MaxHeap maxHeap;
 private int canvasWidth;
 private int canvasHeight;
 private NodeInformation tmpnif;
 private Queue nifqueue = new Queue();//used by to store node locations.
 private Queue wkqueue1 = new Queue();//together with wkqueue2 to achieve 
 private Queue wkqueue2 = new Queue();//breadth first effect level-by-level.
 private int firstNodeX = 170;
 private int firstNodeY = 17;
 private int boundingBox = 24;
 int yInc;
 int xInc;
 private int xText; //the start position for node's x value
 private int yText; //the start position for node's y value

 public DrawTree(MaxHeap aMaxHeap, int aWidth, int aHeight) {
  maxHeap = aMaxHeap;
  canvasWidth = aWidth;
  canvasHeight = aHeight;
  yInc = canvasHeight/6; 
 }
  
 public void updateTree(MaxHeap aMaxHeap) {
  BTNode root, child;
  maxHeap = aMaxHeap;
  int x, y;
  int level = 0;
  int maxLevel;
  
  if(!maxHeap.isEmpty()){ 
   root = maxHeap.root();
   root.setX(firstNodeX); 
   root.setY(firstNodeY);
   wkqueue1.enque(root);
   tmpnif = new NodeInformation();
   tmpnif.setE(root.element());
   tmpnif.setX(firstNodeX);
   tmpnif.setY(firstNodeY);
   nifqueue.enque(tmpnif);
  }

  while(!wkqueue1.isEmpty() || !wkqueue2.isEmpty()){ 
   maxLevel = maxNodesInLevel(++level+1);
   xInc = canvasWidth / maxLevel;
   if(!wkqueue1.isEmpty()){
    while(!wkqueue1.isEmpty()){ 
     root = (BTNode)wkqueue1.deque();
     if(root.getLeft()!= null){
      child = root.getLeft();
      x = root.getX() - (xInc+level*3);
      y = root.getY() + yInc;
      child.setX(x);
      child.setY(y);
      wkqueue2.enque(child);
      tmpnif = new NodeInformation();
      tmpnif.setE(child.element());
      tmpnif.setX(x);
      tmpnif.setY(y);
      tmpnif.setParentX(root.getX());
      tmpnif.setParentY(root.getY());
      nifqueue.enque(tmpnif);
     }
     if(root.getRight()!= null){
      child = root.getRight();
      x = root.getX() + (xInc+level*3);
      y = root.getY() + yInc;
      child.setX(x);
      child.setY(y);
      wkqueue2.enque(child);
      tmpnif = new NodeInformation();
      tmpnif.setE(child.element());
      tmpnif.setX(x);
      tmpnif.setY(y);
      tmpnif.setParentX(root.getX());
      tmpnif.setParentY(root.getY());
      nifqueue.enque(tmpnif);
     }

    }
   }
   else if(!wkqueue2.isEmpty()){
    while(!wkqueue2.isEmpty()){ 
     root = (BTNode)wkqueue2.deque();
     if(root.getLeft()!= null){
      child = root.getLeft();
      x = root.getX() - (xInc+level*3);
      y = root.getY() + yInc;
      child.setX(x);
      child.setY(y);
      wkqueue1.enque(child);
      tmpnif = new NodeInformation();
      tmpnif.setE(child.element());
      tmpnif.setX(x);
      tmpnif.setY(y); 
      tmpnif.setParentX(root.getX());
      tmpnif.setParentY(root.getY());
      nifqueue.enque(tmpnif);
     }
     if(root.getRight()!= null){
      child = root.getRight();
      x = root.getX() + (xInc+level*3);
      y = root.getY() + yInc;
      child.setX(x);
      child.setY(y);
      wkqueue1.enque(child);
      tmpnif = new NodeInformation();
      tmpnif.setE(child.element());
      tmpnif.setX(x);
      tmpnif.setY(y);
      tmpnif.setParentX(root.getX());
      tmpnif.setParentY(root.getY());
      nifqueue.enque(tmpnif);
     }
    }
   }
   level++;
  }
 }
 
 public void paint(Graphics g) {
     int x, y, xp, yp, e;
     while(!nifqueue.isEmpty()){
      tmpnif = (NodeInformation)nifqueue.deque();
      e = tmpnif.getElement();
      x = tmpnif.getX();
      y = tmpnif.getY();
      xp = tmpnif.getParentX();
      yp = tmpnif.getParentY();
      g.setColor(Color.black);
      g.fillOval(x, y, boundingBox, boundingBox);
      xText = x + boundingBox/3 - 5;
      yText = y + boundingBox/2 + 5;
      g.setColor(Color.white);
      g.drawString(e+"", xText, yText);
      if(xp != -1){
       g.setColor(Color.black);
       g.drawLine(xp+boundingBox/2, yp+boundingBox-1,
         x+boundingBox/2, y);
      }
     }
 }
 
 private int maxNodesInLevel(int level){
  return (int)Math.pow(2.0, level);
 }
 
 private class NodeInformation {
  public int e;
  public int x;
  public int y;
  public int xp;
  public int yp;
  
  public NodeInformation(){
   e = -1;
   x = -1; 
   y = -1;
   xp = -1;
   yp = -1;
  }
  
  public void setE(int element){
   e = element;
  }
  public void setX(int aX){
   x = aX;
  }
  public void setY(int aY){
   y = aY;
  }
  public void setParentX(int aXp){
   xp = aXp;
  }
  public void setParentY(int aYp){
   yp = aYp;
  }
  public int getElement(){
   return e;
  }
  public int getX(){
   return x;
  }
  public int getY(){
   return y;
  }
  public int getParentX(){
   return xp;
  }
  public int getParentY(){
   return yp;
  }
 }
}
