package com.sigma.library.sigmalibrary.util

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader
import java.text.SimpleDateFormat

/**
 * This is a utility class for the Library REST API.
 */
@Component
class Util {

    /**
     * This method is a utility method that directly extracts data from a CSV file format and returns the data.
     */
    fun csvDataExtractor(csvPath: String): CSVParser {
        val resource = ClassPathResource(csvPath)
        val file = resource.file
        val fileReader = FileReader(file)
        val bufferedReader = BufferedReader(fileReader)
        val csvParser = CSVParser(
            bufferedReader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim()
        );
        return csvParser
    }

    /**
     * THis method is a utility method that converts date from a "MM/dd/yyyy" format to a timestamp format. Timestamp
     * format is important for the date-range calculations.
     */
    fun dateToTimestampFormatter(dateString: String): Long {
        if (dateString !== "") {
            val date = SimpleDateFormat("MM/dd/yyyy").parse(dateString)
            return date.time
        }
        return Long.MAX_VALUE;
    }
}