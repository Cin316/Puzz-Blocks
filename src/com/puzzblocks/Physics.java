package com.puzzblocks;

import com.puzzblocks.obj.WorldCanvas;
import com.utilis.game.obj.CollisionGroup.Collision;

public class Physics {
	
	protected static boolean movingLeft = false;
	protected static boolean movingRight = false;
	protected static float jumpCompleteness = 0F;
	protected static long lastFrameTime = 0L;
	protected static long currentTime = 0L;
	
	public static void doPhysics(WorldCanvas wc){
		
		long deltaTime; //Time between frames, in milliseconds.
		long deltaSeconds;
		int gravDistance;
		int rightDistance;
		int leftDistance;
		
		//Sets currentTime.
		currentTime = System.currentTimeMillis();
		//Checks if lastFrameTime is set, and if not, sets it to current time.
		if(lastFrameTime == 0L){
			lastFrameTime = System.currentTimeMillis();
		}
		
		//Calculates deltaTime.
		deltaTime = currentTime - lastFrameTime;
		deltaSeconds = deltaTime/1000;
		
		//Apply gravity.
		gravDistance = (int) (GameConstants.GRAVITY * deltaSeconds); //distance = speed * delta;
		
		wc.getPlayer().moveDown(gravDistance);
		//Check collisions.
		Collision gravCollision = wc.getCollisionGroup().checkCollision();
		if(gravCollision.hasCollided()){
			wc.getPlayer().moveUp(gravDistance);
		}
		
		//Move right.
		if(movingRight){
			rightDistance = (int) (GameConstants.CHARACTER_MOVEMENT * deltaSeconds); //distance = speed * delta;
			
			wc.getPlayer().moveRight(rightDistance);
			//Check collisions.
			Collision rightCollision = wc.getCollisionGroup().checkCollision();
			if(rightCollision.hasCollided()){
				wc.getPlayer().moveLeft(gravDistance);
			}
		}
		
		//Move left.
		if(movingLeft){
			leftDistance = (int) (GameConstants.CHARACTER_MOVEMENT * deltaSeconds); //distance = speed * delta;
			
			wc.getPlayer().moveLeft(leftDistance);
			//Check collisions.
			Collision leftCollision = wc.getCollisionGroup().checkCollision();
			if(leftCollision.hasCollided()){
				wc.getPlayer().moveRight(gravDistance);
			}
		}
		
		//Sets time of lastFrame.
		lastFrameTime = System.currentTimeMillis();
	}

	public static void setMovingLeft(boolean movingLeft) {
		Physics.movingLeft = movingLeft;
	}

	public static void setMovingRight(boolean movingRight) {
		Physics.movingRight = movingRight;
	}
	
	
	
}
