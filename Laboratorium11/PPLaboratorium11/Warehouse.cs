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

        public void changeStock(Product product, int stock)
        {
            _products[product] = stock;
        }

        public int getFullStock()
        {
            return _products.Count;
        }

        public void showProducts()
        {
            foreach (var element in _products)
                Console.WriteLine($"Product: {element.Key}, Stock: {element.Value}\n");
        }

    }
}
