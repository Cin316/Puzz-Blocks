package com.puzzblocks.obj;

import java.awt.Image;

import com.utilis.game.obj.CollisionBox;
import com.utilis.game.obj.ScrollingEntity;

public class Player extends ScrollingEntity {
	
	public Player(Image i) {
		super(i);
	}
	public Player(CollisionBox c) {
		super(c);
	}
	public Player(int w, int h) {
		super(w, h);
	}
	public Player(Image i, CollisionBox c) {
		super(i, c);
	}
	
}
