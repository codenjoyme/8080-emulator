package spec.sound.test;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;

/**
 * Пример получения всех форматов PCM аудио, поддерживаемых устройствами в системе.
 * Полезно для понимания, какие форматы аудио поддерживаются на вашем устройстве.
 * Класс для экспериментов с форматами аудио.
 */
public class AllPCMFormats {

    public static void main(String[] args) {
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();

        for (Mixer.Info mixerInfo : mixerInfos) {
            System.out.println("Mixer: " + mixerInfo.getName() + " [" + mixerInfo.getDescription() + "]");
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getSourceLineInfo();

            for (Line.Info lineInfo : lineInfos) {
                System.out.println("\tSource Line: " + lineInfo);
                if (lineInfo instanceof DataLine.Info) {
                    DataLine.Info dataLineInfo = (DataLine.Info) lineInfo;
                    AudioFormat[] supportedFormats = dataLineInfo.getFormats();

                    for (AudioFormat format : supportedFormats) {
                        System.out.println("\t\tSupported Audio Format: " + format);
                    }
                }
            }
        }
    }
}