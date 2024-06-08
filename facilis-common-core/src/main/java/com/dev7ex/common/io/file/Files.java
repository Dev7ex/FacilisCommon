package com.dev7ex.common.io.file;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utility class for file operations.
 */
public final class Files {

    private Files() {
    }

    /**
     * Checks if the specified folder contains a file with the given name.
     *
     * @param folder   The folder to search in.
     * @param fileName The name of the file to check for.
     * @return True if the folder contains the file, false otherwise.
     */
    public static boolean containsFile(final File folder, final String fileName) {
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("Specified file is not a directory: " + folder.getAbsolutePath());
        }

        final File[] files = Objects.requireNonNull(folder.listFiles());

        for (final File file : files) {
            if (file.isFile() && file.getName().equalsIgnoreCase(fileName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieves a list of files in the specified folder.
     *
     * @param folder The folder to retrieve files from.
     * @return A list of files in the folder.
     * @throws IllegalArgumentException if the specified file is not a directory.
     */
    public static List<File> getFiles(final File folder) {
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("Specified file is not a directory: " + folder.getAbsolutePath());
        }
        return Arrays.asList(Objects.requireNonNull(folder.listFiles()));
    }

    /**
     * Converts the names of all subdirectories of the specified file to a list of strings.
     *
     * @param file The file containing subdirectories.
     * @return A list of subdirectory names.
     */
    public static List<String> toStringList(final File file) {
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Specified file is not a directory: " + file.getAbsolutePath());
        }
        return Arrays.stream(Objects.requireNonNull(file.listFiles(File::isDirectory)))
                .map(File::getName)
                .collect(Collectors.toList());
    }

}
