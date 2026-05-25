//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

//    Creating a Root Folder
    Folder root = new Folder("Root");
    File file1 = new File("file1.txt");
    File file2 = new File("file2.txt");

//    Creating a Sub Folder
    Folder subFolder = new Folder("SubFolder");
    File file3 = new File("file3.txt");

//    Adding file to Root
    root.addComponent(file1);
    root.addComponent(file2);
    root.addComponent(subFolder);

//    Adding Files to subFolder
    subFolder.addComponent(file3);

    root.showDetails();
}
