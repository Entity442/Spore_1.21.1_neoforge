package com.Harbinger.Spore.Client.MusicManager;

import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
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

    private static SporeMusicInstance currentMusic;
    private static SoundEvent oldMusic;

    // ===== MUSIC STATE =====
    private static SoundEvent battleMusic;
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
            Ssounds.THEY_GROW_BELOW.value(),
            Ssounds.MYCONOCLAST.value()

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
            Ssounds.SPORE_BURST_SONG.value(),
            Ssounds.SYNAPTIC_RELAPSE.value(),
            Ssounds.THEY_LISTEN.value(),
            Ssounds.WHAT_WE_BECOME.value(),
            Ssounds.WHISPERS.value(),
            Ssounds.MENTAL_MUTILATION.value()
    );

    // =========================================================
    // ===================== TICK LOOP ==========================
    // =========================================================

    public static void tickMusic() {

        // Tick currently playing music
        if (currentMusic != null) {
            currentMusic.tick();
        }

        if (worldUpdateDelay > 0) {
            worldUpdateDelay--;
        }
        if (battleMusicTicks > 0) {
            battleMusicTicks--;

            if (battleMusic != null && oldMusic != battleMusic) {
                playMusic(battleMusic);
            }
            if (battleMusicTicks == 60 && oldMusic == battleMusic) {
                stopMusic();
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
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen instanceof TitleScreen) {
            return;
        }
        if (currentMusic != null && SoundManager.isActive(currentMusic) && music.equals(oldMusic))
            return;

        stopMusic();

        currentMusic = new SporeMusicInstance(music);
        currentMusic.fadeIn();

        SoundManager.play(currentMusic);
        oldMusic = music;
    }

    private static void stopMusic() {
        if (currentMusic != null && !currentMusic.isStopped()) {
            currentMusic.fadeOut();
        }
    }

    // =========================================================
    // ===================== NETWORK PACKET =====================
    // =========================================================

    public static void handlePacket(boolean pro, int id, boolean inCombat) {
        if (pro && id == 3){
            currentMusic = null;
            playMusic(Ssounds.SOMETHING_ONCE_GREAT.value());
            return;
        }
        if (inCombat && id >= 0) {
            battleMusicTicks = 200;
            battleMusic = SongVariantsPerEntity.getVariant(id).getName();
            return;
        }
        battleMusicTicks = 0;
        battleMusic = null;
        if (currentMusic == null || !SoundManager.isActive(currentMusic)) {
            if (pro) {
                playRandomPost();
            } else {
                playRandomDefault();
            }
        }
    }

    // =========================================================
    // ================= ENTITY COMBAT TRACKS ===================
    // =========================================================

    public enum SongVariantsPerEntity {
        CALAMITY(0, Ssounds.MYCOPHOBIA.value()),
        VANGUARD(1, Ssounds.BANE_OF_SETTLEMENT.value()),
        VIGIL(2, Ssounds.VIRULENT_VIGIL.value()),
        PROTO(3, Ssounds.SOMETHING_ONCE_GREAT.value());

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