package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VictoryScreen implements Screen {
    MyGdxGame mainGame;
    SpriteBatch batch;
    private BitmapFont font;
    private BitmapFont font2;
    public VictoryScreen(MyGdxGame mainGame) {
        this.mainGame = mainGame;
    }
    @Override
    public void show() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);

        font2 = new BitmapFont();
        font2.setColor(Color.GRAY);
        font.getData().setScale(1);

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        checkInputs();
        batch.begin();
        font.draw(batch,"You won! Congratulations!", Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f);
        font2.draw(batch, "Want to play again? Press enter!", Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f - 20);
        batch.end();

    }

    public void checkInputs() {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            mainGame.setScreen(new GameScreen(mainGame));
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
        font.dispose();
        font2.dispose();
        batch.dispose();
    }
}
