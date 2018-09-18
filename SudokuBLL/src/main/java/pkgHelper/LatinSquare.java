package pkgHelper;

import java.util.Arrays;

public class LatinSquare {
	public LatinSquare(int[][] latinSquare) {
		super();
		this.latinSquare = latinSquare;
	}

	private int[][] latinSquare;

	public int[][] getLatinSquare() {
		return latinSquare;
	}

	public void setLatinSquare(int[][] latinSquare) {
		this.latinSquare = latinSquare;
	}

	public LatinSquare() {
		latinSquare = null;
	}
	
	public boolean hasDuplicates(int[] arr) {
		int[] nArr=arr;
		if (arr == null) {
			return false;
		}
		for(int i = 0; i <arr.length-2; i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(nArr[i]==nArr[j]) {
					return true;
				}
			}
		}
		return false;
	}
		
	public boolean doesElementExist(int[] arr, int iValue) {
		boolean doesElementExist = false;
		if (arr == null) {
			return false;
		}
		for (int i: arr) {
			if (i==iValue) {
				doesElementExist= true;
				break;
			}
		}
		return doesElementExist;
	}
	public boolean containsZero(int[][] arr) {
		boolean containsZero = false;
		if (arr == null) {
			return false;
		}
		for(int i=0; i<arr.length-1; i++) {
			for(int y=0;y<arr.length-1;y++) {
				if (arr[i][y] == 0) {
					containsZero = true; 
					break;
				}
			}
		}
		return containsZero;
	}
	 public int[] getColumn(int column) {
         int[] array = new int[latinSquare.length];
         for(int j = 0; j < latinSquare.length; j++) {
        	 array[j]= latinSquare[j][column];
         }
         return array;
	 }
	public int[] getRow(int row) {
		int[] array = new int[latinSquare.length];
		array = latinSquare[row];
		return array;
	}
	public boolean hasAllValues(int[] arr1,int[] arr2) {
		if (arr1 == null && arr2 != null) {
			return false;
		}
		if (arr2 == null && arr1 != null) {
			return false;
		}
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		for(int i=0; i<arr1.length; i++) {
			boolean inArray = false;
			for(int j=0; j <arr2.length; j++){
				if (arr1[i] == arr2[j]) {
					inArray = true;
					break;
				}
			}
			if(!inArray) {
				return false;
			}
		}
		return true;
		}
	public boolean isLatinSquare() {
		if (latinSquare == null) {
			return false;
		}
		int sum = LatinSquare.getSum(getRow(0));
		for(int i = 1; i< latinSquare.length; i++) {
			if(sum != LatinSquare.getSum(getRow(i))) {
				return false;
			}
		}
		for(int i = 0; i< latinSquare.length; i++) {
			if(sum != LatinSquare.getSum(getColumn(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static int getSum(int[] arr) {
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return sum;
	}
	
}

