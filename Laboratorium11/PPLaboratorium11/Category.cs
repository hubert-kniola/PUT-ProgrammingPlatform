using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPLaboratorium11
{
    public class Category
    {
        public int _id { get; }
        public string _name { get; set; }
        public string _description { get; set; }

        public Category(int id, string name, string description)
        {
            _id = id;
            _name = name;
            _description = description;
        }

        public override string ToString()
        {
            return string.Format("<Category = id: {0}, name: {1}, description: {2}>", _id, _name, _description);
        }
    }
}
