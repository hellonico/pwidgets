import net.hellonico.pwidgets.*;
import com.explodingpixels.macwidgets.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Desktop;
import com.esotericsoftware.wildcard.Paths;

PWidgetLibrary widgets;
JComponent j;

String root = "/Users/Niko/Dropbox/Processing/"; 

void setup() {
  // instanciate the ui widgets library
  widgets = new PWidgetLibrary(this);

  // easy path regexp
  Paths paths = new Paths();
  paths.glob(root, "**"+"/"+"*.pde");
  
  // boring work to turn the list of path into a map 
  // <category>, <pde files>
  HashMap m = new HashMap();
  for (File file : paths.getFiles()) { 
    String path = file.getPath().substring(root.length());
    path = path.substring(path.indexOf("/")+1);
    String category = path.substring(0,path.indexOf("/"));
    String pfile = path.substring(category.length()+1);
    ArrayList o = (ArrayList) m.get(category);
    if(o==null) o = new ArrayList();
    o.add(pfile);
    m.put(category,o); 
  }
  
  // create an external frame for me
  JFrame f = new JFrame();
  SourceList c = widgets.addSourceList(f, m, false);
  c.useIAppStyleScrollBars();
  f.pack();
  f.setSize(400, f.getHeight());
  f.show();
}

/**
  Hide the main frame
*/
void draw() {
  frame.setVisible(false);
}

/**
 This callback is created when using <code>widgets.addSourceList()</code>
*/
void onSourceListClick(NSourceListItem item) {
 File f = new File(root+"libraries/"+item.getCategory()+"/"+item.getText()); 
 Desktop dt = Desktop.getDesktop();
 try {dt.open(f);} catch(Exception e) {}
}
