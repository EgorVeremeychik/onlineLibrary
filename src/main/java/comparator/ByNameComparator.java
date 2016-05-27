package comparator;

import entity.impl.Author;

import java.util.Comparator;

/**
 * Created by EgorVeremeychik on 17.04.2016.
 */
public class ByNameComparator implements Comparator<Author> {
    @Override
    public int compare(Author author1, Author author2) {
        return author1.getName().compareTo(author2.getName());
    }
}
