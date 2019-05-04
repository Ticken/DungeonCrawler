package ca.nanorex.dungeoncrawler.engine.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import ca.nanorex.dungeoncrawler.engine.Game;
import ca.nanorex.dungeoncrawler.engine.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.engine.world.objects.events.MobCommandEvent;

public class TmpKeyboardInput implements InputProcessor {

    private GameObject player;

    private boolean w, a, s, d;

    public TmpKeyboardInput(GameObject player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.D)
            d = true;
        else if (keycode == Input.Keys.A)
            a = true;
        else if (keycode == Input.Keys.W)
            w = true;
        else if (keycode == Input.Keys.S)
            s = true;
        else
            return false;

        //Create vector
        int x = d != a ? (d ? 1 : -1) : 0;
        int y = w != s ? (w ? 1 : -1) : 0;

        Vector2 output = new Vector2(x, y).nor();

        MobCommandEvent event = new MobCommandEvent(player, MobCommandEvent.Type.MOVE, output, 0);

        //Publish event
        Game.getEventManager().publish(event);

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == Input.Keys.D)
            d = false;
        else if (keycode == Input.Keys.A)
            a = false;
        else if (keycode == Input.Keys.W)
            w = false;
        else if (keycode == Input.Keys.S)
            s = false;
        else
            return false;

        //Create vector
        int x = d != a ? (d ? 1 : -1) : 0;
        int y = w != s ? (w ? 1 : -1) : 0;

        Vector2 output = new Vector2(x, y).nor();

        MobCommandEvent event = new MobCommandEvent(player, MobCommandEvent.Type.MOVE, output, 0);

        //Publish event
        Game.getEventManager().publish(event);

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
