import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class MoveMP3 {


    int i = 1;

    public static void moveFile(CollectionBase.BeatmapData src) throws IOException {
        String des = "C:/Users/ZZZ/Desktop/songs";
        //"D:/osu/Songs/";
        File source = new File(src.fullNameA);
        File dest = new File(des);
        try {
            FileUtils.copyToDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();

        }

        Path newName = Paths.get(des + "/" + src.fileTab.audioFileName);


        if (!Files.exists(newName.resolveSibling(src.title + ".mp3"))) {
            Files.move(newName, newName.resolveSibling(src.title + ".mp3"));
        } else {
            int i = 1;
            while (true) {

                if (!Files.exists(newName.resolveSibling(src.title + i + ".mp3"))) {
                    Files.move(newName, newName.resolveSibling(src.title + i + ".mp3"));
                    break;
                }
                i++;
            }

        }

    }
}
