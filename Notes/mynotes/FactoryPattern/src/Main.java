//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    List<Integer> lt = new ArrayList<>();
    OSFactory obj = new OSFactory();
    OS os = obj.getInstance("OPEN");
    os.spec();
}
