public class Planet{
	public double xxPos; //its current x position
	public double yyPos; //its current y position
	public double xxVel; //its current velocity in the x dir
	public double yyVel; //Its current velocity in the y direction
	public double mass;
	public String imgFileName;

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
}