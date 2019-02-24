package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.LoveGame;
import com.mygdx.game.screen.game.Casino;
import com.mygdx.game.screen.game.Factory;
import com.mygdx.game.screen.game.Home;
import com.mygdx.game.screen.game.Hotel;
import com.mygdx.game.screen.game.Restaurant;

public class GameScreen implements Screen{

	private static final float SPEED = 200;

	private LoveGame game;

	Animation<TextureRegion> khaidaica;
	Rectangle khaica;
	Texture khai;
	private static final float TIME = 0.2f;
	private static final int SIZE = 256;
	private float time;
	
	Rectangle house1, house2, house3, house4;
	private Texture house;
	
	Texture hotel;
	Rectangle hotel1;
	
	Texture casinoImage;
	Rectangle casino;
	
	Texture restaurantImage;
	Rectangle restaurant;
	
	Texture factoryImage;
	Rectangle factory;
	
	Texture homeImage;
	Rectangle home;

	private float y;

	private float x;
	
	private int money;
	private float moneyUpTime;
	private static final float MONEY_UP_TIME = 1;

	public GameScreen(LoveGame game) {
		this.game = game;
		this.money = game.money;
		
		khai = new Texture("Punk_Run.png");
		house = new Texture("1.png");

		khaica = new Rectangle(x, y, SIZE, SIZE);
		khaidaica = new Animation<TextureRegion>(TIME, TextureRegion.split(khai, SIZE, SIZE)[0]);
		
		house1 = new Rectangle(100, 100, house.getWidth(), house.getHeight());
		house2 = new Rectangle(100, 400, house.getWidth(), house.getHeight());
		house3 = new Rectangle(500, 100, house.getWidth(), house.getHeight());
		house4 = new Rectangle(500, 400, house.getWidth(), house.getHeight());
		
		hotel = new Texture("hotel.jpg");
		hotel1 = new Rectangle(700, 300, hotel.getWidth(), hotel.getHeight());
		
		casinoImage = new Texture("casino.bmp");
		casino = new Rectangle(700, 500, casinoImage.getWidth(), casinoImage.getHeight());
		
		restaurantImage = new Texture("restaurant.bmp");
		restaurant = new Rectangle(400, 300, restaurantImage.getWidth(), restaurantImage.getHeight());
		
		factoryImage = new Texture("factory.bmp");
		factory = new Rectangle(400, 500, factoryImage.getWidth(), factoryImage.getHeight());
		
		homeImage = new Texture("home.jpg");
		home = new Rectangle(100, 500, homeImage.getWidth(), homeImage.getHeight());
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		/*// test
				if(Gdx.input.isKeyPressed(Keys.S)) {
					System.out.println(khaidaica.x);
					System.out.println(khaidaica.y);
					System.out.println(house1.x);
					System.out.println(house1.y);
				}*/
		
		moneyUpTime += Gdx.graphics.getDeltaTime();
		if(moneyUpTime > MONEY_UP_TIME) {
		money++;
		moneyUpTime =0;
		}
		
		time += delta;
		
		game.batch.begin();
		
		game.font.draw(game.batch, "MONEY: "+money, 10, Gdx.graphics.getHeight()-30);
		game.font.draw(game.batch, "HEALTH: "+game.health, 10, Gdx.graphics.getHeight()-50);
		
		game.batch.draw(house, house1.x, house1.y);
		
		game.batch.draw(house, house2.x, house2.y);
		
		game.batch.draw(house, house3.x, house3.y);
		
		game.batch.draw(house, house4.x, house4.y);
		
		game.batch.draw(hotel, hotel1.x, hotel1.y);
		
		game.batch.draw(casinoImage, casino.x, casino.y);
		
		game.batch.draw(factoryImage, factory.x, factory.y);
		
		game.batch.draw(restaurantImage, restaurant.x, restaurant.y);
		
		game.batch.draw(homeImage, home.x, home.y);

		game.batch.draw(khaidaica.getKeyFrame(time, true), x, y);
		

		game.batch.end();

		// move khaidaica
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			y += SPEED * Gdx.graphics.getDeltaTime();
			khaica.y = y;
			if(y > Gdx.graphics.getHeight()- khai.getHeight())
				y = Gdx.graphics.getHeight()- khai.getHeight();
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= SPEED * Gdx.graphics.getDeltaTime();
			khaica.y = y;
			if(y < 0) {
				y = 0;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= SPEED * Gdx.graphics.getDeltaTime();
			khaica.x = x;
			if(x < 0) {
				x = 0;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += SPEED * Gdx.graphics.getDeltaTime();
			khaica.x = x;
			if(x > Gdx.graphics.getWidth()- SIZE)
				x = Gdx.graphics.getWidth()- SIZE;
		}
		
		// check collision khaidaica vs house
		if((khaica.x < house1.x + house1.width && khaica.x + khaica.width > house1.x &&
				khaica.y < house1.y + house1.height && khaica.y + khaica.height > house1.y )
			|| khaica.overlaps(house2)|| khaica.overlaps(house3) ||khaica.overlaps(house4)) {
			
			money ++;
		}
		
		// check khaidaica go into casino
		if(khaica.overlaps(casino)) {
			game.money = money;
			game.health --;
			game.setScreen(new Casino(game));
			this.dispose();
		}
		
		// check khaidaica go into hotel
		if(khaica.overlaps(hotel1)) {
			game.money = money;
			game.health --;
			game.setScreen(new Hotel(game));
			this.dispose();
		}
		
		// check khaidaica go into restaurant
		if(khaica.overlaps(restaurant)) {
			game.money = money;
			game.health --;
			game.setScreen(new Restaurant(game));
			this.dispose();
		}
		
		// check khaidaica go into factory
		if(khaica.overlaps(factory)) {
			game.money = money;
			game.health --;
			game.setScreen(new Factory(game));
			this.dispose();
		}
		
		// check khaidaica go home
		if(khaica.overlaps(home)) {
			game.money = money;
			game.health --;
			game.setScreen(new Home(game));
			this.dispose();
		}

		// go to game over screen
		if(Gdx.input.isKeyJustPressed(Keys.Q)) {
			game.money = money;
			game.setScreen(new GameOverScreen(game));{
			this.dispose();
			}
		}
		
		// game over
		if(game.health <= 0) {
			game.setScreen(new GameOverScreen(game));
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
