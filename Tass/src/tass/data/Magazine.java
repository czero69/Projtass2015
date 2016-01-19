package tass.data;

public class Magazine {

		private String magazineName;
		private double impactFactor;
		
		public Magazine(String magazineName, double impactFactor){
			this.magazineName = magazineName;
			this.impactFactor = impactFactor;
		}
		
		public String getMagazineName() {
			return magazineName;
		}
		public void setMagazineName(String name) {
			this.magazineName = name;
		}
		public double getImpactFactor() {
			return impactFactor;
		}
		public void setImpactFactor(double impactFactor) {
			this.impactFactor = impactFactor;
		}
		
}
