package markup;

import java.util.List;

public abstract class AbstractMarkdown implements Markdown {
    private List<Markdown> markdowns;

    protected AbstractMarkdown(final List<Markdown> markdowns) {
        this.markdowns = markdowns;
    }

    @Override
    public void toMarkdown(StringBuilder markdown) {
        markdown.append(getStartTag());
        for (Markdown md : markdowns) {
            md.toMarkdown(markdown);
        }
        markdown.append(getEndTag());
    }

    protected abstract String getStartTag();
    protected abstract String getEndTag();
}

