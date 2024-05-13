public class Main 
{
    public static final int[] powersOfTwo = 
    {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768};
    public static void main(String[] args) 
    {
        long startNaive, endNaive, startFast, endFast;
        
        
        startNaive = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) 
        {
            naiveMult(200, 113);
        }
        endNaive = System.currentTimeMillis();

        startFast = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) 
        {
            fastMult(200, 113);
        }
        endFast = System.currentTimeMillis();

        System.out.println("naiveMult() Time: " + (endNaive - startNaive) + " miliseconds");
        System.out.println("fastMult() Time: " + (endFast - startFast) + " miliseconds");
    }


    public static int naiveMult(int x, int y)
    {
        int product = 0;
        for(int i = 0; i < y; i++)
        {
            product += x;
        }

        return product;
    }

    public static int fastMult(int x, int y)
    {
        int product = 0;
        for(int i = 0; i < 16; i++)
        {
            if((y & powersOfTwo[i]) != 0)
            {
                product += x;
            }
            x = x + x;
        }

        return product;
    }
}
