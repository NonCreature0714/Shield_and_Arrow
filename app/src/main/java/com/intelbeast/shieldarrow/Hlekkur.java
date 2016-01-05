package com.intelbeast.shieldarrow;

/**
 * Created by Bill on 11/18/15.
 */
public class Hlekkur {

    //Positions
    private int PLATFORM_1_POS = 110;
    private int PLATFORM_2_POS = 210;
    private int PLATFORM_3_POS = 310;
    private int PLATFORM_4_POS = 410;
    private int PLATFORM_5_POS = 510;
    private int PLATFORM_6_POS = 610;
    private int PLATFORM_7_POS = 710;
    private int PLATFORM_8_POS = 810;

    private int HLEKKUR_STANDING_OFFSET = 10;

    private int hlekkurX;

    private int hlekkurPlatformPos; //Will have a 1-8 value

    private int FULL_LIFE = 6;

    private int hlekkurLife;

    private boolean alive;

    private boolean superPowered;

    private int standingPosition[] = {0, 0, 0, 0, 0, 0, 0, 0};

    private boolean attackFront;

    private boolean hlekkurMoveRight = false;

    private boolean hlekkurMoveLeft = false;

    private boolean harmed;

    private boolean defendFront;

    private boolean attackRear;

    private boolean leaveRight;

    private boolean leaveLeft;

    private boolean climb;

    private boolean ascend;

    public Hlekkur(int pos) {
        FULL_LIFE = 6;
        hlekkurLife = FULL_LIFE;
        alive = true;
        superPowered = true;
        standingPosition[pos] = 1;
        attackFront = false;
        harmed = false;
        defendFront = true;
        attackRear = false;
        leaveRight = false;
        leaveLeft = false;
        climb = false;
        ascend = false;

    }

    //TODO: create destructor

    public void setHarmToHlekkur(boolean harmed) {
        if (harmed) {
            this.hlekkurLife -= 1;
        }
    }

    public void setHlekkurMoveRight(boolean move){
        hlekkurMoveRight = move;
    }

    public boolean getHlekkurMoveRightState(){
        return hlekkurMoveRight;
    }

    public void setHlekkurMoveLeft(boolean move){
        hlekkurMoveRight = move;
    }

    public boolean getHlekkurMoveLeftState(){
        return hlekkurMoveLeft;
    }

    public int getHlekkurStandingPosition() {
        int num = 0;
        for (int i = 0; i < 8; i++) {
            if (this.standingPosition[i] == 1) {
                break;
            }
            num += 1;
        }
        return num;
    }

    public void setHlekkurStandingPosition() {
        if(hlekkurX == PLATFORM_1_POS){
            for(int i=0; i<8;i++){
                if(i==0){
                    this.standingPosition[i]=1;
                } else {
                    this.standingPosition[i]=0;
                }
            }
        } else if (hlekkurX == PLATFORM_2_POS){
            for(int i=0; i<8;i++){
                if(i==1){
                    this.standingPosition[i]=1;
                } else {
                    this.standingPosition[i]=0;
                }
            }
        } else if (hlekkurX == PLATFORM_3_POS){
            for(int i=0; i<8;i++){
                if(i==2){
                    this.standingPosition[i]=1;
                } else {
                    this.standingPosition[i]=0;
                }
            }
        } else if (hlekkurX == PLATFORM_4_POS){
            for(int i=0; i<8;i++){
                if(i==3){
                    this.standingPosition[i]=1;
                } else {
                    this.standingPosition[i]=0;
                }
            }
        } else if (hlekkurX == PLATFORM_5_POS){
            for(int i=0; i<8;i++){
                if(i==4){
                    this.standingPosition[i]=1;
                } else {
                    this.standingPosition[i]=0;
                }
            }
        } else if (hlekkurX == PLATFORM_6_POS){
            for(int i=0; i<8;i++){
                if(i==5){
                    this.standingPosition[i]=1;
                } else {
                    this.standingPosition[i]=0;
                }
            }
        } else if (hlekkurX == PLATFORM_7_POS){
            for(int i=0; i<8;i++){
                if(i==6){
                    this.standingPosition[i]=1;
                } else {
                    this.standingPosition[i]=0;
                }
            }
        } else if (hlekkurX == PLATFORM_8_POS){
            for(int i=0; i<8;i++){
                if(i==7){
                    this.standingPosition[i]=1;
                } else {
                    this.standingPosition[i]=0;
                }
            }
        }
    }

    public void toggleAttackandDefendTouch(boolean attack) {
        this.attackFront = attack;
    }

    public boolean getAttackAndDefendState() {
        return this.attackFront;
    }

    public void moveHlekkurRight(boolean b) {
        //TODO, figure out move right
    }

    public void moveHlekkurLeft(boolean b) {
        //TODO, figure out move left
    }

    public void setFullLife(int life) {
        this.FULL_LIFE = life;
    }

}
