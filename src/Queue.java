public class Queue {
 //the capacity is 100
 public Object[] data;
 public int size;
 public int numOfNodes;
 public int front;
 public int rear;
  
 public Queue(){
  size = 100;
  numOfNodes = 0;
  front = 0;
  rear = 0;
  data = new Object[100];
 }
  
 public Queue(int n){
  size = n;
  numOfNodes = 0;
  front = 0;
  rear = 0;
  data = new Object[n];
 }
  
 public boolean isEmpty(){
  return (numOfNodes == 0);
 }
  
 public boolean enque(Object aData){
  if(numOfNodes == size)
   return false; // overflow
  else {
   numOfNodes++;
   data[rear] = aData;
   rear = (rear + 1) % size;
   return true;
  }
 }

 public Object peek(){
  if(numOfNodes == 0){
   return null;
  }
  else{
   return data[front];
  }
 }
 
 public Object deque(){
  int frontL;
  if(numOfNodes == 0){
   return null;
  }
  else{
   frontL = front;
   front = (front + 1) % size;
   numOfNodes--;
   return data[frontL];
  }
 }
  
 public Object dequerear(){
  if(numOfNodes == 0){
   return null;
  }
  else{
   rear = rear-1;
   numOfNodes--;
   return data[rear];
  }
 }
}