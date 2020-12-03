package pl.poznan.put.gol.game;

public interface Rules {

	public boolean inNextGeneration(boolean alive, int numberOfNeighbors);
}
