package markup;

import java.util.List;

public class Strong extends AbstractMarkdown {

    public Strong(List<Markdown> markdowns) {
        super(markdowns);
    }

    @Override
    protected String getStartTag() {
        return "__";
    }

    @Override
    protected String getEndTag() {
        return "__";
    }
}

