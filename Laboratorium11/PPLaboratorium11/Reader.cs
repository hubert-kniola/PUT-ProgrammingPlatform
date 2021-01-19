using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPLaboratorium11
{
    class Reader
    {
        //TASK 4
        public static void MostCommonWords()
        {
            try
            {
                using (var reader = new StreamReader("holmes.txt"))
                {                  
                    var words = reader.ReadToEnd();
                    var orderedWords = words.Split(' ')
                        .GroupBy(x => x.ToLower())
                        .Select(x => new{KeyField = x.Key, Count = x.Count()})
                        .Where(x => x.KeyField.Length > 2)
                        .OrderByDescending(x => x.Count)
                        .Take(20);

                    foreach (var element in orderedWords)
                        Console.WriteLine($"{element}");
                }
            }
            catch (IOException e)
            {
                Console.WriteLine("The file could not be read:");
                Console.WriteLine(e.Message);
            }
        }
    }
}
