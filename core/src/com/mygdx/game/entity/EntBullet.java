package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EntBullet {
    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;
    private float speed;
    private boolean isPlayerBullet;
    private Rectangle boundingBox;
    private boolean isActive;

    public EntBullet(Vector2 position, Vector2 velocity, float speed, float angle, Texture texture, boolean isPlayerBullet) {
        this.position = position;
        this.texture = texture;
        this.speed = speed;
        this.isPlayerBullet = isPlayerBullet;
        this.boundingBox = new Rectangle(position.x, position.y, 10, 10);
        this.isActive = true;

        float radians = (float) Math.toRadians(angle);
        this.velocity = velocity;
    }

    public void update(float delta) {
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
        boundingBox.setPosition(position);
    }

    public void draw(SpriteBatch batch) {
        if (isPlayerBullet) {
            batch.draw(texture, position.x, position.y, 10, 20);
        } else {
            batch.draw(texture, position.x, position.y, 20, 20);
        }

    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public boolean isPlayerBullet() {
        return isPlayerBullet;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isOutOfBounds() {
        return position.x > Gdx.graphics.getWidth() || position.x < 0 || position.y > Gdx.graphics.getHeight() || position.y < 0;
    }
}
