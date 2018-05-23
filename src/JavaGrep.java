import java.io.IOException;

public class JavaGrep {

    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();
        configuration.setPattern(args[getPatternPosition(args)]);
        configuration.setMatcherType(getMatcher(args));
        configuration.setInputSource(InputSource.getNameForValue(args[getInputType(args)]));
        configuration.setInputSourceData(args[getInputType(args)+1]);
        configuration.setOutputSource(OutputSource.get(args[getOutputSourcePosition(args)]));
        configuration.setOutputSourceData(getOutputSourceData(args));
        configuration.setOutputType(getOutputType(args));
        MatcherApp matcherApp = new MatcherApp();
        matcherApp.matcher(configuration);

    }

    private static OutputType getOutputType(String[] args){
        for (int i=0;i<args.length;i++) {
            if (OutputType.get(args[i]) != null) {
                return OutputType.get(args[i]);
            }
        }
        return null;
    }
    private static String getOutputSourceData(String[] args){
        if(OutputSource.get(args[getOutputSourcePosition(args)])==OutputSource.FILE){
            return args[getOutputSourcePosition(args)+1];
        }
        return null;
    }
    private static int getOutputSourcePosition(String[] args){
        for (int i=0;i<args.length;i++) {
            if (OutputSource.get(args[i]) != null) {
                return i;
            }
        }
        return -1;
    }
    private static int getPatternPosition(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) {
                return i + 1;
            }
        }
        return -1;
    }

    private static Matcher getMatcher(String[] args) {
        for (int i=0;i<args.length;i++) {
            if (Matcher.getNameForValue(args[i]) != null) {
                return Matcher.getNameForValue(args[i]);
            }
        }
        return null;
    }


    private static int getInputType(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (InputSource.getNameForValue(args[i]) != null) {
                return i;
            }
        }
        return -1;
    }

}