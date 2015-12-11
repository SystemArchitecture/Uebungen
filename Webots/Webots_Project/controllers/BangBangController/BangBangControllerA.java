// File:          BangBangController.java
// Date:          
// Description:   
// Author:        
// Modifications: 

// You may need to add other webots classes such as
//  import com.cyberbotics.webots.controller.DistanceSensor;
//  import com.cyberbotics.webots.controller.LED;
// or more simply:
//  import com.cyberbotics.webots.controller.*;
import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;

// Here is the main class of your controller.
// This class defines how to initialize and how to run your controller.
// Note that this class derives Robot and so inherits all its functions
public class BangBangControllerA extends DifferentialWheels {
  public static final int STEP_TIME = 16;
  public static final int MIN_SPEED = 0;
  public static final int MAX_SPEED = 1000;
  public static final int LIGHT_SENSOR_L = 0; // sensor left
  public static final int LIGHT_SENSOR_LF = 1; // sensor left front
  public static final int LIGHT_SENSOR_RF = 2; // sensor right front
  public static final int LIGHT_SENSOR_R = 3; // sensor right
  public static final double LIGHT_SENSOR_DIFF = 100; // sensor right
  
  private LightSensor[] lightSensors;
  
  // You may need to define your own functions or variables, like
  //  private LED led;
  
  // BangBangController constructor
  public BangBangControllerA() {
      
    // call the Robot constructor
    super();
    
    // initialize and activate distance sensors
    lightSensors = new LightSensor[] {getLightSensor("ls5"),
                                      getLightSensor("ls7"),
                                      getLightSensor("ls0"),
                                      getLightSensor("ls2")};
    for (int i=0; i<4; i++){
      lightSensors[i].enable(10);
    }
    
		  
    // You should insert a getDevice-like function in order to get the
    // instance of a device of the robot. Something like:
    //  led = getLED("ledName");
    
  }
    
  // User defined function for initializing and running
  // the BangBangController class
  public void run() {
    
    // Main loop:
    // Perform simulation steps of 64 milliseconds
    // and leave the loop when the simulation is over
    while (step(STEP_TIME) != -1) {
      // Read the sensors:
      // Enter here functions to read sensor data, like:
      //  double val = distanceSensor.getValue();
      
      // Process sensor data here
      
      // drive towards light
      // TODO use ALL light sensors??
      if(getRightLightValue() < getLeftLightValue() + LIGHT_SENSOR_DIFF){
        driveRight();
      } else if (getLeftLightValue() < getRightLightValue() + LIGHT_SENSOR_DIFF) {
        driveLeft();
      } else {
        driveForward();
      }
      
    };
    
    // Enter here exit cleanup code
  }

  // This is the main program of your controller.
  // It creates an instance of your Robot subclass, launches its
  // function(s) and destroys it at the end of the execution.
  // Note that only one instance of Robot should be created in
  // a controller program.
  // The arguments of the main function can be specified by the
  // "controllerArgs" field of the Robot node
  public static void main(String[] args) {
    BangBangControllerA controller = new BangBangControllerA();
    controller.run();
  }
  
  public void driveLeft(){
    setSpeed(MIN_SPEED, MAX_SPEED);
  }
  
  public void driveRight(){
    setSpeed(MAX_SPEED, MIN_SPEED);
  }
  
  public void driveForward(){
    setSpeed(MAX_SPEED, MAX_SPEED);
  }
  
  public void driveStop(){
    setSpeed(MIN_SPEED, MIN_SPEED);
  }
  
  public double getLeftLightValue() {
    return lightSensors[LIGHT_SENSOR_LF].getValue() + lightSensors[LIGHT_SENSOR_L].getValue();
  }
  
  public double getRightLightValue() {
    return lightSensors[LIGHT_SENSOR_RF].getValue() + lightSensors[LIGHT_SENSOR_R].getValue();
  }
  
}
