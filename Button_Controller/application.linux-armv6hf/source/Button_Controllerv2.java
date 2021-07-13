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

public class Button_Controllerv2 extends PApplet {






Serial port;

ControlP5 cp5;



//define color
int red = color(255,0,0);
int green = color(0,186,0);
int blue = color(0,0,217);
int orange = color(255,153,0);
int yellow = color(246,255,0);
int teal = color(0,242,255);
int purple = color(209,0,206);
int white = color(255);
int black = color(0);

//define color locations
public int bgcolor = black;
public int buttoncolor = green;
public int mocolor = orange;
public int actcolor = blue;
public int txtcolor = white;
public int highcolor;
public int Actcolor;

//define var
public int myColor = color(0,0,0);
public int BackGroundC = 100;
Slider abc;
public int textbright;
public int c;
public String command = "";
Textarea txt;
Println console;

public void setup(){
 
 noStroke();
String portName = Serial.list()[1];
port = new Serial(this, portName, 115200);

PFont p = createFont("Arial",10,true);
ControlFont font = new ControlFont(p,20);
ControlFont font2 = new ControlFont(p,15);
ControlFont font3 = new ControlFont(p,10);
 
 cp5 = new ControlP5(this);
 cp5.enableShortcuts();
 frameRate(50);
 
 cp5.addTab("Manual")
   .setColorBackground(buttoncolor)
  // .setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setId(2)
   .setHeight(50)
   .setWidth(75)
   .setLabel("Arm Manual")
   ;
   
   cp5.addTab("XY")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setHeight(50)
   .setWidth(75)
   .setLabel("XY Manual")
   ;
  
   
 cp5.getTab("default")
   .setLabel("GlueApp")
   .setColorBackground(buttoncolor)
   .setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setId(1)
   .setHeight(50)
   .setWidth(75)
   
    
   ;
 cp5.addTab("Settings")
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setId(3)
   .setHeight(50)
   .setWidth(75)
   ;
 
 Group num = cp5.addGroup("num")
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(550,140)
   .setBarHeight(20)
   .setSize(225,50)
   .setFont(font3)
   ;
   
   cp5.addBang("one")
   .setPosition(10,170)
   .setLabel("1")
   .setSize(50,50)
   .setGroup(num)
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setFont(font)
   ;
   
   cp5.addBang("two")
   .setLabel("2")
   .setPosition(85,170)
   .setSize(50,50)
   .setGroup(num)
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setFont(font)
   ;
   
   cp5.addBang("three")
   .setLabel("3")
   .setPosition(160,170)
   .setSize(50,50)
   .setGroup(num)
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setFont(font)
   ;
   cp5.addBang("four")
   .setLabel("4")
   .setPosition(10,85)
   .setSize(50,50)
   .setGroup(num)
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setFont(font)
   ;
   cp5.addBang("five")
   .setLabel("5")
   .setPosition(85,85)
   .setSize(50,50)
   .setGroup(num)
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setFont(font)
   ;
   cp5.addBang("six")
   .setLabel("6")
   .setPosition(160,85)
   .setSize(50,50)
   .setGroup(num)
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setFont(font)
   ;
   cp5.addBang("seven")
   .setLabel("7")
   .setPosition(10,5)
   .setSize(50,50)
   .setGroup(num)
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setFont(font)
   ;
   cp5.addBang("eight")
   .setLabel("8")
   .setPosition(85,5)
   .setSize(50,50)
   .setGroup(num)
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setFont(font)
   ;
   cp5.addBang("nine")
   .setLabel("9")
   .setPosition(160,5)
   .setSize(50,50)
   .setGroup(num)
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setFont(font)
   ;
   cp5.addBang("zero")
   .setLabel("0")
   .setPosition(85,255)
   .setSize(50,50)
   .setGroup(num)
   .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setFont(font)
   ;
   
 txt = cp5.addTextarea("Cmd")
   .setPosition(50,100)
   .setSize(200,200)
   .setFont(font3)
   .setLineHeight(14)
   .setColor(blue)
   .setColorBackground(white)
   .setColorForeground(red)
   ;
   
   console = cp5.addConsole(txt);
   
 
 
 cp5.addColorWheel("buttoncolor", 475, 250, 200)
   .registerProperty("value")
   .removeProperty("ArrayValue")
   .setRGB(green)
   .setFont(font3)
   .setLabel("Button Color")
   ;
 cp5.addColorWheel("highcolor", 250, 250, 200)
 .registerProperty("value")
   .removeProperty("ArrayValue")
   .setRGB(orange)
   .setFont(font3)
   .setLabel("Mouseover/Highlight Color")
   ;
 cp5.addColorWheel("Actcolor", 25, 250, 200)
 .registerProperty("value")
   .removeProperty("ArrayValue")
   .setRGB(blue)
   .setFont(font3)
   .setLabel("Active Color")
   ;
   
 cp5.addSlider("textbright")
   .setPosition(25, 100)
   .setRange(0,255)
   .setValue(255)
   .setSize(200,20)
   .setLabel("Text Brightness")
   .setFont(font3)
   ;

 
 cp5.addSlider("BackGroundC")
   .setPosition(25,150)
   .setRange(0,255)
   .setValue(0)
   .setSize(200,20)
   .setLabel("Background Brightness")
   .setFont(font3)
   ;
 
   
  
 
 cp5.addButton("Retract")
  .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(75,100)
   .setSize(170,70)
   .setFont(font2)
   .setId(5)
   .setLabel("Arm Retract")
   ;
 cp5.addButton("Extend")
 .setLabel("Arm Extend")
  .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(75,200)
   .setSize(170,70)
   .setFont(font2)
   .setId(5);
   ;
 cp5.addButton("Extend2")
  .setColorBackground(buttoncolor)
  // .setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(300,100)
   .setSize(170,70)
   .setLabel("Needle Extend")
   .setFont(font2)
   .setId(5);
   ;
 cp5.addButton("Retract2")
  .setColorBackground(buttoncolor)
   //.setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(300,200)
   .setSize(170,70)
   .setLabel("Needle Retract")
   .setFont(font2)
   .setId(5);
   ;
 cp5.addButton("ExtendFull")
  .setColorBackground(buttoncolor)
  .setLabel("Needle Extend Full")
  // .setColorLabel(txtcolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(300, 300)
   .setSize(170, 70)
   .setFont(font2)
   .setId(5);
   ;
   
   cp5.addButton("Stop")
   .setPosition(650,0)
   .setSize(150,100)
   .setColorBackground(color(255,89,89))
   .setColorActive(color(120,27,20))
   .setColorForeground(color(255,0,0))
   .setFont(font)
   
   ;
   
   cp5.addButton("X")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(300,100)
   .setSize(50,50)
   .setFont(font2)
   ;
   cp5.addButton("Y")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(360,100)
   .setSize(50,50)
   .setFont(font2)
   ;
   cp5.addButton("Center")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(420,100)
   .setSize(100,50)
   .setFont(font2)
   ;
   cp5.addButton("Calibrate")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(300,160)
   .setSize(100,50)
   .setFont(font2)
   ;
   cp5.addButton("XYSpeed")
   .setLabel("XY Speed")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(410,160)
   .setSize(100,50)
   .setFont(font2)
   ;
   cp5.addButton("XYAcel")
   .setLabel("XY Acel")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(300,220)
   .setSize(100,50)
   .setFont(font2)
   ;
   cp5.addButton("ArmSpeed")
   .setLabel("Arm Speed")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(410,220)
   .setSize(100,50)
   .setFont(font2)
   ;
   cp5.addButton("NeedleSpeed")
   .setLabel("Needle Speed")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(300,280)
   .setSize(125,50)
   .setFont(font2)
   ;
   cp5.addButton("Step")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(435,280)
   .setSize(100,50)
   .setFont(font2)
   ;
   cp5.addButton("Info")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(300,340)
   .setSize(100,50)
   .setFont(font2)
   ;
   
   cp5.addButton("Clear")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(300,400)
   .setSize(100,50)
   .setFont(font2)
   ;
   cp5.addButton("Confirm")
   .setColorBackground(buttoncolor)
   .setColorActive(actcolor)
   .setColorForeground(mocolor)
   .setPosition(410,340)
   .setSize(100,50)
   .setFont(font2)
   ;
   
   
   cp5.addButton("b1")
     .setPosition(400,50)
     .setSize(100,50)
     .setFont(font3)
     .setCaptionLabel("save slot 1")
     
     ;
   cp5.addButton("b2")
    .setPosition(510,50)
    .setSize(100,50)
    .setFont(font3)
    .setCaptionLabel("load slot 1")
     ;
   cp5.addButton("b3")
     .setPosition(400,110)
     .setSize(100,50)
     .setFont(font3)
     .setCaptionLabel("save default")
     ;
   cp5.addButton("b4")
     .setPosition(510,110)
     .setSize(100,50)
     .setFont(font3)
     .setCaptionLabel("load default")
     ;
 
 //Tabs  
  cp5.getController("Stop").moveTo("global");
  cp5.getController("Extend2").moveTo("Manual");
  
  cp5.getController("Retract2").moveTo("Manual");
  cp5.getController("ExtendFull").moveTo("Manual");
  cp5.getController("Extend").moveTo("Manual");
  cp5.getController("Retract").moveTo("Manual");
  cp5.getController("BackGroundC").moveTo("Settings");
  cp5.getController("buttoncolor").moveTo("Settings");
  cp5.getController("textbright").moveTo("Settings");
  cp5.getController("highcolor").moveTo("Settings");
  cp5.getController("Actcolor").moveTo("Settings");
  cp5.getController("b1").moveTo("Settings");
  cp5.getController("b2").moveTo("Settings");
  cp5.getController("b3").moveTo("Settings");
  cp5.getController("b4").moveTo("Settings");
  cp5.getGroup("num").moveTo("XY");
  cp5.getController("X").moveTo("XY");
  cp5.getController("Y").moveTo("XY");
  cp5.getController("Center").moveTo("XY");
  cp5.getController("Calibrate").moveTo("XY");
  cp5.getController("XYSpeed").moveTo("XY");
  cp5.getController("XYAcel").moveTo("XY");
  cp5.getController("ArmSpeed").moveTo("XY");
  cp5.getController("NeedleSpeed").moveTo("XY");
  cp5.getController("Step").moveTo("XY");
  cp5.getController("Info").moveTo("XY");
  cp5.getController("Confirm").moveTo("XY");
  cp5.getGroup("Cmd").moveTo("XY");
  cp5.getController("Clear").moveTo("XY");

  
 //Properties
  cp5.getProperties().addSet("slot1"); 
  
  cp5.getProperties().copy(cp5.getController("BackGroundC"), "default", "slot1");
  cp5.getProperties().copy(cp5.getController("textbright"), "default", "slot1");
  cp5.getProperties().copy(cp5.getController("buttoncolor"), "default", "slot1");
  cp5.getProperties().copy(cp5.getController("highcolor"), "default", "slot1");
  cp5.getProperties().copy(cp5.getController("Actcolor"), "default", "slot1");
  
 // cp5.getProperties().print();
 // ControlP5.printPublicMethodsFor(ControllerProperties.class);
 
}
  
 public void draw(){
   background(BackGroundC); 
   //Color Updates
   
   //Background
   cp5.getTab("default").setColorBackground(buttoncolor);
   cp5.getTab("Manual").setColorBackground(buttoncolor);
   cp5.getTab("Settings").setColorBackground(buttoncolor);
   cp5.getController("b1").setColorBackground(green);
   cp5.getController("b3").setColorBackground(green);
   cp5.getController("b2").setColorBackground(red);
   cp5.getController("b4").setColorBackground(red);
   
   //Text Color

   cp5.getTab("default").setColorLabel(color(textbright,textbright,textbright));
   cp5.getTab("Manual").setColorLabel(color(textbright,textbright,textbright));
   cp5.getTab("Settings").setColorLabel(color(textbright,textbright,textbright));
   
   
   //Mouse Over Color

   cp5.getTab("default").setColorForeground(highcolor);
   cp5.getTab("Manual").setColorForeground(highcolor);
   cp5.getTab("Settings").setColorForeground(highcolor);
   
   //Click Color

   cp5.getTab("default").setColorActive(Actcolor);
   cp5.getTab("Manual").setColorActive(Actcolor);
   cp5.getTab("Settings").setColorActive(Actcolor);
   
      
  }
 //Button Actions
 public void Clear(){
   command = "";
   println("...");
 }
 public void X(){
   command = command + "x";
   print(" X: ");
   
 }
 public void Y(){
   command = command + "y";
   print(" Y: ");
 }
 public void Center(){
   command = command + "t";
   println(" Center ");
 }
 public void XYSpeed(){
   command = command + "p";
   print(" XY Speed: ");
 }
 public void XYAcel(){
   command = command + "a";
   print(" XY Acel: ");
 }
 public void ArmSpeed(){
   command = command + "u";
   print(" Arm Speed: ");
 }
 public void NeedleSpeed(){
   command = command + "b";
   print(" Needle Speed: " );
 }
 public void Calibrate(){
   command = command + "C";
   println(" Calibrate ");
 }
 public void Info(){
   command = command + "i";
   println(" Info ");
 }
 public void Step(){
   command = command + "f";
   print(" Step: ");
 }
 
 public void Confirm(){
  port.write(command);
 
  print("... Running: ");
  println(command);
   command = "";
 }
 
 public void one(){
   command = command + "1";
   print("1");
 }
 public void two(){
   command = command + "2";
   print("2");
 }
 public void three(){
   command = command + "3";
   print("3");
 }
 public void four(){
   command = command + "4";
   print("4");
 }
 public void five(){
   command = command + "5";
   print("5");
 }
 public void six(){
   command = command + "6";
   print("6");
 }
 public void seven(){
   command = command + "7";
   print("7");
 }
 public void eight(){
   command = command + "8";
   print("8");
 }
 public void nine(){
   command = command + "9";
   print("9");
 }
 public void zero(){
   command = command + "0";
   print("0");
 }
 
 
 
 public void Extend(){
   port.write('o'); 
 }
 public void Retract(){
   port.write('c');
 }
 public void Extend2(){
   port.write('e');
 }
 public void Retract2(){
   port.write('r');
 }
 public void ExtendFull(){
   port.write('E');
 }
 public void Stop(){
   port.write('s');
 }
 public void b1() {
   cp5.saveProperties("slot1", "slot1");
 }
 public void b2() {
   cp5.loadProperties(("slot1"));
   ColorWheel cw = (ColorWheel) cp5.getController("buttoncolor");
   cw.setRGB((int) cw.getValue());
   buttoncolor = (int) cw.getValue();
   
   ColorWheel cw2 = (ColorWheel) cp5.getController("highcolor");
   cw2.setRGB((int) cw2.getValue());
   highcolor = (int) cw2.getValue();
   
   ColorWheel cw3 = (ColorWheel) cp5.getController("Actcolor");
   cw3.setRGB((int) cw3.getValue());
   Actcolor = (int) cw3.getValue();
 }
 public void b3() {
   cp5.saveProperties("default", "default");
 }
 public void b4() {
   cp5.loadProperties(("default.json"));
   ColorWheel cw = (ColorWheel) cp5.getController("buttoncolor");
   cw.setRGB((int) cw.getValue());
   buttoncolor = (int) cw.getValue();
   
   ColorWheel cw2 = (ColorWheel) cp5.getController("highcolor");
   cw2.setRGB((int) cw2.getValue());
   highcolor = (int) cw2.getValue();
   
   ColorWheel cw3 = (ColorWheel) cp5.getController("Actcolor");
   cw3.setRGB((int) cw3.getValue());
   Actcolor = (int) cw3.getValue();
 }

 
  public void settings() {  size( 800,480) ; }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Button_Controllerv2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
