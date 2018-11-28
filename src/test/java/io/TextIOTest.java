package io;

import org.junit.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import javax.crypto.AEADBadTagException;

import  org.mockito.ArgumentCaptor;
import static org.junit.Assert.*;

import data_access.*;
import io.*;


public class TextIOTest {
        ByteArrayOutputStream outContent;
        // ByteArrayInputStream inContent;
        TextIO textIO;
	
        public TextIOTest() {
        }

        @Before
	public void setUp() {
                outContent = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outContent));
                textIO = new TextIO();

                

	}

        @Test
        public void printWorks() {

        textIO.print("does this work?");

        assertEquals("does this work?\n", outContent.toString());
        }

        @Test
        public void readIntWorks() {
            ByteArrayInputStream inContentt = new ByteArrayInputStream("8".getBytes());
            System.setIn(inContentt);
            TextIO ttt = new TextIO();
            int lineContent = ttt.readInt("does this work?");

            assertEquals("does this work?\n", outContent.toString());
            assertEquals(8, lineContent);
        }

        @Test
        public void readLineWorks() {
            ByteArrayInputStream inContents = new ByteArrayInputStream("yes it works".getBytes());
            System.setIn(inContents);
            TextIO tt = new TextIO();
            String lineContent = tt.readLine("does this work?");

            assertEquals("does this work?\n", outContent.toString());
            assertEquals("yes it works", lineContent);
        }
}
