package ca.nanorex.dungeoncrawler.engine.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class CircularButton extends Button
{
    private ButtonState state;
    private Circle buttonCircle;


    public CircularButton(Vector2 position, float radius)
    {
        buttonCircle = new Circle(position.x, position.y, radius);
        state = ButtonState.DEFAULT;
    }

    public ButtonState getButtonState()
    {
        return state;
    }

    private boolean circleContains(int screenX, int screenY)
    {
        screenY = Gdx.graphics.getHeight() - screenY;

        if (buttonCircle.contains(screenX, screenY))
        {
            return true;
        }
        else
        {
            state = ButtonState.DEFAULT;
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if (circleContains(screenX, screenY))
        {
            state = ButtonState.PRESSED;
        }

        //DO YOUR EVENT HERE

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if (circleContains(screenX, screenY))
        {
            state = ButtonState.HOVER;
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        if (circleContains(screenX, screenY))
        {
            state = ButtonState.HOVER;
        }

        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
