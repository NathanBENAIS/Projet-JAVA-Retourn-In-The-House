class Obj_Vaisseau {

  float xv ;
  float yv ;
  float speed_x;
  float speed_y ;
  int rayon;
  color coulv;
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


  void MoveV() {
    if (mousePressed) {         
      xv = mouseX;                    
      yv = speed_y   ;                  


      x_rect = mouseX;
      y_rect   =  speed_y+10 ;
    }
  }

  void DessineV_Cercle() {

    fill(coulv);
    ellipse (xv, yv, 2*rayon, 2*rayon)  ;
    imageMode(CENTER);
  }


  void DessineV_Rect() {

    rectMode(RADIUS);
    rect(x_rect, y_rect, largeur_rect, Hauteur_rect);

  image(Img2, xv, yv, 95, 60);
  }
}