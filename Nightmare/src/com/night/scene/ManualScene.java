package com.night.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.opengl.util.GLState;

import com.night.base.BaseScene;
import com.night.manager.ResourcesManager;
import com.night.manager.SceneManager;
import com.night.manager.SceneManager.SceneType;

public class ManualScene extends BaseScene implements IOnMenuItemClickListener, IScrollDetectorListener,
        IOnSceneTouchListener
{
    
    // ---------------------------------------------
    // VARIABLES
    // ---------------------------------------------
    
    private Sprite background1;
    
    private Sprite background2;
    
    private Sprite background3;
    
    private Sprite background4;
    
    private Sprite background5;
    
    private Sprite background6;
    
    private Sprite background7;
    
    private Rectangle rectangle;
    
    private SurfaceScrollDetector mScrollDetector;
    
    // ---------------------------------------------
    // METHODS FROM SUPERCLASS
    // ---------------------------------------------
    
    @Override
    public void createScene()
    {
        createBackground();
        
        mScrollDetector = new SurfaceScrollDetector(this);
        
        setOnSceneTouchListener(this);
        setTouchAreaBindingOnActionDownEnabled(true);
        setTouchAreaBindingOnActionMoveEnabled(true);
        setOnSceneTouchListenerBindingOnActionDownEnabled(true);
        
    }
    
    @Override
    public void onBackKeyPressed()
    {
        ResourcesManager.getInstance().unloadManualTextures();
        SceneManager.getInstance().loadMenuScene(engine);
        this.disposeScene();
        this.dispose();
        
        resetAllField();
    }
    
    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_MENU;
    }
    
    @Override
    public void disposeScene()
    {
        background1.dispose();
        background2.dispose();
        background3.dispose();
        background4.dispose();
        background5.dispose();
        background6.dispose();
        background7.dispose();
    }
    
    // ---------------------------------------------
    // LISTENER
    // ---------------------------------------------
    
    // ---------------------------------------------
    // CREATE METHODS
    // ---------------------------------------------
    public void resetAllField()
    {
        rectangle = null;
        background1 = null;
        background2 = null;
        background3 = null;
        background4 = null;
        background5 = null;
        background6 = null;
        background7 = null;
        
        System.gc();
    }
    
    private void createBackground()
    {
        
        rectangle = new Rectangle(0, 0, 3080, 330, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        background1 = new Sprite(0, 0, resourcesManager.manual1_region, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        background2 = new Sprite(440, 0, resourcesManager.manual2_region, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        background3 = new Sprite(880, 0, resourcesManager.manual3_region, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        background4 = new Sprite(1320, 0, resourcesManager.manual4_region, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        background5 = new Sprite(1760, 0, resourcesManager.manual5_region, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        background6 = new Sprite(2200, 0, resourcesManager.manual6_region, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        background7 = new Sprite(2640, 0, resourcesManager.manual7_region, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        rectangle.attachChild(background1);
        rectangle.attachChild(background2);
        rectangle.attachChild(background3);
        rectangle.attachChild(background4);
        rectangle.attachChild(background5);
        rectangle.attachChild(background6);
        rectangle.attachChild(background7);
        attachChild(rectangle);
    }
    
    public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent)
    {
        this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
        
        return true;
    }
    
    @Override
    public void onScroll(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY)
    {
        
        rectangle.setPosition(rectangle.getX() + pDistanceX, rectangle.getY() + pDistanceY);
        
        if (rectangle.getY() + pDistanceY > 0)
        {
            rectangle.setY(0);
        }
        
        if (rectangle.getX() + pDistanceX > 0)
        {
            rectangle.setX(0);
        }
        
        if (rectangle.getX() < -(rectangle.getWidth() - camera.getWidth()))
        {
            rectangle.setX(-(rectangle.getWidth() - camera.getWidth()));
            
        }
        
        if (rectangle.getY() < -(rectangle.getHeight() - camera.getHeight()))
        {
            rectangle.setY(-(rectangle.getHeight() - camera.getHeight()));
        }
    }
    
    @Override
    public void onScrollStarted(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY)
    {
        
    }
    
    @Override
    public void onScrollFinished(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY)
    {
        
    }
    
    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
            float pMenuItemLocalY)
    {
        // TODO Auto-generated method stub
        return false;
    }
}