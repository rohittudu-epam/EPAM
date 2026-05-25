public abstract class Company {
    public Gpu assembleGpu()
    {
        Gpu gpu = createGpu();
        gpu.assesmble();
        return gpu;
    }

    public abstract Gpu createGpu();
}
