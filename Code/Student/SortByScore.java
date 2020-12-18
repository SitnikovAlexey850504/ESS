package Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByScore implements SortStudents{
    @Override
    public  void sortStudents(ArrayList<Student> students){
        Collections.sort(students, new CustomComparator());
    }

    public class CustomComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getScore() <= o2.getScore() ? 1 : -1;
        }
    }
}
