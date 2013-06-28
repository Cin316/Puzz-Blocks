package com.puzzblocks.obj;

import com.puzzblocks.gui.WorldCanvas;

public class Level {
	
	String name;
	String levelUnlockName;
	WorldCanvas world;
	
	public Level(String name, WorldCanvas world){
		this.name = name;
		this.world = world;
	}
	public Level(String name, String nextLevel, WorldCanvas world){
		this.name = name;
		this.levelUnlockName = nextLevel;
		this.world = world;
	}
	
	public String getName() {
		return name;
	}
	public String getLevelUnlockName() {
		return levelUnlockName;
	}
	public WorldCanvas getWorld() {
		return world;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLevelUnlockName(String levelUnlockName) {
		this.levelUnlockName = levelUnlockName;
	}
	public void setWorld(WorldCanvas world) {
		this.world = world;
	}
	
}
