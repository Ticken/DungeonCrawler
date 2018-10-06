package ca.nanorex.dungeoncrawler.game;

import ca.nanorex.dungeoncrawler.game.world.objects.components.ObjectComponent;

public class GameLoop {


    public void init() {

    }

    public void loop() {
        //Iterate over each component type
        for (Class<?> type: ObjectComponent.order) {
/*
            for (GameObject object: objects) {
                if (object.hasComponent(type))
                    object.getComponent(type).update();
            }
            for (GameObject object: objects) {
                if (object.hasComponent(type))
                    object.getComponent(type).processEvents();
            }*/
        }
    }
}
