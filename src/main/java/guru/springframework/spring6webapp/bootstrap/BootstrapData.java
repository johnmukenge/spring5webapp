package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author john = new Author();
        john.setFirstname("John");
        john.setLastname("Mukenge");

        Book ddd = new Book();
        ddd.setTitle("Spring Boot Course");
        ddd.setIsbn("123456");

        Author johnSaved = authorRepository.save(john);
        Book dddSaved = bookRepository.save(ddd);

        Author johnson = new Author();
        john.setFirstname("Johnson");
        john.setLastname("MukengeNJ");

        Book noEJB = new Book();
        ddd.setTitle("J2EE Development without EJB");
        ddd.setIsbn("678953");

        Author johnsonSaved = authorRepository.save(johnson);
        Book noEJBSaved = bookRepository.save(noEJB);

        // create association between those things

        johnSaved.getBooks().add(dddSaved);
        johnsonSaved.getBooks().add(noEJBSaved);

        authorRepository.save(johnSaved);
        authorRepository.save(johnsonSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count " + bookRepository.count());
    }
}
