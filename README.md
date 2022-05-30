# OsuSongExtractor

## General info

Read binary file "collection.db"  
  
Elements list:
  * Int	Version 
  * Int	Number of collections
  * 
  * String	Name of the collection
  * Int	Number of beatmaps in the collection
  * String*	Beatmap MD5 hash. Repeated for as many beatmaps as are in the collection.
  * 
  
Then download data from site (example: https://osu.ppy.sh/api/get_beatmaps?k="+ api +"&h="+ MD5)  
Get useful informations and search the folders, then copy to selected folder  






### TODO
* graphic interface
* class arrangement
* md5 hashes export to json file
* id3 tag edit 
