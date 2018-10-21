package ca.nanorex.dungeoncrawler.game.render;

/**
 * Represents a layer of a model
 */
public class ModelLayer {

    /**
     * Contains the data necessary to represent the state of an instance of a model layer
     */
    protected static class Data {

        protected boolean visible;

        /**
         * Constructor for ModelLayer.Data
         *
         * @author David Neuman
         */
        public Data() {

        }
    }

    protected String texture;
    protected int textureIndex;

    /**
     *
     */
    public ModelLayer(String texture, int textureIndex) {

        this.texture = texture;
        this.textureIndex = textureIndex;
    }
}