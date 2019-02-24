package com.mygdx.game.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.LoveGame;
import com.mygdx.game.screen.GameScreen;

public class Home implements Screen{
	
	private LoveGame game;
	private int money;
	
	private Texture girl;
	private int lovePoints;
	
	public Home(LoveGame game) {
		this.game = game;
		this.money = game.money;
		
		girl = new Texture("girl.jpg");
	}
	
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		game.font.draw(game.batch, "HOME", Gdx.graphics.getWidth()/2 -50, Gdx.graphics.getHeight() -50);

		game.font.draw(game.batch, "Press SPACE to go home", Gdx.graphics.getWidth()/2 -50, Gdx.graphics.getHeight()-80);
		
		game.font.draw(game.batch, "MONEY: "+money, 10, Gdx.graphics.getHeight()-30);
		
		game.font.draw(game.batch, "Love Points: "+lovePoints, 10, Gdx.graphics.getHeight()-50);
		
		game.batch.draw(girl, Gdx.graphics.getWidth()/2 - girl.getWidth()/2, Gdx.graphics.getHeight()/4);
		
		game.batch.end();
		
		// touch girl
		if(Gdx.input.getX()< Gdx.graphics.getWidth()/2 + girl.getWidth()/2 && Gdx.input.getX() > Gdx.graphics.getWidth()/2 - girl.getWidth()/2 &&
				Gdx.input.getY()<Gdx.graphics.getHeight()*3/4 && Gdx.input.getY()>Gdx.graphics.getHeight()*3/4-girl.getHeight()) {
			if(Gdx.input.isTouched()) {
				money -= 100;
				lovePoints ++;
			}
		}
		
		// back to game screen
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			game.money = money;
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
		
	}

}
