package com.sigma.library.sigmalibrary.controller

import com.sigma.library.sigmalibrary.model.Book
import com.sigma.library.sigmalibrary.model.User
import com.sigma.library.sigmalibrary.service.LibraryService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.mockito.Mockito.verify
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


/**
 * These are the integration tests for the Library REST API Controller <-> Service Components.
 */
@WebMvcTest
internal class LibraryControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var libraryService: LibraryService

    @Test
    @Throws(Exception::class)
    fun shouldGetBorrowers() {
        `when`(libraryService.getUsersBorrowers()).thenReturn(returnTestUsers())
        mockMvc.perform(get("/borrowers"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    "[{\"name\":\"Aexi\",\"firstName\":\"Liam\"," +
                            "\"memberSince\":\"01/01/2010\",\"memberTill\":\"\"," +
                            "\"memberSinceTimestamp\":1262300400000," +
                            "\"memberTillTimestamp\":9223372036854775807,\"gender\":\"m\"}]"
                )
            )
        verify(libraryService).getUsersBorrowers()
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetNonBorrowers() {
        `when`(libraryService.getUsersNonBorrowers()).thenReturn(returnTestUsers())
        mockMvc.perform(get("/nonBorrowers"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    "[{\"name\":\"Aexi\",\"firstName\":\"Liam\"," +
                            "\"memberSince\":\"01/01/2010\",\"memberTill\":\"\"," +
                            "\"memberSinceTimestamp\":1262300400000," +
                            "\"memberTillTimestamp\":9223372036854775807,\"gender\":\"m\"}]"
                )
            )
        verify(libraryService).getUsersNonBorrowers()
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetBorrowersByDate() {
        `when`(libraryService.getUsersBorrowersByDate("01/01/2020")).thenReturn(returnTestUsers())
        mockMvc.perform(get("/borrowersByDate?date=01/01/2020"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    "[{\"name\":\"Aexi\",\"firstName\":\"Liam\"," +
                            "\"memberSince\":\"01/01/2010\",\"memberTill\":\"\"," +
                            "\"memberSinceTimestamp\":1262300400000," +
                            "\"memberTillTimestamp\":9223372036854775807,\"gender\":\"m\"}]"
                )
            )
        verify(libraryService).getUsersBorrowersByDate("01/01/2020")

    }

    @Test
    @Throws(Exception::class)
    fun shouldGetNonBorrowedBooks() {
        `when`(libraryService.getNotBorrowedBooks()).thenReturn(returnTestBooks())
        mockMvc.perform(get("/nonBorrowedBooks"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    "[{\"title\":\"Age of Discontuinity, The\",\"author\":\"Drucker, Peter\",\"genre\":\"economics\",\"publisher\":\"Random House\"}]"
                )
            )
        verify(libraryService).getNotBorrowedBooks()
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetBorrowedBooksByDateRange() {
        `when`(
            libraryService.getBorrowedBooksByUserAndDateRange(
                "Chish,Elijah",
                "05/14/2008",
                "05/29/2008"
            )
        ).thenReturn(returnTestBooks())
        mockMvc.perform(get("/borrowersByDateRange?user=Chish,Elijah&dateFrom=05/14/2008&dateTo=05/29/2008"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().json(
                    "[{\"title\":\"Age of Discontuinity, The\",\"author\":\"Drucker, Peter\",\"genre\":\"economics\",\"publisher\":\"Random House\"}]"
                )
            )
        verify(libraryService).getBorrowedBooksByUserAndDateRange("Chish,Elijah", "05/14/2008", "05/29/2008")
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