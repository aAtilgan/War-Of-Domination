package model.personmodel;

/**
 * @author Ayberk
 *
 */
import org.newdawn.slick.SlickException;

public class Character extends Moving {

	// constructor
	public Character(int character_id) throws SlickException {
		super(0);
		this.alive = true;
		talk = "";
		setSpeed(.1f);
	}

	// Random talk
	public String setRandomTalk(int rand) {
		switch (rand) {
		case 1:
			talk = "Scrum meetings are more fun than this game..";
			break;
		case 2:
			talk = "Let's fight!";
			break;
		case 3:
			talk = "Let's have a quick quiz.";
			break;
		case 4:
			talk = "Wanna be my friend?";
			break;
		case 5:
			talk = "I'm bored.";
			break;
		case 6:
			talk = "";
			break;
		case 7:
			talk = "Haters gonna hate.";
			break;
		case 8:
			talk = "";
			break;
		case 9:
			talk = "Eto'o Bitmis";
			break;
		case 10:
			talk = "Give me your command.";
			break;

		}
		return talk;
	}
}