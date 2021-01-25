using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPLaboratorium11
{
    class Program
    {
        static void Main(string[] args)
        {
            //========= TASK 1/2 =========
            Console.WriteLine("=== TASK 1/2 ===");
            Product product1 = new Product(1) { _name = "Redbull",
                _category = new Category(1) { _name = "Napoje", _description = "Do picia" }, 
                _supplier = new Supplier(1) { _companyName = "Redbull", _city = "Berlin", _homePage = "redbull.pl" }, _unitPrice = 10 };
            Product product2 = new Product(2) { _name = "Monster", 
                _category = new Category(2) { _name = "Napoje", _description = "Do picia" }, 
                _supplier = new Supplier(2) { _companyName = "Monster", _city = "Warszawa", _homePage = "monster.pl" }, _unitPrice = 5 };
            Product product3 = new Product(3) { _name = "Rockstar", 
                _category = new Category(3) { _name = "Napoje", _description = "Do picia" }, 
                _supplier = new Supplier(3) { _companyName = "Rockstar", _city = "Londyn", _homePage = "rockstar.pl" }, _unitPrice = 15 };

            Warehouse whouse = new Warehouse();

            whouse.addProduct(product1, 8);
            whouse.addProduct(product2, 16);
            whouse.addProduct(product3, 24);

            whouse.showWarehouse();
            Console.WriteLine(" ");

            whouse.showProducts();
            Console.WriteLine(" ");

            whouse.changeStock(product1, 12);

            whouse.showProducts();
            Console.WriteLine(" ");

            whouse.deleteProduct(product2);

            whouse.showProducts();
            Console.WriteLine(" ");

            whouse.getFullStock();
            Console.WriteLine(" ");

            //========= TASK 3 =========

            Console.WriteLine("=== TASK 3 ===");
            Factorial.MeasureTime(2);
            Factorial.MeasureTime(5);
            Factorial.MeasureTime(10);
            Factorial.MeasureTime(1000);
            Factorial.MeasureTime(10000);
            Factorial.MeasureTime(50000);
            Console.WriteLine(" ");

            //========= 4=========

            Console.WriteLine("=== TASK 4 ===");
            Console.WriteLine("20 Most Common Words:");
            Reader.MostCommonWords();

            Console.ReadKey();
        }
    }
}
