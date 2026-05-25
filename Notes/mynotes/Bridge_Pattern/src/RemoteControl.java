public class RemoteControl {
    protected Device device;

    RemoteControl(Device device){
        this.device = device;
    }

    void togglePower(){
        if (device.isEnabled()) device.disable();
        else device.enable();
    }

    void volumeDown(){
        device.setVolume(device.getVolume() - 10);
    }

    void volumeUp(){
        device.setVolume(device.getVolume() + 10);
    }

    void channelUp(){
        device.setChannel(device.getChannel() + 1);
    }

    void channelDown(){
        device.setChannel(device.getChannel() + 1);
    }
}
