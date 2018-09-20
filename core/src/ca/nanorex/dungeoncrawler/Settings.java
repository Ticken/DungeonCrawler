package ca.nanorex.dungeoncrawler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Settings {

    DungeonCrawler game;

    // Settings
    public float guiScale = 1.0f, volumeGlobal = 1.0f, volumeMusic = 1.0f, volumeSound = 1.0f;

    public Settings(DungeonCrawler game){
        this.game = game;
    }

    public void saveToFile(){
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("settings.properties");

            // set the properties value
            prop.setProperty("guiScale", Float.toString(guiScale));
            prop.setProperty("volumeGlobal", Float.toString(volumeGlobal));
            prop.setProperty("volumeMusic", Float.toString(volumeMusic));
            prop.setProperty("volumeSound", Float.toString(volumeSound));

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void loadFromFile(){
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("settings.properties");

            // load a properties file
            prop.load(input);

            // get the property value
            guiScale = Float.valueOf(prop.getProperty("guiScale"));
            volumeGlobal = Float.valueOf(prop.getProperty("volumeGlobal"));
            volumeMusic = Float.valueOf(prop.getProperty("volumeMusic"));
            volumeSound = Float.valueOf(prop.getProperty("volumeSound"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setToDefault(){
        guiScale = 1.0f;
        volumeGlobal = 1.0f;
        volumeMusic = 1.0f;
        volumeSound = 1.0f;
        saveToFile();
    }
}
