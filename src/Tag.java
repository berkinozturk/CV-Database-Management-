import java.time.LocalDate;

public class Tag {
    private String name;
    private String surname;
    private String education;
    private String[] languages;
    private String[] experiences;
    private String[] projects;
    private String department;
    private String address;
    private int ID;
    private String[] competencies;
    private String[] certificates;
    private long phoneNumber;
    private LocalDate date;
    private String about;

    public Tag(String name, String surname, String education, String[] languages, String[] experiences, String[] projects, String department, String address, int ID, String[] competencies, String[] certificates, long phoneNumber, LocalDate date, String about) {
        this.name = name;
        this.surname = surname;
        this.education = education;
        this.languages = languages;
        this.experiences = experiences;
        this.projects = projects;
        this.department = department;
        this.address = address;
        this.ID = ID;
        this.competencies = competencies;
        this.certificates = certificates;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.about = about;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String[] getExperiences() {
        return experiences;
    }

    public void setExperiences(String[] experiences) {
        this.experiences = experiences;
    }

    public String[] getProjects() {
        return projects;
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String[] getCompetencies() {
        return competencies;
    }

    public void setCompetencies(String[] competencies) {
        this.competencies = competencies;
    }

    public String[] getCertificates() {
        return certificates;
    }

    public void setCertificates(String[] certificates) {
        this.certificates = certificates;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
