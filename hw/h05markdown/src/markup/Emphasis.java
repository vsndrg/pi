package markup;

import java.util.List;

public class Emphasis extends AbstractMarkdown {

    public Emphasis(List<Markdown> markdowns) {
        super(markdowns);
    }

    @Override
    protected String getStartTag() {
        return "*";
    }

    @Override
    protected String getEndTag() {
        return "*";
    }
}

