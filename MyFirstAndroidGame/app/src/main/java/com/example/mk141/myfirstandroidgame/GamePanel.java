package com.example.mk141.myfirstandroidgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback//Game panel is the screen of the game
{
    public static final int WIDTH=850;
    public static final int HEIGHT=480;
    public static int MOVE_SPEED=-3;
    Bitmap heart_a;
    Bitmap heart_b;
    Bitmap heart_c;
    private int hearts=3;
    Bitmap coin_bonus;
    Bitmap my_panel;
    private int hero_coins;
//    private long bonus_start_time;
    private ArrayList<Bonus> my_coins;
    MediaPlayer mp;
    SoundPool sound_pool;
    int coin_sound_id;
    int bullet_sound_id;
    int explosion_sound_id;
    int heroexplosion_sound_id;
    private Background background;
    private Hero hero;
    private ArrayList<Bullet> bullet;
//    private long bullet_start_time;
    private ArrayList<Enemy> enemy;
//    private long enemy_start_time;
    private Random random=new Random();
    private ArrayList<Borderbottom> borderbottom;
    private ArrayList<Obstecle> obstecle;
//    private long obstecle_start_time;
    private boolean new_game_created;
    private long start_reset;
    private boolean reset;
    private boolean dissappear;
    private boolean started;
    private Explosion explosion;
    private int best;
    private MainThread thread;
    int level;
    float obtecle_level,coin_level,enemy_level,bullet_level;
    Bitmap arrow_up;
    Bitmap arrow_down;
    DisplayMetrics displayMetrics=new DisplayMetrics();
    public GamePanel(Context context)
    {
        super(context);
        mp=MediaPlayer.create(context,R.raw.arcademusicloop);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            AudioAttributes audioAttributes=new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
            sound_pool=new SoundPool.Builder().setMaxStreams(5).setAudioAttributes(audioAttributes).build();
        }
        else
        {
            sound_pool=new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }
        coin_sound_id=sound_pool.load(context,R.raw.pickedcoin,1);
        bullet_sound_id=sound_pool.load(context,R.raw.bulletsound,1);
        explosion_sound_id=sound_pool.load(context,R.raw.explosionsound,1);
        heroexplosion_sound_id=sound_pool.load(context,R.raw.heroexplosion,1);
        getHolder().addCallback(this);//getting holder to intercept events

        setFocusable(true);//setting focusable to handel events
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder)//called after structural changes and we will safely start loop here
    {
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        thread=new MainThread(getHolder(),this);
        background=new Background(BitmapFactory.decodeResource(getResources(),R.drawable.background));
        hero=new Hero(BitmapFactory.decodeResource(getResources(),R.drawable.hero),45,46,2);
        bullet=new ArrayList<>();
//        bullet_start_time=System.nanoTime();
        enemy=new ArrayList<>();
//        enemy_start_time=System.nanoTime();
        borderbottom=new ArrayList<>();
        obstecle=new ArrayList<>();
//        obstecle_start_time=System.nanoTime();
        my_coins=new ArrayList<>();
//        bonus_start_time=System.nanoTime();
        thread.set_running(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2)//called before surface is destroyed
    {
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder)//works with override on touch event and we will end loop here
    {
        boolean retry=true;
        int counter=0;
        while(retry&&counter<1000)
        {
            try
            {
                thread.set_running(false);
                thread.join();//currently running thread will go in waiting state until joined thread finishes its execution
                retry=false;
                thread=null;//if we reset the game then we
                // will start a brand new game
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            counter++;
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction()==MotionEvent.ACTION_DOWN)
        {
            if (!hero.getPlaying()&&new_game_created&&reset)
            {
                hero.setPlaying(true);
                if (event.getY()<(HEIGHT/2+60))
                hero.setUp(-1);
                else
                    hero.setUp(1);
            }
            if (hero.getPlaying())
            {
                if (!started)
                    started=true;
                reset=false;
                if (event.getY()<=displayMetrics.heightPixels/2+60)
                hero.setUp(MOVE_SPEED);
                else
                    hero.setUp(-MOVE_SPEED);
            }
            return true;
        }
        if (event.getAction()==MotionEvent.ACTION_MOVE)
        {
            if (hero.getPlaying())
            {
                if (!started)
                    started=true;
                reset=false;
                if (event.getY()<HEIGHT/2+60)
                    hero.setUp(MOVE_SPEED);
                else
                    hero.setUp(-MOVE_SPEED);
            }
            return true;
        }
        if (event.getAction()==MotionEvent.ACTION_UP)
        {
            hero.setUp(0);
            return true;
        }
        return super.onTouchEvent(event);
    }
    public void update()
    {
//        MOVE_SPEED=-1-2*hero.getScore()/1000;
        update_levels();
        if (hero.getPlaying())
        {
            mp.start();
            mp.setVolume(0.5f,0.5f);
            background.update();
            hero.update();
            if (hero.getY()>HEIGHT-hero.getHeight()||hero.getY()<0)
            {
                hearts=0;
            }
//            long buller_timer=(System.nanoTime()-bullet_start_time)/1000000;
//            if (buller_timer>(2500-hero.getScore()))
            if(bullet.isEmpty()||bullet.get(bullet.size()-1).getX()>WIDTH*bullet_level)
            {
                sound_pool.play(bullet_sound_id,1,1,0,0,1);
                bullet.add(new Bullet(BitmapFactory.decodeResource(getResources(),R.drawable.bullet),hero.getX()+60,hero.getY()+24,15,7,7));
//                bullet_start_time=System.nanoTime();
            }
            for(int i=0;i<bullet.size();i++)//to remove off screen bullets
            {
                bullet.get(i).update();
                if (bullet.get(i).getX()<-10)
                    bullet.remove(i);
            }
//            long enemy_timer=(System.nanoTime()-enemy_start_time)/1000000;
//            if (enemy_timer>(5000-hero.getScore()/4))
            if (enemy.isEmpty()||enemy.get(enemy.size()-1).getX()<WIDTH*enemy_level)
            {
                enemy.add(new Enemy(BitmapFactory.decodeResource(getResources(),R.drawable.enemy),WIDTH+10,(int)(random.nextDouble()*(HEIGHT-50)),40,60,hero.getScore(),3));
//                enemy_start_time=System.nanoTime();
            }
            for(int i=0;i<enemy.size();i++)//to remove off screen enemies
            {
                enemy.get(i).update();
                if (collision(enemy.get(i),hero))
                {
                    explosion=new Explosion(BitmapFactory.decodeResource(getResources(),R.drawable.explosion),enemy.get(i).getX(),enemy.get(i).getY(),100,100,15);
                    sound_pool.play(explosion_sound_id,1,1,0,0,1);
                    enemy.remove(i);
                    hearts--;

                    break;
                }
                if (enemy.get(i).getX()<-100)
                {
                    enemy.remove(i);
                    break;
                }
                for (int j=0;j<bullet.size();j++)
                {
                    if (collision(enemy.get(i),bullet.get(j)))
                    {
                        my_coins.add(new Bonus(BitmapFactory.decodeResource(getResources(),R.drawable.coin),enemy.get(i).getX(),enemy.get(i).getY(),20,20,1));
                        explosion=new Explosion(BitmapFactory.decodeResource(getResources(),R.drawable.explosion),enemy.get(i).getX(),enemy.get(i).getY(),100,100,15);
                        sound_pool.play(explosion_sound_id,1,1,0,0,1);
                        enemy.remove(i);
                        bullet.remove(j);
                        best++;
                        break;
                    }
                    bullet.get(j).update();
                }
            }
            if (borderbottom.isEmpty()||borderbottom.get(borderbottom.size()-1).getX()<WIDTH-15)
            {
                borderbottom.add(new Borderbottom(BitmapFactory.decodeResource(getResources(),R.drawable.borderbottom),WIDTH,HEIGHT-50+random.nextInt(10)));
                borderbottom.add(new Borderbottom(BitmapFactory.decodeResource(getResources(),R.drawable.bordertop),WIDTH,-100));
            }
            for (int i=0;i<borderbottom.size();i++)
            {
                borderbottom.get(i).update();
                if (collision(hero,borderbottom.get(i)))
                {
                    hearts=0;
                    break;
                }
                for (int j=0;j<enemy.size();j++)
                {
                    if (collision(enemy.get(j),borderbottom.get(i))) {
                        enemy.remove(j);
                    }
                }
                if (borderbottom.get(i).getX()<-15) {
                    borderbottom.remove(i);
                }
            }
//            long bonus_timer=(System.nanoTime()-bonus_start_time)/1000000;
//            if(bonus_timer>(3000-hero.getScore()/4))
            if (my_coins.isEmpty()||my_coins.get(my_coins.size()-1).getX()<WIDTH*coin_level)
            {
                my_coins.add(new Bonus(BitmapFactory.decodeResource(getResources(),R.drawable.coin),WIDTH+1,(int)(random.nextDouble()*(HEIGHT-200)),20,20,1));
//                bonus_start_time=System.nanoTime();
            }
            for (int i=0;i<my_coins.size();i++)
            {
                my_coins.get(i).update();
                if (collision(hero,my_coins.get(i)))
                {
                    sound_pool.play(coin_sound_id,1,1,0,0,1);

                    my_coins.remove(i);
                    hero_coins++;
                    break;
                }
                for (int j=0;j<borderbottom.size();j++)
                {
                    if(collision(borderbottom.get(j),my_coins.get(i)))
                    {
                        my_coins.remove(i);
                        break;
                    }
                }
                if (my_coins.get(i).getX()<-50)
                {
                    my_coins.remove(i);
                    break;
                }
            }
//            long obstecle_timer=(System.nanoTime()-obstecle_start_time)/1000000;
            boolean obstecle_boolean=random.nextBoolean();
//            if(obstecle_timer>(7500-hero.getScore()/4))
            if(obstecle.isEmpty()||obstecle.get(obstecle.size()-1).getX()<WIDTH*obtecle_level)
            {
                if (obstecle_boolean)
                {
                    obstecle.add(new Obstecle(BitmapFactory.decodeResource(getResources(), R.drawable.obstacle),WIDTH+10,HEIGHT-290+random.nextInt(150),90,300,hero.getScore(),1));
                }
                else
                {
                    obstecle.add(new Obstecle(BitmapFactory.decodeResource(getResources(), R.drawable.obstacleup),WIDTH+10,-random.nextInt(150),90,300,hero.getScore(),1));
                }
//                obstecle_start_time=System.nanoTime();
            }
            for (int i=0;i<obstecle.size();i++)
            {
                obstecle.get(i).update();
                if(collision(hero,obstecle.get(i)))
                {
                    hearts=0;
                    break;
                }
                if (obstecle.get(i).getX()<-80) {
                    obstecle.remove(i);
                }
            }
        }
        else
        {
            hero.reset_dya();
            if (!reset)
            {
                new_game_created=false;
                start_reset=System.nanoTime();
                reset=true;
                dissappear=true;
                explosion=new Explosion(BitmapFactory.decodeResource(getResources(),R.drawable.explosion),hero.getX(),hero.getY(),100,100,15);
                sound_pool.play(heroexplosion_sound_id,1,1,0,0,1);
            }
            long reset_timer=(System.nanoTime()-start_reset)/1000000;
            if (reset_timer>2500)
            {
//                mp.reset();
                new_game();
            }
        }
        explosion.update();

    }
    public boolean collision(Game_Object a,Game_Object b)
    {
        return Rect.intersects(a.getRextangle(),b.getRextangle());
    }
    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        final float scale_factor_x = getWidth() / (float)WIDTH;//for responsive background
        final float scale_factor_y = getHeight() / (float)HEIGHT;
        if (canvas != null)
        {
            final int saved_state = canvas.save();
            canvas.scale(scale_factor_x, scale_factor_y);
            background.draw(canvas);
            if (!dissappear)
            hero.draw(canvas);
            for (Bullet fp:bullet)
                fp.draw(canvas);
            for (Enemy en:enemy)
                en.draw(canvas);
            for (Borderbottom bb:borderbottom)
                bb.draw(canvas);
            for (Obstecle ob:obstecle)
                ob.draw(canvas);
            for (Bonus bo:my_coins)
                bo.draw(canvas);
            if(started)
                explosion.draw(canvas);
            draw_text(canvas);

            canvas.restoreToCount(saved_state);
        }                                               //for responsive background
    }
    private void new_game()
    {
        dissappear=false;
        enemy.clear();
        obstecle.clear();
        borderbottom.clear();
        bullet.clear();
        my_coins.clear();
        hero.reset_dya();
        hero.reset_score();
        best=0;
        hero_coins=0;
        hero.setY(HEIGHT/3);
        hearts=3;
        new_game_created=true;
    }
    public void draw_text(Canvas canvas)
    {
        Paint paint=new Paint();
        paint.setTextSize(30);
        paint.setColor(Color.YELLOW);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        canvas.drawText("Distance:"+(hero.getScore()*2),10,HEIGHT-10,paint);
        canvas.drawText("Kills:"+(best),WIDTH-160,HEIGHT-10,paint);
        canvas.drawText("Level:"+(level),10,30,paint);
        coin_bonus=BitmapFactory.decodeResource(getResources(),R.drawable.coin);
        canvas.drawBitmap(coin_bonus,WIDTH-130,10,null);
        canvas.drawText(" "+hero_coins,WIDTH-90,25+5,paint);
        heart_a=BitmapFactory.decodeResource(getResources(),R.drawable.lifea);
        heart_b=BitmapFactory.decodeResource(getResources(),R.drawable.lifeb);
        heart_c=BitmapFactory.decodeResource(getResources(),R.drawable.lifec);
        if (hero.getUp()<0)
            arrow_up=BitmapFactory.decodeResource(getResources(),R.drawable.upp);
        else
            arrow_up=BitmapFactory.decodeResource(getResources(),R.drawable.up);
        if (hero.getUp()>0)
            arrow_down=BitmapFactory.decodeResource(getResources(),R.drawable.downn);
        else
            arrow_down=BitmapFactory.decodeResource(getResources(),R.drawable.down);
        if(hearts==3)
        {
            canvas.drawBitmap(heart_a,WIDTH/2-120,0,null);
            canvas.drawBitmap(heart_b,WIDTH/2-80,0,null);
            canvas.drawBitmap(heart_c,WIDTH/2-40,0,null);
        }
        if(hearts==2)
        {
            canvas.drawBitmap(heart_a,WIDTH/2-120,0,null);
            canvas.drawBitmap(heart_b,WIDTH/2-80,0,null);
        }
        if(hearts==1)
        {
            canvas.drawBitmap(heart_a,WIDTH/2-120,0,null);
        }
        if(hearts==0)
        {
            hero.setPlaying(false);
            mp.pause();
            mp.seekTo(0);
        }
        canvas.drawBitmap(arrow_up,0,HEIGHT/2-arrow_up.getHeight(),null);
        canvas.drawBitmap(arrow_down,-5,HEIGHT/2,null);
        if (!hero.getPlaying()&&new_game_created&&reset)
        {
            Rect start_bounds=new Rect();
            Rect up_bounds=new Rect();
            Rect down_bounds=new Rect();
            Paint paint1=new Paint();
            String start="Press to Start";
            String up="Move Up and Down";
            String down="Don't hit obstacles and aliens";
            paint1.getTextBounds(start,0,start.length(),start_bounds);
            paint1.getTextBounds(up,0,up.length(),up_bounds);
            paint1.getTextBounds(down,0,down.length(),down_bounds);
            paint1.setTextSize(25);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            my_panel=BitmapFactory.decodeResource(getResources(),R.drawable.panel);
            canvas.drawBitmap(my_panel,WIDTH/2-my_panel.getWidth()/2,HEIGHT/2-my_panel.getHeight()/2,null);
            canvas.drawText(start,WIDTH/2-start_bounds.width(),HEIGHT/2-start_bounds.height()-4*(up_bounds.height()),paint1);
            canvas.drawText(up,WIDTH/2-up_bounds.width(),HEIGHT/2-up_bounds.height(),paint1);
            canvas.drawText(down,WIDTH/2-down_bounds.width(),HEIGHT/2-down_bounds.height()+4*(up_bounds.height()),paint1);
        }
    }
    public void update_levels()
    {
        int levels_gap=2;
        if(2*hero.getScore()<=100*levels_gap)
        {
            level=1;
            obtecle_level=0;
            bullet_level=1;
            enemy_level=0;
            coin_level=0;
        }
        else if (2*hero.getScore()>100*levels_gap&&2*hero.getScore()<=200*levels_gap)
        {
            level=2;
        }
        else if (2*hero.getScore()>200*levels_gap&&2*hero.getScore()<=300*levels_gap)
        {
            level=3;
            bullet_level=0.9f;
            coin_level=0.2f;
        }
        else if (2*hero.getScore()>300*levels_gap&&2*hero.getScore()<=400*levels_gap)
        {
            level=4;
            enemy_level=0.25f;
        }
        else if (2*hero.getScore()>400*levels_gap&&2*hero.getScore()<=500*levels_gap)
        {
            level=5;
            obtecle_level=0.5f;
            bullet_level=0.8f;
            coin_level=0.4f;
        }
        else if (2*hero.getScore()>500*levels_gap&&2*hero.getScore()<=600*levels_gap)
        {
            level=6;
            bullet_level=0.7f;
        }
        else if (2*hero.getScore()>600*levels_gap&&2*hero.getScore()<=700*levels_gap)
        {
            level=7;
            enemy_level=0.5f;
            coin_level=0.6f;
        }
        else if (2*hero.getScore()>700*levels_gap&&2*hero.getScore()<=800*levels_gap)
        {
            level=8;
            bullet_level=0.6f;
        }
        else if (2*hero.getScore()>800*levels_gap&&2*hero.getScore()<=900*levels_gap)
        {
            level=9;
            obtecle_level=0.66f;
            bullet_level=0.5f;
            coin_level=0.8f;
        }
        else if (2*hero.getScore()>900*levels_gap&&2*hero.getScore()<=1000*levels_gap)
        {
            level=10;
            bullet_level=0.4f;
            enemy_level=0.75f;
        }
    }
}
