package com.puzzblocks;

import com.puzzblocks.obj.*;
import com.utilis.game.gui.Window;
import com.utilis.game.obj.CollisionBox;

/**
 * A class use to represent an instance of the Puzz Blocks game.
 * @author Cin316
 */
public class PuzzBlocks {
	
	Player player;
	WorldCanvas currentWorld;
	Window puzzBlocksWindow;
	
	public PuzzBlocks(){
		
		player = new Player(Main.getArt("Character/Character.png"), new CollisionBox(GameConstants.CHARACTER_WIDTH, GameConstants.CHARACTER_HEIGHT, true));
		currentWorld = new WorldCanvas(5, 5, GameConstants.TILE_WIDTH, GameConstants.TILE_HEIGHT);
		currentWorld.setCenterEntity(player);
		currentWorld.add(player);
		puzzBlocksWindow = new Window(currentWorld);
	}
	
	/**
	 * Call when quitting the specified instance of the Puzz Blocks game.
	 */
	public void close(){
		
		puzzBlocksWindow.setVisible(false);
		System.exit(0);
		
	}
	
}
