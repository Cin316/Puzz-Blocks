package com.puzzblocks;

import com.puzzblocks.LevelLoader.InvalidLevelFormatException;
import com.puzzblocks.obj.*;
import com.utilis.game.gui.Window;

/**
 * A class use to represent an instance of the Puzz Blocks game.
 * @author Cin316
 */
public class PuzzBlocks {
	
	WorldCanvas currentWorld;
	Window loadedLevelWindow;
	Level level;
	
	public PuzzBlocks(){
		
		try {
			level = LevelLoader.loadLevel("Cool Art");
		} catch (InvalidLevelFormatException e) {
			e.printStackTrace();
		}
		
		loadedLevelWindow =  new Window(level.getWorld());
		loadedLevelWindow.setVisible(true);
		
	}
	
	/**
	 * Call when quitting the specified instance of the Puzz Blocks game.
	 */
	public void close(){
		
		loadedLevelWindow.setVisible(false);
		System.exit(0);
		
	}
	
}
