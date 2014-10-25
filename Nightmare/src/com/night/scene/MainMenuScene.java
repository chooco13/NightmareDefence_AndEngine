package com.night.scene;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import android.widget.Toast;

import com.night.base.BaseScene;
import com.night.manager.SceneManager;
import com.night.manager.SceneManager.SceneType;

/**
 * @Ŭ�����̸� : MainMenuScene.java
 * @Ŭ�������� : ���� �޴��� ����ִ� Ŭ����, ��ư�� ������ ��ư�� ���� �۾��� ���� �ȴ�.
 */

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener
{
    
    // ---------------------------------------------
    // VARIABLES
    // ---------------------------------------------
    
    private final int MENU_PLAY = 0;
    
    private final int MENU_Manual = 1;
    
    // ---------------------------------------------
    
    private MenuScene menuChildScene;
    
    // ---------------------------------------------
    
    private long backKeyPressedTime = 0;
    
    private Toast toast;
    
    // ---------------------------------------------
    
    private int temp = 0;
    
    private Sprite background;
    
    public static Music music;
    
    private IMenuItem playMenuItem;
    
    private IMenuItem manualMenuItem;
    
    // ---------------------------------------------
    // METHODS FROM SUPERCLASS
    // ---------------------------------------------
    
    @Override
    public void createScene()
    {
        createBackground();
        createMenuChildScene();
        temp = 0;
        
        MusicFactory.setAssetBasePath("mfx/");
        
        try
        {
            music = MusicFactory.createMusicFromAsset(engine.getMusicManager(), activity, "menu.mid");
            
            music.play();
            music.setLooping(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void onBackKeyPressed()
    {
        if (temp == 1)
        {
            temp = 0;
            createMenuChildScene();
        }
        else
        {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000)
            {
                backKeyPressedTime = System.currentTimeMillis();
                showToast();
                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000)
            {
                System.exit(0);
                toast.cancel();
            }
        }
    }
    
    private void showToast()
    {
        toast = Toast.makeText(activity, "\'�ڷ�\'��ư�� �ѹ� �� �����ø� ����˴ϴ�.", Toast.LENGTH_SHORT);
        toast.show();
    }
    
    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_MENU;
    }
    
    @Override
    public void disposeScene()
    {
    }
    
    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
            float pMenuItemLocalY)
    {
        switch (pMenuItem.getID())
        {
            case MENU_PLAY:
                resetAllField();
                SceneManager.getInstance().loadStageSelectScene();
                return true;
            case MENU_Manual:
                resetAllField();
                SceneManager.getInstance().createManualScene();
                return true;
            default:
                return false;
        }
    }
    
    public void resetAllField()
    {
        background = null;
        manualMenuItem = null;
        playMenuItem = null;
        
        System.gc();
    }
    
    // ---------------------------------------------
    // CLASS LOGIC
    // ---------------------------------------------
    
    private void createBackground()
    {
        background = new Sprite(0, 0, resourcesManager.menu_background_region, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        attachChild(background);
    }
    
    private void createMenuChildScene()
    {
        menuChildScene = new MenuScene(camera);
        menuChildScene.setPosition(0, 0);
        
        playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourcesManager.play_region, vbom),
                1.2f, 1);
        
        manualMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_Manual,
                resourcesManager.manualbutton_region, vbom), 1.2f, 1);
        
        menuChildScene.addMenuItem(playMenuItem);
        menuChildScene.addMenuItem(manualMenuItem);
        
        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);
        
        playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY() + 75);
        
        manualMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY() + 50);
        
        menuChildScene.setOnMenuItemClickListener(this);
        
        setChildScene(menuChildScene);
    }
    
}
