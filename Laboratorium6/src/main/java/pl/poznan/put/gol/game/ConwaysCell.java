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
		var cells = new Cells();

		cells.add(new ConwaysCell(i - 1, j - 1));
		cells.add(new ConwaysCell(i, j - 1));
		cells.add(new ConwaysCell(i + 1, j - 1));
		cells.add(new ConwaysCell(i - 1, j));
		cells.add(new ConwaysCell(i + 1, j));
		cells.add(new ConwaysCell(i - 1, j + 1));
		cells.add(new ConwaysCell(i, j + 1));
		cells.add(new ConwaysCell(i + 1, j + 1));

		return cells;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ConwaysCell that = (ConwaysCell) o;

		if(i == that.i && j == that.j)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "c(" + i + ":" + j + ")";
	}

}
