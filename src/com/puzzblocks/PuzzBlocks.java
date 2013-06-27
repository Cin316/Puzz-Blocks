package com.puzzblocks;

import com.puzzblocks.LevelLoader.InvalidLevelFormatException;
import com.puzzblocks.obj.*;
import com.utilis.game.gui.Window;

/**
 * A class use to represent an instance of the Puzz Blocks game.
 * 
 * @author Cin316
 */
public class PuzzBlocks {

	protected WorldCanvas currentWorld;
	protected Window loadedLevelWindow;
	protected Level level;
	protected boolean running = true;
	private final static long desiredDeltaLoop = (1000 * 1000 * 1000)
			/ GameConstants.MAX_FPS;
	private int fps; // fps used for counting.
	protected int FPS; // official fps of last seconds.

	public PuzzBlocks() {

		try {
			level = LevelLoader.loadLevel("1-1");
		} catch (InvalidLevelFormatException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		currentWorld = level.getWorld();

		loadedLevelWindow = new Window(level.getWorld());
		loadedLevelWindow.setTitle(level.getName());
		loadedLevelWindow.setVisible(true);
		loadedLevelWindow.setResizable(false);

		((WorldCanvas) loadedLevelWindow.getCanvas()).setGame(this);

		gameLoop();

	}

	private void gameLoop() {

		long beginLoopTime;
		long endLoopTime;
		long deltaLoop;
		long lastSecond = 0;

		while (running) {
			beginLoopTime = System.nanoTime();

			Physics.doPhysics(currentWorld);
			loadedLevelWindow.repaint();// Render image.
			fps++;

			// Calculate fps.
			if (beginLoopTime >= lastSecond + 1000000000) {

				FPS = fps;
				fps = 0;
				lastSecond = beginLoopTime;
			}

			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;

			if (deltaLoop > desiredDeltaLoop) {
				// Do nothing. We are already late!
			} else {
				try {
					Thread.sleep((desiredDeltaLoop - deltaLoop) / (1000 * 1000));
				} catch (InterruptedException e) {
					// Do nothing.
				}
			}
		}

	}

	/**
	 * Call when quitting the specified instance of the Puzz Blocks game.
	 */
	public void close() {

		loadedLevelWindow.setVisible(false);
		running = false;
		System.exit(0);

	}

	public WorldCanvas getCurrentWorld() {
		return currentWorld;
	}

	public Window getLoadedLevelWindow() {
		return loadedLevelWindow;
	}

	public Level getLevel() {
		return level;
	}

	public int getFPS() {
		return FPS;
	}

	public boolean isRunning() {
		return running;
	}

	public void setCurrentWorld(WorldCanvas currentWorld) {
		this.currentWorld = currentWorld;
	}

	public void setLoadedLevelWindow(Window loadedLevelWindow) {
		this.loadedLevelWindow = loadedLevelWindow;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
