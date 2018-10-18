package pkgGame;

//tanyanes on github

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

import pkgEnum.ePuzzleViolation;
import pkgHelper.LatinSquare;
import pkgHelper.PuzzleViolation;
/**
 * Sudoku - This class extends LatinSquare, adding methods, constructor to
 * handle Sudoku logic
 * 
 * @version 1.2
 * @since Lab #2
 * @author Bert.Gibbons
 *
 */
public class Sudoku extends LatinSquare {
	/**
	 * 
	 * iSize - the length of the width/height of the Sudoku puzzle.
	 * 
	 * @version 1.2
	 * @since Lab #2
	 */
	private int iSize;

	/**
	 * iSqrtSize - SquareRoot of the iSize. If the iSize is 9, iSqrtSize will be
	 * calculated as 3
	 * 
	 * @version 1.2
	 * @since Lab #2
	 */

	private int iSqrtSize;

	/** puzzle 
	 * Sudoku - for Lab #2... do the following:
	 * 
	 * set iSize If SquareRoot(iSize) is an integer, set iSqrtSize, otherwise throw
	 * exception
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iSize- length of the width/height of the puzzle
	 * @throws Exception if the iSize given doesn't have a whole number square root
	 */
	public Sudoku(int iSize) throws Exception {
		this.iSize = iSize;

		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		} else {
			throw new Exception("Invalid size");
		}
		int[][] puzzle = new int[iSize][iSize];
		super.setLatinSquare(puzzle);
		
		FillDiagonalRegions();
		SetCells();
		fillRemaining(this.cells.get(Objects.hash(0,iSqrtSize)));
	}

	/**
	 * Sudoku - pass in a given two-dimensional array puzzle, create an instance.
	 * Set iSize and iSqrtSize
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param puzzle - given (working) Sudoku puzzle. Use for testing
	 * @throws Exception will be thrown if the length of the puzzle do not have a
	 *                   whole number square root
	 */
	public Sudoku(int[][] puzzle) throws Exception {
		super(puzzle);
		this.iSize = puzzle.length;
		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		} else {
			throw new Exception("Invalid size");
		}

	}

	/**
	 * getPuzzle - return the Sudoku puzzle
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return - returns the LatinSquare instance
	 */
	
	//new methods start here
	
	//added a hashmap to Sudoku class as a private attribute
	
	private HashMap<Integer, Cell> cells = new HashMap<Integer, Cell>();
	
	//needed a getter method for the hashcode for a specific cell at index row,column
	public static Integer getCellHashCode(Integer row, Integer column) {
		return Objects.hash(row,column);
	}
	
	//Gibbons added a private attribute called HashSet of integers to get  the range of unused values
	//note*** this was not in the lab write-up
	
	private HashSet<Integer> getAllValidCellValues(int iCol, int iRow) {

		HashSet<Integer> hsCellRange = new HashSet<Integer>();
		for (int i = 0; i < iSize; i++) {
		hsCellRange.add(i + 1);
		}
		HashSet<Integer> hsUsedValues = new HashSet<Integer>();

		Collections.addAll(hsUsedValues, Arrays.stream(super.
		getRow(iRow)).boxed().toArray(Integer[]::new));

		Collections.addAll(hsUsedValues, Arrays.stream(super.getColumn(iCol)).boxed().toArray(Integer[]::new));
		Collections.addAll(hsUsedValues, Arrays.stream(this.getRegion(iCol, iRow)).boxed().toArray(Integer[]::new));

		hsCellRange.removeAll(hsUsedValues);
		return hsCellRange;
		}
	
	private void SetCells() {
		for (int iRow=0; iRow<iSize;iRow++) {
			for(int iCol=0;iCol<iSize;iCol++) {
				Cell c = new Cell(iRow,iCol);
				c.setlstValidValues(getAllValidCellValues(iCol,iRow));
				c.ShuffleValidValues();
				cells.put(c.hashCode(), c);
				//Cell cl= Cells.get(Objects.hash(2,2)); idk what this is but the professor added that
				}
		}
	}
	
	private void ShowAvailableValues() {
		for (int iRow = 0; iRow < iSize; iRow++) {
			for (int iCol = 0; iCol < iSize; iCol++) {

				Cell c = cells.get(Objects.hash(iRow, iCol));
				for (Integer i : c.getlstValidValues()) {
					System.out.print(i + " ");
				}
				System.out.println("");
			}
		}
	}
	
	private boolean fillRemaining(Cell c) {

		if (c == null)
			return true;

		for (int num : c.getlstValidValues()) {
			if (isValidValue(c.getiRow(), c.getiCol(), num)) { //fixed this
				this.getPuzzle()[c.getiRow()][c.getiCol()] = num;

				if (fillRemaining(c.GetNextCell(c))) //recursive statement
					return true;
				this.PrintPuzzle();

				this.getPuzzle()[c.getiRow()][c.getiCol()] = 0;
			}
		}
		return false;

	}
	
	//cell class
	private class Cell {
		private int iRow, iCol;
		private ArrayList<Integer> lstValidValues = new ArrayList<Integer>();
		
		public Cell(int iRow,int iCol) {
			super();
			this.iRow=iRow;
			this.iCol=iCol;
		}
		
		public int getiRow() {
			return iRow; 
		}
		
		public int getiCol() {
			return iCol;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(iRow, iCol);
		}
		
		@Override
		public boolean equals(Object o) {
			if(o == this) {
				return true;
			}else if (!(o instanceof Cell)) {
				return false;
			}
			Cell c = (Cell)o;
			return iCol == c.iCol&&iRow==c.iRow;
		}
		
		
		public ArrayList<Integer> getlstValidValues() {
			return lstValidValues;
		}
		
		public void setlstValidValues(HashSet<Integer> hsValidValues) {
			lstValidValues = new ArrayList<Integer>(hsValidValues);
		}
		
		public void ShuffleValidValues() {
			Collections.shuffle(lstValidValues);
		}
		
		public Cell GetNextCell(Cell c) {

			int iCol = c.getiCol() + 1;
			int iRow = c.getiRow();
			int iSqrtSize = (int) Math.sqrt(iSize);

			if (iCol >= iSize && iRow < iSize - 1) {
				iRow = iRow + 1;
				iCol = 0;
			}
			if (iRow >= iSize && iCol >= iSize)
				return null;

			if (iRow < iSqrtSize) {
				if (iCol < iSqrtSize)
					iCol = iSqrtSize;
			} else if (iRow < iSize - iSqrtSize) {
				if (iCol == (int) (iRow / iSqrtSize) * iSqrtSize)
					iCol = iCol + iSqrtSize;
			} else {
				if (iCol == iSize - iSqrtSize) {
					iRow = iRow + 1;
					iCol = 0;
					if (iRow >= iSize)
						return null;
				}
			}

			return (Cell) cells.get(Objects.hash(iRow, iCol));

		}
	}
	
	public int[][] getPuzzle() {
		return super.getLatinSquare();
	}

	/**
	 * getRegion - figure out what region you're in based on iCol and iRow and call
	 * getRegion(int)<br>
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegion(0,3) would call getRegion(1) and return [2],[3],[3],[4]
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iCol given column
	 * @param iRow given row
	 * @return - returns a one-dimensional array from a given region of the puzzle
	 */
	public int[] getRegion(int iCol, int iRow) {

		int i = (iCol / iSqrtSize) + ((iRow / iSqrtSize) * iSqrtSize);

		return getRegion(i);
	}

	/**
	 * getRegion - pass in a given region, get back a one-dimensional array of the
	 * region's content<br>
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegion(2) and return [3],[4],[4],[1]
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param r given region
	 * @return - returns a one-dimensional array from a given region of the puzzle
	 */

	public int[] getRegion(int r) {

		int[] reg = new int[super.getLatinSquare().length];

		int i = (r / iSqrtSize) * iSqrtSize;
		int j = (r % iSqrtSize) * iSqrtSize;
		int jMax = j + iSqrtSize;
		int iMax = i + iSqrtSize;
		int iCnt = 0;

		for (; i < iMax; i++) {
			for (j = (r % iSqrtSize) * iSqrtSize; j < jMax; j++) {
				reg[iCnt++] = super.getLatinSquare()[i][j];
			}
		}

		return reg;
	}

	@Override
	public boolean hasDuplicates() {
		if (super.hasDuplicates())
			return true;

		for (int k = 0; k < this.getPuzzle().length; k++) {
			if (super.hasDuplicates(getRegion(k))) {
				super.AddPuzzleViolation(new PuzzleViolation(ePuzzleViolation.DupRegion, k));
			}
		}

		return (super.getPV().size() > 0);
	}

	/**
	 * isPartialSudoku - return 'true' if...
	 * 
	 * It's a LatinSquare If each region doesn't have duplicates If each element in
	 * the first row of the puzzle is in each region of the puzzle At least one of
	 * the elemnts is a zero
	 * 
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return true if the given puzzle is a partial sudoku
	 */
	public boolean isPartialSudoku() {

		super.setbIgnoreZero(true);

		super.ClearPuzzleViolation();

		if (hasDuplicates())
			return false;

		if (!ContainsZero()) {
			super.AddPuzzleViolation(new PuzzleViolation(ePuzzleViolation.MissingZero, -1));
			return false;
		}
		return true;

	}

	/**
	 * isSudoku - return 'true' if...
	 * 
	 * Is a partialSudoku Each element doesn't a zero
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return - returns 'true' if it's a partialSudoku, element match (row versus
	 *         column) and no zeros
	 */
	public boolean isSudoku() {

		this.setbIgnoreZero(false);

		super.ClearPuzzleViolation();

		if (hasDuplicates())
			return false;

		if (!super.isLatinSquare())
			return false;

		for (int i = 1; i < super.getLatinSquare().length; i++) {

			if (!hasAllValues(getRow(0), getRegion(i))) {
				return false;
			}
		}

		if (ContainsZero()) {
			return false;
		}

		return true;
	}

	/**
	 * isValidValue - test to see if a given value would 'work' for a given column /
	 * row
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iCol   puzzle column
	 * @param iRow   puzzle row
	 * @param iValue given value
	 * @return - returns 'true' if the proposed value is valid for the row and
	 *         column
	 */
	
	/* checks to see if value is valid for given column
	 * iCol - given column index
	 * iValue - proposed value to test
	 */
	public boolean isValidColumnValue(int iCol, int iValue) {
		if (doesElementExist(super.getColumn(iCol), iValue)) {
			return false;
		}
		return true;
	}
	
	/* checks to see if value is valid for given row
	 * iRow - given column index
	 * iValue - proposed value to test
	 */
	public boolean isValidRowValue(int iRow, int iValue) {
		if (doesElementExist(super.getRow(iRow), iValue)) {
			return false;
		}
		return true;
	}
	
	/* checks to see if value is valid for given region
	 * iReg - given region index
	 * iValue - proposed value to test
	 */
	public boolean isValidRegionValue(int iRow, int iCol, int iValue) {
		if (doesElementExist(this.getRegion(iCol, iRow), iValue)) {
			return false;
		}
		return true;
	}
	
	public boolean isValidValue(int iCol, int iRow, int iValue) {
		if (isValidColumnValue(iCol, iValue) && isValidRowValue(iRow, iValue) && isValidRegionValue(iRow, iCol, iValue)) {
					return true;
				}
		return false;
	}
	
	/*
	 * Returns a region number based on a given column and row iCol = given column
	 * number, iRow = given row number
	 */
	public int getRegionNbr(int iCol, int iRow) {
		int region = (iCol / iSqrtSize) + ((iRow / iSqrtSize) * iSqrtSize);
		return region;
	}

	// sets the values of a given region (to be shuffled later)
	// r = given region number

	public void SetRegion(int r) {

		int[] arr = new int[iSize];
		int counter = 0;

		for (int k = 0; k < arr.length; k++) {
			arr[k] = k + 1;
		}

		int i = (r / iSqrtSize) * iSqrtSize;
		int j = (r % iSqrtSize) * iSqrtSize;
		int iMax = i + iSqrtSize;
		int jMax = j + iSqrtSize;

		for (i = 0; i < iMax; i++) {
			for (j = 0; j < jMax; j++) {
				int[][] tempSudoku;
				tempSudoku = super.getLatinSquare();
				tempSudoku[i][j] = arr[counter];
				super.setLatinSquare(tempSudoku);
				counter++;
			}
		}

	}

		// shuffleArray will shuffle a given 1d array
	// arr = given 1d array
	// random helps to pick a random index from current position i to the length of
	// the array

	public static void ShuffleArray(int[] arr) {
		int index;
		int temp1;
		int temp2;

		for (int i = 0; i < arr.length; i++) {
			Random random = new SecureRandom();
			index = random.nextInt(arr.length - i);
			index = index - i;

			temp1 = arr[index];
			temp2 = arr[i];
			arr[i] = temp1;
			arr[index] = temp2;
		}
	}

	// uses the region set by SetRegion and Shuffles the values of that region
	// r = given region number

	public void ShuffleRegion(int r) {

		int[][] tempSudoku;
		int[] arr = getRegion(r);
		ShuffleArray(arr);

		int counter = 0;

		int i = (r / iSqrtSize) * iSqrtSize;
		int j = (r % iSqrtSize) * iSqrtSize;
		int iMax = i + iSqrtSize;
		int jMax = j + iSqrtSize;

		for (i = 0; i < iMax; i++) {
			for (j = 0; j < jMax; j++) {
				tempSudoku = super.getLatinSquare();
				tempSudoku[i][j] = arr[counter];
				super.setLatinSquare(tempSudoku);
				counter++;
			}
		}

	}
	
	// after the puzzle is created, sets the diagonal regions with
	// random values

			public void FillDiagonalRegions() {
				for (int i = 0; i < iSize; i = i + iSqrtSize) {
					System.out.println("Filling region: " + getRegionNbr(i, i));
					SetRegion(getRegionNbr(i,i));
					ShuffleRegion(getRegionNbr(i,i));
					}
			}

	// prints the puzzle to the console with
	// space between columns
	// line break after each row//

	public void PrintPuzzle() {
		for (int i = 0; i < this.getPuzzle().length; i++) {
			System.out.println("");
			for (int j = 0; j < this.getPuzzle().length; j++) {
				System.out.print(this.getPuzzle()[i][j]);
				if ((j + 1) % iSqrtSize == 0)
					System.out.print(" ");
			}
			if ((i + 1) % iSqrtSize == 0)
				System.out.println(" ");
		}
	}
	
	/* Lab 4: implement isValidColumnValue, isValidRowValue, isValidRegionValue
	 * Refactor isValidValue to call these new methods
	 * implement fillRemaining(int,int) which will determine a valid value for the remaining cells
	 */
	
	
	public void fillRemaining(int iRow, int iCol) {
		
		for (int i = 0; i < iSize; i++) {
			for (int j = 0; j < iSize; j++) {
				if (getPuzzle()[iRow][iCol] == 0) {
					for (int k = 1; k <= iSize; k++) {
						if (isValidValue(iCol, iRow, k)) {
							getPuzzle()[iRow][iCol] = k;
						}
					}
				}
			}
		
		}
		
		// want to look through all of the values
		// if a value == 0, then set it to a valid value
		
		//can be done using recursion (the method calling itself)
		//is going to use isValueValid
		//when the method returns a true, the value works
		//when the method returns false, we backtrack
		// fill by going across the rows
		
		
		
		
		
	}
	
	
}