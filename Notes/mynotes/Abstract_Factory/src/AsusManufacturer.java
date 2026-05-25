public class AsusManufacturer extends Company{
    @Override
    public Gpu createGpu()
    {
        return new AsusGpu();
    }
}
