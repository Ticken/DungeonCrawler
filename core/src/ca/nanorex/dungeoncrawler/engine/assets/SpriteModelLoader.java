package ca.nanorex.dungeoncrawler.engine.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import ca.nanorex.dungeoncrawler.engine.render.sprite.SpriteModel;

public class SpriteModelLoader extends AsynchronousAssetLoader<SpriteModel,
        SpriteModelLoader.Parameter> {

    public class Parameter extends AssetLoaderParameters<SpriteModel> {

    }

    private SpriteModel model;

    public SpriteModelLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file,
                          Parameter parameter) {

        model = new SpriteModel(file);
    }

    @Override
    public SpriteModel loadSync(AssetManager manager, String fileName, FileHandle file,
                                Parameter parameter) {

        SpriteModel tmp = model;
        model = null;
        return tmp;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file,
                                                  Parameter parameter) {
        return null;
    }
}
