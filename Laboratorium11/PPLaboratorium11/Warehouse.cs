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
            _products.Add(product, stock);
        }

        public void deleteProduct(Product product)
        {
            _products.Remove(product);
        }

        public void changeStock(Product product, int stock)
        {
            _products[product] = stock;
        }

        public int getFullStock()
        {
            return _products.Keys.Count;
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
