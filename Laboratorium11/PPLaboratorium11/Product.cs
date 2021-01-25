using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPLaboratorium11
{
    public class Product
    {
        public int _id { get; }
        public string _name { get; set; }
        public Category _category { get; set; }
        public Supplier _supplier { get; set; }
        public decimal _unitPrice { get; set; }

        public Product(int id)
        {
            _id = id;
        }

        public override string ToString()
        {
            return string.Format("<Product = id: {0}, name: {1}, category: {2}, supplier: {3}, unitPrice: {4}>", _id, _name, _category, _supplier, _unitPrice);
        }
    }
}
