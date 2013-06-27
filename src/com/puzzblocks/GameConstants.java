package com.puzzblocks;

public class GameConstants {
	
	public static final boolean DEBUG_MODE = true;
	
	public static final String ART_DIRECTORY = "art";
	public static final String LEVELS_DIRECTORY = "data/levels";
	
	public static final int CHARACTER_WIDTH = 64;
	public static final int CHARACTER_HEIGHT = 128;
	
	public static final int TILE_WIDTH = 64;
	public static final int TILE_HEIGHT = 64;
	
	public static final String FPS_FONT = "Times New Roman";
	public static final int FPS_FONT_SIZE = 20;
	
	public static final int DARKENING_VALUE = 10;
	
	public static final int MAX_FPS = 120;
	
	public static final float GRAVITY = 128F; //Measured in pixels per second.
	public static final float CHARACTER_MOVEMENT = 96F; //Measured in pixels per second.
	public static final float JUMP_TIME = 1F; //Measured in seconds.
	public static final float JUMP_MAX_HEIGHT = TILE_HEIGHT * 1.25F; //Measured in pixels.
	
}
