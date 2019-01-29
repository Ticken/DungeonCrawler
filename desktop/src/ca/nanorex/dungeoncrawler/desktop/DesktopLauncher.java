package ca.nanorex.dungeoncrawler.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ca.nanorex.dungeoncrawler.DungeonCrawler;

import static com.badlogic.gdx.Files.FileType;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Crawler";
		config.addIcon("icons/icon_desktop_128.png", FileType.Internal);
		config.addIcon("icons/icon_desktop_32.png", FileType.Internal);
        config.height = 720;
        config.width = 1280;
		new LwjglApplication(new DungeonCrawler(), config);
	}
}
