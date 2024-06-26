using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeWork2TmY
{
    class Plant : IGardenItem
    {
        private double waterRequired;
        

        public double WaterRequired
        {
            get => waterRequired; set => waterRequired = value;
        }


    }
}
