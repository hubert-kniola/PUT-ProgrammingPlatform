package pl.poznan.put.gol.game;

public class ConwaysCell implements Cell {

	protected int i;
	protected int j;

	public ConwaysCell(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public Cells neighbors() { //implemented
		return new Cells(new ConwaysCell(i - 1, j - 1),
				new ConwaysCell(i, j - 1),
				new ConwaysCell(i + 1, j - 1),
				new ConwaysCell(i - 1, j),
				new ConwaysCell(i + 1, j),
				new ConwaysCell(i - 1, j + 1),
				new ConwaysCell(i, j + 1),
				new ConwaysCell(i + 1, j + 1));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConwaysCell that = (ConwaysCell) o;

		return i == that.i && j == that.j;
	}

	@Override
	public String toString() {
		return "c(" + i + ":" + j + ")";
	}

}
