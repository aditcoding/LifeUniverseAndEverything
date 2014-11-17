

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiceMaze {
	
	static int numOfEscapedMice = 0;
	static int timeOut;
	static int currentCellNumber;
	static int exitCellNumber;
	static Map<Integer,Cell> cells = new HashMap<Integer,Cell>();
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			int numOfLinesRead = 0;
			int [] initParams = new int [4];
			String [] strArray = null;
			while (((input = br.readLine()) != null && input.trim().length() != 0)) {
				numOfLinesRead++;
				if(numOfLinesRead <= 4) {
					initParams[numOfLinesRead-1] = Integer.parseInt(input);
				} else {
					strArray = input.split(" ");
					int srcCellNumber = Integer.parseInt(strArray[0]);
					int destCellNumber = Integer.parseInt(strArray[1]);
					int time = Integer.parseInt(strArray[2]);
					Cell srcCell = cells.get(srcCellNumber);
					if(srcCell == null){
						srcCell = new Cell(srcCellNumber, srcCellNumber == initParams[1]);
					}
					Cell destCell = cells.get(destCellNumber);
					if(destCell == null){
						destCell = new Cell(destCellNumber, destCellNumber == initParams[1]);
					} 
					Passage passage = new Passage(time, destCell);
					srcCell.passages.add(passage);
					cells.put(srcCellNumber, srcCell);
					cells.put(destCellNumber,destCell);
					if(numOfLinesRead == 4+initParams[3]){
						break;
					}
				}
			}
			timeOut = initParams[2];
			exitCellNumber = initParams[1];
			if(!cells.containsKey(exitCellNumber)){
				numOfEscapedMice++;
			}
			for(Cell cell : cells.values()){
				currentCellNumber = cell.number;
				if(cell.isExit){
					numOfEscapedMice++;
					cell.hasEscaped = true;
				} else {
					recurseMaze(cell, 0);
				}
			}
			System.out.println(numOfEscapedMice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void recurseMaze(Cell nextCell, int timeElapsed) {
		for(Passage passage : nextCell.passages){
			if(cells.get(currentCellNumber).hasEscaped){
				break;
			}
			timeElapsed += passage.time;
			if(timeElapsed <= timeOut){
				Cell destCell = passage.destCell;
				if(destCell.isExit){
					numOfEscapedMice++;
					cells.get(currentCellNumber).hasEscaped = true;
				} else {
					recurseMaze(destCell, timeElapsed);
					timeElapsed -= passage.time;
				} 
			} else {
				timeElapsed -= passage.time;
			}
		}
	}

	static class Cell {
		int number;
		boolean isExit;
		boolean hasEscaped = false;
		List<Passage> passages = new ArrayList<Passage>();
		
		Cell(int number, boolean isExit){
			this.number = number;
			this.isExit = isExit;
		}
		
		@Override
		public boolean equals(Object arg0) {
			if (arg0 == this) {
	            return true;
	        }
	        if (!(arg0 instanceof Cell)) {
	            return false;
	        }
			return number == ((Cell)arg0).number;
		}
		
		@Override
		public int hashCode() {
			return number;
		}
	}
	
	static class Passage {
		int time;
		Cell destCell;
		
		Passage(int time, Cell destCell){
			this.time = time;
			this.destCell = destCell;
		}
	}

}
