/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Pushbot: Auto Drive By Time", group="Pushbot")
public class TestAutonomous_Linear extends LinearOpMode {

    /* Declare OpMode members. */
    Hardware11467Robot         robot   = new Hardware11467Robot();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();


    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;

    public int toTicks(double inch){
        double circumference = Math.PI * 4.0;
        double rotations = inch / circumference;
        return (int)(rotations * 1120);
    }

    @Override
    public void runOpMode() {
        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        //make encoders for all wheels at 0
        robot.leftForwardDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightForwardDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        /**
         * Check to make sure that this encoder code is correct
         **/
        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way
        //setting the target position for the encoders to run
        robot.leftForwardDrive.setTargetPosition(toTicks(10));
        robot.leftBackdDrive.setTargetPosition(toTicks(10));
        robot.rightForwardDrive.setTargetPosition(toTicks(10));
        robot.rightBackDrive.setTargetPosition(toTicks(10));
        //setting the mode so the encoders run to the specified target
        robot.leftForwardDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightForwardDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // Step 1:  Drive forward for 2 seconds
        /robot.rightBackDrive.setPower(FORWARD_SPEED);
        robot.rightForwardDrive.setPower(FORWARD_SPEED);
        robot.leftBackDrive.setPower(FORWARD_SPEED);
        robot.leftForwardDrive.setPower(FORWARD_SPEED);
        runtime.reset();
        while(opModeIsActive() && (leftForwardDrive.isBusy() || rightBackdDrive.isBusy() || rightForwardDrive.isBusy() || leftBackDrive.isBusy())){
            telemetry.addData("Left Front Wheel", leftForwardDrive.getPosition());
            telemetry.addData("Right Front Wheel", rightForwardDrive.getPosition());
            telemetry.addData("RIght Back Wheel", rightBackDrive.getPosition());
            telemetry.addData("Left Back Wheel", leftBackDrive.getPosition());
            telemetry.update();
        }
        /*while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(1000);*/


        // Step 2:  Spin right for 2 seconds
        robot.leftForwardDrive.setPower(TURN_SPEED);
        robot.leftBackDrive.setPower(TURN_SPEED);
        robot.rightForwardDrive.setPower(-TURN_SPEED);
        robot.rightBackDrive.setPower(-TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(1000);

        // Step 3:  Spin left for 2 seconds
        robot.leftForwardDrive.setPower(-TURN_SPEED);
        robot.leftBackDrive.setPower(-TURN_SPEED);
        robot.rightForwardDrive.setPower(TURN_SPEED);
        robot.rightBackDrive.setPower(TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(1000);

        // Step 3:  Drive Backwards for 2 Second
        robot.leftBackDrive.setPower(-FORWARD_SPEED);
        robot.leftForwardDrive.setPower(-FORWARD_SPEED);
        robot.rightBackDrive.setPower(-FORWARD_SPEED);
        robot.rightForwardDrive.setPower(-FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(1000);

        // Step 4: Straf Right for 2 seconds
        robot.leftBackDrive.setPower(-FORWARD_SPEED);
        robot.leftForwardDrive.setPower(FORWARD_SPEED);
        robot.rightBackDrive.setPower(FORWARD_SPEED);
        robot.rightForwardDrive.setPower(-FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(1000);

        // Step 5: Straf Left for 2 seconds
        robot.leftBackDrive.setPower(FORWARD_SPEED);
        robot.leftForwardDrive.setPower(-FORWARD_SPEED);
        robot.rightBackDrive.setPower(-FORWARD_SPEED);
        robot.rightForwardDrive.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(1000);

        // Step 6: Move Diagonally Forward and Left for 2 seconds
        robot.leftBackDrive.setPower(FORWARD_SPEED);
        //robot.leftForwardDrive.setPower(-FORWARD_SPEED);
        //robot.rightBackDrive.setPower(-FORWARD_SPEED);
        robot.rightForwardDrive.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(1000);

        // Step 7: Move Diagonally Back and Right for 2 seconds
        robot.leftBackDrive.setPower(-FORWARD_SPEED);
        //robot.leftForwardDrive.setPower(-FORWARD_SPEED);
        //robot.rightBackDrive.setPower(-FORWARD_SPEED);
        robot.rightForwardDrive.setPower(-FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(1000);

        // Step 8: Move Diagonally Back and Left for 2 seconds
        //robot.leftBackDrive.setPower(FORWARD_SPEED);
        robot.leftForwardDrive.setPower(-FORWARD_SPEED);
        robot.rightBackDrive.setPower(-FORWARD_SPEED);
        //robot.rightForwardDrive.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(1000);

        // Step 9: Move Diagonally Forward and Right for 2 seconds
        //robot.leftBackDrive.setPower(FORWARD_SPEED);
        robot.leftForwardDrive.setPower(FORWARD_SPEED);
        robot.rightBackDrive.setPower(FORWARD_SPEED);
        //robot.rightForwardDrive.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(1000);

        // Step 4:  Stop and close the claw.
//        robot.leftDrive.setPower(0);
//        robot.rightDrive.setPower(0);
//        robot.leftClaw.setPosition(1.0);
//        robot.rightClaw.setPosition(0.0);
//
//        telemetry.addData("Path", "Complete");
//        telemetry.update();
        sleep(1000);
    }
}
