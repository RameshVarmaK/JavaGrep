import java.util.LinkedList;

import com.ramesh.matcher.*;

public class JavaGrep {

	public static void main(String[] args) {

		// StringMatcher javaMatcher = new JavaBasedMatcher(args[0]);
		// StringMatcher customMatcher = new CustomMatcher(args[0]);
		// for (int i = 1; i < args.length; i++) {
		// System.out.println("default: " + javaMatcher.isMatched(args[i]));
		// System.out.println("custom: " + customMatcher.isMatched(args[i]));
		// }
		LinkedList<Node> nodesList = new LinkedList<Node>();
		AutomataBasedMatcher matcher = new AutomataBasedMatcher("((abc)*a)+ba*b.");
		nodesList = matcher.constructFSA("((abc)*a)+c*b.");

	}

}
