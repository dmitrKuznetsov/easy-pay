package com.github.dmitrkuznetsov.backend.service;

import com.github.dmitrkuznetsov.backend.entity.Payment;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

@Service
public class CSVHandlerServiceImpl implements CSVHandlerService {
    private final File file;
    public CSVHandlerServiceImpl() {
        file = new File("./backend/transaction.csv");

        clearFile();
        Field[] declaredFields = Payment.class.getDeclaredFields();
        String[] headers = Arrays.stream(declaredFields).map(Field::getName).toArray(String[]::new);
        write(Collections.singletonList(headers));
    }

    @Override
    public void savePayments(List<Payment> payments) {
        List<String[]> list = payments.stream().map(payment -> new String[]{
                String.valueOf(payment.getId()),
                String.valueOf(payment.getAccountId()),
                String.valueOf(payment.getAmount()),
                String.valueOf(payment.getDatetimeTransaction())
        }).toList();

        write(list);
    }

    private void write(List<String[]> list) {
        try (FileWriter fileWriter = new FileWriter(file, true);
             CSVWriter csvWriter = new CSVWriter(fileWriter, ';',
                     CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)
        ){
            csvWriter.writeAll(list);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void clearFile() {
        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write("");
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}