package com.Harbinger.Spore.Client.MusicManager;

import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SporeMusicPlayer {

    private static final SoundManager SoundManager = Minecraft.getInstance().getSoundManager();

    private static SoundInstance currentMusic;
    private static SoundEvent oldMusic;

    // ===== MUSIC STATE =====
    private static SoundEvent battleMusic;   // null = not in combat
    private static boolean postPhase;        // false = default playlist, true = post playlist
    private static int battleMusicTicks;
    private static int worldUpdateDelay;

    private static final RandomSource random = RandomSource.create();

    // ===== AMBIENT PLAYLISTS =====
    private static final List<SoundEvent> DEFAULT_PLAYLIST = List.of(
            Ssounds.BICENTENNIAL.value(),
            Ssounds.CYCLE_OF_EVOLUTION.value(),
            Ssounds.DESOLATION.value(),
            Ssounds.FALL_OF_MAN.value(),
            Ssounds.MANMADE_HORRORS.value(),
            Ssounds.MYCONOCLAST.value(),
            Ssounds.NOURISHMENT.value(),
            Ssounds.PROJECT_REGENESIS.value(),
            Ssounds.RECLAIMATION.value(),
            Ssounds.RESTLESS_REACH.value(),
            Ssounds.ROADS_ONCE_TRAVELLED.value(),
            Ssounds.SLEEPLESS_DREAMING.value(),
            Ssounds.START_ANEW.value(),
            Ssounds.THE_SOIL_TALKS.value(),
            Ssounds.THEY_AWAKEN.value(),
            Ssounds.THEY_GROW_BELOW.value()
    );

    private static final List<SoundEvent> POST_PLAYLIST = List.of(
            Ssounds.BROKEN_REFLECTION.value(),
            Ssounds.DECAY.value(),
            Ssounds.ENDLESS_FEAST.value(),
            Ssounds.MYCONAUT.value(),
            Ssounds.NATURAL_OCCURANCE.value(),
            Ssounds.NEUROGENESIS.value(),
            Ssounds.PROTOTYPE.value(),
            Ssounds.REPURPOSED.value(),
            Ssounds.ROT.value(),
            Ssounds.SOMETHING_ONCE_GREAT.value(),
            Ssounds.SPORE_BURST_SONG.value(),
            Ssounds.SYNAPTIC_RELAPSE.value(),
            Ssounds.THEY_LISTEN.value(),
            Ssounds.WHAT_WE_BECOME.value(),
            Ssounds.WHISPERS.value()
    );

    // =========================================================
    // ===================== TICK LOOP ==========================
    // =========================================================

    public static void tickMusic() {

        // ===== COMBAT ACTIVE =====
        if (battleMusicTicks > 0) {
            battleMusicTicks--;

            if (battleMusic != null) {
                playMusic(battleMusic);
            }

            // combat just ended
            if (battleMusicTicks <= 1) {
                battleMusic = null;
                stopMusic();
            }

            return; // block ambient while fighting
        }
        if (worldUpdateDelay > 0) {
            worldUpdateDelay--;
        }
        // ===== AMBIENT MODE =====
        if (currentMusic == null || !SoundManager.isActive(currentMusic)) {
            if (postPhase) {
                playRandomPost();
            } else {
                playRandomDefault();
            }
        }
    }

    // =========================================================
    // ===================== PLAY HELPERS =======================
    // =========================================================

    private static void playRandomDefault() {
        SoundEvent pick = DEFAULT_PLAYLIST.get(random.nextInt(DEFAULT_PLAYLIST.size()));
        playMusic(pick);
    }

    private static void playRandomPost() {
        SoundEvent pick = POST_PLAYLIST.get(random.nextInt(POST_PLAYLIST.size()));
        playMusic(pick);
    }

    private static void playMusic(SoundEvent music) {
        // prevent restarting same track
        if (currentMusic != null && SoundManager.isActive(currentMusic) && music.equals(oldMusic))
            return;

        stopMusic();

        currentMusic = SimpleSoundInstance.forMusic(music);
        SoundManager.play(currentMusic);
        oldMusic = music;
    }

    private static void stopMusic() {
        if (currentMusic != null)
            SoundManager.stop(currentMusic);
    }

    // =========================================================
    // ===================== NETWORK PACKET =====================
    // =========================================================

    public static void handlePacket(boolean pro, int id, boolean inCombat) {

        if (worldUpdateDelay > 0){
            postPhase = pro;
            worldUpdateDelay = 200;
        }

        // ===== COMBAT START / REFRESH =====
        if (inCombat && id >= 0) {
            battleMusicTicks = 200;
            battleMusic = SongVariantsPerEntity.getVariant(id).getName();
            return;
        }

        // ===== COMBAT END =====
        battleMusicTicks = 0;
        battleMusic = null;
    }

    // =========================================================
    // ================= ENTITY COMBAT TRACKS ===================
    // =========================================================

    public enum SongVariantsPerEntity {
        CALAMITY(0, Ssounds.MYCONOCLAST.value()),
        VANGUARD(1, Ssounds.BANE_OF_SETTLEMENT.value()),
        VIGIL(2, Ssounds.VIRULENT_VIGIL.value());

        private static final SongVariantsPerEntity[] BY_ID =
                Arrays.stream(values())
                        .sorted(Comparator.comparingInt(SongVariantsPerEntity::getId))
                        .toArray(SongVariantsPerEntity[]::new);

        private final int id;
        private final SoundEvent name;

        SongVariantsPerEntity(int id, SoundEvent name) {
            this.id = id;
            this.name = name;
        }

        public SoundEvent getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public static SongVariantsPerEntity byId(int id) {
            return BY_ID[id % BY_ID.length];
        }

        public static SongVariantsPerEntity getVariant(int var) {
            return byId(var & 255);
        }
    }
}