package com.night.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;
import org.andengine.util.color.Color;

import com.night.base.BaseScene;
import com.night.manager.SceneManager.SceneType;

/**
 * @Ŭ�����̸� : SplashScene.java
 * @Ŭ�������� : ��ó���� �ΰ� ����ִ� Ŭ����
 */

public class SplashScene extends BaseScene
{
    private Sprite splash;
    
    @Override
    public void createScene()
    {
        setBackground(new Background(Color.WHITE));
        
        splash = new Sprite(0, 0, resourcesManager.splash_region, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        splash.setPosition(0, 0);
        
        attachChild(splash);
    }
    
    @Override
    public void onBackKeyPressed()
    {
        return;
    }
    
    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_SPLASH;
    }
    
    @Override
    public void disposeScene()
    {
        splash.detachSelf();
        splash.dispose();
        this.detachSelf();
        this.dispose();
    }
}
