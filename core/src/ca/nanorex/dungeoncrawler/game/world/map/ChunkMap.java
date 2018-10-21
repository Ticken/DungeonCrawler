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
    }

    // Will add the chunk into the chunks List
    // Will add a row or column as needed
    public void add(Chunk chunk, Vector2 position) {
        index.equals(position.add(origin));

        // Check X
        if(index.x < 0 || index.x > chunks.size()) {
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
            }
        }

        // Check Y
        if(index.y < 0 || index.y > chunks.get((int)index.x).size()) {
            // Need to add a horizontal row

            // Checks if the row is above or below
            if(index.y > 0) { // Above
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
            }
        }

        // Insert the chunk
        chunks.get((int)index.x).remove((int)index.y);
        chunks.get((int)index.x).add((int)index.y, chunk);
    }

    // Returns the Chunk at the given position
    // Offsets position by where the origin is
    public Chunk get(Vector2 position) {
        index.equals(position.add(origin));
        return chunks.get((int)(index.x)).get((int)(index.y + position.y));
    }
}
