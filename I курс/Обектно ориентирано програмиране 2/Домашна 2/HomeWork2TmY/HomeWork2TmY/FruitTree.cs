using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeWork2TmY
{
    class FruitTree : Tree, IFood
    {
        private double energy;
        public double Energy { get => energy; set => energy = value; }
    }
}
