public class Position {

	double distanceFromOrigin;
	
	Position(float xPosition, float yPosition) {
		this.distanceFromOrigin = Math.sqrt(xPosition*xPosition+yPosition*yPosition);	
	}
}