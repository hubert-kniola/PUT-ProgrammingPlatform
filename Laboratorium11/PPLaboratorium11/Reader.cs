using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
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
                    var orderedWords = Regex.Split(reader.ReadToEnd(), @"[ ,.;:?!-_'„ ”""\n\t\r]")
                                .Where(x => x.Length > 2)
                                .Select(x => x.ToLower())
                                .GroupBy(x => x)
                                .ToDictionary(x => x.Key, x => x.Count())                              
                                .OrderByDescending(x => x.Value)
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
