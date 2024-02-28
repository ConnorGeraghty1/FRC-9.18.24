package frc.robot.Commands;
import java.util.function.IntConsumer;

import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Subsystems.Arm;
import frc.robot.Subsystems.Camera;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Shooter;
import frc.robot.Subsystems.Swerve.Drivetrain;

public class AmpCommand extends Command {
    private Arm arm;
    private Shooter shooter;
    private Drivetrain swerve;
    private Camera cam;
    private boolean isFinished = false;
    private int ampStep = 0;
    private double distanceTarget;
    private double distanceWall;
    public AmpCommand(){
        arm = Constants.arm;
        swerve = Constants.swerve;
        shooter = Constants.shooter;
        cam = Constants.camera;
        addRequirements(arm);
        addRequirements(shooter);
        addRequirements(Constants.intake);
    }

    @Override
    public void initialize(){
   
    }
    @Override
    public void execute(){
        arm.setDesired(105);

        if(arm.armEnc.getDistance() > 45) {
            shooter.setLowVelocity();
            ampStep++;
        }
        arm.moveArm();
    }

    @Override
    public void end(boolean interrupted) {
        Constants.shooter.stop();

    }
}
