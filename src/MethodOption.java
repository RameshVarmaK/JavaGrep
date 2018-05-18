import com.ramesh.matcher.JavaBasedMatcher;
import com.ramesh.matcher.custom.AutomataBasedMatcher;

public class MethodOption{

    public Object choose(String input) {
        if(input.equals("-c")){
            return new AutomataBasedMatcher(input);
        }
        return new JavaBasedMatcher(input);
    }
}
