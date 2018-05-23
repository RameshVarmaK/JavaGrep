public class Configuration {
    private String pattern;
    private Matcher matcher;
    private InputSource inputSource;
    private String inputSourceData;
    private OutputSource outputSource;
    private String outputSourceData;
    private OutputType outputType;

    public OutputSource getOutputSource() {
        return outputSource;
    }

    public void setOutputSource(OutputSource outputSource) {
        this.outputSource = outputSource;
    }

    public OutputType getOutputType() {
        return outputType;
    }

    public void setOutputType(OutputType outputType) {
        this.outputType = outputType;
    }

    public String getOutputSourceData() {
        return outputSourceData;
    }

    public void setOutputSourceData(String outputSourceData) {
        this.outputSourceData = outputSourceData;
    }

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

    public InputSource getInputSource() {
        return inputSource;
    }

    public void setInputSource(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    public String getInputSourceData() {
        return inputSourceData;
    }

    public void setInputSourceData(String inputSourceData) {
        this.inputSourceData = inputSourceData;
    }
}
