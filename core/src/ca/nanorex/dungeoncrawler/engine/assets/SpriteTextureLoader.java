package ca.nanorex.dungeoncrawler.engine.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import ca.nanorex.dungeoncrawler.engine.render.sprite.SpriteTexture;

public class SpriteTextureLoader extends SynchronousAssetLoader<SpriteTexture,
        SpriteTextureLoader.Parameter> {

    public class Parameter extends AssetLoaderParameters<SpriteTexture> {

    }

    public SpriteTextureLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public SpriteTexture load(AssetManager assetManager, String fileName, FileHandle file,
                              Parameter parameter) {

        return new SpriteTexture(fileName, assetManager.get(SpriteTexture.getTextureFile(fileName),
                Texture.class));
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file,
                                                  Parameter parameter) {

        Array<AssetDescriptor> dependencies = new Array<AssetDescriptor>();
        dependencies.add(new AssetDescriptor<Texture>(SpriteTexture.getTextureFile(fileName),
                Texture.class));
        return dependencies;
    }
}
