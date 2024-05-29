package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import org.w3c.dom.Text;

public class Enemy {
    private Vector2 position;
    private Texture texture;
    private float speed;
    private float shootTimer;
    private Texture bulletTexture;
    private Array<EntBullet> bullets;
    private float health;
    private float maxHealth;

    public Enemy(Vector2 position, Texture texture, float speed) {
        this.position = position;
        this.texture = texture;
        this.speed = speed;
        this.bullets = new Array<>();
        this.health = 2000;
        this.bulletTexture = new Texture("bossbullet.png");
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
        if(position.y > 500) {
            position.y -= speed * delta;
        }
        for (EntBullet bullet : bullets) {
            bullet.update(delta);
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public boolean isOffScreen() {
        return position.y + texture.getHeight() < 0;
    }

    public void shoot(float angle, float bulletSpeed) {
        Vector2 bulletVelocity = new Vector2(bulletSpeed * MathUtils.cosDeg(angle), bulletSpeed * MathUtils.sinDeg(angle));
        bullets.add(new EntBullet(new Vector2(position.x + texture.getWidth() / 2, position.y), bulletVelocity,
                bulletSpeed, angle, bulletTexture, false));
        //(Vector2 position, float speed, float angle, Texture texture, boolean isPlayerBullet)
    }

    public Array<EntBullet> getBullets() {
        return bullets;
    }

    public void shootCircularPattern(int numBullets, float speed, float startAngle) {
        float angleStep = 360f / numBullets; // Angle between each bullet
        float currentAngle = startAngle;

        for (int i = 0; i < numBullets; i++) {
            float angle = currentAngle;
            shoot(angle, 200); // Use the shoot method to fire bullets
            currentAngle += angleStep; // Increase the angle for the next bullet
        }
    }

    public void shootSpiralPattern(int numBullets, float speed, boolean clockwise, float startAngle) {
        float angleStep = 360f / numBullets;

        for (int i = 0; i < numBullets; i++) {
            float bulletSpeed = (i + 1) * speed; // Speed can increase with each bullet
            float bulletAngle = clockwise ? startAngle + angleStep * i : startAngle - angleStep * i;
            shoot(bulletAngle, bulletSpeed);
        }
    }

    public Vector2 getPosition() {
        return position;
    }
}
