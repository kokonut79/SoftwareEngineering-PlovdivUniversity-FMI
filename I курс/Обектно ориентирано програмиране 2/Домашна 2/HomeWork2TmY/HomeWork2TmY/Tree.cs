using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeWork2TmY
{
    class Tree : IGardenItem
    {
        public double waterRequired { get; set; }
        public double WaterRequired { get => waterRequired; set => waterRequired = value; }
    }
}
