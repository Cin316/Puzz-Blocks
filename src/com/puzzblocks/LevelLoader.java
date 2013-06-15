package com.puzzblocks;

import java.io.File;
import com.utilis.game.obj.*;

import com.puzzblocks.obj.*;
import com.puzzblocks.objSpec.*;

public class LevelLoader {
	
	public static Level loadLevel(String s) throws InvalidLevelFormatException{
		String dirPath;
		String levelConfigContents;
		String levelDataContents;
		String[] levelConfigLines;
		String[] levelDataLines;
		String[][] levelDataMatrix;
		File levelConfig;
		File levelData;
		Level level;
		String levelName = "";
		String levelUnlock = "";
		int height = 0;
		int width = 0;
		WorldCanvas world;
		
		//Get root Puzz Blocks directory.
		dirPath = Main.getPuzzBlocksDirectory();
		
		//Finds path to Levels directory.
		dirPath += "/";
		dirPath += GameConstants.LEVELS_DIRECTORY;
		dirPath += "/";
		
		//Finds path to specified level
		dirPath += s;
		
		//Create Files to represent all level Files.
		levelConfig = new File(dirPath + "/level.cfg");
		levelData = new File(dirPath + "/level.data");
		
		//Reads level.cfg and level.data files.
		levelConfigContents = Main.readTextFromFile(levelConfig);
		levelDataContents = Main.readTextFromFile(levelData);
		
		//Separates the lines of the level.cfg.
		levelConfigLines = levelConfigContents.split("\n");
		
		//Evaluate properties of level.cfg.
		for(String line : levelConfigLines){
			
			String[] lineValues;
			String value;
			
			//Checks not make sure line is not a comment (comments start with "#").
			if(line.charAt(0) != '#'){
				
				//Separates the key and value (ex. "key:value") of the current line.
				lineValues = line.split(":");
				String key = lineValues[0];
				if(lineValues.length == 2){ //Checks if there actually is a key and a value sperated by a semicolon ";".
					value = lineValues[1];
				}else{ //If not throws an exception.
					throw new InvalidLevelFormatException(s);
				}
				
				//Evaluate key and do appropriate action with value.
				if(key.equals("level-name")){
					levelName = value;
				}else if(key.equals("level-unlock")){ //Not required.
					levelUnlock = value;
				}else if(key.equals("height")){
					height = Integer.parseInt(value);
				}else if(key.equals("width")){
					width = Integer.parseInt(value);
				}else{
					throw new InvalidLevelFormatException(s, "containing the key/value pair \"" + line + "\"." );
				}
				
			}
			
		}
		
		//Checks if any required value is not assigned.
		if( !(height != 0 && width != 0 && levelName != "") ){
			throw new InvalidLevelFormatException(s);
		}
		
		//Separate levelDataContents into a 2D array.
		levelDataMatrix = new String[height][width];
		
		//Separate into lines.
		levelDataLines = levelDataContents.split("\n");
		
		int lineNum = 0;
		//Separate lines into characters.
		for(String line : levelDataLines){
			
			String lineArray[];
			String lineArrayFixed[];
			
			//Split the array.
			lineArray = line.split("");
			
			//Remove first element, which is "";
			lineArrayFixed = new String[lineArray.length-1];
			
			for(int i = 0; i<lineArrayFixed.length; i++){
				lineArrayFixed[i] = lineArray [i+1];
			}
			
			//Adds fixed lineArray to levelDataMatrix.
			levelDataMatrix[lineNum] = lineArrayFixed;
			lineNum++;
		}
		
		//Rotates levelDataMatrix
	    int w = levelDataMatrix.length;
	    int h = levelDataMatrix[0].length;
	    String[][] ret = new String[h][w];
	    for (int i = 0; i < h; ++i) {
	        for (int j = 0; j < w; ++j) {
	            ret[i][j] = levelDataMatrix[w - j - 1][i];
	        }
	    }
	    levelDataMatrix = ret;
	    
	    //Flips levelDataMatrix vertically.
	    String[][] transformedArray = new String[levelDataMatrix.length][levelDataMatrix[0].length];
		for(int i = 0; i<levelDataMatrix.length; i++ ){
			int index = 0;
			for ( int j = levelDataMatrix[0].length - 1; j > -1; j-- )
			{
				transformedArray[i][index] = levelDataMatrix[i][j];
				index++;
			}
		}
		levelDataMatrix = transformedArray;
	    
		//Creates a World Canvas with specified properties.
		world = new WorldCanvas(levelDataMatrix.length, levelDataMatrix[0].length);
		
		//Iterate through each character in matrix and add it to WorldCanvas's screen.
		for(int x = 0; x<levelDataMatrix.length; x++){
			for(int y = 0; y<levelDataMatrix[0].length; y++){
				
				String lineData = levelDataMatrix[x][y];
				
				if( lineData.equals("M") ){
					Tile t = new MetalBlock(x*GameConstants.TILE_WIDTH, y*GameConstants.TILE_HEIGHT);
					world.getScreen().setTile(x, y, t);
				}else if( lineData.equals("G") ){
					Tile t = new Slime(x*GameConstants.TILE_WIDTH, y*GameConstants.TILE_HEIGHT);
					world.getScreen().setTile(x, y, t);
				}else if( lineData.equals("S") ){
					Player player = new Player(GameConstants.CHARACTER_WIDTH, GameConstants.CHARACTER_HEIGHT);
					player.setPos(x*GameConstants.TILE_WIDTH, y*GameConstants.TILE_HEIGHT);
					player.setRealX(x*GameConstants.TILE_WIDTH);
					player.setRealY(y*GameConstants.TILE_HEIGHT);
					world.setCenterEntity(player);
					world.add(player);
				}else if( lineData.equals("E") ){
					//Tile t = new Slime(x*GameConstants.TILE_WIDTH, y*GameConstants.TILE_HEIGHT);
					//world.getScreen().setTile(x, y, t);
				}else if( lineData.equals("o") ){
					//Tile t = new Slime(x*GameConstants.TILE_WIDTH, y*GameConstants.TILE_HEIGHT);
					//world.getScreen().setTile(x, y, t);
				}else if( lineData.equals(" ") ){
					//This is nothing, air.
				}else{ //Add more here.
					throw new InvalidLevelFormatException(s, "containing the character \"" + lineData + "\" in the level.data file");
				}
				
			}
		}
		
		//Created new level with gathered informaton.
		if(levelUnlock == ""){
			level = new Level(levelName, world);
		}else{
			level = new Level(levelName, levelUnlock, world);
		}
		
		return level;
	}
	
	
	/**
	 * Class to represent an error when loading a level.  This should be thrown when a level that is being read is invalid.
	 * @author Cin316
	 */
	public static class InvalidLevelFormatException extends Exception{
		
		private static final long serialVersionUID = 1L;
		
		public InvalidLevelFormatException(String level){
			super("Level \"" + level + "\" is invalid.");
		}
		public InvalidLevelFormatException(String level, String reason){
			super("Level \"" + level + "\" is invalid, due to " + reason + ".");
		}
		
		
	}
	
}
