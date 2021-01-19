using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPLaboratorium11
{
    public class Supplier
    {
        private int _id { get; }
        private string _companyName { get; set; }
        private string _city { get; set; }
        private string _homePage { get; set; }

        public Supplier(int id, string companyName, string city, string homePage)
        {
            _id = id;
            _companyName = companyName;
            _city = city;
            _homePage = homePage;
        }

        //Supplier() { }

        public override string ToString()
        {
            return string.Format("<Supplier = id: {0}, companyName: {1}, city: {2}, homePage: {3}>", _id, _companyName, _city, _homePage);
        }

    }
}
