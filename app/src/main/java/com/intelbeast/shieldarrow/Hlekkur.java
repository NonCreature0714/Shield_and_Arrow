package com.intelbeast.shieldarrow;

/**
 * Created by Bill on 11/18/15.
 */

public class Hlekkur {

    //Positions for Hlekkur's X position relative to the platform he stands on
    private int PLATFORM_1_POS = 110; //Think of it as, "Hlekkur's left position on platform 'X', not the actual platform position.
    private int PLATFORM_2_POS = 210;
    private int PLATFORM_3_POS = 310;
    private int PLATFORM_4_POS = 410;
    private int PLATFORM_5_POS = 510;
    private int PLATFORM_6_POS = 610;
    private int PLATFORM_7_POS = 710;
    private int PLATFORM_8_POS = 810;

    private int HLEKKUR_X_RIGHT_EDGE_OFFSET = 70; //Value added to hlekkurX for right edge of rectangle

    private int HLEKKUR_Y_BOTTOM_EDGE_OFFSET = 125; // value added to top edge of hlekkur rectangle to get bottom edge

    public static int hlekkurX; //This is the base position of Hlekkur, the left side of his rectangle. Many other variables are set based on hlekkurX.

    private int HLEKKUR_Y = 460; // constant - shouldn't change

    private int hlekkurPlatformPos; //Will have a 1-8 value, for each platform from left to right.

    private int FULL_LIFE = 6; // could adjust to increase life (possibly?)

    private int hlekkurLife; //Amount of life Hlekkur has at any given time.

    private boolean hlekkurMaxLife; //'True' if player is awarded maximum life refill.

    private boolean superPowered; // Playing with an idea of  invincibilty

    private boolean attackFront; //Set to 'true' if player hits attack button/screen area.

    private boolean hlekkurGainLife; //Set to true of Hlekkur is awarded one life point.

    private boolean hlekkurMoveRight = false; //Set to 'true' if player presses screen area for right-ward movement for one frame.

    private boolean hlekkurMoveLeft = false; //Set to 'true' if player presses screen area for left-ward movement for one frame.

    private boolean attackRear; //Used on platform 1 to attack Norn.

    private boolean leaveRight; //Used to select level after beating Skrimsli

    private boolean leaveLeft; //Used to select level after beating Skrimsli

    private boolean climb; //Used after defeating mini boss

    private boolean ascend; //Used after defeating boss.

    private boolean hlekkurHarmed = false; //Set to 'true' if Hlekkur is harmed by bad guy.

    private boolean hlekkurAttacks; //Set to 'true' if player hits attack button/screen area.

    public static boolean hlekkurAlive; //set to true while hlekkur has greater than Zero life.




    //Contructor
    public Hlekkur() { // must pass a value 1-8 for Hlekkur to start on platform.
        FULL_LIFE = 6; //Start with full life.
        hlekkurLife = FULL_LIFE;
        superPowered = true;
        hlekkurPlatformPos = 3;
        attackFront = false;
        hlekkurHarmed = false;
        attackRear = false;
        leaveRight = false;
        leaveLeft = false;
        climb = false;
        ascend = false;
        hlekkurAlive = true;

    }

    //TODO: create destructor









    /********************************************************************
     *                     Movement and Position Logic                  *
     *                                                                  *
     * Need to keep in mind what input occurs in current frame,         *
     * and what will change next frame,                                 *
     * and what input occurred in the past;                             *
     * Such as, is the player still giving move left input, when they   *
     * are allowed allowed to move once per input?                      *
     * ******************************************************************/

    private void setHlekkurX(int xPos){ hlekkurX = xPos; } //Set Hlekkur's left edge X-position.

    private int getHlekkurX(){ return hlekkurX; } //Should be a no-brainer.

    public void setHlekkurMoveRight(boolean moveRight){ hlekkurMoveRight = moveRight; } //Set true if player desires right-ward movement.

    public void setHlekkurMoveLeft(boolean moveLeft){ hlekkurMoveLeft = moveLeft; } //Set true if player desires left-ward movement.

    public boolean getHlekkurMoveRight(){ return hlekkurMoveRight; } //If in the previous frame player desired rightward movement, will return true, otherwise false.

    public boolean getHlekkurMoveLeft(){ return hlekkurMoveLeft; } //If in the previous frame player desired rightward movement, will return true, otherwise false.

    public void setHlekkurPlatformPos(int platform){ hlekkurPlatformPos = platform; } // Used to set Hlekkur's position 1-8;

    public int getHlekkurPlatformPos(){ return hlekkurPlatformPos; }

    private void platformToRight(){ //If not already on right most platform, move platform position one right
        if(this.getHlekkurPlatformPos() < 8){
            hlekkurPlatformPos += 1;
        }
    }

    private void platformToLeft(){ //If not already on left most platform, move platform position one left
        if(this.getHlekkurPlatformPos() > 1){
            hlekkurPlatformPos -= 1;
        }
    }

    public void moveHlekkurRight() { //Give command to move Hlekkur right.
        this.platformToRight();
        this.setHlekkurMoveRight(false);
    }

    public void moveHlekkurLeft() { //Give command to move Hlekkur left.
        this.platformToLeft();
        this.setHlekkurMoveLeft(false);
    }

    public void updateHlekkurX(){ //This function can update hlekkurX based on platform position.
        switch (this.getHlekkurPlatformPos()){
            case 1:
                this.setHlekkurX(PLATFORM_1_POS);
                break;
            case 2:
                this.setHlekkurX(PLATFORM_2_POS);
                break;
            case 3:
                this.setHlekkurX(PLATFORM_3_POS);
                break;
            case 4:
                this.setHlekkurX(PLATFORM_4_POS);
                break;
            case 5:
                this.setHlekkurX(PLATFORM_5_POS);
                break;
            case 6:
                this.setHlekkurX(PLATFORM_6_POS);
                break;
            case 7:
                this.setHlekkurX(PLATFORM_7_POS);
                break;
            case 8:
                this.setHlekkurX(PLATFORM_8_POS);
        }
    }







    /*****************
     * Arrow Animation & logic
     * ***********************/
    private boolean activeArrow;

    private double hlekkurArrowX_leftEdge;

    private void setHlekkurArrowX_leftEdge(int val){ hlekkurArrowX_leftEdge += val; }

    private double hlekkurArrowX_rightEdge;

    private void setHlekkurArrowX_rightEdge(){ hlekkurArrowX_rightEdge = this.getHlekkurX() + 40; }

    private double hlekkurArrowY_topEdge;

    private double hlekkurArrowY_bottomEdge;

    private int arrowLaunchOrigin;

    private void setArrowLaunchOrigin(int launchOrigin){ arrowLaunchOrigin = launchOrigin; }

    private int getArrowLaunchOrigin(){ return arrowLaunchOrigin; }

    private int arrowLaunchOffset = 40;



    private void setActiveArrow(boolean fireArrow){
        this.setActiveArrow(fireArrow);
        this.setArrowLaunchOrigin(this.getHlekkurPlatformPos() + arrowLaunchOffset);
    }

    private boolean getActiveArrow(){ return activeArrow; }

    public void updateActiveArrow(){
        //TODO, work this out
        if(this.getActiveArrow()){
            //TODO, update the arrow position
        }
    }

    /***********************
     * Draw States Information
     * **************************/
    public static int getHlekkurX_leftEdge(){ return hlekkurX; }  // really just for rect draw purposes

    public int getHlekkurX_rightEdge(){
        return hlekkurX + HLEKKUR_X_RIGHT_EDGE_OFFSET;
    } // really just for rect draw purposes

    public int getHlekkurY_topEdge(){
        return HLEKKUR_Y;
    } // really just for rect draw purposes

    public int getHlekkurY_bottomEdge(){
        return HLEKKUR_Y + HLEKKUR_Y_BOTTOM_EDGE_OFFSET;
    } // really just for rect draw purposes



    /*****************************
     * Attack states
     * *****************************/
    public void setHlekkurAttacks(boolean atkFront){ hlekkurAttacks = atkFront; }

    public boolean getHlekkurAttacks(){ return hlekkurAttacks; }

    /*public boolean getAttackFrontState() {
        return this.attackFront;
    }*/

    public void toggleAttackAndDefendTouch(boolean attack) { //Enter a true or false value to make Hlekkur attack front (true) or defend front (false).
        if(attack){
            this.setHlekkurAttacks(attack);
        }

        if(!attack){
            this.setHlekkurAttacks(attack);
        }
    }


    /****************************
     * Life states
     * ****************************/

    public void setNewFullLife(int life) { this.FULL_LIFE = life; }

    public void setHlekkurLife(int numLife){ hlekkurLife = numLife; }

    public int getHlekkurLife(){
        return hlekkurLife;
    }

    public void setHarmToHlekkur(boolean harm) { hlekkurHarmed = harm; }

    public void setHlekkurGainLife(boolean gainLife){ hlekkurGainLife = gainLife; }

    public void setHlekkurMaxLife(boolean maxLife){ hlekkurMaxLife = maxLife; }

    public boolean getHarmToHlekkurState(){ return hlekkurHarmed; }

    public boolean getHlekkurMaxLifeState(){ return hlekkurMaxLife; }

    public boolean getHlekkurGainLifeState(){
        return hlekkurGainLife;
    }



   /*****************************
    * Update frame
    * ******************************/
//Update happens last, consolidating all variables in a frame
    public void updateHlekkur(){
        if(this.getHarmToHlekkurState()){ //Reduce Hlekkur's life if harmed.
            hlekkurLife -= 1;
            this.setHarmToHlekkur(false);
        }

        if(this.getHlekkurAttacks()){
            //TODO add other attack state stuff, like drive
            this.setHlekkurAttacks(false);
        }

        //TODO, complete logic below
        //if(this.) // for firing arrows

        if (this.getHlekkurGainLifeState()){ //Increase Hlekkur's life if earned.
            hlekkurLife += 1;
            this.setHlekkurGainLife(false);
        }

        if (this.getHlekkurMaxLifeState()){ //Max life if condition met.
            hlekkurLife = FULL_LIFE;
            this.setHlekkurMaxLife(false);
        }




        this.updateHlekkurX(); //Update Hlekkur's position

        //TODO update hlekkurArrow

    }
}
