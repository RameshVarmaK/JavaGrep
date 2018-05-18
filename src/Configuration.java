public class Configuration {
    private String pattern;
    private Matcher matcher;
    private InputFormat inputFormat;
    private String inputSource;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcherType() {
        return matcher;
    }

    public void setMatcherType(Matcher matcher) {
        this.matcher = matcher;
    }

    public InputFormat getInputType() {
        return inputFormat;
    }

    public void setInputType(InputFormat inputFormat) {
        this.inputFormat = inputFormat;
    }

    public String getInputSource() {
        return inputSource;
    }

    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }
}
