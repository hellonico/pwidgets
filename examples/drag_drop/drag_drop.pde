import net.hellonico.pwidgets.*;
import java.awt.Component;
import com.explodingpixels.macwidgets.*;

PWidgetLibrary widgets;
PImage i;
int lastX, lastY;
boolean move = false;

void setup() {
       size(800,400);
      
       widgets = new PWidgetLibrary(this);
       HudWindow w = widgets.addWindow("Drag", 200, 180);
       widgets.enableDragAndDrop(w.getContentPane());
       widgets.show(w);
}

void draw() {
      background(0);
      if(i!=null) {
        if(move)
         image(i, mouseX, mouseY);
        else
         image(i, lastX, lastY);
      }  	
}

/**
	This is added when you use the enableDragAndDrop method
*/
void filesDropped(Component c, File[] files) {
  	i = loadImage(files[0].getAbsolutePath());
}

void mouseClicked() {
    move = !move;
    lastX = mouseX;
    lastY = mouseY;
}
