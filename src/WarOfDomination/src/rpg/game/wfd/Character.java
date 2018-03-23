package rpg.game.tutorial;

import org.newdawn.slick.SlickException;

public class Character extends Moving{
	
	public Character(int character_id) throws SlickException {
		super(0);
		this.alive = true;
		talk="";
		setShiftX(300);
		setShiftY(300);
		setSpeed(.1f);
	}

	public void setRandomTalk() {
		int rand = (int)(Math.random()*10 + 1);
		switch (rand) {
        case 1:  talk = "Only Van Helsing can hunt me.";
        break;
        case 2:  talk = "Still a better love story than twilight.";
        break;
        case 3:  talk = "Trolololololo.";
        break;
        case 4:  talk = "Wanna be my friend?";
        break;
        case 5:  talk = "I'm bored.";
        break;
        case 6:  talk = "I have a dream.";
        break;
        case 7:  talk = "Haters gonna hate.";
        break;
        case 8:  talk = "The internet is dark and full of spoilers.";
        break;
        case 9: talk = 	"Eto'o is done.";
        break;
        case 10: talk = "Give me your command.";
        break;
		}
		
	}
}