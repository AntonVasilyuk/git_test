package ru.job4j.game;

import ru.job4j.figure.Figure;
import ru.job4j.figure.Queen;
import ru.job4j.figure.King;
import ru.job4j.figure.Knight;
import ru.job4j.figure.Castl;
import ru.job4j.figure.Bishop;

/**.
* Chapter_002
* Task 2.9.2
* Class for create Board for figure
*
* @author Anton Vasilyuk
* @version 1.0
* @since 0.1
*/

public class Board {

    /**.
     * @numFigure is index
     */
    private int numFigure = 0;

    /**.
     * Array figures
     */
    private Figure[] figures = new Figure[8];

    /**.
     * Method for fill array figures
     */
    public void fillFigure() {
		this.figures[0] = new Castl(new Cell(0, 0));
		this.figures[1] = new Knight(new Cell(0, 1));
        this.figures[2] = new Bishop(new Cell(0, 2));
		this.figures[3] = new Queen(new Cell(0, 3));
		this.figures[4] = new King(new Cell(0, 4));
        this.figures[5] = new Bishop(new Cell(0, 5));
		this.figures[6] = new Knight(new Cell(0, 6));
		this.figures[7] = new Castl(new Cell(0, 7));
    }

    /**.
     * Method for check validate move need figure
     * @param source start point
     * @param dist finish point
     * @return is it possible to move
     * @throws ImposibleMoveException if immposible move
     * @throws OccupedWayException position is occupied
     * @throws FigureNotFoundException figure is not found
     */
    public boolean move(Cell source, Cell dist) throws ImposibleMoveException, OccupedWayException, FigureNotFoundException {
        int index = 10;
        boolean result = true;

        int srcRow = source.getRow();
        int srcCol = source.getCol();
        int disRow = dist.getRow();
        int disCol = dist.getCol();

        for (int i = 0; i < figures.length; i++) {
            if (figures[i] != null && srcRow == figures[i].getCell().getRow() && srcCol == figures[i].getCell().getCol()) {
                index = i;
            }
        }

        if (index == 10) {
            result = false;
            throw new FigureNotFoundException("this cell empty, check your choise");
        }

		if (srcRow == disRow && srcCol == disCol) {
			throw new ImposibleMoveException("It cell is busy, make outher choice");
        }

		if (disRow > 7 || disRow < 0 || disCol > 7 || disCol < 0) {
            throw new ImposibleMoveException("incorrect choice, change outher cell");
        }

		fillFigure();
		Cell[] cell = this.figures[index].way(dist);

        if (cell == null) {
            result = false;
            throw new ImposibleMoveException("figure don't move to this cell");
        }

        for (int x = 1; x < cell.length; x++) {
            for (int y = 0; y < figures.length; y++) {
                if (cell[x].getRow() == figures[y].getCell().getRow()
                        && cell[x].getCol() == figures[y].getCell().getCol()) {
                    result = false;
                    throw new OccupedWayException("way is not empty");
                }
            }
        }
        if (result) {
            figures[index] = clone(dist, figures[index]);
        }
        return result;
    }

    /**.
     * Method for add and treament exception
     * @param source start point
     * @param dist finish point
     * @throws ImposibleMoveException if immposible move
     * @throws OccupedWayException position is occupied
     * @throws FigureNotFoundException figure is not found
     */
    public void checkChoise(Cell source, Cell dist) throws ImposibleMoveException, OccupedWayException, FigureNotFoundException {
        try {
            move(source, dist);
        } catch (FigureNotFoundException fnfe) {
            System.out.println("this cell empty, check your choise");
        } catch (ImposibleMoveException ime) {
            System.out.println("figure don't move to this cell");
        } catch (OccupedWayException owe) {
            System.out.println("way is not empty");
        }
    }

    /**.
     * Getter for figures
     * @return all figures
     */
    public Figure[] getFigures() {
        return this.figures;
    }

    /**.
     * Method for cloning
     * @param cell is cell for figure
     * @param figure is figure for cloning
     * @return cloning figure
     */
    public Figure clone(Cell cell, Figure figure) {
        Figure newFigure = null;
        if (figure instanceof Bishop) {
            newFigure = new Bishop(cell);
        }
        return newFigure;
    }
}