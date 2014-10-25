package com.night.manager;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.graphics.Color;

import com.night.mare.GameActivity;

/**
 * @클래스이름 : ResourcesManager.java
 * @클래스설명 : 리소스를 관리하는 클래스
 */
public class ResourcesManager
{
    
    // ---------------------------------------------
    // VARIABLES
    // ---------------------------------------------
    
    private static final ResourcesManager INSTANCE = new ResourcesManager();
    
    public Engine engine;
    
    public GameActivity activity;
    
    public BoundCamera camera;
    
    public VertexBufferObjectManager vbom;
    
    public Font font;
    
    public Font mfont;
    
    // ---------------------------------------------
    // TEXTURES & TEXTURE REGIONS
    // ---------------------------------------------
    
    private BuildableBitmapTextureAtlas menuTextureAtlas;
    
    private BuildableBitmapTextureAtlas manualTextureAtlas;
    
    private BitmapTextureAtlas splashTextureAtlas;
    
    public BitmapTextureAtlas gameTextureAtlas1;
    
    public BitmapTextureAtlas gameTextureAtlas2;
    
    public BitmapTextureAtlas gameTextureAtlas3;
    
    public BitmapTextureAtlas gameTextureAtlas4;
    
    public BitmapTextureAtlas gameTextureAtlas5;
    
    public BitmapTextureAtlas gameTextureAtlas6;
    
    public BitmapTextureAtlas gameTextureAtlas7;
    
    // --------------------------------------------- SPLASH
    
    public ITextureRegion splash_region;
    
    // --------------------------------------------- MENU
    
    public ITextureRegion menu_background_region;
    
    public ITextureRegion life_region;
    
    public ITextureRegion play_region;
    
    // --------------------------------------------- STAGE SELECT
    
    public ITextureRegion stage_background_region;
    
    public ITextureRegion status1_region;
    
    public ITextureRegion status2_region;
    
    public ITextureRegion status3_region;
    
    public ITextureRegion status4_region;
    
    public ITextureRegion stage_left;
    
    public ITextureRegion stage_right;
    
    public ITextureRegion stage1_region;
    
    public ITextureRegion stage2_region;
    
    public ITextureRegion creattowerbar;
    
    public ITextureRegion stage3_region;
    
    public ITextureRegion status_region;
    
    public ITextureRegion back_region;
    
    // --------------------------------------------- STATUS
    
    public ITextureRegion status_background_region;
    
    public ITextureRegion prev_region;
    
    public ITextureRegion rectangle_btn;
    
    public ITextureRegion next_region;
    
    public ITextureRegion exit_region;
    
    public ITextureRegion plus_region;
    
    public ITextureRegion minus_region;
    
    // --------------------------------------------- GAME
    
    public ITextureRegion stage1_background_region;
    
    public ITextureRegion stage2_background_region;
    
    public ITextureRegion stage3_background_region;
    
    public TiledTextureRegion gameMobTextureRegion1;
    
    public TiledTextureRegion gameMobTextureRegion2;
    
    public TiledTextureRegion gameMobTextureRegion3;
    
    public TiledTextureRegion gameMobTextureRegion4;
    
    public TiledTextureRegion gameMobTextureRegion5;
    
    public TiledTextureRegion gameMobTextureRegion6;
    
    public TiledTextureRegion gameMobTextureRegion7;
    
    public ITextureRegion stage_region;
    
    public ITextureRegion cancle_region;
    
    public ITextureRegion upgradebar_region;
    
    public ITextureRegion underbar_region;
    
    public ITextureRegion help_region;
    
    public ITextureRegion mixbar_region;
    
    public ITextureRegion mix_region;
    
    public ITextureRegion Rbutton_region;
    
    public ITextureRegion Bbutton_region;
    
    public ITextureRegion Gbutton_region;
    
    public ITextureRegion R_F_region;
    
    public ITextureRegion R_RANGE_region;
    
    public ITextureRegion RRG_F_region;
    
    public ITextureRegion RRG_RANGE_region;
    
    public ITextureRegion RG_F_region;
    
    public ITextureRegion RG_RANGE_region;
    
    public ITextureRegion RGG_F_region;
    
    public ITextureRegion RGG_RANGE_region;
    
    public ITextureRegion G_F_region, G_L_region, G_B_region, G_R_region;
    
    public ITextureRegion G_F_RANGE_region, G_L_RANGE_region, G_B_RANGE_region, G_R_RANGE_region;
    
    public ITextureRegion GGB_F_region;
    
    public ITextureRegion GGB_RANGE_region;
    
    public ITextureRegion GB_F_region, GB_L_region, GB_B_region, GB_R_region;
    
    public ITextureRegion GB_F_RANGE_region, GB_L_RANGE_region, GB_B_RANGE_region, GB_R_RANGE_region;
    
    public ITextureRegion GBB_F_region, GBB_L_region, GBB_B_region, GBB_R_region;
    
    public ITextureRegion GBB_F_RANGE_region, GBB_L_RANGE_region, GBB_B_RANGE_region, GBB_R_RANGE_region;
    
    public ITextureRegion B_F_region, B_L_region, B_B_region, B_R_region;
    
    public ITextureRegion B_F_RANGE_region, B_L_RANGE_region, B_B_RANGE_region, B_R_RANGE_region;
    
    public ITextureRegion RBB_F_region;
    
    public ITextureRegion RBB_RANGE_region;
    
    public ITextureRegion RB_F_region;
    
    public ITextureRegion RB_RANGE_region;
    
    public ITextureRegion RRB_F_region;
    
    public ITextureRegion RRB_RANGE_region;
    
    public ITextureRegion RGB_F_region, RGB_L_region, RGB_B_region, RGB_R_region;
    
    public ITextureRegion RGB_FB_RANGE_region, RGB_LR_RANGE_region;
    
    public ITextureRegion START_region;
    
    public ITextureRegion Invisible_region;
    
    public TextureRegion tile_region;
    
    public ITextureRegion Invisible_tower_region;
    
    public ITextureRegion invisible_mixButton_region;
    
    public ITextureRegion up1_region, up2_region, up3_region;
    
    public ITextureRegion Error_region;
    
    public ITextureRegion stage_select_background_region;
    
    public ITextureRegion creattowerbar2;
    
    public ITextureRegion loading_region;
    
    public ITextureRegion gameover_region;
    
    public ITextureRegion stageClear_region;
    
    public ITextureRegion semi_region;
    
    public ITextureRegion manualbutton_region;
    
    public ITextureRegion manual1_region;
    
    public ITextureRegion manual2_region;
    
    public ITextureRegion manual3_region;
    
    public ITextureRegion manual4_region;
    
    public ITextureRegion manual5_region;
    
    public ITextureRegion manual6_region;
    
    public ITextureRegion manual7_region;
    
    public ITextureRegion mixguide_region;
    
    // ---------------------------------------------
    // RESOURCES METHOD
    // ---------------------------------------------
    
    public static void prepareManager(Engine engine, GameActivity activity, BoundCamera camera,
            VertexBufferObjectManager vbom)
    {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vbom = vbom;
    }
    
    // ---------------------------------------------
    // MENU RESOURCES
    // ---------------------------------------------
    
    public void loadMenuResources()
    {
        loadMenuGraphics();
        loadMenuFonts();
    }
    
    public void unloadMenuTextures()
    {
        menuTextureAtlas.unload();
    }
    
    public void loadMenuTextures()
    {
        menuTextureAtlas.load();
    }
    
    public void loadMenu()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
        
        menu_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                        "menu_background.png");
        
        play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "play.png");
        
        manualbutton_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                     "manualButton.png");
    }
    
    public void loadStage()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/stage/");
        
        stage_select_background_region = BitmapTextureAtlasTextureRegionFactory
                .createFromAsset(menuTextureAtlas, activity, "stage_select_background.png");
        
        stage_left = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                            "stage_left.png");
        stage_right = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                             "stage_right.png");
        
        status_region = BitmapTextureAtlasTextureRegionFactory
                .createFromAsset(menuTextureAtlas, activity, "status.png");
        back_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "back.png");
        
        stage1_region = BitmapTextureAtlasTextureRegionFactory
                .createFromAsset(menuTextureAtlas, activity, "stage1.png");
        stage2_region = BitmapTextureAtlasTextureRegionFactory
                .createFromAsset(menuTextureAtlas, activity, "stage2.png");
        stage3_region = BitmapTextureAtlasTextureRegionFactory
                .createFromAsset(menuTextureAtlas, activity, "stage3.png");
    }
    
    public void loadLoading()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        
        loading_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                "loading.png");
    }
    
    public void loadStatus()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/status/");
        
        status_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                          "status_background.png");
        
        plus_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "plus.png");
        
        minus_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "minus.png");
        
        exit_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "Exit.png");
        
        status1_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                "status1.png");
        
        status2_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                "status2.png");
        
        status3_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                "status3.png");
        
        status4_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                "status4.png");
        
        rectangle_btn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                               "rectangle_btn.png");
    }
    
    public void loadGame()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
        
        underbar_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                 "underbar.png");
        
        help_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                             "invisiblehelp.png");
        
        cancle_region = BitmapTextureAtlasTextureRegionFactory
                .createFromAsset(menuTextureAtlas, activity, "cancle.png");
        
        stage_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "stage.png");
        
        mix_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "mix.png");
        
        Rbutton_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                "Rbutton.png");
        
        Gbutton_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                "Gbutton.png");
        
        Bbutton_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                "Bbutton.png");
        
        Error_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "Error.png");
        
        B_L_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "B_L.png");
        B_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "B_F.png");
        B_B_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "B_B.png");
        B_R_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "B_R.png");
        B_L_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "B_L_RANGE.png");
        B_F_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "B_F_RANGE.png");
        B_B_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "B_B_RANGE.png");
        B_R_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "B_R_RANGE.png");
        
        G_L_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "G_L.png");
        G_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "G_F.png");
        G_B_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "G_B.png");
        G_R_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "G_R.png");
        G_L_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "G_L_RANGE.png");
        G_F_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "G_F_RANGE.png");
        G_B_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "G_B_RANGE.png");
        G_R_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "G_R_RANGE.png");
        
        GB_L_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "GB_L.png");
        GB_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "GB_F.png");
        GB_B_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "GB_B.png");
        GB_R_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "GB_R.png");
        GB_L_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                   "GB_L_RANGE.png");
        GB_F_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                   "GB_F_RANGE.png");
        GB_B_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                   "GB_B_RANGE.png");
        GB_R_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                   "GB_R_RANGE.png");
        
        GBB_L_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "GBB_L.png");
        GBB_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "GBB_F.png");
        GBB_B_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "GBB_B.png");
        GBB_R_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "GBB_R.png");
        GBB_L_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                    "GBB_L_RANGE.png");
        GBB_F_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                    "GBB_F_RANGE.png");
        GBB_B_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                    "GBB_B_RANGE.png");
        GBB_R_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                    "GBB_R_RANGE.png");
        
        GGB_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "GGB_F.png");
        GGB_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "GGB_RANGE.png");
        
        R_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "R_F.png");
        
        R_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                "R_RANGE.png");
        
        RB_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "RB_F.png");
        RB_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                 "RB_RANGE.png");
        
        RBB_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "RBB_F.png");
        RBB_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "RBB_RANGE.png");
        
        RG_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "RG_F.png");
        RG_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                 "RG_RANGE.png");
        
        RGG_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "RGG_F.png");
        RGG_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "RGG_RANGE.png");
        
        RRB_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "RRB_F.png");
        RRB_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "RRB_RANGE.png");
        
        RRG_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "RRG_F.png");
        RRG_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "RRG_RANGE.png");
        
        RGB_F_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "RGB_F.png");
        RGB_L_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "RGB_L.png");
        RGB_B_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "RGB_B.png");
        RGB_R_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "RGB_R.png");
        
        RGB_LR_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                     "RGB_RL_RANGE.png");
        RGB_FB_RANGE_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                     "RGB_FB_RANGE.png");
        
        START_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "start.png");
        Invisible_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                  "invisibletouch.png");
        semi_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "semi.png");
        
        Invisible_tower_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                        "invisibletower.png");
        mixbar_region = BitmapTextureAtlasTextureRegionFactory
                .createFromAsset(menuTextureAtlas, activity, "mixbar.png");
        creattowerbar = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                               "creattowerbar.png");
        creattowerbar2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                "creattowerbar2.png");
        
        stage1_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                          "stage1_background.png");
        
        stage2_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                          "stage2_background.png");
        
        stage3_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                          "stage3_background.png");
        
        life_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "life.png");
        
        tile_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "tile.png");
        
        invisible_mixButton_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                            "invisible_mixButton.png");
        
        upgradebar_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                   "upgradebar.png");
        
        up1_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "up1.png");
        
        up2_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "up2.png");
        
        up3_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "up3.png");
        
        gameover_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                 "gameover.png");
        
        stageClear_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                   "stageclear.png");
        
        mixguide_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity,
                                                                                 "mixguide.png");
    }
    
    private void loadMenuGraphics()
    {
        
        menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048,
                TextureOptions.BILINEAR);
        
        loadMenu();
        
        loadStage();
        
        loadLoading();
        
        loadStatus();
        
        loadGame();
        
        // ---------------------------------------------
        
        try
        {
            this.menuTextureAtlas
                    .build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            this.menuTextureAtlas.load();
        }
        catch (final TextureAtlasBuilderException e)
        {
            Debug.e(e);
        }
    }
    
    private void loadMenuFonts()
    {
        FontFactory.setAssetBasePath("font/");
        
        final ITexture statusFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        
        mfont = FontFactory.createStrokeFromAsset(activity.getFontManager(), statusFontTexture, activity.getAssets(),
                                                  "font.ttf", 30, true, Color.BLACK, 0.3f, Color.BLACK);
        mfont.load();
        
    }
    
    // ---------------------------------------------
    // GAME RESOURCES
    // ---------------------------------------------
    
    public void loadGameResources()
    {
        loadGameGraphics();
    }
    
    private void loadGameGraphics()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/monster/");
        
        gameTextureAtlas1 = new BitmapTextureAtlas(engine.getTextureManager(), 50, 50, TextureOptions.BILINEAR);
        
        gameMobTextureRegion1 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas1,
                                                                                            activity, "mob1.png", 0, 0,
                                                                                            1, 1);
        
        gameTextureAtlas2 = new BitmapTextureAtlas(engine.getTextureManager(), 50, 50, TextureOptions.BILINEAR);
        
        gameMobTextureRegion2 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas2,
                                                                                            activity, "mob2.png", 0, 0,
                                                                                            1, 1);
        
        gameTextureAtlas3 = new BitmapTextureAtlas(engine.getTextureManager(), 50, 50, TextureOptions.BILINEAR);
        
        gameMobTextureRegion3 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas3,
                                                                                            activity, "mob3.png", 0, 0,
                                                                                            1, 1);
        
        gameTextureAtlas4 = new BitmapTextureAtlas(engine.getTextureManager(), 60, 60, TextureOptions.BILINEAR);
        
        gameMobTextureRegion4 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas4,
                                                                                            activity, "mob4.png", 0, 0,
                                                                                            1, 1);
        
        gameTextureAtlas5 = new BitmapTextureAtlas(engine.getTextureManager(), 60, 60, TextureOptions.BILINEAR);
        
        gameMobTextureRegion5 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas5,
                                                                                            activity, "mob5.png", 0, 0,
                                                                                            1, 1);
        
        gameTextureAtlas6 = new BitmapTextureAtlas(engine.getTextureManager(), 60, 60, TextureOptions.BILINEAR);
        
        gameMobTextureRegion6 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas6,
                                                                                            activity, "mob6.png", 0, 0,
                                                                                            1, 1);
        
        gameTextureAtlas7 = new BitmapTextureAtlas(engine.getTextureManager(), 70, 70, TextureOptions.BILINEAR);
        
        gameMobTextureRegion7 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas7,
                                                                                            activity, "mob7.png", 0, 0,
                                                                                            1, 1);
        
        gameTextureAtlas1.load();
        gameTextureAtlas2.load();
        gameTextureAtlas3.load();
        gameTextureAtlas4.load();
        gameTextureAtlas5.load();
        gameTextureAtlas6.load();
        gameTextureAtlas7.load();
    }
    
    public void unloadGameTextures()
    {
        gameTextureAtlas1.unload();
        gameTextureAtlas2.unload();
        gameTextureAtlas3.unload();
        gameTextureAtlas4.unload();
        gameTextureAtlas5.unload();
        gameTextureAtlas6.unload();
        gameTextureAtlas7.unload();
    }
    
    // ---------------------------------------------
    // SPLASH RESOURCES
    // ---------------------------------------------
    
    public void loadSplashScreen()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        
        splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 550, 330, TextureOptions.BILINEAR);
        
        splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity,
                                                                               "splash.png", 0, 0);
        
        splashTextureAtlas.load();
    }
    
    public void unloadSplashScreen()
    {
        splashTextureAtlas.unload();
        splash_region = null;
    }
    
    // ---------------------------------------------
    // Manual RESOURCES
    // ---------------------------------------------
    
    public void loadManualResources()
    {
        loadManualGraphics();
    }
    
    public void unloadManualTextures()
    {
        manualTextureAtlas.unload();
    }
    
    public void loadManualTextures()
    {
        loadManualGraphics();
    }
    
    private void loadManualGraphics()
    {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/manual/");
        
        // --------------------------------------------- ATLAS
        
        manualTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048,
                TextureOptions.BILINEAR);
        
        manual1_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(manualTextureAtlas, activity,
                                                                                "manual (1).PNG");
        
        manual2_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(manualTextureAtlas, activity,
                                                                                "manual (2).PNG");
        
        manual3_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(manualTextureAtlas, activity,
                                                                                "manual (3).PNG");
        
        manual4_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(manualTextureAtlas, activity,
                                                                                "manual (4).PNG");
        
        manual5_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(manualTextureAtlas, activity,
                                                                                "manual (5).PNG");
        
        manual6_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(manualTextureAtlas, activity,
                                                                                "manual (6).PNG");
        
        manual7_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(manualTextureAtlas, activity,
                                                                                "manual (7).PNG");
        
        try
        {
            this.manualTextureAtlas
                    .build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            this.manualTextureAtlas.load();
        }
        catch (final TextureAtlasBuilderException e)
        {
            Debug.e(e);
        }
    }
    
    // ---------------------------------------------
    // GETTERS AND SETTERS
    // ---------------------------------------------
    
    public static ResourcesManager getInstance()
    {
        return INSTANCE;
    }
    
}