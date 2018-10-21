package ca.nanorex.dungeoncrawler;

import com.badlogic.gdx.assets.AssetManager;

import java.util.HashMap;
import java.util.Map;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

/**
 * - fileNames: Map<String, String>
 * + loadAll
 * AssetLibrary
 - fileNames: final Map<String, String>
 + loadAll(assetNames: String[]): void
 + unloadAll(assetNames: String[]): void
 + <T> get(assetName: String): T
 */

public class AssetLibrary extends AssetManager {

    DungeonCrawler game;

    /**
     * @brief Constructor
     * @param game the instance of the entire game
     */
    public AssetLibrary(DungeonCrawler game) {
        this.game = game;
        Map<String, String> fileNames = new HashMap<String, String>();
        fileNames.put("test", "png/hello.png");
        this.fileNames = fileNames;
    }

    public void load(String[] assetNames) {
        assetManager.load(fileNames.get("test"), BufferedImage.class);
        /*
        try {
            img = ImageIO.read(new File(fileNames.get("test")));
        } catch (IOException e) {
        }
        */
    }

    public void unload(String[] assetNames) {

    }

    public <T> T get(String assetName) {
        return assetManager.get(fileNames.get("test"));
    }

    private final Map<String, String> fileNames;
    private BufferedImage img;
    private AssetManager assetManager;
}
