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
	protected Image tileImage;
	protected Player player;
	protected PuzzBlocks game;
	protected Rectangle levelArea;
	private boolean secondRepaint = false;
	private boolean firstRepaint = false;
	
	public WorldCanvas(Screen s) {
		super(s);
		updateCollisionGroup();
		createLevelAreaRect();
	}
	public WorldCanvas(Screen s, Tile t) {
		super(s);
		backgroundTile = t;
		if(backgroundTile != null){
			darkenedTileImage = darkenImage( Main.deepCopy( (BufferedImage) backgroundTile.getImage() ) );
			tileImage = Main.deepCopy( (BufferedImage) backgroundTile.getImage() );
		}
		updateCollisionGroup();
		createLevelAreaRect();
	}
	public WorldCanvas(int xTiles, int yTiles) {
		super(xTiles, yTiles, GameConstants.TILE_WIDTH, GameConstants.TILE_WIDTH);
		updateCollisionGroup();
		createLevelAreaRect();
	}
	public WorldCanvas(int xTiles, int yTiles, Tile t) {
		super(xTiles, yTiles, GameConstants.TILE_WIDTH, GameConstants.TILE_WIDTH);
		backgroundTile = t;
		if(backgroundTile != null){
			darkenedTileImage = darkenImage( Main.deepCopy( (BufferedImage) backgroundTile.getImage() ) );
			tileImage = Main.deepCopy( (BufferedImage) backgroundTile.getImage() );
		}
		updateCollisionGroup();
		createLevelAreaRect();
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
	private void createLevelAreaRect(){
		
		int w;
		int h;
		
		w = screen.getNumOfTilesX() * screen.getTileWidth();
		h = screen.getNumOfTilesY() * screen.getTileHeight();
		
		levelArea = new Rectangle( (GameConstants.TILE_WIDTH*-1)+1, (GameConstants.TILE_HEIGHT*-1)+1, w, h );
		
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
		
		centerAlignPlayer();
		
		updateCollisionGroup();
	}
	public CollisionGroup getCollisionGroup() {
		return collisionGroup;
	}
	public void setCollisionGroup(CollisionGroup collisionGroup) {
		this.collisionGroup = collisionGroup;
	}
	public PuzzBlocks getGame() {
		return game;
	}
	public void setGame(PuzzBlocks game) {
		this.game = game;
	}
	
	public CollisionGroup getCollisionGroupFromCollider(Collider c){
		
		CollisionGroup smallColliders = new CollisionGroup();
		smallColliders.add(c);
		
		//Add each tile that intersects with the Player.
		for(int x = 0; x<getScreen().getNumOfTilesX(); x++){
			for(int y = 0; y<getScreen().getNumOfTilesY(); y++){
				
				Tile sTile = getScreen().getTile(x, y);
				if( sTile.getRectangle().intersects( c.getRectangle() ) ){
					smallColliders.add(sTile);
				}
				
			}
		}
		
		return smallColliders;
	}
	
	public void centerAlignPlayer(){
		
		if(player!=null){
			player.setRealX( (this.getWidth() - player.getWidth()) /2 );
			player.setRealY( (this.getHeight() - player.getHeight()) /2 );
		}
		
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
	public void setScreen(Screen s){
		super.setScreen(s);
		createLevelAreaRect();
	}
	
	public void draw(Graphics g){
		
		//Center align the player during second repaint
		if(firstRepaint==false){
			firstRepaint = true;
		}
		if(secondRepaint==false&&firstRepaint==true){
			
			centerAlignPlayer();
			secondRepaint = true;
		}
		
		//Tile the backgroundTile if it exists.
		if(backgroundTile != null){
			
			int width = getWidth();
			int height = getHeight();
			int imageW = darkenedTileImage.getWidth(this);
			int imageH = darkenedTileImage.getHeight(this);
			
			// Tile the image to fill our area.
			if(player != null){ //Use player offsets.
				for (int x = roundDownWidth(offsetX)-GameConstants.TILE_WIDTH; x <= roundUpWidth(offsetX + width); x += imageW){
					for (int y = roundDownHeight(offsetY)-GameConstants.TILE_HEIGHT; y <= roundUpHeight(offsetY + height); y += imageH){
						if(levelArea.contains(x, y)){
							g.drawImage(darkenedTileImage, x-offsetX, y-offsetY, this);
						}else{
							g.drawImage(tileImage, x-offsetX, y-offsetY, this);
						}
					}
				}
			}else{
				for (int x = 0; x <= width; x += imageW){
					for (int y = 0; y <= height; y += imageH){
						if(levelArea.contains(x, y)){
							g.drawImage(darkenedTileImage, x, y, this);
						}else{
							g.drawImage(tileImage, x, y, this);
						}
					}
				}
			}
			
		}
		
		super.draw(g);
		
		//Show FPS if there is a reference to PuzzBlocks game.
		if(GameConstants.DEBUG_MODE){
			if(game != null){
				Color oldColor = g.getColor();
				g.setColor(Color.red);
				
				g.setFont( new Font(GameConstants.FPS_FONT, Font.BOLD, GameConstants.FPS_FONT_SIZE) );
				g.drawString("FPS: " + game.getFPS(), 0, (int)(GameConstants.FPS_FONT_SIZE * 0.75) ); // (+ "") converts int to String.
				
				g.setColor(oldColor);
			}
		}
		
	}
	
	protected int roundUpWidth(int i){
		double out = i/GameConstants.TILE_WIDTH;
		return (int) Math.ceil(out)*GameConstants.TILE_WIDTH;
	}
	protected int roundUpHeight(int i){
		double out = i/GameConstants.TILE_HEIGHT;
		return (int) Math.ceil(out)*GameConstants.TILE_HEIGHT;
	}
	protected int roundDownWidth(int i){
		double out = i/GameConstants.TILE_WIDTH;
		return (int) Math.floor(out)*GameConstants.TILE_WIDTH;
	}
	protected int roundDownHeight(int i){
		double out = i/GameConstants.TILE_HEIGHT;
		return (int) Math.floor(out)*GameConstants.TILE_HEIGHT;
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
