package markup;

public class Text implements Markdown {
    protected String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder markdown) {
        markdown.append(text);
    }
}

