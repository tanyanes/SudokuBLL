package pkgGame;

import java.lang.reflect.Array;
import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;
import javassist.bytecode.stackmap.TypeData.ArrayElement;
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
	public void PrintPuzzle() {
		System.out.println(getPuzzle());
	}
	
	private void FillDiagonalRegions() {
		if (iSqrtSize == 3) {
			int[] diagonalIndeces= {0,4,8};
			for (int i=0; i< 3; i++) {
				SetRegion(diagonalIndeces[i]);
			}
		}
		if (iSqrtSize == 2) {
			int[] diagonalIndeces= {0,3};
			for (int i = 0; i< 2; i++) {
				SetRegion(diagonalIndeces[i]);
			}
		}
	}
	
	private void SetRegion(int r) {
		Random rand = new Random();
		int max=getRegion(0,0).length;
		int value = rand.nextInt(max);
		for (int i = 0; i < iSize; i++) {
			if () { //dont really know what to write here!!!!!
				getRegion(r)[i] = value;
			}
		}	
	}
	
	private void ShuffleRegion(int r) {
		int[] myReg = getRegion(r);
		ShuffleArray(myReg);
	}
	
	private void ShuffleArray(int[] arr) {
		Random rand = new Random();
		int max=getRegion(0,0).length;
		int value = rand.nextInt(max);
		int[] emptyList = new int[max];
		for (int i=0; i< max; i++) {
			emptyList[i]=i;
		}
		
		for (int k = 0; k< arr.length; k++) {
			arr[k]= arr[value];
			emptyList = ArrayUtils.removeElement(emptyList, emptyList[value]);
		}
	}
	
}