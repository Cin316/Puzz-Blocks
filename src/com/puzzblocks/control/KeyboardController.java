package com.puzzblocks.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.puzzblocks.GameConstants;
import com.puzzblocks.Main;
import com.puzzblocks.Physics;
import com.puzzblocks.PuzzBlocks;

public class KeyboardController extends KeyAdapter {
	
	protected PuzzBlocks game;
	
	public KeyboardController(PuzzBlocks pb) {
		game = pb;
		Main.debugPrint("KEYBOARD", "KeyboardController was successfully created.");
		
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==GameConstants.KEY_LEFT){
			Physics.setMovingLeft(true);
		}else if(e.getKeyCode()==GameConstants.KEY_RIGHT){
			Physics.setMovingRight(true);
		}else if(e.getKeyCode()==GameConstants.KEY_JUMP){
			Physics.setJumping(true);
		}else if(e.getKeyCode()==GameConstants.KEY_UP){
			Physics.setMovingUp(true);
		}else if(e.getKeyCode()==GameConstants.KEY_DOWN){
			Physics.setMovingDown(true);
		}else if(e.getKeyCode()==GameConstants.KEY_FLY_TOGGLE&&GameConstants.DEBUG_MODE){
			if(Physics.isFlyEnabled()){
				Physics.setFlyEnabled(false);
				Main.debugPrint("PHYSICS", "Flying is now disabled.");
			}else{
				Physics.setFlyEnabled(true);
				Main.debugPrint("PHYSICS", "Flying is now enabled.");
			}
		}
	}
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==GameConstants.KEY_LEFT){
			Physics.setMovingLeft(false);
		}else if(e.getKeyCode()==GameConstants.KEY_RIGHT){
			Physics.setMovingRight(false);
		}else if(e.getKeyCode()==GameConstants.KEY_UP){
			Physics.setMovingUp(false);
		}else if(e.getKeyCode()==GameConstants.KEY_DOWN){
			Physics.setMovingDown(false);
		}
	}

}
