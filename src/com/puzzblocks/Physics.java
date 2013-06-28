package com.puzzblocks;

import com.puzzblocks.gui.WorldCanvas;
import com.puzzblocks.obj.Player;
import com.utilis.game.obj.CollisionGroup.Collision;

public class Physics {
	
	protected static boolean movingLeft = false;
	protected static boolean movingRight = false;
	protected static boolean movingUp = false;
	protected static boolean movingDown = false;
	protected static boolean flyEnabled = false;
	protected static boolean jumping = false;
	protected static float jumpCompleteness = 0F;
	protected static long lastFrameTime = 0L;
	protected static long currentTime = 0L;
	protected static int jumpBeginningHeight;
	protected static int jumpPreviousFrameHeight = 0;
	
	public static void doPhysics(WorldCanvas wc){
		
		long deltaTime; //Time between frames, in nanoseconds.
		double deltaSeconds;
		int gravDistance;
		int rightDistance;
		int leftDistance;
		int jumpDistance;
		int upDistance;
		int downDistance;
		
		//Sets currentTime.
		currentTime = System.nanoTime();
		//Checks if lastFrameTime is set, and if not, sets it to current time.
		if(lastFrameTime == 0L){
			lastFrameTime = currentTime;
		}
		
		//Calculates deltaTime.
		deltaTime = currentTime - lastFrameTime;
		deltaSeconds = deltaTime/1000000000D;
		
		//Updates collisionGroup.
		wc.updateCollisionGroup();
		
		//Apply gravity.
		if(jumping==false&&flyEnabled==false){
			gravDistance = (int) (GameConstants.GRAVITY * deltaSeconds); //distance = speed * delta;
						
			Player ghostPlayer = new Player();
			Player player = wc.getPlayer();
			ghostPlayer.setPos(player.getX(), player.getY());
			
			ghostPlayer.moveDown(gravDistance);
			
			//Check collisions.
			Collision gravCollision = wc.getCollisionGroupFromCollider(ghostPlayer).checkCollision();
			if( !gravCollision.hasCollided() ){
				player.moveDown(gravDistance);
			}
		}
		//Move right.
		if(movingRight){
			rightDistance = (int) (GameConstants.CHARACTER_MOVEMENT * deltaSeconds); //distance = speed * delta;
			
			Player ghostPlayer = new Player();
			Player player = wc.getPlayer();
			ghostPlayer.setPos(player.getX(), player.getY());
			
			ghostPlayer.moveRight(rightDistance);
			
			//Check collisions.
			Collision gravCollision = wc.getCollisionGroupFromCollider(ghostPlayer).checkCollision();
			if( !gravCollision.hasCollided() ){
				player.moveRight(rightDistance);
			}
		}
		
		//Move left.
		if(movingLeft){
			leftDistance = (int) (GameConstants.CHARACTER_MOVEMENT * deltaSeconds); //distance = speed * delta;
			
			Player ghostPlayer = new Player();
			Player player = wc.getPlayer();
			ghostPlayer.setPos(player.getX(), player.getY());
			
			ghostPlayer.moveLeft(leftDistance);
			
			//Check collisions.
			Collision gravCollision = wc.getCollisionGroupFromCollider(ghostPlayer).checkCollision();
			if( !gravCollision.hasCollided() ){
				player.moveLeft(leftDistance);
			}
		}
		
		//Move up.
		if(movingUp&&flyEnabled){
			upDistance = (int) (GameConstants.CHARACTER_MOVEMENT * deltaSeconds); //distance = speed * delta;
			
			Player ghostPlayer = new Player();
			Player player = wc.getPlayer();
			ghostPlayer.setPos(player.getX(), player.getY());
			
			ghostPlayer.moveUp(upDistance);
			
			//Check collisions.
			Collision gravCollision = wc.getCollisionGroupFromCollider(ghostPlayer).checkCollision();
			if( !gravCollision.hasCollided() ){
				player.moveUp(upDistance);
			}
		}
		
		//Move down.
		if(movingDown&&flyEnabled){
			downDistance = (int) (GameConstants.CHARACTER_MOVEMENT * deltaSeconds); //distance = speed * delta;
			
			Player ghostPlayer = new Player();
			Player player = wc.getPlayer();
			ghostPlayer.setPos(player.getX(), player.getY());
			
			ghostPlayer.moveDown(downDistance);
			
			//Check collisions.
			Collision gravCollision = wc.getCollisionGroupFromCollider(ghostPlayer).checkCollision();
			if( !gravCollision.hasCollided() ){
				player.moveDown(downDistance);
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
					jumpPreviousFrameHeight = jumpBeginningHeight;
				}
				
				//Calculate how much to increase jumpCompleteness.
				jumpCompleteness += deltaSeconds / GameConstants.JUMP_TIME;
				
				//If jumpCompleteness is too big, round it down.
				if(jumpCompleteness>1F){
					jumpCompleteness = 1F;
				}
				
				//Create ghost player for Physics calculations.
				Player ghostPlayer = new Player();
				Player player = wc.getPlayer();
				ghostPlayer.setPos(player.getX(), player.getY());
				
				if(jumpCompleteness<0.5F){ //If in up part of jump...
					
					float x = jumpCompleteness * 2;
					jumpDistance = (int) (x * GameConstants.JUMP_MAX_HEIGHT);
					ghostPlayer.setHeight(jumpBeginningHeight + jumpDistance);
					
				}else if(jumpCompleteness>=0.5F){ //If in down part of jump...
					float x = (1F - jumpCompleteness) * 2;
					jumpDistance = (int) (x * GameConstants.JUMP_MAX_HEIGHT);
					ghostPlayer.setHeight(jumpBeginningHeight + jumpDistance);
				}
				
				//If jump is complete, reset all variables.
				if(jumpCompleteness==1F){
					jumpCompleteness = 0F;
					jumpBeginningHeight = 0;
					jumping = false;
				}
				
				//Check collisions.
				Collision jumpCollision = wc.getCollisionGroupFromCollider(ghostPlayer).checkCollision();
				if(jumpCollision.hasCollided()){
					wc.getPlayer().setHeight(jumpPreviousFrameHeight);
					jumping = false;
				}
				jumpPreviousFrameHeight = wc.getPlayer().getY();
				
			}
			
		}
		
		//Does final collision check to see if there are issues.
		Collision c = wc.getCollisionGroup().checkCollision();
		if(c.hasCollided()){
			Main.debugPrint("PHYSICS", "Error!  Unknown collision detected!");
		}
		
		//Sets time of lastFrame.
		lastFrameTime = System.nanoTime();
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
	public static void setMovingUp(boolean movingUp) {
		Physics.movingUp = movingUp;
	}
	public static void setMovingDown(boolean movingDown) {
		Physics.movingDown = movingDown;
	}
	public static void setFlyEnabled(boolean flyEnabled) {
		Physics.flyEnabled = flyEnabled;
	}

	public static boolean isFlyEnabled() {
		return flyEnabled;
	}
	
}
