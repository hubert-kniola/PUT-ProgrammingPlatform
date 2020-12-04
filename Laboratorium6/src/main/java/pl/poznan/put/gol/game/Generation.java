package pl.poznan.put.gol.game;

import java.util.stream.Collectors;

public class Generation {

	private Rules rules;
	private Cells aliveCells;

	public Generation(Rules rules, Cell... aliveCells) {
		this(rules, new Cells(aliveCells));
	}

	public Generation(Rules rules, Cells aliveCells) {
		this.rules = rules;
		this.aliveCells = aliveCells;
	}

	public void evolve() {
		Cells nextGen = new Cells();
		for(Cell c : aliveCells){
			if(rules.inNextGeneration(isAlive(c), countAliveNeighbors(c)))
				nextGen.add(c);
		}
		for(Cell c : aliveCells.getNeighbors()){
			if(!aliveCells.contains(c) && rules.inNextGeneration(false, countAliveNeighbors(c)))
				nextGen.add(c);
		}
		aliveCells = nextGen;
	}

	public boolean isAlive(Cell cell) { //implemented
		return aliveCells.contains(cell);
	}

	public int countAliveNeighbors(Cell cell) { //implemented
		return (int) cell.neighbors().getCells()
				.stream()
				.distinct()
				.filter(aliveCells::contains)
				.count();
	}

	public boolean extinct() {
		return aliveCells.isEmpty();
	}

	public Cells getAliveCells() {
		return aliveCells;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Generation)) {
			return false;
		}
		Generation other = (Generation) obj;
		return aliveCells.equals(other.aliveCells);
	}

	@Override
	public String toString() {
		return "Generation{" +
				"rules=" + rules +
				", aliveCells=" + aliveCells +
				'}';
	}
}
