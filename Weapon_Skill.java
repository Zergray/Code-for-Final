package pkg;
import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Weapon_Skill {
    //List of Attacks in the Weapon Skill
    ArrayList<Attack> Attack_List;
    //Ire Cost
    int Ire_Cost;
    //Flat Buff. If 0, no buff.
    int Flat_Buff;
    //% Buff. If 0, no buff.
    double Buff_Multiplier;
    //Type of buff. 1 == Physical 2 == Magic 3 == Fire 4 == Lightning 5 == Poison 6 == Ice 7 == Light 8 == Dark
    int Buff_Type;
    //User
    Person User;
    //Weapon Skill Description
    String Description;
    //Weapon Skill Name
    
    
    public Weapon_Skill(ArrayList<Attack> Attack_List, int Ire_Cost, int Flat_Buff, double Buff_Multiplier, int Buff_Type, Person User, String Description, String Name) {
        this.Attack_List = Attack_List;
        this.Ire_Cost = Ire_Cost;
        this.Flat_Buff = Flat_Buff;
        this.Buff_Multiplier = Buff_Multiplier;
        this.Buff_Type = Buff_Type;
        this.User = User;
        this.Description = Description;
    }
    
    public void Buff_Weapon(Weapon Weapon) {
        Weapon.Buff_Weapon(Flat_Buff, Buff_Multiplier, Buff_Type);
    }
    
    public void Use_Skill(Weapon Weapon, Person Target) {
        if(User.Get_Ire() >= Ire_Cost) {
            Buff_Weapon(Weapon);
            for (Attack i : Attack_List) {
                i.Deal_Damage(Weapon.Get_Damage_Array(), Target);
            }
            User.Use_Ire(Ire_Cost);
        }
    }
    
    public ArrayList<Attack> Get_Attack_List() {
        return Attack_List;
    }
    public int Get_Ire_Cost() {
        return Ire_Cost;
    }
    public int Get_Flat_Buff() {
        return Flat_Buff;
    }
    public double Get_Buff_Multiplier() {
        return Buff_Multiplier;
    }
    public int Get_Buff_Type() {
        return Buff_Type;
    }
    public Weapon_Skill Get_Weapon_Skill_Info() {
        return this;
    }
}