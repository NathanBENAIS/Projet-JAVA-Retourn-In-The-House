class Obj_meteorite {

  float xm  ;
  float ym ;
  float speed_x;
  float speed_y;
  int rayon;
  color coulm;


 
  Obj_meteorite(float _x, float _y, float _speed_x, float _speed_y, int _rayon) {
    xm = _x;
    ym = _y;
    speed_x = _speed_x*0.3; 
    speed_y = _speed_y*0.3;
    rayon = _rayon;
    coulm = color(48, 48, 48);
  } 

  
  void Gravite() {
    speed_y += 0.005;
  }

  void MoveM() {
    xm = xm + speed_x;
    ym = ym + speed_y; 
 
  }

  void Rebond() {

    if ((xm > width-rayon) || (xm < rayon)) speed_x = - (speed_x);
  }

  int Relance(int score) {
    if (ym > height+rayon)
    {
      ym = rayon;  
      score++;
    }
    return score;
  }

  void DessineM() {
    fill(coulm);
    ellipse (xm, ym, 2*rayon, 2*rayon);
  }

  boolean ToucheVaisseau_B() {

    return sqrt((xm- Vai.xv)*(xm- Vai.xv)+(ym- Vai.yv)*(ym-Vai.yv)) <=  (Vai.rayon + rayon); 
  }
  boolean ToucheVaisseau_R() {

    return ((xm+rayon >= ( Vai.x_rect- Vai.largeur_rect)) && (xm-rayon <= ( Vai.x_rect+ Vai.largeur_rect/2)) && (ym+ rayon >= (Vai.y_rect-Vai.Hauteur_rect/2)) && (ym-rayon <= ( Vai.y_rect+ Vai.Hauteur_rect/2)));    
  }

  
}