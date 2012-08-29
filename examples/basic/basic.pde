import net.hellonico.pwidgets.*;
import com.explodingpixels.macwidgets.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

PWidgetLibrary widgets;
JTextField tex;
JComboBox box1;
JCheckBox box2; 
JButton hello;

// dialog box example
void setup() {
  widgets = new PWidgetLibrary(this);
  
  HudWindow w = widgets.addWindow("Monday", 200, 180);
  
  widgets.addLabel(w, "buttonLabel");
  hello = widgets.addButton(w, "Hello");
  
  widgets.addLabel(w, "textLabel");
  tex = widgets.addTextField(w, "Text");
  widgets.addLabel(w, "boxLabel");
  box1 = widgets.addBox(w, "Box", new String[]{"Item one", "Item two"});
  widgets.addLabel(w, "checkLabel"); 
  box2 = widgets.addCheckBox(w, "box2"); 
  
  widgets.show(w);
  
}

void onButtonActionHello(ActionEvent evt) { 
  ((JButton) evt.getSource()).setText("Clicked");
}
void onTextFieldKeyTypedText(String c) {
  if(c == "\n") {
   println("yes");   
  }
}
void onBoxActionBox(ItemEvent evt) {
  println(evt);
}
void onCheckboxChangebox2(ChangeEvent evt) {
  tex.setText("Check box is "+((JCheckBox)evt.getSource()).isSelected());
  println(evt);
}

void draw() {
  frame.setVisible(false);
}
