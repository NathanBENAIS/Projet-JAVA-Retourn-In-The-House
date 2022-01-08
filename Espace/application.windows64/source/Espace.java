import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Espace extends PApplet {

Obj_Vaisseau Vai ; 
Obj_meteorite[] Meteo  = new Obj_meteorite[4]; 
PImage Img;
PImage Img2;
int i;
float speedv_X, speedv_Y;
float speedm_X, speedm_Y  ;
float X_v, Y_v;
float X_m, Y_m ;
int rayonv;
int rayonm;
int score;
float ym;
int rayon;
boolean game = true ;



public void setup() {
  
  Img= loadImage("EspaceM.jpg");
  Img2=loadImage("VaisseauM.png");  
  X_v = mouseX ; 
  Y_v = height/1.2f;            
  rayonv = 20;  
  speedv_X = speedv_Y = 500;                                                    

 Vai = new Obj_Vaisseau ( X_v, Y_v, speedv_X, speedv_Y, rayonv);                
  for (int i=0; i<Meteo.length; i++) {
    Meteo[i] = new Obj_meteorite(random(20, width-20), random(0, 4.0f*height/5.0f), random(5, 15), random(5, 15), PApplet.parseInt(random(30, 100))) ; 
  }
}


public void draw() {
  background (Img); 
  score() ;
  gameMenu();
  Vai.DessineV_Cercle();                                         
  Vai.DessineV_Rect();
  Vai.MoveV();      


  for (int i=0; i<Meteo.length; i++) 
  {

    Meteo[i].DessineM();  
    Meteo[i].MoveM();
    Meteo[i].Gravite();
    Meteo[i].Rebond() ;

    score = Meteo[i].Relance(score); 

  
    if (Meteo[i].ToucheVaisseau_B() || Meteo[i].ToucheVaisseau_R()) {
     
      game = false;      
     
    }

    gameMenu();
  }
}

public void score() {
  fill(255);
  text(score, 512, 50);
  textSize(30);
}


public void gameMenu() {
  if (game==false) {
    score = 0;
    background(3, 34, 76);
    fill(255);
    textSize(32);
    textAlign(CENTER, CENTER);
    text("GO TO HOUSE", width/ 2, height/2);
    if ( mousePressed == true) {
      if ( mouseButton == LEFT ) {
        if ( mouseX>= width / 2 - 100 && mouseX <= width /2 + 100&& mouseY >= height / 2 - 56 && mouseY <= height / 2+ 50 ) game = true ;
      }
    }
  }
}
class Obj_Vaisseau {

  float xv ;
  float yv ;
  float speed_x;
  float speed_y ;
  int rayon;
  int coulv;
  float x_rect;
  float y_rect =520;
  float largeur_rect= 40 ;
  float Hauteur_rect= 7;



  Obj_Vaisseau(float _x, float _y, float _spX, float _spY, int _rayon) {
    xv = _x;
    yv = _y;
    speed_x = _spX;
    speed_y = _spY;
    coulv = color(96, 96, 96);
    rayon = _rayon;
  }


  public void MoveV() {
    if (mousePressed) {         
      xv = mouseX;                    
      yv = speed_y   ;                  


      x_rect = mouseX;
      y_rect   =  speed_y+10 ;
    }
  }

  public void DessineV_Cercle() {

    fill(coulv);
    ellipse (xv, yv, 2*rayon, 2*rayon)  ;
    imageMode(CENTER);
  }


  public void DessineV_Rect() {

    rectMode(RADIUS);
    rect(x_rect, y_rect, largeur_rect, Hauteur_rect);

  image(Img2, xv, yv, 95, 60);
  }
}
class Obj_meteorite {

  float xm  ;
  float ym ;
  float speed_x;
  float speed_y;
  int rayon;
  int coulm;


 
  Obj_meteorite(float _x, float _y, float _speed_x, float _speed_y, int _rayon) {
    xm = _x;
    ym = _y;
    speed_x = _speed_x*0.3f; 
    speed_y = _speed_y*0.3f;
    rayon = _rayon;
    coulm = color(48, 48, 48);
  } 

  
  public void Gravite() {
    speed_y += 0.005f;
  }

  public void MoveM() {
    xm = xm + speed_x;
    ym = ym + speed_y; 
 
  }

  public void Rebond() {

    if ((xm > width-rayon) || (xm < rayon)) speed_x = - (speed_x);
  }

  public int Relance(int score) {
    if (ym > height+rayon)
    {
      ym = rayon;  
      score++;
    }
    return score;
  }

  public void DessineM() {
    fill(coulm);
    ellipse (xm, ym, 2*rayon, 2*rayon);
  }

  public boolean ToucheVaisseau_B() {

    return sqrt((xm- Vai.xv)*(xm- Vai.xv)+(ym- Vai.yv)*(ym-Vai.yv)) <=  (Vai.rayon + rayon); 
  }
  public boolean ToucheVaisseau_R() {

    return ((xm+rayon >= ( Vai.x_rect- Vai.largeur_rect)) && (xm-rayon <= ( Vai.x_rect+ Vai.largeur_rect/2)) && (ym+ rayon >= (Vai.y_rect-Vai.Hauteur_rect/2)) && (ym-rayon <= ( Vai.y_rect+ Vai.Hauteur_rect/2)));    
  }

  
}
  public void settings() {  size(1024, 615); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Espace" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
