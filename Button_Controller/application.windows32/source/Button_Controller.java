import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 
import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Button_Controller extends PApplet {






Serial port;

ControlP5 cp5;

public void setup(){
 
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
  
 public void draw(){
   background(150,10,10); 
  }
 
 public void Extend(){
  port.write('O'); 
 }
 public void Retract(){
   port.write('c');
 }
 public void Push(){
   port.write('o');
 }
 public void Stop(){
   port.write('s');
 }
  public void settings() {  size( 800,480) ; }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Button_Controller" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
