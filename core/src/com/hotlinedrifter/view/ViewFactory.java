package com.hotlinedrifter.view;

import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.model.BaseModel;
import com.hotlinedrifter.view.bullets.DrifterBulletView;
import com.hotlinedrifter.view.bullets.JudgementBulletView;
import com.hotlinedrifter.view.entities.*;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory for BaseView objects with cache.
 */
public class ViewFactory {

    /**
     *  Cache to hold BaseView's objects.
     */
    private static Map<BaseModel.ModelType, BaseView> cache = new HashMap<BaseModel.ModelType, BaseView>();

    /**
     * Assigns a view to a model.
     * @param game Game the model belongs to.
     * @param model Model requiring the view.
     * @return View for the desired model.
     */
    public static BaseView makeView(HotlineDrifter game, BaseModel model) {
        if(!cache.containsKey(model.getModelType())) {
            if(model.getModelType() == BaseModel.ModelType.DRIFTER)
                cache.put(model.getModelType(), new DrifterView(game));
            else if(model.getModelType() == BaseModel.ModelType.SPIDER)
                cache.put(model.getModelType(), new SpiderView(game));
            else if(model.getModelType() == BaseModel.ModelType.JUDGEMENT)
                cache.put(model.getModelType(), new JudgementView(game));
            else if(model.getModelType() == BaseModel.ModelType.DBULLET)
                cache.put(model.getModelType(), new DrifterBulletView(game));
            else if(model.getModelType() == BaseModel.ModelType.JBULLET)
                cache.put(model.getModelType(), new JudgementBulletView(game));
        }

        return cache.get(model.getModelType());
    }
}
