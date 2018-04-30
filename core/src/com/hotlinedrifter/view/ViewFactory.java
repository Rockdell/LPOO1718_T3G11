package com.hotlinedrifter.view;

import com.hotlinedrifter.HotlineDrifter;
import com.hotlinedrifter.model.EntityModel;

import java.util.HashMap;
import java.util.Map;

/** A factory for EntityView objects with cache. */
public class ViewFactory {

    /** Cache to hold EntityView's objects. */
    private static Map<EntityModel.ModelType, EntityView> cache = new HashMap<EntityModel.ModelType, EntityView>();

    /** Assigns a view to a model.
     * @param game Game the model belongs to.
     * @param model Model requiring the view.
     * @return View for the desired model. */
    public static EntityView makeView(HotlineDrifter game, EntityModel model) {
        if(!cache.containsKey(model.getModelType())) {
            if(model.getModelType() == EntityModel.ModelType.DRIFTER)
                cache.put(model.getModelType(), new DrifterView(game));
            else if(model.getModelType() == EntityModel.ModelType.ENEMY)
                cache.put(model.getModelType(), new EnemyView(game));
        }

        return cache.get(model.getModelType());
    }
}
