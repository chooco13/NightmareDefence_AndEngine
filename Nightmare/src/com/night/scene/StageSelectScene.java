package com.night.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import android.os.Handler;
import android.os.Looper;

import com.night.base.BaseScene;
import com.night.manager.PreferencesManager;
import com.night.manager.SceneManager;
import com.night.manager.SceneManager.SceneType;

public class StageSelectScene extends BaseScene implements IOnMenuItemClickListener
{
    
    // ---------------------------------------------
    // VARIABLES
    // ---------------------------------------------
    
    private final int MENU_STAGE1 = 1;
    
    private final int MENU_STAGE2 = 2;
    
    private final int MENU_STAGE3 = 3;
    
    private final int MENU_PREVPAGE = 8;
    
    private final int MENU_NEXTPAGE = 9;
    
    private final int MENU_STATUS = 0;
    
    private final int MENU_BACK = 10;
    
    // ---------------------------------------------
    
    private MenuScene menuChildScene;
    
    private int stage_temp = 1;
    
    private Sprite background;
    
    private IMenuItem Stage1Item;
    
    private IMenuItem Stage2Item;
    
    private IMenuItem Stage3Item;
    
    private IMenuItem PrevPageItem;
    
    private IMenuItem NextPageItem;
    
    private IMenuItem StatusItem;
    
    private IMenuItem BackItem;
    
    // ---------------------------------------------
    // METHODS FROM SUPERCLASS
    // ---------------------------------------------
    
    @Override
    public void createScene()
    {
        Leveling();
        System.gc();
        stage_temp = 1;
        createBackground();
        createMenuChildScene();
    }
    
    @Override
    public void onBackKeyPressed()
    {
        SceneManager.getInstance().loadMenuScene(engine);
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
    
    private void Leveling()
    {
        
        Handler h = new Handler(Looper.getMainLooper());
        h.postDelayed(new Runnable() {
            
            @Override
            public void run()
            {
                PreferencesManager preferencesManager = new PreferencesManager();
                
                int level = preferencesManager.getLevelPreferences(activity);
                
                if (level == 18)
                {
                    return;
                }
                else
                {
                    int point = preferencesManager.getPointPreferences(activity);
                    
                    int nexp = preferencesManager.getNexpPreferences(activity);
                    int rexp = 0;
                    
                    while (nexp >= 5000 * level)
                    {
                        nexp -= 5000 * level;
                        level++;
                        point += 3;
                        
                        if (level == 18)
                        {
                            break;
                        }
                    }
                    
                    rexp = 5000 * level - nexp;
                    
                    if (level == 18)
                    {
                        rexp = 0;
                        nexp = 0;
                    }
                    
                    preferencesManager.savePreferences(level, point, rexp, nexp, activity);
                }
            }
        }, 0);
    }
    
    // ---------------------------------------------
    // LISTENER
    // ---------------------------------------------
    
    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
            float pMenuItemLocalY)
    {
        switch (pMenuItem.getID())
        {
            case MENU_STAGE1:
                resetAllField();
                SceneManager.getInstance().loadGameScene(engine);
                GameScene.selectstagenum = 1;
                return true;
                
            case MENU_STAGE2:
                resetAllField();
                SceneManager.getInstance().loadGameScene(engine);
                GameScene.selectstagenum = 2;
                return true;
                
            case MENU_STAGE3:
                resetAllField();
                SceneManager.getInstance().loadGameScene(engine);
                GameScene.selectstagenum = 3;
                return true;
                
            case MENU_PREVPAGE:
                
                if (stage_temp == 1)
                    return false;
                else if (stage_temp == 2)
                {
                    stage_temp -= 1;
                    createMenuChildScene();
                    return true;
                }
                else if (stage_temp == 3)
                {
                    stage_temp -= 1;
                    createMenuChildScene();
                    return true;
                }
                
                return false;
                
            case MENU_NEXTPAGE:
                
                if (stage_temp == 1)
                {
                    stage_temp += 1;
                    createMenuChildScene();
                    return true;
                }
                else if (stage_temp == 2)
                {
                    stage_temp += 1;
                    createMenuChildScene();
                    return true;
                }
                else if (stage_temp == 3)
                {
                    return false;
                }
                
                return false;
                
            case MENU_STATUS:
                resetAllField();
                SceneManager.getInstance().loadStatusScene();
                // detachChild(background);
                return true;
                
            case MENU_BACK:
                resetAllField();
                SceneManager.getInstance().loadMenuScene(engine);
                return true;
                
            default:
                return false;
        }
    }
    
    // ---------------------------------------------
    // CREATE METHODS
    // ---------------------------------------------
    
    public void resetAllField()
    {
        menuChildScene = null;
        background = null;
        Stage1Item = null;
        Stage2Item = null;
        Stage3Item = null;
        PrevPageItem = null;
        NextPageItem = null;
        StatusItem = null;
        BackItem = null;
        
        System.gc();
    }
    
    private void createBackground()
    {
        background = new Sprite(0, 0, resourcesManager.stage_select_background_region, vbom) {
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
        
        Stage1Item = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_STAGE1, resourcesManager.stage1_region, vbom),
                1, 1);
        Stage2Item = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_STAGE2, resourcesManager.stage2_region, vbom),
                1, 1);
        Stage3Item = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_STAGE3, resourcesManager.stage3_region, vbom),
                1, 1);
        
        PrevPageItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PREVPAGE, resourcesManager.stage_left, vbom),
                1.2f, 1);
        NextPageItem = new ScaleMenuItemDecorator(
                new SpriteMenuItem(MENU_NEXTPAGE, resourcesManager.stage_right, vbom), 1.2f, 1);
        StatusItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_STATUS, resourcesManager.status_region, vbom),
                1.2f, 1);
        BackItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_BACK, resourcesManager.back_region, vbom), 1.2f,
                1);
        
        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);
        
        menuChildScene.addMenuItem(PrevPageItem);
        menuChildScene.addMenuItem(NextPageItem);
        
        if (stage_temp == 1)
        {
            menuChildScene.addMenuItem(Stage1Item);
        }
        else if (stage_temp == 2)
        {
            menuChildScene.addMenuItem(Stage2Item);
        }
        else if (stage_temp == 3)
        {
            menuChildScene.addMenuItem(Stage3Item);
        }
        
        menuChildScene.addMenuItem(StatusItem);
        menuChildScene.addMenuItem(BackItem);
        
        PrevPageItem.setPosition(62, 124);
        NextPageItem.setPosition(433, PrevPageItem.getY());
        
        if (stage_temp == 1)
        {
            Stage1Item.setPosition(151, 69);
        }
        else if (stage_temp == 2)
        {
            Stage2Item.setPosition(151, 69);
        }
        else if (stage_temp == 3)
        {
            Stage3Item.setPosition(151, 69);
        }
        
        StatusItem.setPosition(14, 275);
        BackItem.setPosition(399, StatusItem.getY());
        
        menuChildScene.setOnMenuItemClickListener(this);
        
        setChildScene(menuChildScene);
        
    }
}