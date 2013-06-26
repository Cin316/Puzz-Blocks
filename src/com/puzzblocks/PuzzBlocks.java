package com.puzzblocks;

import com.puzzblocks.LevelLoader.InvalidLevelFormatException;
import com.puzzblocks.obj.*;
import com.utilis.Utilis;
import com.utilis.game.gui.Window;

/**
 * A class use to represent an instance of the Puzz Blocks game.
 * @author Cin316
 */
public class PuzzBlocks {
	
	protected WorldCanvas currentWorld;
	protected Window loadedLevelWindow;
	protected Level level;
	private long desiredFPS = 60;
	private long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
	protected boolean running = true;
	
	public PuzzBlocks(){
		
		try {
			level = LevelLoader.loadLevel("1-1");
		} catch (InvalidLevelFormatException e) {
			e.printStackTrace();
		}
		
		loadedLevelWindow =  new Window(level.getWorld());
		loadedLevelWindow.setVisible(true);
		
		animate();
		
	}
	
	private void animate(){
		
		long beginLoopTime;
		long endLoopTime;
		long deltaLoop;
		long lastSecond = 0;
		int fps = 0;
		
		while(running){
			beginLoopTime = System.nanoTime();
			
			Physics.doPhysics(currentWorld);
			loadedLevelWindow.repaint(); //Render
			fps++;
			
			//Calculate fps.
			if(beginLoopTime >= lastSecond + 1000000000){
				
				System.out.println(fps);
				fps = 0;
				lastSecond = beginLoopTime;
			}
			
			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;
			
				if(deltaLoop > desiredDeltaLoop){
					//Do nothing. We are already late!
				}else{
					try{
						Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
					}catch(InterruptedException e){
						//Do nothing.
					}
				}
		}
		
	}
	
	/**
	 * Call when quitting the specified instance of the Puzz Blocks game.
	 */
	public void close(){
		
		loadedLevelWindow.setVisible(false);
		running = false;
		System.exit(0);
		
	}
	
}
