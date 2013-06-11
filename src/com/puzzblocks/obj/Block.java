package com.puzzblocks.obj;

import java.awt.Image;

import com.utilis.game.obj.*;

public class Block extends Tile {
	
	public static Image art;
	public static CollisionBox collisionBox;
	
	public Block(CollisionBox c, int x, int y) {
		super(c, x, y);
	}
	public Block(int width, int height, int x, int y) {
		super(width, height, x, y);
	}
	public Block(Image i, CollisionBox c, int x, int y) {
		super(i, c, x, y);
	}
	
	public void onPlayerWalk(Player p){
		
	}
	
}
