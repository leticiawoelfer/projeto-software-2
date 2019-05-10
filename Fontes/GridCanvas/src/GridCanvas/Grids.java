package GridCanvas;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mattos
 */
/** This is the demo class. */

public class Grids extends Frame {
  /*
   * Construct a GfxDemo2 given its title, width and height. Uses a
   * GridBagLayout to make the Canvas resize properly.
   */
  public Grids(String title, int width, int height, int rows, int cols) {
    setTitle(title);

    // Now create a Canvas and add it to the Frame.
    GridCanvas xyz = new GridCanvas(width, height, rows, cols);
    add(xyz);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        setVisible(false);
        dispose();
        System.exit(0);
      }
    });

    // Normal end ... pack it up!
    pack();
  }

    public static void main(String[] a) {
    int maxLinhas = 15;
    int maxColunas = 15;
    
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize();
    System.out.println("Screen width = " + d.width);
    System.out.println("Screen height = " + d.height);
    
    Grids g = new Grids("Test", d.width-100, d.height-100, maxLinhas, maxColunas);
    g.setVisible(true);
  }

}
