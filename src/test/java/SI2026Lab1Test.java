import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SI2026Lab1Test {

    private Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
        library.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "Programming"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        library.addBook(new Book("1984", "George Orwell", "Dystopian"));
    }


    @Test
    public void searchBookEveryStatementTest() {

        assertThrows(IllegalArgumentException.class, () -> {
            library.searchBookByTitle("");
        });

        List<Book> result = library.searchBookByTitle("Clean Code");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Clean Code", result.get(0).getTitle());

        List<Book> notFound = library.searchBookByTitle("Harry Potter");
        assertNull(notFound);
    }


    @Test
    public void borrowBookEveryBranchTest() {

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("Clean Code", "");
        });

        assertThrows(RuntimeException.class, () -> {
            library.borrowBook("Unknown Book", "Unknown Author");
        });

        assertDoesNotThrow(() -> {
            library.borrowBook("The Hobbit", "J.R.R. Tolkien");
        });

        assertThrows(RuntimeException.class, () -> {
            library.borrowBook("The Hobbit", "J.R.R. Tolkien");
        });
    }

    @Test
    public void searchBookMultipleConditionTest() {

        List<Book> resultTT = library.searchBookByTitle("Clean Code");
        assertNotNull(resultTT);
        assertEquals(1, resultTT.size());

        library.borrowBook("1984", "George Orwell");
        List<Book> resultTF = library.searchBookByTitle("1984");
        assertNull(resultTF);

        List<Book> resultFT = library.searchBookByTitle("Nonexistent Book");
        assertNull(resultFT);
    }

    @Test
    public void borrowBookMultipleConditionTest() {

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "J.R.R. Tolkien");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("The Hobbit", "");
        });

        assertDoesNotThrow(() -> {
            library.borrowBook("Clean Code", "Robert C. Martin");
        });
    }
}
 