package pkg;
import java.util.Scanner;
import java.util.Random;
import java.util.*;

public class Magical_Attack extends Attack {

    //Magical Damage Type of the attack 1 == Physical 2 == Magic 3 == Fire 4 == Lightning 5 == Poison 6 == Ice 7 == Light 8 == Dark
    int Magic_Damage_Type;

    public Magical_Attack(ArrayList<Double> Motion_Values, String Name, int Magic_Damage_Type, int Stamina_Damage) {
        super(Motion_Values, Name, 0, Stamina_Damage);
        this.Magic_Damage_Type = Magic_Damage_Type;
    }
    
    //Takes in a Damage Array (From a weapon), and deals damage to the specified Target Character. 
    public void Deal_Damage (int[] Damage_Array, Person Target) {
        for(double i : Motion_Values) {
            Target.Take_Magic_Damage((int)(Damage_Array[Magic_Damage_Type - 1] * i), Magic_Damage_Type);
        }
    }
    
    //Get Method
    public int Get_Magic_Damage_Type() {
        return Magic_Damage_Type;
    }
}