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

public class WBTTests {
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
    void assignmentAlreadyExists() {
        try {
            service.deleteTema("123");
        } catch (Exception e) {
        }
        Tema tema = new Tema("123", "salut", 3, 1);
        var result1 = service.addTema(tema);
        var result2 = service.addTema(tema);
//        assertEquals(result1, null);
        assertNotEquals(result2, null);
    }
    @Test
    void assignmentDidNotExist() {
        try {
            service.deleteTema("123");
        } catch (Exception e) {
        }
        Tema tema = new Tema("123", "salut", 3, 1);
        var result1 = service.addTema(tema);
        assertEquals(result1, null);
    }

    @Test
    void assignmentInvalidIdEmpty() {
        Tema tema = new Tema("", "salut", 3, 1);
        assertThrows(ValidationException.class, () -> {service.addTema(tema);});
    }

    @Test
    void assignmentInvalidIdNull() {
        Tema tema = new Tema(null, "salut", 3, 1);
        assertThrows(ValidationException.class, () -> {service.addTema(tema);});
    }

    @Test
    void assignmentInvalidDescription() {
        Tema tema = new Tema("123", "", 3, 1);
        assertThrows(ValidationException.class, () -> {service.addTema(tema);});
    }

    @Test
    void assignmentInvalidDeadlineTooSmall() {
        Tema tema = new Tema("123", "afsdfs", 0, 1);
        assertThrows(ValidationException.class, () -> {service.addTema(tema);});
    }

    @Test
    void assignmentInvalidDeadlineTooBig() {
        Tema tema = new Tema("123", "afsdfs", 15, 1);
        assertThrows(ValidationException.class, () -> {service.addTema(tema);});
    }

    @Test
    void assignmentInvalidPrimireTooSmall() {
        Tema tema = new Tema("123", "afsdfs", 1, 0);
        assertThrows(ValidationException.class, () -> {service.addTema(tema);});
    }

    @Test
    void assignmentInvalidPrimireTooBig() {
        Tema tema = new Tema("123", "afsdfs", 1, 15);
        assertThrows(ValidationException.class, () -> {service.addTema(tema);});
    }

}
