package pkg;
import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Attack {
    
    //Damage Multipliers of the Attacks
    ArrayList<Double> Motion_Values;
    //Name of the Attack
    String Name;
    //Physical Damage Type of the Attack. 1 == slash, 2 == thrust, 3 == blunt. 0 == None.
    int Damage_Type;
    //Stamina damage if attack is blocked
    int Stamina_Damage;
    
    public Attack(ArrayList<Double> Motion_Values, String Name, int Damage_Type, int Stamina_Damage) {
        this.Motion_Values = Motion_Values;
        this.Name = Name;
        this.Damage_Type = Damage_Type;
        this.Stamina_Damage = Stamina_Damage;
    }
    
    //Takes in a Damage Array (From a weapon), and deals damage to the specified Target Character. 
    public void Deal_Damage(int[] Damage_Array, Person Target) {
        int [] Damage_Holder = new int [Damage_Array.length];
        int j;
        for (double i : Motion_Values) {
            j = 0;
            for (int Damage : Damage_Array) {
                Damage_Holder[j] = (int) (Damage * i);
                j++;
            }
            Target.Take_Damage(Damage_Holder, Damage_Type);
        }
    }
    
    //Get Methods
    public ArrayList<Double> Get_Motion_Values() {
        return Motion_Values;
    }
    public String Get_Name() {
        return Name;
    }
    public int Get_Damage_Type() {
        return Damage_Type;
    }
    public Attack Get_Attack_Info() {
        return this;
    }
    public int Get_Stamina_Damage() {
        return Stamina_Damage;
    }
}