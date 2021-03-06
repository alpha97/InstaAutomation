/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dotlab.software.instaautomation.Settings;

import com.dotlab.software.instaautomation.Reporter.CSVReport;
import java.util.prefs.*;
import com.dotlab.software.instaautomation.Scrapper.Entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.SystemUtils;


/**
 *
 * @author omandotkom
 */
public class ApplicationSettings {

    private final String PREF_USERNAME = "username";
    private final String PREF_PASSORD = "password";
    private final String PREF_CHROMEDRIVERPATH = "chrome_driver_path";
    private final String PREF_CONFIG_PATH = "config";
    private final String PREF_HEADLESS = "headless";
    private final String PREF_LIKE_INTERVAL = "likeInterval";
    private final String PREF_COMMENT_INTERVAL = "commentInterval";
    private final String PREF_FOLLOW_INTERVAL = "followInterval";
    private final String PREF_LIKE_BUTTTON = "likeButton";
    private static Preferences prefs;

    public ApplicationSettings() {
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    public void saveUser(User user) {
        prefs.put(PREF_USERNAME, user.getUsername());
        prefs.put(PREF_PASSORD, user.getPassword());
    }
    public void saveHeadless(boolean val){
    prefs.putBoolean(PREF_HEADLESS, val);
    }
    public boolean getHeadless(){
        //default not headless
    return prefs.getBoolean(PREF_HEADLESS, false);
    }
    public void saveLikeInterval(String likeInterval) {
        prefs.put(PREF_LIKE_INTERVAL, likeInterval);
    }

    public String getLikeInterval() {
        return prefs.get(PREF_LIKE_INTERVAL, "15,13,25,20,19");
    }

    public void saveCommentInterval(String commentInterval) {
        prefs.put(PREF_COMMENT_INTERVAL, commentInterval);
    }

    public void saveFollowInterval(String followInterval) {
        prefs.put(PREF_FOLLOW_INTERVAL, followInterval);
    }

    public String geetFollowInterval() {
        return prefs.get(PREF_FOLLOW_INTERVAL, "40,35,50,60");
    }

    public void saveConfigPath(String path) {
        prefs.put(PREF_CONFIG_PATH, path);
    }

    public String getConfigPath() {
        return prefs.get(PREF_CONFIG_PATH, "");
    }

    public User getUser() {
        User user = new User();
        user.setUsername(prefs.get(PREF_USERNAME, ""));
        user.setPassword(prefs.get(PREF_PASSORD, ""));
        return user;
    }

    public String getLikeButton() {
        return prefs.get(PREF_LIKE_BUTTTON, "coreSpriteHeartOpen");
    }

    public void saveLikeButton(String name) {
        prefs.put(PREF_LIKE_BUTTTON, name);
    }

    public String getChromeDriverPath() throws IOException, URISyntaxException {
        //   return prefs.get(this.PREF_CHROMEDRIVERPATH, "");
        if (getOperatingSystemSystemUtils().contains("windows")){
        return CSVReport.getJarPath() + "/chromedriver.exe";
        }
        return CSVReport.getJarPath() + "/chromedriver";
    }

    public void setChromeDriverPath(String path) {
        prefs.put(this.PREF_CHROMEDRIVERPATH, path);
    }

    public void clear() {
        try {
            this.prefs.clear();
        } catch (BackingStoreException ex) {
            Logger.getLogger(ApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isConfigValid() {
        try {
            File file = new File(getChromeDriverPath());
            if (file.exists()) {
                return true;
            }
        } catch (IOException ex) {
            return false;
            //Logger.getLogger(ApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            return false;
            //Logger.getLogger(ApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String getOperatingSystemSystemUtils() {
        String os = SystemUtils.OS_NAME;
        // System.out.println("Using SystemUtils: " + os);
        return os.toLowerCase();
    }
    public static void main(String[] args){
    System.out.println(getOperatingSystemSystemUtils());
    }

    /*   public boolean saveConfiguration() throws IOException {
        String filePath = getConfigPath();

        org.json.simple.JSONObject jso = new org.json.simple.JSONObject();
        jso.put(Config.VERSION_STRING, getConfig().getVersion());
        jso.put(Config.VALIDATION_STRING, getConfig().getValidation());
        jso.put(Config.LIKE_INTERVAL_STRING, getConfig().getLikeIntervalString());
        jso.put(Config.COMMENT_INTERVAL_STRING, "12");
        jso.put(Config.DB_PATH_STRING, getConfig().getDbPath());
        jso.put(Config.FOLLOW_INTERVAL, getConfig().getFollowInterval());
        jso.put(Config.LIKE_BUTTON, getConfig().getLikeButton());

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jso.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jso);
        } catch (IOException e) {
            System.out.println("Error while saving configuration file. --" + e.getMessage());
            return false;
        }
        return true;
    }

    private String readFile() throws FileNotFoundException, IOException {
        StringBuilder stringBuilder = new StringBuilder();

        if (this.isValidConfigFile(this.getConfigPath())) {
            //jika file config valid
            File file = new File(this.getConfigPath());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            if (bufferedReader.ready()) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

            }

        }
        return stringBuilder.toString();
    }
    private Config config;

    public Config getConfig() throws IOException {
        //return new ConfigParser(this.readFile()).getConfig();
        return config;
    }

    public boolean loadConfigFile() {
        try {
            System.out.println("Configuration file : " + this.getConfigPath());
            System.out.println("Loading configuration file ....");
            config = new ConfigParser(this.readFile()).getConfig();
            return true;
        } catch (IOException ex) {
            //      Logger.getLogger(ApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error while loding configuration file " + ex.getMessage());
            return false;
        }
    }
    private int emailsCount = 0;

    public int getEmailsCount() {
        return emailsCount;
    }

    public boolean isValidConfigFile(String path) {
        boolean valid = false;

        //jika tidak kosong
        File file = new File(path);
        if (file.exists() && !file.isDirectory()) {
            try {
                // jika file ada dan bukan folder
                Tika tika = new Tika();
                String mimeType = tika.detect(file);
                if (mimeType.equals("application/json")) {
                    //means it's a valid json

                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    if (bufferedReader.ready()) {
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                        ConfigParser configParser = new ConfigParser(stringBuilder.toString());
                        if (configParser.isValidJSON()) {
                            configParser.parse();
                            if (configParser.getConfig().getValidation().equals("61a78b6297c33bba4c3d8e4825444de5afc0c3c15ddd33fd8b2482945bacffac")) {
                                valid = true;

                            }
                        }
                    }
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);
                valid = false;
            }
        } else {
            valid = false;
        }

        return valid;
    }

    public boolean isValidEmailList(String path) {
        boolean result = true;
        File file = new File(path);
        if (file.exists() && !file.isDirectory()) {

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                if (bufferedReader.ready()) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (!new EmailValidator().validateEmail(line)) {
                            return false;
                        }
                        this.emailsCount++;
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return result;
    }

    /**
     * Java email validation program
     *
     * @author pankaj
     *
     */
    class EmailValidator {
        // Email Regex java

        private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

        // static Pattern object, since pattern is fixed
        private Pattern pattern;

        // non-static Matcher object because it's created from the input String
        private Matcher matcher;

        public EmailValidator() {
            // initialize the Pattern object
            pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        }

        /**
         * This method validates the input email address with EMAIL_REGEX
         * pattern
         *
         * @param email
         * @return boolean
         */
        public boolean validateEmail(String email) {
            matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }

}
