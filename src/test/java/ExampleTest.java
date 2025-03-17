import domain.Nota;
import domain.Student;
import domain.Tema;
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

@Tag("ExamplePack")
class ExampleTest {

    @Test
    void exampleTestCase() {
        System.out.println("Running an example test...");
        assertTrue(true, "Example assertion passed");
    }
    @Test
    void exampleTestCase2() {
        // add a student
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        try {
            service.deleteStudent("123");
            service.deleteNota("123");
            service.deleteTema("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "Roberto", 937, "roby@smk.com");
        service.addStudent(student);
        Tema tema = new Tema("123", "salut", 3, 1);
        service.addTema(tema);

        String filename = "fisiere/DataInceput.txt";
        LocalDate dataDeInceput = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line = bufferedReader.readLine();
            String[] words = line.split(",");
            dataDeInceput = LocalDate.of(Integer.parseInt(words[0]), Integer.parseInt(words[1]), Integer.parseInt(words[2]));
        } catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }
        System.out.println(dataDeInceput.toString());
        System.out.println(tema.getDeadline());
        LocalDate dataPredare = dataDeInceput.plusDays(1);
        Nota notaCatalog = new Nota("234324324", "123" ,"123" , 10.0, dataPredare);
        service.addNota(notaCatalog, "super");
    }
    @Test
    void exampleTestCase3() {
        // add a student
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        try {
            service.deleteStudent("123");
            service.deleteNota("123");
            service.deleteTema("123");
        } catch (Exception e) {
        }
        Student student=new Student("123", "Roberto", 937, "roby@smk.com");
        service.addStudent(student);
        Tema tema = new Tema("123", "salut", 3, 1);
        service.addTema(tema);

        String filename = "fisiere/DataInceput.txt";
        LocalDate dataDeInceput = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line = bufferedReader.readLine();
            String[] words = line.split(",");
            dataDeInceput = LocalDate.of(Integer.parseInt(words[0]), Integer.parseInt(words[1]), Integer.parseInt(words[2]));
        } catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }
//        System.out.println(dataDeInceput.toString());
//        System.out.println(tema.getDeadline());
        LocalDate dataPredare = dataDeInceput.plusDays(500);
        Nota notaCatalog = new Nota("234324324", "123" ,"123" , 10.0, dataPredare);
//        service.addNota(notaCatalog, "super");
        try {
            service.addNota(notaCatalog, "super");
            fail();
        } catch (Exception e) {
            assert(true);
        }
        Student student2=new Student("123", "AltStudentCuAcelasiID", 937, "roby@smk.com");
        try {
            service.addStudent(student2);
            fail();
        } catch (Exception e) {
            assert(true);
        }
    }
}
