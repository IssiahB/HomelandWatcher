package protect.jet.audio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameSoundEffects {
	
	private static List<Clip> allGameSounds = new ArrayList<Clip>();
	
	public static final String PLAYER_LASER_SOUND_PATH = "res/sounds/effects/Player_Laser.wav";
	public static final String PLAYER_EXPLOSION_SOUND_PATH = "res/sounds/effects/Player_Explosion.wav";
	public static final String PLAYER_HIT_SOUND_PATH = "res/sounds/effects/Player_Hit.wav";
	
	public static final String ENEMY_EXPLOSION_SOUND_PATH = "res/sounds/effects/Enemy_Explosion.wav";

	public static Clip getSoundEffect(String path) {
		Clip soundClip = null;
		try {
			soundClip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
			soundClip.open(inputStream);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		
		allGameSounds.add(soundClip);
		return soundClip;
	}
	
	public static Clip getEnemyExplosion() {
		return (getSoundEffect(ENEMY_EXPLOSION_SOUND_PATH));
	}
	
	public static Clip getPlayerHit() {
		return (getSoundEffect(PLAYER_HIT_SOUND_PATH));
	}
	
	public static Clip getPlayerExplosion() {
		return (getSoundEffect(PLAYER_EXPLOSION_SOUND_PATH));
	}
	
	public static Clip getPlayerLaser() {
		return (getSoundEffect(PLAYER_LASER_SOUND_PATH));
	}
	
	public static void cleanup() {
		for(Clip clip : allGameSounds)
			clip.close();
	}
}
