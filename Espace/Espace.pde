Obj_Vaisseau Vai ;  //<>//
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



void setup() {
  size(1024, 615);
  Img= loadImage("EspaceM.jpg");
  Img2=loadImage("VaisseauM.png");  
  X_v = mouseX ; 
  Y_v = height/1.2;            
  rayonv = 20;  
  speedv_X = speedv_Y = 500;                                                    

 Vai = new Obj_Vaisseau ( X_v, Y_v, speedv_X, speedv_Y, rayonv);                
  for (int i=0; i<Meteo.length; i++) {
    Meteo[i] = new Obj_meteorite(random(20, width-20), random(0, 4.0*height/5.0), random(5, 15), random(5, 15), int(random(30, 100))) ; 
  }
}


void draw() {
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

void score() {
  fill(255);
  text(score, 512, 50);
  textSize(30);
}


void gameMenu() {
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