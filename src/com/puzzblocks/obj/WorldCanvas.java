package com.puzzblocks.obj;

import com.utilis.game.gui.ScrollingCanvas;
import com.utilis.game.obj.Screen;

public class WorldCanvas extends ScrollingCanvas {

	public WorldCanvas(Screen s) {
		super(s);
	}
	public WorldCanvas(int xTiles, int yTiles, int tWidth, int tHeight) {
		super(xTiles, yTiles, tWidth, tHeight);
	}

}
