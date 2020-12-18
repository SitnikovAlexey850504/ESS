package Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByAlphabet implements SortStudents{
    @Override
    public  void sortStudents(ArrayList<Student> students){
        Collections.sort(students, new CustomComparator());
    }

    public class CustomComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return (o1.getSurname() + o1.getName()).compareTo(o2.getSurname() + o2.getName());
        }
    }
}
