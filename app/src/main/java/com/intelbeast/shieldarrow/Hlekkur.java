package com.intelbeast.shieldarrow;

/**
 * Created by Bill on 11/18/15.
 */
public class Hlekkur {

    private int FULL_LIFE = 6;

    private int hlekkurLife;

    private boolean alive;

    private boolean superPowered;

    private int standingPosition[] = {0, 0, 0, 0, 0, 0, 0, 0};

    private int newStandingPosition;

    private int oldStandingPositon;

    private boolean attackFront;

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

    public int getStandingPosition() {
        int num = 0;
        for (int i = 0; i < 8; i++) {
            if (this.standingPosition[i] == 1) {
                break;
            }
            num += 1;
        }
        return num;
    }

    public void setStandingPosition(int pos) {
        this.newStandingPosition = pos;
        this.oldStandingPositon = this.getStandingPosition();
        this.standingPosition[oldStandingPositon] = 0;
        this.standingPosition[newStandingPosition] = 1;
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
