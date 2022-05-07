import java.io.*;

import java.util.Scanner;


public class SongLoad {

    public static CollectionBase.FileCollection readFolder(String patch, CollectionBase.BeatmapData bm) {

        File folder = new File(patch);
        File[] listOfFiles = folder.listFiles();

        CollectionBase.FileCollection f = new CollectionBase.FileCollection();

        //System.out.println("File list: ");
        for (File file : listOfFiles) {
            if (file.isFile()) {
                //System.out.println(file.getName());
                if (file.getName().toLowerCase().equals(bm.fileName.toLowerCase())) {

                    //System.out.println(file.getName());
                    String data;


                    try {
                        File myObj = new File(file.toString());
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine()) {
                            data = myReader.nextLine();

                            if( data.contains("AudioFilename") ){               //get mp4
                                f.audioFileName = data.substring(15);

                            }else if( data.equals("//Background and Video events") ){       //get background then break
                                data = myReader.nextLine();
                                f.background = data.substring(data.indexOf('"')+1,data.indexOf('"',data.indexOf('"')+1));
                                break;
                            }


                        }
                        myReader.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }








                }
            }


        }
        return f;

    }

}