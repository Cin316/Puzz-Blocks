package com.puzzblocks.objSpec;

import java.awt.Image;

import com.puzzblocks.GameConstants;
import com.puzzblocks.Main;
import com.puzzblocks.obj.Block;
import com.puzzblocks.obj.Player;
import com.utilis.game.obj.CollisionBox;

public class MetalBlock extends Block{
	
	public static Image art = Main.getArt("Tiles/Metal Block/Metal Block.png");
	public static CollisionBox collisionBox = new CollisionBox(GameConstants.TILE_WIDTH, GameConstants.TILE_HEIGHT, true);
	
	public MetalBlock(int x, int y){
		super(art, collisionBox, x, y);
	}
	
	public void onPlayerWalk(Player p){
		p.setCanJump(true);
		p.setWalkSpeed(1.0F);
	}
	
}
