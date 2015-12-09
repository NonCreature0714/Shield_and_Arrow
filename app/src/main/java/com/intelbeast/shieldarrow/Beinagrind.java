package com.intelbeast.shieldarrow;

/**
 * Created by Bill on 11/18/15.
 */

public class Beinagrind {

    private boolean beinagrindAttack;

    private int positionBeinagrind;

    private int attackPositionOfBeinagrind;

    private int ATTACK = 4;

    public Beinagrind() {
        beinagrindAttack = false;
        attackPositionOfBeinagrind = 0;
    }

    //TODO: create destructor


    //TODO: create public get and set functions

    public void setPositionBeinagrind(int pos) {
        positionBeinagrind = pos;
    }

    public void setAttackPositionOfBeinagrind(int atkPos) {
        attackPositionOfBeinagrind = atkPos;
        beinagrindAttack = attackPositionOfBeinagrind == ATTACK;
    }

}
