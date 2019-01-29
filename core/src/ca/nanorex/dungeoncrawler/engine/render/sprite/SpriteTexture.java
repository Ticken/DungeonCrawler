package ca.nanorex.dungeoncrawler.engine.render.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.PropertiesUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//Todo: should throw error if properties file not found
public class SpriteTexture {

    private Texture texture;
    private Vector2 tileSize;

    public SpriteTexture(String internalPath) {
        this(internalPath, new Texture(getTextureFile(internalPath)));
    }

    public SpriteTexture(String internalPath, Texture texture) {

        this.texture = texture;

        ObjectMap<String, String> properties = new ObjectMap<String, String>();

        try {
            PropertiesUtils.load(properties, Gdx.files.internal(internalPath).reader());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        int w = Integer.valueOf(properties.get("tile_width"));
        int h = Integer.valueOf(properties.get("tile_height"));

        this.tileSize = new Vector2(w, h);
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getTileSize() {
        return tileSize;
    }

    public static String getTextureFile(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf('.')) + ".png";
    }
}
