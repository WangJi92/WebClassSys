package com.hdu.cms.common.Utils;

/**
 * Created by JetWang on 2016/10/1.
 */
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    public static void logException(Exception e) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        StringBuffer logInfo = new StringBuffer(500);
        logInfo.append("[");
        logInfo.append(stacks[1].getLineNumber());
        logInfo.append("]");
        logInfo.append(stacks[1].getMethodName());
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logInfo.append(" Exception:");
        logInfo.append(trace.toString());
        Logger logger = null;
        try {
            logger = LogManager.getLogger(Class.forName(newClass).getName());
            logger.error(logInfo.toString());
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static void logInfo(String message, Object... args) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        StringBuffer logInfo = new StringBuffer(500);
        logInfo.append("[");
        logInfo.append(stacks[1].getLineNumber());
        logInfo.append("]");
        logInfo.append(stacks[1].getMethodName());
        logInfo.append(" INFO:");
        if (null != args){
            for (Object arg : args) {
                String[] strArr = message.split("\\{\\}", 2);
                if (arg == null) {
                    arg = "null";
                }
                message = strArr[0] + String.valueOf(arg) + strArr[1];
            }
        }
        logInfo.append(message);
        Logger logger = null;
        try {
            logger = LogManager.getLogger(Class.forName(newClass).getName());
            logger.info(logInfo.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void logDebug(String message, Object... args) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        StringBuffer logInfo = new StringBuffer(500);
        logInfo.append("[");
        logInfo.append(stacks[1].getLineNumber());
        logInfo.append("]");
        logInfo.append(stacks[1].getMethodName());
        logInfo.append(" DEBUG:");
        if (null != args){
            for (Object arg : args) {
                String[] strArr = message.split("\\{\\}", 2);
                if (arg == null) {
                    arg = "null";
                }
                message = strArr[0] + String.valueOf(arg) + strArr[1];
            }
        }
        logInfo.append(message);
        Logger logger = null;
        try {
            logger = LogManager.getLogger(Class.forName(newClass).getName());
            logger.debug(logInfo.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void logError(String message, Object... args) {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        String newClass = stacks[1].getClassName();
        StringBuffer logInfo = new StringBuffer(500);
        logInfo.append("[");
        logInfo.append(stacks[1].getLineNumber());
        logInfo.append("]");
        logInfo.append(stacks[1].getMethodName());
        logInfo.append(" INFO:");
        if (null != args){
            for (Object arg : args) {
                String[] strArr = message.split("\\{\\}", 2);
                if (arg == null) {
                    arg = "null";
                }
                message = strArr[0] + String.valueOf(arg) + strArr[1];
            }
        }
        logInfo.append(message);
        Logger logger = null;
        try {
            logger = LogManager.getLogger(Class.forName(newClass).getName());
            logger.error(logInfo.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
