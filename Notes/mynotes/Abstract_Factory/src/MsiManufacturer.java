public class MsiManufacturer extends Company{
    @Override
    public Gpu createGpu() {
        return new MsiGpu();
    }
}
