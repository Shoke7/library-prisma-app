package com.sigma.library.sigmalibrary.controller

import com.sigma.library.sigmalibrary.model.Book
import com.sigma.library.sigmalibrary.model.User
import com.sigma.library.sigmalibrary.service.LibraryService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any

/**
 * These are the unit tests for the LibraryController (Controller) class.
 */
internal class LibraryControllerTest {

    private val mockLibraryService = Mockito.mock(LibraryService::class.java)
    private val libraryController = LibraryController(mockLibraryService)

    @Test
    fun borrowers() {
        val userBorrowers = returnTestUsers()
        Mockito.`when`(mockLibraryService.getUsersBorrowers()).thenReturn(userBorrowers)
        assertEquals(libraryController.borrowers(), userBorrowers)
    }

    @Test
    fun nonBorrowers() {
        val userNonBorrowers = returnTestUsers()
        Mockito.`when`(mockLibraryService.getUsersNonBorrowers()).thenReturn(userNonBorrowers)
        assertEquals(libraryController.nonBorrowers(), userNonBorrowers)
    }

    @Test
    fun borrowersByDate() {
        val borrowersByDate = returnTestUsers()
        Mockito.`when`(mockLibraryService.getUsersBorrowersByDate(any())).thenReturn(borrowersByDate)
        assertEquals(libraryController.borrowersByDate("01/01/2020"), borrowersByDate)
    }

    @Test
    fun nonBorrowedBooks() {
        val nonBorrowedBooks = returnTestBooks()
        Mockito.`when`(mockLibraryService.getNotBorrowedBooks()).thenReturn(nonBorrowedBooks)
        assertEquals(libraryController.nonBorrowedBooks(), nonBorrowedBooks)
    }

    @Test
    fun borrowersByDateRange() {
        val borrowedByDateRangeUser = returnTestBooks()
        Mockito.`when`(
            mockLibraryService.getBorrowedBooksByUserAndDateRange(
                "borrower", "01/01/2020",
                "01/01/2021"
            )
        ).thenReturn(borrowedByDateRangeUser)
        assertEquals(libraryController.borrowersByDateRange("borrower", "01/01/2020",
            "01/01/2021"), borrowedByDateRangeUser)
    }


    private fun returnTestUsers(): MutableList<User> {
        return mutableListOf(
            User(
                "Aexi", "Liam", "01/01/2010", "",
                1262300400000, 9223372036854775807, "m"
            )
        )
    }

    private fun returnTestBooks(): MutableList<Book> {
        return mutableListOf(
            Book(
                "Age of Discontuinity, The", "Drucker, Peter", "economics", "Random House"
            )
        )
    }


}