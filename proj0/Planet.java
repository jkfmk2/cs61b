public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67E-11; 

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double xx = Math.pow(xxPos - p.xxPos, 2);
        double yy = Math.pow(yyPos - p.yyPos, 2);
        return Math.sqrt(xx + yy);
    }

    public double calcForceExertedBy(Planet p) {
        return G * mass * p.mass / Math.pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double res = 0;
        for (int i = 0; i < p.length; i+=1) {
            if (this.equals(p[i]) == false) {
                res += this.calcForceExertedByX(p[i]);
            }
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double res = 0;
        for (int i = 0; i < p.length; i+=1) {
            if (this.equals(p[i]) == false) {
                res += this.calcForceExertedByY(p[i]);
            }
        }
        return res;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel; 
    }

    public void draw() {
        String imgStarfield = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgStarfield);
    }
}