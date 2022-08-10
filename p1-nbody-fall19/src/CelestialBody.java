

/**
 * Celestial Body class for NBody
 * @author ola
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;


	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		myXPos=xp;
		myYPos=yp;
		myXVel=xv;
		myYVel=yv;
		myMass=mass;
		myFileName=filename;	}


	public CelestialBody(CelestialBody b){
		myXPos = b.myXPos;
		myYPos=b.myYPos;
		myXVel=b.myXVel;
		myYVel=b.myYVel;
		myMass=b.myMass;
		myFileName=b.myFileName;	}

	public double getX() {
		return myXPos;
	}
	public double getY() {
		return myYPos;
	}
	public double getXVel() {
		return myXVel;
	}

	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	public String getName() {
		return myFileName;
	}


	public double calcDistance(CelestialBody b) {
		double distance =0;
		double DeltaX = myXPos-b.myXPos;
		double DeltaY = myYPos-b.myYPos;
		distance= Math.sqrt(Math.pow(DeltaX,2)+Math.pow(DeltaY,2));
		return distance;
	}

	public double calcForceExertedBy(CelestialBody b) {
		double G = 6.67*1e-11;
		double force = G*((myMass*b.myMass)/(Math.pow(calcDistance(b),2)));
		return force;
	}

	public double calcForceExertedByX(CelestialBody b) {
		double DeltaX = b.myXPos-myXPos;
		double xExerted = (calcForceExertedBy(b)*DeltaX)/calcDistance(b);
		return xExerted;
	}
	public double calcForceExertedByY(CelestialBody b) {
		double DeltaY = b.myYPos-myYPos;
		double yExerted = (calcForceExertedBy(b)*DeltaY)/calcDistance(b);
		return yExerted;
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double netForceX=0;
		for(CelestialBody b: bodies){
			if(!b.equals(this)){
				netForceX+=calcForceExertedByX(b);
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double netForceY=0;
		for(CelestialBody b: bodies){
			if(!b.equals(this)){
				netForceY+=calcForceExertedByY(b);
			}
		}
		return netForceY;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos=nx;
		myYPos=ny;
		myXVel=nvx;
		myYVel=nvy;
	}

	public void draw() {
		// TODO: complete method
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
