package com.night.manager;

import java.util.Timer;
import java.util.TimerTask;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import com.night.base.BaseScene;
import com.night.scene.GameScene;
import com.night.scene.LoadingScene;
import com.night.scene.MainMenuScene;
import com.night.scene.ManualScene;
import com.night.scene.SplashScene;
import com.night.scene.StageSelectScene;
import com.night.scene.StatusScene;

/**
 * @클래스이름 : SceneManager.java
 * @클래스설명 : 게임 화면 전환 관련 클래스
 */

public class SceneManager
{
    // ---------------------------------------------
    // SCENES
    // ---------------------------------------------
    
    private BaseScene splashScene;
    
    private BaseScene stageSelectScene;
    
    private BaseScene menuScene;
    
    private BaseScene gameScene;
    
    private BaseScene loadingScene;
    
    private BaseScene statusScene;
    
    private BaseScene currentScene;
    
    private BaseScene manualScene;
    
    // ---------------------------------------------
    // VARIABLES
    // ---------------------------------------------
    
    private static final SceneManager INSTANCE = new SceneManager();
    
    private SceneType currentSceneType = SceneType.SCENE_SPLASH;
    
    private Engine engine = ResourcesManager.getInstance().engine;
    
    public enum SceneType
    {
        SCENE_SPLASH, SCENE_MENU, SCENE_GAME, SCENE_LOADING, SCENE_STAGESELECT, SCENE_STATUS
    }
    
    // ---------------------------------------------
    // SCENE METHODS
    // ---------------------------------------------
    
    public void setScene(BaseScene scene)
    {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }
    
    public void setScene(SceneType sceneType)
    {
        switch (sceneType)
        {
            case SCENE_MENU:
                
                setScene(menuScene);
                break;
            
            case SCENE_GAME:
                
                setScene(gameScene);
                break;
            
            case SCENE_SPLASH:
                
                setScene(splashScene);
                break;
            
            case SCENE_LOADING:
                
                setScene(loadingScene);
                break;
            
            case SCENE_STAGESELECT:
                
                setScene(stageSelectScene);
                break;
            
            case SCENE_STATUS:
                
                setScene(statusScene);
                break;
            
            default:
                break;
        }
    }
    
    // ---------------------------------------------
    
    public void createStageSelectScene()
    {
        ResourcesManager.getInstance().loadMenuResources();
        
        stageSelectScene = new StageSelectScene();
        loadingScene = new LoadingScene();
        
        SceneManager.getInstance().setScene(stageSelectScene);
    }
    
    public void loadStageSelectScene()
    {
        stageSelectScene = new StageSelectScene();
        setScene(stageSelectScene);
    }
    
    // ---------------------------------------------
    
    public void createMenuScene()
    {
        ResourcesManager.getInstance().loadMenuResources();
        
        menuScene = new MainMenuScene();
        loadingScene = new LoadingScene();
        
        SceneManager.getInstance().setScene(menuScene);
        
        ResourcesManager.getInstance().unloadSplashScreen();
        disposeSplashScene();
    }
    
    public void loadMenuScene(final Engine mEngine)
    {
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            @Override
            public void onTimePassed(final TimerHandler pTimerHandler)
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadMenuTextures();
                setScene(menuScene);
            }
        }));
    }
    
    // ---------------------------------------------
    
    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback)
    {
        ResourcesManager.getInstance().loadSplashScreen();
        
        splashScene = new SplashScene();
        currentScene = splashScene;
        
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }
    
    private void disposeSplashScene()
    {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene = null;
    }
    
    // ---------------------------------------------
    
    public void createManualScene()
    {
        setScene(loadingScene);
        
        engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            @Override
            public void onTimePassed(final TimerHandler pTimerHandler)
            {
                engine.unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadManualTextures();
                manualScene = new ManualScene();
                SceneManager.getInstance().setScene(manualScene);
            }
        }));
    }
    
    public void loadGameScene(final Engine mEngine)
    {
        setScene(loadingScene);
        
        TimerTask checkTask;
        checkTask = new TimerTask() {
            
            @Override
            public void run()
            {
                mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
                    @Override
                    public void onTimePassed(final TimerHandler pTimerHandler)
                    {
                        mEngine.unregisterUpdateHandler(pTimerHandler);
                        ResourcesManager.getInstance().loadGameResources();
                        gameScene = new GameScene();
                        setScene(gameScene);
                    }
                }));
            }
        };
        
        Timer mTimer = new Timer();
        mTimer.schedule(checkTask, 1000);
        
    }
    
    // ---------------------------------------------
    
    public void loadStatusScene()
    {
        statusScene = new StatusScene();
        setScene(statusScene);
    }
    
    // ---------------------------------------------
    
    // ---------------------------------------------
    // GETTERS AND SETTERS
    // ---------------------------------------------
    
    public static SceneManager getInstance()
    {
        return INSTANCE;
    }
    
    public SceneType getCurrentSceneType()
    {
        return currentSceneType;
    }
    
    public BaseScene getCurrentScene()
    {
        return currentScene;
    }
    
}
