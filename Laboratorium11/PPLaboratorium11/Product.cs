using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPLaboratorium11
{
    public class Product
    {
        private int _id { get; }
        public string _name { get; set; }
        private Category _category { get; set; }
        private Supplier _supplier { get; set; }
        private decimal _unitPrice { get; set; }

        public Product(int id, string name, Category category, Supplier supplier, decimal unitPrice)
        {
            _id = id;
            _name = name;
            _category = category;
            _supplier = supplier;
            _unitPrice = unitPrice;
        }

        public Product() { }

        public override string ToString()
        {
            return string.Format("<Product = id: {0}, name: {1}, category: {2}, supplier: {3}, unitPrice: {4}>", _id, _name, _category, _supplier, _unitPrice);
        }
    }
}
