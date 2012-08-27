/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

package net.hellonico.pwidgets;

import java.awt.Dimension;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import processing.core.PApplet;

import com.explodingpixels.macwidgets.HudWidgetFactory;
import com.explodingpixels.macwidgets.HudWindow;
import com.explodingpixels.macwidgets.SourceList;
import com.explodingpixels.macwidgets.SourceListCategory;
import com.explodingpixels.macwidgets.SourceListClickListener;
import com.explodingpixels.macwidgets.SourceListDarkColorScheme;
import com.explodingpixels.macwidgets.SourceListItem;
import com.explodingpixels.macwidgets.SourceListModel;

/**
 * This is a template class and can be used to start a new processing library or
 * tool. Make sure you rename this class as well as the name of the example
 * package 'template' to your own library or tool naming convention.
 * 
 * @example Hello
 * 
 *          (the tag @example followed by the name of an example included in
 *          folder 'examples' will automatically include the example in the
 *          javadoc.)
 * 
 */

public class PWidgetLibrary {

	PApplet myParent;
	public final static String VERSION = "##library.prettyVersion##";

	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the library.
	 * 
	 * @example Hello
	 * @param theParent
	 */
	public PWidgetLibrary(PApplet theParent) {
		myParent = theParent;
	}
	
	public void disableMainWindow() {
		myParent.frame = new net.hellonico.pwidgets.EmptyFrame();
		//myParent.frame.setVisible(false);
	}

	public void enableDragAndDrop() {
		try {
			String methodName = "onDragAndDrop";
			final Method m = myParent.getClass().getMethod(methodName,
					new Class[] { DropTargetDropEvent.class });

			DropTarget dt = new DropTarget(myParent.frame,
					new DropTargetListener() {
						public void dragEnter(DropTargetDragEvent arg0) {
						}

						public void dragExit(DropTargetEvent arg0) {
						}

						public void dragOver(DropTargetDragEvent arg0) {
						}

						public void drop(DropTargetDropEvent event) {
							try {
								m.invoke(myParent, new Object[] { event });
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}

						public void dropActionChanged(DropTargetDragEvent arg0) {
						}

					});
			myParent.frame.setDropTarget(dt);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public HudWindow addWindow(String name, int w, int h) {
		HudWindow hud = new HudWindow(name);
//		hud.getContentPane().setLayout(
//				new BoxLayout(hud.getContentPane(), BoxLayout.Y_AXIS));
		//JPanel listPane = new JPanel();
		BoxLayout boxLayout = new BoxLayout(hud.getContentPane(), BoxLayout.PAGE_AXIS);
		
		hud.getContentPane().setLayout(boxLayout);
		//hud.getContentPane().setMaximumSize(new Dimension(w, h-100));
		hud.getJDialog().setSize(w, h);
		return hud;
	}

	public HudWindow show(HudWindow hud) {
		int cs = hud.getContentPane().getComponents().length;
		//hud.getContentPane().setLayout(new GridLayout(cs/2,2));
		hud.getJDialog().setLocationRelativeTo(null);
		hud.getJDialog().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		hud.getJDialog().setVisible(true);
		return hud;
	}

//	private void a(HudWindow hud, JComponent j) {
////		JPanel p = new JPanel();
////		p.add(j);
////		p.setMaximumSize(new Dimension(200, 50));
////		p.setBorder(BorderFactory.createTitledBorder(" - "));
//		hud.getContentPane().add(j);
//	}
//	private void a(JFrame frame, JComponent j) {
//		b((JComponent) frame.getContentPane(), j);
//	}
	private void b(Object o, JComponent j) {
		if(o instanceof JFrame) {
			((JFrame)o).getContentPane().add(j);
		} 
		if(o instanceof JComponent) {
			((JComponent)o).add(j);
		}
		if(o instanceof HudWindow) {
			((HudWindow)o).getContentPane().add(j);
		}
	}

	public JComboBox addBox(Object hud, String name, String[] list) {
		JComboBox box = HudWidgetFactory
				.createHudComboBox(new DefaultComboBoxModel(list));
		box.setName(name);
		try {
			String methodName = "onBoxAction" + name;
			final Method m = myParent.getClass().getMethod(methodName,
					new Class[] { ItemEvent.class });
			box.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent event) {
					try {
						m.invoke(myParent, new Object[] { event });
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			});
		} catch (Exception e) {

		}
		b(hud, box);
		return box;
	}

	public JLabel addLabel(Object hud, String labelText) {
		JLabel label = HudWidgetFactory.createHudLabel(labelText);
		b(hud, label);
		return label;
	}
	public JButton addButton(Object hud, final String label) {
		final JButton button = HudWidgetFactory.createHudButton(label);
		try {
			String methodName = "onButtonAction" + label;
			final Method m = myParent.getClass().getMethod(methodName,
					new Class[] { ActionEvent.class });
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent action) {
					try {
						m.invoke(myParent, new Object[] { action });
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			});
		} catch (Exception e) {

		}

		b(hud, button);
		return button;
	}

	public JCheckBox addCheckBox(Object hud, String label) {
		JCheckBox box = HudWidgetFactory.createHudCheckBox(label);
		try {
			String methodName = "onCheckboxChange" + label;
			final Method m = myParent.getClass().getMethod(methodName,
					new Class[] { ChangeEvent.class });

			box.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent evt) {
					try {
						m.invoke(myParent, new Object[] { evt });
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}

			});
		} catch (Exception e) {

		}
		b(hud, box);
		return box;
	}
	
	public SourceList addSourceList(
			Object frame, 
			HashMap<String, ArrayList<String>> items,
			boolean blackTheme
			) {
		
		SourceListModel model = new SourceListModel();
		for(String category : items.keySet()) {
			SourceListCategory cat = new SourceListCategory(category);
			model.addCategory(cat);
			ArrayList<String> its = items.get(category);
			for(String it : its) {
				SourceListItem a = new NSourceListItem(category, it);
				model.addItemToCategory(a, cat);
			}	
		}
		
		SourceList sourceList = new SourceList(model);
		
		for(SourceListCategory category : model.getCategories()) {
			sourceList.setExpanded(category, false);
		}

		if(blackTheme)
		  sourceList.setColorScheme(new SourceListDarkColorScheme());
		
		try {
			String methodName = "onSourceListClick";
			final Method m = myParent.getClass().getMethod(methodName,
					new Class[] { NSourceListItem.class });
			sourceList.addSourceListClickListener(new SourceListClickListener() {

				public void sourceListCategoryClicked(SourceListCategory arg0,
						Button arg1, int arg2) {
					// TODO Auto-generated method stub
					
				}

				public void sourceListItemClicked(SourceListItem arg0,
						Button arg1, int arg2) {
					try {
						m.invoke(myParent,new Object[] { (NSourceListItem) arg0 });
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			});
		} catch (Exception e) {

		}
		
		b(frame, sourceList.getComponent());
		//frame.add(sourceList.getComponent());
		return sourceList;
	}

	public JTextField addTextField(Object hud, String label) {
		JTextField jt = HudWidgetFactory.createHudTextField(label);
		jt.setColumns(10);
		jt.setMaximumSize(new Dimension(300, 100));
		try {
			String methodName = "onTextFieldKeyTyped" + label;
			final Method m = myParent.getClass().getMethod(methodName,
					new Class[] { String.class });
			jt.addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent arg0) {
				}

				public void keyReleased(KeyEvent arg0) {
				}

				public void keyTyped(KeyEvent arg0) {
					try {
						m.invoke(myParent,
								new Object[] { "" + arg0.getKeyChar() });
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			});
		} catch (Exception e) {

		}

		b(hud, jt);
		return jt;
	}

}
