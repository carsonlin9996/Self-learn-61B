public class Planet{
	public double xxPos; //its current x position
	public double yyPos; //its current y position
	public double xxVel; //its current velocity in the x dir
	public double yyVel; //Its current velocity in the y direction
	public double mass;
	public String imgFileName;
	private static final double constantG = 6.67e-11;

	/*constructor
	Initialize an instance of the Planet class */

	public Planet(double xP, double yP, double xV,
				 double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	/* Take Planet object and initialize an identical Planet object */
	public Planet(Planet p){

		this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName); //This line can be replaced by below
		
		/*
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;*/

	}

	public double calcDistance(Planet p){

		double dX = this.xxPos - p.xxPos;
		double dY = this.yyPos - p.yyPos;

		double distance = Math.sqrt(dX*dX + dY*dY); 

		return distance;
	}

	public double calcForceExertedBy(Planet p){
		//double distance = this.calcDistance(p);
		double force = constantG * this.mass * p.mass/(this.calcDistance(p)*this.calcDistance(p));

		//double force = constantG*((this.mass)*(p.mass)) / (this.calcDistance(p)*this.calcDistance(p));*/
		return force;
		//System.out.print(distance);
	}

	public double calcForceExertedByX(Planet p){
		double forceX = this.calcForceExertedBy(p)*(p.xxPos - this.xxPos)/this.calcDistance(p);

		return forceX;


	}

	public double calcForceExertedByY(Planet p){
		double forceY = this.calcForceExertedBy(p)*(p.yyPos - this.yyPos)/this.calcDistance(p);

		return forceY;
	}


	public double calcNetForceExertedByX(Planet[] all){
		double netForceX = 0;

			for(Planet a : all){
				//netForceX = this.calcForceExertedBy(a) + netForceX;

				if (this.equal(a) == true){
					continue;
				}
				
			netForceX = this.calcForceExertedByX(a) + netForceX;
				//if (this.equal(a) == true)
				//	continue;
			}
		return netForceX;

	}

	public double calcNetForceExertedByY(Planet[] all){

		double netForceY = 0;
			for(Planet a : all){
				

				if (this.equal(a) == true){
					continue;
				}
			
			netForceY = this.calcForceExertedByY(a) + netForceY;
				//if (this.equal(a) == true)
				//	continue;
			}
		return netForceY;
	}

	private boolean equal(Planet p){
		if((this.xxPos == p.xxPos) && (this.yyPos == p.yyPos) && (this.mass == p.mass))
			{return true;}

		return false;

	}

	public void update(double dt, double Fx, double Fy){
		
		double accelerationX = Fx/this.mass;
		double accelerationY = Fy/this.mass;

		this.xxVel = this.xxVel + accelerationX * dt;
		this.yyVel = this.yyVel + accelerationY * dt;
		this.xxPos = this.xxPos + this.xxVel*dt;
		this.yyPos = this.yyPos + this.yyVel*dt;


	}
/*draws this planet */
	public void draw(){

		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName );
	}


}