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

    private static final List<SoundEvent> DEFAULT_PLAYLIST = List.of(
            Ssounds.FORSAKEN_FUTURE.value(),
            Ssounds.FORGOTTEN_PATIENT.value()
    );
    private static final List<SoundEvent> POST_PLAYLIST = List.of(
            Ssounds.CALAMITY_SPAWN.value()
    );


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
        CALAMITY(0,Ssounds.BIOBLOB.value()),
        VANGUARD(1,Ssounds.SIEGER_AMBIENT.value());

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