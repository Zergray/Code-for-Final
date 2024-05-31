package pkg;
import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Person {
    //This array contains the player's levels. 0 == Vigor, 1 == Endurance, 2 == Strength, 3 == Dexterity, 4 == Attunement, 5 == Luminosity, 6 == Tenebrosity, 7 == Fortune, 8 == Frenzy
    int [] Levels;
    //This is the Maximum Ire the player has
    int Max_Ire;
    //This is the Current Ire the player has
    int Current_Ire;
    //This is the Maximum HP the player has
    int Max_Health;
    //This is the Current HP the player has
    int Current_Health;
    int Max_Stamina;
    int Current_Stamina;
    double Max_Weight;
    double Current_Weight;
    //This array contains the player's % defense.
    double [] Percent_Defenses = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    //Fucking fuck you java, why do you work like this. If I modify Percent Defenses and declare the placeholder armor with them
    //It just fucking shits itself if I tweak the value of percent defenses despite the fact it really ought not. Fuck.
    double [] Percent_Defenses_Unmodifiable = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    //This array contains the player's flat defense.
    int [] Flat_Defenses = {0,0,0,0,0,0,0,0,0,0,0};
    int [] Flat_Defenses_Unmodifiable = {0,0,0,0,0,0,0,0,0,0,0};
    //This array contains the player's Armor.
    Armor [] Armor_Array = new Armor [4];
    Weapon Weapon;
    
    
    public Person(int [] Levels) {
        this.Levels = Levels;
        Calculate_Ire();
        Calculate_Health();
        Calculate_Stamina();
        Max_Weight = 60.0 + Levels[1];
        for (int i = 0; i < 4; i++) {
            Armor_Array[i] = new Armor(Percent_Defenses_Unmodifiable, Flat_Defenses_Unmodifiable, i+1, 0.0, "No Description", "Blank");
        }
    }
    
    public void Equip_Armor(Armor Armor, int Armor_Slot) {
        Armor_Array[Armor_Slot - 1] = Armor;
        Calculate_Defense();
        Calculate_Weight_Load();
    }
    
    public void Equip_Weapon(Weapon Weapon) {
        this.Weapon = Weapon;
        Calculate_Weight_Load();
    }
    
    public double Calculate_Weight_Load() {
        Current_Weight = 0;
        for (Armor i : Armor_Array) {
            Current_Weight += i.Get_Weight();
        }
        if (Weapon != null) {
            Current_Weight += Weapon.Get_Weight();
        }
        
        return 100 * Current_Weight / Max_Weight;
    }
    
    public void Calculate_Defense() {
        for (int i = 0; i < Percent_Defenses.length; i++) {
            Percent_Defenses[i] = 0.0;
            Flat_Defenses[i] = 0;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 11; j++) {
                Percent_Defenses[j] = 100.0 * (1 - (1 - Percent_Defenses[j]/100.0) * (1 - Armor_Array[i].Get_Percent_Defenses()[j]/100.0));
                Flat_Defenses[j] = Flat_Defenses[j] + Armor_Array[i].Get_Flat_Defenses()[j];
            }
        }
    }
    
    public void Calculate_Ire() {
        if (Levels[8] <= 30) {
            Max_Ire = 100 + (int) (75 * Math.pow((((double) Levels[8] - 1)/29), 1.5));
        } else if (Levels[8] <= 50) {
            Max_Ire = 175 + (int) (75 * (1 - (Math.pow((((double) Levels[8] - 30)/20), 1.5))));
        } else {
            Max_Ire = 250 + (int) (50 * (1 - (Math.pow((((double) Levels[8] - 50)/50), 1.5))));
        }
        Current_Ire = Max_Ire;
    }
    
    public void Calculate_Health() {
        
        int Capped_Level = 0;
        for (int i : Levels) {
            if (i > 50) {
                Capped_Level += 50;
            } else {
                Capped_Level += i;
            }
        }
        
        if (Levels[0] <= 20) {
            Max_Health = 500 + (int) (550 * Math.pow((((double) Levels[0] - 1)/19), 1.5)) + (int) (1000 * (1 - Math.pow(1 - (((double) Capped_Level - 9)/441), 1.5)));
        } else if (Levels[0] <= 40) {
            Max_Health = 1050 + (int) (650 * (1 - Math.pow(1 - (((double) Levels[0] - 20)/20), 1.5))) + (int) (1000 * (1 - Math.pow(1 - (((double) Capped_Level - 9)/441), 1.5)));
        } else if (Levels[0] <= 60) {
            Max_Health = 1700 + (int) (800 * Math.pow((((double) Levels[0] - 40)/20), 1.5)) + (int) (1000 * (1 - Math.pow(1 - (((double) Capped_Level - 9)/441), 1.5)));
        } else {
            Max_Health = 2500 + (int) (500 * (1 - Math.pow(1 - (((double) Levels[0] - 60)/40), 1.5))) + (int) (1000 * (1 - Math.pow(1 - (((double) Capped_Level - 9)/441), 1.5)));
        }
        Current_Health = Max_Health;
        System.out.println("The Current Health is " + Current_Health);
    }
    
    public void Calculate_Stamina() {
        if (Levels[1] <= 30) {
            Max_Stamina = 125 + (int) (75 * Math.pow(((Levels[1] - 1)/29.0), 1.5));
        } else if (Levels[1] <= 50) {
            Max_Stamina = 200 + (int) (100 * (1 - Math.pow((1 - (Levels[1] - 30)/20.0), 1.5)));
        } else {
            Max_Stamina = 300 + (int) (75 * (1 - Math.pow((1 - (Levels[1] - 50)/50.0), 1.5)));
        }
        Current_Stamina = Max_Stamina;
    }
    
    public double Flat_Damage_Calculator(int Damage, int Flat_Defense) {
        if (Damage < Flat_Defense/10) {
            return .1;
        } else if (Damage < Flat_Defense/2) {
            return .5;
        } else if (Damage < Flat_Defense * 10) {
            return (Damage - (double) Flat_Defense/2)/Damage;
        } else {
            return (Damage * 1.25 - Flat_Defense * 3)/Damage;
        }
    }
    
    public void Take_Magic_Damage(int Damage, int Magic_Damage_Type) {
        int Holder = Current_Health;
        Current_Health -= (int) (Damage * Flat_Damage_Calculator(Damage, Flat_Defenses[Magic_Damage_Type+1]) * (1 - Percent_Defenses[Magic_Damage_Type+1]/100));
        if (Current_Health != Holder) {
            System.out.println("The Current Health is " + Current_Health);
        }
    }
    
    public void Take_Damage(int [] Damages, int Phys_Damage_Type) {
        switch(Phys_Damage_Type) {
            case 1:
                Current_Health -= (int) (Damages[0] * Flat_Damage_Calculator(Damages[0], Flat_Defenses[Phys_Damage_Type - 1]) * (1 - Percent_Defenses[Phys_Damage_Type - 1]/100));
                System.out.println("The Current Health is " + Current_Health);
                break;
            case 2:
                Current_Health -= (int) (Damages[0] * Flat_Damage_Calculator(Damages[0], Flat_Defenses[Phys_Damage_Type - 1]) * (1 - Percent_Defenses[Phys_Damage_Type - 1]/100));
                System.out.println("The Current Health is " + Current_Health);
                break;
            case 3:
                Current_Health -= (int) (Damages[0] * Flat_Damage_Calculator(Damages[0], Flat_Defenses[Phys_Damage_Type - 1]) * (1 - Percent_Defenses[Phys_Damage_Type - 1]/100));
                System.out.println("The Current Health is " + Current_Health);
                break;
        }
        for (int i = 3; i < 11; i++) {
            Take_Magic_Damage(Damages[i - 2],i - 1);
        }
    }
    
    public void Take_Stamina_Damage(int Stamina_Damage) {
        Current_Stamina -= Stamina_Damage;
        System.out.println(Current_Stamina + " Out of " + Max_Stamina + " Remains");
    }
    
    public void Rest() {
        Current_Health = Max_Health;
        System.out.println(Current_Health);
        Current_Ire = Max_Ire;
        Current_Stamina = Max_Stamina;
    }
    
    public int [] Get_Levels() {
        return Levels;
    }
    public int [] Get_Scaling_Levels() {
        int [] Arr = {Levels[2],Levels[3],Levels[4],Levels[5],Levels[6]};
        return Arr;
    }
    public int Get_Ire() {
        return Current_Ire;
    }
    public void Use_Ire(int Ire_Cost) {
        Current_Ire -= Ire_Cost;
        System.out.println(Current_Ire + " Out of " + Max_Ire + " Remains");
    }
    public int Get_Health() {
        return Current_Health;
    }
}