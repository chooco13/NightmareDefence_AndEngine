package com.night.manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager extends Activity
{
    SharedPreferences pref;
    
    public int getStatPreferences(Context context, int i)
    {
        pref = context.getSharedPreferences("stat", MODE_PRIVATE);
        String stat = "STAT" + String.valueOf(i);
        return pref.getInt(stat, 0);
    }
    
    public int getLevelPreferences(Context context)
    {
        pref = context.getSharedPreferences("stat", MODE_PRIVATE);
        return pref.getInt("LEVEL", 1);
    }
    
    public int getPointPreferences(Context context)
    {
        pref = context.getSharedPreferences("stat", MODE_PRIVATE);
        return pref.getInt("POINT", 0);
    }
    
    public int getRexpPreferences(Context context)
    {
        pref = context.getSharedPreferences("stat", MODE_PRIVATE);
        return pref.getInt("REXP", 0);
    }
    
    public int getNexpPreferences(Context context)
    {
        pref = context.getSharedPreferences("stat", MODE_PRIVATE);
        return pref.getInt("NEXP", 0);
    }
    
    public void savePreferences(int level, int point, int rexp, int nexp, Context context)
    {
        SharedPreferences pref = context.getSharedPreferences("stat", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("LEVEL", level);
        editor.putInt("POINT", point);
        editor.putInt("REXP", rexp);// 남은
        editor.putInt("NEXP", nexp);// 현재
        
        editor.commit();
    }
    
    public void saveNexp(int nexp, Context context)
    {
        SharedPreferences pref = context.getSharedPreferences("stat", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("NEXP", nexp);
        editor.commit();
    }
    
    public void savestatPreferences(int stat, int n, Context context)
    {
        SharedPreferences pref = context.getSharedPreferences("stat", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String stata = "STAT" + String.valueOf(n);
        editor.putInt(stata, stat);
        editor.commit();
    }
}
