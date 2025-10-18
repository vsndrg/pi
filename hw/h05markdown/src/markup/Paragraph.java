package markup;

import java.util.List;

public class Paragraph extends AbstractMarkdown {

    public Paragraph(List<Markdown> markdowns) {
        super(markdowns);
    }

    @Override
    protected String getStartTag() {
        return "";
    }

    @Override
    protected String getEndTag() {
        return "";
    }

}

