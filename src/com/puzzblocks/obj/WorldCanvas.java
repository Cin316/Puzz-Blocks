package com.puzzblocks.obj;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.puzzblocks.*;
import com.utilis.game.gui.*;
import com.utilis.game.obj.*;

public class WorldCanvas extends ScrollingCanvas {

	private static final long serialVersionUID = 1L;
	protected CollisionGroup collisionGroup;
	protected Tile backgroundTile;
	protected Image darkenedTileImage;
	protected Player player;
	
	public WorldCanvas(Screen s) {
		super(s);
		updateCollisionGroup();
	}
	public WorldCanvas(Screen s, Tile t) {
		super(s);
		backgroundTile = t;
		if(backgroundTile != null){
			darkenedTileImage = darkenImage( Main.deepCopy( (BufferedImage) backgroundTile.getImage() ) );
		}
		updateCollisionGroup();
	}
	public WorldCanvas(int xTiles, int yTiles) {
		super(xTiles, yTiles, GameConstants.TILE_WIDTH, GameConstants.TILE_WIDTH);
		updateCollisionGroup();
	}
	public WorldCanvas(int xTiles, int yTiles, Tile t) {
		super(xTiles, yTiles, GameConstants.TILE_WIDTH, GameConstants.TILE_WIDTH);
		backgroundTile = t;
		if(backgroundTile != null){
			darkenedTileImage = darkenImage( Main.deepCopy( (BufferedImage) backgroundTile.getImage() ) );
		}
		updateCollisionGroup();
	}
	public void updateCollisionGroup(){
		
		//Empties out the current collisionGroup.
		collisionGroup = new CollisionGroup();
		
		//Adds tile from the Screen into the collisionGroup.
		for(int x = 0; x<getScreen().getNumOfTilesX(); x++){
			for(int y = 0; y<getScreen().getNumOfTilesY(); y++){
				
				Tile sTile = getScreen().getTile(x, y);
				collisionGroup.add(sTile);
				
			}
		}
		
		//Adds the Player to the collisionGroup.
		if(player != null){
			collisionGroup.add(getPlayer());
		}
	}
	
	public Tile getBackgroundTile() {
		return backgroundTile;
	}
	public void setBackgroundTile(Tile backgroundTile) {
		this.backgroundTile = backgroundTile;
		if(backgroundTile != null){
			darkenedTileImage = darkenImage( Main.deepCopy( (BufferedImage) backgroundTile.getImage() ) );
		}
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
		updateCollisionGroup();
	}
	public CollisionGroup getCollisionGroup() {
		return collisionGroup;
	}
	public void setCollisionGroup(CollisionGroup collisionGroup) {
		this.collisionGroup = collisionGroup;
	}
	
	public void add(Entity e){
		super.add(e);
		collisionGroup.add(e);
	}
	
	public void setCenterEntity(ScrollingEntity e){
		super.setCenterEntity(e);
		if(e instanceof Player){
			this.setPlayer( (Player) e );
		}
	}
	
	public void draw(Graphics g){
		
		//Tile the backgroundTile if it exists.
		if(backgroundTile != null){
			
			int width = getWidth();
			int height = getHeight();
			int imageW = darkenedTileImage.getWidth(this);
			int imageH = darkenedTileImage.getHeight(this);
			
			// Tile the image to fill our area.
			for (int x = 0; x < width; x += imageW){
				for (int y = 0; y < height; y += imageH){
					g.drawImage(darkenedTileImage, x, y, this);
				}
			}
			
		}
		
		Physics.doPhysics(this);
		
		super.draw(g);
		
	}
	
	protected Image darkenImage(Image i){
		
		BufferedImage buff = (BufferedImage) i;
		int rgb;
		int darkRGB;
		int red,green,blue;
		
		for(int y=0; y<buff.getHeight();y++){
			for(int x=0; x<buff.getWidth();x++){
				rgb = buff.getRGB(x, y);
				Color c = new Color(rgb);
				
				red = c.getRed();
				green = c.getGreen();
				blue = c.getGreen();
				
				red -= GameConstants.DARKENING_VALUE;
				green -= GameConstants.DARKENING_VALUE;
				blue -= GameConstants.DARKENING_VALUE;
				
				red = rgbCap(red);
				green = rgbCap(green);
				blue = rgbCap(blue);
				
				Color newColor = new Color(red, green, blue);
				darkRGB = newColor.getRGB();
				
				buff.setRGB(x, y, darkRGB);
			}
		}
		
		return buff;
	}
	
	private int rgbCap(int color){
		
		if(color<0){
			color = 0;
		}else if(color>255){
			color = 255;
		}
		
		return color;
		
	}

}
