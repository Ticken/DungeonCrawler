package ca.nanorex.dungeoncrawler.engine.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class RectangularButton extends Button
{
    private ButtonState state;
    private Rectangle buttonRectangle;


    public RectangularButton(Vector2 position, float width, float height)
    {
        buttonRectangle = new Rectangle(position.x, position.y, width, height);
        state = ButtonState.DEFAULT;
    }

    public ButtonState getButtonState()
    {
        return state;
    }

    private boolean rectangleContains(int screenX, int screenY)
    {
        screenY = Gdx.graphics.getHeight() - screenY;

        if (buttonRectangle.contains(screenX, screenY))
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
        if (rectangleContains(screenX, screenY))
        {
            state = ButtonState.PRESSED;
        }

        //DO YOUR EVENT HERE

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if (rectangleContains(screenX, screenY))
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
        if (rectangleContains(screenX, screenY))
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