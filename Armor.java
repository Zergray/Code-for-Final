package pkg;
import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Armor extends Item {
    //Percent Defenses
    double [] Percent_Defenses;
    //Flat Defenses
    int [] Flat_Defenses;
    //Armor Slot, 1 == Head, 2 == Chest, 3 == Hands, 4 == Legs
    int Armor_Slot;
    
    public Armor (double [] Percent_Defenses, int [] Flat_Defenses, int Armor_Slot, double Weight, String Description, String Name) {
        this.Percent_Defenses = Percent_Defenses;
        this.Flat_Defenses = Flat_Defenses;
        this.Armor_Slot = Armor_Slot;
        this.Weight = Weight;
        this.Description = Description;
        this.Name = Name;
    }
    
    public double [] Get_Percent_Defenses() {
        return Percent_Defenses;
    }
    public int [] Get_Flat_Defenses() {
        return Flat_Defenses;
    }
    public int Get_Armor_Slot() {
        return Armor_Slot;
    }
}