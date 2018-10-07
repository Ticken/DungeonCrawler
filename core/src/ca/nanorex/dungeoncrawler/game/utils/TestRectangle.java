package ca.nanorex.dungeoncrawler.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TestRectangle {

    SpriteBatch batch = new SpriteBatch();
    Rectangle rect = new Rectangle();
    Texture txtImage, txtHit = new Texture("squareRed.png");
    Vector2 vel = new Vector2();
    boolean isHit = false;

    public TestRectangle(String sFile){
        txtImage = new Texture(sFile);
    }

    public void render() {
        rect.x = rect.x + vel.x;
        rect.y = rect.y + vel.y;
        vel.set(vel.x * 0.5f, vel.y * 0.5f);
        batch.begin();
        if(isHit){
            batch.draw(txtHit, rect.x, rect.y, rect.width, rect.height);
        } else {
            batch.draw(txtImage, rect.x, rect.y, rect.width, rect.height);
        }
        batch.end();
    }

    public void setPosition(int x, int y) {
        rect.setPosition(x, y);
    }

    public void setSize(int w, int h) {
        rect.setSize(w, h);
    }

    public void setVelocity(Vector2 vel) {
        this.vel.set(vel);
     }

    public void setVelocityX(float x) {
        this.vel.set(x, vel.y);
    }

    public void setVelocityY(float y) {
        this.vel.set(vel.x, y);
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
     }

    public Rectangle getRect() { return rect; }

    public Vector2 getVel() { return vel; }
}
