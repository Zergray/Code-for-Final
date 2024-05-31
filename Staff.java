package pkg;
import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Staff extends Item {
    //List of booleans which say if it can use certain schools of magic
    boolean [] Allowed_Schools;
    //List of base magic spellbuffs for each school
    int [] Base_School_Spell_Buffs;
    //List of upgraded magic spellbuffs for each school
    int [] Upgraded_School_Spell_Buffs;
    //List of actual magic spellbuffs for each school
    int [] School_Spell_Buffs;
    //List of base stat scalings
    double [] Base_Stat_Scalings;
    //List of upgraded stat scalings
    double [] Stat_Scalings;
    //Cantrip/base spell
    Spell Cantrip;
    //List of stat requirements
    int [] Stat_Requirements;
    //User
    Person User;
    //Upgrade type. 1 == normal, 2 == Unique, 3 == Boss
    int Upgrade_Type;
    //Upgrade level. Max is 20 for normal, 15 Unique, and 10 Boss.
    int Upgrade_Level;
    
    public Staff (boolean [] Allowed_Schools, int [] Base_School_Spell_Buffs, double [] Base_Stat_Scalings, int [] Stat_Requirements, Spell Cantrip, double Weight, String Description, String Name, Person User, int Upgrade_Type, int Upgrade_Level) {
        this.Allowed_Schools = Allowed_Schools;
        this.Base_School_Spell_Buffs = Base_School_Spell_Buffs;
        Upgraded_School_Spell_Buffs = new int [Base_School_Spell_Buffs.length];
        School_Spell_Buffs = new int [Base_School_Spell_Buffs.length];
        this.Base_Stat_Scalings = Base_Stat_Scalings;
        Stat_Scalings = new double [Base_Stat_Scalings.length];
        this.Stat_Requirements = Stat_Requirements;
        this.Cantrip = Cantrip;
        this.Weight = Weight;
        this.Description = Description;
        this.Name = Name;
        this.User = User;
        this.Upgrade_Type = Upgrade_Type;
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
        System.out.println("Here are the upgraded Spell Buffs: ");
        for (int i : Base_School_Spell_Buffs) {
            Upgraded_School_Spell_Buffs[j] = (int) (i + i * 1.5 * ( Upgrade_Level / Max_Upgrade_Level));
            System.out.print(Upgraded_School_Spell_Buffs[j] + ", ");
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
        Calculate_Spell_Buffs(User.Get_Scaling_Levels());
    }
    
    public void Calculate_Spell_Buffs(int [] Levels) {
        System.out.println("Here are the Spell Buffs with Scaling: ");
        int j = 0;
        for (int i = 0; i < 8; i++) {
            switch (j) {
                case 0:
                    School_Spell_Buffs[j] = Upgraded_School_Spell_Buffs[j] + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[0] * Math.pow((Levels[0]/100.0), 1.2))
                    + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[1] * Math.pow((Levels[1]/100.0), 1.2));
                    System.out.print(School_Spell_Buffs[j] + ", ");
                    break;
                case 1:
                    School_Spell_Buffs[j] = Upgraded_School_Spell_Buffs[j] + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[2] * Math.pow((Levels[2]/100.0), 1.2));
                    System.out.print(School_Spell_Buffs[j] + ", ");
                    break;
                case 2:
                    School_Spell_Buffs[j] = Upgraded_School_Spell_Buffs[j] + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[2] * Math.pow((Levels[2]/100.0), 1.2))
                    + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[3] * Math.pow((Levels[3]/100.0), 1.2));
                    System.out.print(School_Spell_Buffs[j] + ", ");
                    break;
                case 3:
                    School_Spell_Buffs[j] = Upgraded_School_Spell_Buffs[j] + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[2] * Math.pow((Levels[2]/100.0), 1.2))
                    + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[3] * Math.pow((Levels[3]/100.0), 1.2));
                    System.out.print(School_Spell_Buffs[j] + ", ");
                    break;
                case 4:
                    School_Spell_Buffs[j] = Upgraded_School_Spell_Buffs[j] + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[2] * Math.pow((Levels[2]/100.0), 1.2))
                    + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[4] * Math.pow((Levels[4]/100.0), 1.2));
                    System.out.print(School_Spell_Buffs[j] + ", ");
                    break;
                case 5:
                    School_Spell_Buffs[j] = Upgraded_School_Spell_Buffs[j] + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[2] * Math.pow((Levels[2]/100.0), 1.2))
                    + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[4] * Math.pow((Levels[4]/100.0), 1.2));
                    System.out.print(School_Spell_Buffs[j] + ", ");
                    break;
                case 6:
                    School_Spell_Buffs[j] = Upgraded_School_Spell_Buffs[j] + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[3] * Math.pow((Levels[3]/100.0), 1.2));
                    System.out.print(School_Spell_Buffs[j] + ", ");
                    break;
                case 7:
                    School_Spell_Buffs[j] = Upgraded_School_Spell_Buffs[j] + (int) (Upgraded_School_Spell_Buffs[j] * Stat_Scalings[4] * Math.pow((Levels[4]/100.0), 1.2));
                    System.out.print(School_Spell_Buffs[j] + ", ");
                    break;
            }
            j++;
        }
        System.out.println("");
    }
    
    public boolean Meets_Requirements(int [] Levels) {
        int j = 0;
        for (int i : Levels) {
            if (i < Stat_Requirements[j]) {
                return false;
            }
            j++;
        }
        return true;
    }
    
    public boolean Can_Cast(int School) {
        return Allowed_Schools[School];
    }
    
    public void Cast_Spell(Spell Spell, Person Target) {
        if (Meets_Requirements(User.Get_Scaling_Levels()) && Can_Cast(Spell.Get_School())) {
            Spell.Cast_Spell(this, Target);
        } else {
            System.out.println("This spell cannot be cast");
        }
    }
    
    public int Get_Spell_Buff(int School) {
        switch (School) {
            case 1:
                return School_Spell_Buffs[0];
            case 2:
                return School_Spell_Buffs[1];
            case 3:
                return School_Spell_Buffs[2];
            case 4:
                return School_Spell_Buffs[3];
            case 5:
                return School_Spell_Buffs[4];
            case 6:
                return School_Spell_Buffs[5];
            case 7:
                return School_Spell_Buffs[6];
            case 8:
                return School_Spell_Buffs[7];
        }
        return 0;
    }
}