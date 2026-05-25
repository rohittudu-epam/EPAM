public class Editor {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    // Creates a memento (EditorState) with current content
    public EditorState createState() {
        return new EditorState(content);
    }

    // Restores content from a memento (EditorState)
    public void restore(EditorState state) {
        this.content = state.getContent();
    }
}