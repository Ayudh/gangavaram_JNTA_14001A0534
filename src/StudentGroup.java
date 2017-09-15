import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		// Add your implementation here
		return students;
//		return null;
	}

	@Override
	public void setStudents(Student[] students) {
		// Add your implementation here
		if (students==null)
			throw new IllegalArgumentException();
		this.students = students;
	}

	@Override
	public Student getStudent(int index) {
		// Add your implementation here
		if (index<0 || index>=students.length)
			throw new IllegalArgumentException();
		return students[index];
//		return null;
	}

	@Override
	public void setStudent(Student student, int index) {
		// Add your implementation here
		if (student==null)
			throw new IllegalArgumentException();
		if (index<0 || index>=students.length)
			throw new IllegalArgumentException();
		students[index] = student;
	}

	@Override
	public void addFirst(Student student) {
		// Add your implementation here
		if (student==null)
			throw new IllegalArgumentException();
		Student[] newStudents = new Student[students.length + 1];
		newStudents[0] = student;
		for (int i=0;i<students.length;i++) {
			newStudents[i + 1] = students[i];
		}
		students = newStudents;

	}

	@Override
	public void addLast(Student student) {
		// Add your implementation here
		if (student==null)
			throw new IllegalArgumentException();
		Student[] newStudents = new Student[students.length + 1];
		for (int i=0;i<students.length;i++) {
			newStudents[i] = students[i];
		}
		newStudents[newStudents.length-1] = student;
		students= newStudents;
	}

	@Override
	public void add(Student student, int index) {
		// Add your implementation here
		if (index<0 ||index>=students.length)
			throw new IllegalArgumentException();
		if (student==null)
			throw new IllegalArgumentException();
		Student[] newStudents = new Student[students.length + 1];
		for (int i=0;i<index;i++) {
			newStudents[i]= students[i];
		}
		newStudents[index] = student;
		for (int i=index;i<students.length;i++) {
			newStudents[i + 1] = students[i];
		}
		students = newStudents;
	}

	@Override
	public void remove(int index) {
		// Add your implementation here
		if (index<0 || index>=students.length)
			throw new IllegalArgumentException();
		Student[] newStudents = new Student[students.length - 1];
		for (int i=0;i<index;i++)
			newStudents[i] = students[i];
		for (int i=index+1;i<students.length;i++)
			newStudents[i-1] = students[i];
		students = newStudents;
	}

	@Override
	public void remove(Student student) {
		// Add your implementation here
		if (student==null)
			throw new IllegalArgumentException();
		for (int i=0;i<students.length;i++) {
			if (students[i].equals(student)) {
				remove(i);
				return;
			}
		}
		throw new IllegalArgumentException("Student not exist");
	}

	@Override
	public void removeFromIndex(int index) {
		// Add your implementation here
		if (index<0 || index>=students.length)
			throw new IllegalArgumentException();
		Student[] newStudents = new Student[index + 1];
		for (int i=0;i<=index;i++)
			newStudents[i] = students[i];
		students = newStudents;
	}

	@Override
	public void removeFromElement(Student student) {
		// Add your implementation here
		if (student==null)
			throw new IllegalArgumentException();
		for (int i=0;i<students.length;i++) {
			if (students[i].equals(student)) {
				removeFromIndex(i);
				return;
			}
		}

	}

	@Override
	public void removeToIndex(int index) {
		// Add your implementation here
		if (index<0 || index>=students.length)
			throw new IllegalArgumentException();
		Student[] newStudents = new Student[students.length-index];
		for (int i=0;i<newStudents.length;i++) {
			newStudents[i] = students[index + i];
		}
		students = newStudents;
	}

	@Override
	public void removeToElement(Student student) {
		// Add your implementation here
		if (student==null)
			throw new IllegalArgumentException();
		for (int i=0;i<students.length;i++) {
			if (students[i].equals(student)) {
				removeToIndex(i);
				return;
			}
		}
	}

	@Override
	public void bubbleSort() {
		// Add your implementation here
		for (int i=students.length-1;i>0;i--) {
			for (int j=0;j<i;j++) {
				if (students[j].compareTo(students[j+1])>0) {
					Student temp = students[j];
					students[j] = students[j+1];
					students[j+1] = temp;
				}
			}
		}
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		// Add your implementation here
		if (date==null)
			throw new IllegalArgumentException();
		List<Student> temp = new LinkedList<>();
		for (int i=0;i<students.length;i++) {
			if (students[i].getBirthDate().before(date) || students[i].getBirthDate().equals(date))
				temp.add(students[i]);
		}
		Student[] dates = new Student[temp.size()];
		int i=0;
		for (Student s : temp) {
			dates[i] = s;
			i++;
		}
		return dates;
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		// Add your implementation here
		if (firstDate==null || lastDate == null)
			throw new IllegalArgumentException();

		List<Student> temp = new LinkedList<>();
		for (int i=0;i<students.length;i++) {
			Date date = students[i].getBirthDate();
			if ((date.after(firstDate) && date.before(lastDate)) || date.equals(firstDate) ||
					date.equals(lastDate))
				temp.add(students[i]);
		}
		Student[] dates = new Student[temp.size()];
		int i=0;
		for (Student s : temp) {
			dates[i] = s;
			i++;
		}
		return dates;
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		// Add your implementation here
		Date startDate = date;
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DATE, days-1);
		Date endDate = c.getTime();
		return getBetweenBirthDates(startDate, endDate);
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		// Add your implementation here
		if (indexOfStudent<0 || indexOfStudent>=students.length)
			throw new IllegalArgumentException();

		Date birthdate = students[indexOfStudent].getBirthDate();
		Calendar birth = Calendar.getInstance();
        birth.setTime(birthdate);
        Calendar today = Calendar.getInstance();

        int yearDifference = today.get(Calendar.YEAR)
                - birth.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
            yearDifference--;
        } else {
            if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < birth
                            .get(Calendar.DAY_OF_MONTH)) {
                yearDifference--;
            }

        }

        return yearDifference;
	}

	public int getAge (Date birthDate) {
		Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);
        Calendar today = Calendar.getInstance();

        int yearDifference = today.get(Calendar.YEAR)
                - birth.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
            yearDifference--;
        } else {
            if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < birth
                            .get(Calendar.DAY_OF_MONTH)) {
                yearDifference--;
            }

        }

        return yearDifference;
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		// Add your implementation here
		List<Student> list = new LinkedList<>();
		for (int i=0;i<students.length;i++) {
			if (getAge(students[i].getBirthDate())== age)
				list.add(students[i]);
		}
		Student[] ans = new Student[list.size()];
		int i=0;
		for (Student s : list) {
			ans[i] = s;
			i++;
		}
		return ans;
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		// Add your implementation here

		Double max = Double.MIN_VALUE;
		for (int i=0;i<students.length;i++) {
			if (max<students[i].getAvgMark())
				max = students[i].getAvgMark();
		}

		List<Student> list = new LinkedList<>();
		for (int i=0;i<students.length;i++) {
			if (students[i].getAvgMark()==max)
				list.add(students[i]);
		}
		Student[] ans = new Student[list.size()];
		int i=0;
		for (Student s : list) {
			ans[i] = s;
			i++;
		}

		return ans;
	}

	@Override
	public Student getNextStudent(Student student) {
		// Add your implementation here
		if (student == null)
			throw new IllegalArgumentException();
		int i;
		for (i=0;i<students.length;i++) {
			if (students[i].equals(student))
				break;
		}
//		if (i==students.length-1)
//			throw new IllegalArgumentException();
		if (i==students.length-1)
			i=0;
		return students[i+1];
	}
}
