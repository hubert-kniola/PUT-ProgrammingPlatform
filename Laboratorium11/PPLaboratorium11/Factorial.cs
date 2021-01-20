using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace PPLaboratorium11
{
    class Factorial
    {
        //TASK 3
        public static BigInteger Calculate(BigInteger number)
        {
            if (number < 0)
                throw new ArgumentOutOfRangeException();

            BigInteger accumulator = 1;
            for (BigInteger factor = 1; factor <= number; factor++)
            {
                accumulator *= factor;
            }
            return accumulator;
        }

        public static void MeasureTime(BigInteger number)
        {
            Stopwatch stopWatch = new Stopwatch();
            stopWatch.Start();

            Console.WriteLine($"Result of factorial [{number}]: {Calculate(number)}");

            stopWatch.Stop();
            TimeSpan ts = stopWatch.Elapsed;

            string elapsedTime = String.Format("{0:00}:{1:00}:{2:00}.{3:00}", ts.Hours, ts.Minutes, ts.Seconds, ts.Milliseconds / 10);
            Console.WriteLine("RunTime: " + elapsedTime);
        }
    }
}
