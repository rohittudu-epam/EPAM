public class ErasureTool extends Tool {

    @Override
    public void mouseDown() {
        System.out.println("Selected Erasure Tool");
    }

    @Override
    public void mouseUp() {
        System.out.println("Erased Object");
    }
}
