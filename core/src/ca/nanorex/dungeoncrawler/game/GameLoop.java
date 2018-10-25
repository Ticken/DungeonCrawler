package ca.nanorex.dungeoncrawler.game;

import java.util.LinkedList;
import java.util.List;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.components.ObjectComponent;

public class GameLoop {
    List<GameObject> objects;

    public void init() {
        objects = new LinkedList<GameObject>();
    }

    public void loop() {
        //Iterate over each component type
        for (Class<? extends ObjectComponent> type: ObjectComponent.order) {
            for (GameObject object: objects) {
                if (object.hasComponent(type)) {
                    object.getComponent(type).update();
                }
            }
            /*for (GameObject object: objects) {
                if (object.hasComponent(type))
                    object.getComponent(type).processEvents();
            }*/
        }
    }
}
