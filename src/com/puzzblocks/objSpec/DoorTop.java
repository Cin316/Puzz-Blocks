package com.puzzblocks.objSpec;

import java.awt.Image;

import com.puzzblocks.GameConstants;
import com.puzzblocks.Main;
import com.puzzblocks.obj.Block;
import com.utilis.game.obj.CollisionBox;

public class DoorTop extends Block {
	
	public static Image art = Main.getArt("Tiles/Door/DoorTop.png");
	public static CollisionBox collisionBox = new CollisionBox(GameConstants.TILE_WIDTH, GameConstants.TILE_HEIGHT, false);
	
	public DoorTop(int x, int y) {
		super(art, collisionBox, x, y);
	}

}
