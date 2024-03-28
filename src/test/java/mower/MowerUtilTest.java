package mower;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mower.service.MowerService;
import org.mower.util.MowerUtil;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MowerUtilTest {
    @Mock
    private MowerService mowerService;

    private MowerUtil mowerUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mowerUtil = new MowerUtil(mowerService);
    }

    @Test
    public void testProcessInputFile_ValidInput() throws FileNotFoundException {
        String inputFileName = "src/test/resources/valid_input.txt";
        File inputFile = new File(inputFileName);

        mowerUtil.processInputFile(inputFileName);

        verify(mowerService, times(1)).move(any(), anyString(), anyInt(), anyInt());
    }

    @Test
    public void testProcessInputFile_InvalidInputFile() {
        String inputFileName = "src/test/resources/invalid_input.txt"; // File that does not exist

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));

        mowerUtil.processInputFile(inputFileName);

        assertEquals("Input file not found!", outContent.toString().trim());
    }

    @Test
    public void testProcessInputFile_EmptyInputFile() throws IOException {
        String inputFileName = "src/test/resources/empty_input.txt";
        File inputFile = new File(inputFileName);
        inputFile.createNewFile(); // Create an empty file

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));

        mowerUtil.processInputFile(inputFileName);

        assertEquals("The file is empty!", outContent.toString().trim());
    }
}