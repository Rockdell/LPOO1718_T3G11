package com.hotlinedrifter.ai;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.hotlinedrifter.model.GameModel;
import com.hotlinedrifter.model.entities.EntityModel;
import com.hotlinedrifter.model.entities.JudgementModel;
import com.hotlinedrifter.model.entities.SpiderModel;
import com.hotlinedrifter.model.entities.actions.BaseAction;
import com.hotlinedrifter.model.entities.actions.Shoot;
import com.hotlinedrifter.model.entities.actions.Still;
import com.hotlinedrifter.model.entities.actions.Walk;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the AI.
 */
public class AIController {

    /**
     * List of map coordinates, to be used in the path-finding algorithm.
     */
    private List<List<Vector2>>     _vectors;

    /**
     * Graph representing the map.
     */
    private Graph                   _graph;

    /**
     * Singleton instance of this class
     */
    private static AIController _instance;

    /**
     * Creates an object AIController.
     */
    private AIController() {

        MapProperties prop = GameModel.getInstance().getMap().getProperties();
        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);

        List<List<Character>> level = new ArrayList<List<Character>>(mapWidth);

        TiledMapTileLayer _layer = (TiledMapTileLayer) GameModel.getInstance().getMap().getLayers().get("path");

        for(int x = 0; x < _layer.getWidth(); ++x) {

            List<Character> tmp = new ArrayList<Character>(mapHeight);

            for(int y = 0; y < _layer.getHeight(); ++y) {

                if(_layer.getCell(x, y) == null)
                    tmp.add(' ');
                else if(_layer.getCell(x, y).getTile().getId() == 2 || _layer.getCell(x, y).getTile().getId() == 3)
                    tmp.add('*');
            }

            level.add(x, tmp);
        }

        _graph = new Graph();

        _vectors = new ArrayList<List<Vector2>>();

        for(int x = 0; x < level.size(); ++x) {

            List<Vector2> tmp = new ArrayList<Vector2>();

            for (int y = 0; y < level.get(x).size(); ++y) {
                    tmp.add(new Vector2(x, y));
                    _graph.addNode(tmp.get(y));
            }

            _vectors.add(x, tmp);
        }

        for(int x = 0; x < level.size(); ++x) {
            for (int y = 0; y < level.get(x).size(); ++y) {

                if(level.get(x).get(y) == '*')
                    continue;

                //Up
                if (level.get(x).get(y + 1) != '*')
                    _graph.addEdge(_vectors.get(x).get(y), _vectors.get(x).get(y + 1));

                //Down
                if (level.get(x).get(y - 1) != '*')
                    _graph.addEdge(_vectors.get(x).get(y), _vectors.get(x).get(y - 1));

                //Left
                if (level.get(x - 1).get(y) != '*')
                    _graph.addEdge(_vectors.get(x).get(y), _vectors.get(x - 1).get(y));

                //Right
                if (level.get(x + 1).get(y) != '*')
                    _graph.addEdge(_vectors.get(x).get(y), _vectors.get(x + 1).get(y));
            }
        }
    }

    /**
     * Returns the singleton instance of this class.
     * @return Singleton instance.
     */
    public static AIController getController() {

        if(_instance == null)
            _instance = new AIController();

        return _instance;
    }

    /**
     * Computes the next action for the entity.
     * @param entity Entity asking for a new action.
     * @return Action computed.
     */
    public BaseAction getNextAction(EntityModel entity) {

        BaseAction nextAction = null;

        Vector2 drifterTilePos = GameModel.getInstance().getDrifter().getTilePosition();
        Vector2 entityTilePos = entity.getTilePosition();

        float distanceDrifterEntity = (float) Math.sqrt(Math.pow((entityTilePos.x - drifterTilePos.x), 2) + Math.pow((entityTilePos.y - drifterTilePos.y), 2));

        if(distanceDrifterEntity > 20f)
            nextAction = new Still(entity, new Vector2(0f, -1f));
        else if(distanceDrifterEntity > 10f)
            nextAction = new Walk(entity, findBestPath(entity));
        else {
            if(entity instanceof SpiderModel)
                nextAction = new Walk(entity, findBestPath(entity));
            else if(entity instanceof JudgementModel) {
                if (entity.getActionDelay() < 0)
                    nextAction = new Shoot(entity, GameModel.getInstance().getDrifter().getPosition());
                else
                    nextAction = new Still(entity, entity.getAction().getFacingDirection());
            }
        }

        return nextAction;
    }

    /**
     * Finds best path between the entity position and the drifter's position.
     * @param entity Entity asking for the path.
     * @return Best path between the entity and the drifter.
     */
    private Vector2 findBestPath(EntityModel entity) {

        Vector2 drifterTilePos = GameModel.getInstance().getDrifter().getTilePosition();
        Vector2 drifterGraphPos = new Vector2(drifterTilePos.x / 0.8f, drifterTilePos.y / 0.8f);

        Vector2 entityTilePos = entity.getTilePosition();
        Vector2 entityGraphPos = new Vector2(entityTilePos.x / 0.8f, entityTilePos.y / 0.8f);

        _graph.dijkstra(_vectors.get((int) Math.rint(entityGraphPos.x)).get((int) Math.rint(entityGraphPos.y)));

        List<Vector2> path = _graph.getPath(_vectors.get((int) Math.rint(drifterGraphPos.x)).get((int) Math.rint(drifterGraphPos.y)));

        Vector2 nextPath;

        if (path == null)
            nextPath = entity.getAction().getFacingDirection();
        else if (path.size() == 1)
            nextPath = new Vector2(entityGraphPos.x - path.get(0).x, entityGraphPos.y - path.get(0).y);
        else
            nextPath = new Vector2(path.get(1).x - path.get(0).x, path.get(1).y - path.get(0).y);

        return nextPath;
    }
}
