package com.puzzblocks.obj;

import java.awt.Graphics;

import com.puzzblocks.GameConstants;
import com.utilis.game.gui.*;
import com.utilis.game.obj.*;

public class WorldCanvas extends ScrollingCanvas {

	private static final long serialVersionUID = 1L;
	public Tile backgroundTile;
	
	public WorldCanvas(Screen s) {
		super(s);
	}
	public WorldCanvas(Screen s, Tile t) {
		super(s);
		backgroundTile = t;
	}
	public WorldCanvas(int xTiles, int yTiles) {
		super(xTiles, yTiles, GameConstants.TILE_WIDTH, GameConstants.TILE_WIDTH);
	}
	public WorldCanvas(int xTiles, int yTiles, Tile t) {
		super(xTiles, yTiles, GameConstants.TILE_WIDTH, GameConstants.TILE_WIDTH);
		backgroundTile = t;
	}
	
	public Tile getBackgroundTile() {
		return backgroundTile;
	}
	public void setBackgroundTile(Tile backgroundTile) {
		this.backgroundTile = backgroundTile;
	}
	
	public void draw(Graphics g){
		
		if(backgroundTile != null){
			int width = getWidth();
			int height = getHeight();
			int imageW = backgroundTile.getImage().getWidth(this);
			int imageH = backgroundTile.getImage().getHeight(this);
			
			// Tile the image to fill our area.
			for (int x = 0; x < width; x += imageW){
				for (int y = 0; y < height; y += imageH){
					g.drawImage(backgroundTile.getImage(), x, y, this);
				}
			}
		}
		
		paintTiles(g);
		paintEntities(g);
		paintHUD(g);
		
	}

}
