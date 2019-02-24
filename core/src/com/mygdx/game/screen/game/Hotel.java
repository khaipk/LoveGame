package com.mygdx.game.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.LoveGame;
import com.mygdx.game.screen.GameScreen;

public class Hotel implements Screen{
	
	private LoveGame game;
	private int money;
	
	private Texture girl1, girl2, girl3, girl4;
	
	public Hotel(LoveGame game) {
		this.game = game;
		this.money = game.money;
		
		girl1 = new Texture("1.jpg");
		girl2 = new Texture("2.jpg");
		girl3 = new Texture("3.jpg");
		girl4 = new Texture("4.jpg");
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
		
		game.font.draw(game.batch, "Lan Kwai Fong", Gdx.graphics.getWidth()/2 -50, Gdx.graphics.getHeight()/2+50);

		game.font.draw(game.batch, "Press SPACE to go home", Gdx.graphics.getWidth()/2 -50, Gdx.graphics.getHeight()/2);
		
		game.font.draw(game.batch, "MONEY: "+money, 10, Gdx.graphics.getHeight()-30);
		
		game.batch.draw(girl1, 150, 100);
		game.batch.draw(girl2, 150 + girl1.getWidth()+100, 100);
		game.batch.draw(girl3, 150+girl1.getWidth()+100+girl2.getWidth()+100, 100);
		game.batch.draw(girl4, 150+girl1.getWidth()+100+girl2.getWidth()+100+girl3.getWidth()+100, 100);
		
		game.batch.end();
		
		// choose girls
		if(Gdx.input.getY()<Gdx.graphics.getHeight()-100 && Gdx.input.getY()>Gdx.graphics.getHeight()-250) {
			if( (Gdx.input.getX()>150 && Gdx.input.getX()<150+girl1.getWidth()) ||
					(Gdx.input.getX()>150 + girl1.getWidth()+100 && Gdx.input.getX()<150+girl1.getWidth()+100+girl2.getWidth()) ||
					(Gdx.input.getX()>150+girl1.getWidth()+100+girl2.getWidth()+100 && Gdx.input.getX()<150+girl1.getWidth()+100+girl2.getWidth()+100+girl3.getWidth()) ||
					(Gdx.input.getX()>150+girl1.getWidth()+100+girl2.getWidth()+100+girl3.getWidth()+100 && Gdx.input.getX()<150+girl1.getWidth()+100+girl2.getWidth()+100+girl3.getWidth()+100+girl4.getWidth()) )
			if(Gdx.input.isTouched())
				money -= 1000;
		}
		
		// hack money
		if(Gdx.input.isKeyJustPressed(Keys.M)) 
			money += 100;
		
		// call girl
		if(Gdx.input.isKeyJustPressed(Keys.C))
			money -= 100;
		
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
