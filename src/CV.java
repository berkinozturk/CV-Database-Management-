import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class CV {


    public static void addCV(String name, String surname, String Education, String[] Languages, String[] Experiences, String[] Projects, String Department, String Address, int ID, String[] Competencies, String[] Certificates, Long PhoneNumber, Date LocalDate, String About){


        String sql = "CREATE FUNCTION add(name TEXT , y INTEGER) " +
                "RETURNS INTEGER " +
                "AS $$ SELECT x + y; $$ LANGUAGE SQL";
    }
    public static void openCV(){}
    public static void generateCV(String name, String surname, String Education, String[] Languages, String[] Experiences, String[] Projects, String Department, String Address, String[] Competencies, String[] Certificates, Long PhoneNumber, LocalDate LocalDate, String About){

        //These prevent getting an error when no value is entered.
        String Name = "not defined";
        String Surname = "not defined";
        String address = "not defined";
        String education = "not defined";
        String[] languages = new String[]{"not defined"};
        String[] experiences = new String[]{"not defined"};
        String[] projects = new String[]{"not defined"};
        String department = "not defined";
        String[] competencies = new String[]{"not defined"};
        String[] certificates = new String[]{"not defined"};
        long phoneNumber = 0;
        //LocalDate date = null; What is it and why we need this?
        String about = "not defined";

        //Get values if they defined.
        if (name != null){
            Name = name;
        } else if (surname != null) {
            Surname = surname;
        } else if (Address != null) {
            address = Address;
        } else if (Education != null) {
            education = Education;
        } else if (Languages != null) {
            languages = Languages;
        } else if (Experiences != null) {
            experiences = Experiences;
        } else if (Projects != null) {
            projects = Projects;
        } else if (Department != null) {
            department = Department;
        } else if (Competencies != null) {
            competencies = Competencies;
        } else if (Certificates != null) {
            certificates = Certificates;
        } else if (PhoneNumber != null) {
            phoneNumber = PhoneNumber;
        }
        //else if (LocalDate != null) {
        //    date = LocalDate;}
        else if (About != null) {
            about = About;
        }

        String template = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<title>Auto Generated CV</title>" +
                "</head>" +
                "<body>" +
                "<h1>CV</h1>" +
                "<h2>Personal Information</h2>" +
                "<p>Name: ${Name}</p>" +
                "<p>Surname: ${Surname}</p>" +
                "<p>Address: ${address}</p>" +
                "<p>Phone Number: ${phoneNumber}</p>" +
                "<p>Date: ${date}</p>" +
                "<h2>Education</h2>" +
                "<p>Education: ${education}</p>" +
                "<h2>Languages</h2>" +
                "<ul>" +
                "${languages}" +
                "</ul>" +
                "<h2>Experience</h2>" +
                "<ul>" +
                "${experiences}" +
                "</ul>" +
                "<h2>Projects</h2>" +
                "<ul>" +
                "${projects}" +
                "</ul>" +
                "<h2>Department</h2>" +
                "<p>Department: ${department}</p>" +
                "<h2>Competencies</h2>" +
                "<ul>" +
                "${competencies}" +
                "</ul>" +
                "<h2>Certificates</h2>" +
                "<ul>" +
                "${certificates}" +
                "</ul>" +
                "<h2>About</h2>" +
                "<p>${about}</p>" +
                "</body>" +
                "</html>";

        //Change the html with user inputs.
        template = template.replace("${Name}", Name);
        template = template.replace("${Surname}", Surname);
        template = template.replace("${address}", address);
        template = template.replace("${education}", education);
        template = template.replace("${phoneNumber}", String.valueOf(phoneNumber));
        //template = template.replace("${date}", date.toString());
        template = template.replace("${department}", department);
        template = template.replace("${about}", about);

        String languagesList = "";
        for (String language : languages) {
            languagesList += "<li>" + language + "</li>";
        }
        template = template.replace("${languages}", languagesList);

        String experiencesList = "";
        for (String experience : experiences) {
            experiencesList += "<li>" + experience + "</li>";
        }
        template = template.replace("${experiences}", experiencesList);

        String projectsList = "";
        for (String project : projects) {
            projectsList += "<li>" + project + "</li>";
        }
        template = template.replace("${projects}", projectsList);

        String competenciesList = "";
        for (String competency : competencies) {
            competenciesList += "<li>" + competency + "</li>";
        }
        template = template.replace("${competencies}", competenciesList);

        String certificatesList = "";
        for (String certificate : certificates) {
            certificatesList += "<li>" + certificate + "</li>";
        }
        template = template.replace("${certificates}", certificatesList);

        try {
            //This is the cv file in html format, we can send it to database.
            File CVfile = new File("cv.html");
            BufferedWriter writer = new BufferedWriter(new FileWriter(CVfile));
            writer.write(template);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Almost completed, I'll check back when the other parts are progressing.
    }
    public static void searchCV(){}
    public static void deleteCV(){}
    public static void updateCV(){}
    public static void printCV(File file){
    }
}
