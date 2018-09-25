package ca.nanorex.dungeoncrawler.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameCamera {

    GameScreen game;
    OrthographicCamera camera;
    StretchViewport viewport;
    int nFollowW, nFollowH;

    public GameCamera(GameScreen game, int w, int h){
        this.game = game;

        // Setup Camera
        // From Ameer
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new StretchViewport(w, h, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        resize(w, h);
    }

    public void setFollowBox(int nW, int nH){
        nFollowW = nW;
        nFollowH = nH;
    }

    public void update(SpriteBatch batch){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    public void follow(float nX, float nY, int nW, int nH){
        if(nY+nH > camera.position.y+(nFollowH/2)){ // Up
            //moveUp();
        }
        if(nY < camera.position.y-(nFollowH/2)){ // Down
            //moveDown();
        }
        if(nX < camera.position.x-(nFollowW/2)){ // Left
            //moveLeft();
        }
        if(nX+nW > camera.position.x+(nFollowW/2)){ // Right
            //moveRight();
        }
    }

    public void resize(int w, int h){
        viewport.update(w, h);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }
}
