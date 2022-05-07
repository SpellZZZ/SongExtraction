import com.mpatric.mp3agic.*;
import org.apache.commons.io.FileUtils;
import org.farng.mp3.AbstractMP3Tag;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.*;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class SongEdit {

    static public void songEdit(CollectionBase.BeatmapData src) throws TagException, IOException, NotSupportedException, InvalidDataException, UnsupportedTagException {

        String des = "C:/Users/ZZZ/Desktop/songs";
        String des2 = "C:/Users/ZZZ/Desktop/songs/asd";
        //MP3File mp3file = new MP3File(des +"/"+src.fileTab.audioFileName);
         //MP3File mp3file = new MP3File(des +"/"+src.title+".mp3");
        // String image = "C:/Users/ZZZ/Desktop/songs";


        /*File img = new File("img.jpg");
        imgLink[1]="http://"+imgLink[1];
        URL url = new URL(imgLink[1]);
        FileUtils.copyURLToFile(url, img);
        byte[] bytes = new byte[(int) img.length()];
        id3v2Tag.setTitle("Something");
        id3v2Tag.setAlbumImage(bytes, "image/jpg");
        mp3file.save("something.mp3");

*/

        /*Mp3File song = new Mp3File(des +"/"+src.title+".mp3");
        File img = new File(src.fullNameB+src.fileTab.background);

        if (song.hasId3v2Tag()){
            ID3v2 id3v2tag = song.getId3v2Tag();
            BufferedImage img2 = ;
            byte[] imageData = id3v2tag.setAlbumImage(img);
            //converting the bytes to an image

        }*/

        Mp3File song = new Mp3File(des +"/"+src.title+".mp3");


        ID3v2 id3v2Tag;
        if (song.hasId3v2Tag()) {
            id3v2Tag = song.getId3v2Tag();
        } else {
            // mp3 does not have an ID3v2 tag, let's create one..
            id3v2Tag = new ID3v24Tag();
            song.setId3v2Tag(id3v2Tag);
        }



        File img = new File((src.fullNameB + src.fileTab.background));
        byte[] bytes = new byte[(int) img.length()];
        id3v2Tag.setTitle(src.title);
        id3v2Tag.setArtist(src.artist);
        id3v2Tag.setComment("");
        id3v2Tag.setGenre(1);
        id3v2Tag.setAlbum(" ");
        id3v2Tag.setAlbumImage(bytes, "image/jpeg");
        //id3v2Tag.se

        System.out.println(bytes);
        song.save(des2 +"/"+src.title+".mp3");













        //System.out.println("AAAAAAAAAAAAAAAAAA 3v1: " + mp3file.hasID3v1Tag());
        //System.out.println("AAAAAAAAAAAAAAAAAA 3v2: " + mp3file.hasID3v2Tag());



       // MP3File song = new MP3File(des +"/"+src.title+".mp3");
      //  if (song.hasID3v2Tag()){
        //    FrameBodyAPIC pic = new FrameBodyAPIC();



         //   ID3v2_3 id3v2tag = song.getID3v2Tag();


           // byte[] imageData = id3v2tag.set;
            //converting the bytes to an image
          //  BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
        }



/*
         ID3v2_3 id3v2_3Tag;
        if (mp3file.hasID3v2Tag()) {
            id3v2_3Tag =  mp3file.getID3v2Tag();
        } else {
            id3v2_3Tag = new ID3v2_3();
            mp3file.setID3v2Tag(id3v2_3Tag);
        }


        AbstractID3v2Frame frame = ID3v2_3.getFrame("APIC");

        // frame is always null
        if (frame == null) {
            frame = new ID3v2_3Frame();
        }

        FrameBodyAPIC frameBody = (FrameBodyAPIC) frame.getBody();

        if (frameBody == null) {
            frameBody = new FrameBodyAPIC();
        }

        frameBody.setObject("Picture Data", image);
        frameBody.setObject("MIME Type", "image/png");
        frame.setBody(frameBody);

        // Set the newly created frame to mp3 file
        id3v2_3Tag.setFrame(frame);

        // frame is still null - even just after setting it!
        frame = id3v2_3Tag.getFrame("APIC");
*/

        //set new name


      /*  ID3v1 id3v1Tag = new ID3v1();;
        if (mp3file.hasID3v1Tag()) {
            id3v1Tag =  mp3file.getID3v1Tag();
        } else {
            id3v1Tag = new ID3v1();
            mp3file.setID3v1Tag(id3v1Tag);
        }

        FrameBodyAPIC pic = new FrameBodyAPIC();
        pic.setObject(src.fullNameB, mp3file);

        id3v1Tag.setArtist(src.artist);
        id3v1Tag.setTitle(src.title);
        id3v1Tag.setAlbum(" ");
        id3v1Tag.setYear(" ");
        id3v1Tag.setComment(" ");

        mp3file.save(des +"/"+src.title+".mp3");
        //mp3file.save(des+"/"+src.title+".mp3");
    */}

