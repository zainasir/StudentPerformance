package StudentPerformance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class StudentPerformance {
	private static Set<Student> roster = new TreeSet<>();
	private static Map<String, Course> courseList = new TreeMap<>();
	private static Map<Student, List<Course>> studentRecord = new HashMap<>();
	private static Map<Student, Double> studentGPAs = new HashMap<>();

	private static Course[] courses = {
			new Course(4, "ANTH 256 Native American Culture and History"),
			new Course(4, "CHEM 107 General Chem I"),
			new Course(4, "CHEM 231 Organic Chemistry I"),
			new Course(4, "HIST 104A Modern American Civ"),
			new Course(4, "CW 250 Creative Writing"),
			new Course(4, "CS 301 Eth Soc & Global Issues Comput"),
			new Course(4, "GEOG 101 Intro To Geog"),
			new Course(4, "WRIT 111 Research & Writ"),
			new Course(4, "ECON 162 Principles Of Macroeconomics"),
			new Course(4, "BIOL 113 Intro to Cell & Molecular Biol"),
			new Course(4, "BIOL 114 Intro to Organisms & Pops Biol"),
			new Course(4, "GEOG 121 Physical Geography"),
			new Course(4, "CS 101 Prof Skills Ethics & CS Trends"),
			new Course(4, "CS 120 Comp Sys I:Machine Org"),
			new Course(4, "CS 240 Data Struct & Algorithms"),
			new Course(4, "CS 220 Arch from a Prog Perspective"),
			new Course(4, "MATH 327 Probability with Stat Methods"),
			new Course(4, "CW 250 Creative Writing"),
			new Course(4, "CS 301 Eth Soc & Global Issues Comput"),
			new Course(4, "GEOG 101 Intro To Geog"),
			new Course(2, "MATH 224 Differential Calculus"),
			new Course(2, "MATH 225 Integral Calculus"),
			new Course(2, "MATH 226 Integration Tech & Application"),
			new Course(2, "MATH 227 Infinite Series"),
			new Course(4, "WRIT 111 Research & Writ"),
			new Course(4, "ECON 162 Principles Of Macroeconomics"),
			new Course(4, "BIOL 113 Intro to Cell & Molecular Biol"),
			new Course(4, "BIOL 114 Intro to Organisms & Pops Biol"),
			new Course(4, "GEOG 121 Physical Geography"),
			new Course(4, "MATH 314 Discrete Mathematics"),
			new Course(4, "MATH 304 Linear Algebra"),
			new Course(4, "CS 101 Prof Skills Ethics & CS Trends"),
			new Course(4, "CS 120 Comp Sys I:Machine Org"),
			new Course(4, "CS 535 Intro to Data Mining"),
			new Course(4, "HDEV 101 Human Deveelopment"),
			new Course(4, "MUS 113 Jazz In American Music"),
			new Course(4, "GEOG 151 World Regional Geography"),
			new Course(4, "MUS 115 Pop, Rock, And Soul Musics"),
			new Course(4, "ARTH 110 Introduction to Art History"),
			new Course(4,"ANTH 111 Intro to Anthropology"),
			new Course(4, "BCHM 403 Biochemistry"),
			new Course(4, "GEOG 103 Multicultural Geographies of the US"),
			new Course(4, "KOR 203 Intermediate Korean 1"),
			new Course(4, "ENG 230 Medieval Literature"),
			new Course(2, "MATH 223 Introduction to Calculus"),
			new Course(4, "AAAS 259 Eastern Asia: Land and People"),
			new Course(4, "MATH 330 Number Systems"),
			new Course(4, "CS 140 Prog with Obj & Data Struct"),
			new Course(4, "ASTR 114 Sun, Stars And Galaxies"),
			new Course(4, "ECON 360 Microeconomic Theory"),
			new Course(4, "MATH304 Linear Algebra")
	};

	public static void setupRosterAndRecords() {
		Person p1 = new Person("Zach", "Braverman");
		Person p2 = new Person("Jeremy", "Wells");
		Person p3 = new Person("Ryan", "Ogi");
		Person p4 = new Person("Nate", "Shanly");
		Person p5 = new Person("Zain", "Nasir");
		
		Student s1 = new Student(p1, "Philosophy", "Binghamton University");
		Student s2 = new Student(p2, "Nursing", "Binghamton University");
		Student s3 = new Student(p3, "Biomedical Engineering", "Harvard University");
		Student s4 = new Student(p4, "Computer Science", "Cornell University");
		Student s5 = new Student(p5, "Mechanincal Engineering" , "Rutgers University");
		
		roster.add(s1);
		roster.add(s2);
		roster.add(s3);
		roster.add(s4);
		roster.add(s5);
		
		for(Student st : roster)
			studentRecord.put(st, new ArrayList<>());
	}

	public static void setupCourseList() {
		for(Course crs : courses) {
			String[] parts = crs.getTitle().toUpperCase().split("\\s+");
			String key = parts[0] + parts[1];
			courseList.put(key, crs);
		}
	}

	public static void addCourse(Student st, String crs, double grade) {
		Course temp = courseList.get(crs.toUpperCase()).clone();
		temp.setGrade(grade);
		studentRecord.get(st).add(temp);
	}

	public static void populateStudentRecords() {
		List<String> keys = new ArrayList<>(courseList.keySet());
		Random gen = new Random();
		for(Student s : roster) {
			int numCourses = 6 + gen.nextInt(10);
			Set<String> crsAbbr = new HashSet<>();
			while(crsAbbr.size() < numCourses) {
				crsAbbr.add(keys.get(gen.nextInt(keys.size())));
			}
			List<String> crsAbbrList = new ArrayList<>(crsAbbr);
			for(int i = 0; i < numCourses; ++i) {
				double grade = 2.0 + 2*Math.random();
				addCourse(s, crsAbbrList.get(i), grade);
			}
		}
	}

	public static void computeGPAs() {
		double sumOfPoints;
		int sumOfCredits;
		
		for (Student st : roster) {
			sumOfPoints = 0.0;
			sumOfCredits = 0;
			for (Course crs : studentRecord.get(st)) {
				sumOfPoints += (crs.getGrade() * crs.getCredits());
				sumOfCredits += crs.getCredits();
			}
			studentGPAs.put(st, sumOfPoints/sumOfCredits);
		}
	}

	public static List<Student> studentsByGPA() {
		List<Student> list = new ArrayList<>();
		list.addAll(roster);
		Collections.sort(list, Comparator.comparingDouble(st -> studentGPAs.get(st)).reversed());
		return list;
	}
	
	public static void main(String[] args) {
		setupRosterAndRecords();
		setupCourseList();
		populateStudentRecords();
		computeGPAs();
		for(Student st : studentsByGPA()) {
			System.out.printf("%.3f--%s\n", studentGPAs.get(st), st);
		}
	}
}
