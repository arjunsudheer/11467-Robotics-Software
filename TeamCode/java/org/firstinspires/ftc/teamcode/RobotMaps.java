/**
 * This is the RobotMaps class. This class stores the X and Y coordinates of the robot and using this along
 * with the other values provided for the goals and power shots, can calculate all angles needed for robot
 * movement to hit the objects. This has methods such as inRange() method, that tells if the robot is inside
 * the playing field and doesn't allow the robot to move if it is at an edge.
 *
 * The coordinate system works by having the origin (0, 0) at the top left corner. The "right edge"
 * and "bottom edge" variables indicate the maximum distance the robot can travel.
 *
 * @author Arjun Sudheer
 * @version 10-01-2020
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Hardware11467Robot;


public class RobotMaps {
    //robot x position
    public int xPos;
    //robot y position
    public int yPos;
    //robot shooting mech angle
    public double shotAngle;
    //robot angle
    public double strafeAngle;
    //max distance of horizontal playing field
    private final double rightEdge = 1000;
    //max distance of vertical playing field
    private final double bottomEdge = 1000;
    /**
     * constructor for RobotMaps class
     * @param x X position of the robot
     * @param y Y position of the robot
     */
    public RobotMaps(int x, int y, double shotAngle, double strafeAngle){
        this.xPos = x;
        this.yPos = y;
        this.shotAngle = shotAngle;
        this.strafeAngle = strafeAngle;
    }
    /**
     * Method to tell if the robot is at the right edge
     * @return true if the robot is at the specified edge
     */
    public boolean isAtRightEdge(){
        if(xPos >= rightEdge){
            return true;
        }
        return false;
    }
    /**
     * Method to tell if the robot is at the left edge
     * @return true if the robot is at the specified edge
     */
    public boolean isAtLeftEdge(){
        if(xPos <= 0)
            return true;

        return false;
    }
    /**
     * Method to tell if the robot is at the top edge
     * @return true if the robot is at the specified edge
     */
    public boolean isAtTopEdge(){
        if(yPos <= 0){
            return true;
        }
        return false;
    }
    /**
     * Method to tell if the robot is at the bottom edge
     * @return true if the robot is at the specified edge
     */
    public boolean isAtBottomEdge(){
        if(yPos >= bottomEdge){
            return true;
        }
        return false;
    }
    /**
     * Method to calculate the angle of elevation of the shooting mech
     * @param x X position of the shot target
     * @param height height of the shot target relative to the shooting mech
     */
    private double calcShotDegrees(int x, int height) {
        return Math.atan2(height, xPos - x);
    }
    /**
     * Method to calculate the angle of strafing the robot
     * @param x X position of the shot target
     * @param y Y position of the shot target
     */
    private double calcStrafeDegrees(int x, int y) {
        return Math.atan2(yPos - y, xPos - x);
    }
    /**
     * Method to convert degrees into ticks
     */
    private int toTicks(double degrees){
        //replace the "1" with the appropriate calculation
        return (int)1.2;
    }
    /**
     * Method call to shoot the rings
     */
    public void shootRings(int x, int y, int height){
        //assigning the varibales to the vlaue given by the angle calculating methods
        strafeAngle = calcStrafeDegrees(x, y);
        shotAngle = calcShotDegrees(x, height);
        //instance of main class to access motors for turning
        Hardware11467Robot robot = new Hardware11467Robot();
        /*
        Insert the code here to turn the appropriate angle degrees from the calcStrafeDegrees() method
         */
        /*
        Insert the code here to turn the appropriate angle degrees from the calcShotDegrees() method
         */
         /*
        Insert the code here to turn the appropriate angle degrees from the calcStrafeDegrees() method
        but negative so that the robot returns to its original angle after all shots are fired
         */
    }
}
