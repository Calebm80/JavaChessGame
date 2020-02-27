package app;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
  // cached references
  private List<GameObject> gameObjects;
  private Timer timer;

  public GamePanel(int width, int height)
  {
    initPanel(width, height);
  }

  public GamePanel()
  {
    initPanel(600, 600); // default pane size here
  }

  private void initPanel(int width, int height)
  {
    gameObjects = new ArrayList<>();
    setPreferredSize(new Dimension(width, height));

    timer = new Timer(10, new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        updateAll();
        repaint();
      }
    });
    timer.start();
  }

  public void add(GameObject gameObject)
  {
    gameObjects.add(gameObject);
  }

  public void startAll()
  {
    for (GameObject gameObject : gameObjects)
    {
      gameObject.start();
    }
  }

  public void updateAll()
  {
    for (GameObject gameObject : gameObjects)
    {
      gameObject.update();
    }
  }

  private void drawBoard(Graphics g) {
	  int blockSizeX = this.getSize().width / 8;
	  int blockSizeY = this.getSize().height / 8;
	  for(int pos = 0; pos < 8*8; pos++) {
		  int x = (pos % 8) * blockSizeX;
		  int y = (pos / 8) * blockSizeY;
		  int offset = (pos % 16) < 8 ? 0:1;
		  Color color = (pos + offset) % 2 == 0 ? Color.WHITE : Color.black;
		  g.setColor(color);
		  g.fillRect(x, y, blockSizeX, blockSizeY);
	  }
  }

  /* for possible use later, not doing anything right now */
  public List<IClickable> getIntersections(Point p)
  {
    List<IClickable> clickables = new ArrayList<>();
    for (GameObject gameObject : gameObjects)
    {
      IClickable clickable = null;
      if (gameObject instanceof IClickable)
      {
        clickable = (IClickable) gameObject;
      }
      if (clickable != null && clickable.intersects(p))
      {
        clickables.add(clickable);
      }
    }
    return clickables;
  }

  // override of JPanel drawing
  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    drawBoard(g);
    for (GameObject gameObject : gameObjects) {
      gameObject.draw(g);
    }
  }
}
