package ca.nanorex.dungeoncrawler.game.world.objects.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private Map<String, ModelLayer> layers;

    public Model(String json_file_path) {
        layers = new HashMap();
        JsonReader json_reader = new JsonReader();
        JsonValue json_file = json_reader.parse(Gdx.files.internal(json_file_path));

        int tile_width = json_file.getInt("tile_width");
        int tile_height = json_file.getInt("tile_height");
        int directions = json_file.getInt("directions");
        JsonValue layers = json_file.get("layers");

        Texture tex = new Texture(Gdx.files.internal(json_file.get("sprite_sheet_path").asString()));
        TextureRegion sprite_sheet = new TextureRegion(tex);
        TextureRegion[][] tiles = sprite_sheet.split(tile_width, tile_height);

        for (int i = 0; i < layers.size; i++)
            this.layers.put( layers.get(i).get("name").asString(),
                    new ModelLayer(layers.get(i), tiles, directions, i) );
    }

    public ModelLayer getLayer(String layer) {
        return layers.get(layer);
    }

    //public TextureRegion getTest() { return sprite_sheet_test; }
}
