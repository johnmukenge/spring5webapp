package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author john = new Author();
        john.setFirstname("John");
        john.setLastname("Mukenge");

        Author johnson = new Author();
        john.setFirstname("Johnson");
        john.setLastname("MukengeNJ");

        Book ddd = new Book();
        ddd.setTitle("Spring Boot Course");
        ddd.setIsbn("123456");

        Book noEJB = new Book();
        ddd.setTitle("J2EE Development without EJB");
        ddd.setIsbn("678953");

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Pipo");
        publisher.setCity("Roma");
        publisher.setZip("6$%3242");
        publisher.setAddress("Via Guglielmo Oberdan");
        publisher.setState("Italy");

        Author johnSaved = authorRepository.save(john);
        Author johnsonSaved = authorRepository.save(johnson);
        Book dddSaved = bookRepository.save(ddd);
        Book noEJBSaved = bookRepository.save(noEJB);
        Publisher savedPublisher = publisherRepository.save(publisher);

        dddSaved.setPublisher(savedPublisher);
        noEJBSaved.setPublisher(savedPublisher);


        // create association between those things

        johnSaved.getBooks().add(dddSaved);
        johnsonSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(johnSaved);
        noEJBSaved.getAuthors().add(johnsonSaved);

        authorRepository.save(johnSaved);
        authorRepository.save(johnsonSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);
        publisherRepository.save(savedPublisher);


        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count " + bookRepository.count());
        System.out.println("Publisher " + publisherRepository.count());
    }
}
