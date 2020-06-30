package src.main.java.halsteadMetrics.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import src.main.java.halsteadMetrics.java.ASTVisitorMod;
import src.main.java.halsteadMetrics.java.Main;

public class MainTest {
	
	@Test
	public void testNumberDistOperators() throws IOException {	
		
		Main testMain = new Main();
		ASTVisitorMod ASTVisitorFile;
		ASTVisitorFile=testMain.parse(testMain.ParseFilesInDir(testMain.retrieveFiles("test_datasets/TestCorrecteness")).get(0));
		
		assertEquals(10, ASTVisitorFile.oprt.size());
	}
	
	
	@Test
	public void testNumberDistOperands() throws IOException {	
		
		Main testMain = new Main();
		ASTVisitorMod ASTVisitorFile;
		ASTVisitorFile=testMain.parse(testMain.ParseFilesInDir(testMain.retrieveFiles("test_datasets/TestCorrecteness")).get(0));
		
		assertEquals(17, ASTVisitorFile.names.size());
	}
	
}
