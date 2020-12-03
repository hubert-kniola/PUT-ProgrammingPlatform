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
		var list = aliveCells.getCells()
				.stream()
				.flatMap(cell -> cell.neighbors().getCells().stream())
				.filter(this::isAlive)
				.collect(Collectors.toList());

		this.aliveCells = new Cells(list);
	}

	public boolean isAlive(Cell cell) { //implemented
		var aliveNeighborsAmount = countAliveNeighbors(cell);

		return aliveCells.contains(cell) ? rules.inNextGeneration(true, aliveNeighborsAmount) : rules.inNextGeneration(false, aliveNeighborsAmount);
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
}
