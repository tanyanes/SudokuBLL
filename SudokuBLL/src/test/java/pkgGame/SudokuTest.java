package pkgGame;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class SudokuTest {
	
		@Test
		public void testGetRegionInt_test1() throws Exception {
			int[][] test = {
					{7,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			int[] ret = temp.getRegion(0);
			int[] result = {7,9,1,6,8,5,3,2,4};
			assertTrue(Arrays.equals(ret, result));
		}
		
		@Test
		public void testGetRegionInt_test2() throws Exception {
			int[][] test = {
					{7,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			int[] ret = temp.getRegion(8);
			int[] result = {1,4,2,6,9,7,8,5,3};
			assertTrue(Arrays.equals(ret, result));
		}

		@Test
		public void testGetRegionIntInt_test1() throws Exception {
			int[][] test = {
					{7,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			int[] ret = temp.getRegion(0, 0);
			int[] result = {7,9,1,6,8,5,3,2,4};
			assertTrue(Arrays.equals(ret, result));
		}
		
		@Test
		public void testGetRegionIntInt_test2() throws Exception {
			int[][] test = {
					{7,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			int[] ret = temp.getRegion(3, 4);
			int[] result = {3,1,5,4,6,2,9,8,7};
			assertTrue(Arrays.equals(ret, result));
		}

		@Test
		public void testIsPartialSudoku_test1() throws Exception{ //baseline test
			int[][] test = {
					{7,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			assertFalse(temp.isPartialSudoku());
		}
		
		@Test
		public void testIsPartialSudoku_test2() throws Exception { //test with 0
			int[][] test = {
					{7,9,1,8,5,6,3,2,4},
					{6,0,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,0,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			assertTrue(temp.isPartialSudoku());
		}
		
		@Test
		public void testIsPartialSudoku_test3() throws Exception { // test for duplicate
			int[][] test = {
					{7,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,7,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			assertFalse(temp.isPartialSudoku());
		}
		

		@Test
		public void testIsSudoku_test1() throws Exception {
			int[][] test = {
					{7,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			assertFalse(temp.isSudoku());
			assertFalse(temp.isPartialSudoku());
		}
		
		@Test
		public void testIsSudoku_test2() throws Exception {
			int[][] test = {
					{0,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			assertFalse(temp.isSudoku());
		}
		
		@Test
		public void testIsSudoku_test3() throws Exception {
			int[][] test = {
					{7,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,7,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			assertFalse(temp.isSudoku());
		}

		@Test
		public void testIsValidValue_test1() throws Exception {
			int[][] test = {
					{0,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			assertTrue(temp.isValidValue(0, 0, 7));
		}
		
		@Test
		public void testIsValidValue_test2() throws Exception {
			int[][] test = {
					{0,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
			};
			Sudoku temp = new Sudoku(test);
			assertFalse(temp.isValidValue(0, 0, 6));
			
		}
		
		@Test
		public void testGetRegionNbr() throws Exception{
			int[][] test = {
					{0,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
		};
		Sudoku temp = new Sudoku(test);
		assertTrue(temp.getRegionNbr(1, 1)==0);
}
		@Test
		public void testGetRegionNbr2() throws Exception{
			int[][] test = {
					{0,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
		};
		Sudoku temp = new Sudoku(test);
		assertTrue(temp.getRegionNbr(7, 7)==8);
}
		@Test
		public void testGetRegionNbr3() throws Exception{
			int[][] test = {
					{0,9,1,8,5,6,3,2,4},
					{6,8,5,2,4,3,7,1,9},
					{3,2,4,1,7,9,5,8,6},
					{9,7,2,3,1,5,4,6,8},
					{8,5,3,4,6,2,9,7,1},
					{1,4,6,9,8,7,2,3,5},
					{5,6,9,7,3,8,1,4,2},
					{4,3,8,5,2,1,6,9,7},
					{2,1,7,6,9,4,8,5,3}
		};
		Sudoku temp = new Sudoku(test);
		assertFalse(temp.getRegionNbr(1, 1)==5);
		}
		//cannot test private methods
		//didn't learn array reflect
		//shuffle changes a copied array, does not modify the array from LatinSquare
		//Java does pass by value for primitives, cannot fix this without help from professor
		}
