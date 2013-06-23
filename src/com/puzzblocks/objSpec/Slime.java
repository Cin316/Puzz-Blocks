package com.puzzblocks.objSpec;

import java.awt.Image;

import com.puzzblocks.GameConstants;
import com.puzzblocks.Main;
import com.puzzblocks.obj.Block;
import com.puzzblocks.obj.Player;
import com.utilis.game.obj.CollisionBox;

public class Slime extends Block {
	
	public static Image art = Main.getArt("Tiles/Slime/Slime.gif");
	public static CollisionBox collisionBox = new CollisionBox(GameConstants.TILE_WIDTH, GameConstants.TILE_HEIGHT, true);
	
	public Slime(int x, int y){
		super(art, collisionBox, x, y);
	}
	
	public void onPlayerWalk(Player p){
		p.setCanJump(false);
		p.setWalkSpeed(0.4F);
	}
	
}
