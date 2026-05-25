public class Canvas {
    private Tool currentTool;

    public void mouseDown(){
        currentTool.mouseDown();
    }

    public Tool getCurrentTool(){
        return currentTool;
    }

    public void setCurrentTool(Tool currentTool){
    this.currentTool = currentTool;

    }
}
