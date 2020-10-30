package pl.poznan.put.cie.coffee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CoffeeMain {
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		//CoffeeMain.coffee();
		CoffeeMain.entity();
	}

	public static void coffee() {
		System.out.println("Coffee");
		while (true) {
			System.out.println("\n"
					  + "Choose an action\n"
					  + "(a) select coffee,\n"
					  + "(b) list all,\n"
					  + "(c) create new coffee,\n"
					  + "(d) update coffee,\n"
					  + "(e) delete coffee,\n"
					  + "(any other key) exit.\n");

			var dao = new CoffeeDao();

			Scanner in = new Scanner(System.in);
			switch (in.nextLine()) {
				case "a": {
					System.out.println("Please enter coffee name : ");
					String name = in.nextLine();
					System.out.println(dao.get(name));
					break;
				}
				case "b": {
					List<Coffee> coffeeList = dao.getAll();
					for (Coffee c : coffeeList) {
						System.out.println(c.toString());
					}
					break;
				}
				case "c": {
					System.out.print("Please enter coffee name : ");
					String name = in.nextLine();
					System.out.print("Please enter coffee supplier ID : ");
					int supId = Integer.parseInt(in.nextLine());
					System.out.print("Please enter coffee price: ");
					BigDecimal price = new BigDecimal(in.nextLine());
					System.out.print("Please enter coffee sales : ");
					int sales = Integer.parseInt(in.nextLine());
					System.out.print("Please enter coffee total : ");
					int total = Integer.parseInt(in.nextLine());
					dao.create(new Coffee(name, supId, price, sales, total));
					break;
				}
				case "d": {
					System.out.print("Please enter coffee name: ");
					String name = in.nextLine();
					System.out.print("Please enter coffee supplier ID : ");
					int supId = Integer.parseInt(in.nextLine());
					System.out.print("Please enter coffee price: ");
					BigDecimal price = new BigDecimal(in.nextLine());
					System.out.print("Please enter coffee sales : ");
					int sales = Integer.parseInt(in.nextLine());
					System.out.print("Please enter coffee total : ");
					int total = Integer.parseInt(in.nextLine());
					dao.update(new Coffee(name, supId, price, sales, total));
					break;
				}
				case "e": {
					System.out.print("Please enter coffee name : ");
					String name = in.nextLine();
					System.out.print("Please enter coffee suppplier ID : ");
					int supId = Integer.parseInt(in.nextLine());
					dao.delete(name, supId);
					break;
				}
				default:
					return;
			}
		}
	}

	public static void entity() {
		System.out.println("Coffee");
		while (true) {
			System.out.println("\n"
					+ "Choose an action\n"
					+ "(a) select supplier,\n"
					+ "(b) list all,\n"
					+ "(c) create new supplier,\n"
					+ "(d) update supplier,\n"
					+ "(e) delete supplier,\n"
					+ "(any other key) exit.\n");

			var entDao = new EntityDao();

			Scanner in = new Scanner(System.in);
			switch (in.nextLine()) {
				case "a": {
					System.out.println("Please enter supplier id : ");
					Integer id = Integer.parseInt(in.nextLine());
					entDao.getEnt(id).ifPresentOrElse(System.out::println, () -> System.out.println("Supplier not found"));
					break;
				}
				case "b": {
					List<EntityElements> entityList = entDao.getAllEnt();
					for (EntityElements e : entityList) {
						System.out.println(e.toString());
					}
					break;
				}
				case "c":
				case "d": {
					System.out.print("Please enter supplier ID : ");
					Integer supId = Integer.parseInt(in.nextLine());
					System.out.print("Please enter supplier name : ");
					String name = in.nextLine();
					System.out.print("Please enter street : ");
					String street = in.nextLine();
					System.out.print("Please enter city : ");
					String city = in.nextLine();
					System.out.print("Please enter state : ");
					String state = in.nextLine();
					System.out.print("Please enter zip : ");
					String zip = in.nextLine();
					try {
						entDao.saveEnt(new EntityElements(supId, name, street, city, state, zip));
					} catch (Exception e) {
						System.out.println("Couldn't save given supplier, reason: " + e.getMessage());
					}
					break;
				}
				case "e": {
					System.out.print("Please enter supplier id: ");
					Integer id = Integer.parseInt(in.nextLine());
					entDao.deleteEnt(id);
					break;
				}
				default:
					return;
			}
		}
	}
}
