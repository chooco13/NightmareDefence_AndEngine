package com.night.base;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.night.manager.ResourcesManager;
import com.night.scene.GameScene;

public class BaseMonster
{
    
    // ---------------------------------------------
    // VARIABLES
    // ---------------------------------------------
    
    private boolean dead, stop, start;
    
    private int gold, speed, atk, poisonCount, exp;
    
    private double hp;
    
    public VertexBufferObjectManager vbom;
    
    public ResourcesManager resourcesManager;
    
    public Sprite character;
    
    public GameScene gameScene;
    
    private LoopEntityModifier loop;
    
    private Path path;
    
    // ---------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------
    
    public BaseMonster(double hp, int gold, int atk, int speed, int exp)
    {
        start = false;
        dead = false;
        stop = false;
        
        poisonCount = 0;
        
        this.hp = hp;
        this.gold = gold;
        this.atk = atk;
        this.speed = speed;
        this.exp = exp;
        this.resourcesManager = ResourcesManager.getInstance();
    }
    
    // ---------------------------------------------
    // CHARACTER SETTING
    // ---------------------------------------------
    
    public void createCharacter(TiledTextureRegion texture, VertexBufferObjectManager vbom, Path path)
    {
        
        character = new Sprite(-50, -50, texture, this.vbom);
        
        this.path = path;
        
        setLoop(path);
        
        character.registerEntityModifier(loop);
        
    }
    
    public LoopEntityModifier getLoop()
    {
        setLoop(this.path);
        return loop;
    }
    
    public void setLoop(Path path)
    {
        loop = new LoopEntityModifier(new PathModifier(speed, path, null, new IPathModifierListener() {
            @Override
            public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity)
            {
                if (!start)
                {
                    GameScene.realcount++;
                    start = true;
                }
            }
            
            @Override
            public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity,
                    final int pWaypointIndex)
            {
                
            }
            
            @Override
            public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity,
                    final int pWaypointIndex)
            {
                
            }
            
            @Override
            public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity)
            {
                if (hp > 0)
                {
                    GameScene.death++;
                    
                    for (; atk > 0; atk--)
                        GameScene.life--;
                }
                
                pEntity.setVisible(false);
                pEntity.clearEntityModifiers();
                
                character.setCullingEnabled(true);
                character.dispose();
            }
            
        }));
    }
    
    // ---------------------------------------------
    // Getters & Setters
    // ---------------------------------------------
    
    public double getHp()
    {
        return hp;
    }
    
    public void setHp(double hp)
    {
        this.hp = hp;
    }
    
    public void setDead(boolean dead)
    {
        GameScene.gold += this.gold;
        this.dead = dead;
    }
    
    public boolean isDead()
    {
        return dead;
    }
    
    public int getPoisonCount()
    {
        return poisonCount;
    }
    
    public void setPoisonCount(int poisonCount)
    {
        this.poisonCount = poisonCount;
    }
    
    public int getGold()
    {
        return gold;
    }
    
    public void setGold(int gold)
    {
        this.gold = gold;
    }
    
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    
    public int getExp()
    {
        return exp;
    }
    
    public boolean isStop()
    {
        return stop;
    }
    
    public void setStop(boolean stop)
    {
        this.stop = stop;
    }
}