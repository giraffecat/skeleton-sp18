public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double r = Math.sqrt(dx*dx+dy*dy);
        return r;
    }

    public double calcForceExertedBy(Planet p){
        double r = this.calcDistance(p);
        double force = G*this.mass*p.mass/(r*r);
        return force;
    }

    public double calcForceExertedByX(Planet p){
        double r = this.calcDistance(p);
        double force = this.calcForceExertedBy(p);
        double Fx = -force*(this.xxPos - p.xxPos)/r;
        return Fx;
    }

    public double calcForceExertedByY(Planet p){
        double r = this.calcDistance(p);
        double force = this.calcForceExertedBy(p);
        double Fy = -force*(this.yyPos - p.yyPos)/r;
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double NetFx = 0;
        for(Planet planet : planets){
            if(planet != this){
                NetFx += this.calcForceExertedByX(planet);
            }
        }
        return NetFx;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double NetFy = 0;
        for(Planet planet : planets){
            if(planet != this){
                NetFy += this.calcForceExertedByY(planet);
            }
        }
        return NetFy;
    }

    public void update(double time, double ForceX, double ForceY){
        double ax = ForceX / this.mass;
        double ay = ForceY / this.mass;
        this.xxVel = this.xxVel + time * ax;
        this.yyVel = this.yyVel + time * ay;

        this.xxPos = this.xxPos + time * this.xxVel;
        this.yyPos = this.yyPos + time * this.yyVel;
    }

    public void draw(){};
}
