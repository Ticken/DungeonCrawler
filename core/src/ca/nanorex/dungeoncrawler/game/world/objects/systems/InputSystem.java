package ca.nanorex.dungeoncrawler.game.world.objects.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.Input.Keys;

import java.util.HashMap;
import java.util.Map;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.components.controllers.PlayerControllerComponent;

public class InputSystem extends ObjectSystem implements InputProcessor {
    private final String JSON_FILE_PATH = "json/controls.json";
    // control names:
    private static final String MOVE_UP = "moveUp";
    private static final String MOVE_DOWN = "moveDown";
    private static final String MOVE_LEFT = "moveLeft";
    private static final String MOVE_RIGHT = "moveRight";

    private Map<Integer, String> controls; // key name to string action
    private static final String[] KEY_LIST = getKeyList();
    private boolean[] moveKeys; //up down left right
    private Vector2 direction;

    // gamepad
    // TODO @Jordan implement game controllers

    public InputSystem() {
        JsonReader jsonReader = new JsonReader();
        JsonValue jsonFile = jsonReader.parse(Gdx.files.internal(JSON_FILE_PATH));
        JsonValue jsonKeyboard = jsonFile.get("keyboard");

        controls = new HashMap();
        moveKeys = new boolean[4];
        for (int i = 0; i < moveKeys.length; i++)
            moveKeys[i] = false;

        for (String key: KEY_LIST)
            controls.put(Keys.valueOf(jsonKeyboard.getString(key)), key);

        direction = new Vector2();
    }

    @Override
    public void update(ObjectList objectList) {
        for (GameObject player : objectList.getObjects()) {
            PlayerControllerComponent playerControllerComponent = player.getComponent
                    (PlayerControllerComponent.class);
            if (playerControllerComponent != null)
                playerControllerComponent.setDirection(direction);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        String keyAction = controls.get(keycode);
        // if the keycode is written as a control
        if (keyAction != null) {
            // then this is a movement key
            int directionIndex = getMoveDirectionIndex(keyAction);
            if (directionIndex != -1) {
                moveKeys[directionIndex] = true;
                moveKeys[getOppositeMoveDirectionIndex(directionIndex)] = false;
                updateDirection();
            }
        }

        /*
        // TODO @Jordan Slater this should be in our utils (get a string of an array to print)
        String s = "{";
        for (int i = 0; i < moveKeys.length; i++)
            s = s + moveKeys[i] + ",";
        System.out.println(s + "}");
        */
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        String keyAction = controls.get(keycode);
        // if the keycode is written as a control
        if (keyAction != null) {
            // then this is a movement key
            int directionIndex = getMoveDirectionIndex(keyAction);
            if (directionIndex != -1) {
                moveKeys[directionIndex] = false;
                updateDirection();
            }
        }
        return false;
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

    static final private String[] getKeyList() {
        return new String[]{
            MOVE_UP,
            MOVE_DOWN,
            MOVE_LEFT,
            MOVE_RIGHT
        };
    }

    private int getMoveDirectionIndex(String keyName) {
        if (keyName == MOVE_UP)
            return 0;
        if (keyName == MOVE_DOWN)
            return 1;
        if (keyName == MOVE_LEFT)
            return 2;
        if (keyName == MOVE_RIGHT)
            return 3;
        return -1;
    }

    private int getOppositeMoveDirectionIndex(int i) {
        if (i < 0 || i > 3)
            return -1;
        if (i % 2 == 0) //even
            return i + 1;
        return i - 1; // odd
    }

    private void updateDirection() {
        int x = (moveKeys[2] ? -1 : 0) + (moveKeys[3] ? 1 : 0);
        int y = (moveKeys[0] ? 1 : 0) + (moveKeys[1] ? -1 : 0);
        direction = new Vector2(x,y).nor();
    }
}