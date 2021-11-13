package ar.com.campeonatos.demo;

import java.io.Serializable;
import java.util.List;

public class ZonaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6949013974526849114L;
	private List<List<Integer>> clubes;
	
	public ZonaVO() {}

	public List<List<Integer>> getClubes() {
		return clubes;
	}

	public void setClubes(List<List<Integer>> clubes) {
		this.clubes = clubes;
	}
}
