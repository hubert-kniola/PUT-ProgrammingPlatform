package pl.poznan.put.gol.game;

public class ConwaysRules implements Rules {

	@Override
	public boolean inNextGeneration(boolean alive, int numberOfNeighbors) { //implemented
		if (alive) {
			return numberOfNeighbors == 2 || numberOfNeighbors == 3;
		}
		return numberOfNeighbors == 3;
	}
}
