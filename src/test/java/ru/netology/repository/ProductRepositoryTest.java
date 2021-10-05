package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    Product book1 = new Book(1, "book1", 500, "автор1",500,1999);
    Product book2 = new Book(2, "book2", 500, "автор1",500,1999);
    Product tshirt1 = new TShirt(11,"name1", 150, "blue", "42");
    Product tshirt2 = new TShirt(22,"name1", 150, "red", "42");

    @BeforeEach
    public void setUp() {
        repository.save(book1);
        repository.save(book2);
        repository.save(tshirt1);
        repository.save(tshirt2);
    }

    @Test
    public void shouldSaveAll() {
        Product[] expected = {book1, book2, tshirt1, tshirt2};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        repository.removeById(11);
        Product[] expected = {book1, book2, tshirt2};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldUseNotFoundException(){
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(55);
        });
    }
}