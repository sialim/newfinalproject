package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public class Player {
    public float speed = 200;
    private Vector2 position;
    private Texture texture;
    private Array<EntBullet> bullets;
    private Texture bulletTexture;
    private Sound laserSound;
    private float health;
    private float maxHealth;
    private float cooldown;

    public Player(Vector2 position, Texture texture) {
        this.position = position;
        this.texture = texture;
        this.bullets = new Array<>();
        this.bulletTexture = new Texture("playerbullet.jpg");
        laserSound = Gdx.audio.newSound(Gdx.files.internal("playerbulletsoundeffect.wav"));
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += 200 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= 200 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= 200 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += 200 * delta;
        }

        cooldown += delta;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && cooldown > 0.05f) {
            shoot();
            cooldown = 0;
        }

        for (EntBullet bullet : bullets) {
            bullet.update(delta);
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
        for (EntBullet bullet : bullets) {
            bullet.draw(batch);
        }
    }

    private void shoot() {
        float bulletSpeed = 600;
        float angle = (float) MyGdxGame.generateRandomNum(87, 93);
        float vx = bulletSpeed * MathUtils.cosDeg(angle);
        float vy = bulletSpeed * MathUtils.sinDeg(angle);
        Vector2 bulletVelocity = new Vector2(vx, vy);
        Vector2 bulletPosition = new Vector2(position.x + texture.getWidth() / 2, position.y + texture.getHeight());
        EntBullet bullet = new EntBullet(bulletPosition, bulletVelocity, bulletSpeed, angle, bulletTexture, true);
        bullets.add(bullet);
        laserSound.play();
    }

    public Array<EntBullet> getBullets() {
        return bullets;
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(position.x, position.y, 10, 10);
    }

    public Vector2 getPosition() {
        return position;
    }
}
