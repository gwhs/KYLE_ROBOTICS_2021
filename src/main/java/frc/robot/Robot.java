
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private DifferentialDrive m_myRobot;
  //private DifferentialDrive m_myRobotRight;


  private final XboxController m_driverController = new XboxController(0);
  private static final int m_myRobotLeftEnd = 6;
  private static final int m_myRobotLeftMiddle = 5;
  private static final int m_myRobotLeftFront = 4;
  private static final int m_myRobotRightEnd = 3;
  private static final int m_myRobotRightMiddle = 2;
  private static final int m_myRobotRightFront = 1;
  private static Compressor compressor;
  private DoubleSolenoid leftDouble = new DoubleSolenoid(0, 1);
  private DoubleSolenoid rightDouble = new DoubleSolenoid(/* The PCM CAN ID */ 0, 2, 3);
  
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    CANSparkMax m_myRobotLeftGroupEnd = new CANSparkMax(m_myRobotLeftEnd, MotorType.kBrushless);
    CANSparkMax m_myRobotLeftGroupMiddle = new CANSparkMax(m_myRobotLeftMiddle, MotorType.kBrushless);
    CANSparkMax m_myRobotLeftGroupFront = new CANSparkMax(m_myRobotLeftFront, MotorType.kBrushless);
    SpeedControllerGroup m_leftGroup = new SpeedControllerGroup(m_myRobotLeftGroupEnd, m_myRobotLeftGroupMiddle,
        m_myRobotLeftGroupFront);
    CANSparkMax m_myRobotRightGroupEnd = new CANSparkMax(m_myRobotRightEnd, MotorType.kBrushless);
    CANSparkMax m_myRobotRightGroupMiddle = new CANSparkMax(m_myRobotRightMiddle, MotorType.kBrushless);
    CANSparkMax m_myRobotRightGroupFront = new CANSparkMax(m_myRobotRightFront, MotorType.kBrushless);
    SpeedControllerGroup m_RightGroup = new SpeedControllerGroup(m_myRobotRightGroupEnd, m_myRobotRightGroupMiddle,
        m_myRobotRightGroupFront);
    // m_myRobot____ is not actually a group it defines the CANspark as seen above.
    // Change if needed.
    m_myRobot = new DifferentialDrive(m_leftGroup, m_RightGroup);
    compressor = new Compressor();
    compressor = null;

    leftDouble.set(DoubleSolenoid.Value.kReverse);
    rightDouble.set(DoubleSolenoid.Value.kForward);
  }


  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
   // m_leftMotor.set(m_driverController.getY(Hand.kLeft) * 0.50);
   // m1_Motor.set(ControlMode.PercentOutput, m_driverController.getY(Hand.kRight) * 0.25);
   if (m_driverController.getYButtonPressed()) {
    leftDouble.toggle();
    rightDouble.toggle();
  }
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //m_myRobot.tankDrive(m_driverController.getX(Hand.kLeft), m_driverController.getX(Hand.kRight)); //Not needed
    m_myRobot.arcadeDrive(m_driverController.getY(Hand.kLeft)*1, m_driverController.getX(Hand.kRight)*.9);
    //m_myRobot.arcadeDrive(m_driverController.getX();
    //m_myRobot.tankDrive(m_driverController.getX(Hand.kRight),m_driverController.getX(Hand.kRight));
    //Swapped (Hand.kLeft) and (Hand.kRight) so you let go of Right joystick you turn right and vice versa
    //m_myRobot.tankDrive(m_driverController.getTriggerAxis(Hand.kLeft), m_driverController.getTriggerAxis((Hand.kRight)));
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}