import javax.swing.text.PlainDocument;

public class NBody {
    public static double readRadius(String txt) {
        In in = new In(txt);
        double radius=in.readDouble();
        radius=in.readDouble();
        
        return radius;
    }
    public static Planet[] readPlanets(String txt) {
        In in = new In(txt);
        int planetNum=in.readInt();
        Planet[] planets= new Planet[planetNum];
        in.readDouble();//This is for skiping the radius part on the txt files.
        for (int i = 0; i < planetNum; i++) {
            double xC=in.readDouble();
            double yC=in.readDouble();
            double xV=in.readDouble();
            double yV=in.readDouble();
            double m=in.readDouble();
            String img=in.readString();

            planets[i]=new Planet(xC,yC,xV,yV,m,img);
        }

        return planets;
    }
    public static void draw(Planet[] planets, Double rad){
        StdDraw.picture(0, 0, "/images/starfield.jpg", rad*2, rad*2);
            for (int i = 0; i < planets.length; i++) {
                StdDraw.picture(planets[i].xxPos, planets[i].yyPos, "/images/"+planets[i].imgFileName);
            }
    }
    public static void main(String[] args) {
        //read time varibles.
        Double T=Double.parseDouble(args[0]);
        Double dt=Double.parseDouble(args[1]);
        //read text file name.
        String filename=args[2];
        //gather the text and radius.
        Planet[] planets=readPlanets(filename);
        double rad=readRadius(filename);
        StdDraw.clear();
        StdDraw.setScale(-rad,rad);
        StdDraw.picture(0, 0, "/images/starfield.jpg", rad*2, rad*2);
        for (int i = 0; i < planets.length; i++) {
            StdDraw.picture(planets[i].xxPos, planets[i].yyPos, "/images/"+planets[i].imgFileName);
        }
        StdDraw.enableDoubleBuffering();
        double time=0;
        Double[] xForces=new Double[planets.length];
        Double[] yForces=new Double[planets.length];
        while(time<=T){
            xForces=new Double[planets.length];
            yForces=new Double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            draw(planets,rad);
            StdDraw.show();
            StdDraw.pause(10);
			
            time=time+dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", rad);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }

    }
}
