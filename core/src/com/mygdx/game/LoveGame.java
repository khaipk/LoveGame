package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.MenuScreen;

public class LoveGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public static int money;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
		
		font = new BitmapFont();
	}

	@Override
	public void render () {
		super.render();
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			this.dispose();
			System.exit(0);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
