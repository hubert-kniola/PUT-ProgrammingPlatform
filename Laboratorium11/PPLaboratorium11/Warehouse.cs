using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPLaboratorium11
{
    public class Warehouse
    {
        private Dictionary<Product, int> _products { get; set; }

        public Warehouse()
        {
            _products = new Dictionary<Product, int>();
        }

        public void addProduct(Product product, int stock)
        {
            if (!_products.ContainsKey(product))
                _products.Add(product, (stock >= 0) ? stock : 0);
            else
                Console.WriteLine("Product with that name already exist!");
        }

        public void deleteProduct(Product product)
        {
            if (_products.ContainsKey(product))
                _products.Remove(product);
            else
                Console.WriteLine("Product with that name does not exist!");

        }

        public void changeStock(Product product, int stock)
        {
            if (_products.ContainsKey(product))
                _products[product] = stock;
            else
                Console.WriteLine("Product with that name does not exist!");
        }

        public void getFullStock()
        {
            Console.WriteLine($"Products: {_products.Count}, Products in stock: {_products.Sum(x=>x.Value)}");
        }

        public void showProducts()
        {
            foreach (var element in _products)
                Console.WriteLine($"Product: {element.Key._name}, Stock: {element.Value}");
        }

        public void showWarehouse()
        {
            foreach (var element in _products)
                Console.WriteLine($"Product: {element.Key}");
        }
    }
}
