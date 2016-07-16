package com.vigi.ruby.challenge.subtitle;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Iterator;

/**
 * Created by vigi on 7/16/2016.
 */
public class FixSubtitles {

    public FixSubtitles() {
    }

    public static void main(String[] args) throws IOException {
        String path = args.length > 0 ? args[0] : ".";
        Path folder = Paths.get(path, new String[0]);
        System.out.println("--- Folder: " + folder.getFileName());
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("--- Default charset is: " + defaultCharset);
        DirectoryStream ds = Files.newDirectoryStream(folder, "*.{srt,sub}");

        try {
            Iterator it = ds.iterator();
            while (it.hasNext()) {
                Path file = (Path) it.next();
                replaceInFile(file);
            }
        } finally {
            if (ds != null) {
                try {
                    ds.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void replaceInFile(Path file) throws IOException {
        System.out.println("--- Replacing subtitle file: " + file.getFileName());
        Charset charset = StandardCharsets.ISO_8859_1;
        String content = (new String(Files.readAllBytes(file)))
                .replaceAll("º", "s")
                .replaceAll("þ", "t")
                .replaceAll("ª", "S")
                .replaceAll("Þ", "T");
        Files.write(file, content.getBytes(), new OpenOption[0]);
    }

}
