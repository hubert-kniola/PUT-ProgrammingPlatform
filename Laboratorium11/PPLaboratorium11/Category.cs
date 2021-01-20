using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPLaboratorium11
{
    public class Category
    {
        private int _id { get; }
        private string _name { get; set; }
        private string _description { get; set; }

        public Category(int id, string name, string description)
        {
            _id = id;
            _name = name;
            _description = description;
        }

        public Category() { }

        public override string ToString()
        {
            return string.Format("<Category = id: {0}, name: {1}, description: {2}>", _id, _name, _description);
        }
    }
}
