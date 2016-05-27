package manager;

/**
 * Created by EgorVeremeychik on 01.05.2016.
 */
public enum PagesEnum {
    GO_TO_MAIN("page.go.to.main"),
    MAIN("page.main"),
    INDEX("page.index"),
    BOOK_CATALOG("page.book.catalog"),
    START_BOOK_CATALOG("page.start.book.catalog");

    String value;
    PagesEnum(String value){
        this.value = value;
    }

    public String getValue(){return this.value;}
}
