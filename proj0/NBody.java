public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        in.readDouble();
        
        Planet[] planets = new Planet[n];
        for (int i= 0; i < n; i+=1) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, name);
        }

        return planets;
    }

    public static void drawStarfield() {
        String imgStarfield = "images/starfield.jpg";
        StdDraw.picture(0, 0, imgStarfield);
    }

    public static void drawPlanets(Planet[] planets) {
        for (Planet p : planets) {
            p.draw();
        }
    }

    public static void main(String[] args) {
        
        // Read planets data from txt file
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        int t = 0;
        while (t < T) {
            for (int i = 0; i < planets.length; i+=1) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i+=1) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
        
            StdDraw.clear();
            // Draw background
            drawStarfield();
            
            // Draw planets
            drawPlanets(planets);
    
            StdDraw.show();
            StdDraw.pause(20);

            t += dt;
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