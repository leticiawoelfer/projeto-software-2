package GridCanvas;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



/**
 * Program to draw grids.
 * 
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class GridCanvas extends Canvas {
  int width;
  int height;
  int rows;
  int cols;

  public GridCanvas(int w, int h, int r, int c) {
    setSize(width = w, height = h);
    rows = r;
    cols = c;
  }

  public void paint(Graphics g) {
    int i;
    width = getSize().width;
    height = getSize().height;

    // draw the rows
    int rowHt = height / (rows);
    for (i = 0; i < rows; i++) {
      g.drawLine(0, i * rowHt, width, i * rowHt);
    }

    // draw the columns
    int rowWid = width / (cols);
    for (i = 0; i < cols; i++)
      g.drawLine(i * rowWid, 0, i * rowWid, height);
  }
}



    
