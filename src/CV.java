import com.itextpdf.html2pdf.HtmlConverter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Date;
public class CV {


    public static void addCV(File file, String name, String surname, String Education, String[] Languages, String[] Experiences,
                             String[] Projects, String Department, String Address, int ID, String[] Competencies,
                             String[] Certificates, Long PhoneNumber, Date LocalDate, String About){
    }
    public static void openCV(){}
    public static void generateCV(int ID, String name, String surname, String Education, String[] Languages, String[] Experiences,
                                  String[] Projects, String Department, String Address, String[] Competencies,
                                  String[] Certificates, Long PhoneNumber, String About) throws SQLException {

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
        }

        htmlFile.delete();

        //-------------------------------------Send to Database---------------------------------------------

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Tag.db")) {
            // Load the PDF file into memory
            Path filePath = Paths.get("CVDocument.pdf");
            byte[] fileData = Files.readAllBytes(filePath);

            // Insert the PDF file into the database
            String sql = "INSERT INTO Tag (ID, CVFile) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, ID);
                pstmt.setBytes(2, fileData);
                pstmt.executeUpdate();
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }


        //--------------------------------------Get from Database--------------------------------------------

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
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

        //--------------------------------------Delete first created PDF-----------------------------------------
        File file = new File("CVDocument.pdf");
        file.delete();

    }
    public static void searchCV(){}
    public static void deleteCV(String blob) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Connect to the database
            conn = DriverManager.getConnection("jdbc:sqlite:Tag.db");

            // Delete the entry from the CV table
            String sql = "DELETE FROM CV WHERE CV = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, blob);
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

    /*public static void updateCV(String name, String surname, String education, String[] languages, String[] experiences, String[] projects,
                                String department, String address, int ID, String[] competencies, String[] certificates,
                                Long phoneNumber, Date localDate, String about){
        //TODO burda yeni tag objeleri oluşturmuşsun ki sanırım yanlış anlaşılmayı anladım.
            adı üstünde tag eklemek demek herhangi bir türde bir şey ekleyebilirsin demek fakat bizim dizayn da tag
            isimden başlayıp numarasına kadar olan bilgiyi tek bir objede (Tag) toplamak demek
            kodunda değiştirmen gereken tek şey toplu tag objelerini silip onun yerine; databasemizden seçilen CV için
            eski ve yeni tag objelerini gelen parametreyle karşılaştırmak ve fark varsa onu yeniye güncellemek olmalı
        //
     */
        /*Connection conn = null;
        Statement stmt = conn.createStatement();

        String sql = "UPDATE table_name SET column1 = value1, column2 = value2 WHERE condition";
        stmt.executeUpdate(sql); '//'

        Scanner scan = new Scanner(System.in);
        //name
        name = scan.next();
        Tag nam = new Tag();
        nam.setName(name);
        //surname
        surname = scan.next();
        Tag surName = new Tag();
        surName.setSurname(surname);
        //education
        education = scan.next();
        Tag edu = new Tag();
        edu.setEducation(education);
        //languages
        String a = scan.nextLine();
        languages = a.split(" ");  // split the input string into an array of strings
        int[] newLan = new int[languages.length];  // create a new array with the same length as the input
        for (int i = 0; i < languages.length; i++) {
            newLan[i] = Integer.parseInt(languages[i]);  // parse each element of the input array and add it to the new array
        }
        Tag lan = new Tag();
        lan.setLanguages(languages);// update the myArray field with the new array
        //experiences
        String b = scan.nextLine();
        experiences = b.split(" ");
        int[] newEXp = new int[experiences.length];
        for (int i = 0; i < experiences.length; i++) {
            newEXp[i] = Integer.parseInt(experiences[i]);
        }
        Tag exp = new Tag();
        exp.setExperiences(experiences);
        //projects
        String c = scan.nextLine();
        projects = c.split(" ");
        int[] newPro = new int[projects.length];
        for (int i = 0; i < projects.length; i++) {
            newPro[i] = Integer.parseInt(projects[i]);
        }
        Tag proj = new Tag();
        proj.setProjects(projects);
        //department
        department = scan.next();
        Tag dep = new Tag();
        dep.setDepartment(department);
        //address
        address = scan.next();
        Tag addr = new Tag();
        addr.setAddress(address);
        //ID
        ID = scan.nextInt();
        Tag id = new Tag();
        id.setID(ID);
        //competencies
        String d = scan.nextLine();
        competencies = d.split(" ");
        int[] newCom = new int[competencies.length];
        for (int i = 0; i < competencies.length; i++) {
            newCom[i] = Integer.parseInt(competencies[i]);
        }
        Tag comp = new Tag();
        comp.setCompetencies(competencies);
        //certificates
        String e = scan.nextLine();
        certificates = e.split(" ");
        int[] newCer = new int[certificates.length];
        for (int i = 0; i < certificates.length; i++) {
            newCer[i] = Integer.parseInt(certificates[i]);
        }
        Tag cert = new Tag();
        cert.setCertificates(certificates);
        //phoneNumber
        phoneNumber = scan.nextLong();
        Tag ph = new Tag();
        ph.setPhoneNumber(phoneNumber);
        //date
        /*Tag dt = new Tag();
        Date date = new Date();  // creates a new Date object with the current date and time
        dt.setDate(date);???
        //about
        about = scan.next();
        Tag abt = new Tag();
        abt.setAbout(about);

    }*/

    public static void printCV(File file){
        //TODO
    }
}
