package pkg;
import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Spell {
    
    //Base Damage Arraylist (To allow for multi hit/multi projectile spells)
    ArrayList<Integer> Base_Damages = new ArrayList<Integer>();
    //Name of the Spell
    String Name;
    //School of magic of the Spell. For the sake of simplicity, this is the same as magic types. Will not be in the future, obviously.
    int School;
    //Magical Damage Type of the Spell 1 == Physical 2 == Magic 3 == Fire 4 == Lightning 5 == Poison 6 == Ice 7 == Light 8 == Dark
    int Magic_Damage_Type;
    //Base Casts of the Spell
    int Base_Casts;
    //Max Casts of the Spell
    int Max_Casts;
    //Current Casts of the Spell
    int Current_Casts;
    //Ire Cost
    int Ire_Cost;
    //Description of the Spell
    String Description;
    //User
    Person User;
    
    
    public Spell(ArrayList<Integer> Base_Damages, String Name, int School, int Magic_Damage_Type, int Base_Casts, int Ire_Cost, String Description, Person User) {
        this.Base_Damages = Base_Damages;
        this.Name = Name;
        this.School = School;
        this.Magic_Damage_Type = Magic_Damage_Type;
        this.Base_Casts = Base_Casts;
        this.Ire_Cost = Ire_Cost;
        this.Description = Description;
        this.User = User;
        Calculate_Casts(this.User.Get_Scaling_Levels());
    }

    //Calculates the actual number of casts based on the user's stats
    public void Calculate_Casts(int[] Levels) {
        switch (Magic_Damage_Type) {
            case 1:
                Max_Casts = Base_Casts + (int) (Base_Casts * 0.5 * (1 - Math.pow((1 - (double)(Levels[0])/100),3)))
                + (int) (Base_Casts * 0.5 * (1 - Math.pow((1 - (double)(Levels[1])/100),3)));
                System.out.println("This spell has " + Max_Casts + " Casts");
                break;
            case 2:
                Max_Casts = Base_Casts + (int) (Base_Casts * 1 * (1 - Math.pow((1 - (double)(Levels[2])/100),1.5)));
                System.out.println("This spell has " + Max_Casts + " Casts");
                break;
            case 3:
                Max_Casts = Base_Casts + (int) (Base_Casts * 0.75 * (1 - Math.pow((1 - (double)(Levels[2])/100),3)))
                + (int) (Base_Casts * 0.25 * (1 - Math.pow((1 - (double)(Levels[3])/100),3)));
                System.out.println("This spell has " + Max_Casts + " Casts");
                break;
            case 4:
                Max_Casts = Base_Casts + (int) (Base_Casts * 0.25 * (1 - Math.pow((1 - (double)(Levels[2])/100),3)))
                + (int) (Base_Casts * 0.75 * (1 - Math.pow((1 - (double)(Levels[3])/100),3)));
                System.out.println("This spell has " + Max_Casts + " Casts");
                break;
            case 5:
                Max_Casts = Base_Casts + (int) (Base_Casts * 0.25 * (1 - Math.pow((1 - (double)(Levels[2])/100),3)))
                + (int) (Base_Casts * 0.75 * (1 - Math.pow((1 - (double)(Levels[4])/100),3)));
                System.out.println("This spell has " + Max_Casts + " Casts");
                break;
            case 6:
                Max_Casts = Base_Casts + (int) (Base_Casts * 0.75 * (1 - Math.pow((1 - (double)(Levels[2])/100),3)))
                + (int) (Base_Casts * 0.25 * (1 - Math.pow((1 - (double)(Levels[4])/100),3)));
                System.out.println("This spell has " + Max_Casts + " Casts");
                break;
            case 7:
                Max_Casts = Base_Casts + (int) (Base_Casts * 1 * (1 - Math.pow((1 - (double)(Levels[3])/100),1.5)));
                System.out.println("This spell has " + Max_Casts + " Casts");
                break;
            case 8:
                Max_Casts = Base_Casts + (int) (Base_Casts * 1 * (1 - Math.pow((1 - (double)(Levels[4])/100),1.5)));
                System.out.println("This spell has " + Max_Casts + " Casts");
                break;
        }
        Current_Casts = Max_Casts;
    }
    
    //Refresh and update casts, called by resting.
    public void Refresh_Casts() {
        Calculate_Casts(User.Get_Scaling_Levels());
    }
    
    //Cast the spell
    public void Cast_Spell(Staff Casting_Tool, Person Target) {
        if (Current_Casts >= 1 && User.Get_Ire() >= Ire_Cost && Casting_Tool.Can_Cast(School - 1)){
            for (int i : Base_Damages) {
                System.out.println(i);
                System.out.println((Casting_Tool.Get_Spell_Buff(School - 1))/100.0 * i);
                Target.Take_Magic_Damage((int) ((Casting_Tool.Get_Spell_Buff(School - 1))/100.0 * i), Magic_Damage_Type);
            }
            Current_Casts--;
            User.Use_Ire(Ire_Cost);
        }
    }
    
    public ArrayList<Integer> Get_Base_Damages() {
        return Base_Damages;
    }
    
    public String Get_Name() {
        return Name;
    }
    
    public int Get_School() {
        return School;
    }
    
    public int Get_Magic_Damage_Type() {
        return Magic_Damage_Type;
    }
    
    public int Get_Max_Casts() {
        return Max_Casts;
    }
    
    public int Get_Current_Casts() {
        return Current_Casts;
    }
    
    public String Get_Description() {
        return Description;
    }
    
    public Spell Get_Spell_Info() {
        return this;
    }
}