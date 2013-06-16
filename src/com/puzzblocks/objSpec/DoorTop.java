package com.puzzblocks.objSpec;

import java.awt.Image;

import com.puzzblocks.GameConstants;
import com.puzzblocks.Main;
import com.utilis.game.obj.CollisionBox;
import com.utilis.game.obj.Tile;

public class DoorTop extends Tile {
	
	public static Image art = Main.getArt("Tiles/Door/DoorTop.png");
	public static CollisionBox collisionBox = new CollisionBox(GameConstants.TILE_WIDTH, GameConstants.TILE_HEIGHT, false);
	
	public DoorTop(int x, int y) {
		super(art, collisionBox, x, y);
	}

}
