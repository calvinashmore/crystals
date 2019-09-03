/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.crystals.viz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author ashmore
 */
public abstract class RendererPanel extends JPanel {
  private BufferedImage image;

  public RendererPanel() {
    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
      }
    });
  }

  protected abstract void paintImpl(Graphics2D g);

  @Override
  protected void paintComponent(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());

    g.drawImage(image, 0, 0, this);
    paintImpl((Graphics2D)image.getGraphics());
  }
}
