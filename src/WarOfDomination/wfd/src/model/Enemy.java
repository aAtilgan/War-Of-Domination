package model;
/**
 * @author Ayberk
 *
 */
import org.newdawn.slick.SlickException;

public class Enemy extends Moving {

	public Enemy(int character_id) throws SlickException {
		super(character_id);
		setShiftX(520);
		setShiftY(520);
		setSpeed(.1f);
	}

	public String setRandomTalk(int rand) {
		switch (rand) {
		case 1:
			talk = "";
			break;
		case 2:
			talk = "Screw it, lets do this.";
			break;
		case 3:
			talk = "LEEEEEEROOOOOY JENKINSSSSSSSSS";
			break;
		case 4:
			talk = "Conquer from within.";
			break;
		case 5:
			talk = "Hold the door";
			break;
		case 6:
			talk = "I dont know why my code works";
			break;
		case 7:
			talk = "A bug in my code? It is a feature";
			break;
		case 8:
			talk = "I will smash you";
			break;
		case 9:
			talk = "The tempest is in your command";
			break;
		case 10:
			talk = "Give me your command.";
			break;

		}
		return talk;
	}
}
