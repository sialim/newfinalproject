package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen implements Screen {
    private final MyGdxGame mainGame;
    private SpriteBatch batch;
    private BitmapFont font;
    private String[] menuOptions;
    private int selectedOption;
    private OrthographicCamera camera;

    public MenuScreen(MyGdxGame mainGame) {
        this.mainGame = mainGame;
        this.menuOptions = new String[] {"Start Game", "Options", "Exit"};
        this.selectedOption = 0;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
    }

    @Override
    public void render(float delta) {
        handleInput();


        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (int i = 0; i < menuOptions.length; i++) {
            if (i == selectedOption) {
                font.setColor(Color.GOLD);
            } else {
                font.setColor(Color.WHITE);
            }
            font.draw(batch, menuOptions[i], Gdx.graphics.getWidth() / 2f - 475, (Gdx.graphics.getHeight() / 2f - i * 40) - 300);
        }
        batch.end();
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
        batch.dispose();
        font.dispose();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectedOption = (selectedOption - 1 + menuOptions.length) % menuOptions.length;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedOption = (selectedOption + 1) % menuOptions.length;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            selectOption();
        }
    }

    private void selectOption() {
        switch (selectedOption) {
            case 0:
                mainGame.setScreen(new GameScreen(mainGame));
                break;
            case 1:
                Gdx.app.exit();
                break;
            case 2:
                Gdx.app.exit();
                break;
        }
    }
}
