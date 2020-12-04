package pl.poznan.put.gol.game;

public class ConwaysRules implements Rules {

	@Override
	public boolean inNextGeneration(boolean alive, int numberOfNeighbors) { //implemented
		if((numberOfNeighbors > 3 || numberOfNeighbors < 2) && alive)
			return false;
		else return numberOfNeighbors == 3 || alive;
	}
}
