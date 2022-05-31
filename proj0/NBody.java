import java.util.Scanner;

public class NBody {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1.Read the data of planets
        double T = scanner.nextDouble();
        double dt = scanner.nextDouble();
        String filename = scanner.next();
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // 2.Draw the backgroud and start
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        for (Planet planet : planets) {
            planet.draw();
        }

        StdDraw.show();
        StdDraw.pause(10);

        // 3.Create the animation
        StdDraw.enableDoubleBuffering();
        for (double time = 0; time < T; time += dt) {
            int count = readCount(filename);
            double[] xForces = new double[count];
            double[] yForces = new double[count];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int j = 0; j < planets.length; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        // 4.Print the Universe
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

    /** Get the radius of the univers */
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** Get the array of the planets in the universe */
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int count = in.readInt();
        in.readDouble();

        Planet[] planets = new Planet[count];
        for (int i = 0; i < count; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }

        return planets;
    }

    private static int readCount(String filename) {
        In in = new In(filename);
        int count = in.readInt();
        return count;
    }
}
