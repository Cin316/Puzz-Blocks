package com.puzzblocks;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.puzzblocks.gui.WorldCanvas;
import com.utilis.game.gui.Window;

public class ResizeListener extends ComponentAdapter {
	
	protected WorldCanvas world;
	protected Window parent;
	
	public ResizeListener(WorldCanvas w, Window parent) {
		world = w;
		this.parent = parent;
		Main.debugPrint("GRAPHICS", "ResizeListener was successfully created.");
	}
	
	public void componentResized(ComponentEvent e) {
		world.setSize(parent.getSize());
		world.centerAlignPlayer();
	}

}
