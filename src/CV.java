import java.util.Date;

public class CV {


    public static void addCV(String name, String surname, String Education, String[] Languages, String[] Experiences, String[] Projects, String Department, String Address, int ID, String[] Competencies, String[] Certificates, Long PhoneNumber, Date LocalDate, String About){


        String sql = "CREATE FUNCTION add(name TEXT , y INTEGER) " +
                "RETURNS INTEGER " +
                "AS $$ SELECT x + y; $$ LANGUAGE SQL";
    }
    public static void openCV(){}
    public static void generateCV(){}
    public static void searchCV(){}
    public static void deleteCV(){}
    public static void updateCV(){}
    public static void printCV(){}
}
