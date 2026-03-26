package com.Harbinger.Spore.Client.MusicManager;

import com.Harbinger.Spore.core.Ssounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class MenuMusicPlayer {

    private static SoundInstance currentMusic;

    private static final List<SoundEvent> MENU_TRACKS = List.of(
            Ssounds.ONCE_HERE.value()
    );

    private static final RandomSource random = RandomSource.create();

    public static void tick() {
        Minecraft mc = Minecraft.getInstance();
        mc.getMusicManager().stopPlaying();
        if (currentMusic != null && mc.getSoundManager().isActive(currentMusic)) return;
        SoundEvent track = MENU_TRACKS.get(random.nextInt(MENU_TRACKS.size()));

        currentMusic = SimpleSoundInstance.forMusic(track);
        mc.getSoundManager().play(currentMusic);
    }
}