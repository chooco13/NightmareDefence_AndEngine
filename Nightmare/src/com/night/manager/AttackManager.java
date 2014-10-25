package com.night.manager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;

import android.util.Log;

import com.night.base.BaseMonster;
import com.night.base.BaseTower;
import com.night.scene.GameScene;

public class AttackManager
{
    private Random random;
    
    private GameScene gameScene;
    
    private Timer attackTimer;
    
    private TimerTask checkTask;
    
    private AlphaModifier alphaModifier;
    
    private int mCount = 0;
    
    public AttackManager(GameScene gameScene)
    {
        random = new Random();
        this.gameScene = gameScene;
    }
    
    // ---------------------------------------------
    // MAIN ATTACK
    // ---------------------------------------------
    
    public void attack(final ArrayList<BaseMonster> mob1, final ArrayList<BaseMonster> mob2,
            final ArrayList<BaseMonster> mob3, final ArrayList<BaseMonster> mob4, final ArrayList<BaseMonster> mob5,
            final ArrayList<BaseMonster> mob6, final ArrayList<BaseMonster> mob7, final BaseTower tower)
    {
        if (tower.getDelay() > 0)
        {
            tower.setDelay(tower.getDelay() - 100);
            return;
        }
        
        alphaModifier = new AlphaModifier(1, 1, 0);
        
        int range = tower.getRange();
        float centerX = tower.getCenterX();
        float centerY = tower.getCenterY();
        
        if (mob1.size() != 0)
            Attack_R(mob1, tower, range, centerX, centerY);
        if (mob2.size() != 0)
            Attack_R(mob2, tower, range, centerX, centerY);
        if (mob3.size() != 0)
            Attack_R(mob3, tower, range, centerX, centerY);
        if (mob4.size() != 0)
            Attack_R(mob4, tower, range, centerX, centerY);
        if (mob5.size() != 0)
            Attack_R(mob5, tower, range, centerX, centerY);
        if (mob6.size() != 0)
            Attack_R(mob6, tower, range, centerX, centerY);
        if (mob7.size() != 0)
            Attack_R(mob7, tower, range, centerX, centerY);
        
        if (mCount > 0)
        {
            tower.range_S.registerEntityModifier(alphaModifier);
            
            mCount = 0;
            tower.setDelay(tower.getAtkSpeed());
        }
    }
    
    private void Attack_R(final ArrayList<BaseMonster> mob, final BaseTower tower, int range, float centerX,
            float centerY)
    {
        if (range == 1)
        {// 3*3
            for (int i = 0; i < mob.size(); i++)
            {
                if (mob.get(i).isDead() == false)
                {
                    if (mob.get(i).character.getX() + mob.get(i).character.getWidth() / 2 > (centerX - 60)
                            && mob.get(i).character.getX() + mob.get(i).character.getWidth() / 2 < (centerX + 60))
                    {
                        if (mob.get(i).character.getY() + mob.get(i).character.getHeight() / 2 > (centerY - 60)
                                && mob.get(i).character.getY() + mob.get(i).character.getHeight() / 2 < (centerY + 60))
                        {
                            mCount++;
                            normalAtk(mob.get(i), tower);
                            
                            if (tower.getId() == 4)
                            {
                                normalAtk(mob.get(i), tower);
                                poisonAtk(mob.get(i), tower);
                            }
                            else if (tower.getId() == 5)
                            {
                                stopAtk(mob.get(i));
                                normalAtk(mob.get(i), tower);
                            }
                            else if (tower.getId() == 9)
                            {
                                criticalAtk(mob.get(i), tower);
                            }
                        }
                    }
                }
            }
        }
        else if (range == 2)
        {// 5*5
            for (int i = 0; i < mob.size(); i++)
            {
                if (mob.get(i).isDead() == false)
                {
                    if (mob.get(i).character.getX() + mob.get(i).character.getWidth() / 2 > (centerX - 105)
                            && mob.get(i).character.getX() + mob.get(i).character.getWidth() / 2 < (centerX + 105))
                    {
                        if (mob.get(i).character.getY() + mob.get(i).character.getHeight() / 2 > (centerY - 105)
                                && mob.get(i).character.getY() + mob.get(i).character.getHeight() / 2 < (centerY + 105))
                        {
                            if (tower.getId() == 10)
                            {
                                normalAtk(mob.get(i), tower);
                                
                                tower.setDelay(tower.getAtkSpeed());
                                tower.range_S.registerEntityModifier(alphaModifier);
                                
                                return;
                            }
                            else if (tower.getId() == 11)
                            {
                                longAtk(mob.get(i), centerX, centerY, tower);
                            }
                        }
                    }
                }
            }
        }
        else if (range == 3)
        {// line
        
            for (int i = 0; i < mob.size(); i++)
            {
                if (mob.get(i).isDead() == false)
                {
                    if (mob.get(i).character.getX() + mob.get(i).character.getWidth() / 2 > (centerX - tower.getLeft())
                            && mob.get(i).character.getX() + mob.get(i).character.getWidth() / 2 < (centerX + tower
                                    .getRight()))
                    {
                        if (mob.get(i).character.getY() + mob.get(i).character.getHeight() / 2 > (centerY - tower
                                .getUp())
                                && mob.get(i).character.getY() + mob.get(i).character.getHeight() / 2 < (centerY + tower
                                        .getDown()))
                        {
                            mCount++;
                            
                            normalAtk(mob.get(i), tower);
                        }
                    }
                }
            }
        }
        else
        {// 1*1
        
            for (int i = 0; i < mob.size(); i++)
            {
                if (mob.get(i).isDead() == false)
                {
                    if (mob.get(i).character.getX() + mob.get(i).character.getWidth() / 2 > (centerX - tower.getLeft())
                            && mob.get(i).character.getX() + mob.get(i).character.getWidth() / 2 < (centerX + tower
                                    .getRight()))
                    {
                        if (mob.get(i).character.getY() + mob.get(i).character.getHeight() / 2 > (centerY - tower
                                .getUp())
                                && mob.get(i).character.getY() + mob.get(i).character.getHeight() / 2 < (centerY + tower
                                        .getDown()))
                        {
                            mCount++;
                            if (tower.getId() == 2)
                            {
                                poisonAtk(mob.get(i), tower);
                            }
                            else if (tower.getId() == 3)
                            {
                                stopAtk(mob.get(i));
                            }
                            else if (tower.getId() == 6)
                            {
                                stopAtk(mob.get(i));
                                poisonAtk(mob.get(i), tower);
                            }
                            else if (tower.getId() == 12)
                            {
                                teleportAtk(mob.get(i));
                            }
                        }
                    }
                }
            }
        }
    }
    
    // ---------------------------------------------
    // ATTACK METHODS
    // ---------------------------------------------
    
    private void longAtk(final BaseMonster mob, float centerX, float centerY, final BaseTower tower)
    {
        if (mob.character.getX() + mob.character.getWidth() / 2 > (centerX - 60)
                && mob.character.getX() + mob.character.getWidth() / 2 < (centerX + 60))
        {
            if (mob.character.getY() + mob.character.getHeight() / 2 > (centerY - 60)
                    && mob.character.getY() + mob.character.getHeight() / 2 < (centerY + 60))
            {
                
            }
            else
            {
                mCount++;
                normalAtk(mob, tower);
            }
        }
        else
        {
            mCount++;
            normalAtk(mob, tower);
        }
    }
    
    // ---------------------------------------------
    
    private void teleportAtk(final BaseMonster mob)
    {
        mob.character.clearEntityModifiers();
        mob.character.registerEntityModifier(mob.getLoop());
    }
    
    // ---------------------------------------------
    
    private void poisonAtk(final BaseMonster mob, final BaseTower tower)
    {
        mob.setPoisonCount(6);
        
        final Timer mTimer = new Timer();
        
        TimerTask mTask = new TimerTask() {
            
            @Override
            public void run()
            {
                {
                    if (gameScene.getCheckAtk() == 1)
                    {
                        if (mob.getPoisonCount() > 0)
                        {
                            mob.setHp(mob.getHp() * (100 - (int) (5 + 5 * tower.getUpgrade())) / 100);
                            mob.setPoisonCount(mob.getPoisonCount() - 1);
                        }
                        else
                        {
                            mTimer.cancel();
                        }
                    }
                    else
                    {
                        mTimer.cancel();
                    }
                }
            }
        };
        mTimer.schedule(mTask, 0, 500);
    }
    
    // ---------------------------------------------
    
    private void normalAtk(final BaseMonster mob, final BaseTower tower)
    {
        double hp = mob.getHp();
        
        mob.setHp(hp - tower.getAtk());
        
        if (mob.getHp() <= 0 && mob.isDead() == false)
        {
            mob.setDead(true);
            
            gameScene.setExp(gameScene.getExp() + mob.getExp());
            
            mob.character.clearEntityModifiers();
            mob.character.registerEntityModifier(new AlphaModifier(0.5f, 1, 0) {
                @Override
                protected void onModifierFinished(IEntity pItem)
                {
                    GameScene.death++;
                    mob.character.setPosition(-200, -200);
                    try
                    {
                        mob.character.dispose();
                    }
                    catch (Exception e)
                    {
                        Log.e("", "dispose error");
                    }
                    super.onModifierFinished(pItem);
                }
            });
            
        }
        
    }
    
    // ---------------------------------------------
    
    private void criticalAtk(final BaseMonster mob, final BaseTower tower)
    {
        int percentage = random.nextInt(100);
        
        if (percentage > (100 - (10 + 5 * tower.getUpgrade())))
        {
            
            double hp = mob.getHp();
            
            mob.setHp(hp - tower.getAtk() * (10 + (5 * tower.getUpgrade()) / 100));
            
            if (mob.getHp() <= 0 && mob.isDead() == false)
            {
                
                mob.setDead(true);
                
                gameScene.setExp(gameScene.getExp() + mob.getExp());
                
                mob.character.clearEntityModifiers();
                mob.character.registerEntityModifier(new AlphaModifier(0.5f, 1, 0) {
                    @Override
                    protected void onModifierFinished(IEntity pItem)
                    {
                        GameScene.death++;
                        mob.character.setPosition(-200, -200);
                        try
                        {
                            mob.character.dispose();
                        }
                        catch (Exception e)
                        {
                            Log.e("", "dispose error");
                        }
                        super.onModifierFinished(pItem);
                    }
                });
                
            }
            
        }
    }
    
    // ---------------------------------------------
    
    private void stopAtk(final BaseMonster mob)
    {
        if (mob.isStop() == false)
        {
            mob.setStop(true);
            mob.character.setIgnoreUpdate(true);
            
            final Timer mTimer = new Timer();
            
            TimerTask mTask = new TimerTask() {
                
                @Override
                public void run()
                {
                    {
                        if (gameScene.getCheckAtk() == 1)
                        {
                            mob.character.setIgnoreUpdate(false);
                            mob.setStop(false);
                        }
                    }
                }
            };
            
            mTimer.schedule(mTask, 1000);
            
        }
    }
    
    // ---------------------------------------------
    // ATTACK CHECK
    // ---------------------------------------------
    
    public void checkattack()
    {
        attackTimer = new Timer();
        
        checkTask = new TimerTask() {
            
            @Override
            public void run()
            {
                {
                    for (int j = 0; j < gameScene.getTowerArr().size(); j++)
                    {
                        try
                        {
                            if (gameScene.getTowerArr().get(j).getId() != 7
                                    && gameScene.getTowerArr().get(j).getId() != 8)
                                attack(gameScene.getMon1(), gameScene.getMon2(), gameScene.getMon3(),
                                       gameScene.getMon4(), gameScene.getMon5(), gameScene.getMon6(),
                                       gameScene.getMon7(), gameScene.getTowerArr().get(j));
                        }
                        catch (Exception e)
                        {
                            Log.e("checkattack catch", "error");
                        }
                        
                    }
                }
            }
        };
        
        attackTimer.schedule(checkTask, 0, 100);
    }
    
    // ---------------------------------------------
    
    public void stoptimer()
    {
        attackTimer.cancel();
        attackTimer = null;
        
        checkTask.cancel();
        checkTask = null;
    }
    
}
