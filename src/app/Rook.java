package app;

import java.awt.Point;
import java.util.ArrayList;

/**
 * This is the Rook class inherited from abstract Piece class
 *
 */

public class Rook extends ChessPiece {
    public Rook(PieceColor color) {
            super("Rook.png", color);  
    }

    protected ArrayList<Point> generatePossibleMoves() {
        ArrayList<Point> moves = new ArrayList<Point>();
        for (int i = 1; i < 8; i++) {
            moves.add(new Point(0, i));
            moves.add(new Point(i, 0));
            moves.add(new Point(-1 * i, 0));
            moves.add(new Point(0, -1 * i));
        }
        
        return moves;
    }
}
