package ca.nanorex.dungeoncrawler.engine.gui;

import com.badlogic.gdx.InputProcessor;

public abstract class Button implements InputProcessor
{
    enum ButtonState
    {
        DEFAULT,
        PRESSED,
        HOVER,
        DISABLED
    }

    public abstract ButtonState getButtonState();

    @Override
    public abstract boolean keyDown(int keycode);

    @Override
    public abstract boolean keyUp(int keycode);

    @Override
    public abstract boolean keyTyped(char character);

    @Override
    public abstract boolean touchDown(int screenX, int screenY, int pointer, int button);

    @Override
    public abstract boolean touchUp(int screenX, int screenY, int pointer, int button);

    @Override
    public abstract boolean touchDragged(int screenX, int screenY, int pointer);

    @Override
    public abstract boolean mouseMoved(int screenX, int screenY);

    @Override
    public abstract boolean scrolled(int amount);
}
