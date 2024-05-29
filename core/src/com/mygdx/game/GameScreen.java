package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entity.Enemy;
import com.mygdx.game.entity.EntBullet;
import com.mygdx.game.entity.Player;
import sun.jvm.hotspot.utilities.BitMap;

public class GameScreen implements Screen {
    private final MyGdxGame mainGame;
    private SpriteBatch batch;
    private Texture background;
    private Texture playerTexture;
    private Player player;
    private Enemy enemy;
    private Texture enemyTexture;
    private Array<Enemy> enemies;
    private BitmapFont font;
    private BitmapFont font2;

    private float timer;
    private float enemyShootTimer;

    public GameScreen(MyGdxGame mainGame) {
        this.mainGame = mainGame;
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(1);

        font2 = new BitmapFont();
        font2.setColor(Color.RED);
        font.getData().setScale(2f);



        batch = new SpriteBatch();
        background = new Texture("background.jpg");
        playerTexture = new Texture("player.png");
        enemyTexture = new Texture("hongmeiling.png");
        player = new Player(new Vector2(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f - 100), playerTexture);
        player.setHealth(100);

        enemy = new Enemy(new Vector2(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()), enemyTexture, 100);
        enemy.setHealth(1000);
        enemies = new Array<>();
        enemies.add(enemy);
        timer = 0.0f;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timer += delta;
        enemyShootTimer += delta;

        if (enemyShootTimer > 0.5f && enemy.getHealth() > 0f) {
            chooseRandomAttack(MyGdxGame.generateRandomNum(1, 2));
            enemyShootTimer = 0;
        }

        player.update(delta);

        enemy.update(delta);

        checkCollisions();

        batch.begin();

        batch.draw(background, 0, 0); // L1

        player.draw(batch); // L2

        enemy.draw(batch);

        for (EntBullet bullet : player.getBullets()) {
            if (bullet.isActive()) {
                bullet.draw(batch);
            }
        }
        for (EntBullet bullet : enemy.getBullets()) {
            if (bullet.isActive()) {
                bullet.draw(batch);
            }
        }

        font.draw(batch, "Your HP: " + player.getHealth(), Gdx.graphics.getWidth()/2f, 100);
        font2.draw(batch, "Enemy HP: " + enemy.getHealth(), Gdx.graphics.getWidth()/2f, 600);

        batch.end();
    }

    private void checkCollisions() {
        Array<EntBullet> playerBullets = player.getBullets();
        Array<EntBullet> enemyBullets = new Array<>();

        for (Enemy enemy : enemies) {
            for (EntBullet bullet : playerBullets) {
                if (bullet.getBoundingBox().overlaps(enemy.getBoundingBox())) {
                    playerBullets.removeValue(bullet, false);
                    enemy.setHealth(enemy.getHealth() - 10);
                    if (enemy.getHealth() <= 0) {
                        enemies.removeValue(enemy, false);
                    }
                    break;
                }
            }
            enemyBullets.addAll(enemy.getBullets());
        }

        for (EntBullet bullet : enemyBullets) {
            if (bullet.getBoundingBox().overlaps(player.getBoundingBox()) && bullet.isActive()) {
                bullet.setActive(false);
                player.setHealth(player.getHealth() - 10);
                System.out.println("Player hit!");
            }
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
        background.dispose();
        batch.dispose();
        font.dispose();
        font2.dispose();
    }

    private void chooseRandomAttack(int choice) {
        switch (choice) {
            case 1: {
                int numBullets = MyGdxGame.generateRandomNum(10, 55);
                float speed = (float) MyGdxGame.generateRandomNum(50, 300);
                float angle = (float) MyGdxGame.generateRandomNum(-10, 10);
                enemy.shootCircularPattern(numBullets, speed, angle);
            }
            case 2: {
                int numBullets = MyGdxGame.generateRandomNum(10, 55);
                float speed = (float) MyGdxGame.generateRandomNum(10, 20);
                float angle = (float) MyGdxGame.generateRandomNum(-10, 10);
                enemy.shootSpiralPattern(numBullets, speed, true, angle);
            }
        }
    }
}