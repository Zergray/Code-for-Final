import pkg.*;
import java.util.Scanner;
import java.util.Random;
import java.util.*;


class starter {
	public static void main(String args[]) {
		int[] Levels = {50,50,50,50,50,50,50,50,50};
		Person Player = new Person(Levels);
	    int[] Damages = {100,0,0,0,0,0,100,0,0};
	    double[] Scalings = {1.0,1.0,1.0,1.0,1.0};
	    double[] Guards = {50.0,50.0,50.0,50.0,50.0,50.0,50.0,50.0,50.0};
	    Attack [] Attack_Array = new Attack [3];
	    ArrayList<Double> New_Attack_MVs = new ArrayList<Double>();
	    New_Attack_MVs.add(1.0);
	    New_Attack_MVs.add(1.5);
	    Attack New_Attack = new Attack(New_Attack_MVs, "New Attack", 1, 50);
	    Attack New_Attack2 = new Attack(New_Attack_MVs, "New Attack", 2, 50);
	    Magical_Attack New_Magical_Attack = new Magical_Attack(New_Attack_MVs, "New Magical Attack", 7, 50);
	    ArrayList<Attack> Weapon_Skill_Attack_List = new ArrayList<Attack>();
	    Attack Weapon_Skill_Attack = new Attack(New_Attack_MVs, "New Attack", 3, 50);
	    Weapon_Skill_Attack_List.add(Weapon_Skill_Attack);
	    Weapon_Skill New_Weapon_Skill = new Weapon_Skill(Weapon_Skill_Attack_List, 20, 20, 1.1, 7, Player, "No Description", "New Weapon Skill");
	    Attack_Array[0] = New_Attack;
	    Attack_Array[1] = New_Magical_Attack;
	    Attack_Array[2] = New_Attack2;
	    Weapon New_Weapon = new Weapon(Damages, Scalings, Guards, 60, 1, 20, Attack_Array, New_Weapon_Skill, 10.0, "No Description", "New Weapon", Player);
	    ArrayList<Integer> New_Spell_Damages = new ArrayList<Integer>();
	    New_Spell_Damages.add(100);
	    New_Spell_Damages.add(200);
	    Spell New_Spell = new Spell(New_Spell_Damages, "New Spell", 6, 6, 20, 5, "No Description", Player);
	    Spell Alt_New_Spell = new Spell(New_Spell_Damages, "New Spell", 1, 1, 20, 5, "No Description", Player);
	    boolean[] Allowed_Schools = {true,true,true,true,true,true,true,true};
	    int[] School_Spell_Buffs = {100,100,100,100,100,100,100,100};
	    double[] Staff_Stat_Scalings = {1.0,1.0,1.0,1.0,1.0};
	    int[] Staff_Requirements = {1,1,1,1,1};
	    int [] Flat_Defenses = {50,50,50,50,50,50,50,50,50,50,50};
	    double [] Percent_Defenses = {10.0,10.0,10.0,10.0,10.0,10.0,10.0,10.0,10.0,10.0,10.0};
	    int [] Flat_Defenses2 = {25,25,25,25,25,25,25,25,25,25,25};
	    double [] Percent_Defenses2 = {5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0,5.0};
	    
	    Armor New_Helmet = new Armor(Percent_Defenses, Flat_Defenses, 1, 5.0, "No Description", "New Helmet");
	    Armor New_Chest = new Armor(Percent_Defenses2, Flat_Defenses2, 2, 2.5, "No Description", "New Helmet");
	    Player.Equip_Armor(New_Helmet, New_Helmet.Get_Armor_Slot());
	    Player.Equip_Armor(New_Chest, New_Chest.Get_Armor_Slot());
	    
	    System.out.println("This is the Current load %: " + Player.Calculate_Weight_Load());
	    
	    Staff New_Staff = new Staff(Allowed_Schools, School_Spell_Buffs, Staff_Stat_Scalings, Staff_Requirements, New_Spell, 1.0, "No Description", "New Staff", Player, 1, 20);
	    New_Spell.Cast_Spell(New_Staff, Player);
	    Player.Rest();
	    New_Weapon.Attack(4, Player);
	    Player.Rest();
	    New_Weapon.Attack(3, Player);
	    Player.Rest();
	    New_Weapon.Attack(2, Player);
	    Player.Rest();
	    New_Weapon.Block_Attack(Damages, 1, New_Attack.Get_Stamina_Damage());
	}
}