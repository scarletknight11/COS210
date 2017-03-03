package edu.mccc.cos210.if2rpn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.mccc.cos210.ds.IQueue;
import edu.mccc.cos210.ds.IStack;
import edu.mccc.cos210.ds.Queue;
import edu.mccc.cos210.ds.Stack;

public class Converter {
	private static final Pattern p1 = Pattern.compile(
		"(-?[0-9]+\\.?[0-9]*)(.*)"
	);
	private static final Pattern p2 = Pattern.compile(
		"([()*/+-])(.*)"
	);
	private IQueue<Object> outqueue = new Queue<>();
	private IStack<String> opstack = new Stack<>();
	public static void main(String[] sa) throws IOException {
		new Converter().doIt();
	}
	private void doIt() throws IOException {
		try (
			BufferedReader br = new BufferedReader(
				new InputStreamReader(
					System.in
				)
			);
		) {	
			String s = "";
			while ((s = br.readLine()) != null) {
				try {
					String t = s.trim();
					t = t.replace(" ", "");
					if (t.length() == 0) {
						continue;
					}
					System.out.println(t);
					boolean needNumber = true;
					while (t.length() != 0) {
						Matcher m = p1.matcher(t);
						if (needNumber && m.matches()) {
							outqueue.enqueue(
								Double.parseDouble(
									m.group(1)
								)
							);
							t = m.group(2);
							needNumber = false;
						} else {
							m = p2.matcher(t);
							if (m.matches()) {
								doToken(m.group(1));
								t = m.group(2);
							} else {
								throw new SyntaxError();
							}
							needNumber = true;
						}
					}
					while (!opstack.isEmpty()) {
						String op = opstack.peek();
						if ("(".equals(op)) {
							throw new SyntaxError();
						}
						opstack.pop();
						outqueue.enqueue(op);
				
					}
					System.out.println(outqueue);
					calc();
				} catch (
					SyntaxError | NoSuchElementException ex) {
					System.err.println("Syntax Error");
				} finally {
					outqueue = new Queue<>();
					opstack = new Stack<>();
				}
			}
			br.close();
		} catch (IOException ex) {
			throw ex;
		}
	}
	private void doToken(String s) throws SyntaxError {
		String t = "";
		switch (s) {
			case "(":
				opstack.push(s);
				break;
			case ")":
				t = opstack.peek();
				while (!"(".equals(t)) {
					opstack.pop();
					outqueue.enqueue(t);
					t = opstack.peek();
				}
				opstack.pop();
				break;
			case "+":
			case "-":
				while (!opstack.isEmpty()) {
					t = opstack.peek();
					if (
						"+".equals(t) ||
						"-".equals(t) ||
						"*".equals(t) ||
						"/".equals(t)
					) {
						opstack.pop();
						outqueue.enqueue(t);
					} else {
						break;
					}
				}
				opstack.push(s);
				break;
			case "*":
			case "/":
				while (!opstack.isEmpty()) {
					t = opstack.peek();
					if (
						"*".equals(t) ||
						"/".equals(t)
					) {
						opstack.pop();
						outqueue.enqueue(t);
					} else {
						break;
					}
				}
				opstack.push(s);
				break;
			case " ":
			case "\t":
				break;
			default:
				throw new SyntaxError();
		}
	}
	private void calc() throws SyntaxError {
		IStack<Double> stack = new Stack<>();
		Double value = 0.0;
		while (!outqueue.isEmpty()) {
			Object o = outqueue.dequeue();
			if (o instanceof Double) {
				stack.push((Double) o);
			} else {
			String op = (String) o;
			Double d2 = stack.peek();
			stack.pop();
				Double d1 = stack.peek();
				stack.pop();
				switch (op) {
					case "+":
						value = d1 + d2;
						break;
					case "-":
						value = d1 - d2;
						break;
					case "*":
						value = d1 * d2;
						break;
					case "/":
						value = d1 / d2;
						break;
				}
				stack.push(value);
			}
		}
		System.out.println(stack.peek());
	}
	private class SyntaxError extends Exception {
		private static final long serialVersionUID = 1L;
		public SyntaxError(Exception ex) {
			super("Syntax Error", ex);
		}
		public SyntaxError() {
			this(null);
		}
	}
}