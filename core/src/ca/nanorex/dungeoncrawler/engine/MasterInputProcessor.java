package ca.nanorex.dungeoncrawler.engine;

import java.util.List;

import com.badlogic.gdx.InputProcessor;

public class MasterInputProcessor implements InputProcessor {

    private List<InputProcessor> inputProcessors;

    public void setInputProcessors(List<InputProcessor> inputProcessors) {
        this.inputProcessors = inputProcessors;
    }

    @Override
    public boolean keyDown(int keycode) {
        for (InputProcessor inputProcessor: inputProcessors)
            if (inputProcessor.keyDown(keycode)) break;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (InputProcessor inputProcessor: inputProcessors)
            if (inputProcessor.keyUp(keycode)) break;
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        for (InputProcessor inputProcessor: inputProcessors)
            if (inputProcessor.keyTyped(character)) break;
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (InputProcessor inputProcessor: inputProcessors)
            if (inputProcessor.touchDown(screenX, screenY, pointer, button)) break;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (InputProcessor inputProcessor: inputProcessors)
            if (inputProcessor.touchUp(screenX, screenY, pointer, button)) break;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (InputProcessor inputProcessor: inputProcessors)
            if (inputProcessor.touchDragged(screenX, screenY, pointer)) break;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        for (InputProcessor inputProcessor: inputProcessors)
            if (inputProcessor.mouseMoved(screenX, screenY)) break;
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        for (InputProcessor inputProcessor: inputProcessors)
            if (inputProcessor.scrolled(amount)) break;
        return true;
    }
}
