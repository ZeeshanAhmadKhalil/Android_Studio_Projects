package com.example.mk141.defenderblaster;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor
{
    private SpriteBatch batch;
    private BitmapFont font,input_teller_font;
    private int screen_width,screen_height;
    private String messege="Touch me";
    private Texture title;
    private Sprite title_graphic;
    private TextureAtlas shootinggirl_atlas,ironan_atlas;
    private Animation<TextureAtlas.AtlasRegion> shootinggirl_animation,ironman_animation;
    private float time_passed;
    private Sound gun_fire,reload,ironman_flying;
    private Music action_music;
    private long gun_fire_id,reload_id,ironman_flying_id;

	@Override
	public void create ()//called when app is started
	{
        batch=new SpriteBatch();
        font=new BitmapFont();
        font.setColor(Color.BLUE);
        shootinggirl_atlas=new TextureAtlas(Gdx.files.internal("shootinggirl.atlas"));
        ironan_atlas=new TextureAtlas(Gdx.files.internal("ironman.atlas"));
        shootinggirl_animation=new Animation<TextureAtlas.AtlasRegion>(1/6f,shootinggirl_atlas.getRegions());
        ironman_animation=new Animation<TextureAtlas.AtlasRegion>(1/3f,ironan_atlas.getRegions());
        screen_width=Gdx.graphics.getWidth();
        screen_height=Gdx.graphics.getHeight();
        input_teller_font=new BitmapFont();
        input_teller_font.setColor(Color.BLACK);
        if(screen_width>1100)
        {
            title=new Texture("title.png");
            font.getData().setScale(8);//increases font size 8 times
            input_teller_font.getData().setScale(3);
        }
        else
        {
            title=new Texture("titlesmall.png");
            font.getData().setScale(4);//increases font size 8 times
            input_teller_font.getData().setScale(2);
        }
        title_graphic=new Sprite(title);
        Gdx.input.setInputProcessor(this);
        gun_fire=Gdx.audio.newSound(Gdx.files.internal("Sounds/gunfire.mp3"));
        reload=Gdx.audio.newSound(Gdx.files.internal("Sounds/reload.mp3"));
        ironman_flying=Gdx.audio.newSound(Gdx.files.internal("Sounds/ironmanflying.mp3"));
        action_music=Gdx.audio.newMusic(Gdx.files.internal("Sounds/actionmusic.mp3"));
        action_music.play();
        action_music.setLooping(true);
	}
	@Override
	public void render ()//called abt 80 times/s when game is running like a loop
    {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        GlyphLayout glyphLayout_input_teller_font=new GlyphLayout(input_teller_font,messege);
        GlyphLayout glyphLayout_font=new GlyphLayout(font,"Cowgirl VS Ironman");
        float input_teller_font_x=screen_width/2-glyphLayout_input_teller_font.width/2;
        float input_teller_font_y=screen_height/2+glyphLayout_input_teller_font.height/2-glyphLayout_font.height;
        batch.begin();
        input_teller_font.draw(batch,messege,input_teller_font_x,input_teller_font_y);
        title_graphic.setX(screen_width/2-title_graphic.getRegionWidth()/2);
        title_graphic.setY(screen_height-title_graphic.getRegionHeight());
        title_graphic.draw(batch,10);//alpha-->transparency
        font.draw(batch,"Cowgirl VS Ironman",screen_width/2-glyphLayout_font.width/2,screen_height-title_graphic.getHeight());
        time_passed+=Gdx.graphics.getDeltaTime();
        batch.draw(shootinggirl_animation.getKeyFrame(time_passed,true),0,0);
        batch.draw(ironman_animation.getKeyFrame(time_passed,true),screen_width-250,0);
        batch.end();

	}

    @Override
    public void dispose()//to free all the resources
    {
        batch.dispose();
        font.dispose();
        title.dispose();
        shootinggirl_atlas.dispose();
        ironan_atlas.dispose();
        input_teller_font.dispose();
        action_music.dispose();
        gun_fire.dispose();
        ironman_flying.dispose();
        reload.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        messege="Touched Down at "+screenX+","+screenY;
        if(screenX<=screen_width/2)
        {
            gun_fire_id=gun_fire.play();
            gun_fire.setVolume(gun_fire_id,0.8f);//volume ranges from 0 to 1
        }
        else
        {
            ironman_flying_id=ironman_flying.play();
            ironman_flying.setPitch(ironman_flying_id,0.5f);//speed(pitch) ranges from 0.5 to 2
            ironman_flying.setVolume(ironman_flying_id,0.8f);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if(screenX<=screen_width/2)
        {
            reload_id=reload.play();
            reload.setVolume(reload_id,0.8f);
        }
        messege="Touched up at "+screenX+","+screenY;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        messege="Dragging at "+screenX+","+screenY;
        return false;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
