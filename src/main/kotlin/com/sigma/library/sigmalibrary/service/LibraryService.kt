package com.sigma.library.sigmalibrary.service

import com.sigma.library.sigmalibrary.model.Book
import com.sigma.library.sigmalibrary.model.User
import com.sigma.library.sigmalibrary.repository.LibraryRepository
import com.sigma.library.sigmalibrary.util.Util
import org.springframework.stereotype.Service


/**
 * This class contains business logic of the Library REST API. It is a middle-layer between data source (Repository)
 * and Controller.
 */
@Service
class LibraryService(

    val libRepository: LibraryRepository,
    val utilFunctions: Util
) {

    /**
     * This method returns all users that borrowed at least one book.
     */
    fun getUsersBorrowers(): MutableList<User> {
        val userBorrowers: MutableList<User> = mutableListOf()
        val borrowedUsersFullNames: MutableList<String> = mutableListOf()
        for (borrowed in libRepository.returnBorrowedObjectList()) {
            borrowedUsersFullNames.add(borrowed.borrower)
        }
        for (user in libRepository.returnUserObjectList()) {
            if (borrowedUsersFullNames.contains((user.name + "," + user.firstName).trim()))
                userBorrowers.add(user)
        }
        return userBorrowers
    }

    /**
     * This method returns all users that did not borrow any book.
     */
    fun getUsersNonBorrowers(): MutableList<User> {
        val userNonBorrowers: MutableList<User> = mutableListOf()
        val borrowedUsersFullNames: MutableList<String> = mutableListOf()
        for (borrowed in libRepository.returnBorrowedObjectList()) {
            borrowedUsersFullNames.add(borrowed.borrower)
        }
        for (user in libRepository.returnUserObjectList()) {
            if (!borrowedUsersFullNames.contains((user.name + "," + user.firstName).trim()))
                userNonBorrowers.add(user)
        }
        return userNonBorrowers
    }

    /**
     * This method returns all users that borrowed on a specific date.
     */
    fun getUsersBorrowersByDate(date: String): MutableList<User> {
        val userBorrowersByDate: MutableList<User> = mutableListOf()
        val borrowedUsersOnSpecificDateFullNames: MutableList<String> = mutableListOf()
        for (borrowed in libRepository.returnBorrowedObjectList()) {
            if (borrowed.borrowedFrom == date) {
                borrowedUsersOnSpecificDateFullNames.add(borrowed.borrower)
            }
        }
        for (user in libRepository.returnUserObjectList()) {
            if (borrowedUsersOnSpecificDateFullNames.contains((user.name + "," + user.firstName).trim()))
                userBorrowersByDate.add(user)
        }
        return userBorrowersByDate
    }

    /**
     * This method returns all books that are not yet borrowed by anyone.
     */
    fun getNotBorrowedBooks(): MutableList<Book> {
        val unborrowedBooks: MutableList<Book> = mutableListOf()
        val borrowedBooksNames: MutableList<String> = mutableListOf()
        for (borrowed in libRepository.returnBorrowedObjectList()) {
            borrowedBooksNames.add(borrowed.book)
        }
        for (book in libRepository.returnBookObjectList()) {
            if (!borrowedBooksNames.contains(book.title)) {
                unborrowedBooks.add(book)
            }
        }
        return unborrowedBooks
    }

    /**
     * This method returns all books that are borrowed in a given range by a given borrower.
     */
    fun getBorrowedBooksByUserAndDateRange(
        borrower: String,
        dateBorrowedFrom: String,
        dateBorrowedTo: String
    ): MutableList<Book> {

        val borrowedBooksByDateRange: MutableList<Book> = mutableListOf()
        val borrowedBookNames: MutableList<String> = mutableListOf()
        val dateBorrowedFromTimestamp = utilFunctions.dateToTimestampFormatter(dateBorrowedFrom)
        val dateBorrowedToTimestamp = utilFunctions.dateToTimestampFormatter(dateBorrowedTo)
        for (borrowed in libRepository.returnBorrowedObjectList()) {
            if (borrowed.borrowedFromTimestamp <= dateBorrowedFromTimestamp && borrowed.borrowedToTimestamp >= dateBorrowedToTimestamp
                && borrowed.borrower == borrower
            ) {
                borrowedBookNames.add(borrowed.book)
            }
        }
        for (book in libRepository.returnBookObjectList()) {
            if (borrowedBookNames.contains(book.title)) {
                borrowedBooksByDateRange.add(book)
            }
        }
        return borrowedBooksByDateRange
    }

}