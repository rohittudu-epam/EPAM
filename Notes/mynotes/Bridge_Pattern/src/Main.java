
void main() {
    Device tv = new Tv();
    RemoteControl remote = new RemoteControl(tv);
    remote.togglePower();

    Device radio = new Radio();
    AdvancedRemoteControl advancedRemote = new AdvancedRemoteControl(radio);
    advancedRemote.mute();
    System.out.println(advancedRemote);
}
