package com.hotlinedrifter.layout;

import com.hotlinedrifter.model.DrifterModel;
import com.hotlinedrifter.model.EnemyModel;

import java.util.ArrayList;
import java.util.List;

/** Basic implementation of a level. */
public class GameLevel {

    /** Singleton instance of this class */
    private static GameLevel _instance;

    /** Drifter. */
    private DrifterModel        _drifter;

    /** Enemies. */
    private List<EnemyModel>    _enemies;

    /** Creates an object GameLevel. */
    private GameLevel() {
        _drifter = new DrifterModel(100, 100);

        _enemies = new ArrayList<EnemyModel>();
        for(int i = 0; i < 1; i++)
            _enemies.add(new EnemyModel(50, 50));
    }

    /** Returns the singleton instance of this class.
     * @return Singleton instance. */
    public static GameLevel getInstance() {

        if(_instance == null)
            _instance = new GameLevel();

        return _instance;
    }

    /** Returns the drifter.
     * @return Drifter. */
    public DrifterModel getDrifter() {
        return _drifter;
    }

    /** Returns the enemies.
     * @return Enemies. */
    public List<EnemyModel> getEnemies() {
        return _enemies;
    }

    /** Generates a layout. */
    public void generateLevel() {
        //TODO
    }
}
