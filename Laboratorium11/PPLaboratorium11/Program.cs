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
            Product product1 = new Product(1, "Redbull", new Category(1, "Napoje", "Do picia"), new Supplier(1, "Redbull", "Berlin", "redbull.pl"), 10);
            Product product2 = new Product(2, "Monster", new Category(2, "Napoje", "Do picia"), new Supplier(2, "Monster", "Warszawa", "monster.pl"), 5);
            Product product3 = new Product(3, "Rockstar", new Category(3, "Napoje", "Do picia"), new Supplier(3, "Rockstar", "Londyn", "rockstar.pl"), 15);

            Warehouse whouse = new Warehouse();

            whouse.addProduct(product1, 8);
            whouse.addProduct(product2, 16);
            whouse.addProduct(product3, 24);

            whouse.showProducts();

            whouse.changeStock(product1, 12);

            whouse.showProducts();

            Console.WriteLine("Stock: ", whouse.getFullStock());
            Console.WriteLine(" ");

            //========= TASK 3 =========

            Factorial.MeasureTime(2);
            Factorial.MeasureTime(5);
            Factorial.MeasureTime(10);
            Console.WriteLine(" ");

            //========= 4=========

            Console.WriteLine("20 Most Common Words:");
            Reader.MostCommonWords();

            Console.ReadKey();
        }
    }
}
