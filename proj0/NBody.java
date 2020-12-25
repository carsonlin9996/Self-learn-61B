public class NBody{

	public static double readRadius(String planetInfo){
		In readinfo = new In(planetInfo);

		int numberOfPlanets = readinfo.readInt();

		double radius = readinfo.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String planetInfo){

		In readinfo = new In(planetInfo);
		/* reads the number of planets from line 1*/
		int numberOfPlanets = readinfo.readInt();

		/*reads the radius of the universe*/
		double radius = readinfo.readDouble();

		/*Creates an Planet array that holds numberOfPlanets*/
		Planet [] allPlanets = new Planet[numberOfPlanets];

		int i = 0;
		while(i < numberOfPlanets){

			double positionX = readinfo.readDouble();
			double positionY = readinfo.readDouble();
			double velocityX = readinfo.readDouble();
			double velocityY = readinfo.readDouble();
			double mass = readinfo.readDouble();
			String img = readinfo.readString();

			allPlanets[i] = new Planet(positionX, positionY, velocityX, velocityY, mass, img);
			
			i++;

		}
		return allPlanets;

	}

	public static void main(String[] args){

		String backgroundimg = "images/starfield.jpg"; 

		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);

		String filename = args[2];

		double radius = readRadius(filename);

		Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-radius, radius);

		StdDraw.picture(0, 0, backgroundimg );

		int n = planets.length;	

		/*for(int i = 0; i < n; i++ ){

			planets[i].draw();

		}*/
		

		for( Planet i : planets){

			i.draw();
		}

		StdDraw.enableDoubleBuffering();

		for(double t = 0; t < T; t+=dt){

			double [] xForces = new double[n];
			double [] yForces = new double[n];

			for(int j = 0; j < n; j++){
				
				xForces[j] = planets[j].calcNetForceExertedByX(planets);
				yForces[j] = planets[j].calcNetForceExertedByY(planets);
			}
			
			for(int k = 0; k < n; k++){

				planets[k].update(dt, xForces[k], yForces[k]);	
			}

			StdDraw.picture(0, 0, backgroundimg );

			for( Planet l : planets){
				l.draw();
				
				
			}

			StdDraw.show();
			StdDraw.pause(10);
		}


	}

}