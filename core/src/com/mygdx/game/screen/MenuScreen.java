package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.LoveGame;

public class MenuScreen implements Screen{
	
	private LoveGame game;
	
	Music rain;
		
	public MenuScreen(LoveGame game) {
		this.game = game;
		
		rain = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		rain.setLooping(true);
		
		game.money = 0;
		game.health = 10;
	}
	
	@Override
	public void show() {
		rain.play();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		game.font.draw(game.batch, "Press any key to start game", Gdx.graphics.getWidth()/2 -50, Gdx.graphics.getHeight()/2);
		
		game.batch.end();
		
		// go to game screen
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			game.setScreen(new GameScreen(game));
			this.dispose();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		rain.dispose();
	}

}
