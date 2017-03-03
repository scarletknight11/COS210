package edu.mccc.cos210.ds;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class QueueTests {
	private static StringBuilder report = new StringBuilder();
	@Rule 
	public TestWatcher watchman = new TestWatcher() {
	    @Override
	    protected void failed(Throwable t, Description description) {
	        report.append("  FAILURE: ").append(description.getMethodName()).append("\n");
	    }
	    @Override
	    protected void succeeded(Description description) {
	        report.append("  Success: ").append(description.getMethodName()).append("\n");
	    }
	};
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		report.append("\nTesting: ").append(QueueTests.class.getSimpleName()).append("\n");
	}
	@AfterClass
	public static void tearDownClass() {
	    System.out.println(report.toString());
	}
	@Test
	public void test() {
		IQueue<Double> queue = new Queue<>();
		queue.enqueue(Math.PI);
		queue.enqueue(Math.E);
		assertEquals("3.14159", queue.peek().toString().substring(0, 7));
		assertEquals("3.14159", queue.dequeue().toString().substring(0, 7));
		assertEquals("2.7182", queue.dequeue().toString().substring(0, 6));
		assertEquals(true, queue.isEmpty());
	}
}
