package ca.nanorex.dungeoncrawler.engine.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class VirtualJoystick implements InputProcessor
{
    private Vector2 center;
    private Vector2 position = new Vector2();
    private Vector2 output = new Vector2();

    private float joystickRadius;
    private float deadZoneRadius;
    private boolean touched = false;

    public VirtualJoystick(Vector2 center, float radius)
    {
        this.center = center;
        joystickRadius = radius;
        deadZoneRadius = joystickRadius/5;
    }

    public Vector2 getOutput()
    {
        return output;
    }
    public Vector2 getPosition() { return position; }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        float distance = new Vector2(screenX, Gdx.graphics.getHeight() - screenY).dst2(center);
        if (distance <= joystickRadius * joystickRadius)
            touched = true;

        touchDragged(screenX, screenY, pointer);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        position.set(Vector2.Zero);
        output.set(Vector2.Zero);
        touched = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        screenY = Gdx.graphics.getHeight() - screenY;

        Vector2 delta = new Vector2();
        //position = center - (screenX, screenY)
        delta.set(screenX, screenY).sub(new Vector2(center));
        float distance = delta.len2();

        if (distance <= deadZoneRadius * deadZoneRadius) //dead-zone
        {
            position.set(delta);
            output.set(Vector2.Zero);
        }
        else if (distance >= joystickRadius * joystickRadius) //outside
        {
            if(touched)
            {
                position.set(delta);
                output.set(new Vector2(position.setLength2(joystickRadius * joystickRadius)).setLength2(1));
            }
        }
        else //active zone
        {
            position.set(delta);

            output.set(new Vector2(position).setLength((position.len()- deadZoneRadius)/(joystickRadius - deadZoneRadius)));
           // output.setLength((position.len()- deadZoneRadius)/(joystickRadius - deadZoneRadius));
            //output.set(new Vector2(position).setLength2(1));
        }

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