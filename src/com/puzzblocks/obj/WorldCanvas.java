package com.puzzblocks.obj;

import com.puzzblocks.GameConstants;
import com.utilis.game.gui.*;
import com.utilis.game.obj.*;

public class WorldCanvas extends ScrollingCanvas {

	private static final long serialVersionUID = 1L;
	
	public WorldCanvas(Screen s) {
		super(s);
	}
	public WorldCanvas(int xTiles, int yTiles) {
		super(xTiles, yTiles, GameConstants.TILE_WIDTH, GameConstants.TILE_WIDTH);
	}

}
