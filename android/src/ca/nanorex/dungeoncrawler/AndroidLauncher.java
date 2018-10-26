package ca.nanorex.dungeoncrawler;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import ca.nanorex.dungeoncrawler.DungeonCrawler;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.hideStatusBar = true;
		config.useImmersiveMode = true;
		config.useAccelerometer = false;
		config.useGyroscope = false;
		config.useCompass = false;
		config.useRotationVectorSensor = false;
		initialize(new DungeonCrawler(), config);
	}
}
