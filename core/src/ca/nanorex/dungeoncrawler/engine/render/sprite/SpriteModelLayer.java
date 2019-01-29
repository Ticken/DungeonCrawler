package ca.nanorex.dungeoncrawler.engine.render.sprite;

class SpriteModelLayer {

    static class InstanceData {

        boolean visible;

        InstanceData(boolean visible) {
            this.visible = visible;
        }
    }

    String name;
    String texture;
    int textureIndex;

    SpriteModelLayer(String name, String texture, int textureIndex) {

        this.name = name;
        this.texture = texture;
        this.textureIndex = textureIndex;
    }
}
