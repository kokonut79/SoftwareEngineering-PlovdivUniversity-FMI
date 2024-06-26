using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeWork2TmY
{
    class Garden
    {
        public List<IGardenItem> ItemsInGarden;

        public Garden()
        {
            this.ItemsInGarden = new List<IGardenItem>();
        }

        public double GetEnergy()
        {
            double sum = 0;
            foreach (IFood item in this.Foods)
            {
                sum += item.Energy;
            }
            return sum;
        }

        public double GetWaterRequired()
        {
            double sum = 0;
            foreach (IGardenItem item in ItemsInGarden)
            {
                sum += item.WaterRequired;
            }
            return sum;
        }

        public double GetBeautyPoints()
        {
            double sum = 0;
            foreach (IBeauty item in this.Beauties)
            {
                sum += item.BeautyScore;
            }
            return sum;
        }

        public List<IBeauty> Beauties
        {
            get { 
            
                List<IBeauty> AllBeauties = new List<IBeauty>();
                foreach (IGardenItem item in this.ItemsInGarden)
                {
                    if (item.GetType().GetInterface("IBeauty") != null)
                    {
                        AllBeauties.Add((IBeauty)item);
                    }
                }
                return AllBeauties;
            }
        }

        public List<IFood> Foods
        {
            get
            {
                List<IFood> AllFoods = new List<IFood>();
                foreach (IGardenItem item in this.ItemsInGarden)
                {
                    if (item.GetType().GetInterface("IFood") != null)
                    {
                        AllFoods.Add((IFood)item);
                    }
                }
                return AllFoods;
            }
        }

    }
}
