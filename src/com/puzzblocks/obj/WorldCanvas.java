package com.puzzblocks.obj;

import com.utilis.game.gui.*;
import com.utilis.game.obj.*;

public class WorldCanvas extends ScrollingCanvas {

	private static final long serialVersionUID = 1L;
	
	public WorldCanvas(Screen s) {
		super(s);
	}
	public WorldCanvas(int xTiles, int yTiles, int tWidth, int tHeight) {
		super(xTiles, yTiles, tWidth, tHeight);
	}

}
