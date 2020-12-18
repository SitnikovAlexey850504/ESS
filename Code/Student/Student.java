package Student;

import java.util.Comparator;

public class Student implements Comparable<Student>{
    private String surname;
    private String name;
    private String patronymic;
    private String faculty;
    private String specialty;
    private String course;
    private String group;
    private String form;
    private String studentCard;

    private float score;


    public Student(){
        this.surname = null;
        this.name = null;
        this.patronymic = null;
        this.faculty = null;
        this.specialty = null;
        this.course = null;
        this.group = null;
        this.form = null;
        this.studentCard = null;
    }

    public Student(Student student) {
        this.surname = new String(student.getSurname());
        this.name = new String(student.getName());
        this.patronymic = new String(student.getPatronymic());
        this.faculty = new String(student.getFaculty());
        this.specialty = new String(student.getSpecialty());
        this.course = new String(student.getCourse());
        this.group = new String(student.getGroup());
        this.form = new String(student.getForm());
        this.studentCard =  new String(student.getStudentCard());
    }

    public Student(String _surname, String _name, String _patronymic, String _faculty, String _specialty, String _course, String _group, String _form, String _studentCard) {
        surname = _surname;
        name = new String(_name);
        patronymic = new String(_patronymic);
        faculty = new String(_faculty);
        specialty = new String(_specialty);
        course = new String(_course);
        group = new String(_group);
        form = new String(_form);
        studentCard =  new String(_studentCard);
    }


    public String getSurname(){
        return surname;
    }
    public String getName(){
        return name;
    }
    public String getPatronymic(){
        return patronymic;
    }
    public String getFaculty(){
        return faculty;
    }
    public String getSpecialty(){
        return specialty;
    }
    public String getCourse(){
        return course;
    }
    public String getGroup(){
        return group;
    }
    public String getForm(){
        return form;
    }
    public String getStudentCard(){
        return studentCard;
    }
    public float getScore(){ return score; }

    public void setSurname(String _surname){
        surname = _surname;
    }
    public void setName(String _name){
        name = _name;
    }
    public void setPatronymic(String _patronymic){
        patronymic = _patronymic;
    }
    public void setFaculty(String _faculty){
        faculty = _faculty;
    }
    public void setSpecialty(String _specialty){
        specialty = _specialty;
    }
    public void setCourse(String _course){
        course = _course;
    }
    public void setGroup(String _group){
        group = _group;
    }
    public void setForm(String _form){
        form = _form;
    }
    public void setStudentCard(String _studentCard){
        studentCard = _studentCard;
    }
    public void setScore(int mark1, int mark2, int mark3, int mark4, int mark5){
        score = (float)(mark1 + mark2 + mark3 + mark4 + mark5) / 5;
    }

    @Override
    public int compareTo(Student st) {
        int res = compare_string(this.getSurname(), st.getSurname());

        if (res == 1) return 1;
        if (res == 2) return 0;

        res = compare_string(this.getName(), st.getName());

        return res == 1 ? 1 : 0;
    }

    private int compare_string(String s1, String s2) {
        for (int i = 0; i < s1.length() && i < s2.length(); i++){
            if (s1.charAt(i) < s2.charAt(i))
            {
                return 1;
            }
            else
            {
                if (s1.charAt(i) > s2.charAt(i))
                {
                    return 2;
                }
            }
        }

        if (s1.length() < s2.length()) return 1;
        else if (s1.length() > s2.length()) return 2;
        return 0;
    }
}
