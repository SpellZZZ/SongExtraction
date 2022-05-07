

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;

public class ReadSite  {


    public static CollectionBase.BeatmapData Reader(String str) throws IOException{

        String api = "";
        var url = new URL("https://osu.ppy.sh/api/get_beatmaps?k="+ api +"&h="+str);
        try (var br = new BufferedReader(new InputStreamReader(url.openStream()))) {

            int strL;
            String wholeName;
            String osuSongPatch = "D:/osu/Songs/";


            CollectionBase.BeatmapData beatmap = new CollectionBase.BeatmapData();
            String line;

            var sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            String s = sb.toString();
            int indexB;
            int indexE;

            if(s.length()>10){
                //id
                indexB = s.indexOf(':')+2;
                indexE = s.indexOf('"',indexB);
                beatmap.beatmapID = s.substring(indexB,indexE);
                //diff
                indexB = s.indexOf("version")+10;
                indexE = s.indexOf('"',indexB);
                beatmap.version = s.substring(indexB,indexE);
                //title
                indexB = s.indexOf("title")+8;
                indexE = s.indexOf('"',indexB);
                beatmap.title = s.substring(indexB,indexE);
                //author
                indexB = s.indexOf("artist")+9;
                indexE = s.indexOf('"',indexB);
                beatmap.artist = s.substring(indexB,indexE);
                //creator
                indexB = s.indexOf("creator")+10;
                indexE = s.indexOf('"',indexB);
                beatmap.creator = s.substring(indexB,indexE);
                //foldername
                beatmap.name = beatmap.beatmapID + " " + beatmap.artist + " - " + beatmap.title;
                beatmap.folderName = beatmap.name.replace(".","");
                beatmap.folderName = beatmap.folderName.replace(":","_");
                beatmap.folderName = beatmap.folderName.replace("~","_");
                //merge
                beatmap.fileName = beatmap.artist+ " - " + beatmap.title+" ("+beatmap.creator + ") ["+beatmap.version+"].osu";
                beatmap.fileName = beatmap.fileName.replace(":","");

                //cut folder name if needed
                strL = beatmap.folderName.length();
                if(strL>105) {
                    wholeName = beatmap.folderName.substring(0, 105);
                }else {
                    wholeName = beatmap.folderName.substring(0, strL);
                }

                beatmap.fullNameA = osuSongPatch+wholeName+"/";
                beatmap.fullNameB = osuSongPatch+wholeName+"/";
                //get background and mp4
                beatmap.fileTab = SongLoad.readFolder(osuSongPatch+wholeName,beatmap) ;
               // C:\Users\ZZZ\Desktop\songs


            }
            return beatmap;
        }
    }
}
