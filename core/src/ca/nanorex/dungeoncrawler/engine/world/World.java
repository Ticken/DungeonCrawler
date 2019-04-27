package ca.nanorex.dungeoncrawler.engine.world;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

import ca.nanorex.dungeoncrawler.engine.Game;
import ca.nanorex.dungeoncrawler.engine.gui.VirtualJoystick;
import ca.nanorex.dungeoncrawler.engine.render.renderers.SpriteRenderer;
import ca.nanorex.dungeoncrawler.engine.render.sprite.Sprite;
import ca.nanorex.dungeoncrawler.engine.render.sprite.SpriteModel;
import ca.nanorex.dungeoncrawler.engine.render.sprite.SpriteTexture;
import ca.nanorex.dungeoncrawler.engine.world.objects.ObjectManager;

public class World {

    private ObjectManager objects;

    private Sprite test;
    int tmr, r;
    double x = 50, y = 50, dx, dy;
    boolean walk, prevwalk;
    boolean w, a, s, d;
    boolean pw, pa, ps, pd;

    boolean foundAsset;

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    SpriteRenderer spriteRenderer;

    Vector2 input, vel;
    float traction = 2f, maxSpeed = 10f;
    float animFactor = 1.5f;

    float js = 2f / 5;


    VirtualJoystick joystick;

    public World() {
        objects = new ObjectManager();

        Game.getAssetLibrary().load("basic_humanoid", SpriteModel.class);
        //Game.getAssetManager().load("assets/textures/objects/player.png", SpriteTexture.class);
        Game.getAssetLibrary().load("objects/player", SpriteTexture.class);
        Game.getAssetLibrary().load("objects/basic_sword", SpriteTexture.class);
        Game.getAssetLibrary().finishLoading();

        test = new Sprite("basic_humanoid",
                new HashMap<String, String>() {{
                    put("main", "objects/player");
                    put("weapon", "objects/basic_sword");
                }});
        //todo: should automatically assume directory based on asset type

        //test.playAnimation("walk", 1, false);

        OrthographicCamera camera = new OrthographicCamera();
        if (Gdx.app.getType() == Application.ApplicationType.Android)
            camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        else
            camera.setToOrtho(false);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        //shapeRenderer.setProjectionMatrix(camera.combined);

        spriteRenderer = new SpriteRenderer();


        input = new Vector2();
        vel = new Vector2();


        joystick = new VirtualJoystick(new Vector2(180.0f,180.0f), 144.0f);
        Gdx.input.setInputProcessor(joystick);

        x = 640;
        y = 360;
    }

    public void tick() {

        tmr++;/*
        if (tmr == 30) {
            tmr = 0;
        }*/

        float spd = 7f;//7f; //walk speed
        float spd2 = 0.18f; //animation speed
        double turnspd = (double)tmr / 30;
/*
        dx = spd * Math.cos(turnspd);
        dy = spd * Math.sin(turnspd);


*/
/*
        final int scrw = Gdx.graphics.getWidth();
        final int scrh = Gdx.graphics.getHeight();

        int tx = Gdx.input.getX();
        int ty = Gdx.input.getY();

        pw = w;
        pa = a;
        ps = s;
        pd = d;

        w =
                Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isTouched() && ty < scrh - scrh * js + scrh * js / 3 && ty > scrh - scrh * js && tx < scrh * js;
        a =
                Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isTouched() && tx < scrh * js / 3 && ty > scrh - scrh * js;
        s =
                Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isTouched() && ty > scrh - scrh * js / 3 && tx < scrh * js;
        d =
                Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isTouched() && tx > scrh * js - scrh * js / 3 && tx < scrh * js && ty > scrh - scrh * js;



        input.x = d ? 1 : a ? -1 : 0;
        input.y = w ? 1 : s ? -1 : 0;

        input.nor();*/

        input = joystick.getOutput();

        Vector2 ddesiredVel = new Vector2(input).scl(maxSpeed).sub(vel);
        Vector2 accel = new Vector2(ddesiredVel).nor().scl(traction);

        if (accel.len() > ddesiredVel.len()) accel.setLength(ddesiredVel.len());

        vel.add(accel);
        if (vel.len() > maxSpeed) vel.setLength(maxSpeed);

        prevwalk = walk;

        if (vel.isZero(4E-4f)) {
            walk = false;
            vel.scl(0f);
        } else {
            walk = true;
        }

        if (walk && ! prevwalk)
            test.playAnimation("walk", 0, false);
        else if (prevwalk && !walk)
            test.stopAnimation("walk", false);

        if (walk) {
            test.setAnimationSpeed(vel.len() / maxSpeed * animFactor);
        }

        if (!input.isZero())
            test.getRotation().setRadians((float) Math.atan2(input.y, input.x));

        x += vel.x;
        y += vel.y;

        test.setPos(new Vector2((float)x, (float)y));

        test.tick();
    }

    public void render(float lerp) {

        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );


        batch.begin();

        /*
        for (TextureRegion r: test.getTextureRegions()) {
                batch.draw(r, (int) x, (int) y, 128, 128);

        }*/
        spriteRenderer.render(test, batch, lerp);
        batch.end();


        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 0.1f);
        shapeRenderer.circle(180,180,144);
        shapeRenderer.circle(180 + joystick.getPosition().x, 180 + joystick.getPosition().y, 72);
      //  shapeRenderer.rect(0, 0, Gdx.graphics.getHeight() * js, Gdx.graphics.getHeight() * js);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

    }

    public ObjectManager getObjects() {
        return objects;
    }
}
