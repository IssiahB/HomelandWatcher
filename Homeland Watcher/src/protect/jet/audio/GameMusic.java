package protect.jet.audio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class GameMusic {

	private static final String EPIC_BATTLE_PATH = "res/sounds/music/Epic_Battle - Sons Of Hades.mp3";
	private static final String BASIC_BATTLE_PATH = "res/sounds/music/Simple_Battle - Soara.mp3";
	private static final String MENU_MUSIC_PATH = "res/sounds/music/Menu_Music - The New Fools.mp3";
	
	private static volatile Player player = null;
	private static int totalSongLength = 0;
	private static BufferedInputStream input = null;
	private static boolean loop = false;

	private static SongType currentSong = SongType.None;
	private static SongState currentState = SongState.None;

	public static enum SongType {
		EpicBattle, BasicBattle, MenuMusic, None;
	}

	public static enum SongState {
		Play, Set, None;
	}

	public static void playSong() {
		if (player == null) {
			System.err.println("Must have a song set before playing!");
			return;
		}
		
		Thread play = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					do {
						player.play();
						player.close();
						setSong(currentSong, loop);
					} while (loop);
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		});
		play.start();
	}

	public static void setSong(SongType type, boolean loop) {
		if (type == null || type == SongType.None) {
			currentSong = SongType.None;
			currentState = SongState.None;
			totalSongLength = 0;
			GameMusic.loop = false;
			player = null;
			return;
		}
		
		if (player != null) 
			player.close();
		
		GameMusic.loop = loop;
		String songPath = getSongPath(type);
		try {
			input = new BufferedInputStream(new FileInputStream(new File(songPath)));
			player = new Player(input);
			totalSongLength = input.available();
			currentSong = type;
			currentState = SongState.Set;
		} catch (JavaLayerException | IOException e) {
			e.printStackTrace();
		}
	}

	public static String getSongPath(SongType type) {
		String songPath;
		switch (type) {
		case EpicBattle:
			songPath = EPIC_BATTLE_PATH;
			break;
		case BasicBattle:
			songPath = BASIC_BATTLE_PATH;
			break;
		case MenuMusic:
			songPath = MENU_MUSIC_PATH;
			break;
		default:
			songPath = "";
		}

		return songPath;
	}

	public static boolean isLoop() {
		return loop;
	}

	public static void setLoop(boolean loop) {
		GameMusic.loop = loop;
	}

	public static Player getPlayer() {
		return player;
	}

	public static int getTotalSongLength() {
		return totalSongLength;
	}

	public static SongType getCurrentSong() {
		return currentSong;
	}

	public static SongState getCurrentState() {
		return currentState;
	}
}
