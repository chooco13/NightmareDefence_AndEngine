package com.night.scene;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.color.Color;

import com.night.base.BaseScene;
import com.night.manager.SceneManager.SceneType;

/**
 * @Ŭ�����̸� : LoadingScene.java
 * @Ŭ�������� : �ε� �� ��� �ִ� Ŭ����
 */

public class LoadingScene extends BaseScene
{
    private Sprite loading;
    
    @Override
    public void createScene()
    {
        setBackground(new Background(Color.WHITE));
        
        loading = new Sprite(0, 0, resourcesManager.loading_region, vbom);
        
        loading.setPosition(0, 0);
        
        attachChild(loading);
    }
    
    @Override
    public void onBackKeyPressed()
    {
        return;
    }
    
    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_LOADING;
    }
    
    @Override
    public void disposeScene()
    {
        
    }
    
    public void resetAllField()
    {
        loading = null;
    }
    
}
