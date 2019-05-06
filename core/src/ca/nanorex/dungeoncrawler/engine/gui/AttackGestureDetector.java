package ca.nanorex.dungeoncrawler.engine.gui;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class AttackGestureDetector implements InputProcessor
{
    public enum TypeOfAttack
    {
        SWORD,
        BOW
    }

    private long startTime;
    private Vector2 touchedDownPosition = new Vector2();
    private TypeOfAttack typeOfAttack;
    private Vector2 unitVector = new Vector2();

    private final float attackTypeSwitch = 3000.0f;

    public TypeOfAttack getAttackType()
    {
        return typeOfAttack;
    }

    public Vector2 getUnitVector()
    {
        return unitVector;
    }

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
        touchedDownPosition = new Vector2(screenX, screenY);
        startTime = TimeUtils.millis();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        Vector2 fingerPosition = new Vector2(screenX, screenY);
        fingerPosition.sub(touchedDownPosition);
        unitVector = fingerPosition.nor();

        long amountOfTime = TimeUtils.timeSinceMillis(startTime);
        if (amountOfTime >= attackTypeSwitch)
        {
            typeOfAttack  = TypeOfAttack.BOW;
        }
        else
        {
            typeOfAttack = TypeOfAttack.SWORD;
        }

        System.out.println("Attack Type = " + typeOfAttack.toString());
        System.out.println("Unit Vector: " + unitVector);


        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        Vector2 fingerPosition = new Vector2(screenX, screenY);
        fingerPosition.sub(touchedDownPosition);
        unitVector = fingerPosition.nor();

        System.out.println("Unit Vector: " + unitVector);

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
