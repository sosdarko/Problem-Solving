package main;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SolutionTestFullJustify {
	
	private static final Solution SOLUTION = new Solution();

	@Test
	public void testFullJustify() {
    	String words[] = {"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
    	List<String> r = SOLUTION.fullJustify(words, 16);
    	List<String> answer = Arrays.asList("ask   not   what", "your country can", "do  for  you ask", "what  you can do", "for your country");
    	assertEquals(answer, r);
	}
	
	@Test
	public void testFullJustify2() {
    	String words[] = {"What","must","be","acknowledgment","shall","be"};
    	List<String> r = SOLUTION.fullJustify(words, 16);
    	List<String> answer = Arrays.asList("What   must   be", "acknowledgment  ", "shall be        ");
    	assertEquals("second test", answer, r);
	}

}
