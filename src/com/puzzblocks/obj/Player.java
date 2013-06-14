package com.puzzblocks.obj;

import java.awt.Image;

import com.puzzblocks.GameConstants;
import com.puzzblocks.Main;
import com.utilis.game.obj.*;

public class Player extends ScrollingEntity {
	
	public static Image art = Main.getArt("Character/Character.png");
	public static CollisionBox collisionBox = new CollisionBox(GameConstants.CHARACTER_WIDTH, GameConstants.CHARACTER_HEIGHT, true);
	
	protected float walkSpeed = 1.0F;
	protected boolean canJump = true;
	
	public Player(){
		super(art, collisionBox);
	}
	public Player(Image i) {
		super(i);
		try {
			this.setCollisionBox(collisionBox);
		} catch (CollisionBoxSizeException e) {
			e.printStackTrace();
		}
	}
	public Player(CollisionBox c) {
		super(c);
		this.setImage(art);
	}
	public Player(int w, int h) {
		super(w, h);
		this.setImage(art);
		try {
			this.setCollisionBox(collisionBox);
		} catch (CollisionBoxSizeException e) {
			e.printStackTrace();
		}
	}
	public Player(Image i, CollisionBox c) {
		super(i, c);
	}
	
	public void setWalkSpeed(float f){
		this.walkSpeed = f;
	}
	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}
	
	public float getWalkSpeed(){
		return walkSpeed;
	}
	public boolean getCanJump() {
		return canJump;
	}
	
}
