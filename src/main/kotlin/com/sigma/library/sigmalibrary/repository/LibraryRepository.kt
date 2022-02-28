package com.sigma.library.sigmalibrary.repository

import com.sigma.library.sigmalibrary.model.Book
import com.sigma.library.sigmalibrary.model.Borrowed
import com.sigma.library.sigmalibrary.model.User
import com.sigma.library.sigmalibrary.util.Util
import org.apache.commons.csv.CSVParser
import org.springframework.stereotype.Repository


/**
 * This is a Repository class of the Library REST API. This class contains methods that directly grab data from the data-source
 * (CSV files)
 */
@Repository
class LibraryRepository(val utilFunctions: Util) {

    /**
     * This method returns data from the "user.csv" file.
     */
    fun returnUserObjectList(): MutableList<User> {
        val csvData = utilFunctions.csvDataExtractor("csv/user.csv")
        val userList: MutableList<User> = mutableListOf()
        for (csvRecord in csvData) {
            userList.add(
                User(
                    csvRecord.get("Name"),
                    csvRecord.get("First name"),
                    csvRecord.get("Member since"),
                    csvRecord.get("Member till"),
                    utilFunctions.dateToTimestampFormatter(csvRecord.get("Member since")),
                    utilFunctions.dateToTimestampFormatter(csvRecord.get("Member till")),
                    csvRecord.get("Gender")
                )
            )
        }

        return userList;
    }

    /**
     * This method returns data from the "books.csv" file.
     */
    fun returnBookObjectList(): MutableList<Book> {
        val csvData = utilFunctions.csvDataExtractor("csv/books.csv")
        val bookList: MutableList<Book> = mutableListOf()
        for (csvRecord in csvData) {
            bookList.add(
                Book(
                    csvRecord.get("Title"),
                    csvRecord.get("Author"),
                    csvRecord.get("Genre"),
                    csvRecord.get("Publisher")
                )
            )
        }
        return bookList;
    }

    /**
     * This method returns data from the "borrowed.csv" file.
     */
    fun returnBorrowedObjectList(): MutableList<Borrowed> {
        val csvData: CSVParser = utilFunctions.csvDataExtractor("csv/borrowed.csv")
        val borrowedList: MutableList<Borrowed> = mutableListOf()
        for (csvRecord in csvData) {
            borrowedList.add(
                Borrowed(
                    csvRecord.get("Borrower"),
                    csvRecord.get("Book"),
                    csvRecord.get("borrowed from"),
                    csvRecord.get("borrowed to"),
                    utilFunctions.dateToTimestampFormatter(csvRecord.get("borrowed from")),
                    utilFunctions.dateToTimestampFormatter(csvRecord.get("borrowed to"))
                )
            )
        }
        return borrowedList;
    }
}