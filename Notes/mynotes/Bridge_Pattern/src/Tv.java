public class Tv implements Device {
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    Tv(){
        System.out.println("Creating a TV");
    }
    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
    }

    @Override
    public void disable() {
        on = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        volume = Math.max(0, Math.min(100, percent));
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
    }
}