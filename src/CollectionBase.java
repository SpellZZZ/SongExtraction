import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.farng.mp3.TagException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class CollectionBase {
    public static void main(String[] args) throws IOException, TagException, InvalidDataException, UnsupportedTagException, NotSupportedException {


        ArrayList<BeatmapData> bmMain = new ArrayList<>();
        BeatmapData item;
        CollectionReader.ReadFile();
        Scanner scan = new Scanner(System.in);



        int ind, cnt = 0;

        CollectionReader obj = new CollectionReader("collection.db");

        CollectionReader.CollectionDB db = obj.readCollectionDB();

        System.out.println("Select collection: ");
        for(int i=0;i<db.collectionCount;i++){
            System.out.println(i+" "+db.collections.get(i).name);
        }

        ind = scan.nextInt();


        System.out.println(db.collections.get(ind).name);
                for(String s :  db.collections.get(ind).md5Hashes )
                {
                    item = ReadSite.Reader(s);
                    bmMain.add(item);
                }

        for(String s :  db.collections.get(ind).md5Hashes )
        {
            //item = ReadSite.Reader(s);
            System.out.println(s);
        }


        for(BeatmapData s :  bmMain )
        {
            if(s.beatmapID.equals("Error")  && s.title.equals("Error")  && s.version.equals("Error") ) {
                cnt++;
            }else{
                System.out.println("Set ID: "+s.beatmapID);
                System.out.println("Title: "+s.title);
                System.out.println("Artist: "+s.artist);
                System.out.println("Creator: "+s.creator);
                System.out.println("Version: "+s.version);
                System.out.println("Folder name: " + s.folderName);
                System.out.println("File name: " + s.fileName);
                System.out.println("Audio file: "+ s.fileTab.audioFileName);
                System.out.println("Background file: "+ s.fileTab.background);
                //System.out.println("Background file s: "+ s.fullNameB);
                System.out.println("---");
                s.fullNameA+=s.fileTab.audioFileName;
                s.fullNameB+=s.fileTab.background;
                MoveMP3.moveFile(s);
                //SongEdit.songEdit(s); kiedys dodac

            }
        }




        System.out.println("Error count: "+ cnt);


    }



    public static class BeatmapData {
        public String beatmapID = "Error";
        public String title = "Error";
        public String artist = "Error";
        public String creator = "Error";
        public String version = "Error";
        public String folderName = "Error";
        public String name = "Error";
        public String fileName = "Error";
        public FileCollection fileTab;
        public String fullNameA = "Error";
        public String fullNameB = "Error";

    }
    public static class FileCollection {
        public String audioFileName = "Error";
        public String background = "Error";
    }


}







