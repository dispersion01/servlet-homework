package ru.nikolaeva.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Logger;

public class FileConroller {
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {

            try {

                byte[] fileBytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");
                System.out.println("Server rootPath: " + rootPath);
                System.out.println("File original name: " + file.getOriginalFilename());
                System.out.println("File content type: " + file.getContentType());

                File newFile = new File(rootPath + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
                stream.write(fileBytes);
                stream.close();

                System.out.println("File is saved under: " + rootPath + File.separator + file.getOriginalFilename());
                return "File is saved under: " + rootPath + File.separator + file.getOriginalFilename();

            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }

}
