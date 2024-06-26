using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeWork2TmY
{
    class DecorationTree : Tree, IBeauty
    {
        private double beautyScore;
        public double BeautyScore { get => beautyScore; set => beautyScore = value; }
    }
}
