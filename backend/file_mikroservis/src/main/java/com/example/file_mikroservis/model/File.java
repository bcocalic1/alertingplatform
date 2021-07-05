package com.example.file_mikroservis.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//more than 5 in one minute

public class File {
    private static final String err = "ERROR";
    private static final String errMsg = "There was an error in processing file.";

    private static final String noErr = "OK";
    private static final String noErrMsg = "The file was successfully processed.";

    private final ArrayList<String> logs;

    public File() {
        this.logs = new ArrayList<>();

        if (ThreadLocalRandom.current().nextInt() %2 == 0){
            logs.add(err);

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);

            logs.add(strDate);
            logs.add(errMsg);
        }
        else {
            logs.add(noErr);

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);

            logs.add(strDate);
            logs.add(noErrMsg);
        }
    }

    public File(ArrayList<String> logs) {
        this.logs = logs;
    }
    public ArrayList<String> getLogs() {
        return logs;
    }
}
