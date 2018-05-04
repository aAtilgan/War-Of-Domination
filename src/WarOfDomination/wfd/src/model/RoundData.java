package model;

import java.util.ArrayList;

/**
 * @author Ayberk
 *
 */
public class RoundData {
	public static int round=1;
	
	public static String character_choice_hero="ch";
	public static String character_choice_enemy="ch1";
	
	public static ArrayList<Integer> weapon_choice_hero;
	public static ArrayList<Integer> weapon_choice_enemy;
	
	public static int choice_turn=0; // 0 stands for hero 1 stands for enemy
	
	public static int num_of_bullet_hero=0;
	public static int num_of_bullet_enemy=0;
	
	public static int given_damage_hero=0;
	public static int given_damage_enemy=0;
	
	private static RoundData r0und=new RoundData(); //singleton pattern
	
	RoundData(){
		 num_of_bullet_enemy=0;
		 num_of_bullet_hero=0;
		 choice_turn=0;
		 character_choice_hero="ch";
		 character_choice_enemy="ch1";
		 round=1;
	}
	
}
