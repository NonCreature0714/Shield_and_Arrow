package com.intelbeast.shieldarrow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by Bill on 11/23/15.
 */


public class ShieldAndArrowView extends SurfaceView implements SurfaceHolder.Callback {

    private final ShieldAndArrowActivity gameActivity;
    private ShieldAndArrowThread _thread;
    private boolean previouslyRunning = false;
    private int width;  //Screen width
    private int height; //Screen height

    private int gameState;

    //Game states:
    private boolean GAME_PAUSED = false;
    private int HLEKKUR_VICTORY = 11;
    private int HLEKKUR_DEFEAT = 12;
    private int SKRIMSLI_VICTORY = 21;
    private int SKRIMSLI_DEFEAT = 22;
    private int NORN_VICTORY = 31;
    private int NORN_DEFEAT = 32;
    private int BEINAGRIND_VICTORY = 41;


    private boolean newGame = true;

    public ShieldAndArrowView(Context context) {
        super(context);
        getHolder().addCallback(this);
        this.gameActivity = (ShieldAndArrowActivity) context;
        _thread = new ShieldAndArrowThread(getHolder(), this);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;

        super.onSizeChanged(w, h, oldw, oldh);

        //_thread.initialize();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!previouslyRunning) {
            _thread = new ShieldAndArrowThread(getHolder(), this);
            _thread.initialize();
        }

        _thread.setRunning(true);
        _thread.start();
        previouslyRunning = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //TODO - this was an Auto-generated method stub...
        //TODO - research what this might be useful for
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        //Put stuff that needs destructed here.

        boolean retry = true;
        _thread.setRunning(false);
        while (retry) {
            try {
                _thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // will will try again and again
                //TODO: what?
            }
        }

    }

    public boolean onTouchEvent(MotionEvent event) {

        int numPointers = event.getPointerCount();
        int ptrIdx = 0;

        boolean hlekkurMovedLeft = false;
        boolean hlekkurMovedRight = false;
        boolean hlekkurAttacks = false;

        int touch = event.getActionMasked();

        if (touch == MotionEvent.ACTION_DOWN) {

            while (ptrIdx < numPointers) {
                int id = event.getPointerId(ptrIdx);
                float xp = event.getX(ptrIdx) / width;

                if (xp > 0.6) {
                    _thread.hlekkurShieldFront = false;
                }

                if (xp > 0.6 && !hlekkurAttacks) {
                    hlekkurAttacks = true;
                    _thread.attackandDefendToggle(true);
                } else if (xp > 0.6 && hlekkurAttacks) {
                    hlekkurAttacks = false;
                    _thread.attackandDefendToggle(false);
                } else if ((xp < 0.4 && xp > 0.2) && !hlekkurMovedRight) {
                    hlekkurMovedRight = true;
                    _thread.moveHlekkurRight(true);
                } else if ((xp < 0.4 && xp > 0.2) && hlekkurMovedRight) {
                    hlekkurMovedRight = false;
                    _thread.moveHlekkurRight(false);
                } else if (xp < 0.2 && !hlekkurMovedLeft) {
                    hlekkurMovedLeft = true;
                    _thread.moveHlekkurLeft(true);
                } else if (xp < 0.2 && hlekkurMovedLeft) {
                    hlekkurMovedLeft = false;
                    _thread.moveHlekkurLeft(false);
                }
                ptrIdx++;
            }
        }

        if (touch == MotionEvent.ACTION_UP) {
            _thread.moveHlekkurLeft(false);
            _thread.moveHlekkurRight(false);
            _thread.attackandDefendToggle(false);
            hlekkurAttacks = false;
            _thread.hlekkurShieldFront = true;
        }


        //TODO: create destructor

        //TODO: create override functions
        //TODO: touch detection
        //TODO: onPause

        return true;
    }

    class ShieldAndArrowThread extends Thread {

        //Heroes and enemies
        Hlekkur hlekkur;
        Beinagrind[] beinagrind = new Beinagrind[8];
        Skrimsli skrimsli;
        Platform platform;
        Gate gate;
        int atkID = 0;
        int atkGoing = 1;
        private boolean battle = true;
        private boolean hlekkurWon = false;
        private Paint background = new Paint();
        private Paint light = new Paint();
        private Paint greenish = new Paint();
        private Paint white = new Paint();
        private Paint pink = new Paint();
        private Paint purple = new Paint();
        private Paint popBlue = new Paint();
        private Paint red = new Paint();
        private Paint orange = new Paint();
        private Paint yellow = new Paint();
        private Paint skyBlue = new Paint();
        private Paint leafGreen = new Paint();
        private Paint mountainBlack = new Paint();
        private Rect trectHlekkur = new Rect();

        // All ShieldAndArrow variables go below here.
        private Rect trectSkrimsli = new Rect();
        private Rect trectNorn = new Rect();
        private SurfaceHolder _surfaceHolder;
        private ShieldAndArrowView _panel;
        private int hlekkurX = 110;
        private int hlekkurProjX = 440;
        private float skrimsliProjX = 730;
        private boolean _run = false;
        private int timesRight = 0;
        private int timesLeft = 0;
        private int timesAttack = 0;
        private boolean hlekkurMoveLeft = false;
        private boolean hlekkurMoveRight = false;
        private int initialHlekkurArrowX = -30;
        private boolean hlekkurClimb = false;
        private boolean hlekkurAttack = false;
        private boolean hlekkurShootArrow = false;
        private boolean hlekkurJustFiredArrow = false;
        private boolean hlekkurShieldFront = true;
        private boolean skrimsliShootArrow = false;

        //Dead or Alive States
        private boolean skrimsliAlive = true;
        private boolean hlekkurAlive = true;
        private int HLEKKUR_FULL_LIFE = 3;
        private int SKRIMSLI_FULL_LIFE = 8;
        private int hlekkurLife = HLEKKUR_FULL_LIFE;
        private int skrimsliLife = SKRIMSLI_FULL_LIFE;
        private boolean skrimsliHarmed = false;
        private boolean hlekkurHarmed = false;

        //Win or lose states
        private boolean hlekkurWins = false;
        private boolean skrimsliWins = false;
        private float xArrowSkrimsli;
        private float xMagicNorn;
        private int posBeinagrind[] = {0, 0, 0, 0, 0, 0, 0, 0}; // for positions of Beinagrind.
        private float x;
        private float y;

        //Sounds
        private SoundPool soundPool;
        private int clickAttackButton = -1;
        private int clickMoveButton = -1;
        private int hlekkurMovesSound = -1;
        private int hlekkurAttackSound = -1;
        private int hlekkrStrikeSound = -1;
        private int hlekkurArrowSound = -1;
        private int hlekkurDefendsSound = -1;
        private int hlekkurDamagedSound = -1;
        private int hlekkurDiesSounds = -1;
        private int hlekkurVictorySound = -1;
        private int skrimsliAttackSound = -1;
        private int skrimsliArrowSound = -1;
        private int skrimsliStrikeSound = -1;
        private int skrimsliBlockSound = -1;
        private int skrimsliDamagedSound = -1;
        private int skrimsliDiesSounds = -1;
        private int skrimsliLaughSound = -1;

        //TODO: Norn sounds
        private int beinagrindMoveSound = -1;
        private int beinagrindAttackSound = -1;
        private double mLastTime;

        private double runningTimer;

        private double beinagrindTime;

        private double beinagrind5AttackTimer;

        private boolean skrimsliFired = false;

        private int numBeinagrind;

        private boolean beinagrind5Appear = false;

        private int beinagrind5AttackSwitch = 0;

        private boolean beinagrind5Attacks = false;


        //For beinagrind 8
        private double beinagrind8AttackTimer;

        private boolean beinagrind8Appear = false;

        private int beinagrind8AttackSwitch = 0;

        private boolean beinagrind8Attacks = false;

        /****************************
         * Public functions       *
         ****************************/

        public ShieldAndArrowThread(SurfaceHolder surfaceHolder, ShieldAndArrowView panel) {
            _surfaceHolder = surfaceHolder;
            _panel = panel;

            // put sounds here.
            soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
            //more sounds later
            //TODO: create sounds

            for (int i = 0; i < 8; i++) {
                beinagrind[i] = new Beinagrind();
            }
        }

        /************************************************
         *  update() function updates all variables,    *
         *  such as physics, Canvas draw points, score  *
         *  life, etc.. It is called before draw.       *
         ************************************************/
        private void update() {
            if (battle) {
                long now = System.currentTimeMillis();


                if (mLastTime > now) {
                    return;
                }
                double elapsed = ((now - mLastTime) / 1000.0);
                double mElapsedTime = (elapsed * 0.00000000001);
                double mLastTime = now;
                runningTimer += mElapsedTime;
                beinagrindTime += mElapsedTime;
                beinagrind5AttackTimer += mElapsedTime;
                beinagrind8AttackTimer += mElapsedTime;


                // update hlekkur movements based on touch
                if (hlekkurMoveRight && hlekkurX != 810) {
                    while (timesRight == 0) {
                        hlekkurX += 100;
                        timesRight += 1;
                    }
                } else {
                    timesRight = 0;
                }


                if (hlekkurMoveLeft && hlekkurX != 110) {
                    while (timesLeft == 0) {
                        hlekkurX -= 100;
                        timesLeft += 1;
                    }
                } else {
                    timesLeft = 0;
                }

                //TODO: update hlekkur arrows if fired
                if (hlekkurAttack && !hlekkurShootArrow) {
                    hlekkurProjX = hlekkurX + 30;
                    hlekkurShootArrow = true;
                }

                if (hlekkurAttack && hlekkurShootArrow) {
                    hlekkurProjX += 15;
                }

                if (hlekkurProjX > 910) {
                    hlekkurShootArrow = false;
                    hlekkurAttack = false;
                    skrimsliHarmed = true;
                    hlekkurProjX = -40;
                }

                if (skrimsliHarmed) {
                    skrimsliLife -= 1;
                    skrimsliHarmed = false;
                }

                if (skrimsliLife == 0) {
                    skrimsliAlive = false;
                    hlekkurWon = true;
                    //battle = false; // commented out for now to test animations
                }

                if (skrimsliAlive && runningTimer > 1) {
                    skrimsliShootArrow = true;
                    runningTimer = 0;
                }

                if (skrimsliShootArrow) {
                    skrimsliShootArrow = false;
                    skrimsliFired = true;
                    skrimsliProjX = 910;
                }

                if (skrimsliFired && skrimsliProjX > hlekkurX + 42) {
                    skrimsliProjX -= 15;
                }

                if (skrimsliFired && skrimsliProjX < hlekkurX + 40 && hlekkurShieldFront) {
                    skrimsliFired = false;
                    skrimsliProjX = 130;
                }

                if (skrimsliFired && skrimsliProjX < hlekkurX + 40 && !hlekkurShieldFront) {
                    hlekkurHarmed = true;
                    skrimsliFired = false;
                    skrimsliProjX = 130;
                }

                if (hlekkurHarmed) {
                    hlekkurHarmed = false;
                    hlekkurLife -= 1;
                }

                if (hlekkurLife == 0) {
                    hlekkurAlive = false;
                    hlekkurWon = false;
                    //battle = false; //commented out for now to test animations
                }

                if (beinagrindTime < 4) {
                    beinagrind5AttackTimer = 0;
                } else if (beinagrindTime > 4) {
                    beinagrind5Appear = true;
                    beinagrind5AttackTimer += mElapsedTime;
                }

                if (beinagrind5Appear) {
                    if (beinagrind5AttackTimer < 0.5) {
                        beinagrind5AttackSwitch = 0;
                    }
                    if (beinagrind5AttackTimer > 0.5 && beinagrind5AttackTimer < 1) {
                        beinagrind5AttackSwitch = 1;
                    }
                    if (beinagrind5AttackTimer > 1 && beinagrind5AttackTimer < 1.5) {
                        beinagrind5AttackSwitch = 2;
                    }
                    if (beinagrind5AttackTimer > 1.5 && beinagrind5AttackTimer < 2) {
                        beinagrind5AttackSwitch = 3;
                        beinagrind5Attacks = !hlekkurHarmed;
                    }
                    if (beinagrind5AttackTimer > 2 && beinagrind5AttackTimer < 2.5) {
                        beinagrind5AttackSwitch = 2;
                        beinagrind5Attacks = false;
                    }
                    if (beinagrind5AttackTimer > 2.5 && beinagrind5AttackTimer < 3) {
                        beinagrind5AttackSwitch = 1;
                    }
                    if (beinagrind5AttackTimer > 3 && beinagrind5AttackTimer < 3.5) {
                        beinagrind5AttackSwitch = 0;
                    }
                    if (beinagrind5AttackTimer > 3.5) {
                        beinagrind5AttackTimer = 0;
                    }
                }
                if (beinagrind5Attacks && hlekkurX == 510) {
                    hlekkurHarmed = true;
                }


                //For beinagrind 8
                if (beinagrindTime < 10) {
                    beinagrind8AttackTimer = 0;
                } else if (beinagrindTime > 10) {
                    beinagrind8Appear = true;
                    beinagrind8AttackTimer += mElapsedTime;
                }


                if (beinagrind8Appear) {
                    if (beinagrind8AttackTimer < 0.5) {
                        beinagrind8AttackSwitch = 0;
                    }
                    if (beinagrind8AttackTimer > 0.5 && beinagrind8AttackTimer < 1) {
                        beinagrind8AttackSwitch = 1;
                    }
                    if (beinagrind8AttackTimer > 1 && beinagrind8AttackTimer < 1.5) {
                        beinagrind8AttackSwitch = 2;
                    }
                    if (beinagrind8AttackTimer > 1.5 && beinagrind8AttackTimer < 2) {
                        beinagrind8AttackSwitch = 3;
                        beinagrind8Attacks = !hlekkurHarmed;
                    }
                    if (beinagrind8AttackTimer > 2 && beinagrind8AttackTimer < 2.5) {
                        beinagrind8AttackSwitch = 2;
                        beinagrind8Attacks = false;
                    }
                    if (beinagrind8AttackTimer > 2.5 && beinagrind8AttackTimer < 3) {
                        beinagrind8AttackSwitch = 1;
                    }
                    if (beinagrind8AttackTimer > 3 && beinagrind8AttackTimer < 3.5) {
                        beinagrind8AttackSwitch = 0;
                    }
                    if (beinagrind8AttackTimer > 3.5) {
                        beinagrind8AttackTimer = 0;
                    }
                }
                if (beinagrind8Attacks && hlekkurX == 810) {
                    hlekkurHarmed = true;
                }

                //TODO: Update collision edges of Hlekkur

                //TODO: Update collision edges of Skrimsli

                //TODO: Update collision edges of Norn

                //TODO: Update collision edges of Beinagrind(s)

                //TODO: Shield detection Hlekkur

                //TODO: Collision detection Hlekkur

                //TODO: Collision detection Skrimsli


                //TODO: Blunt force damage detection Hlekkur

                //TODO: Blunt force damage detection Skrimsli

                //TODO: Blunt force damage detection Norn


                //TODO: Update Hlekkur health

                //TODO: Update Skrimsli health

                //TODO: Update Norn health


                //TODO: Detect Hlekkur Victory/Defeat

                //TODO: Detect Skrimsli Victory/Defeat

                //TODO: Detect Norn Victory/Defeat


            } else if (!battle && hlekkurWon) {
                battle = true;
            } else if (!battle && !hlekkurWon) {
                battle = true;
            }
        
        
        }

        /************************************************
         * draw() function creates images on screen,   *
         * but it performs no game logic.              *
         ************************************************/
        private void draw(Canvas canvas) {

            if (canvas == null) {
                return;
            }
            if (battle) {

                // Create background.
                canvas.drawRect(0, 0, getWidth(), getHeight(), mountainBlack);
                //canvas.drawRect(0.5f, 0.5f, 0.5f, 0.5f, greenish);

                //Colors
                light.setColor(getResources().getColor(R.color.popBlue));  // should really get this from colors.xml
                greenish.setColor(getResources().getColor(R.color.warmgreen));
                white.setColor(getResources().getColor(R.color.white));
                pink.setColor(getResources().getColor(R.color.pink));
                purple.setColor(getResources().getColor(R.color.purple));
                leafGreen.setColor(getResources().getColor(R.color.leafGreen));
                red.setColor(getResources().getColor(R.color.bloodRed));
                orange.setColor(getResources().getColor(R.color.orange));
                yellow.setColor(getResources().getColor(R.color.yellow));


                skyBlue.setColor(getResources().getColor(R.color.skyBlue));


                //TODO: draw 8 separat lines on the same line for Hlekkur
                white.setStrokeWidth(10);
                greenish.setStrokeWidth(10);
                leafGreen.setStrokeWidth(10);
                purple.setStrokeWidth(10);
                canvas.drawLine(100 * width / 1000, 600 * height / 1000, 190 * width / 1000, 600 * height / 1000, greenish);
                canvas.drawLine(200 * width / 1000, 600 * height / 1000, 290 * width / 1000, 600 * height / 1000, leafGreen);
                canvas.drawLine(300 * width / 1000, 600 * height / 1000, 390 * width / 1000, 600 * height / 1000, greenish);
                canvas.drawLine(400 * width / 1000, 600 * height / 1000, 490 * width / 1000, 600 * height / 1000, leafGreen);
                canvas.drawLine(500 * width / 1000, 600 * height / 1000, 590 * width / 1000, 600 * height / 1000, greenish);
                canvas.drawLine(600 * width / 1000, 600 * height / 1000, 690 * width / 1000, 600 * height / 1000, leafGreen);
                canvas.drawLine(700 * width / 1000, 600 * height / 1000, 790 * width / 1000, 600 * height / 1000, greenish);
                canvas.drawLine(800 * width / 1000, 600 * height / 1000, 890 * width / 1000, 600 * height / 1000, leafGreen);

                //2 platforms for Norn and Skrimsli
                pink.setStrokeWidth(10);
                canvas.drawLine(10 * width / 1000, 610 * height / 1000, 90 * width / 1000, 610 * height / 1000, purple);
                canvas.drawLine(10 * width / 1000, 650 * height / 1000, 50 * width / 1000, 610 * height / 1000, purple);
                canvas.drawLine(900 * width / 1000, 610 * height / 1000, 990 * width / 1000, 610 * height / 1000, purple);
                canvas.drawLine(950 * width / 1000, 610 * height / 1000, 990 * width / 1000, 650 * height / 1000, purple);


                red.setStrokeWidth(10);

                // Draw Hlekkur shapes
                //Hlekkur
                if (hlekkurAlive) {
                    canvas.drawRect((hlekkurX) * width / 1000, 460 * height / 1000, (hlekkurX + 70) * width / 1000, 585 * height / 1000, skyBlue);

                    // Hlekkur projectile
                    if (hlekkurShootArrow) {
                        canvas.drawRect((hlekkurProjX) * width / 1000, 480 * height / 1000, (hlekkurProjX + 30) * width / 1000, 510 * height / 1000, white);
                    }

                    if (hlekkurShieldFront) {
                        canvas.drawLine((hlekkurX + 75) * width / 1000, 470 * height / 1000, (hlekkurX + 75) * width / 1000, 575 * height / 1000, red);
                    } else {
                        canvas.drawLine((hlekkurX - 5) * width / 1000, 470 * height / 1000, (hlekkurX - 5) * width / 1000, 575 * height / 1000, red);
                    }

                }

                // Hlekkur life
                int hlekkurFirstLife = 400;
                for (int i = 0; i < hlekkurLife; i++) {
                    canvas.drawRect(hlekkurFirstLife * width / 1000, 20 * height / 1000, (hlekkurFirstLife + 20) * width / 1000, 50 * height / 1000, red);
                    hlekkurFirstLife += 25;
                }


                // TODO: Hlekkur sword

                // TODO: Klekkur shield


                //Draw Skrimsli shapes
                if (skrimsliAlive) {
                    if (!skrimsliHarmed) {
                        canvas.drawRect(910 * width / 1000, 400 * height / 1000, 980 * width / 1000, 595 * height / 1000, orange);
                    } else {
                        canvas.drawRect(910 * width / 1000, 400 * height / 1000, 980 * width / 1000, 595 * height / 1000, yellow);
                    }

                }

                // skrimsli projectile
                if (skrimsliFired) {
                    canvas.drawRect((skrimsliProjX) * width / 1000, 480 * height / 1000, (skrimsliProjX + 30) * width / 1000, 510 * height / 1000, yellow);
                }


                if (beinagrind5Appear) {
                    switch (beinagrind5AttackSwitch) {
                        case 0:
                            canvas.drawRect(510 * width / 1000, 610 * height / 1000, 580 * width / 1000, 980 * height / 1000, white);
                            break;
                        case 1:
                            canvas.drawRect(510 * width / 1000, 610 * height / 1000, 580 * width / 1000, 980 * height / 1000, yellow);
                            break;
                        case 2:
                            canvas.drawRect(510 * width / 1000, 610 * height / 1000, 580 * width / 1000, 980 * height / 1000, orange);
                            break;
                        case 3:
                            canvas.drawRect(510 * width / 1000, 610 * height / 1000, 580 * width / 1000, 980 * height / 1000, red);
                    }

                }

                if (beinagrind8Appear) {
                    switch (beinagrind8AttackSwitch) {
                        case 0:
                            canvas.drawRect(810 * width / 1000, 610 * height / 1000, 880 * width / 1000, 980 * height / 1000, white);
                            break;
                        case 1:
                            canvas.drawRect(810 * width / 1000, 610 * height / 1000, 880 * width / 1000, 980 * height / 1000, yellow);
                            break;
                        case 2:
                            canvas.drawRect(810 * width / 1000, 610 * height / 1000, 880 * width / 1000, 980 * height / 1000, orange);
                            break;
                        case 3:
                            canvas.drawRect(810 * width / 1000, 610 * height / 1000, 880 * width / 1000, 980 * height / 1000, red);
                    }

                }
                //Norn
                //canvas.drawRect(20 * width / 1000, 410 * height / 1000, 80 * width / 1000, 575 * height / 1000, pink);

                //TODO: draw placeholder for Norn projectile

                //TODO: draw 8 placeholders for beinagrind


                //TODO: font

                //TODO: Detect gameSate;


                //TODO: Upate Skrimsli dimensions

                //TODO: Update Norn dimensions

                //TODO: Update Beinagrind Positions

                //TODO: draw Hlekkur

                //TODO: draw Norn

                //TODO: draw Beinagrind


            } else if (!battle && hlekkurWon) {

            } else if (!battle && !hlekkurWon) {

            }
            /************************************
             *------ END OF DRAW FUNCTION-------*
             ************************************/
        }

        public void initialize() {
            hlekkurX = 310;
            beinagrind[3].setPositionBeinagrind(3);
        }

        public void setRunning(boolean run) {
            _run = run;
        }


        /*********************************
         * setxHlekkur sets the         *
         * position of Hlekkur onscreen *
         * for the draw/update fucntions*
         *********************************/
        public void setxHlekkur(int x) {
            synchronized (_surfaceHolder) {
                hlekkurX = x;
            }
        }

        //TODO: improve touch moveHlekkurLeftTouch;
        public void moveHlekkurLeft(boolean move) {
            synchronized (_surfaceHolder) {
                if (move) {
                    _thread.hlekkurMoveLeft = move;
                }

                if (!move) {
                    _thread.hlekkurMoveLeft = move;
                }
            }
        }

        //TODO: improve touch moveHlekkurRightTouch;
        public void moveHlekkurRight(boolean move) {
            synchronized (_surfaceHolder) {
                //TODO: has to be a certain value of xPos to work
                if (move) {
                    _thread.hlekkurMoveRight = move;
                }
                if (!move) {
                    _thread.hlekkurMoveRight = move;
                }
            }

        }

        //TODO: improve toggleAttachandDefendTouch;
        public void attackandDefendToggle(boolean attack) {
            synchronized (_surfaceHolder) {
                //TODO: has to be a certain value of xPos to work
                if (attack) {
                    _thread.hlekkurAttack = attack;
                }

            }
        }

        public void gamePause(boolean pause) {
            synchronized (_surfaceHolder) {
                //TODO: has to be a certain value of yPos to work.
                if (pause) {
                    GAME_PAUSED = pause;
                }

            }
        }

        public void setX(float xPos) {
            synchronized (_surfaceHolder) {
                x = xPos;
            }
        }

        public void setY(float yPos) {
            synchronized (_surfaceHolder) {
                y = yPos;
            }
        }

        @Override
        public void run() {
            Canvas c;
            while (_run) {
                c = null;
                try {
                    c = _surfaceHolder.lockCanvas(null);
                    synchronized (_surfaceHolder) {

                        // Update the game state
                        update();

                        // Draw image
                        draw(c);
                    }

                } finally {
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state

                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

/*******************************/
/********************************
 ^^ End of ShieldAndArrowThread ^^
 ********************************/
/********************************/
    }


}
