package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.LoveGame;
import com.mygdx.game.screen.game.Casino;
import com.mygdx.game.screen.game.Hotel;

public class GameScreen implements Screen{

	private static final float SPEED = 200;

	private LoveGame game;

	Rectangle khaidaica;
	Texture khai;
	
	Rectangle house1, house2, house3, house4;
	private Texture house;
	
	Texture hotel;
	Rectangle hotel1;
	
	Texture casinoImage;
	Rectangle casino;

	private float y;

	private float x;
	
	private int money;
	private float moneyUpTime;
	private static final float MONEY_UP_TIME = 1;

	public GameScreen(LoveGame game) {
		this.game = game;
		this.money = game.money;
		
		khai = new Texture("PMC_Stand.bmp");
		house = new Texture("1.png");

		khaidaica = new Rectangle(x, y, khai.getWidth(), khai.getHeight());
		
		house1 = new Rectangle(100, 100, house.getWidth(), house.getHeight());
		house2 = new Rectangle(100, 400, house.getWidth(), house.getHeight());
		house3 = new Rectangle(500, 100, house.getWidth(), house.getHeight());
		house4 = new Rectangle(500, 400, house.getWidth(), house.getHeight());
		
		hotel = new Texture("lan kwai fong.bmp");
		hotel1 = new Rectangle(700, 300, hotel.getWidth(), hotel.getHeight());
		
		casinoImage = new Texture("casino.bmp");
		casino = new Rectangle(700, 500, casinoImage.getWidth(), casinoImage.getHeight());
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// test
				if(Gdx.input.isKeyPressed(Keys.S)) {
					System.out.println(khaidaica.x);
					System.out.println(khaidaica.y);
					System.out.println(house1.x);
					System.out.println(house1.y);
				}
		
		moneyUpTime += Gdx.graphics.getDeltaTime();
		if(moneyUpTime > MONEY_UP_TIME) {
		money++;
		moneyUpTime =0;
		}

		game.batch.begin();
		
		game.font.draw(game.batch, "MONEY: "+money, 10, Gdx.graphics.getHeight()-30);
		
		game.batch.draw(house, house1.x, house1.y);
		
		game.batch.draw(house, house2.x, house2.y);
		
		game.batch.draw(house, house3.x, house3.y);
		
		game.batch.draw(house, house4.x, house4.y);
		
		game.batch.draw(hotel, hotel1.x, hotel1.y);
		
		game.batch.draw(casinoImage, casino.x, casino.y);

		game.batch.draw(khai, khaidaica.x, khaidaica.y);
		

		game.batch.end();

		// move khaidaica
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			y += SPEED * Gdx.graphics.getDeltaTime();
			khaidaica.y = y;
			if(y > Gdx.graphics.getHeight()- khai.getHeight())
				y = Gdx.graphics.getHeight()- khai.getHeight();
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= SPEED * Gdx.graphics.getDeltaTime();
			khaidaica.y = y;
			if(y < 0) {
				y = 0;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= SPEED * Gdx.graphics.getDeltaTime();
			khaidaica.x = x;
			if(x < 0) {
				x = 0;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += SPEED * Gdx.graphics.getDeltaTime();
			khaidaica.x = x;
			if(x > Gdx.graphics.getWidth()- khai.getWidth())
				x = Gdx.graphics.getWidth()- khai.getWidth();
		}
		
		// check collision khaidaica vs house
		if(khaidaica.x < house1.x + house1.width && khaidaica.x + khaidaica.width > house1.x &&
				khaidaica.y < house1.y + house1.height && khaidaica.y + khaidaica.height > house1.y) {
			//|| khaidaica.overlaps(house2)|| khaidaica.overlaps(house3) ||khaidaica.overlaps(house4))
			
			money ++;
		}
		
		// check khaidaica go into casino
		if(khaidaica.overlaps(casino)) {
			game.money = money;
			game.setScreen(new Casino(game));
			this.dispose();
		}
		
		// check khaidaica go into hotel
		if(khaidaica.overlaps(hotel1)) {
			game.money = money;
			game.setScreen(new Hotel(game));
			this.dispose();
		}

		// go to game over screen
		if(Gdx.input.isKeyJustPressed(Keys.Q)) {
			game.setScreen(new GameOverScreen(game));{
			System.out.println("cham");
			this.dispose();
			}
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
