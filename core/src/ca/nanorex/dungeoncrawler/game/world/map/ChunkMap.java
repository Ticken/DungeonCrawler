package ca.nanorex.dungeoncrawler.game.world.map;

import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;

public class ChunkMap {

    // List of Chunks
    List<List<Chunk>> chunks;

    // The indexes of the origin Chunk
    Vector2 origin;

    // The index of the List that is currently being used
    Vector2 index;

    public ChunkMap() {
        chunks = new LinkedList<List<Chunk>>();
        origin = new Vector2();
        index = new Vector2();

        chunks.add(new LinkedList<Chunk>());

        System.out.println("ChunkMap MADE");
    }

    // Will add the chunk into the chunks List
    // Will add a row or column as needed
    public void add(Chunk chunk, Vector2 position) {
        System.out.println("======Adding Chunk========");
        System.out.println("position: "+position.x+", "+position.y);
        index.set(position);
        index.add(origin);
        System.out.println("index: "+index.x+", "+index.y);

        // Check X
        if(index.x < 0 || index.x + 1 > chunks.size()) {
            System.out.println("Changing X");
            // Need to add a vertical column

            // Check if row is right or left
            if(index.x > 0) { // Right
                //Add the needed entries
                for(int x = chunks.size(); x <= index.x; x++) {
                    chunks.add(new LinkedList<Chunk>());

                    // Populate the new List with null
                    for(int y = 0; y < chunks.get(0).size(); y++) {
                        chunks.get(x).add(null);
                    }
                }
            } else { // Left
                // Add needed entries
                for(int x = 0; x > index.x; x--) {
                    chunks.add(0, new LinkedList<Chunk>());

                    // Populate the new List with null
                    for(int y = 0; y < chunks.get(1).size(); y++) {
                        chunks.get(0).add(null);
                    }
                }

                //Update origin's X
                origin.x = origin.x - index.x;
                index.x = 0;
            }
        }

        // Check Y
        //System.out.println(index.y+" < 0 || "+(index.y+1)+" > "+chunks.get((int)index.x).size());
        if(index.y < 0 || index.y + 1 > chunks.get((int)index.x).size()) {
            System.out.println("Changing Y");
            // Need to add a horizontal row

            // Checks if the row is above or below
            if(index.y >= 0) { // Above
                // Add the entries to each List
                for(int x = 0; x < chunks.size(); x++) {
                    // Add the needed entries
                    for(int y = chunks.get(x).size(); y <= index.y; y++) {
                        chunks.get(x).add(null);
                    }
                }
            } else { // Below
                // Add the entries to each List
                for(int x = 0; x < chunks.size(); x++) {
                    // Add the needed entries
                    for(int y = 0; y > index.y; y--) {
                        chunks.get(x).add(0 , null);
                    }
                }

                // Update origin's Y
                origin.y = origin.y - index.y;
                index.y = 0;
            }
        }

        // Insert the chunk
        chunks.get((int)index.x).remove((int)index.y);
        chunks.get((int)index.x).add((int)index.y, chunk);

        System.out.println("origin: "+origin.x+", "+origin.y);
        System.out.println("=====Chunk Added=====");
        System.out.println();
    }

    // Returns the Chunk at the given position
    // Offsets position by where the origin is
    public Chunk get(Vector2 position) {
        System.out.println("=====Get Chunk=======");
        System.out.println("position: "+position.x+", "+position.y);
        index.set(position);
        index.add(origin);
        System.out.println("index: "+index.x+", "+index.y);
        System.out.println();
        if (index.x + 1 > chunks.size() || index.y + 1 > chunks.get((int)index.x).size()) {
            return null;
        }
        return chunks.get((int)(index.x)).get((int)(index.y + position.y));
    }
}
