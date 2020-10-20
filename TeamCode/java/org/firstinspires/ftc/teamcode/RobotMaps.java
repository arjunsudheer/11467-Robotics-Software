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

import java.lang.Math;

/**
 * Double check this class for all methods and to see if this works correctly or not
 **/

public class RobotMaps {
    //robot x position
    public int xPos;
    //robot y position
    public int yPos;
    //max distance of horizontal playing field
    private final double rightEdge = 1000;
    //max distance of vertical playing field
    private final double bottomEdge = 1000;
    /**
     * constructor for RobotMaps class
     * @param x X position of the robot
     * @param y Y position of the robot
     */
    public RobotMaps(int x, int y){
        this.xPos = x;
        this.yPos = y;
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
     * To ticks method returns number of ticks based on inches of given distance
     * @param inch the distance to travel
     */
    public int toTicks(double inch){
        double circumference = Math.PI * 4.0;
        double rotations = inch / circumference;
        return (int)(rotations * 1120);
    }
    /**
     * Method to move the robot to a specific position on the playing field
     * @param x X position of target
     * @param y Y postiion of target
     */
    public void goTo(int x, int y){
        //number of ticks to move in the x-axis direction
        int xDistance = toTicks((Math.abs(xPos) - Math.abs(x)));
        //number of ticks to move in the y-axis direction
        int yDistance = toTicks(Math.abs(yPos) - Math.abs(y));
        //run this twice once for horizontal movement, other for vertical movement
        for(int i = 0; i < 2; i++) {
            //make encoders for all wheels at 0
            robot.leftForwardDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightForwardDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //depending on iteration, adjust the values of movement
            switch (i) {
                case 0:
                    //setting the target position for the encoders to run
                    robot.leftForwardDrive.setTargetPosition(Math.abs(xDistance));
                    robot.leftBackdDrive.setTargetPosition(Math.abs(xDistance));
                    robot.rightForwardDrive.setTargetPosition(Math.abs(xDistance));
                    robot.rightBackDrive.setTargetPosition(Math.abs(xDistance));
                    break;
                case 1:
                    //setting the target position for the encoders to run
                    robot.leftForwardDrive.setTargetPosition(Math.abs(yDistance));
                    robot.leftBackdDrive.setTargetPosition(Math.abs(yDistance));
                    robot.rightForwardDrive.setTargetPosition(Math.abs(yDistance));
                    robot.rightBackDrive.setTargetPosition(Math.abs(yDistance));
                    break;
            }
            //setting the mode so the encoders run to the specified target
            robot.leftForwardDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightForwardDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            switch (i) {
                //set the power accordingly based on whether or not the given position is greater
                //or smaller than the current robot position
                case 0:
                    robot.leftForwardDrive.setPower(0.6 * (xDistance / Math.abs(xDistance)));
                    robot.rightForwardDrive.setPower(-0.6 * (xDistance / Math.abs(xDistance)));
                    robot.leftBackDrive.setPower(-0.6 * (xDistance / Math.abs(xDistance)));
                    robot.rightBackDrive.setPower(0.6 * (xDistance / Math.abs(xDistance)));
                    break;
                case 1:
                    robot.leftForwardDrive.setPower(0.6 * (yDistance / Math.abs(yDistance)));
                    robot.rightForwardDrive.setPower(0.6 * (yDistance / Math.abs(yDistance)));
                    robot.leftBackDrive.setPower(0.6 * (yDistance / Math.abs(yDistance)));
                    robot.rightBackDrive.setPower(0.6 * (yDistance / Math.abs(yDistance)));
                    break;
            }
        }
    }
    /**
     * Method call to shoot the rings
     */
    public void shootRings(int x, int y, int height){
        //get into position for shooting
        goTo(x, y);
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
