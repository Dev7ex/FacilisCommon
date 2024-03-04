package com.dev7ex.common.io.file;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Dev7ex
 * @since 18.07.2022
 */
public class Files {

    private Files() {}

    public static boolean containsFile(final File folder, final String fileName) {
        final File[] files = folder.listFiles();

        if (files == null) {
            return false;
        }

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                continue;
            }
            if(!files[i].getName().equalsIgnoreCase(fileName)) {
                continue;
            }
            return true;
        }
        return false;
    }

    public static List<File> getFiles(final File folder) {
        if (!folder.isDirectory()) {
            throw new UnsupportedOperationException(folder.getName() + " is not a directory");
        }
        return List.of(Objects.requireNonNull(folder.listFiles()));
    }

    public static List<String> toStringList(final File file) {
        return Arrays.stream(Objects.requireNonNull(file.listFiles())).filter(File::isDirectory).map(File::getName).collect(Collectors.toList());
    }

}