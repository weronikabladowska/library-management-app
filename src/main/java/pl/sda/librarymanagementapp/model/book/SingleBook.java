package pl.sda.librarymanagementapp.model.book;


import lombok.Data;

@Data
    public class SingleBook{

        private String title;
        private String author;
        private String publisher;
        private String placeOfPublication;
        private String publicationYear;
        private String isbnIssn;
    }
