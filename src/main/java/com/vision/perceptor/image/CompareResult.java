package com.vision.perceptor.image;

public class CompareResult {
	
	private static final double [][] STATUS = {
		{1.0, 0.7, -1.0},
		{0.0, 0.67, 2.0},
		{1.0, 0.5, 0.0},
		{0.0, 0.55, 1.0}
	};

	private double corelation;
	private double chiSquare;
	private double intersection;
	private double bhattaccharyya;
	
	public int calcCorelation() {
		if( corelation >= STATUS[0][0] ) {
			return 1;
		}		
		if( corelation > STATUS[0][1]) {
			return 0;
		}		
		if( corelation > STATUS[0][2]) {
			return -1;
		}
		return -2;
	}
	
	public int calcChiSquare() {
		if( chiSquare <= STATUS[1][0] ) {
			return 1;
		}		
		if( chiSquare < STATUS[1][1]) {
			return 0;
		}		
		if( chiSquare < STATUS[1][2]) {
			return -1;
		}
		return -2;
	}
	
	public int calcIntersection() {
		if( intersection >= STATUS[2][0] ) {
			return 1;
		}		
		if( intersection > STATUS[2][1]) {
			return 0;
		}		
		if( intersection > STATUS[2][2]) {
			return -1;
		}
		return -2;
	}
	
	public int calcBhattaccharyya() {
		if( bhattaccharyya <= STATUS[3][0] ) {
			return 1;
		}		
		if( bhattaccharyya < STATUS[3][1]) {
			return 0;
		}		
		if( bhattaccharyya < STATUS[3][2]) {
			return -1;
		}
		return -2;
	}
	
	/**
	 * @return the corelation
	 */
	public double getCorelation() {
		return corelation;
	}
	/**
	 * @param corelation the corelation to set
	 */
	public void setCorelation(double corelation) {
		this.corelation = corelation;
	}
	/**
	 * @return the chiSquare
	 */
	public double getChiSquare() {
		return chiSquare;
	}
	/**
	 * @param chiSquare the chiSquare to set
	 */
	public void setChiSquare(double chiSquare) {
		this.chiSquare = chiSquare;
	}
	/**
	 * @return the intersection
	 */
	public double getIntersection() {
		return intersection;
	}
	/**
	 * @param intersection the intersection to set
	 */
	public void setIntersection(double intersection) {
		this.intersection = intersection;
	}
	/**
	 * @return the bhattaccharyya
	 */
	public double getBhattaccharyya() {
		return bhattaccharyya;
	}
	/**
	 * @param bhattaccharyya the bhattaccharyya to set
	 */
	public void setBhattaccharyya(double bhattaccharyya) {
		this.bhattaccharyya = bhattaccharyya;
	}
	
	
	
}
