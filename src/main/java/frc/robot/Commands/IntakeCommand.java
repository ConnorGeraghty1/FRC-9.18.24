package frc.robot.Commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Subsystems.Arm;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Lights;
import edu.wpi.first.wpilibj.Timer;

public class IntakeCommand extends Command {
    private Arm arm;
    private Intake intake;
    private boolean isFinished = false;
    public int elapsed = 0;
    public boolean triggered = false;
    public Lights lights;
    public IntakeCommand(){
        addRequirements(Constants.intake);

    }
    @Override
    public void initialize(){
        intake = Constants.intake;
        triggered = false;
        elapsed = 0;
        arm = Constants.arm;
        arm.setDesired(5.5);
        addRequirements(Constants.arm);
        lights = Constants.lights;
    }   

    // crates timer object
    public Timer timer = new timer();
    
    @Override
    public void execute(){
        if(Constants.intake.intakeSensor.getVoltage()<.5) {
            triggered = true;
        }
        // start the timer at 0, hopefuly
        timer.start();
        intake.run();
        // while time is NOT 5 run intake
        while(true){
            if(timer.hasElapsed(5)){
                intake.stop();
                break;
            }
        }
        //stop the timer and reset
        timer.stop();
        timer.reset();
        
    }
    @Override
    public boolean isFinished(){
         
        return isFinished;

    }

    @Override
    public void end(boolean interrupted) {
        if(interrupted) {
            lights.off();
        }
        Constants.intake.stop();
        isFinished = false;
    }
}

