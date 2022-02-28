package com.sigma.library.sigmalibrary.controller

import com.sigma.library.sigmalibrary.model.Book
import com.sigma.library.sigmalibrary.model.User
import com.sigma.library.sigmalibrary.service.LibraryService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


/**
 * This is a Controller class of the Library REST API. This class contains endpoint methods that can be called by API
 * users.
 */
@RestController
class LibraryController(val libraryService: LibraryService) {


    /**
     * Example API call:
     * http://localhost:8080/borrowers
     */
    @GetMapping(
        value = ["/borrowers"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun borrowers(): MutableList<User> = libraryService.getUsersBorrowers()


    /**
     * Example API call:
     * http://localhost:8080/nonBorrowers
     */
    @GetMapping(
        value = ["/nonBorrowers"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun nonBorrowers(): MutableList<User> = libraryService.getUsersNonBorrowers()


    /**
     * Example API call:
     * http://localhost:8080/borrowersByDate?date=05/14/2008
     * IMPORTANT: date parameter MUST contain the following format -> MM/dd/yyyy
     */
    @GetMapping(
        value = ["/borrowersByDate"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun borrowersByDate(@RequestParam(name = "date") date: String): MutableList<User> =
        libraryService.getUsersBorrowersByDate(date)


    /**
     * Example API call:
     * http://localhost:8080/nonBorrowedBooks
     */
    @GetMapping(
        value = ["/nonBorrowedBooks"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun nonBorrowedBooks(): MutableList<Book> = libraryService.getNotBorrowedBooks()


    /**
     * Example API call:
     * http://localhost:8080/borrowersByDateRange?user=Chish,Elijah&dateFrom=05/14/2008&dateTo=05/29/2008
     * IMPORTANT: user parameter MUST contain the following format -> name,firstname
     * IMPORTANT: dateFrom & dateTo parameter MUST contain the following format -> MM/dd/yyyy
     */
    @GetMapping(
        value = ["/borrowersByDateRange"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun borrowersByDateRange(
        @RequestParam(name = "user") user: String,
        @RequestParam(name = "dateFrom") dateFrom: String,
        @RequestParam(name = "dateTo") dateTo: String
    ): MutableList<Book> =
        libraryService.getBorrowedBooksByUserAndDateRange(user, dateFrom, dateTo)

}