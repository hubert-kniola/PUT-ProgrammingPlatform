package pl.poznan.put.gol.game;

import java.util.*;
import java.util.stream.Collectors;

public class Cells implements Iterable<Cell> {

	protected List<Cell> cells;

	public Cells(Cell... cells) {
		this.cells = Arrays.asList(cells);
	}

	public Cells(List<Cell> cells){this.cells = cells;}

	public Cells() {
		this.cells = new ArrayList<>();
	}

	public List<Cell> getCells() {
		return cells;
	}

	public Cells getNeighbors() { //implemented
			var list = new Cells();
			for(Cell e : cells){
				for(Cell c : e.neighbors()){
						list.add(c);
			    }
			}
			return list;
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
