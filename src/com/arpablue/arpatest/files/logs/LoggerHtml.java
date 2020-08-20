/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.files.logs;

import com.arpablue.arpatest.tools.TimeTool;
import java.io.File;
import java.io.RandomAccessFile;

/**
 *
 * @author AugLaptop
 */
public class LoggerHtml extends LogObject {

    public LoggerHtml() {
        this.setLogFile("html");
    }

    private void println(String text) {
        String line = TimeTool.getNow();
        line = line + ": " + text;
        this.write(line);
    }
    /**
     * It write the content of a file.
     * @param file it is the path of the file.
     */
    protected void writeFile(String file) {
        File f = new File(this.getClass().getResource(file).getFile());
        try {
            RandomAccessFile read = new RandomAccessFile(f, "r");
            if (!f.exists()) {
                return;
            }
            if (!f.isFile()) {
                return;
            }
            String line = "";
            do {
                line = read.readLine();
                if( line == null ){
                    write(line);
                }
            } while (line != null);

        } catch (Exception e) {

        }

    }

    protected void writeDiv(String kind, String text) {
        write("        <div class='" + kind + "'>");
        write("            " + kind.toUpperCase() + ": " + text);
        write("        </div>");

    }

    @Override
    protected void before() {
        write("<html>");
        write("    <head>");
        write("        <script>");
        writeFile("/com/arpablue/arpatest/files/logs/loggerhtml/header.js");
        write("        </script>");
        write("    </head>");
        write("    <body>");
    }

    @Override
    protected void after() {
        write("    </body>");
        write("</html>");
    }

    @Override
    public void title(String text) {
        writeDiv("title", text);
    }

    @Override
    public void area(String text) {
        writeDiv("area", text);
    }

    @Override
    public void body(String text) {
        writeDiv("body", text);
    }

    @Override
    public void step(String text) {
        writeDiv("step", text);
    }

    @Override
    public void section(String text) {
        writeDiv("section", text);
    }

    @Override
    public void action(String text) {
        writeDiv("action", text);
    }

    @Override
    public void cmd(String text) {
        writeDiv("cmd", text);
    }

    @Override
    public void inst(String text) {
        writeDiv("inst", text);
    }

    @Override
    public void msg(String text) {
        writeDiv("msg", text);
    }

    @Override
    public void message(String text) {
        writeDiv("message", text);
    }

    @Override
    public void err(String text) {
        writeDiv("err", text);
    }

    @Override
    public void wrong(String text) {
        writeDiv("wrong", text);
    }

    @Override
    public void warning(String text) {
        writeDiv("warning", text);
    }

    @Override
    public void oops(String text) {
        writeDiv("oops", text);
    }

    @Override
    public void mistake(String text) {
        writeDiv("", text);
    }

    @Override
    public void success(String text) {
        writeDiv("", text);
    }

    @Override
    public void complete(String text) {
        writeDiv("", text);
    }

    @Override
    public void finished(String text) {
        writeDiv("", text);
    }

    @Override
    public void pass(String text) {
        writeDiv("", text);
    }

    @Override
    public void fail(String text) {
        writeDiv("", text);
    }

    @Override
    public void error(String text) {
        writeDiv("", text);
    }

    @Override
    public void block(String text) {
        writeDiv("", text);
    }

    @Override
    public void notApplicable(String text) {
        writeDiv("", text);
    }

    @Override
    public void deprecated(String text) {
        writeDiv("", text);
    }

}
