package frc.robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {
    private DoubleSolenoid leftDouble = new DoubleSolenoid(0, 1);
  private DoubleSolenoid rightDouble = new DoubleSolenoid(/* The PCM CAN ID */ 0, 2, 3);

public void gearOne() {
    leftDouble.set(DoubleSolenoid.Value.kReverse);
    rightDouble.set(DoubleSolenoid.Value.kForward);
}
public void gearTwo(){
    leftDouble.set(DoubleSolenoid.Value.kForward);
    rightDouble.set(DoubleSolenoid.Value.kReverse);
}

@Override
protected void initDefaultCommand() {
	// TODO Auto-generated method stub
	
}
}