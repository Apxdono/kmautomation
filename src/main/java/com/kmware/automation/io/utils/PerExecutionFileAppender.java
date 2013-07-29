package com.kmware.automation.io.utils;

import org.apache.log4j.Layout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.spi.ErrorCode;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 2:34
 */
public class PerExecutionFileAppender extends RollingFileAppender{

    public PerExecutionFileAppender() {
    }

    public PerExecutionFileAppender(Layout layout, String filename,
                                    boolean append) throws IOException {
        super(layout, filename, append);
    }

    public PerExecutionFileAppender(Layout layout, String filename)
            throws IOException {
        super(layout, filename);
    }

    public void activateOptions() {
        if (fileName != null) {
            try {
                fileName = getNewLogFileName();
                setFile(fileName, fileAppend, bufferedIO, bufferSize);
            } catch (Exception e) {
                errorHandler.error("Error while activating log options", e,
                        ErrorCode.FILE_OPEN_FAILURE);
            }
        }
    }

    private String getNewLogFileName() {
        if (fileName != null) {
            final String DOT = ".";
            final String HIPHEN = "-";
            final File logFile = new File(fileName);
            final String fileName = logFile.getName();
            String newFileName = "";

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy-HH-mm-ss");
            String suffix =  sdf.format(new Date());

            final int dotIndex = fileName.indexOf(DOT);
            if (dotIndex != -1) {
                // the file name has an extension. so, insert the time stamp
                // between the file name and the extension
                newFileName = fileName.substring(0, dotIndex) + HIPHEN +
                        suffix + DOT +
                        fileName.substring(dotIndex + 1);
            } else {
                // the file name has no extension. So, just append the timestamp
                // at the end.
                newFileName = fileName + HIPHEN + suffix;
            }
            return logFile.getParent() + File.separator + newFileName;
        }
        return null;
    }
}