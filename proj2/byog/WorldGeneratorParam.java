package byog;


//随机数生成参数 长度、高度 由SEED来决定
class WorldGeneratorParam {
    private int width;
    private int height;
    private long seed;

    public WorldGeneratorParam() {
        this.width = 80;
        this.height = 30;
        this.seed = 587667;
    }

    public WorldGeneratorParam(int w, int h, long seed) {
        this.width = w;
        this.height = h;
        this.seed = seed;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public long seed() {
        return this.seed;
    }

    //设置world的各项参数。
    public WorldGeneratorParam setWorldGeneratorParam(int w, int h, long s) {
        WorldGeneratorParam wgp = new WorldGeneratorParam();
        wgp.width = w;
        wgp.height = h;
        wgp.seed = s;
        return wgp;
    }
}
