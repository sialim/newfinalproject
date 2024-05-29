package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends Game {
	public static final int SCREEN_WIDTH = 500;
	public static final int SCREEN_HEIGHT = 700;

	SpriteBatch batch;
	Texture img;

	private GameScreen gameScreen;
	private MenuScreen menuScreen;

	private Music backgroundMusic;
	
	@Override
	public void create () {
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("meilingtheme.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(0.5f);
		backgroundMusic.play();

		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public static int generateRandomNum(int min, int max){
		//min is the smallest number this method can return
		//max is the biggest number this method can return
		return (int)(Math.random() * (max - min + 1) + min);
	}
}
