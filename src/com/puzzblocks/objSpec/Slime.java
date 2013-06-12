package com.puzzblocks.objSpec;

import java.awt.Image;

import com.puzzblocks.GameConstants;
import com.puzzblocks.Main;
import com.puzzblocks.obj.Player;
import com.utilis.game.obj.CollisionBox;
import com.utilis.game.obj.Tile;

public class Slime extends Tile {
	
	public static Image art = Main.getArt("Tiles/Metal Block/Slime.gif");
	public static CollisionBox collisionBox = new CollisionBox(GameConstants.TILE_WIDTH, GameConstants.TILE_HEIGHT, true);
	
	public Slime(int x, int y){
		super(art, collisionBox, x, y);
	}
	
	public void onPlayerWalk(Player p){
		p.setCanJump(false);
		p.setWalkSpeed(0.4F);
	}
	
}
