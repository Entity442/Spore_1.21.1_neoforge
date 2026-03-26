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
    private static final RandomSource random = RandomSource.create();
    private static int battleMusicTicks;

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
    public static void tickMusic(){
        if (battleMusicTicks > 0){
            battleMusicTicks--;
            if (battleMusicTicks == 1){
                stopMusic();
            }
        }
    }

    public static void playRandomDefault() {
        SoundEvent pick = DEFAULT_PLAYLIST.get(random.nextInt(DEFAULT_PLAYLIST.size()));
        playMusic(pick,false);
    }
    public static void playRandomPreDefault() {
        SoundEvent pick = POST_PLAYLIST.get(random.nextInt(POST_PLAYLIST.size()));
        playMusic(pick,false);
    }

    public static void playMusic(SoundEvent music,boolean forceCut) {
        if (forceCut && (DEFAULT_PLAYLIST.contains(music) || POST_PLAYLIST.contains(music))){
            stopMusic();
            oldMusic = null;
        }
        if (forceCut && oldMusic != null && !oldMusic.equals(music)){
            stopMusic();
            oldMusic = null;
        }
        if (currentMusic != null && SoundManager.isActive(currentMusic)){
            return;
        }
        stopMusic();

        currentMusic = SimpleSoundInstance.forMusic(
                music
        );

        SoundManager.play(currentMusic);
        oldMusic = music;
    }

    public static void stopMusic() {
        if (currentMusic != null)
            SoundManager.stop(currentMusic);
    }

    public static void handlePacket(boolean pro, int id, boolean val) {
        if (val && id != -1){
            battleMusicTicks = 100;
            SoundEvent event = SongVariantsPerEntity.getVariant(id).getName();
            playMusic(event,true);
        }else {
            if (pro){
                playRandomPreDefault();
            }else {
                playRandomDefault();
            }
        }
    }



    public enum SongVariantsPerEntity {
        CALAMITY(0,Ssounds.VIRULENT_VIGIL.value()),
        VANGUARD(1,Ssounds.BANE_OF_SETTLEMENT.value());

        private static final SongVariantsPerEntity[] BY_ID = Arrays.stream(values()).sorted(Comparator.
                comparingInt(SongVariantsPerEntity::getId)).toArray(SongVariantsPerEntity[]::new);
        private final int id;
        private final SoundEvent name;

        SongVariantsPerEntity(int id, SoundEvent name) {
            this.id = id;
            this.name = name;
        }
        public SoundEvent getName(){
            return name;
        }

        public int getId() {
            return this.id;
        }

        public static SongVariantsPerEntity byId(int id) {
            return BY_ID[id % BY_ID.length];
        }
        public static SongVariantsPerEntity getVariant(int var) {
            return SongVariantsPerEntity.byId(var & 255);
        }
    }
}