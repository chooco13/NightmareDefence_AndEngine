package com.night.mare;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.view.KeyEvent;

import com.night.manager.ResourcesManager;
import com.night.manager.SceneManager;
import com.night.scene.GameScene;
import com.night.scene.MainMenuScene;

/**
 * @클래스이름 : GameActivity.java
 * @클래스설명 : 가장 먼저 실행 되는 클래스, 기본적인 설정을 마친 후 메인 메뉴를 띄워준다.
 */

public class GameActivity extends BaseGameActivity
{
    
    private BoundCamera camera;
    
    private boolean gameStage;
    
    // ---------------------------------------------
    // Engine
    // ---------------------------------------------
    
    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions)
    {
        return new LimitedFPSEngine(pEngineOptions, 60);
    }
    
    @Override
    public EngineOptions onCreateEngineOptions()
    {
        camera = new BoundCamera(0, 0, 550, 330);
        
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                new FillResolutionPolicy(), this.camera);
        
        engineOptions.getRenderOptions().setDithering(true);
        
        loadStageDB();
        
        engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        return engineOptions;
    }
    
    // ---------------------------------------------
    // METHODS
    // ---------------------------------------------
    
    @Override
    protected void onPause()
    {
        super.onPause();
        if (this.isGameLoaded())
        {
            if (MainMenuScene.music.isPlaying() == true)
            {
                MainMenuScene.music.pause();
            }
            else if (GameScene.music.isPlaying() == true)
            {
                gameStage = true;
                mEngine.stop();
                GameScene.checkTimer.cancel();
                GameScene.music.pause();
            }
        }
    }
    
    @Override
    protected synchronized void onResume()
    {
        super.onResume();
        
        if (this.isGameLoaded())
        {
            if (gameStage == true)
            {
                System.exit(0);
            }
            else
            {
                MainMenuScene.music.play();
            }
        }
    }
    
    @Override
    public void onGameCreated()
    {
        super.onGameCreated();
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        
        if (this.isGameLoaded())
        {
            System.exit(0);
        }
    }
    
    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException
    {
        ResourcesManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }
    
    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException
    {
        SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
    }
    
    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException
    {
        mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
            @Override
            public void onTimePassed(final TimerHandler pTimerHandler)
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                SceneManager.getInstance().createMenuScene();
            }
        }));
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
    
    // ---------------------------------------------
    // LOAD DATABASE
    // ---------------------------------------------
    
    @SuppressLint("SdCardPath")
    public void loadStageDB()
    {
        File folder = new File("/data/data/com.night.mare/databases");
        folder.mkdirs();
        File outfile = new File("/data/data/com.night.mare/databases/STAGE");
        
        if (outfile.length() <= 0)
        {
            AssetManager assetManager = getResources().getAssets();
            try
            {
                InputStream is = assetManager.open("database/STAGE.db", AssetManager.ACCESS_BUFFER);
                long filesize = is.available();
                byte[] tempdata = new byte[(int) filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                FileOutputStream fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    // ---------------------------------------------
    // LISTENER
    // ---------------------------------------------
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
        }
        return false;
    }
    
}
