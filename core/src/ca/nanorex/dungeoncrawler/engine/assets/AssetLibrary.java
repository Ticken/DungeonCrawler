package ca.nanorex.dungeoncrawler.engine.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

import ca.nanorex.dungeoncrawler.engine.render.sprite.SpriteModel;
import ca.nanorex.dungeoncrawler.engine.render.sprite.SpriteTexture;

/**
 * - fileNames: Map<String, String>
 * + loadAll
 * AssetLibrary
 - fileNames: final Map<String, String>
 + loadAll(assetNames: String[]): void
 + unloadAll(assetNames: String[]): void
 + <T> get(assetName: String): T
 */

public class AssetLibrary {

    private static final Map<Class<?>, String> assetDirs = new HashMap<Class<?>, String>() {{
        put(Texture.class, "assets/textures/");
        put(SpriteTexture.class, "assets/textures/sprites/");
        put(SpriteModel.class, "assets/models/");
    }};

    private static final Map<Class<?>, String> assetExts = new HashMap<Class<?>, String>() {{
        put(Texture.class, ".png");
        put(SpriteTexture.class, ".properties");
        put(SpriteModel.class, ".json");
    }};

    public AssetManager assetManager;
    private Map<String, String> assetFullNames;

    public AssetLibrary() {

        assetManager = new AssetManager();
        assetFullNames = new HashMap<String, String>();

        assetManager.setLoader(SpriteModel.class,
                new SpriteModelLoader(new InternalFileHandleResolver()));
        assetManager.setLoader(SpriteTexture.class,
                new SpriteTextureLoader(new InternalFileHandleResolver()));

        Texture.setAssetManager(assetManager);
    }

    public <T> void load(String fileName, java.lang.Class<T> type) {

        String fullPath = assetDirs.get(type) + fileName + assetExts.get(type);
        assetFullNames.put(fileName, fullPath);
        assetManager.load(fullPath, type);
    }

    public void unload(String fileName) {
        assetManager.unload(assetFullNames.get(fileName));
    }

    public <T> T get(String fileName, Class<T> type) {
        return assetManager.get(assetFullNames.get(fileName), type);
    }

    public void update() {
        assetManager.update();
    }

    public void finishLoading() {
        assetManager.finishLoading();
    }
}
