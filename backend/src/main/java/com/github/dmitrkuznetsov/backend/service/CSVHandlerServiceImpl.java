package com.github.dmitrkuznetsov.backend.service;

//import com.github.dmitrkuznetsov.backend.entity.Payment;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

//import java.io.FileWriter;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.Arrays;

@Service
public class CSVHandlerServiceImpl implements CSVHandlerService {

    @Override
    public void saveNewPayments() {

    }

//    public void createCSVFile() throws IOException {
//        FileWriter out = new FileWriter("transaction.csv");
//
//        Field[] declaredFields = Payment.class.getDeclaredFields();
//        Arrays.stream(declaredFields).map(field -> field.getName()).toArray()
//
//        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
//            AUTHOR_BOOK_MAP.forEach((author, title) -> {
//                printer.printRecord(author, title);
//            });
//        }
//    }
}
