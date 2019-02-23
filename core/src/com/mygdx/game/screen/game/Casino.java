package com.mygdx.game.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.LoveGame;
import com.mygdx.game.screen.GameScreen;

public class Casino implements Screen{
	
	private LoveGame game;
	private int money;
	
	public Casino(LoveGame game) {
		this.game = game;
		this.money = game.money;
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		game.font.draw(game.batch, "Royal Casino", Gdx.graphics.getWidth()/2 -50, Gdx.graphics.getHeight()/2+50);

		game.font.draw(game.batch, "Press SPACE to go home", Gdx.graphics.getWidth()/2 -50, Gdx.graphics.getHeight()/2);
		
		game.font.draw(game.batch, "MONEY: "+money, 10, Gdx.graphics.getHeight()-30);
		
		game.batch.end();
		
		// lose money
		if(Gdx.input.isKeyJustPressed(Keys.M)) {
			money -= 100;
		}
		// win money
		if(Gdx.input.isKeyJustPressed(Keys.W))
			money += 1000;
		
		// back game screen
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			game.money = money;
			game.setScreen(new GameScreen(game));
			this.dispose();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
