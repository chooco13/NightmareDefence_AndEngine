package com.night.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.util.GLState;

import android.os.Handler;
import android.os.Looper;

import com.night.base.BaseScene;
import com.night.manager.PreferencesManager;
import com.night.manager.SceneManager;
import com.night.manager.SceneManager.SceneType;

public class StatusScene extends BaseScene implements IOnMenuItemClickListener
{
    
    // ---------------------------------------------
    // VARIABLES
    // ---------------------------------------------
    
    private MenuScene menuChildScene;
    
    private final int MENU_EXIT = 0;
    
    private final int MENU_STATUS1 = 110;
    
    private final int MENU_STATUS2 = 120;
    
    private final int MENU_STATUS3 = 130;
    
    private final int MENU_STATUS4 = 140;
    
    private int status;
    
    private int level;
    
    private int nexp;
    
    private int rexp;
    
    private int point;
    
    private int[] x;
    
    private Sprite background;
    
    private Sprite status1;
    
    private Sprite status2;
    
    private Sprite status3;
    
    private Sprite status4;
    
    private IMenuItem status1_btn;
    
    private IMenuItem status2_btn;
    
    private IMenuItem status3_btn;
    
    private IMenuItem status4_btn;
    
    private PreferencesManager preferencesManager;
    
    private IMenuItem[] p_item;
    
    private IMenuItem[] m_item;
    
    // ---------------------------------------------
    // METHODS FROM SUPERCLASS
    // ---------------------------------------------
    
    @Override
    public void createScene()
    {
        createBackground();
        x = new int[17];
        status = 1;
        loadStatus();
    }
    
    @Override
    public void onBackKeyPressed()
    {
        saveStatus();
        SceneManager.getInstance().loadStageSelectScene();
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
    
    public void loadStatus()
    {
        Handler h = new Handler(Looper.getMainLooper());
        h.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                preferencesManager = new PreferencesManager();
                for (int i = 0; i < 17; i++)
                {
                    x[i] = preferencesManager.getStatPreferences(activity, i);
                }
                level = preferencesManager.getLevelPreferences(activity);
                point = preferencesManager.getPointPreferences(activity);
                rexp = preferencesManager.getRexpPreferences(activity);
                nexp = preferencesManager.getNexpPreferences(activity);
                createMenuChildScene();
            }
        }, 0);
        
    }
    
    public void saveStatus()
    {
        Handler h = new Handler(Looper.getMainLooper());
        h.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                preferencesManager = new PreferencesManager();
                preferencesManager.savePreferences(level, point, rexp, nexp, activity);
                for (int i = 0; i < 17; i++)
                {
                    preferencesManager.savestatPreferences(x[i], i, activity);
                }
            }
        }, 0);
    }
    
    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
            float pMenuItemLocalY)
    {
        switch (pMenuItem.getID())
        {
        // TODO
            case MENU_EXIT:
                SceneManager.getInstance().loadStageSelectScene();
                saveStatus();
                return true;
                
            case MENU_STATUS1:
                status = 1;
                createMenuChildScene();
                return true;
            case MENU_STATUS2:
                status = 2;
                createMenuChildScene();
                return true;
            case MENU_STATUS3:
                status = 3;
                createMenuChildScene();
                return true;
            case MENU_STATUS4:
                status = 4;
                createMenuChildScene();
                return true;
            default:
                for (int i = 4; i < 21; i++)
                {
                    if (pMenuItem.getID() == i)
                    {
                        if (x[i - 4] < 3 && point > 0)
                        {
                            x[i - 4] += 1;
                            point -= 1;
                            createMenuChildScene();
                            
                        }
                    }
                }
                for (int i = 21; i < 38; i++)
                {
                    if (pMenuItem.getID() == i)
                        if (x[i - 21] > 0)
                        {
                            x[i - 21] -= 1;
                            point += 1;
                            createMenuChildScene();
                            
                            return true;
                        }
                    
                }
                return false;
        }
        
    }
    
    // ---------------------------------------------
    // CLASS LOGIC
    // ---------------------------------------------
    
    public void resetAllField()
    {
        background = null;
        status1 = null;
        status2 = null;
        status3 = null;
        status4 = null;
        status1_btn = null;
        status2_btn = null;
        status3_btn = null;
        status4_btn = null;
        p_item = null;
        m_item = null;
        
        System.gc();
    }
    
    private void createBackground()
    {
        background = new Sprite(0, 0, resourcesManager.status_background_region, vbom) {
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
        
        final IMenuItem exititem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_EXIT,
                resourcesManager.exit_region, vbom), 1.2f, 1);
        
        p_item = new IMenuItem[17];
        
        status1 = new Sprite(0, 0, resourcesManager.status1_region, vbom);
        status2 = new Sprite(0, 0, resourcesManager.status2_region, vbom);
        status3 = new Sprite(0, 0, resourcesManager.status3_region, vbom);
        status4 = new Sprite(0, 0, resourcesManager.status4_region, vbom);
        
        for (int i = 0; i < 17; i++)
        {
            p_item[i] = new ScaleMenuItemDecorator(new SpriteMenuItem(i + 4, resourcesManager.plus_region, vbom), 1.2f,
                    1);
        }
        
        m_item = new IMenuItem[17];
        
        for (int i = 0; i < 17; i++)
        {
            m_item[i] = new ScaleMenuItemDecorator(new SpriteMenuItem(i + 21, resourcesManager.minus_region, vbom),
                    1.2f, 1);
        }
        
        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);
        
        menuChildScene.attachChild(new Text(155, 13, resourcesManager.mfont, "" + level, vbom));
        menuChildScene.attachChild(new Text(130, 292, resourcesManager.mfont, "" + nexp, vbom));
        menuChildScene.attachChild(new Text(390, 292, resourcesManager.mfont, "" + rexp, vbom));
        menuChildScene.attachChild(new Text(350, 11, resourcesManager.mfont, "" + point, vbom));
        
        // menuChildScene.attachChild(new Text(20, 400, resourcesManager.mfont,
        // "Æ¯¼º", vbom));
        
        exititem.setPosition(409, 7);
        
        for (int i = 0; i < 5; i++)
        {
            p_item[i].setPosition(420, 62 + i * 42);
            m_item[i].setPosition(450, 62 + i * 42);
            
        }
        
        for (int i = 5; i < 10; i++)
        {
            p_item[i].setPosition(420, 62 + (i - 5) * 42);
            m_item[i].setPosition(450, 62 + (i - 5) * 42);
            
        }
        
        for (int i = 10; i < 15; i++)
        {
            p_item[i].setPosition(420, 62 + (i - 10) * 42);
            m_item[i].setPosition(450, 62 + (i - 10) * 42);
            
        }
        
        for (int i = 15; i < 17; i++)
        {
            p_item[i].setPosition(420, 62 + (i - 15) * 42);
            m_item[i].setPosition(450, 62 + (i - 15) * 42);
            
        }
        
        menuChildScene.addMenuItem(exititem);
        
        if (status == 1)
        {
            menuChildScene.attachChild(status1);
            for (int i = 0; i < 5; i++)
            {
                menuChildScene.addMenuItem(p_item[i]);
                menuChildScene.addMenuItem(m_item[i]);
                menuChildScene.attachChild(new Text(375, 64 + i * 40, resourcesManager.mfont, "" + x[i], vbom));
            }
            
        }
        else if (status == 2)
        {
            menuChildScene.attachChild(status2);
            for (int i = 5; i < 10; i++)
            {
                menuChildScene.addMenuItem(p_item[i]);
                menuChildScene.addMenuItem(m_item[i]);
                menuChildScene.attachChild(new Text(375, 64 + (i - 5) * 40, resourcesManager.mfont, "" + x[i], vbom));
            }
        }
        else if (status == 3)
        {
            menuChildScene.attachChild(status3);
            for (int i = 10; i < 15; i++)
            {
                menuChildScene.addMenuItem(p_item[i]);
                menuChildScene.addMenuItem(m_item[i]);
                menuChildScene.attachChild(new Text(375, 64 + (i - 10) * 40, resourcesManager.mfont, "" + x[i], vbom));
            }
        }
        else if (status == 4)
        {
            menuChildScene.attachChild(status4);
            for (int i = 15; i < 17; i++)
            {
                menuChildScene.addMenuItem(p_item[i]);
                menuChildScene.addMenuItem(m_item[i]);
                menuChildScene.attachChild(new Text(375, 64 + (i - 15) * 40, resourcesManager.mfont, "" + x[i], vbom));
            }
        }
        
        status1_btn = new ScaleMenuItemDecorator(
                new SpriteMenuItem(MENU_STATUS1, resourcesManager.rectangle_btn, vbom), 1.2f, 1);
        status1_btn.setPosition(80, 75);
        menuChildScene.addMenuItem(status1_btn);
        
        status2_btn = new ScaleMenuItemDecorator(
                new SpriteMenuItem(MENU_STATUS2, resourcesManager.rectangle_btn, vbom), 1.2f, 1);
        status2_btn.setPosition(80, 125);
        menuChildScene.addMenuItem(status2_btn);
        
        status3_btn = new ScaleMenuItemDecorator(
                new SpriteMenuItem(MENU_STATUS3, resourcesManager.rectangle_btn, vbom), 1.2f, 1);
        status3_btn.setPosition(80, 170);
        menuChildScene.addMenuItem(status3_btn);
        
        status4_btn = new ScaleMenuItemDecorator(
                new SpriteMenuItem(MENU_STATUS4, resourcesManager.rectangle_btn, vbom), 1.2f, 1);
        status4_btn.setPosition(80, 216);
        menuChildScene.addMenuItem(status4_btn);
        
        menuChildScene.setOnMenuItemClickListener(this);
        
        setChildScene(menuChildScene);
    }
    
}