public class Lucky {
    static int x = 0;
    static int count = 0;
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                int temp = xIncAndGet();
                if (temp < 999999) {
                    if ((temp % 10) + (temp / 10) % 10 + (temp / 100) % 10 == (temp / 1000)
                            % 10 + (temp / 10000) % 10 + (temp / 100000) % 10) {
                        System.out.println(temp);
                        countInc();
                    }
                }
                else return;
            }
        }
        private int xIncAndGet () {
            synchronized (lock1) {
                x++;
                return x;
            }
        }
        private void countInc () {
            synchronized (lock2) {
                count++;
            }
        }
    }
}