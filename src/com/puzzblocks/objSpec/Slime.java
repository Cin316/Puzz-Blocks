package com.puzzblocks.objSpec;

import java.awt.Image;

import com.utilis.game.obj.CollisionBox;
import com.utilis.game.obj.Tile;

public class Slime extends Tile {
	
	public Slime(CollisionBox c, int x, int y) {
		super(c, x, y);
	}
	public Slime(int width, int height, int x, int y) {
		super(width, height, x, y);
	}
	public Slime(Image i, CollisionBox c, int x, int y) {
		super(i, c, x, y);
	}
	
}
