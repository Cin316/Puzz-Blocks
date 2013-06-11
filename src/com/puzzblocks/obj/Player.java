package com.puzzblocks.obj;

import java.awt.Image;

import com.utilis.game.obj.*;

public class Player extends ScrollingEntity {
	
	protected float walkSpeed = 1.0F;
	protected boolean canJump = true;
	
	public Player(Image i) {
		super(i);
	}
	public Player(CollisionBox c) {
		super(c);
	}
	public Player(int w, int h) {
		super(w, h);
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
