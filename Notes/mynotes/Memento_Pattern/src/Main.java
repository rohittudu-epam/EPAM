//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Editor editor = new Editor();
    History history = new History();

    editor.setContent("First version");
    history.push(editor.createState());

    editor.setContent("Second version");
    history.push(editor.createState());

    editor.setContent("Third version");
    System.out.println(editor.getContent());

    // Undo
    editor.restore(history.pop());
    System.out.println(editor.getContent()); // Output: Second version

    editor.restore(history.pop());
    System.out.println(editor.getContent());
}
