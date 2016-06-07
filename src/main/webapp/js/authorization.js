function validateForm(form, message) {
    var login = form.login.value;
    if (login == '') {
        alert(message);
        return false;
    } else {
        return true;
    }
}

var addBook = $('#addBook');
var bookID;
var genreID;
var authorID;
var publisherID;

function saveChange() {
    var genre = new Object();
    genre.id = genreID;
    genre.name = addBook.find('.i-genre').val();

    var author = new Object();
    author.id = authorID;
    author.name = addBook.find('.i-author').val();

    var publisher = new Object();
    publisher.id = publisherID;
    publisher.name = addBook.find('.i-publisher').val();

    var book = new Object();
    book.id = bookID;
    book.name = addBook.find('.c-name').val();
    book.genre = genre;
    book.isbn = addBook.find('.c-isbn').val();
    var src = addBook.find('.c-img').attr('src').split("/");
    book.image = src[src.length - 1];
    book.author = author;
    book.pageCount = addBook.find('.c-pages').val();
    book.publishDate = addBook.find('.c-year').val();
    book.description = addBook.find('.c-descr').val();
    book.publisher = publisher;
    $.ajax({
        type: 'POST',
        url: 'changeBook',
        dataType: 'json',
        data: JSON.stringify(book),
        contentType: 'application/json',
        mimeType: 'application/json'
    });
}

function bookMore(id) {
    $.ajax({
            type: 'POST',
            url: 'bookMore?bookID=' + id,
            success: function (data) {
                console.log(data);
                addBook.find('.c-genre').html('');
                addBook.find('.c-author').html('');
                addBook.find('.c-publisher').html('');

                addBook.find('.c-genre').append($(new Option("", "", true, true)));
                addBook.find('.c-author').append($(new Option("", "", true, true)));
                addBook.find('.c-publisher').append($(new Option("", "", true, true)));

                var genres = data["genresList"];
                var authors = data["authorsList"];
                var publishers = data["publishersList"];
                var book = data["book"];
                var genreBook = book["genre"];
                var authorBook = book["author"];
                var publisherBook = book["publisher"];

                bookID = id;
                genreID = genreBook.id;
                authorID = authorBook.id;
                if (publisherBook != null) {
                    publisherID = publisherBook.id;
                } else {
                    publisherID = null;
                }

                for (var key in genres) {
                    if (genres[key].name === genreBook.name) {
                        addBook.find('.c-genre').append($(new Option(genres[key].name, genres[key].name, true, true)));
                        $("input.i-genre").val(addBook.find('.c-genre').val());
                    } else {
                        addBook.find('.c-genre').append($(new Option(genres[key].name, genres[key].name, false, false)));
                    }
                }
                for (var key in authors) {
                    if (authors[key].name === authorBook.name) {
                        addBook.find('.c-author').append($(new Option(authors[key].name, authors[key].name, true, true)));
                        $("input.i-author").val(addBook.find('.c-author').val());
                    } else {
                        addBook.find('.c-author').append($(new Option(authors[key].name, authors[key].name, false, false)));
                    }
                }
                for (var key in publishers) {
                    if (publisherBook != null) {
                        if (publishers[key].name === publisherBook.name) {
                            addBook.find('.c-publisher').append($(new Option(publishers[key].name, publishers[key].name, true, true)));
                            $("input.i-publisher").val(addBook.find('.c-publisher').val());
                        } else {
                            addBook.find('.c-publisher').append
                            ($(new Option(publishers[key].name, publishers[key].name, false, false)));
                        }
                    } else {
                        addBook.find('.c-publisher').append
                        ($(new Option(publishers[key].name, publishers[key].name, false, false)));
                    }
                }
                addBook.find('.c-img').attr('src', 'images/books/' + book.image);
                addBook.find('.c-name').val(book.name);
                addBook.find('.c-isbn').val(book.isbn);
                addBook.find('.c-year').val(book.publishDate);
                addBook.find('.c-pages').val(book.pageCount);
                addBook.find('.c-descr').val(book.description);
                $(document.body).addClass('scroll');
                modifyGenre();
                modifyAuthor();
                modifyPublisher();
                addBook.addClass('active');
            }
        }
    );
}

function closeModal() {
    addBook.removeClass('active');
    $(document.body).removeClass('scroll');
}

addBook.find('.c-genre').change(function () {
    modifyGenre();
})

function modifyGenre() {
    $("input.i-genre").val(addBook.find('.c-genre').val());
}

addBook.find('.c-author').change(function () {
    modifyAuthor();
})

function modifyAuthor() {
    $("input.i-author").val(addBook.find('.c-author').val());
}

addBook.find('.c-publisher').change(function () {
    modifyPublisher();
})

function modifyPublisher() {
    $("input.i-publisher").val(addBook.find('.c-publisher').val());
}
