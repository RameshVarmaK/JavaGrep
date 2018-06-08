import com.ramesh.JavaGrep.MatcherApp;
import com.ramesh.JavaGrep.helpers.WriterException;
import com.ramesh.JavaGrep.options.InputSource;
import com.ramesh.JavaGrep.options.MatcherType;
import com.ramesh.JavaGrep.options.OutputSourceType;
import com.ramesh.JavaGrep.options.OutputType;
import com.ramesh.JavaGrep.utils.ConfigurationBuilder;

import java.util.Optional;
import java.util.OptionalInt;

public class CommandLine {

    public static void main(String[] args) throws Exception {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setPattern(args[getPatternPosition(args)]);
        configurationBuilder.setMatcherType(getMatcher(args));
        configurationBuilder.setInputSource(InputSource.getNameForValue(args[getInputType(args)]).get());
        configurationBuilder.setInputSourceData(args[getInputType(args) + 1]);
        configurationBuilder.setOutputSourceType(OutputSourceType.valueFor(args[getOutputSourcePosition(args)]).get());
        configurationBuilder.setOutputSourceData(getOutputSourceData(args));
        configurationBuilder.setOutputType(getOutputType(args));
        MatcherApp matcherApp = new MatcherApp();
        matcherApp.match(configurationBuilder.buid());
    }

    private static OutputType getOutputType(String[] args) {
        Optional<OutputType> optionalOutputType = Optional.empty();
        for (String arg : args) {
            if (OutputType.get(arg).isPresent()) {
                optionalOutputType = Optional.of(OutputType.get(arg).get());
                break;
            }
        }
        return optionalOutputType.get();
    }

    private static String getOutputSourceData(String[] args) throws WriterException {
        Optional<String> optionalS = Optional.empty();
        if (OutputSourceType.valueFor(args[getOutputSourcePosition(args)]).get() == OutputSourceType.FILE) {
            return args[getOutputSourcePosition(args) + 1];
        }
        return null;
    }

    private static int getOutputSourcePosition(String[] args) throws WriterException {
        OptionalInt optionalInt = OptionalInt.empty();
        for (int i = 0; i < args.length; i++) {
            if (OutputSourceType.valueFor(args[i]).isPresent()) {
                optionalInt = OptionalInt.of(i);
                break;
            }
        }
        return optionalInt.getAsInt();
    }

    private static int getPatternPosition(String[] args) {
        OptionalInt optionalInt = OptionalInt.empty();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) {
                optionalInt = OptionalInt.of(i + 1);
                break;
            }
        }
        return optionalInt.getAsInt();
    }

    private static MatcherType getMatcher(String[] args) throws WriterException {
        Optional<MatcherType> optionalMatcher = Optional.empty();
        for (String arg : args) {
            if (MatcherType.valueFor(arg) != null) {
                optionalMatcher = Optional.of(MatcherType.valueFor(arg).get());
                break;
            }
        }
        return optionalMatcher.get();
    }


    private static int getInputType(String[] args) {
        OptionalInt optionalInt = OptionalInt.empty();
        for (int i = 0; i < args.length; i++) {
            Optional<InputSource> optionalInputSource = InputSource.getNameForValue(args[i]);
            if (!optionalInputSource.equals(Optional.empty())) {
                optionalInt = OptionalInt.of(i);
            }
        }
        return optionalInt.getAsInt();
    }

}