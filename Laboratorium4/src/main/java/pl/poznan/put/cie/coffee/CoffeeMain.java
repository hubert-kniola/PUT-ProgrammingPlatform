package pl.poznan.put.cie.coffee;

import pl.poznan.put.cie.coffee.EntityModel.CoffHaousesDao;
import pl.poznan.put.cie.coffee.EntityModel.CoffHousesElements;
import pl.poznan.put.cie.coffee.EntityModel.SupplierDao;
import pl.poznan.put.cie.coffee.EntityModel.SupplierElements;
import pl.poznan.put.cie.coffee.EntityModel.MerchDao;
import pl.poznan.put.cie.coffee.EntityModel.MerchElements;
import pl.poznan.put.cie.coffee.CoffeeModel.CoffeeDao;
import pl.poznan.put.cie.coffee.CoffeeModel.Coffee;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CoffeeMain {
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try{
			CoffeeMain.coffee();
			//CoffeeMain.suppliers();
			//CoffeeMain.coffhouses();
			//CoffeeMain.merch();
		}
		catch(Exception e) { System.out.println(e); }
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
			var sDao = new SupplierDao();

			Scanner in = new Scanner(System.in);
			switch (in.nextLine()) {
				case "a": {
					System.out.println("SELECT");
					System.out.println("Please enter coffee name: ");
					String name = in.nextLine();
					System.out.println(dao.get(name));
					break;
				}
				case "b": {
					System.out.println("LISTING");
					List<Coffee> coffeeList = dao.getAll();
					for (Coffee c : coffeeList) {
						System.out.println(c.toString());
					}
					break;
				}
				case "c": {
					System.out.println("CREATE");
					System.out.print("Please enter coffee name: ");
					String name = in.nextLine();
					System.out.print("Please enter coffee supplier ID: ");
					int supId = Integer.parseInt(in.nextLine());
					System.out.print("Please enter coffee price: ");
					BigDecimal price = new BigDecimal(in.nextLine());
					System.out.print("Please enter coffee sales: ");
					int sales = Integer.parseInt(in.nextLine());
					System.out.print("Please enter coffee total: ");
					int total = Integer.parseInt(in.nextLine());
					dao.create(new Coffee(name, supId, price, sales, total));
					break;
				}
				case "d": {
					System.out.println("UPDATE");
					System.out.print("Please enter coffee name: ");
					String name = in.nextLine();
					System.out.print("Please enter coffee supplier ID: ");
					int supId = Integer.parseInt(in.nextLine());
					System.out.print("Please enter coffee price: ");
					BigDecimal price = new BigDecimal(in.nextLine());
					System.out.print("Please enter coffee sales: ");
					int sales = Integer.parseInt(in.nextLine());
					System.out.print("Please enter coffee total: ");
					int total = Integer.parseInt(in.nextLine());
					dao.update(new Coffee(name, supId, price, sales, total));
					break;
				}
				case "e": {
					System.out.println("DELETE");
					sDao.getIdList();
					dao.getIdList();
					System.out.print("Please enter coffee name: ");
					String name = in.nextLine();
					System.out.print("Please enter coffee suppplier ID: ");
					int supId = Integer.parseInt(in.nextLine());
					dao.delete(name, supId);
					break;
				}
				default:
					return;
			}
		}
	}

	public static void suppliers() {
		System.out.println("Suppliers");
		while (true) {
			System.out.println("\n"
					+ "Choose an action\n"
					+ "(a) select supplier,\n"
					+ "(b) list all,\n"
					+ "(c) create new supplier,\n"
					+ "(d) update supplier,\n"
					+ "(e) delete supplier,\n"
					+ "(any other key) exit.\n");

			var sDao = new SupplierDao();

			Scanner in = new Scanner(System.in);
			switch (in.nextLine()) {
				case "a": {
					System.out.println("SELECT");
					System.out.println("Please enter supplier name: ");
					var name = in.nextLine();
					sDao.getEnt(name).ifPresentOrElse(System.out::println, () -> System.out.println("Supplier not found"));
					break;
				}
				case "b": {
					System.out.println("LISTING");
					List<SupplierElements> entityList = sDao.getAllEnt();
					for (SupplierElements e : entityList) {
						System.out.println(e.toString());
					}
					break;
				}
				case "c":
				case "d": {
					System.out.println("CREATE/UPDATE");
					System.out.print("Please enter supplier ID: ");
					Integer supId = Integer.parseInt(in.nextLine());
					System.out.print("Please enter supplier name: ");
					String name = in.nextLine();
					System.out.print("Please enter supplier street: ");
					String street = in.nextLine();
					System.out.print("Please enter supplier city: ");
					String city = in.nextLine();
					System.out.print("Please enter supplier state: ");
					String state = in.nextLine();
					System.out.print("Please enter supplier zip: ");
					String zip = in.nextLine();
					try {
						sDao.saveEnt(new SupplierElements(supId, name, street, city, state, zip));
					} catch (Exception e) {
						System.out.println("Couldn't save given supplier, reason: " + e.getMessage());
					}
					break;
				}
				case "e": {
					System.out.println("DELETE");
					System.out.print("Please enter supplier id: ");
					var name = in.nextLine();
					sDao.deleteEnt(name);
					break;
				}
				default:
					return;
			}
		}
	}

	public static void coffhouses() {
		System.out.println("Coffee Houses");
		while (true) {
			System.out.println("\n"
					+ "Choose an action\n"
					+ "(a) select house,\n"
					+ "(b) list all,\n"
					+ "(c) create new house,\n"
					+ "(d) update house,\n"
					+ "(e) delete house,\n"
					+ "(any other key) exit.\n");

			var chDao = new CoffHaousesDao();

			Scanner in = new Scanner(System.in);
			switch (in.nextLine()) {
				case "a": {
					System.out.println("SELECT");
					System.out.println("Please enter coffee house ID: ");
					var coffHouseID = Integer.parseInt(in.nextLine());
					chDao.getEnt(coffHouseID).ifPresentOrElse(System.out::println, () -> System.out.println("Coffee House not found"));
					break;
				}
				case "b": {
					System.out.println("LISTING");
					List<CoffHousesElements> entityList = chDao.getAllEnt();
					for (CoffHousesElements e : entityList) {
						System.out.println(e.toString());
					}
					break;
				}
				case "c":
				case "d": {
					System.out.println("CREATE/UPDATE");
					System.out.print("Please enter coffee house ID: ");
					var coffHouseId = Integer.parseInt(in.nextLine());
					System.out.print("Please enter coffee house city: ");
					String city = in.nextLine();
					System.out.print("Please enter coffee: ");
					var coffee = Integer.parseInt(in.nextLine());
					System.out.print("Please enter coffee house merch: ");
					var merch = Integer.parseInt(in.nextLine());
					System.out.print("Please enter coffee total: ");
					var total = Integer.parseInt(in.nextLine());
					try {
						chDao.saveEnt(new CoffHousesElements(coffHouseId, city, coffee, merch, total));
					} catch (Exception e) {
						System.out.println("Couldn't save given coffee house, reason: " + e.getMessage());
					}
					break;
				}
				case "e": {
					System.out.println("DELETE");
					System.out.print("Please enter coffee house ID: ");
					var coffHouseID = Integer.parseInt(in.nextLine());
					chDao.deleteEnt(coffHouseID);
					break;
				}
				default:
					return;
			}
		}
	}

	public static void merch() throws ParseException {
		System.out.println("Merch Inventory");
		while (true) {
			System.out.println("\n"
					+ "Choose an action\n"
					+ "(a) select merch,\n"
					+ "(b) list all,\n"
					+ "(c) create new merch,\n"
					+ "(d) update merch,\n"
					+ "(e) delete merch,\n"
					+ "(any other key) exit.\n");

			var mDao = new MerchDao();

			Scanner in = new Scanner(System.in);
			switch (in.nextLine()) {
				case "a": {
					System.out.println("SELECT");
					System.out.println("Please enter merch ID: ");
					var merchID = Integer.parseInt(in.nextLine());
					mDao.getEnt(merchID).ifPresentOrElse(System.out::println, () -> System.out.println("Merch not found"));
					break;
				}
				case "b": {
					System.out.println("LISTING");
					List<MerchElements> entityList = mDao.getAllEnt();
					for (MerchElements e : entityList) {
						System.out.println(e.toString());
					}
					break;
				}
				case "c":
				case "d": {
					System.out.println("CREATE/UPDATE");
					System.out.print("Please enter merch ID: ");
					var merchId = Integer.parseInt(in.nextLine());
					System.out.print("Please enter merch name: ");
					String name = in.nextLine();
					System.out.print("Please enter supplier ID: ");
					var supId = Integer.parseInt(in.nextLine());
					System.out.print("Please enter merch quan: ");
					var quan = Integer.parseInt(in.nextLine());
					System.out.print("Please enter merch date: ");
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
					Date parsedDate = dateFormat.parse(in.nextLine());
					Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
					try {
						mDao.saveEnt(new MerchElements(merchId, name, supId, quan, timestamp));
					} catch (Exception e) {
						System.out.println("Couldn't save given merch, reason: " + e.getMessage());
					}
					break;
				}
				case "e": {
					System.out.println("DELETE");
					System.out.print("Please enter merch ID: ");
					var merchID = Integer.parseInt(in.nextLine());
					mDao.deleteEnt(merchID);
					break;
				}
				default:
					return;
			}
		}
	}
}
