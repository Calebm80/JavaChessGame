package app;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The GamePanel class is the primary content pane for the chess game itself, and is where all chesspieces and board will be drawn to.
 */

public class GamePanel extends JPanel implements MouseListener {
    private static final long serialVersionUID = 1L;

    // cached references
    private Timer timer;
    private Board board;

    // config params
    private final int DEF_WIDTH = 600; // default panel width
    private final int DEF_HEIGHT = 600; // default panel height
    private final int REFRESH_RATE = 10; // how often update and redraw will be called in ms

    public GamePanel(int width, int height) {
        initPanel(width, height);
    }

    public GamePanel() {
        super();
        addMouseListener(this);
        initPanel(DEF_WIDTH, DEF_HEIGHT);
    }

    private void initPanel(int width, int height) {
        board = new Board();
        setPreferredSize(new Dimension(width, height));
        timer = new Timer(REFRESH_RATE, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    public Board getBoard() {
        return this.board;
    }

    private void drawBoard(Graphics g) {
        board.draw(g);
    }

    // override of JPanel drawing
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        board.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // intentionally left empty
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // intentionally left empty
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // intentionally left empty
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // intentionally left empty
    }
}
