package app;

import javax.swing.JFrame;
import java.awt.Dimension;

public class ChessGame
{
  // cached references
  private JFrame window;
  private GamePanel gamePanel;

  // config params
  private final int DEFAULT_WIDTH = 600;
  private final int DEFAULT_HEIGHT = 600;

  private State GameState; // not used yet

  public enum State {
    EXITING,
    PAUSED,
    PLAYING
  }

  public ChessGame()
  {
    this.gamePanel = new GamePanel();
    initPieces();
    initWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT); // default window size here
  }

/* this needs to be called before initWindow() */
  private void initPieces()
  {
    gamePanel.add(new Rook());
    gamePanel.add(new Rook(300, 0));
  }

  private void initWindow(int width, int height)
  {
    this.window = new JFrame("Chess Game");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setPreferredSize(new Dimension(width, height));
    window.add(gamePanel);
    window.setContentPane(gamePanel);
    window.addMouseListener(new GameMouseListener(gamePanel));
    window.pack();
    window.setVisible(true);
  }

  public void gameLoop()
  {
    gamePanel.startAll();
  }
}