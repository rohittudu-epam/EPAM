import java.util.ArrayList;
import java.util.List;

public class History {
    private List<EditorState> states = new ArrayList<>();

    public void push(EditorState state) {
        states.add(state);
    }

    public EditorState pop() {
        if (states.isEmpty()) return null;
        return states.remove(states.size() - 1);
    }
}