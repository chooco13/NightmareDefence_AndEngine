package com.night.scene;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.night.base.BaseMonster;
import com.night.base.BaseScene;
import com.night.base.BaseTower;
import com.night.base.BaseWave;
import com.night.manager.AttackManager;
import com.night.manager.DBManager;
import com.night.manager.PreferencesManager;
import com.night.manager.ResourcesManager;
import com.night.manager.SceneManager;
import com.night.manager.SceneManager.SceneType;
import com.night.manager.TileManager;

/**
 * @클래스이름 : GameScene.java
 * @클래스설명 : 게임 화면 클래스
 */

public class GameScene extends BaseScene implements IOnMenuItemClickListener, IScrollDetectorListener,
        IOnSceneTouchListener
{
    // ===========================================================
    // Constants
    // ===========================================================
    
    private final int MENU_MIX = 0;
    
    private final int MENU_Rbutton = 1;
    
    private final int MENU_Gbutton = 2;
    
    private final int MENU_Bbutton = 3;
    
    private final int MENU_START = 4;
    
    private final int MENU_BACK = 5;
    
    private final int MENU_RESUME = 6;
    
    private final int MENU_SET = 7;
    
    private final int MENU_TURN = 8;
    
    private final int MENU_CANCLE = 9;
    
    private final int MENU_HELP = 55;
    
    private final int MIX_COMP = 10;
    
    private final int MIX_BUTTON = 11;
    
    private final int DETACH_TOWER1 = 12;
    
    private final int DETACH_TOWER2 = 13;
    
    private final int DETACH_TOWER3 = 14;
    
    private final int TOWER_UP = 100;
    
    private final int TOWER_SELL = 110;
    
    private final int TOWER_CANCLE = 120;
    
    // ===========================================================
    // Fields
    // ===========================================================
    
    private SurfaceScrollDetector mScrollDetector;
    
    protected VertexBufferObjectManager vbom;
    
    private ResourcesManager resourcesManager;
    
    public MenuScene menuChildScene;
    
    private AttackManager attackManager;
    
    private TileManager tileManager;
    
    private DBManager dbManager;
    
    private ArrayList<BaseWave> wave;
    
    private BaseTower tower;
    
    // ===========================================================
    
    private ITextureRegion barTexture;
    
    private IMenuItem rButton, gButton, bButton, mix, sButton;
    
    private IMenuItem resume, back;
    
    private IMenuItem towerSet, towerTurn, towerCancle;
    
    private IMenuItem mix1, mix2, mix3, mix_c, invisible_mix;
    
    private Sprite mixBar, underBar, createTowerBar, upgradeBar;
    
    private IMenuItem help;
    
    private Sprite background;
    
    private Sprite bossAlarm;
    
    private Sprite resultCharacter, errorCharacter;
    
    private Sprite howToMix;
    
    private Rectangle mainTransparent, transparent;
    
    private Sprite life_s;
    
    private Sprite mixcharacter;
    
    private Text lifeText, goldText, R_goldText, G_goldText, B_goldText;
    
    public Text sellText, upText;
    
    public static Music music;
    
    public static Timer checkTimer;
    
    // ===========================================================
    
    private ArrayList<BaseMonster> mon1;
    
    private ArrayList<BaseMonster> mon2;
    
    private ArrayList<BaseMonster> mon3;
    
    private ArrayList<BaseMonster> mon4;
    
    private ArrayList<BaseMonster> mon5;
    
    private ArrayList<BaseMonster> mon6;
    
    private ArrayList<BaseMonster> mon7;
    
    private ArrayList<BaseTower> towerArr;
    
    private ArrayList<BaseWave> ary_string;
    
    // ===========================================================
    
    public static int death, life;
    
    public static int wavenum, mobUpgrade, realcount;
    
    public static int towerID1 = 100, towerID2 = 100, towerID3 = 100;
    
    public static int _towerID1 = 100, _towerID2 = 100, _towerID3 = 100;
    
    public static int towerindex1 = -1, towerindex2 = -1, towerindex3 = -1;
    
    public static int _towerindex1 = -1, _towerindex2 = -1, _towerindex3 = -1;
    
    private int towerStatus = 0, mixStatus = 0, pauseGame = 0, mixManager = 0;
    
    private int towerN;
    
    private int exp;
    
    private int timeCalc;
    
    private int checkAtk = 0, checkTile = 0;
    
    public static int stack = 0;
    
    private int towerID;
    
    private int gold_temp = 0;
    
    private int mixError = 0;
    
    private int tileIndex;
    
    private int cost;
    
    private float x1, x2, x3;
    
    private float y1, y2, y3;
    
    public static int towerTemp;
    
    public static int gold;
    
    public int[] upgrade;
    
    public static int selectstagenum;
    
    private int dbID;
    
    private Text costText;
    
    private Rectangle mixMoney;
    
    // ===========================================================
    // CREATE SCENE
    // ===========================================================
    
    @Override
    public void createScene()
    {
        playMusic();
        
        towerID1 = 100;
        towerID2 = 100;
        towerID3 = 100;
        
        exp = 0;
        mobUpgrade = 0;
        gold = 250;
        wavenum = 0;
        death = 0;
        life = 30;
        dbID = 0;
        towerN = 0;
        cost = 0;
        
        mon1 = new ArrayList<BaseMonster>();
        mon2 = new ArrayList<BaseMonster>();
        mon3 = new ArrayList<BaseMonster>();
        mon4 = new ArrayList<BaseMonster>();
        mon5 = new ArrayList<BaseMonster>();
        mon6 = new ArrayList<BaseMonster>();
        mon7 = new ArrayList<BaseMonster>();
        towerArr = new ArrayList<BaseTower>();
        mixManager = 0;
        checkAtk = 0;
        towerStatus = 0;
        
        preferenceLoad();
        
        this.resourcesManager = ResourcesManager.getInstance();
        mScrollDetector = new SurfaceScrollDetector(this);
        
        errorCharacter = new Sprite(0, 0, resourcesManager.Error_region, vbom);
        errorCharacter.setVisible(false);
        
        tileManager = new TileManager(selectstagenum, this);
        attackManager = new AttackManager(this);
        
        tower = new BaseTower(0, this, -1, 0, 0, 0);
        
        setOnSceneTouchListener(this);
        setTouchAreaBindingOnActionDownEnabled(true);
        setTouchAreaBindingOnActionMoveEnabled(true);
        setOnSceneTouchListenerBindingOnActionDownEnabled(true);
        
        resourcesManager.loadGameResources();
        
        createBackground();
        createMenuChildScene();
        
        tileManager.attachTile();
        
        loadDBData();
    }
    
    // ===========================================================
    // Methods
    // ===========================================================
    
    private void preferenceLoad()
    {
        upgrade = new int[17];
        
        Handler h = new Handler(Looper.getMainLooper());
        
        h.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                PreferencesManager preferencesManager = new PreferencesManager();
                
                for (int i = 0; i < 17; i++)
                {
                    upgrade[i] = preferencesManager.getStatPreferences(activity, i);
                }
            }
        }, 0);
    }
    
    private void playMusic()
    {
        
        MainMenuScene.music.pause();
        
        MusicFactory.setAssetBasePath("mfx/");
        
        try
        {
            music = MusicFactory.createMusicFromAsset(engine.getMusicManager(), activity, "game.mid");
            
            music.play();
            
            music.setVolume(0.5f);
            
            music.setLooping(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public void loadDBData()
    {
        ary_string = new ArrayList<BaseWave>();
        
        dbManager = new DBManager(activity, selectstagenum);
        
        dbManager.open();
        
        if (ary_string == null)
        {
            ary_string = new ArrayList<BaseWave>();
        }
        
        ary_string = dbManager.selectAllWaveList();
        wave = new ArrayList<BaseWave>();
        dbManager.close();
    }
    
    public void setWaveData()
    {
        int monSize[] = new int[7];
        
        for (int n = 0; dbID + n < ary_string.size(); n++)
        {
            if (ary_string.get(dbID + n).getWave() == wavenum + 1)
            {
                wave.add(new BaseWave(ary_string.get(dbID + n).getId(), ary_string.get(dbID + n).getWave(), ary_string
                        .get(dbID + n).getMob1(), ary_string.get(dbID + n).getMob2(), ary_string.get(dbID + n)
                        .getMob3(), ary_string.get(dbID + n).getMob4(), ary_string.get(dbID + n).getMob5(), ary_string
                        .get(dbID + n).getMob6(), ary_string.get(dbID + n).getMob7(), selectstagenum, ary_string
                        .get(dbID + n).getWay()));
                
                monSize[0] += ary_string.get(dbID + n).getMob1();
                monSize[1] += ary_string.get(dbID + n).getMob2();
                monSize[2] += ary_string.get(dbID + n).getMob3();
                monSize[3] += ary_string.get(dbID + n).getMob4();
                monSize[4] += ary_string.get(dbID + n).getMob5();
                monSize[5] += ary_string.get(dbID + n).getMob6();
                monSize[6] += ary_string.get(dbID + n).getMob7();
                
                if (dbID + n == ary_string.size() - 1)
                {
                    dbID += n;
                    
                    timeCalc = 0;
                    
                    for (int i = 0; i < 7; i++)
                    {
                        if (monSize[i] > timeCalc)
                        {
                            timeCalc = monSize[i];
                        }
                    }
                    
                    timeCalc *= 300;
                    
                    break;
                }
            }
            else
            {
                dbID += n;
                
                timeCalc = 0;
                
                for (int i = 0; i < 7; i++)
                {
                    if (monSize[i] > timeCalc)
                    {
                        timeCalc = monSize[i];
                    }
                }
                
                timeCalc *= 300;
                
                break;
            }
            
        }
        
        wavenum += 1;
        
        if (wavenum % 5 == 0)
            mobUpgrade++;
        
    }
    
    public void showClear()
    {
        Rectangle endPage = new Rectangle(0, 0, background.getWidth(), background.getHeight(), vbom);
        endPage.setColor(1, 1, 1, 0.8f);
        
        Sprite stageclear = new Sprite(0, 0, resourcesManager.stageClear_region, vbom);
        
        createBackMenuChildScene();
        
        back.setPosition(330, 225);
        resume.setVisible(false);
        resume.setPosition(-100, -100);
        
        mainTransparent.attachChild(endPage);
        attachChild(stageclear);
    }
    
    public void startWave()
    {
        realcount = 0;
        death = 0;
        exp = 0;
        
        mon1.clear();
        mon2.clear();
        mon3.clear();
        mon4.clear();
        mon5.clear();
        mon6.clear();
        mon7.clear();
        
        if (life > 0)
        {
            setWaveData();
            
            TimerTask checkTask;
            checkTask = new TimerTask() {
                int delay = 1;
                
                @Override
                public void run()
                {
                    switch (delay)
                    {
                        case 1:
                            for (int x = 0; x < wave.size(); x++)
                            {
                                if (wave.get(x).getMob1() != 0)
                                {
                                    createWave(wave.get(x).getMob1(), 1, mon1, wave.get(x).getWave(), timeCalc
                                            / wave.get(x).getMob1());
                                    break;
                                }
                            }
                            delay++;
                            
                        case 2:
                            for (int x = 0; x < wave.size(); x++)
                            {
                                if (wave.get(x).getMob2() != 0)
                                {
                                    createWave(wave.get(x).getMob2(), 2, mon2, wave.get(x).getWave(), timeCalc
                                            / wave.get(x).getMob2());
                                    break;
                                }
                            }
                            delay++;
                            
                        case 3:
                            for (int x = 0; x < wave.size(); x++)
                            {
                                if (wave.get(x).getMob3() != 0)
                                {
                                    createWave(wave.get(x).getMob3(), 3, mon3, wave.get(x).getWave(), timeCalc
                                            / wave.get(x).getMob3());
                                    break;
                                }
                            }
                            delay++;
                            
                        case 4:
                            for (int x = 0; x < wave.size(); x++)
                            {
                                if (wave.get(x).getMob4() != 0)
                                {
                                    createWave(wave.get(x).getMob4(), 4, mon4, wave.get(x).getWave(), timeCalc
                                            / wave.get(x).getMob4());
                                    break;
                                }
                            }
                            delay++;
                            
                        case 5:
                            for (int x = 0; x < wave.size(); x++)
                            {
                                if (wave.get(x).getMob5() != 0)
                                {
                                    createWave(wave.get(x).getMob5(), 5, mon5, wave.get(x).getWave(), timeCalc
                                            / wave.get(x).getMob5());
                                    break;
                                }
                            }
                            delay++;
                            
                        case 6:
                            for (int x = 0; x < wave.size(); x++)
                            {
                                if (wave.get(x).getMob6() != 0)
                                {
                                    createWave(wave.get(x).getMob6(), 6, mon6, wave.get(x).getWave(), timeCalc
                                            / wave.get(x).getMob6());
                                    break;
                                }
                            }
                            delay++;
                            
                        case 7:
                            for (int x = 0; x < wave.size(); x++)
                            {
                                if (wave.get(x).getMob7() != 0)
                                {
                                    createWave(wave.get(x).getMob7(), 7, mon7, wave.get(x).getWave(), timeCalc
                                            / wave.get(x).getMob7());
                                    break;
                                }
                            }
                            this.cancel();
                            
                        default:
                            this.cancel();
                    }
                }
            };
            
            Timer mTimer = new Timer();
            mTimer.schedule(checkTask, 0, 100);
            
        }
        
    }
    
    public void createWave(final int limit, final int mobID, final ArrayList<BaseMonster> mob, final int wavenum,
            int regenTime)
    {
        TimerTask mTask;
        
        mTask = new TimerTask() {
            int count = 0;
            
            @Override
            public void run()
            {
                if (count < limit)
                {
                    if (life < 0)
                    {
                        this.cancel();
                    }
                    else
                    {
                        for (int x = 0; x < wave.size(); x++)
                        {
                            mob.add(count, wave.get(x).createMob(mobID));
                        }
                        
                        ChildAttach(mob.get(count));
                        
                        count++;
                    }
                }
                else
                {
                    this.cancel();
                }
            }
        };
        
        Timer mTimer = new Timer();
        mTimer.schedule(mTask, 1000, regenTime);
        
    }
    
    public void checkWave()
    {
        
        TimerTask checkTask;
        
        checkTask = new TimerTask() {
            
            @Override
            public void run()
            {
                if (realcount > 0)
                {
                    if (life <= 0)
                    {
                        mScrollDetector.setEnabled(false);
                        
                        Handler h = new Handler(Looper.getMainLooper());
                        h.postDelayed(new Runnable() {
                            
                            @Override
                            public void run()
                            {
                                PreferencesManager preferencesManager = new PreferencesManager();
                                
                                int temp = preferencesManager.getNexpPreferences(activity);
                                
                                preferencesManager.saveNexp(temp + exp, activity);
                                
                            }
                        }, 0);
                        
                        Rectangle endPage = new Rectangle(0, 0, background.getWidth(), background.getHeight(), vbom);
                        endPage.setColor(1, 1, 1, 0.8f);
                        
                        Sprite gameover = new Sprite(0, 0, resourcesManager.gameover_region, vbom);
                        
                        createBackMenuChildScene();
                        
                        back.setPosition(330, 225);
                        resume.setVisible(false);
                        resume.setPosition(-100, -100);
                        
                        mainTransparent.attachChild(endPage);
                        attachChild(gameover);
                        
                        attackManager.stoptimer();
                        this.cancel();
                    }
                    
                    if (death >= realcount)
                    {
                        if (life > 0)
                        {
                            if (wavenum == 30)
                            {
                                showClear();
                                
                                return;
                            }
                            
                            Handler h = new Handler(Looper.getMainLooper());
                            
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run()
                                {
                                    PreferencesManager preferencesManager = new PreferencesManager();
                                    
                                    int temp = preferencesManager.getNexpPreferences(activity);
                                    
                                    preferencesManager.saveNexp(temp + exp, activity);
                                }
                            }, 0);
                            
                            checkAtk = 0;
                            
                            underBar.setVisible(true);
                            
                            engine.runOnUpdateThread(new Runnable() {
                                public void run()
                                {
                                    background.detachChildren();
                                }
                            });
                            
                            createMenuChildScene();
                            
                            if (towerN > 0)
                            {
                                attackManager.stoptimer();
                            }
                            wave.clear();
                            realcount = 0;
                            
                            this.cancel();
                            
                        }
                    }
                    
                }
            }
        };
        
        checkTimer = new Timer();
        checkTimer.schedule(checkTask, 0, 100);
    }
    
    // ===========================================================
    // Scene
    // ===========================================================
    
    public void setBossAlarm()
    {
        try
        {
            detachChild(bossAlarm);
        }
        catch (Exception e)
        {
            Log.e("", "BossAlarm");
        }
        
        if (selectstagenum == 1)
        {
            switch (wavenum + 1)
            {
                case 10:
                case 20:
                case 30:
                    bossAlarm = new Sprite(470, -3, resourcesManager.gameMobTextureRegion7, vbom);
                    bossAlarm.setScale(0.5f);
                    attachChild(bossAlarm);
                    break;
                
                default:
                    break;
            }
        }
        else if (selectstagenum == 2)
        {
            switch (wavenum + 1)
            {
                case 7:
                case 17:
                case 27:
                    bossAlarm = new Sprite(470, -3, resourcesManager.gameMobTextureRegion7, vbom);
                    bossAlarm.setScale(0.5f);
                    attachChild(bossAlarm);
                    break;
                
                default:
                    break;
            }
        }
        else
        {
            switch (wavenum + 1)
            {
                case 10:
                case 20:
                case 30:
                    bossAlarm = new Sprite(470, -3, resourcesManager.gameMobTextureRegion7, vbom);
                    bossAlarm.setScale(0.5f);
                    attachChild(bossAlarm);
                    break;
                
                default:
                    break;
            }
        }
    }
    
    public void createMenuChildScene()
    {
        menuChildScene = new MenuScene(camera);
        menuChildScene.setPosition(0, 0);
        
        setBossAlarm();
        
        Text waveText = new Text(525, 5, resourcesManager.mfont, "" + (wavenum + 1), vbom);
        
        goldText = new Text(370, 295, resourcesManager.mfont, "" + gold, 5, vbom);
        
        R_goldText = new Text(42, 295, resourcesManager.mfont, ""
                + Integer.valueOf((int) (50 * (1 - 0.1 * upgrade[1]))), vbom);
        
        G_goldText = new Text(112, 295, resourcesManager.mfont, ""
                + Integer.valueOf((int) (30 * (1 - 0.1 * upgrade[2]))), vbom);
        
        B_goldText = new Text(180, 295, resourcesManager.mfont, ""
                + Integer.valueOf((int) (30 * (1 - 0.1 * upgrade[3]))), vbom);
        
        lifeText = new Text(515, 295, resourcesManager.mfont, "" + life, vbom) {
            @Override
            protected void onManagedUpdate(float pSecondsElapsed)
            {
                super.onManagedUpdate(pSecondsElapsed);
                if (life >= 0)
                    lifeText.setText("" + life);
                else
                    lifeText.setText("0");
            }
        };
        
        upgradeBar = new Sprite(170, -60, resourcesManager.upgradebar_region, vbom);
        underBar = new Sprite(0, 290, resourcesManager.underbar_region, super.vbom);
        
        life_s = new Sprite(467, 299, resourcesManager.life_region, super.vbom);
        setMixBar(new Sprite(490, 0, resourcesManager.mixbar_region, super.vbom));
        
        mix = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_MIX, resourcesManager.mix_region, vbom), 1, 1);
        mix1 = new ScaleMenuItemDecorator(new SpriteMenuItem(DETACH_TOWER1, resourcesManager.Invisible_tower_region,
                vbom), 1, 1);
        mix2 = new ScaleMenuItemDecorator(new SpriteMenuItem(DETACH_TOWER2, resourcesManager.Invisible_tower_region,
                vbom), 1, 1);
        mix3 = new ScaleMenuItemDecorator(new SpriteMenuItem(DETACH_TOWER3, resourcesManager.Invisible_tower_region,
                vbom), 1, 1);
        mix_c = new ScaleMenuItemDecorator(new SpriteMenuItem(MIX_COMP, resourcesManager.Invisible_tower_region, vbom),
                1, 1);
        invisible_mix = new ScaleMenuItemDecorator(new SpriteMenuItem(MIX_BUTTON,
                resourcesManager.invisible_mixButton_region, vbom), 1, 1);
        
        rButton = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_Rbutton, resourcesManager.Rbutton_region, vbom),
                1, 1);
        gButton = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_Gbutton, resourcesManager.Gbutton_region, vbom),
                1, 1);
        bButton = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_Bbutton, resourcesManager.Bbutton_region, vbom),
                1, 1);
        sButton = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_START, resourcesManager.START_region, vbom), 1, 1);
        
        help = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_HELP, resourcesManager.help_region, vbom), 1, 1);
        
        howToMix = new Sprite(90, 30, resourcesManager.mixguide_region, vbom);
        howToMix.setVisible(false);
        
        mixMoney = new Rectangle(0, 90, 60, 25, vbom);
        mixMoney.setColor(0, 0, 0, 0);
        
        mix.setPosition(206, 292);
        rButton.setPosition(0, 292);
        gButton.setPosition(69, 292);
        bButton.setPosition(137, 292);
        sButton.setPosition(1, 1);
        help.setPosition(292, 292);
        
        invisible_mix.setPosition(492, 2);
        mix1.setPosition(506, 131);
        mix2.setPosition(506, 189);
        mix3.setPosition(506, 247);
        mix_c.setPosition(506, 44);
        
        upgradeBar.setVisible(false);
        
        mixBar.attachChild(mixMoney);
        
        menuChildScene.attachChild(waveText);
        menuChildScene.attachChild(underBar);
        menuChildScene.attachChild(lifeText);
        
        menuChildScene.attachChild(life_s);
        menuChildScene.attachChild(upgradeBar);
        menuChildScene.attachChild(howToMix);
        
        menuChildScene.addMenuItem(mix);
        menuChildScene.addMenuItem(rButton);
        menuChildScene.addMenuItem(gButton);
        menuChildScene.addMenuItem(bButton);
        menuChildScene.addMenuItem(sButton);
        menuChildScene.addMenuItem(help);
        menuChildScene.setBackgroundEnabled(false);
        menuChildScene.setOnMenuItemClickListener(this);
        
        attachGoldText();
        
        if (towerArr != null)
        {
            for (int j = 0; j < towerArr.size(); j++)
            {
                if (towerArr.get(j).getId() == 7 || towerArr.get(j).getId() == 8)
                {
                    towerArr.get(j).range_S.setAlpha(0);
                }
            }
        }
        
        setChildScene(menuChildScene);
    }
    
    // ===========================================================
    
    public ITextureRegion setBackground(int stage)
    {
        ITextureRegion iTextureRegion = null;
        
        switch (stage)
        {
            case 1:
                iTextureRegion = resourcesManager.stage1_background_region;
                break;
            case 2:
                iTextureRegion = resourcesManager.stage2_background_region;
                break;
            case 3:
                iTextureRegion = resourcesManager.stage3_background_region;
                break;
            default:
                break;
        }
        
        return iTextureRegion;
        
    }
    
    private void createBackground()
    {
        background = new Sprite(0, 0, setBackground(selectstagenum), vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        transparent = new Rectangle(0, 0, background.getWidth(), background.getHeight(), vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        mainTransparent = new Rectangle(0, 0, background.getWidth(), background.getHeight(), vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        
        transparent.setColor(0, 0, 0, 0);
        
        mainTransparent.attachChild(background);
        mainTransparent.attachChild(transparent);
        
        attachChild(mainTransparent);
    }
    
    // ===========================================================
    
    private void createBackMenuChildScene()
    {
        if (tileManager.isTileVisible() == false && upgradeBar.getY() == -60 && mixManager == 0)
        {
            if (towerN == 0 || createTowerBar.getY() == -30)
            {
                resume = new ScaleMenuItemDecorator(
                        new SpriteMenuItem(MENU_RESUME, resourcesManager.back_region, vbom), 1, 1);
                back = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_BACK, resourcesManager.stage_region, vbom),
                        1, 1);
                
                menuChildScene.addMenuItem(resume);
                menuChildScene.addMenuItem(back);
                
                resume.setPosition(220, 150);
                back.setPosition(220, 110);
                
                menuChildScene.setOnMenuItemClickListener(this);
            }
        }
    }
    
    private void delBackMenuChildScene()
    {
        engine.runOnUpdateThread(new Runnable() {
            public void run()
            {
                menuChildScene.detachChild(resume);
                menuChildScene.detachChild(back);
            }
        });
    }
    
    // ===========================================================
    
    private void createTowerMenuChildScene()
    {
        upgradeBar.setY(-60);
        
        errorCharacter = new Sprite(0, 0, resourcesManager.Error_region, vbom);
        
        int id = towerArr.get(towerN - 1).getId();
        
        if (id == 2 || id == 3 || id == 6 || id == 12 || id == 13)
            createTowerBar = new Sprite(170, 0, resourcesManager.creattowerbar, super.vbom);
        else
            createTowerBar = new Sprite(170, 0, resourcesManager.creattowerbar2, super.vbom);
        
        towerSet = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_SET, resourcesManager.Invisible_region, vbom), 1,
                1);
        towerTurn = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_TURN, resourcesManager.Invisible_region, vbom),
                1, 1);
        towerCancle = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_CANCLE, resourcesManager.Invisible_region,
                vbom), 1, 1);
        
        errorCharacter.setVisible(false);
        
        towerTurn.setPosition(240, 0);
        towerSet.setPosition(170, 0);
        towerCancle.setPosition(310, 0);
        
        towerTurn.setColor(0.5f, 0.5f, 0.5f, 0.5f);
        
        menuChildScene.attachChild(createTowerBar);
        
        createTowerBar.registerEntityModifier(new PathModifier(0.7f, new Path(2).to(170, -30).to(170, 0)));
        
        menuChildScene.addMenuItem(towerSet);
        menuChildScene.addMenuItem(towerTurn);
        menuChildScene.addMenuItem(towerCancle);
        
    }
    
    private void delTowerMenuChildScene()
    {
        menuChildScene.detachChild(createTowerBar);
        menuChildScene.detachChild(towerSet);
        menuChildScene.detachChild(towerTurn);
        menuChildScene.detachChild(towerCancle);
        
        int id = towerArr.get(towerN - 1).getId();
        
        if (id == 2 || id == 3 || id == 6 || id == 12 || id == 13)
            createTowerBar = new Sprite(170, 0, resourcesManager.creattowerbar, super.vbom);
        else
            createTowerBar = new Sprite(170, 0, resourcesManager.creattowerbar2, super.vbom);
        
        menuChildScene.attachChild(createTowerBar);
        
        createTowerBar.registerEntityModifier(new PathModifier(0.7f, new Path(2).to(170, 0).to(170, -30)));
    }
    
    // todo
    private void addTower(float pX, float pY, int id)
    {
        int tiledX = ((int) (pX - 15) / 40) * 40 + 5;
        int tiledY = ((int) (pY - 15) / 40) * 40 + 5;
        
        for (int i = 0; i < towerArr.size(); i++)
        {
            if (tiledX == towerArr.get(i).character.getX() - 5 && tiledY == towerArr.get(i).character.getY() - 5)
            {
                stack++;
                return;
            }
        }
        
        checkTile = 0;
        tileIndex = 0;
        
        int i = 0;
        
        for (; i < tileManager.getTile().size(); i++)
        {
            checkTile++;
            
            if (tiledX == tileManager.getTile().get(i).getX() + 5 && tiledY == tileManager.getTile().get(i).getY() + 5)
            {
                tileIndex = i;
                
                break;
            }
        }
        
        if (checkTile == tileManager.getTile().size() && tileIndex == 0)
        {
            checkTile = -1;
            return;
        }
        
        towerArr.add(new BaseTower(id, this, towerN, id, pX, pY));
        
        ChildAttach(towerArr.get(towerN).character);
        
        towerN += 1;
        
        towerStatus = 0;
        
        tileManager.setTileVisible(false);
    }
    
    public void createPreview()
    {
        tower.setId(towerID);
        tower.setCharacter();
        resultCharacter = new Sprite(0, 0, tower.getTowerTexture(), vbom);
    }
    
    private void ChildAttach(Sprite tower)
    {
        mainTransparent.attachChild(tower);
    }
    
    // ===========================================================
    
    private void ChildAttach(BaseMonster mob)
    {
        background.attachChild(mob.character);
    }
    
    // ===========================================================
    // LISTENER
    // ===========================================================
    
    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
            float pMenuItemLocalY)
    {
        switch (pMenuItem.getID())
        {
        
            case MENU_HELP:
                if (howToMix.isVisible() == true)
                {
                    howToMix.setVisible(false);
                    mScrollDetector.setEnabled(true);
                }
                else
                {
                    howToMix.setVisible(true);
                    mScrollDetector.setEnabled(false);
                }
                return true;
                
            case MENU_Rbutton:
                if (checkMoney(50 * (1 - 0.1 * upgrade[1])) && BaseTower.upgradeManager == 0 && towerStatus == 0)
                {
                    mScrollDetector.setEnabled(true);
                    howToMix.setVisible(false);
                    tileManager.setTileVisible(true);
                    mixManager = 0;
                    towerStatus = 1;
                    towerindex2 = -1;
                    stack = 0;
                }
                return true;
                
            case MENU_Gbutton:
                if (checkMoney(30 * (1 - 0.1 * upgrade[2])) && BaseTower.upgradeManager == 0 && towerStatus == 0)
                {
                    mScrollDetector.setEnabled(true);
                    howToMix.setVisible(false);
                    tileManager.setTileVisible(true);
                    mixManager = 0;
                    towerStatus = 2;
                    towerindex2 = -1;
                    stack = 0;
                }
                return true;
                
            case MENU_Bbutton:
                if (checkMoney(30 * (1 - 0.1 * upgrade[3])) && BaseTower.upgradeManager == 0 && towerStatus == 0)
                {
                    mScrollDetector.setEnabled(true);
                    howToMix.setVisible(false);
                    tileManager.setTileVisible(true);
                    mixManager = 0;
                    towerStatus = 3;
                    towerindex2 = -1;
                    stack = 0;
                }
                return true;
                
            case MENU_START:
                if (BaseTower.upgradeManager == 1)
                {
                    towerArr.get(towerTemp).range_S.setAlpha(0);
                    BaseTower.upgradeManager = 0;
                    tower.detachtowerbar();
                }
                
                if (towerStatus == 0 && towerN > 0)
                {
                    mScrollDetector.setEnabled(true);
                    howToMix.setVisible(false);
                    
                    mixManager = 0;
                    towerStatus = 0;
                    
                    tileManager.setTileVisible(false);
                    
                    checkBuff();
                    
                    underBar.setVisible(false);
                    mixBar.setVisible(false);
                    menuChildScene.detachChild(goldText);
                    menuChildScene.detachChild(R_goldText);
                    menuChildScene.detachChild(G_goldText);
                    menuChildScene.detachChild(B_goldText);
                    
                    attackManager.checkattack();
                    
                    for (int a = 0; a < towerArr.size(); a++)
                    {
                        towerArr.get(a).resetTimerSpeed();
                    }
                    
                    menuChildScene.clearMenuItems();
                    
                    checkAtk = 1;
                    
                    startWave();
                    checkWave();
                }
                return true;
                
            case MIX_BUTTON:
                mixStatus = 0;
                
                if (towerID1 != 100 && mixStatus == 0)
                {
                    mixMoney.detachChildren();
                    
                    towerID1 = _towerID1;
                    towerID2 = _towerID2;
                    towerID3 = _towerID3;
                    
                    sort(towerID1, towerID2, towerID3, 1);
                    selectMixTower();
                    
                    if (mixError == -1)
                    {
                        mix_c.detachChildren();
                        
                        errorCharacter = new Sprite(0, 0, resourcesManager.Error_region, vbom);
                        
                        mix_c.attachChild(errorCharacter);
                        
                        return true;
                    }
                    else
                    {
                        costText = new Text(29, -3, resourcesManager.mfont, "" + cost, vbom);
                        costText.setScale(0.7f);
                        mixMoney.attachChild(costText);
                        
                        mix_c.detachChildren();
                        
                        errorCharacter.setVisible(false);
                    }
                    createPreview();
                    
                    mix_c.attachChild(resultCharacter);
                    
                    mixStatus = 1;
                }
                for (int j = 0; j < towerArr.size(); j++)
                {
                    towerArr.get(j).setIndex(j);
                }
                
                return true;
                
            case DETACH_TOWER1:
                _towerID1 = 100;
                _towerindex1 = -1;
                
                mix1.detachChildren();
                
                return true;
                
            case DETACH_TOWER2:
                _towerID2 = 100;
                _towerindex2 = -1;
                
                mix2.detachChildren();
                
                return true;
                
            case DETACH_TOWER3:
                _towerID3 = 100;
                _towerindex3 = -1;
                
                mix3.detachChildren();
                
                return true;
                
            case MENU_MIX:
                
                if (tileManager.isTileVisible())
                {
                    return false;
                }
                
                mScrollDetector.setEnabled(true);
                
                cost = 0;
                towerStatus = 0;
                towerID = 0;
                towerindex1 = -1;
                towerindex2 = -1;
                towerindex3 = -1;
                _towerindex1 = -1;
                _towerindex2 = -1;
                _towerindex3 = -1;
                towerID1 = 100;
                towerID2 = 100;
                towerID3 = 100;
                _towerID1 = 100;
                _towerID2 = 100;
                _towerID3 = 100;
                mixError = 0;
                
                if (BaseTower.upgradeManager == 1)
                {
                    if (towerN > 0)
                        towerArr.get(towerTemp).range_S.setAlpha(0);
                    BaseTower.upgradeManager = 0;
                    
                    tower.detachtowerbar();
                }
                
                menuChildScene.clearMenuItems();
                createMenuChildScene();
                
                if (mixManager == 0)
                {
                    for (int j = 0; j < towerArr.size(); j++)
                    {
                        towerArr.get(j).setIndex(j);
                    }
                    
                    mixStatus = 0;
                    
                    tileManager.setTileVisible(false);
                    
                    menuChildScene.attachChild(mixBar);
                    menuChildScene.addMenuItem(mix1);
                    menuChildScene.addMenuItem(mix2);
                    menuChildScene.addMenuItem(mix3);
                    menuChildScene.addMenuItem(mix_c);
                    
                    mixBar.registerEntityModifier(new PathModifier(0.5f, new Path(2).to(550, 0).to(490, 0)));
                    
                    if (errorCharacter.hasParent() == false)
                        mix_c.attachChild(errorCharacter);
                    
                    menuChildScene.addMenuItem(invisible_mix);
                    mixManager = 1;
                }
                else
                {
                    tileManager.setTileVisible(false);
                    
                    menuChildScene.detachChild(mixBar);
                    menuChildScene.attachChild(mixBar);
                    
                    mixBar.registerEntityModifier(new PathModifier(0.5f, new Path(2).to(490, 0).to(550, 0)));
                    mixManager = 0;
                }
                return true;
                
            case MENU_BACK:
                
                SceneManager.getInstance().loadStageSelectScene();
                
                MainMenuScene.music.play();
                GameScene.music.pause();
                this.dispose();
                background.dispose();
                transparent.dispose();
                mainTransparent.dispose();
                
                resetAllField();
                
                return true;
                
            case MENU_RESUME:
                delBackMenuChildScene();
                return true;
                
            case MENU_SET:
                
                mixManager = 0;
                
                if (createTowerBar.getY() == 0)
                {
                    tileManager.getTile().get(tileIndex).setX(-50);
                    tileManager.getTile().get(tileIndex).setY(-50);
                    
                    transparent.detachChild(tileManager.getTile().get(tileIndex));
                    tileManager.getTile().remove(tileIndex);
                    towerArr.get(towerN - 1).range_S.setAlpha(0);
                    
                    createMenuChildScene();
                    delTowerMenuChildScene();
                    
                    tileManager.setTileVisible(false);
                    gold -= gold_temp;
                    goldText.setText("" + gold);
                    
                    if (towerindex2 != -1)
                    {
                        int avg = 0;
                        int count = 0;
                        
                        avg += towerArr.get(towerindex1).getUpgrade();
                        
                        mixProcess(towerindex1);
                        
                        avg += towerArr.get(towerindex2).getUpgrade();
                        
                        mixProcess(towerindex2);
                        
                        if (towerindex3 != -1)
                        {
                            avg += towerArr.get(towerindex3).getUpgrade();
                            
                            mixProcess(towerindex3);
                        }
                        
                        towerArr.get(towerindex1).character.setVisible(false);
                        towerArr.get(towerindex1).character.dispose();
                        towerArr.get(towerindex1).range_S.setVisible(false);
                        towerArr.get(towerindex1).range_S.dispose();
                        
                        towerArr.remove(towerindex1);
                        
                        towerArr.get(towerindex2).character.setVisible(false);
                        towerArr.get(towerindex2).character.dispose();
                        towerArr.get(towerindex2).range_S.setVisible(false);
                        towerArr.get(towerindex2).range_S.dispose();
                        
                        towerArr.remove(towerindex2);
                        
                        count = 2;
                        towerN -= 2;
                        
                        if (towerindex3 != -1)
                        {
                            
                            towerArr.get(towerindex3).character.setVisible(false);
                            towerArr.get(towerindex3).character.dispose();
                            towerArr.get(towerindex3).range_S.setVisible(false);
                            towerArr.get(towerindex3).range_S.dispose();
                            towerArr.remove(towerindex3);
                            
                            count++;
                            towerN--;
                        }
                        
                        towerindex1 = -1;
                        towerindex2 = -1;
                        towerindex3 = -1;
                        
                        towerArr.get(towerN - 1).setUpgrade((int) avg / count);
                        towerArr.get(towerN - 1).mixedTower();
                        
                        for (int j = 0; j < towerArr.size(); j++)
                        {
                            towerArr.get(j).setIndex(j);
                        }
                    }
                    
                }
                
                towerStatus = 0;
                
                return true;
                
            case MENU_TURN:
                if (createTowerBar.getY() == 0)
                {
                    int temp_id = towerArr.get(towerN - 1).getId();
                    
                    if (temp_id == 2 || temp_id == 3 || temp_id == 6 || temp_id == 12 || temp_id == 13)
                    {
                        unregisterTouchArea(towerArr.get(towerN - 1).character);
                        mainTransparent.detachChild(towerArr.get(towerN - 1).character);
                        transparent.detachChild(towerArr.get(towerN - 1).range_S);
                        
                        towerArr.get(towerN - 1).character.dispose();
                        
                        if (towerArr.get(towerN - 1).getDir() < 4)
                        {
                            towerArr.get(towerN - 1).setDir(towerArr.get(towerN - 1).getDir() + 1);
                        }
                        else
                        {
                            towerArr.get(towerN - 1).setDir(1);
                        }
                        
                        towerArr.get(towerN - 1).setCharacter();
                        
                        mainTransparent.attachChild(towerArr.get(towerN - 1).character);
                        registerTouchArea(towerArr.get(towerN - 1).character);
                        
                        if (createTowerBar.getY() == 0 || upgradeBar.getY() == 0)
                            transparent.attachChild(towerArr.get(towerN - 1).range_S);
                    }
                    
                }
                else if (upgradeBar.getY() == 0)
                {
                    int temp_id = towerArr.get(towerTemp).getId();
                    
                    if (temp_id == 2 || temp_id == 3 || temp_id == 6 || temp_id == 12 || temp_id == 13)
                    {
                        unregisterTouchArea(towerArr.get(towerTemp).character);
                        mainTransparent.detachChild(towerArr.get(towerTemp).character);
                        transparent.detachChild(towerArr.get(towerTemp).range_S);
                        
                        if (towerArr.get(towerTemp).getDir() < 4)
                        {
                            towerArr.get(towerTemp).setDir(towerArr.get(towerTemp).getDir() + 1);
                        }
                        else
                        {
                            towerArr.get(towerTemp).setDir(1);
                        }
                        
                        towerArr.get(towerTemp).setCharacter();
                        
                        mainTransparent.attachChild(towerArr.get(towerTemp).character);
                        registerTouchArea(towerArr.get(towerN - 1).character);
                        
                        if (createTowerBar.getY() == 0 || upgradeBar.getY() == 0)
                            transparent.attachChild(towerArr.get(towerTemp).range_S);
                    }
                }
                return true;
                
            case MENU_CANCLE:
                if (createTowerBar.getY() == 0)
                {
                    mainTransparent.detachChild(towerArr.get(towerN - 1).character);
                    transparent.detachChild(towerArr.get(towerN - 1).range_S);
                    towerArr.get(towerN - 1).character.setPosition(-100, -100);
                    
                    createMenuChildScene();
                    delTowerMenuChildScene();
                    
                    towerArr.remove(towerN - 1);
                    towerN--;
                    
                    tileManager.setTileVisible(false);
                    
                    if (mixManager != 0)
                    {
                        towerArr.get(towerindex1).character.setPosition(x1, y1);
                        checkUpgrade(towerindex1);
                        tileManager.getTile().get(tileManager.getTile().size() - 1).detachSelf();
                        tileManager.getTile().remove(tileManager.getTile().size() - 1);
                        
                        towerArr.get(towerindex2).character.setPosition(x2, y2);
                        checkUpgrade(towerindex2);
                        tileManager.getTile().get(tileManager.getTile().size() - 1).detachSelf();
                        tileManager.getTile().remove(tileManager.getTile().size() - 1);
                        if (towerindex3 != -1)
                        {
                            towerArr.get(towerindex3).character.setPosition(x3, y3);
                            checkUpgrade(towerindex3);
                            tileManager.getTile().get(tileManager.getTile().size() - 1).detachSelf();
                            tileManager.getTile().remove(tileManager.getTile().size() - 1);
                        }
                        
                        mixManager = 0;
                    }
                    
                    towerindex1 = -1;
                    towerindex2 = -1;
                    towerindex3 = -1;
                }
                
                towerStatus = 0;
                
                return true;
                
            case MIX_COMP:
                try
                {
                    if (checkMoney(cost))
                    {
                        towerindex1 = _towerindex1;
                        towerindex2 = _towerindex2;
                        towerindex3 = _towerindex3;
                        
                        sort(towerindex1, towerindex2, towerindex3, 0);
                        
                        if (towerID1 != 100 && mixStatus == 1)
                        {
                            x1 = towerArr.get(towerindex1).character.getX();
                            x2 = towerArr.get(towerindex2).character.getX();
                            
                            y1 = towerArr.get(towerindex1).character.getY();
                            y2 = towerArr.get(towerindex2).character.getY();
                            
                            towerArr.get(towerindex1).character.setPosition(-200, -200);
                            
                            checkUpgrade(towerindex1);
                            
                            towerArr.get(towerindex2).character.setPosition(-200, -200);
                            
                            checkUpgrade(towerindex2);
                            
                            Sprite sprite1 = new Sprite(x1 - 5, y1 - 5, resourcesManager.tile_region, vbom);
                            
                            sprite1.setVisible(false);
                            
                            tileManager.getTile().add(sprite1);
                            transparent.attachChild(sprite1);
                            
                            Sprite sprite2 = new Sprite(x2 - 5, y2 - 5, resourcesManager.tile_region, vbom);
                            
                            sprite2.setVisible(false);
                            
                            tileManager.getTile().add(sprite2);
                            transparent.attachChild(sprite2);
                            
                            if (towerindex3 != -1)
                            {
                                x3 = towerArr.get(towerindex3).character.getX();
                                y3 = towerArr.get(towerindex3).character.getY();
                                towerArr.get(towerindex3).character.setPosition(-200, -200);
                                
                                checkUpgrade(towerindex3);
                                
                                Sprite sprite3 = new Sprite(x3 - 5, y3 - 5, resourcesManager.tile_region, vbom);
                                
                                sprite3.setVisible(false);
                                
                                tileManager.getTile().add(sprite3);
                                transparent.attachChild(sprite3);
                                
                            }
                            
                            tileManager.setTileVisible(true);
                            towerStatus = towerID;
                            mixStatus = 0;
                        }
                    }
                }
                catch (Exception e)
                {
                    Log.e("", "mix error");
                }
                
                return true;
                
            case TOWER_UP:
                try
                {
                    if (towerArr.get(towerTemp).getId() == 4 || towerArr.get(towerTemp).getId() == 5
                            || towerArr.get(towerTemp).getId() == 6)
                    {
                        return false;
                    }
                    else if (upgradeBar.isVisible() == true && upgradeBar.getY() == 0)
                    {
                        if (towerArr.get(towerTemp).getUpgrade() < 3)
                        {
                            if (checkMoney(towerArr.get(towerTemp)
                                    .upgradePrice(upgrade[towerArr.get(towerTemp).getId() + 3])))
                            {
                                towerArr.get(towerTemp).range_S.setAlpha(0);
                                
                                towerArr.get(towerTemp).upgradeTower();
                                
                                gold -= gold_temp;
                                
                                goldText.setText("" + gold);
                                
                                tower.detachtowerbar();
                            }
                        }
                    }
                    return true;
                }
                catch (Exception e)
                {
                    Log.e("", "towerUP");
                }
                
            case TOWER_SELL:
                
                if (upgradeBar.isVisible() == true && upgradeBar.getY() == 0)
                {
                    towerArr.get(towerTemp).range_S.setAlpha(0);
                    
                    int x = (int) towerArr.get(towerTemp).character.getX() - 5;
                    int y = (int) towerArr.get(towerTemp).character.getY() - 5;
                    
                    Sprite sprite = new Sprite(x, y, resourcesManager.tile_region, vbom);
                    sprite.setVisible(false);
                    
                    tileManager.getTile().add(sprite);
                    transparent.attachChild(sprite);
                    
                    towerArr.get(towerTemp).character.setVisible(false);
                    towerArr.get(towerTemp).character.setPosition(-100, -100);
                    towerArr.get(towerTemp).range_S.setVisible(false);
                    towerArr.get(towerTemp).range_S.setPosition(-100, -100);
                    
                    towerArr.get(towerTemp).character.setCullingEnabled(true);
                    towerArr.get(towerTemp).range_S.setCullingEnabled(true);
                    
                    mainTransparent.detachChild(towerArr.get(towerTemp).character);
                    mainTransparent.detachChild(towerArr.get(towerTemp).range_S);
                    
                    tower.detachtowerbar();
                    
                    gold_temp = towerArr.get(towerTemp).sellTower();
                    
                    detachUpgradeMark(towerTemp);
                    
                    towerArr.remove(towerTemp);
                    towerN--;
                    
                    gold += gold_temp;
                    goldText.setText("" + gold);
                    
                    for (int j = 0; j < towerArr.size(); j++)
                    {
                        towerArr.get(j).setIndex(j);
                    }
                }
                return true;
                
            case TOWER_CANCLE:
                if (upgradeBar.getY() == 0)
                {
                    towerArr.get(towerTemp).range_S.setAlpha(0);
                    tower.detachtowerbar();
                }
                
                return true;
                
            default:
                return false;
        }
    }
    
    // TODO
    private void resetAllField()
    {
        mScrollDetector = null;
        vbom = null;
        resourcesManager = null;
        menuChildScene = null;
        attackManager = null;
        tileManager = null;
        dbManager = null;
        wave = null;
        tower = null;
        barTexture = null;
        rButton = null;
        gButton = null;
        bButton = null;
        mix = null;
        sButton = null;
        resume = null;
        back = null;
        towerSet = null;
        towerTurn = null;
        towerCancle = null;
        mix1 = null;
        mix2 = null;
        mix3 = null;
        mix_c = null;
        invisible_mix = null;
        mixBar = null;
        underBar = null;
        createTowerBar = null;
        upgradeBar = null;
        help = null;
        background = null;
        resultCharacter = null;
        errorCharacter = null;
        howToMix = null;
        mainTransparent = null;
        transparent = null;
        life_s = null;
        mixcharacter = null;
        lifeText = null;
        goldText = null;
        R_goldText = null;
        G_goldText = null;
        B_goldText = null;
        sellText = null;
        upText = null;
        music = null;
        checkTimer = null;
        mon1 = null;
        mon2 = null;
        mon3 = null;
        mon4 = null;
        mon5 = null;
        mon6 = null;
        mon7 = null;
        towerArr = null;
        ary_string = null;
        
        System.gc();
    }
    
    private void checkUpgrade(int index)
    {
        if (towerArr.get(index).character.getX() < 0)
        {
            if (towerArr.get(index).getUpgrade() == 1)
                towerArr.get(index).up_S[0].setPosition(-200, -200);
            else if (towerArr.get(index).getUpgrade() == 2)
                towerArr.get(index).up_S[1].setPosition(-200, -200);
            else if (towerArr.get(index).getUpgrade() >= 3)
                towerArr.get(index).up_S[2].setPosition(-200, -200);
        }
        else
        {
            if (towerArr.get(index).getUpgrade() == 1)
                towerArr.get(index).up_S[0].setPosition(towerArr.get(index).character.getX() - 5,
                                                        towerArr.get(index).character.getY());
            else if (towerArr.get(index).getUpgrade() == 2)
                towerArr.get(index).up_S[1].setPosition(towerArr.get(index).character.getX() - 5,
                                                        towerArr.get(index).character.getY());
            else if (towerArr.get(index).getUpgrade() >= 3)
                towerArr.get(index).up_S[2].setPosition(towerArr.get(index).character.getX() - 5,
                                                        towerArr.get(index).character.getY());
            
        }
    }
    
    private void mixProcess(int index)
    {
        towerArr.get(index).character.setCullingEnabled(true);
        towerArr.get(index).range_S.setCullingEnabled(true);
        mainTransparent.detachChild(towerArr.get(index).character);
        mainTransparent.detachChild(towerArr.get(index).range_S);
        detachUpgradeMark(index);
    }
    
    private void detachUpgradeMark(int towerTemp)
    {
        if (towerArr.get(towerTemp).getUpgrade() == 1)
        {
            towerArr.get(towerTemp).up_S[0].setCullingEnabled(true);
            mainTransparent.detachChild(towerArr.get(towerTemp).up_S[0]);
        }
        else if (towerArr.get(towerTemp).getUpgrade() == 2)
        {
            towerArr.get(towerTemp).up_S[0].setCullingEnabled(true);
            mainTransparent.detachChild(towerArr.get(towerTemp).up_S[1]);
        }
        else if (towerArr.get(towerTemp).getUpgrade() == 3)
        {
            towerArr.get(towerTemp).up_S[0].setCullingEnabled(true);
            mainTransparent.detachChild(towerArr.get(towerTemp).up_S[2]);
        }
    }
    
    private void detachGoldText()
    {
        menuChildScene.detachChild(R_goldText);
        menuChildScene.detachChild(G_goldText);
        menuChildScene.detachChild(B_goldText);
        menuChildScene.detachChild(goldText);
    }
    
    private void attachGoldText()
    {
        menuChildScene.attachChild(R_goldText);
        menuChildScene.attachChild(G_goldText);
        menuChildScene.attachChild(B_goldText);
        menuChildScene.attachChild(goldText);
    }
    
    // ===========================================================
    // TOWER METHODS
    // ===========================================================
    
    private void checkBuff()
    {
        for (int j = 0; j < towerArr.size(); j++)
        {
            if (towerArr.get(j).getId() == 7 || towerArr.get(j).getId() == 8)
                buff(towerArr.get(j));
        }
    }
    
    private void buff(BaseTower tower)
    {
        float centerX = tower.character.getX() + tower.character.getWidth() / 2;
        
        float centerY = tower.character.getY() + tower.character.getHeight() / 2;
        
        for (int i = 0; i < towerArr.size(); i++)
        {
            towerArr.get(i).setSpeedBuff(0);
            towerArr.get(i).setDamageBuff(0);
            towerArr.get(i).setSupgrade(0);
            towerArr.get(i).setDupgrade(0);
        }
        
        if (tower.getId() == 7)
        {
            tower.range_S.setAlpha(0.7f);
            for (int i = 0; i < towerArr.size(); i++)
            {
                if (towerArr.get(i).character.getX() + towerArr.get(i).character.getWidth() / 2 > (centerX - 105)
                        && towerArr.get(i).character.getX() + towerArr.get(i).character.getWidth() / 2 < (centerX + 105))
                {
                    if (towerArr.get(i).character.getY() + towerArr.get(i).character.getHeight() / 2 > (centerY - 105)
                            && towerArr.get(i).character.getY() + towerArr.get(i).character.getHeight() / 2 < (centerY + 105))
                    {
                        towerArr.get(i).setDamageBuff(1);
                        towerArr.get(i).setDupgrade(tower.getUpgrade());
                        towerArr.get(i).setAtk();
                    }
                }
            }
        }
        
        else if (tower.getId() == 8)
        {
            tower.range_S.setAlpha(0.7f);
            for (int i = 0; i < towerArr.size(); i++)
            {
                if (towerArr.get(i).character.getX() + towerArr.get(i).character.getWidth() / 2 > (centerX - 105)
                        && towerArr.get(i).character.getX() + towerArr.get(i).character.getWidth() / 2 < (centerX + 105))
                {
                    if (towerArr.get(i).character.getY() + towerArr.get(i).character.getHeight() / 2 > (centerY - 105)
                            && towerArr.get(i).character.getY() + towerArr.get(i).character.getHeight() / 2 < (centerY + 105))
                    {
                        towerArr.get(i).setSpeedBuff(1);
                        towerArr.get(i).setSupgrade(tower.getUpgrade());
                        towerArr.get(i).setAtkSpeed();
                    }
                }
            }
        }
    }
    
    public void selectMixTower()
    {
        mixError = 0;
        
        if (towerID1 == 1)
        {
            if (towerID2 == 1)
            {
                if (towerID3 == 2)
                {
                    towerID = 7;
                    cost = 75;
                }
                else if (towerID3 == 3)
                {
                    towerID = 8;
                    cost = 75;
                }
                else
                {
                    mixError = -1;
                }
            }
            else if (towerID2 == 2)
            {
                if (towerID3 == 100)
                {
                    towerID = 4;
                    cost = 50;
                }
                else if (towerID3 == 2)
                {
                    towerID = 9;
                    cost = 50;
                }
                else
                {
                    towerID = 13;
                    cost = 125;
                }
            }
            if (towerID2 == 3)
            {
                if (towerID3 == 100)
                {
                    towerID = 5;
                    cost = 50;
                }
                else
                {
                    towerID = 11;
                    cost = 50;
                }
            }
            else if (towerID2 == 100)
            {
                mixError = -1;
            }
        }
        else if (towerID1 == 2)
        {
            if (towerID2 == 2)
            {
                if (towerID3 == 3)
                {
                    towerID = 10;
                    cost = 75;
                }
                else
                {
                    mixError = -1;
                }
            }
            else if (towerID2 == 3)
            {
                if (towerID3 == 3)
                {
                    towerID = 12;
                    cost = 70;
                }
                else
                {
                    towerID = 6;
                    cost = 50;
                }
            }
            else if (towerID2 == 100)
            {
                mixError = -1;
            }
        }
        else
        {
            mixError = -1;
        }
        
        cost = (int) (cost * (1 - 0.1 * upgrade[0]));
    }
    
    private void sort(int a, int b, int c, int d)
    {
        int temp = 0;
        
        if (a > b)
        {
            temp = b;
            b = a;
            a = temp;
        }
        
        if (a > c)
        {
            temp = c;
            c = a;
            a = temp;
        }
        
        if (b > c)
        {
            temp = b;
            b = c;
            c = temp;
        }
        
        if (d == 1)
        {
            towerID1 = a;
            towerID2 = b;
            towerID3 = c;
        }
        else
        {
            towerindex1 = c;
            towerindex2 = b;
            towerindex3 = a;
        }
        
    }
    
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    
    @Override
    public void onBackKeyPressed()
    {
        if (howToMix.isVisible())
        {
            howToMix.setVisible(false);
            mScrollDetector.setEnabled(true);
            return;
        }
        
        if (pauseGame == 0 && checkAtk != 1)
        {
            pauseGame += 1;
            
            mScrollDetector.setEnabled(false);
            
            createBackMenuChildScene();
            
        }
        else if (checkAtk != 1)
        {
            pauseGame -= 1;
            
            mScrollDetector.setEnabled(true);
            
            delBackMenuChildScene();
            
        }
        return;
    }
    
    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_GAME;
    }
    
    @Override
    public void disposeScene()
    {
        this.detachSelf();
        this.dispose();
    }
    
    public void roadResources()
    {
        
    }
    
    public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent)
    {
        this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
        
        if (towerStatus > 0)
            switch (pSceneTouchEvent.getAction())
            {
            
                case TouchEvent.ACTION_DOWN:
                    
                    this.addTower(pSceneTouchEvent.getX() - mainTransparent.getX(), pSceneTouchEvent.getY()
                            - mainTransparent.getY(), towerStatus);
                    
                    if (stack == 0 && checkTile != -1)
                    {
                        menuChildScene.clearMenuItems();
                        createTowerMenuChildScene();
                        underBar.setVisible(false);
                        mixBar.setVisible(false);
                        detachGoldText();
                        transparent.attachChild(towerArr.get(towerN - 1).range_S);
                        
                        return true;
                    }
                    else
                    {
                        stack = 0;
                        return true;
                    }
                    
            }
        return true;
    }
    
    @Override
    public void onScroll(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY)
    {
        mainTransparent.setPosition(mainTransparent.getX() + pDistanceX, mainTransparent.getY() + pDistanceY);
        
        if (mainTransparent.getY() + pDistanceY > 0)
        {
            mainTransparent.setY(0);
        }
        
        if (mainTransparent.getX() + pDistanceX > 0)
        {
            mainTransparent.setX(0);
        }
        
        if (mainTransparent.getX() < -(mainTransparent.getWidth() - camera.getWidth()))
        {
            mainTransparent.setX(-(mainTransparent.getWidth() - camera.getWidth()));
            
        }
        
        if (mainTransparent.getY() < -(mainTransparent.getHeight() - camera.getHeight()))
        {
            mainTransparent.setY(-(mainTransparent.getHeight() - camera.getHeight()));
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
    
    // ===========================================================
    // MONEY
    // ===========================================================
    
    public Boolean checkMoney(int money)
    {
        if (gold >= money)
        {
            gold_temp = money;
            return true;
        }
        return false;
    }
    
    public Boolean checkMoney(double money)
    {
        if (gold >= money)
        {
            gold_temp = (int) money;
            return true;
        }
        return false;
    }
    
    // ===========================================================
    // GETTERS & SETTERS
    // ===========================================================
    
    public Sprite getUpgradebar()
    {
        return upgradeBar;
    }
    
    public Sprite getResultCharacter()
    {
        return resultCharacter;
    }
    
    public void setResultCharacter(Sprite resultCharacter)
    {
        this.resultCharacter = resultCharacter;
    }
    
    public static int getTowerindex1()
    {
        return towerindex1;
    }
    
    public static int getTowerindex2()
    {
        return towerindex2;
    }
    
    public static int getTowerindex3()
    {
        return towerindex3;
    }
    
    public static void setTowerindex1(int towerindex1)
    {
        GameScene.towerindex1 = towerindex1;
    }
    
    public static void setTowerindex2(int towerindex2)
    {
        GameScene.towerindex2 = towerindex2;
    }
    
    public static void setTowerindex3(int towerindex3)
    {
        GameScene.towerindex3 = towerindex3;
    }
    
    public IMenuItem getMix1()
    {
        return mix1;
    }
    
    public IMenuItem getMix2()
    {
        return mix2;
    }
    
    public IMenuItem getMix3()
    {
        return mix3;
    }
    
    public MenuScene getMenuChildScene()
    {
        return menuChildScene;
    }
    
    public void setMenuChildScene(MenuScene menuChildScene)
    {
        this.menuChildScene = menuChildScene;
    }
    
    public int getMixManager()
    {
        return mixManager;
    }
    
    public void setMixManager(int mixManager)
    {
        this.mixManager = mixManager;
    }
    
    public ArrayList<BaseMonster> getMon1()
    {
        return mon1;
    }
    
    public ArrayList<BaseMonster> getMon2()
    {
        return mon2;
    }
    
    public ArrayList<BaseMonster> getMon3()
    {
        return mon3;
    }
    
    public ArrayList<BaseMonster> getMon4()
    {
        return mon4;
    }
    
    public ArrayList<BaseMonster> getMon5()
    {
        return mon5;
    }
    
    public ArrayList<BaseMonster> getMon6()
    {
        return mon6;
    }
    
    public ArrayList<BaseMonster> getMon7()
    {
        return mon7;
    }
    
    public Rectangle getTransparent()
    {
        return transparent;
    }
    
    public Text getLifeText()
    {
        return lifeText;
    }
    
    public int getTowerN()
    {
        return towerN;
    }
    
    public int getCheckAtk()
    {
        return checkAtk;
    }
    
    public void setCheckAtk(int checkAtk)
    {
        this.checkAtk = checkAtk;
    }
    
    public Rectangle getMainTransparent()
    {
        return mainTransparent;
    }
    
    public ArrayList<BaseTower> getTowerArr()
    {
        return towerArr;
    }
    
    public Sprite getMixBar()
    {
        return mixBar;
    }
    
    public void setMixBar(Sprite mixBar)
    {
        this.mixBar = mixBar;
    }
    
    public void setUpgradeBar(Sprite upgradeBar)
    {
        this.upgradeBar = upgradeBar;
    }
    
    public Sprite getCreateTowerBar()
    {
        return createTowerBar;
    }
    
    public void setBarTexture(ITextureRegion barTexture)
    {
        this.barTexture = barTexture;
    }
    
    public int getExp()
    {
        return exp;
    }
    
    public void setExp(int exp)
    {
        this.exp = exp;
    }
    
    public ITextureRegion getBarTexture()
    {
        return barTexture;
    }
    
    public void setGold_temp(int gold_temp)
    {
        this.gold_temp = gold_temp;
    }
    
    public Sprite getBackgroundSprite()
    {
        return background;
    }
    
    public int getTowerStatus()
    {
        return towerStatus;
    }
    
    public Sprite getUnderBar()
    {
        return underBar;
    }
    
    public Sprite getMixcharacter()
    {
        return mixcharacter;
    }
    
    public void setMixcharacter(Sprite mixcharacter)
    {
        this.mixcharacter = mixcharacter;
    }
    
}