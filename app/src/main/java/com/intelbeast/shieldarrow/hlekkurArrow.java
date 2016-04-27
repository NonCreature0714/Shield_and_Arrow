package com.intelbeast.shieldarrow;

/**
 * Created by Bill on 1/11/16.
 */
public class HlekkurArrow { //TODO Hlekkur arrow logic
    private static double hlekkurArrowX_leftEdge;
    private static double hlekkurArrowX_rightEdge;
    private static double hlekkurArrowY_Top;
    private static double hlekkurArrowY_Bottom;
    private static int arrowWidth = 30;

    public static void setHlekkurArrow(){
        hlekkurArrowX_leftEdge = Hlekkur.getHlekkurX_leftEdge();
        hlekkurArrowX_rightEdge = Hlekkur.getHlekkurX_leftEdge() + arrowWidth;
    }

    public double getLeadingEdgeCollision(){ return hlekkurArrowX_rightEdge; }

    public static double getHlekkurArrowX_leftEdge(){
        return hlekkurArrowX_leftEdge;
    }

    public static double getHlekkurArrowX_rightEdge(){
        return hlekkurArrowX_rightEdge;
    }

    public static double getHlekkurArrowY_Top(){
        return hlekkurArrowY_Top;
    }

    public static double getHlekkurArrowY_Bottom(){
        return hlekkurArrowY_Bottom;
    }

    public static void setArrowColor(int selection){
        switch (selection){
            case 1:
                //TODO choose color
                break;
            case 2:
                //TODO chose color
                break;
            default:
                //TODO set default color
        }
    }

    //TODO design 'getArrowColor' method

    public static void updateHlekkurArrow(){ //Update arrow edges and color.
        //TODO use animator to move arrow
        //TODO update arrow color
    }


}

