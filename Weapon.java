package pkg;
import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Weapon extends Item {
    int [] Base_Damage_Array;
    int [] Upgraded_Damage_Array;
    int [] Damage_Array_Scaling;
    double [] Base_Stat_Scalings;
    double [] Stat_Scalings;
    double [] Base_Guard_Array;
    double [] Upgraded_Guard_Array;
    int Base_Stability;
    int Upgraded_Stability;
    int Upgrade_Type;
    int Upgrade_Level;
    Attack [] Attacks;
    Weapon_Skill Weapon_Skill;
    Person User;
    
    public Weapon(int [] Base_Damage_Array, double [] Base_Stat_Scalings, double [] Base_Guard_Array, int Base_Stability, int Upgrade_Type, int Upgrade_Level, Attack [] Attacks, Weapon_Skill Weapon_Skill, double Weight, String Description, String Name, Person User) {
        this.Base_Damage_Array = Base_Damage_Array;
        Upgraded_Damage_Array = new int [Base_Damage_Array.length];
        Damage_Array_Scaling = new int [Base_Damage_Array.length];
        this.Base_Stat_Scalings = Base_Stat_Scalings;
        Stat_Scalings = new double [Base_Stat_Scalings.length];
        this.Base_Guard_Array = Base_Guard_Array;
        Upgraded_Guard_Array = new double [Base_Guard_Array.length];
        this.Base_Stability = Base_Stability;
        this.Attacks = Attacks;
        this.Weapon_Skill = Weapon_Skill;
        this.Weight = Weight;
        this.Description = Description;
        this.Name = Name;
        this.Upgrade_Type = Upgrade_Type;
        this.User = User;
        Upgrade(Upgrade_Level);
    }
    
     public void Upgrade(int Upgrade_Level) {
        int Max_Upgrade_Level = 0;
        switch(Upgrade_Type) {
            case 1: 
                Max_Upgrade_Level = 20;
                break;
            case 2:
                Max_Upgrade_Level = 15;
                break;
            case 3:
                Max_Upgrade_Level = 10;
                break;
        }
        if (Upgrade_Level > Max_Upgrade_Level) {
            Upgrade_Level = Max_Upgrade_Level;
        }
        int j = 0;
        System.out.println("Here are the upgraded Attack Damages: ");
        for (int i : Base_Damage_Array) {
            Upgraded_Damage_Array[j] = (int) (i + i * 1.5 * ( Upgrade_Level / Max_Upgrade_Level));
            System.out.print(Upgraded_Damage_Array[j] + ", ");
            j++;
        }
        System.out.println("");
        System.out.println("Here are the upgraded Stat Scalings: ");
        j = 0;
        for (double i : Base_Stat_Scalings) {
            Stat_Scalings[j] = i + i * .5 * (Upgrade_Level/Max_Upgrade_Level);
            System.out.print(Stat_Scalings[j] + ", ");
            j++;
        }
        System.out.println("");
        j = 0;
        System.out.println("Here are the upgraded Guards: ");
        for (double i : Base_Guard_Array) {
            Upgraded_Guard_Array[j] = i + i * .2 * (Upgrade_Level/Max_Upgrade_Level);
            System.out.print(Upgraded_Guard_Array[j] + ", ");
            j++;
        }
        Calculate_Damage(User.Get_Scaling_Levels());
    }
    
    public void Calculate_Damage(int [] Levels) {
         System.out.println("Here are the Spell Buffs with Scaling: ");
        int j = 0;
        for (int i = 0; i < 8; i++) {
            switch(j) {
                case 0:
                    Damage_Array_Scaling[j] = Upgraded_Damage_Array[j] + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[0] * Math.pow((Levels[0]/100.0), 1.2))
                    + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[1] * Math.pow((Levels[1]/100.0), 1.2));
                    System.out.print(Damage_Array_Scaling[j] + ", ");
                    break;
                case 1:
                    Damage_Array_Scaling[j] = Upgraded_Damage_Array[j] + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[2] * Math.pow((Levels[2]/100.0), 1.2));
                    System.out.print(Damage_Array_Scaling[j] + ", ");
                    break;
                case 2:
                    Damage_Array_Scaling[j] = Upgraded_Damage_Array[j] + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[2] * Math.pow((Levels[2]/100.0), 1.2))
                    + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[3] * Math.pow((Levels[3]/100.0), 1.2));
                    System.out.print(Damage_Array_Scaling[j] + ", ");
                    break;
                case 3:
                    Damage_Array_Scaling[j] = Upgraded_Damage_Array[j] + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[2] * Math.pow((Levels[2]/100.0), 1.2))
                    + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[3] * Math.pow((Levels[3]/100.0), 1.2));
                    System.out.print(Damage_Array_Scaling[j] + ", ");
                    break;
                case 4:
                    Damage_Array_Scaling[j] = Upgraded_Damage_Array[j] + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[2] * Math.pow((Levels[2]/100.0), 1.2))
                    + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[4] * Math.pow((Levels[4]/100.0), 1.2));
                    System.out.print(Damage_Array_Scaling[j] + ", ");
                    break;
                case 5:
                    Damage_Array_Scaling[j] = Upgraded_Damage_Array[j] + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[2] * Math.pow((Levels[2]/100.0), 1.2))
                    + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[4] * Math.pow((Levels[4]/100.0), 1.2));
                    System.out.print(Damage_Array_Scaling[j] + ", ");
                    break;
                case 6:
                    Damage_Array_Scaling[j] = Upgraded_Damage_Array[j] + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[3] * Math.pow((Levels[3]/100.0), 1.2));
                    System.out.print(Damage_Array_Scaling[j] + ", ");
                    break;
                case 7:
                    Damage_Array_Scaling[j] = Upgraded_Damage_Array[j] + (int) (Upgraded_Damage_Array[j] * Stat_Scalings[4] * Math.pow((Levels[4]/100.0), 1.2));
                    System.out.print(Damage_Array_Scaling[j] + ", ");
                    break;
            }
            j++;
        }
    }
    
    public void Block_Attack(int [] Damages, int Physical_Damage_Type, int Stamina_Damage) {
        int j = 0;
        for (int i : Damages) {
            i = (int) (i * (1 - Upgraded_Guard_Array[j] / 100));
        }
        User.Take_Damage(Damages, Physical_Damage_Type);
        User.Take_Stamina_Damage(Stamina_Damage);
    }
    
    public void Attack(int Attack_Input, Person Target) {
        switch(Attack_Input) {
            case 1:
                Attacks[0].Deal_Damage(Damage_Array_Scaling, Target);
                break;
            case 2:
                Attacks[1].Deal_Damage(Damage_Array_Scaling, Target);
                break;
            case 3:
                Attacks[0].Deal_Damage(Damage_Array_Scaling, Target);
                break;
            case 4:
                Weapon_Skill.Use_Skill(this, Target);
                break;
        }
    }
    
    public void Buff_Weapon(int Flat_Buff, double Buff_Multiplier, int Buff_Type) {
        Damage_Array_Scaling[Buff_Type - 1] = (int) ((Damage_Array_Scaling[Buff_Type - 1] + Flat_Buff) * Buff_Multiplier);
    }
    
    public int [] Get_Damage_Array() {
        return Damage_Array_Scaling;
    }
    
    
}