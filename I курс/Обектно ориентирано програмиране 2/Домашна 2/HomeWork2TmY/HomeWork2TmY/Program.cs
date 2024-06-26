using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeWork2TmY {

    class Program {

        static void menu() {
            Console.WriteLine("Welcome to our garden! \n" +
                "We offer: \n" +
                "1.Peach \n" +
                "2.Apricot \n" +
                "3.Acacia \n" +
                "4.Oak \n" +
                "5.Lily \n" +
                "6.Carrot \n" +
                "7.Strawberry \n" +
                "8.Rose \n" +
                "Enter the type of the plant and it's count e.g(Peach 2) \n" +
                "When you are ready type 'ready' and you will see the results");
            Garden garden = new Garden();
            string answ = Console.ReadLine();
            while (answ != string.Empty && answ != "ready")
            {
                string[] splittedAnsw = answ.Split(' ');
                int.TryParse(splittedAnsw[1], out int items);
                switch (splittedAnsw[0])
                {
                    case "Peach":
                        for (int i = 0; i < items; i++)
                        {
                            Peach peach = new Peach();
                            garden.ItemsInGarden.Add(peach);
                        }
                        break;

                    case "Apricot":
                        for (int i = 0; i < items; i++)
                        {
                            Apricot apricot = new Apricot();
                            garden.ItemsInGarden.Add(apricot);
                        }
                        break;

                    case "Acacia":
                        for (int i = 0; i < items; i++)
                        {
                            Acacia acacia = new Acacia();
                            garden.ItemsInGarden.Add(acacia);
                        }
                        break;

                    case "Oak":
                        for (int i = 0; i < items; i++)
                        {
                            Oak oak = new Oak();
                            garden.ItemsInGarden.Add(oak);
                        }
                        break;

                    case "Lily":
                        for (int i = 0; i < items; i++)
                        {
                            Lily lily = new Lily();
                            garden.ItemsInGarden.Add(lily);
                        }
                        break;
                    case "Carrot":
                        for (int i = 0; i < items; i++)
                        {
                            Carrot carrot = new Carrot();
                            garden.ItemsInGarden.Add(carrot);
                        }
                        break;
                    case "Strawberry":
                        for (int i = 0; i < items; i++)
                        {
                            Strawberry strawberry = new Strawberry();
                            garden.ItemsInGarden.Add(strawberry);
                        }
                        break;

                    case "Rose":
                        for (int i = 0; i < items; i++)
                        {
                            Rose rose = new Rose();
                            garden.ItemsInGarden.Add(rose);
                        }
                        break;
                    default:
                        Console.WriteLine("You've entered invalid product, try again: ");
                        break;

                }
             
            }

            Console.WriteLine("Beauty: " + garden.GetBeautyPoints());
            Console.WriteLine("Water: " + garden.GetWaterRequired());
            Console.WriteLine("Energy: " + garden.GetEnergy());

        }


        static void Main(string[] args)
        {
            menu();
        }
    }

}