import com.itextpdf.html2pdf.HtmlConverter;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class CV {


    public static void addCV(String Name, String Surname, String Education, String[] Languages, String[] Experiences,
                             String[] Projects, String Department, String Address, String[] Competencies,
                             String[] Certificates, Long PhoneNumber, String About, String path){


        // establish a connection to the database
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:Tag.db";
            conn = DriverManager.getConnection(url);

            Path filePath = Paths.get(path);
            byte[] fileData = Files.readAllBytes(filePath);

            // Insert the PDF file into the database
            String sql = "INSERT INTO Tag (Name, Surname, Education, Languages, Experiences, Projects, Department" +
                    ",Address, Competencies, Certificates, PhoneNumber, Date, About, CVFile) VALUES (?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,Name);
            pstmt.setString(2,Surname);
            pstmt.setString(3,Education);
            pstmt.setString(4, Arrays.toString(Languages));
            pstmt.setString(5, Arrays.toString(Experiences));
            pstmt.setString(6, Arrays.toString(Projects));
            pstmt.setString(7,Department);
            pstmt.setString(8, Address);
            pstmt.setString(9, Arrays.toString(Competencies));
            pstmt.setString(10, Arrays.toString(Certificates));
            pstmt.setDouble(11,PhoneNumber);
            pstmt.setString(12, String.valueOf(new Date()));
            pstmt.setString(13, About);
            pstmt.setBytes(14, fileData);

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }
    }

    public static void openCV(int id) {

        String Name ="not defined";
        String Surname ="not defined";
        String address = "not defined";
        String education ="not defined";
        String[] languages = new String[]{""};
        String[] experiences = new String[]{""};
        String[] projects = new String[]{""};
        String department = "not defined";
        String[] competencies = new String[]{""};
        String[] certificates = new String[]{""};
        long phoneNumber = 0;
        //LocalDate date = null;
        String about = "not defined";



        PreparedStatement stmt = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Tag.db");
            String sql = "SELECT * FROM Tag WHERE ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Name = rs.getString("Name");
                Surname = rs.getString("Surname");
                education = rs.getString("Education");
                languages = rs.getString("Languages").split(",");
                experiences = rs.getString("Experiences").split(",");
                projects = rs.getString("Projects").split(",");
                department = rs.getString("Department");
                address = rs.getString("Address");
                competencies = rs.getString("Competencies").split(",");
                certificates = rs.getString("Certificates").split(",");
                phoneNumber = rs.getLong("PhoneNumber");
                about = rs.getString("About");
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(languages[0]);



        OpenCVScreen openCVScreen = new OpenCVScreen(id, Name, Surname, education, languages, experiences, projects, department, address, competencies
                , certificates, phoneNumber, about);
        openCVScreen.setVisible(true);
    }
    public static void generateCV(String name, String surname, String Education, String[] Languages, String[] Experiences,
                                  String[] Projects, String Department, String Address, String[] Competencies,
                                  String[] Certificates, Long PhoneNumber, String About) {

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
        //TODO: This is here because of sorting algorithms maybe it can be as follow ==> CV has an Date attribute when
        // we import CV into System I will also add its current imported date and you can get it from it ??? -YİĞİT
        //LocalDate date = null;
        String about = "not defined";

        //Get values if they defined.
        if (name != null){
            Name = name;
        }
        if (surname != null) {
            Surname = surname;
        }
        if (Address != null) {
            address = Address;
        }
        if (Education != null) {
            education = Education;
        }
        if (Languages != null) {
            languages = Languages;
        }
        if (Experiences != null) {
            experiences = Experiences;
        }
        if (Projects != null) {
            projects = Projects;
        }
        if (Department != null) {
            department = Department;
        }
        if (Competencies != null) {
            competencies = Competencies;
        }
        if (Certificates != null) {
            certificates = Certificates;
        }
        if (PhoneNumber != null) {
            phoneNumber = PhoneNumber;
        }
        //else if (LocalDate != null) {
        //    date = LocalDate;}
        if (About != null) {
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

        StringBuilder languagesList = new StringBuilder();
        for (String language : languages) {
            languagesList.append("<li>").append(language).append("</li>");
        }
        template = template.replace("${languages}", languagesList.toString());

        StringBuilder experiencesList = new StringBuilder();
        for (String experience : experiences) {
            experiencesList.append("<li>").append(experience).append("</li>");
        }
        template = template.replace("${experiences}", experiencesList.toString());

        StringBuilder projectsList = new StringBuilder();
        for (String project : projects) {
            projectsList.append("<li>").append(project).append("</li>");
        }
        template = template.replace("${projects}", projectsList.toString());

        StringBuilder competenciesList = new StringBuilder();
        for (String competency : competencies) {
            competenciesList.append("<li>").append(competency).append("</li>");
        }
        template = template.replace("${competencies}", competenciesList.toString());

        StringBuilder certificatesList = new StringBuilder();
        for (String certificate : certificates) {
            certificatesList.append("<li>").append(certificate).append("</li>");
        }
        template = template.replace("${certificates}", certificatesList.toString());

        //This is the cv file in html format.
        File htmlFile = null;
        try {
            htmlFile = new File("cv.html");
            BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile));
            writer.write(template);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //We need to convert auto-generated html file to pdf because pdf is more common
        //so its more possible to open a pdf file without any other software requirement.
        try {

            FileInputStream inputStream = new FileInputStream("cv.html");
            FileOutputStream outputStream = new FileOutputStream("CVDocument.pdf");

            //Convert the HTML file to a PDF file
            //This line can give error. (solution: adding external library)
            HtmlConverter.convertToPdf(inputStream, outputStream);

            inputStream.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            htmlFile.delete();
        }

        //-------------------------------------Send to Database---------------------------------------------

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Tag.db")) {
            // Load the PDF file into memory
            Path filePath = Paths.get("CVDocument.pdf");
            byte[] fileData = Files.readAllBytes(filePath);

            // Insert the PDF file into the database
            String sql = "INSERT INTO Tag (Name, Surname, Education, Languages, Experiences, Projects, Department" +
                    ",Address, Competencies, Certificates, PhoneNumber, Date, About, CVFile) VALUES (?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1,Name);
                pstmt.setString(2,Surname);
                pstmt.setString(3,education);
                pstmt.setString(4, Arrays.toString(languages));
                pstmt.setString(5, Arrays.toString(Experiences));
                pstmt.setString(6, Arrays.toString(Projects));
                pstmt.setString(7,department);
                pstmt.setString(8, address);
                pstmt.setString(9, Arrays.toString(competencies));
                pstmt.setString(10, Arrays.toString(Certificates));
                pstmt.setDouble(11,phoneNumber);
                pstmt.setString(12, String.valueOf(new Date()));
                pstmt.setString(13, about);
                pstmt.setBytes(14, fileData);
                pstmt.executeUpdate();
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            //--------------------------------------Delete first created PDF-----------------------------------------
            File file = new File("CVDocument.pdf");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            file.delete();
        }



    }
    public static void searchCV(){}
    public static void deleteCV(int ID) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Connect to the database
            conn = DriverManager.getConnection("jdbc:sqlite:Tag.db");

            // Delete the entry from the CV table
            String sql = "DELETE FROM Tag WHERE Id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Close the connection and statement
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void updateCV(String Name, String Surname, String Education, String[] Languages, String[] Experiences,
                                String[] Projects, String Department, String Address, String[] Competencies, int ID,
                                String[] Certificates, Long PhoneNumber, String About){

        Connection connect = null;
        PreparedStatement state = null;
        try {
            // Connecting to the database
            connect = DriverManager.getConnection("jdbc:sqlite:Tag.db");

            //Update the variables
            state = connect.prepareStatement("UPDATE TAG set Name = ?, Surname=?, Education=?, Languages=?, " +
                    "Experiences=?, Projects=?, Department=?, Address=?, Competencies=?, Certificates=?," +
                    "PhoneNumber=?, Date=?, About=?  where ID=?");
            state.setString(1,Name);
            state.setString(2,Surname);
            state.setString(3,Education);
            state.setString(4,Arrays.toString(Languages));
            state.setString(5,Arrays.toString(Experiences));
            state.setString(6,Arrays.toString(Projects));
            state.setString(7,Department);
            state.setString(8,Address);
            state.setString(9,Arrays.toString(Competencies));
            state.setString(10,Arrays.toString(Certificates));
            state.setDouble(11,PhoneNumber);
            state.setString(12,String.valueOf(new Date()));
            state.setString(13,About);
            state.setInt(14,ID);

            state.executeUpdate();
            connect.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            // Close the connection and statement
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    public static void printCV(int ID){

        //--------------------------------------Get from Database--------------------------------------------

        File file = null;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Tag.db")) {
            // Retrieve the PDF file from the database
            String sql = "SELECT CVFile FROM Tag WHERE ID = ?";
            try (Statement stmt = conn.createStatement();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, ID);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    byte[] fileData = rs.getBytes("CVFile");

                    // Write the PDF file to the file system
                    Path filePath = Paths.get("test_retrieved.pdf");
                    Files.write(filePath, fileData);

                    //open the file
                    file = new File("test_retrieved.pdf");
                    Desktop.getDesktop().open(file);
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                Thread.sleep(1000);
                file.delete();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
  }}

}