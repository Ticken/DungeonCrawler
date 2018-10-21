package ca.nanorex.dungeoncrawler.game.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ca.nanorex.dungeoncrawler.game.world.objects.GameObject;
import ca.nanorex.dungeoncrawler.game.world.objects.components.RenderComponent;

public class ObjectRenderer extends Renderer<GameObject> {

    @Override
    public void render(GameObject object, SpriteBatch batch, float lerp) {

        if (object.hasComponent(RenderComponent.class)) {

            RenderComponent renderComponent = object.getComponent(RenderComponent.class);

            /*
            for (String layerName: renderComponent.getModelData().layers.keySet()) {
                if (true);
            }
            */

            /*
            for each layer in modeldata.layers
                if layer.visible
                    tr = getTextureRegion
                    tr.texture = getTexture(string)
                    draw tr, rc.getpos
             */
        }
    }
}
