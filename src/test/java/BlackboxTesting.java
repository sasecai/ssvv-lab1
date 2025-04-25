import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;
import view.UI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BlackboxTesting {
    private static StudentValidator studentValidator;
    private static TemaValidator temaValidator;
    private static String filenameStudent;
    private static String filenameTema;
    private static String filenameNota;
    private static StudentXMLRepo studentXMLRepository;
    private static TemaXMLRepo temaXMLRepository;
    private static NotaValidator notaValidator;
    private static NotaXMLRepo notaXMLRepository;
    private static Service service;

    @BeforeAll
    public static void setUp() {
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        filenameStudent = "fisiere/Studenti.xml";
        filenameTema = "fisiere/Teme.xml";
        filenameNota = "fisiere/Note.xml";

        studentXMLRepository = new StudentXMLRepo(filenameStudent);
        temaXMLRepository = new TemaXMLRepo(filenameTema);
        notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    void validEverythingE1() {
        try {
            service.deleteStudent("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "Roberto", 937, "roby@smk.com");
//        assertThrows(Exception.class, () -> service.addStudent(student));
        service.addStudent(student);
    }

    @Test
    void idNegativeNumbersE2() {
        Student student=new Student("-123", "Roberto", 937, "roby@smk.com");
//        assertThrows(Exception.class, () -> service.addStudent(student));
        assertThrows(Exception.class, () -> service.addStudent(student));
    }

    @Test
    void idNonIntegerNumbersE3() {
        Student student=new Student("YOLO", "Roberto", 937, "roby@smk.com");
//        assertThrows(Exception.class, () -> service.addStudent(student));
        assertThrows(Exception.class, () -> service.addStudent(student));
    }

//    @Test
//    void idDuplicateE4() {
//        try {
//            service.deleteStudent("123");
//        } catch (Exception e) {
//        }
//        Student student=new Student("123", "Roberto", 937, "roby@smk.com");
////        assertThrows(Exception.class, () -> service.addStudent(student));
//        service.addStudent(student);
//        assertThrows(Exception.class, () -> service.addStudent(student));
//    }


    @Test
    void nameInvalidNamesE5() {
        try {
            service.deleteStudent("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "", 937, "roby@smk.com");
        assertThrows(Exception.class, () -> service.addStudent(student));
//        service.addStudent(student);
    }

    @Test
    void groupInvalidE6() {
        try {
            service.deleteStudent("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "name", -999, "roby@smk.com");
        assertThrows(Exception.class, () -> service.addStudent(student));
//        service.addStudent(student);
    }

    @Test
    void emailInvalidE7() {
        try {
            service.deleteStudent("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "name", 999, "roby@@@smk.com");
        assertThrows(Exception.class, () -> service.addStudent(student));
//        service.addStudent(student);
    }

    @Test
    void E1lowerBoundminus() {
        try {
            service.deleteStudent("0");
        } catch (Exception e) {
        }
        Student student=new Student("0", "Roberto", 937, "roby@smk.com");
        assertThrows(Exception.class, () -> service.addStudent(student));
//        service.addStudent(student);
    }

    @Test
    void E1lowerBound() {
        try {
            service.deleteStudent("1");
        } catch (Exception e) {
        }
        Student student=new Student("1", "Roberto", 937, "roby@smk.com");
//        assertThrows(Exception.class, () -> service.addStudent(student));
        service.addStudent(student);
    }

    @Test
    void E1lowerBoundplus() {
        try {
            service.deleteStudent("2");
        } catch (Exception e) {
        }
        Student student=new Student("2", "Roberto", 937, "roby@smk.com");
//        assertThrows(Exception.class, () -> service.addStudent(student));
        service.addStudent(student);
    }

    @Test
    void E2upperBoundminus() {
        try {
            service.deleteStudent("-1");
        } catch (Exception e) {
        }
        Student student=new Student("-1", "Roberto", 937, "roby@smk.com");
        assertThrows(Exception.class, () -> service.addStudent(student));
//        service.addStudent(student);
    }

    @Test
    void E2upperBound() {
        try {
            service.deleteStudent("0");
        } catch (Exception e) {
        }
        Student student=new Student("0", "Roberto", 937, "roby@smk.com");
        assertThrows(Exception.class, () -> service.addStudent(student));
//        service.addStudent(student);
    }

    @Test
    void E2upperBoundplus() {
        try {
            service.deleteStudent("1");
        } catch (Exception e) {
        }
        Student student=new Student("1", "Roberto", 937, "roby@smk.com");
//        assertThrows(Exception.class, () -> service.addStudent(student));
        service.addStudent(student);
    }




    @Test
    void E6GrouplowerBound() {
        try {
            service.deleteStudent("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "Roberto", -1, "roby@smk.com");
        assertThrows(Exception.class, () -> service.addStudent(student));
//        service.addStudent(student);
    }

    @Test
    void E6GrouplowerBoundplus() {
        try {
            service.deleteStudent("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "Roberto", 0, "roby@smk.com");
//        assertThrows(Exception.class, () -> service.addStudent(student));
        service.addStudent(student);
    }


    @Test
    void E6GrouplowerBoundminus() {
        try {
            service.deleteStudent("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "Roberto", 1, "roby@smk.com");
//        assertThrows(Exception.class, () -> service.addStudent(student));
        service.addStudent(student);
    }


    @Test
    void E7GroupupperBound() {
        try {
            service.deleteStudent("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "Roberto", -1, "roby@smk.com");
        assertThrows(Exception.class, () -> service.addStudent(student));
//        service.addStudent(student);
    }

    @Test
    void E7GroupupperBoundplus() {
        try {
            service.deleteStudent("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "Roberto", 0, "roby@smk.com");
//        assertThrows(Exception.class, () -> service.addStudent(student));
        service.addStudent(student);
    }


    @Test
    void E7GroupupperBoundminus() {
        try {
            service.deleteStudent("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "Roberto", -2, "roby@smk.com");
        assertThrows(Exception.class, () -> service.addStudent(student));
//        service.addStudent(student);
    }
}
