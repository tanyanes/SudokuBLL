package pkgGame;

import java.util.Random;
import java.lang.reflect.Array;
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
	
	@Override
	public boolean hasDuplicates() {
		if(super.hasDuplicates()) {
			return true;
		}
		for (int k = 0; k< this.getPuzzle().length; k++) {
			if(super.hasDuplicates(getRegion(k))) {
				return true;
			}
		}
		return false;
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
		this.setbIgnoreZero(true);
		
		if (hasDuplicates()){
			return false;
		}
		if (!ContainsZero()) {
			return false;
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
	
	public int getRegionNbr(int iCol, int iRow) {
		return (iCol/iSqrtSize)*iSqrtSize +(iRow/iSqrtSize);
	}
	
	public void PrintPuzzle() {
		System.out.println(getPuzzle());
	}
	
	private void FillDiagonalRegions() {
		if (iSqrtSize == 3) {
			int[] diagonalIndeces= {0,4,8};
			for (int i=0; i< 3; i++) {
				SetRegion(diagonalIndeces[i]);
				ShuffleRegion(diagonalIndeces[i]);
			}
		}
		if (iSqrtSize == 2) {
			int[] diagonalIndeces= {0,3};
			for (int i = 0; i< 2; i++) {
				SetRegion(diagonalIndeces[i]);
				ShuffleRegion(diagonalIndeces[i]);
			}
		}
	}
	
	private void SetRegion(int r) { //use a counter
		int n = 1;
		for (int i = ((r % iSqrtSize) * iSqrtSize); i < i + iSqrtSize; i++) {
			for (int j= (r / iSqrtSize) * iSqrtSize; j < j+ iSqrtSize; j++) {
				this.getPuzzle()[i][j] = n;
				n++;
			}
		}	
	}
	
	private void ShuffleRegion(int r) {
		int[] myReg = getRegion(r);
		ShuffleArray(myReg);
	}
	
	//public void testShuffleRegion(int r) {
		//ShuffleRegion(r);
	//}
	
	//public void testShuffleArray(int[] r) {
		//ShuffleArray(r);
	//}
	
	private void ShuffleArray(int[] arr) {
		Random rand = new Random();
		int count = arr.length;
		int temp;
		int rand_int;
		
		for(int j = count; j > 1; j--) {
			temp = arr[j-1];
			rand_int = rand.nextInt(j);
			arr[j-1] = arr[rand_int];
			arr[rand_int] = temp;
		}
	}
	
}