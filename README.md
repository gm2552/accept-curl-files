# accept-curl-files
Tool for accepting files uploaded via CURL command.  It opens up an HTTP listener (no security) and places files into an "uploadFiles" diretory once they have been uploaded.

This project can either by run from maven or the maven wrapper (for systems that do not have maven installed).  The maven wrapper is the easiest and probably most portable method of running this application.  To run this application simply run the following command:

From Linux/Unix/MacOs:
```
./mvnw spring-boot:run
```

From Windows
```
mvnw spring-boot:run
```

To upload a file via curl to this application, use the following command (note the IP address or node name of the maching running this application):

```
curl -F file=@fileName http://ipaddress:8080/upload
```

For example: if you have file names readme.txt that you want to upload to IP address 192.168.1.6, use the following command:

```
curl -F file=@readme.txt http://192.168.1.6:8080/upload
```

Another example uploads the output of an application's stdout after the application has terminated.  Note that these outputs have no file name, so the application automatically names the file pipe<time in ms>.txt.  For example: lets say you try to out the stdout of the ALU retroplayer application.  The following example would upload the contents to a pipexxxx.txt file.
  
  ```
  /emulator/retroplayer ./emu/puae_libretro.so "./roms/Sinbad_v1.2_0083.hdf" | curl -F file=@-  http://192.168.1.6:8080/upload  ```
