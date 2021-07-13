

import controlP5.*;
import processing.serial.*;

Serial port;

ControlP5 cp5;

void setup(){
 size( 800,480) ;
 String portName = Serial.list()[1];
 port = new Serial(this, portName, 9600);
 
 cp5 = new ControlP5(this);
 
 cp5.addButton("Extend")
   .setPosition(100,100)
   .setSize(120,70)
   ;
 cp5.addButton("Retract")
   .setPosition(300,100)
   .setSize(120,70)
   ;
   cp5.addButton("Push")
   .setPosition(100,300)
   .setSize(120,70)
   ;
   cp5.addButton("Stop")
   .setPosition(300,300)
   .setSize(120,70)
   ;
   
}
  
 void draw(){
   background(150,10,10); 
  }
 
 void Extend(){
  port.write('O'); 
 }
 void Retract(){
   port.write('c');
 }
 void Push(){
   port.write('o');
 }
 void Stop(){
   port.write('s');
 }
