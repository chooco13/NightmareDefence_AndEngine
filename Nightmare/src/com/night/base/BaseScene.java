package com.night.base;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.Activity;

import com.night.manager.ResourcesManager;
import com.night.manager.SceneManager.SceneType;

/**
 * @클래스이름 : BaseScene.java
 * @클래스설명 : 기초적인 설정을 하는 클래스
 */

public abstract class BaseScene extends Scene
{
    // ---------------------------------------------
    // VARIABLES
    // ---------------------------------------------
    
    public static Engine engine;
    
    protected Activity activity;
    
    protected ResourcesManager resourcesManager;
    
    protected VertexBufferObjectManager vbom;
    
    protected BoundCamera camera;
    
    // ---------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------
    
    public BaseScene()
    {
        this.resourcesManager = ResourcesManager.getInstance();
        this.engine = resourcesManager.engine;
        this.activity = resourcesManager.activity;
        this.vbom = resourcesManager.vbom;
        this.camera = resourcesManager.camera;
        createScene();
    }
    
    // ---------------------------------------------
    // ABSTRACTION
    // ---------------------------------------------
    
    public abstract void createScene();
    
    public abstract void onBackKeyPressed();
    
    public abstract SceneType getSceneType();
    
    public abstract void disposeScene();
    
}
