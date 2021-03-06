package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;

/**.
 * Task 7.3.4.
 * Create aplication for search file in the directory
 *
 * @author  Anton Vasilyuk 30.07.2017.
 * @version 1.0.
 */
@ThreadSafe
public class SearchFile {

    /**.
     * @files is container for found the files
     */
    @GuardedBy("this")
    private List<String> files;

    /**.
     * @passed is container for passed directory
     */
    private Set<String> passed;

    /**.
     * @root is root path
     */
    private String root;

    /**.
     * @text is text for searching
     */
    private String text;

    /**.
     * @exts is paths
     */
    private List<String> exts;

    /**.
     * Constructor for this class
     * @param root is directory for search
     * @param text is text for search
     * @param exts is list for extension
     */
    public SearchFile(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
        passed = Collections.synchronizedSet(new HashSet<String>());
        files = Collections.synchronizedList(new ArrayList<String>());
    }

    /**.
     * This method is start this app
     * @throws InterruptedException may be exception
     */
    public void startApp() throws InterruptedException {
        File file = new File(root);
        Thread threadOne = searchThread(file);
        Thread threadTwo = searchThread(file);
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
    }

    /**.
     * Get result
     * @return result
     */
    public List<String> result() {
            System.out.println(files);
            return files;
    }

    /**.
     * Take searcher
     * @param file file for checking
     * @return thread worker
     */
    private Thread searchThread(File file) {
        return new Thread() {
            @Override
            public void run() {
                try {
                    recursiveSearchFile(file);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**.
     * Recursive method for search and write files contains text
     * @param file is file for search
     * @throws InterruptedException may be exception
     * @throws FileNotFoundException may be exception
     */
    private void recursiveSearchFile(File file) throws InterruptedException, FileNotFoundException {
        if (!passed.contains(file.getPath()) && !files.contains(file.getPath())) {
            if (file.isFile()) {
                if (checkExtension(file)) {
                    searchAndWrite(file);
                    passed.add(file.getPath());
                }
            } else {
                File[] arrayFile = file.listFiles();
                for (int i = 0; i < arrayFile.length; i++) {
                    recursiveSearchFile(arrayFile[i]);
                    passed.add(file.getPath());
                }
            }
        }
    }

    /**.
     * Method for checking files
     * @param file is file for checking
     * @return if the file
     */
    private boolean checkExtension(File file) {
        String name = file.getName();
        int index = name.indexOf('.');
        String extensionFile = name.substring(index + 1);
        return exts.contains(extensionFile) ? true : false;
    }

    /**.
     * Method for searching and write files to result list
     * @param file is file for action
     * @throws FileNotFoundException may be exception
     */
    private void searchAndWrite(File file) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String s;
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                br.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String name = file.getPath();
        int num = sb.indexOf(text);
        if (num != -1) {
            files.add(name);
        }
    }

    /**.
     * Getting result
     * @return result
     */
    public List<String> getResult() {
        return this.files;
    }
}
