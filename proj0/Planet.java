public class Planet{
    //Initialize the x,y positions,velocity,mass and image of a Planet
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G=6.67*Math.pow(10,-11);

    //Simple constructors
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet p){
        this.xxPos=p.xxPos;
        this.yyPos=p.yyPos;
        this.xxVel=p.xxVel;
        this.yyVel=p.yyVel;
        this.mass=p.mass;
        this.imgFileName=p.imgFileName;
    }
    //Calculate the distance between planets.
    public  double calcDistance(Planet p){
        double xDist=this.xxPos-p.xxPos;
        double yDist=this.yyPos-p.yyPos;
        return Math.sqrt((xDist*xDist)+(yDist*yDist));
    }
    //Calculate force extrected by given planet
    public double calcForceExertedBy(Planet p){
        double dist=calcDistance(p);
        return (G*this.mass*p.mass)/(dist*dist);
    }
    public double calcForceExertedByX(Planet p) {
        return (calcForceExertedBy(p)*(p.xxPos-this.xxPos))/calcDistance(p);
        
    }
    public double calcForceExertedByY(Planet p) {
        
        return (calcForceExertedBy(p)*(p.yyPos-this.yyPos))/calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceExertedByX=0;
        for(int i=0;i<planets.length;i++){
            if(!this.equals(planets[i])){
                netForceExertedByX=netForceExertedByX+calcForceExertedByX(planets[i]);
            } 
        }
        return netForceExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceExertedByY=0.0;
        for(int i=0;i<planets.length;i++){
            if(!this.equals(planets[i])){
                netForceExertedByY=netForceExertedByY+calcForceExertedByY(planets[i]);
            } 
        }
        return netForceExertedByY;
    }
    

    public void update(double dt, double fX, double fY){
        double accelerationX=fX/this.mass;
        double accelerationY=fY/this.mass;

        this.xxVel=xxVel+(dt*accelerationX);
        this.yyVel=yyVel+(dt*accelerationY);

        this.xxPos=this.xxPos+(dt*this.xxVel);
        this.yyPos=this.yyPos+(dt*this.yyVel);
    }
}
