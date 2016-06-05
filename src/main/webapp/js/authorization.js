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

function bookMore(bookID) {
    $.ajax({
            type: 'POST',
            url: 'bookMore?bookID=' + bookID,
            success: function (data) {
                console.log(data);
                var genres = data["genresList"];
                var authors = data["authorsList"];
                var publishers = data["publishersList"];
                var book = data["book"];
                var bookGenre = book["genre"];
                var bookAuthor = book["author"];
                var bookPublisher = book["publisher"];

                addBook.find('.c-genre').html('');
                addBook.find('.c-author').html('');
                addBook.find('.c-publisher').html('');

                addBook.find('.c-genre').append($(new Option("","",true,true)));
                addBook.find('.c-author').append($(new Option("","",true,true)));
                addBook.find('.c-publisher').append($(new Option("","",true,true)));

                for (var key in genres) {
                    if (genres[key].name === bookGenre.name) {
                        addBook.find('.c-genre').append($(new Option(genres[key].name,genres[key].name,true,true)));
                    } else {
                        addBook.find('.c-genre').append($(new Option(genres[key].name,genres[key].name,false,false)));
                    }
                }
                for (var key in authors) {
                    if (authors[key].name === bookAuthor.name) {
                        addBook.find('.c-author').append($(new Option(authors[key].name,authors[key].name,true,true)));
                    } else {
                        addBook.find('.c-author').append($(new Option(authors[key].name,authors[key].name,false,false)));
                    }
                }
                for (var key in publishers) {
                    if (bookPublisher != null) {
                        if (publishers[key].name === bookPublisher.name) {
                            addBook.find('.c-publisher').append($(new Option(publishers[key].name,publishers[key].name,true,true)));
                        } else {
                            addBook.find('.c-publisher').append
                            ($(new Option(publishers[key].name,publishers[key].name,false,false)));
                        }
                    }else{
                        addBook.find('.c-publisher').append
                        ($(new Option(publishers[key].name,publishers[key].name,false,false)));
                    }
                }

                addBook.find('.c-img').attr('src', 'images/books/' + book.image);
                addBook.find('.c-name').val(book.name);
                addBook.find('.c-isbn').val(book.isbn);
                addBook.find('.c-year').val(book.publishDate);
                addBook.find('.c-pages').val(book.pageCount);
                addBook.find('.c-descr').val(book.description);
                $(document.body).addClass('scroll');
                addBook.addClass('active');
            }
        }
    );
}

function closeModal() {
    addBook.removeClass('active');
    $(document.body).removeClass('scroll');
}