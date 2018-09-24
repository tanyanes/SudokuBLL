package pkgGame;


import pkgHelper.LatinSquare;

public class Sudoku extends LatinSquare {
	private int iSize;
	private int iSqrtSize;
	
	public Sudoku() {
		super();
	}
	
	public Sudoku(int iSize) throws java.lang.Exception {
		double root = Math.sqrt(iSize);
		int temp = (int) root;
		
		if(Math.pow(root, 2) == Math.pow(temp, 2)) {
			this.iSize = iSize;
			this.iSqrtSize = (int) Math.sqrt(iSize); 
		}
		else {
			throw new Exception("Invalid size");
		}
	}
	
	public Sudoku(int[][] puzzle) throws java.lang.Exception {
		super(puzzle);
		
		double root = Math.sqrt(puzzle.length);
		int temp = (int) root;
		
		if(Math.pow(root, 2) == Math.pow(temp, 2)) {
			iSize = puzzle.length;
			iSqrtSize = (int) Math.sqrt(puzzle.length);
		}
		else {
			throw new Exception("Invalid size");
		}
	}
	
	public int[][] getPuzzle() {
		return getLatinSquare();
	}
	
	public int[] getRegion(int r) {
		int[] reg = new int[getLatinSquare().length];
		
		int i = (r % iSqrtSize) * iSqrtSize;
		int j = (r / iSqrtSize) * iSqrtSize;
		int iMax = i + iSqrtSize;
		int jMax = j + iSqrtSize;
		int index = 0;
		
		for(; j < jMax; j++) {
			for(i = (r % iSqrtSize) * iSqrtSize; i < iMax; i++) {
				reg[index] = getLatinSquare()[j][i];
				index++;
			}
		}
		return reg;
	}
	
	public int[] getRegion(int iCol, int iRow) {
		return getRegion((iCol/iSqrtSize) + ((iRow/iSqrtSize)*iSqrtSize)); 
	}
	
	public boolean isPartialSudoku() {
		int[] firstRow = getRow(0);
		int[] temp;
		
		if(!this.isLatinSquare()) {
			if(ContainsZero()) {
				return true;
			}
			return false;
		}
		
		for(int j = 0; j < iSize; j++) {
			temp = getRegion(j);
			
			if(hasDuplicates(temp)) {
				return false;
			}
			if (!ContainsZero()) {
				return false;
			}
			if(!(hasAllValues(temp, firstRow))) {
				if(ContainsZero()) {
					return true;
				}
				return false;
			}
		}
		
		return true;
		
	}
	
	public boolean isSudoku() {
		return isPartialSudoku() && !ContainsZero();
	}
	
	public boolean isValidValue(int iCol, int iRow, int iValue) {
		boolean test = false;
		if(iValue <= iSize && iValue >= 0) {
			if(!(doesElementExist(getColumn(iCol), iValue))) {
				if(!(doesElementExist(getRow(iRow), iValue))) {
					test = true;
				}
			}
		}
		return test;
	}
}