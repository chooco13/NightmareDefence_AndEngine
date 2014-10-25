package com.night.base;

import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

import com.night.manager.ResourcesManager;
import com.night.scene.GameScene;

public class BaseTower
{
    
    // ---------------------------------------------
    // VARIABLES
    // ---------------------------------------------
    
    public static int upgradeManager = 0;
    
    public VertexBufferObjectManager vbom;
    
    public ResourcesManager resourcesManager;
    
    public GameScene gameScene;
    
    private PathModifier pathModifier, textModifier_1, textModifier_2;
    
    private IMenuItem towerUp, towerTurn, towerSell, towerCancle;
    
    private ITextureRegion TowerTexture;
    
    public Sprite upgradebar;
    
    public Sprite[] up_S;
    
    public Sprite character;
    
    public Sprite mixcharacter;
    
    public Sprite range_S;
    
    private int price = 0;
    
    private int sum_price = 0;
    
    // ---------------------------------------------
    
    private int damageBuff, speedBuff;
    
    private int Supgrade, Dupgrade;
    
    private int id, atk, upgrade, dir, range, index;
    
    private float pX, pY, centerX, centerY;
    
    private int tiledX, tiledY;
    
    private long atkSpeed, delay;
    
    private int up, down, right, left;
    
    private boolean upgradable;
    
    // ---------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------
    
    public BaseTower(int id, final GameScene gameScene, int index, int _id, final float pX, final float pY)
    {
        up_S = new Sprite[3];
        
        damageBuff = 0;
        speedBuff = 0;
        Dupgrade = 0;
        Supgrade = 0;
        
        delay = 0;
        dir = 1;
        upgrade = 0;
        
        upgradable = true;
        
        this.resourcesManager = ResourcesManager.getInstance();
        this.id = id;
        this.index = index;
        this.gameScene = gameScene;
        
        setRange();
        setAtk();
        setAtkSpeed();
        setDir(dir);
        
        if (index != -1)
            createTower(_id, pX, pY);
    }
    
    // ---------------------------------------------
    // SPRITE SETTING
    // ---------------------------------------------
    
    public void createTower(int _id, final float pX, final float pY)
    {
        this.pX = pX;
        this.pY = pY;
        this.id = _id;
        
        tiledX = ((int) (pX - 15) / 40) * 40 + 5;
        tiledY = ((int) (pY - 15) / 40) * 40 + 5;
        
        this.pX = tiledX;
        this.pY = tiledY;
        
        setCharacter();
        
        character.setPosition(tiledX, tiledY);
        gameScene.registerTouchArea(character);
        
        centerX = tiledX + character.getWidth() / 2;
        centerY = tiledY + character.getHeight() / 2;
    }
    
    // ---------------------------------------------
    // TOWER SETTING
    // ---------------------------------------------
    
    public void setAtkSpeed()
    {
        switch (id)
        {
            case 1:
            case 3:
            case 4:
            case 6:
            case 9:
                if (speedBuff == 0)
                    atkSpeed = 1000;
                else
                    atkSpeed = 1000 * (100 - (10 + 7 * Supgrade) * speedBuff) / 100;
                break;
            
            case 5:
                if (speedBuff == 0)
                    atkSpeed = 1500;
                else
                    atkSpeed = 1500 * (100 - (10 + 7 * Supgrade) * speedBuff) / 100;
                break;
            
            case 2:
                if (speedBuff == 0)
                    atkSpeed = 1000;
                else
                    atkSpeed = 1000 * (100 - (10 + 7 * Supgrade) * speedBuff) / 100;
                break;
            
            case 11:
                if (speedBuff == 0)
                    atkSpeed = 200;
                else
                    atkSpeed = 200 * (100 - (10 + 7 * Supgrade) * speedBuff) / 100;
                break;
            
            case 10:
                if (speedBuff == 0)
                    atkSpeed = 5000 - (500 * upgrade);
                else
                    atkSpeed = (5000 - (500 * upgrade)) * (100 - (10 + 7 * Supgrade) * speedBuff) / 100;
                break;
            
            case 12:
                if (speedBuff == 0)
                    atkSpeed = 6500 - (500 * upgrade);
                else
                    atkSpeed = (6500 - (500 * upgrade)) * (100 - (10 + 7 * Supgrade) * speedBuff) / 100;
                break;
            
            case 13:
                if (speedBuff == 0)
                    atkSpeed = 8000 - (300 * upgrade);
                else
                    atkSpeed = (8000 - (300 * upgrade)) * (100 - (10 + 7 * Supgrade) * speedBuff) / 100;
                break;
            
            default:
                break;
        }
    }
    
    public void setRange()
    {
        if (id == 1 || id == 4 || id == 5 || id == 9)
            range = 1;
        else if (id == 7 || id == 8 || id == 10 || id == 11)
            range = 2;
        else if (id == 13)
            range = 3;
        else
            range = 0;
    }
    
    public void setAtk()
    {
        if (damageBuff == 0)
        {
            if (id == 1)
                atk = 25 + (10 * upgrade);
            else if (id == 2)
                atk = 20;
            else if (id == 3)
                atk = 20;
            else if (id == 4 || id == 5)
                atk = 25 + (10 * upgrade);
            else if (id == 9)
                atk = 20 + (10 * upgrade);
            else if (id == 10)
                atk = 300 + (30 * upgrade);
            else if (id == 11)
                atk = 10 + (3 * upgrade);
            else if (id == 13)
                atk = 200 + (20 * upgrade);
        }
        else
        {
            if (id == 1)
                atk = (25 + (10 * upgrade)) * (125 + 5 * Dupgrade) / 100;
            else if (id == 2)
                atk = 20 * (125 + 5 * Dupgrade) / 100;
            else if (id == 3)
                atk = 20 * (125 + 5 * Dupgrade) / 100;
            else if (id == 4 || id == 5)
                atk = 25 + (10 * upgrade) * (125 + 5 * Dupgrade) / 100;
            else if (id == 9)
                atk = (20 + (10 * upgrade)) * (125 + 5 * Dupgrade) / 100;
            else if (id == 10)
                atk = 300 + (30 * upgrade) * (125 + 5 * Dupgrade) / 100;
            else if (id == 11)
                atk = (10 + (3 * upgrade)) * (125 + 5 * Dupgrade) / 100;
            else if (id == 13)
                atk = (200 + (20 * upgrade)) * (125 + 5 * Dupgrade) / 100;
        }
    }
    
    public void setCharacter()
    {
        switch (id)
        {
            case 1:
                TowerTexture = resourcesManager.R_F_region;
                range_S = new Sprite(pX - 45, pY - 45, resourcesManager.R_RANGE_region, vbom);
                break;
            
            case 2:
                if (dir == 1)
                {
                    TowerTexture = resourcesManager.G_F_region;
                    range_S = new Sprite(pX - 5, pY - 8, resourcesManager.G_F_RANGE_region, vbom);
                }
                else if (dir == 2)
                {
                    TowerTexture = resourcesManager.G_R_region;
                    range_S = new Sprite(pX - 8, pY - 5, resourcesManager.G_R_RANGE_region, vbom);
                }
                else if (dir == 3)
                {
                    TowerTexture = resourcesManager.G_B_region;
                    range_S = new Sprite(pX - 5, pY - 45, resourcesManager.G_B_RANGE_region, vbom);
                }
                else
                {
                    TowerTexture = resourcesManager.G_L_region;
                    range_S = new Sprite(pX - 42, pY - 5, resourcesManager.G_L_RANGE_region, vbom);
                }
                break;
            
            case 3:
                if (dir == 1)
                {
                    TowerTexture = resourcesManager.B_F_region;
                    range_S = new Sprite(pX - 5, pY - 8, resourcesManager.B_F_RANGE_region, vbom);
                }
                else if (dir == 2)
                {
                    TowerTexture = resourcesManager.B_R_region;
                    range_S = new Sprite(pX - 8, pY - 5, resourcesManager.B_R_RANGE_region, vbom);
                }
                else if (dir == 3)
                {
                    TowerTexture = resourcesManager.B_B_region;
                    range_S = new Sprite(pX - 5, pY - 45, resourcesManager.B_B_RANGE_region, vbom);
                }
                else
                {
                    TowerTexture = resourcesManager.B_L_region;
                    range_S = new Sprite(pX - 42, pY - 5, resourcesManager.B_L_RANGE_region, vbom);
                }
                break;
            
            case 4:
                TowerTexture = resourcesManager.RG_F_region;
                range_S = new Sprite(pX - 45, pY - 45, resourcesManager.RG_RANGE_region, vbom);
                break;
            
            case 5:
                TowerTexture = resourcesManager.RB_F_region;
                range_S = new Sprite(pX - 45, pY - 45, resourcesManager.RB_RANGE_region, vbom);
                break;
            
            case 6:
                if (dir == 1)
                {
                    TowerTexture = resourcesManager.GB_F_region;
                    range_S = new Sprite(pX - 5, pY - 8, resourcesManager.GB_F_RANGE_region, vbom);
                }
                else if (dir == 2)
                {
                    TowerTexture = resourcesManager.GB_R_region;
                    range_S = new Sprite(pX - 8, pY - 5, resourcesManager.GB_R_RANGE_region, vbom);
                }
                else if (dir == 3)
                {
                    TowerTexture = resourcesManager.GB_B_region;
                    range_S = new Sprite(pX - 5, pY - 45, resourcesManager.GB_B_RANGE_region, vbom);
                }
                else
                {
                    TowerTexture = resourcesManager.GB_L_region;
                    range_S = new Sprite(pX - 42, pY - 5, resourcesManager.GB_L_RANGE_region, vbom);
                }
                break;
            
            case 7:
                TowerTexture = resourcesManager.RRG_F_region;
                range_S = new Sprite(pX - 85, pY - 85, resourcesManager.RRG_RANGE_region, vbom);
                break;
            
            case 8:
                TowerTexture = resourcesManager.RRB_F_region;
                range_S = new Sprite(pX - 85, pY - 85, resourcesManager.RRB_RANGE_region, vbom);
                break;
            
            case 9:
                TowerTexture = resourcesManager.RGG_F_region;
                range_S = new Sprite(pX - 45, pY - 45, resourcesManager.RGG_RANGE_region, vbom);
                break;
            
            case 10:
                TowerTexture = resourcesManager.GGB_F_region;
                range_S = new Sprite(pX - 85, pY - 85, resourcesManager.GGB_RANGE_region, vbom);
                break;
            
            case 11:
                TowerTexture = resourcesManager.RBB_F_region;
                range_S = new Sprite(pX - 85, pY - 85, resourcesManager.RBB_RANGE_region, vbom);
                break;
            
            case 12:
                if (dir == 1)
                {
                    TowerTexture = resourcesManager.GBB_F_region;
                    range_S = new Sprite(pX - 5, pY - 8, resourcesManager.GBB_F_RANGE_region, vbom);
                }
                else if (dir == 2)
                {
                    TowerTexture = resourcesManager.GBB_R_region;
                    range_S = new Sprite(pX - 8, pY - 5, resourcesManager.GBB_R_RANGE_region, vbom);
                }
                else if (dir == 3)
                {
                    TowerTexture = resourcesManager.GBB_B_region;
                    range_S = new Sprite(pX - 5, pY - 45, resourcesManager.GBB_B_RANGE_region, vbom);
                }
                else
                {
                    TowerTexture = resourcesManager.GBB_L_region;
                    range_S = new Sprite(pX - 42, pY - 5, resourcesManager.GBB_L_RANGE_region, vbom);
                }
                break;
            
            case 13:
                if (dir == 1)
                {
                    TowerTexture = resourcesManager.RGB_F_region;
                    range_S = new Sprite(pX - 5, pY + 30, resourcesManager.RGB_FB_RANGE_region, vbom);
                    range_S.setHeight(gameScene.getBackgroundSprite().getHeight() - pY + 30);
                }
                else if (dir == 2)
                {
                    TowerTexture = resourcesManager.RGB_R_region;
                    range_S = new Sprite(pX + 20, pY - 3, resourcesManager.RGB_LR_RANGE_region, vbom);
                    range_S.setWidth(gameScene.getBackgroundSprite().getWidth() - pX + 20);
                }
                else if (dir == 3)
                {
                    TowerTexture = resourcesManager.RGB_B_region;
                    range_S = new Sprite(pX - 5, 0, resourcesManager.RGB_FB_RANGE_region, vbom);
                    range_S.setHeight(pY + 5);
                }
                else
                {
                    TowerTexture = resourcesManager.RGB_L_region;
                    range_S = new Sprite(0, pY - 3, resourcesManager.RGB_LR_RANGE_region, vbom);
                    range_S.setWidth(pX + 15);
                }
                break;
        }
        
        // TODO
        
        character = new Sprite(pX, pY, TowerTexture, vbom) {
            
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY)
            {
                Log.e("", "touched");
                
                if (gameScene.getMixBar().getX() == 490)
                {
                    
                    if (gameScene.getMixManager() == 1 && character.isVisible() == true)
                    {
                        if (id >= 1 && id <= 3)
                        {
                            if (GameScene.getTowerindex1() != index && GameScene.getTowerindex2() != index
                                    && GameScene.getTowerindex3() != index)
                            {
                                if (gameScene.getMix1().getChildCount() == 0)
                                {
                                    if (GameScene.towerID1 == 100)
                                    {
                                        GameScene.towerID1 = id;
                                        GameScene.towerindex1 = index;
                                    }
                                    else if (GameScene.towerID2 == 100)
                                    {
                                        GameScene.towerID2 = id;
                                        GameScene.towerindex2 = index;
                                    }
                                    else
                                    {
                                        GameScene.towerID3 = id;
                                        GameScene.towerindex3 = index;
                                    }
                                    
                                    GameScene._towerID1 = id;
                                    GameScene._towerindex1 = index;
                                    
                                    mixcharacter = new Sprite(0, 0, TowerTexture, vbom);
                                    
                                    gameScene.getMix1().attachChild(mixcharacter);
                                }
                                else if (gameScene.getMix2().getChildCount() == 0)
                                {
                                    if (GameScene.towerID1 == 100)
                                    {
                                        GameScene.towerID1 = id;
                                        GameScene.towerindex1 = index;
                                    }
                                    else if (GameScene.towerID2 == 100)
                                    {
                                        GameScene.towerID2 = id;
                                        GameScene.towerindex2 = index;
                                    }
                                    else
                                    {
                                        GameScene.towerID3 = id;
                                        GameScene.towerindex3 = index;
                                    }
                                    
                                    GameScene._towerID2 = id;
                                    GameScene._towerindex2 = index;
                                    
                                    mixcharacter = new Sprite(0, 0, TowerTexture, vbom);
                                    
                                    gameScene.getMix2().attachChild(mixcharacter);
                                }
                                else if (gameScene.getMix3().getChildCount() == 0)
                                {
                                    if (GameScene.towerID1 == 100)
                                    {
                                        GameScene.towerID1 = id;
                                        GameScene.towerindex1 = index;
                                    }
                                    else if (GameScene.towerID2 == 100)
                                    {
                                        GameScene.towerID2 = id;
                                        GameScene.towerindex2 = index;
                                    }
                                    else
                                    {
                                        GameScene.towerID3 = id;
                                        GameScene.towerindex3 = index;
                                    }
                                    
                                    GameScene._towerID3 = id;
                                    GameScene._towerindex3 = index;
                                    
                                    mixcharacter = new Sprite(0, 0, TowerTexture, vbom);
                                    
                                    gameScene.getMix3().attachChild(mixcharacter);
                                }
                                else
                                {
                                    return false;
                                }
                            }
                        }
                    }
                }
                
                if (gameScene.getMixManager() != 1 && gameScene.getCreateTowerBar().getY() == -30
                        && upgradeManager == 0 && gameScene.getCheckAtk() == 0 && gameScene.getTowerStatus() == 0)
                {
                    for (int j = 0; j < gameScene.getTowerArr().size(); j++)
                    {
                        gameScene.getTowerArr().get(j).setIndex(j);
                        
                        if (gameScene.getTowerArr().get(j).character.getX() == character.getX()
                                && gameScene.getTowerArr().get(j).character.getY() == character.getY())
                        {
                            Log.e("", "setTowerTempIndex " + j);
                            GameScene.towerTemp = j;
                            
                            upgradeManager = 1;
                            
                            range_S.setAlpha(1);
                            
                            attachTowerBar();
                            
                            break;
                        }
                    }
                    
                }
                
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        
    }
    
    public void setDir(int dir)
    {
        if (dir > 4)
            dir -= 4;
        
        this.dir = dir;
        
        up = 20;
        down = 20;
        left = 20;
        right = 20;
        
        if (id != 13)
        {
            switch (dir)
            {
                case 1:
                    down = 60;
                    break;
                
                case 2:
                    
                    right = 60;
                    break;
                
                case 3:
                    up = 60;
                    break;
                
                case 4:
                    left = 60;
                    break;
                
                default:
                    break;
            }
        }
        else
        {
            
            switch (dir)
            {
                case 1:
                    down = (int) gameScene.getTransparent().getHeight();
                    break;
                
                case 2:
                    
                    right = (int) gameScene.getTransparent().getWidth();
                    break;
                
                case 3:
                    up = (int) gameScene.getTransparent().getHeight();
                    break;
                
                case 4:
                    left = (int) gameScene.getTransparent().getWidth();
                    break;
                
                default:
                    break;
            }
            
        }
    }
    
    // ---------------------------------------------
    // UPGRADE & MIX METHODS
    // ---------------------------------------------
    
    public void createPreview()
    {
        setCharacter();
        Sprite result = new Sprite(0, 0, TowerTexture, vbom);
        gameScene.setResultCharacter(result);
    }
    
    // TODO
    public void attachTowerBar()
    {
        try
        {
            gameScene.sellText.detachSelf();
            gameScene.upText.detachSelf();
        }
        catch (Exception e)
        {
            Log.e("", "error");
        }
        
        gameScene.sellText = new Text(300, 20, resourcesManager.mfont, "" + sellTower(), vbom);
        
        if (gameScene.getTowerArr().get(GameScene.towerTemp).isUpgradable())
        {
            gameScene.upText = new Text(160, 20, resourcesManager.mfont, ""
                    + upgradeTextPrice(gameScene.upgrade[id + 3]), vbom);
        }
        
        if (id == 2 || id == 3 || id == 6 || id == 12 || id == 13)
        {
            towerTurn = new ScaleMenuItemDecorator(new SpriteMenuItem(8, resourcesManager.Invisible_region, vbom), 1, 1);
        }
        else
        {
            towerTurn = new ScaleMenuItemDecorator(new SpriteMenuItem(8, resourcesManager.semi_region, vbom), 1, 1);
            towerTurn.registerEntityModifier(new PathModifier(1, new Path(2).to(205, -60).to(205, 0)));
        }
        
        if (upgradable)
        {
            towerUp = new ScaleMenuItemDecorator(new SpriteMenuItem(100, resourcesManager.Invisible_region, vbom), 1, 1);
        }
        else
        {
            towerUp = new ScaleMenuItemDecorator(new SpriteMenuItem(100, resourcesManager.semi_region, vbom), 1, 1);
            towerUp.registerEntityModifier(new PathModifier(1, new Path(2).to(135, -60).to(135, 0)));
        }
        
        towerSell = new ScaleMenuItemDecorator(new SpriteMenuItem(110, resourcesManager.Invisible_region, vbom), 1, 1);
        towerCancle = new ScaleMenuItemDecorator(new SpriteMenuItem(120, resourcesManager.Invisible_region, vbom), 1, 1);
        
        towerUp.setPosition(135, 0);
        towerTurn.setPosition(205, 0);
        towerSell.setPosition(275, 0);
        towerCancle.setPosition(345, 0);
        
        gameScene.setBarTexture(resourcesManager.upgradebar_region);
        gameScene.setUpgradeBar(new Sprite(135, 0, gameScene.getBarTexture(), vbom));
        gameScene.menuChildScene.attachChild(gameScene.getUpgradebar());
        
        gameScene.menuChildScene.addMenuItem(towerUp);
        gameScene.menuChildScene.addMenuItem(towerTurn);
        gameScene.menuChildScene.addMenuItem(towerSell);
        gameScene.menuChildScene.addMenuItem(towerCancle);
        
        pathModifier = new PathModifier(1, new Path(2).to(135, -60).to(135, 0));
        
        textModifier_1 = new PathModifier(1, new Path(2).to(160, -42).to(160, 18));
        
        textModifier_2 = new PathModifier(1, new Path(2).to(300, -42).to(300, 18));
        
        gameScene.getUpgradebar().registerEntityModifier(pathModifier);
        if (gameScene.getTowerArr().get(GameScene.towerTemp).getUpgrade() < 3)
            gameScene.menuChildScene.attachChild(gameScene.upText);
        gameScene.menuChildScene.attachChild(gameScene.sellText);
        
        gameScene.upText.registerEntityModifier(textModifier_1);
        gameScene.sellText.registerEntityModifier(textModifier_2);
    }
    
    public void detachtowerbar()
    {
        gameScene.menuChildScene.detachChild(gameScene.getUpgradebar());
        gameScene.menuChildScene.detachChild(gameScene.upText);
        gameScene.menuChildScene.detachChild(gameScene.sellText);
        
        gameScene.menuChildScene.clearMenuItems();
        gameScene.menuChildScene.detachChildren();
        gameScene.createMenuChildScene();
        
        gameScene.setUpgradeBar(new Sprite(135, 0, gameScene.getBarTexture(), vbom));
        
        gameScene.menuChildScene.attachChild(gameScene.getUpgradebar());
        
        if (gameScene.getTowerArr().get(GameScene.towerTemp).isUpgradable())
        {
            gameScene.menuChildScene.attachChild(gameScene.upText);
            
            textModifier_1 = new PathModifier(1, new Path(2).to(160, 18).to(160, -42));
            gameScene.upText.registerEntityModifier(textModifier_1);
            
            if (gameScene.getTowerArr().get(GameScene.towerTemp).getUpgrade() == 3)
            {
                gameScene.getTowerArr().get(GameScene.towerTemp).setUpgradable(false);
            }
        }
        else
        {
            towerUp = new ScaleMenuItemDecorator(new SpriteMenuItem(100, resourcesManager.semi_region, vbom), 1, 1);
            towerUp.registerEntityModifier(new PathModifier(1, new Path(2).to(135, 0).to(135, -60)));
            
            gameScene.menuChildScene.attachChild(towerUp);
        }
        
        gameScene.menuChildScene.attachChild(gameScene.sellText);
        
        int temp_id = gameScene.getTowerArr().get(GameScene.towerTemp).getId();
        
        if (temp_id == 2 || temp_id == 3 || temp_id == 6 || temp_id == 12 || temp_id == 13)
        {
            
        }
        else
        {
            towerTurn = new ScaleMenuItemDecorator(new SpriteMenuItem(8, resourcesManager.semi_region, vbom), 1, 1);
            towerTurn.registerEntityModifier(new PathModifier(1, new Path(2).to(205, 0).to(205, -60)));
            
            gameScene.menuChildScene.attachChild(towerTurn);
        }
        
        pathModifier = new PathModifier(1, new Path(2).to(135, 0).to(135, -60));
        textModifier_2 = new PathModifier(1, new Path(2).to(300, 18).to(300, -42));
        
        gameScene.getUpgradebar().registerEntityModifier(pathModifier);
        gameScene.sellText.registerEntityModifier(textModifier_2);
        
        upgradeManager = 0;
    }
    
    public int sellTower()
    {
        switch (id)
        {
            case 1:
                return 30 + sum_price / 2;
            case 2:
            case 3:
                return 15 + sum_price / 2;
            case 4:
            case 5:
                return 70 + sum_price / 2;
            case 6:
                return 60 + sum_price / 2;
            case 7:
            case 8:
                return 110 + sum_price / 2;
            case 9:
            case 11:
            case 12:
                return 80 + sum_price / 2;
            case 10:
                return 90 + sum_price / 2;
            case 13:
                return 120 + sum_price / 2;
            default:
                return 0;
        }
    }
    
    public void upgradeTower()
    {
        GameScene.towerTemp = index;
        upgrade++;
        
        setAtk();
        setAtkSpeed();
        
        if (upgrade == 1)
        {
            up_S[0] = new Sprite(pX - 5, pY, resourcesManager.up1_region, vbom);
            gameScene.getMainTransparent().attachChild(up_S[0]);
        }
        else if (upgrade == 2)
        {
            up_S[1] = new Sprite(pX - 5, pY, resourcesManager.up2_region, vbom);
            gameScene.getMainTransparent().detachChild(up_S[0]);
            gameScene.getMainTransparent().attachChild(up_S[1]);
        }
        else if (upgrade == 3)
        {
            up_S[2] = new Sprite(pX - 5, pY, resourcesManager.up3_region, vbom);
            gameScene.getMainTransparent().detachChild(up_S[1]);
            gameScene.getMainTransparent().attachChild(up_S[2]);
            
            upgradable = false;
        }
    }
    
    public void mixedTower()
    {
        GameScene.towerTemp = index;
        
        setAtk();
        setAtkSpeed();
        
        if (upgrade == 1)
        {
            up_S[0] = new Sprite(pX - 5, pY, resourcesManager.up1_region, vbom);
            gameScene.getMainTransparent().attachChild(up_S[0]);
        }
        else if (upgrade == 2)
        {
            up_S[1] = new Sprite(pX - 5, pY, resourcesManager.up2_region, vbom);
            gameScene.getMainTransparent().attachChild(up_S[1]);
        }
        else if (upgrade == 3)
        {
            up_S[2] = new Sprite(pX - 5, pY, resourcesManager.up3_region, vbom);
            gameScene.getMainTransparent().attachChild(up_S[2]);
        }
    }
    
    public int upgradeTextPrice(int a)
    {
        int price_t = 0;
        if (id == 1 || id == 9 || id == 11)
        {
            price_t = (int) (30 * (upgrade + 1) * (1 - 0.1 * a));
            return price_t;
        }
        else if (id == 2 || id == 3)
        {
            price_t = (int) (15 * (upgrade + 1) * (1 - 0.1 * a));
            return price_t;
        }
        else if (id == 7 || id == 8)
        {
            price_t = (int) (50 * (upgrade + 1) * (1 - 0.1 * a));
            return price_t;
        }
        else if (id == 10)
        {
            price_t = (int) (50 * (upgrade + 1) * (1 - 0.1 * a));
            return price_t;
        }
        else if (id == 12)
        {
            price_t = (int) (45 * (upgrade + 1) * (1 - 0.1 * a));
            return price_t;
        }
        else if (id == 13)
        {
            price_t = (int) (70 * (upgrade + 1) * (1 - 0.1 * a));
            return price_t;
        }
        else
            return price_t;
    }
    
    public int upgradePrice(int a)
    {
        
        if (id == 1 || id == 9 || id == 11)
        {
            price = (int) (30 * (upgrade + 1) * (1 - 0.1 * a));
            gameScene.setGold_temp(price);
            sum_price += price;
            return price;
        }
        else if (id == 2 || id == 3)
        {
            price = (int) (15 * (upgrade + 1) * (1 - 0.1 * a));
            gameScene.setGold_temp(price);
            sum_price += price;
            return price;
        }
        else if (id == 7 || id == 8)
        {
            price = (int) (50 * (upgrade + 1) * (1 - 0.1 * a));
            gameScene.setGold_temp(price);
            sum_price += price;
            return price;
        }
        else if (id == 10)
        {
            price = (int) (50 * (upgrade + 1) * (1 - 0.1 * a));
            gameScene.setGold_temp(price);
            sum_price += price;
            return price;
        }
        else if (id == 12)
        {
            price = (int) (45 * (upgrade + 1) * (1 - 0.1 * a));
            gameScene.setGold_temp(price);
            sum_price += price;
            return price;
        }
        else if (id == 13)
        {
            price = (int) (70 * (upgrade + 1) * (1 - 0.1 * a));
            gameScene.setGold_temp(price);
            sum_price += price;
            return price;
        }
        else
            return price;
        
    }
    
    // ---------------------------------------------
    // GETTERS & SETTERS
    // ---------------------------------------------
    
    public int getRange()
    {
        return range;
    }
    
    public float getCenterX()
    {
        return centerX;
    }
    
    public float getCenterY()
    {
        return centerY;
    }
    
    public int getAtk()
    {
        return atk;
    }
    
    public int getUp()
    {
        return up;
    }
    
    public int getDown()
    {
        return down;
    }
    
    public int getRight()
    {
        return right;
    }
    
    public int getLeft()
    {
        return left;
    }
    
    public void resetTimerSpeed()
    {
        delay = 0;
    }
    
    public int getDir()
    {
        return dir;
    }
    
    public int getDamageBuff()
    {
        return damageBuff;
    }
    
    public int getSpeedBuff()
    {
        return speedBuff;
    }
    
    public int getSupgrade()
    {
        return Supgrade;
    }
    
    public int getDupgrade()
    {
        return Dupgrade;
    }
    
    public void setDamageBuff(int damageBuff)
    {
        this.damageBuff = damageBuff;
    }
    
    public void setSpeedBuff(int speedBuff)
    {
        this.speedBuff = speedBuff;
    }
    
    public void setSupgrade(int supgrade)
    {
        Supgrade = supgrade;
    }
    
    public void setDupgrade(int dupgrade)
    {
        Dupgrade = dupgrade;
    }
    
    public long getAtkSpeed()
    {
        return atkSpeed;
    }
    
    public void setDelay(long delay)
    {
        this.delay = delay;
    }
    
    public int getUpgrade()
    {
        return upgrade;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public int getTiledX()
    {
        return tiledX;
    }
    
    public int getTiledY()
    {
        return tiledY;
    }
    
    public long getDelay()
    {
        return delay;
    }
    
    public ITextureRegion getTowerTexture()
    {
        return TowerTexture;
    }
    
    public int getIndex()
    {
        return index;
    }
    
    public void setIndex(int index)
    {
        this.index = index;
    }
    
    public void setUpgrade(int upgrade)
    {
        this.upgrade = upgrade;
        
        if (this.upgrade == 3)
        {
            upgradable = false;
        }
        if (this.id == 4 || this.id == 5 || this.id == 6)
        {
            upgradable = false;
        }
    }
    
    public boolean isUpgradable()
    {
        return upgradable;
    }
    
    public void setUpgradable(boolean upgradable)
    {
        this.upgradable = upgradable;
    }
}