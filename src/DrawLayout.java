import java.awt.event.*; 
import javax.swing.*;
import java.awt.*;

public class DrawLayout extends JPanel implements ActionListener{
 private int canvasWidth = 0;
 private int canvasHeight = 0;
 private MaxHeap maxHeap;
 private DrawTree drawTree;
 private JTextField inputTextField = new JTextField(10);
 private JButton clearButton = new JButton("clear");
 private JButton enQueueButton = new JButton("enQueue");
 private JButton deQueueButton = new JButton("deQueue");
 private JLabel outputLabel = new JLabel("Message output will be displayed here");
  
 public DrawLayout(int aWidth, int aHeight) {
  canvasWidth = aWidth - 30;
  canvasHeight = aHeight - 150;//leave some space for buttons & text field in the upPanel 
  JPanel upPanel = new JPanel();
  upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.Y_AXIS));
  JLabel inputLabel = new JLabel("Input: ");
  JPanel inputPanel = new JPanel();
  inputPanel.add(inputLabel);
  inputPanel.add(inputTextField);
  upPanel.add(inputPanel);
  JPanel buttonPanel = new JPanel();
  buttonPanel.add(clearButton);
  buttonPanel.add(enQueueButton);
  buttonPanel.add(deQueueButton);
  upPanel.add(buttonPanel);
  JPanel outputPanel = new JPanel();
  outputPanel.add(outputLabel);
  upPanel.add(outputPanel);
  add(upPanel);
   
  maxHeap = new MaxHeap();
  drawTree = new DrawTree(maxHeap, canvasWidth, canvasHeight);
  drawTree.setBackground(Color.orange);
  drawTree.setSize(canvasWidth, canvasHeight);
  add(drawTree);
   
  clearButton.addActionListener(this);
  enQueueButton.addActionListener(this);
  deQueueButton.addActionListener(this);
 }
  
 public void actionPerformed (ActionEvent evt) {
  Object sourse = evt.getSource();
  String tmp = inputTextField.getText().trim();

  if(sourse == clearButton){
   inputTextField.setText("");
   outputLabel.setText("Cleared");
   maxHeap = new MaxHeap();
   drawTree.updateTree(maxHeap);
   drawTree.repaint();
   inputTextField.requestFocus();
  }
  else if(sourse == enQueueButton){
   if(tmp.length() < 1) {
    outputLabel.setText("The input is empty, reenter an integer.");
   }
   else { 
    try{
     int value = Integer.parseInt(tmp);
     maxHeap.insert(value);
     drawTree.updateTree(maxHeap);
     drawTree.repaint();
    }
    catch (NumberFormatException ex){
     outputLabel.setText("The input is not an integer, reenter an integer.");
    }
   }
   inputTextField.setText("");
   inputTextField.requestFocus();
  }
  else if(sourse == deQueueButton){ 
   //to be finished 
	  BTNode n = maxHeap.deleteRoot();
	   if(n != null) {
	    drawTree.updateTree(maxHeap);
	    drawTree.repaint();
	    outputLabel.setText("The returned node is " + n.element());
	   }
	   else{
	    outputLabel.setText("The heap is empty");
	   }
  }
 }
} 