package test1.servlets;

/**
 * @author EricBruneau
 * 
 * Classe correspondant à la qualité des accords
 * m7,M7,...
 *
 */
public class Quality {
	private String quality;

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String toString()
	{
		return this.quality;
	}

	public Quality(String quality) {
		super();
		this.quality = quality;
	}
	
}
