package ca.nanorex.dungeoncrawler.game.world.map;

import com.badlogic.gdx.math.Vector2;

public class Map {

    ChunkMap chunkMap;

    public Map() {
        chunkMap = new ChunkMap();

        //JoelTest
        chunkMap.add(new Chunk(), new Vector2(0,0));

        chunkMap.add(new Chunk(), new Vector2(1,0));
        chunkMap.add(new Chunk(), new Vector2(0,1));
        chunkMap.add(new Chunk(), new Vector2(3,2));

        chunkMap.add(new Chunk(), new Vector2(-1,0));
        chunkMap.add(new Chunk(), new Vector2(0,-1));
        chunkMap.add(new Chunk(), new Vector2(-2,-3));

        chunkMap.add(new Chunk(), new Vector2(3,2));
        chunkMap.add(new Chunk(), new Vector2(3,0));

        if (chunkMap.get(new Vector2(0,0)) == null) {
            System.out.println("Nothing here boss");
        } else {
            System.out.println("CHUNK GOT");
        }

        if (chunkMap.get(new Vector2(-1,0)) == null) {
            System.out.println("Nothing here boss");
        } else {
            System.out.println("CHUNK GOT");
        }

        if (chunkMap.get(new Vector2(1,1)) == null) {
            System.out.println("Nothing here boss");
        } else {
            System.out.println("CHUNK GOT");
        }

        if (chunkMap.get(new Vector2(5,5)) == null) {
            System.out.println("Nothing here boss");
        } else {
            System.out.println("CHUNK GOT");
        }
    }
}
