package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class FileReader {

    public static void main(String[] args) {
        File file = new File("src/main/resources/Profile.txt");
        System.out.println(new FileReader().getDataFromFile(file));
    }

    public Profile getDataFromFile(File file) {
        Map<String, String> profile = new HashMap<>();
        List<String> strings = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
            stream.forEach(strings::add);

            for (String l : strings) {
                String[] keyVal = l.split(": ");
                profile.put(keyVal[0], keyVal[1]);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return new Profile(profile.get("Name"), Integer.parseInt(profile.get("Age")), profile.get("Email"), Long.parseLong(profile.get("Phone")));
    }
}
