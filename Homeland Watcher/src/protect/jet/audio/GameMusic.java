package protect.jet.audio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class GameMusic {
	// TODO add a game over song
	private static final String EPIC_BATTLE_PATH = "res/sounds/music/Epic_Battle - Sons Of Hades.mp3";
	private static final String BASIC_BATTLE_PATH = "res/sounds/music/Simple_Battle - Soara.mp3";
	private static final String MENU_MUSIC_PATH = "res/sounds/music/Menu_Music - The New Fools.mp3";
	private static final String GAME_OVER_PATH = "";

	private static SongType currentSong = SongType.None;
	private volatile static Player player = null;
	private static Thread songThread;

	public static enum SongType {
		EpicBattle, BasicBattle, MenuMusic, OverMusic, None;
	}

	private static void replaySong() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// Waits about every second to see if song is finished
				while (!player.isComplete()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// if song finished play again
				playSong(currentSong);
			}
		});
		thread.start();
	}
	
	private static void playThread() {
		songThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// Stop song if playing
				if (player != null)
					player.close();
				
				// Play song
				String songPath = getSongPath(currentSong);
				try {
					BufferedInputStream buffer = new BufferedInputStream(
							new FileInputStream(new File(songPath)));
					player = new Player(buffer);
					player.play();
					
					// replay song when ends
					replaySong();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		songThread.start();
	}

	public static void playSong(SongType type) {
		if (type == null || type == SongType.None) {
			currentSong = SongType.None;
			return;
		}
		currentSong = type;
		playThread();
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
		case OverMusic:
			songPath = GAME_OVER_PATH;
			break;
		default:
			songPath = "";
		}

		return songPath;
	}
	
	public static void stopCurrentSong() {
		if (player == null || player.isComplete())
			return;
		
		player.close();
		player = null;
	}

	public static SongType getCurrentSong() {
		return currentSong;
	}
}
