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
		}
	}
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==GameConstants.KEY_LEFT){
			Physics.setMovingLeft(false);
		}else if(e.getKeyCode()==GameConstants.KEY_RIGHT){
			Physics.setMovingRight(false);
		}
	}

}
