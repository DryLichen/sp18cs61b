public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double g = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel,
                  double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet planet) {
        xxPos = planet.xxPos;
        yyPos = planet.yyPos;
        xxVel = planet.xxVel;
        yyVel = planet.yyVel;
        mass = planet.mass;
        imgFileName = planet.imgFileName;
    }

    public double calcDistance(Planet planet) {
        double distance = 0;
        double dx = planet.xxPos - xxPos;
        double dy = planet.yyPos - yyPos;
        distance = Math.sqrt(Math.pow(dx, 2)  + Math.pow(dy, 2));
        return distance;
    }

    public double calcForceExertedBy(Planet planet) {
        double f = 0;
        f = (g * this.mass * planet.mass) / Math.pow(calcDistance(planet), 2);
        return f;
    }

    public double calcForceExertedByX(Planet planet) {
        double fx = 0;
        double dx = planet.xxPos - this.xxPos;
        fx = calcForceExertedBy(planet) * dx / calcDistance(planet);
        return fx;
    }

    public double calcForceExertedByY(Planet planet) {
        double fy = 0;
        double dy = planet.yyPos - this.yyPos;
        fy = calcForceExertedBy(planet) * dy / calcDistance(planet);
        return fy;
    }

    /** Get the net force exerted on a planet at the horizontal direction (include itself) */
    public double calcNetForceExertedByX(Planet... planets) {
        double nfx = 0;
        for(int i = 0; i < planets.length; i++) {
            if (planets[i].xxPos == xxPos && planets[i].yyPos == yyPos) {
                continue;
            }
            nfx += calcForceExertedByX(planets[i]);
        }
        return nfx;
    }

    public double calcNetForceExertedByY(Planet... planets) {
        double nfy = 0;
        for(int i = 0; i < planets.length; i++) {
            if (planets[i].xxPos == xxPos && planets[i].yyPos == yyPos) {
                continue;
            }
            nfy += calcForceExertedByY(planets[i]);
        }
        return nfy;
    }

    /** Move the planet */
    public void update(double dt, double fx, double fy) {
        double ax = fx / mass, bx = fy / mass;
        xxVel += ax * dt;
        yyVel += bx * dt;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    /** draw the planet on screen */
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}
