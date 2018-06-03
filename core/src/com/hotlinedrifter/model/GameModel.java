package com.hotlinedrifter.model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;
import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.layout.GameRoom;
import com.hotlinedrifter.model.attacks.AttackModel;
import com.hotlinedrifter.model.attacks.DrifterAttackModel;
import com.hotlinedrifter.model.bullets.BulletModel;
import com.hotlinedrifter.model.bullets.DrifterBulletModel;
import com.hotlinedrifter.model.bullets.JudgementBulletModel;
import com.hotlinedrifter.model.entities.*;
import com.hotlinedrifter.model.objects.TileObjectModel;
import com.hotlinedrifter.model.objects.WallModel;
import com.hotlinedrifter.model.objects.WaterModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic implementation of a level.
 */
public class GameModel {

    /**
     * Max number of spiders in the level.
     */
    private static final int MAX_NUMBER_SPIDERS = 50;

    /**
     * Singleton instance of this class
     */
    private static GameModel    _instance;

    /**
     * Drifter.
     */
    private DrifterModel        _drifter;

    /**
     * Spiders.
     */
    private List<SpiderModel>   _spiders;

    /**
     * Judgement.
     */
    private JudgementModel      _judgement;

    /**
     * Bullets.
     */
    private List<BulletModel>   _bullets;

    /**
     * Drifter's bullets pool.
     */
    private Pool<DrifterBulletModel>    _dBulletPool = new Pool<DrifterBulletModel>(){
        @Override
        protected DrifterBulletModel newObject() {
            return new DrifterBulletModel(0,0);
        }
    };

    /**
     * Judgement's bullets pool.
     */
    private Pool<JudgementBulletModel>  _jBulletPool = new Pool<JudgementBulletModel>(){
        @Override
        protected JudgementBulletModel newObject() {
            return new JudgementBulletModel(0,0);
        }
    };

    /**
     * Drifter's attacks pool.
     */
    private Pool<DrifterAttackModel>    _dAttackPool = new Pool<DrifterAttackModel>() {
        @Override
        protected DrifterAttackModel newObject() {
            return new DrifterAttackModel(0, 0);
        }
    };

    /**
     * Level.
     */
    private GameRoom _gameRoom;

    /**
     * Tile map.
     */
    private static TiledMap _map;

    /**
     * List of tile object's models.
     */
    private List<TileObjectModel>   _tileObjectModels;

    /**
     * Creates an object GameModel.
     */
    private GameModel() {
        _drifter = new DrifterModel(35, 56);

        _spiders = new ArrayList<SpiderModel>();

        _bullets = new ArrayList<BulletModel>();

        _tileObjectModels = new ArrayList<TileObjectModel>();
    }

    /**
     * Returns the singleton instance of this class.
     * @return Singleton instance.
     */
    public static GameModel getInstance() {

        if(_instance == null)
            _instance = new GameModel();

        return _instance;
    }

    /**
     * Returns the drifter.
     * @return Drifter.
     */
    public DrifterModel getDrifter() {
        return _drifter;
    }

    /**
     * Returns the spiders.
     * @return Spiders.
     */
    public List<SpiderModel> getSpiders() {
        return _spiders;
    }

    /**
     * Returns the judgement.
     * @return Judgement.
     */
    public JudgementModel getJudgement() {
        return _judgement;
    }

    /**
     * Returns the bullets.
     * @return Bullets.
     */
    public List<BulletModel> getBullets() {
        return _bullets;
    }

    /**
     * Returns the Map of the level.
     * @return TiledMap of the level.
     */
    public TiledMap getMap() { return _map; }

    public List<TileObjectModel> getTileObjectModels() {
        return _tileObjectModels;
    }

    /**
     * Generates a layout.
     * @param game Game of the level.
     */
    public void generateLevel(HotlineDrifter game) {

        _gameRoom = new GameRoom();

        _map = game.getAssetManager().get("images/WorldMap.tmx");

        createCollisionObjects(_map);

        for(int i = 0; i < MAX_NUMBER_SPIDERS; ++i)
            spawnSpider();

        spawnJudgement();
    }

    /**
     * Parses collision objects from the given Map into the World.
     * @param map TileMap with the collision objects.
     */
    private void createCollisionObjects(TiledMap map) {

        for (MapObject object : map.getLayers().get("walls").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            _tileObjectModels.add(new WallModel(rect));
        }

        for (MapObject object : map.getLayers().get("water").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            _tileObjectModels.add(new WaterModel(rect));
        }
    }

    /**
     * Creates an AttackModel for the entity.
     * @param type Type of entity who created the attack.
     * @return Newly created AttackModel.
     */
    public AttackModel createAttack(BaseModel.ModelType type) {

        AttackModel attack = null;

        if(type == BaseModel.ModelType.DRIFTER)
            attack = _dAttackPool.obtain();

        return attack;
    }

    /**
     * Creates an BulletModel for the entity.
     * @param type Type of entity who created the bullet.
     * @return Newly created BulletModel.
     */
    public BulletModel createBullet(BaseModel.ModelType type) {

        BulletModel bullet = null;

        if(type == BaseModel.ModelType.DRIFTER)
            bullet = _dBulletPool.obtain();
        else if(type == BaseModel.ModelType.JUDGEMENT)
            bullet = _jBulletPool.obtain();

        _bullets.add(bullet);

        return bullet;
    }

    /**
     * Spawns a spider in the map.
     * @return Newly spawn SpiderModel.
     */
    public SpiderModel spawnSpider() {

        Vector2 spawnPosition = _gameRoom.getSpawnPoint();

        SpiderModel newSpider = new SpiderModel(spawnPosition.x, spawnPosition.y);

        _spiders.add(newSpider);

        return newSpider;
    }

    /**
     * Spawns a judgement in the map.
     * @return Newly spawn JudgementModel.
     */
    public JudgementModel spawnJudgement() {

        _judgement = new JudgementModel(35, 30);

        return _judgement;
    }

    /**
     * Removes a body from the map.
     * @param body Body to be removed.
     */
    public void remove(Body body) {

        BaseModel model = (BaseModel) body.getUserData();

        if(model instanceof BulletModel) {
            _bullets.remove(model);

            if(model.getModelType() == BaseModel.ModelType.DBULLET)
                _dBulletPool.free((DrifterBulletModel) model);
            else
                _jBulletPool.free((JudgementBulletModel) model);
        }
        else if(model instanceof AttackModel) {

            if(model.getModelType() == BaseModel.ModelType.DATTACK)
                _dAttackPool.free((DrifterAttackModel) model);
        }
        else if(model instanceof SpiderModel)
            _spiders.remove(model);
        else if(model instanceof JudgementModel) {
            _judgement = null;
        }
    }
}