
package com.hotlinedrifter.layout;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Room of the game.
 */
public class GameRoom {

    /**
     * List of spawn positions across the room.
     */
    private List<Vector2> _spawnPositions;

    /**
     * Creates an object GameRoom.
     */
    public GameRoom() {
        _spawnPositions = new ArrayList<Vector2>();

        _spawnPositions.add(new Vector2(64, 56));
        _spawnPositions.add(new Vector2(66, 50));
        _spawnPositions.add(new Vector2(48, 55));
        _spawnPositions.add(new Vector2(40, 50));
        _spawnPositions.add(new Vector2(4, 54));
        _spawnPositions.add(new Vector2(3, 45));
        _spawnPositions.add(new Vector2(5, 35));
        _spawnPositions.add(new Vector2(19, 37));
        _spawnPositions.add(new Vector2(20, 45));
        _spawnPositions.add(new Vector2(5, 26));
        _spawnPositions.add(new Vector2(7.5f, 8.5f));
        _spawnPositions.add(new Vector2(21, 9));
        _spawnPositions.add(new Vector2(36, 17));
        _spawnPositions.add(new Vector2(46.5f, 5.5f));
        _spawnPositions.add(new Vector2(34, 2));
        _spawnPositions.add(new Vector2(65.5f, 26));
        _spawnPositions.add(new Vector2(52, 37));
    }

    /**
     * Returns a random spawn position from the list.
     * @return Random spawn position.
     */
    public Vector2 getSpawnPoint() {
        return _spawnPositions.get(new Random().nextInt(_spawnPositions.size()));
    }

}