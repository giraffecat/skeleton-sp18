public class NBody {

    public static double readRadius(String file){
        In in = new In(file);
        int PlanetNum = in.readInt();
        double Radius = in.readDouble();
        return  Radius;
    }

    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int PlanetNum = in.readInt();
        double Radius = in.readDouble();
        Planet[] planets = new Planet[5];
        for(int i=0; i<PlanetNum; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel,mass,imgFileName);
            planets[i] = p;
         }
        return planets;
    }

    public static void main(String args[]){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets("data/planets.txt");
        double radius = readRadius(filename);
        double[] xForces = new double[5];
        double[] yForces = new double[5];
        StdDraw.setScale(-radius,radius);
        StdDraw.picture(0,0,"images/starfield.jpg");
        StdDraw.enableDoubleBuffering();

        for( double Time = 0; Time<=T; Time+=dt){
            StdDraw.clear();
            for(int i=0; i<planets.length;i++ ){
                Planet planet  = planets[i];
                xForces[i] = planet.calcNetForceExertedByX(planets);
                yForces[i] = planet.calcNetForceExertedByY(planets);
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet planet : planets) StdDraw.picture(planet.xxPos, planet.yyPos, "images/" + planet.imgFileName);
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}
