package com.night.base;

import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.night.manager.ResourcesManager;
import com.night.scene.GameScene;

public class BaseWave
{
    // ---------------------------------------------
    // VARIABLES
    // ---------------------------------------------
    
    private int id;
    
    private int wave;
    
    private int mob1, mob2, mob3, mob4, mob5, mob6, mob7;
    
    private int way;
    
    private Path path;
    
    protected VertexBufferObjectManager vbom;
    
    private ResourcesManager resourcesManager;
    
    public BaseMonster[] monster = new BaseMonster[7];
    
    // ---------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------
    
    public BaseWave(int id, int wave, int mob1, int mob2, int mob3, int mob4, int mob5, int mob6, int mob7, int stage,
            int way)
    {
        this.resourcesManager = ResourcesManager.getInstance();
        
        resourcesManager.loadGameResources();
        
        this.id = id;
        this.wave = wave;
        this.mob1 = mob1;
        this.mob2 = mob2;
        this.mob3 = mob3;
        this.mob4 = mob4;
        this.mob5 = mob5;
        this.mob6 = mob6;
        this.mob7 = mob7;
        this.way = way;
        
        roadselect(stage, way);
        
    }
    
    // ---------------------------------------------
    // METHODS
    // ---------------------------------------------
    
    public BaseMonster createMob(int a)
    {
        BaseMonster mob = null;
        
        switch (a)
        {
            case 1:
                mob = new BaseMonster(50 * (1 + 0.25 * GameScene.mobUpgrade), 2, 1, 10, 5);
                mob.createCharacter(resourcesManager.gameMobTextureRegion1, vbom, path);
                return mob;
            case 2:
                mob = new BaseMonster(100 * (1 + 0.25 * GameScene.mobUpgrade), 3, 1, 16, 5);
                mob.createCharacter(resourcesManager.gameMobTextureRegion2, vbom, path);
                return mob;
            case 3:
                mob = new BaseMonster(25 * (1 + 0.25 * GameScene.mobUpgrade), 3, 1, 7, 5);
                mob.createCharacter(resourcesManager.gameMobTextureRegion3, vbom, path);
                return mob;
            case 4:
                mob = new BaseMonster(100 * (1 + 0.25 * GameScene.mobUpgrade), 2, 3, 9, 7);
                mob.createCharacter(resourcesManager.gameMobTextureRegion4, vbom, path);
                return mob;
            case 5:
                mob = new BaseMonster(300 * (1 + 0.25 * GameScene.mobUpgrade), 2, 3, 16, 7);
                mob.createCharacter(resourcesManager.gameMobTextureRegion5, vbom, path);
                return mob;
            case 6:
                mob = new BaseMonster(125 * (1 + 0.25 * GameScene.mobUpgrade), 2, 3, 6, 7);
                mob.createCharacter(resourcesManager.gameMobTextureRegion6, vbom, path);
                return mob;
            case 7:
                mob = new BaseMonster(5000 * (1 + 0.25 * GameScene.mobUpgrade), 25, 5, 16, 30);
                mob.createCharacter(resourcesManager.gameMobTextureRegion7, vbom, path);
                return mob;
        }
        
        return null;
    }
    
    public void roadselect(int stage, int way)
    {
        switch (stage)
        {
            case 1:
                path = new Path(16).to(-60, 100).to(-60, 0).to(0, 0).to(160, 0).to(160, 80).to(0, 80).to(0, 240).to(400, 240).to(400, 80)
                        .to(240, 80).to(240, 0).to(560, 0).to(560, 240).to(680, 240).to(680, 280).to(800, 280);
                break;
            case 2:
                path = new Path(16).to(-60, 100).to(-60, 40).to(0, 40).to(120, 40).to(120, 240).to(80, 240).to(80, 440).to(320, 440)
                        .to(320, 360).to(240, 360).to(240, 200).to(520, 200).to(520, 80).to(640, 80).to(640, 480)
                        .to(760, 480);
                break;
            case 3:
                if (way == 1)
                {
                    path = new Path(12).to(-60, 180).to(-60, 280).to(0, 280).to(240, 280).to(240, 200).to(160, 200).to(160, 40).to(400, 40)
                            .to(400, 200).to(560, 200).to(560, 280).to(760, 280);
                }
                else
                {
                    path = new Path(12).to(-60, 180).to(-60, 280).to(0, 280).to(240, 280).to(240, 360).to(160, 360).to(160, 480).to(440, 480)
                            .to(440, 360).to(560, 360).to(560, 280).to(760, 280);
                }
                break;
            default:
                break;
        }
    }
    
    // ---------------------------------------------
    // GETTERS & SETTERS
    // ---------------------------------------------
    
    public int getId()
    {
        return id;
    }
    
    public int getWave()
    {
        return wave;
    }
    
    public int getMob1()
    {
        return mob1;
    }
    
    public int getMob2()
    {
        return mob2;
    }
    
    public int getMob3()
    {
        return mob3;
    }
    
    public int getMob4()
    {
        return mob4;
    }
    
    public int getMob5()
    {
        return mob5;
    }
    
    public int getMob6()
    {
        return mob6;
    }
    
    public int getMob7()
    {
        return mob7;
    }
    
    public int getWay()
    {
        return way;
    }
}