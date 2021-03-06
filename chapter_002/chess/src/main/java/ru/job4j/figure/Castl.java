package ru.job4j.figure;

import ru.job4j.game.Cell;
import ru.job4j.game.ImposibleMoveException;

/**.
* Chapter_002
* Task 2.9.2
* Create class Castl
*
* @author Anton Vasilyuk
* @version 1.0
* @since 0.1
*/

public class Castl extends Figure {

    /**.
     * Constructor for class Castl
     * @param cellPosition is position figure
     */
    public Castl(Cell cellPosition) {
        super(cellPosition);
    }

    /**.
     * Method for check validate way this figure
     * @param dist point
     * @return array from cell
     */
    @Override
    public Cell[] way(Cell dist) throws ImposibleMoveException {

        Cell[] cells;

        int srcRow = this.getCell().getRow();
        int srcCol = this.getCell().getCol();
        int disRow = dist.getRow();
        int disCol = dist.getCol();

		if (srcRow == disRow || srcCol == disCol) {
			cells = routing(srcRow, srcCol, disRow, disCol);
		} else {
			throw new ImposibleMoveException("don't right choise");
		}
		return cells;
	}
}