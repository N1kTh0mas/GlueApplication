//Glue App by Nik Thomas

//add libraries
#include "AccelStepper.h"

//define stepper motors
AccelStepper motorX(1,9,8);
AccelStepper motorY(1,7,6);


#define home_switchX 22
#define home_switchY 24

//define var
boolean state = false;
byte spd = 255;
byte spd2 = 100;
int armRPWN = 2;
int armLPWN = 3;
int nedRPWN = 4;
int nedLPWN = 5;
int nedstep = 500;


int mspeed = 3500;
int macel = 2000;
boolean inputX = LOW;
boolean inputY = LOW;
long PosX = 0;
long PosY = 0;
long TravelX = 0;
int move_finishedX=1;
long TravelY = 0 ;
int move_finishedY=1;
long initial_homingX=-1;
long initial_homingY=-1;
int timex = 0;
int timey = 0;


void setup() {
 //Serial setup
  Serial.begin(115200);
  Serial.setTimeout(100);

//pin definitons
  pinMode(home_switchX, INPUT_PULLUP);
  pinMode(home_switchY, INPUT_PULLUP);
  pinMode(armRPWN, OUTPUT);
  pinMode(armLPWN, OUTPUT);
  pinMode(nedRPWN, OUTPUT);
  pinMode(nedLPWN, OUTPUT);

  //Calibrate();

}

void loop() {

  if (inputX == LOW || inputY  == LOW){
      
  if (Serial.available() >= 3){
    char command = Serial.read();
    switch (command)
    {
      case 'x' : TravelX = Serial.parseInt();
      move_finishedX = 0;
      if (TravelX < 0 || TravelX > 32000){
        Serial.println("Invalid X cord.");
      }
      else{
        inputX = HIGH;
        Serial.print("X: ");
        Serial.print(TravelX);
        Serial.println(".");
          motorX.moveTo(TravelX);
      }
      break;

      case 'y' : TravelY = Serial.parseInt();
      move_finishedY = 0;
      if (TravelY < 0 || TravelY > 32000){
        Serial.println("Invalid Y cord.");
      }
      else{
        inputY = HIGH;
        Serial.print("Y: ");
        Serial.print(TravelY);
        Serial.println(".");
        motorY.moveTo(TravelY);
      }
      break;

      case 't' :
      TravelX =16000;
      TravelY =16000;
      inputX = HIGH;
      inputY = HIGH;
      Serial.println("Moving to center.");
      motorY.moveTo(TravelY);
      motorX.moveTo(TravelX);
      break;

      case 'p' :
      mspeed = Serial.parseInt();
      Serial.print("Setting New speed to: ");
      Serial.print(mspeed);
      Serial.println(".");
      motorY.setMaxSpeed(mspeed);
      motorX.setMaxSpeed(mspeed);
      break;

      case 'a' :
      macel = Serial.parseInt();
      Serial.print("Setting New aceleration to: ");
      Serial.print(macel);
      Serial.println(".");
      motorY.setAcceleration(macel);
      motorX.setAcceleration(macel);
      break;

      case 'o':
      arm_extend();
      break;

      case 'c':
      arm_retract();
      break;

      case 'r':
      needle_retract();
      break;

      case 'e':
      needle_step();
      break;

      case 'E':
      spd2 = 50;
      analogWrite(nedRPWN, 0);
      analogWrite(nedLPWN, spd2);
      break;

      case 's':
      analogWrite(armRPWN,0);
      analogWrite(armLPWN, 0);
      analogWrite(nedRPWN, 0);
      analogWrite(nedLPWN, 0);
      break;

      case 'u':
      spd = Serial.parseInt();
      break;

      case 'b':
      spd2 = Serial.parseInt();
      break;

      case 'f':
      nedstep = Serial.parseInt();
      break;

      case 'C':
      Calibrate();
      break;

      case 'i':
      info();
      break;
    }
  }
  }
  

  if (inputX == HIGH && inputY  == HIGH){

  
    
    delay(1000);

    while ((motorX.distanceToGo()!=0)){
      motorX.run();
    }
    while ((motorY.distanceToGo()!=0)){
      motorY.run();
    }
    
    

  if ((motorX.distanceToGo() == 0) && (motorY.distanceToGo() == 0)){
     Serial.println("Move Completed.");
     inputX = LOW;
     inputY = LOW;
     
  }
  }

}

void info(){
  int px;
  int py;
  px = motorX.currentPosition();
  py = motorY.currentPosition();
  Serial.println("");
  Serial.println("INFORMATION.");
  Serial.println("Position.");
  Serial.print("Current X: ");
  Serial.print(px);
  Serial.println(".");
  Serial.print("Current Y: ");
  Serial.print(py);
  Serial.println(".");
  Serial.println("Speeds.");
  Serial.print("Current XY speed: ");
  Serial.print(mspeed);
  Serial.println(".");
  Serial.print("Current XY Aceleration: ");
  Serial.print(macel);
  Serial.println(".");
  Serial.print("Current Arm Speed: ");
  Serial.print(spd);
  Serial.println(".");
  Serial.print("Current Needle Speed: ");
  Serial.print(spd2);
  Serial.println(".");
  Serial.println("Steps.");
  Serial.print("Current Needle Step Length (milSec): ");
  Serial.print(nedstep);
  Serial.println(".");
  Serial.println(".Inputs States/Travel RAM.");
  Serial.print("Current X Input State: ");
  if (inputX == HIGH){
    Serial.print("TRUE");
    Serial.println(".");
  }
  else if (inputX == LOW){
    Serial.print("FALSE");
    Serial.println(".");
  }
  else{
    Serial.print("ERROR");
    Serial.println(".");
  }
  Serial.print("Current Y Input State: ");
  if (inputY == HIGH){
    Serial.print("TRUE");
    Serial.println(".");
  }
  else if (inputY == LOW){
    Serial.print("FALSE");
    Serial.println(".");
  }
  else{
    Serial.print("ERROR");
    Serial.println(".");
  }
  Serial.print("Current TravelX RAM: ");
  Serial.print(TravelX);
  Serial.println(".");
  Serial.print("Current TravelY RAM: ");
  Serial.print(TravelY);
  Serial.println(".");
  
  
}


void needle_step(){
  spd2 = 50;
      analogWrite(nedRPWN, 0);
      analogWrite(nedLPWN, spd2);
      delay(nedstep);
      analogWrite(nedLPWN, 0);
}

void needle_retract(){
  analogWrite(nedRPWN,spd2);
  analogWrite(nedLPWN,0);
}

void arm_extend(){
  analogWrite(armRPWN,0);
  analogWrite(armLPWN, spd);
}

void arm_retract(){
  spd2 = 150;
      analogWrite(armRPWN, spd);
      analogWrite(armLPWN, 0);
}




void Calibrate(){
  //Setting start speeds for calibration
  motorX.setMaxSpeed(1000.0);
  motorX.setAcceleration(500.0);

  Serial.println("Stepper is Calibrating. . . . . . . . . . . . . .");
  Serial.println("Starting X axis Calibration");
//MotorX Calibration
//MotorX moves to limit swich until limit switch is depressed
  while (digitalRead(home_switchX) || timex == 250){
    motorX.moveTo(initial_homingX);
    initial_homingX--;
    motorX.run();
    timex++;
    delay(1);
    
  }

//MotorX Sets Current Position as zero when clicking the limit switch

 
  motorX.setCurrentPosition(0);
  Serial.println("Set First X Position");
 
//Moves MotorX 250 points away to clear limit Switch
  PosX = motorX.currentPosition();
  while (PosX < 250){
    motorX.moveTo(250);
    PosX = motorX.currentPosition();
    motorX.run();
    delay(5);
  }
  Serial.println("Current X Pos");
  Serial.println(PosX);

//Sets Speed and Acceleration of MotorX to normal speed and waits 2 seconds
  motorX.setMaxSpeed(mspeed);
  motorX.setAcceleration(macel);
  delay(2000);

//Moves MotorX to Other  side of the rail to make sure calibration is correct and waits 1 second
  while (PosX < 32000) {
    motorX.moveTo(32000);
    PosX = motorX.currentPosition();
    motorX.run();
  }
  Serial.print("Set Second X Position = ");
  Serial.println(PosX);
  delay(1000);

//Moves MotorX to Center
  while (PosX > 16000) {
    motorX.moveTo(16000);
    PosX = motorX.currentPosition();
    motorX.run();
    
  }
  Serial.println("Current X  Pos");
  Serial.println(PosX);



//MotorY Calibration





  Serial.println("Starting Y axis Calibration");
delay(4000);

//Setting start speeds for calibration
  motorY.setMaxSpeed(1000.0);
  motorY.setAcceleration(500.0);


//MotorY moves to limit swich until limit switch is depressed
  while (digitalRead(home_switchY)){
    motorY.moveTo(initial_homingY);
    initial_homingY--;
    motorY.run();
    delay(1);

  }
//MotorY Sets Current Position as zero when clicking the limit switch
  motorY.setCurrentPosition(0);
  Serial.println("Set First Y Position");

//Moves MotorY 250 points away to clear limit Switch
  PosY = motorY.currentPosition();
  while (PosY < 250){
    motorY.moveTo(250);
    PosY = motorY.currentPosition();
    motorY.run();
    delay(5);
  }
  Serial.println("Current Y Pos");
  Serial.println(PosY);

//Sets Speed and Acceleration of MotorY to normal speed and waits 2 seconds
  motorY.setMaxSpeed(mspeed);
  motorY.setAcceleration(macel);

//Moves MotorY to Other  side of the rail to make sure calibration is correct and waits 1 second
  while (PosY < 32000) {
    motorY.moveTo(32000);
    PosY = motorY.currentPosition();
    motorY.run();
  }
  Serial.print("Set Second Y Position = ");
  Serial.println(PosY);
  delay(1000);

//Moves MotorY to Center
  while (PosY > 16000) {
    motorY.moveTo(16000);
    PosY = motorY.currentPosition();
    motorY.run();
    
  }
  Serial.println("Current Y Pos");
  Serial.println(PosY);
  Serial.println(".....Setup Complete......");
}
