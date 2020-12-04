package pl.poznan.put.gol.game;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cells implements Iterable<Cell> {

	protected HashSet<Cell> cells;

	public Cells(Cell... cells) {
		this.cells = new HashSet<>(Arrays.asList(cells));
	}

	public Cells(HashSet<Cell> cells){this.cells = cells;}

	public Cells() {
		this.cells = new HashSet<>();
	}

	public HashSet<Cell> getCells() {
		return cells;
	}

	public Cells getNeighbors() { //implemented
			var set = new HashSet<Cell>();
			for(Cell e : cells){
				for(Cell c : e.neighbors()){
						set.add(c);
			    }
			}
			return new Cells(set);
	}

	public boolean isEmpty() {
		return cells.isEmpty();
	}

	public int size() {
		return cells.size();
	}

	public void add(Cell cell) {
		if (cells.contains(cell)) {
			return;
		}
		cells.add(cell);
	}

	public void addAll(Iterable<? extends Cell> cells) {
		cells.forEach((cell) -> {
			add(cell);
		});
	}

	@Override
	public Iterator<Cell> iterator() {
		return cells.iterator();
	}

	public boolean contains(Cell cell) {
		return cells.contains(cell);
	}

	public void remove(Cell cell) {
		cells.remove(cell);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Cells)) {
			return false;
		}
		Cells other = (Cells) obj;
		if (!cells.stream().noneMatch((cell) -> (!other.contains(cell)))) {
			return false;
		}
		if (!other.cells.stream().noneMatch((cell) -> (!contains(cell)))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return cells.toString();
	}

}
