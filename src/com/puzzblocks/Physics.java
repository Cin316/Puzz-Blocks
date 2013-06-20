package com.puzzblocks;

import com.puzzblocks.obj.WorldCanvas;
import com.utilis.game.obj.CollisionGroup.Collision;

public class Physics {
	
	protected static boolean movingLeft = false;
	protected static boolean movingRight = false;
	protected static boolean jumping = false;
	protected static float jumpCompleteness = 0F;
	protected static long lastFrameTime = 0L;
	protected static long currentTime = 0L;
	protected static int jumpBeginningHeight;
	
	public static void doPhysics(WorldCanvas wc){
		
		long deltaTime; //Time between frames, in milliseconds.
		long deltaSeconds;
		int gravDistance;
		int rightDistance;
		int leftDistance;
		int jumpDistance;
		
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
		if(jumping==false){
			gravDistance = (int) (GameConstants.GRAVITY * deltaSeconds); //distance = speed * delta;
			
			wc.getPlayer().moveDown(gravDistance);
			//Check collisions.
			Collision gravCollision = wc.getCollisionGroup().checkCollision();
			if(gravCollision.hasCollided()){
				wc.getPlayer().moveUp(gravDistance);
			}
		}
		//Move right.
		if(movingRight){
			rightDistance = (int) (GameConstants.CHARACTER_MOVEMENT * deltaSeconds); //distance = speed * delta;
			
			wc.getPlayer().moveRight(rightDistance);
			//Check collisions.
			Collision rightCollision = wc.getCollisionGroup().checkCollision();
			if(rightCollision.hasCollided()){
				wc.getPlayer().moveLeft(rightDistance);
			}
		}
		
		//Move left.
		if(movingLeft){
			leftDistance = (int) (GameConstants.CHARACTER_MOVEMENT * deltaSeconds); //distance = speed * delta;
			
			wc.getPlayer().moveLeft(leftDistance);
			//Check collisions.
			Collision leftCollision = wc.getCollisionGroup().checkCollision();
			if(leftCollision.hasCollided()){
				wc.getPlayer().moveRight(leftDistance);
			}
		}
		
		//Jump
		if(jumping){
			
			//If Player can't jump, skip jump
			if(wc.getPlayer().getCanJump()==false){
				jumping = false;
			}else{
				//If jump is just starting...
				if(jumpCompleteness==0F){
					jumpBeginningHeight = wc.getPlayer().getY();
				}
				
				//Calculate how much to increase jumpCompleteness.
				jumpCompleteness += deltaSeconds / GameConstants.JUMP_TIME;
				
				//If jumpCompleteness is too big, round it down.
				if(jumpCompleteness>1F){
					jumpCompleteness = 1F;
				}
				
				
				if(jumpCompleteness<0.5F){ //If in up part of jump...
					
					float x = jumpCompleteness * 2;
					jumpDistance = (int) (x * GameConstants.JUMP_MAX_HEIGHT);
					wc.getPlayer().setHeight(jumpBeginningHeight + jumpDistance);
					
				}else if(jumpCompleteness>=0.5F){ //If in down part of jump...
					float x = (1F - jumpCompleteness) * 2;
					jumpDistance = (int) (x * GameConstants.JUMP_MAX_HEIGHT);
				}
				
				//If jump is complete.
				if(jumpCompleteness==1F){
					jumpCompleteness = 0F;
					jumpBeginningHeight = 0;
					jumping = false;
				}
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

	public static void setJumping(boolean jumping) {
		Physics.jumping = jumping;
	}
	
}
