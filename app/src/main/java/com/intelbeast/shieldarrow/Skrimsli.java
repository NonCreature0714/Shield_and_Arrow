package com.intelbeast.shieldarrow;

/**
 * Created by Bill on 11/23/15.
 */

public class Skrimsli {

    private int skrimsliLife;

    private boolean skrimsliAlive;

    private boolean skrimsliAttack;

    private boolean skrimsliHarmed;

    //TODO: create default constructor
    public Skrimsli() {
        skrimsliLife = 8;
        skrimsliAlive = true;
        skrimsliAttack = true;
        skrimsliHarmed = false;
    }

    //TODO: create destructor

    //TODO: create public get and set functions
    public void setSkrimsliAlive(boolean alive) {
        if (!alive) {
            skrimsliAlive = false;
            skrimsliLife = 0;
            skrimsliAttack = false;
        }
    }

    public void setHarmToSkrimsli(boolean harmed) {
        if (harmed) {
            skrimsliLife -= 1;
        }

        if (skrimsliLife == 0) {
            this.setSkrimsliAlive(false);
        }
    }

    public boolean getskrimsliAliveStat() {
        return skrimsliAlive;
    }




}
