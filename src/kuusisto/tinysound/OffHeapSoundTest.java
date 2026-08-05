package kuusisto.tinysound;

public class OffHeapSoundTest {
    public OffHeapSoundTest() throws Exception {
        TinySound.init();

        System.out.println("1. 초기 상태: OS 작업 관리자에서 프로세스의 메모리 사용량을 확인하세요.");
        Thread.sleep(7000);

        for (int cycle = 1; cycle <= 5; cycle++) {
            System.out.println("\n=== [ " + cycle + "회차 테스트 시작 ] ===");

            Sound[] sounds = new Sound[100];
            for (int i = 0; i < 100; i++) {
                sounds[i] = TinySound.loadSound("bgm3.wav");
            }
            System.out.println("로드 완료 :)");
            Thread.sleep(7000);

            for (int i = 0; i < 100; i++) {
                sounds[i].free();
            }

            // JVM에 가비지 컬렉션을 강력히 권고 (잔여 Heap 정리)
            System.gc();

            System.out.println("언로드 완료 :)");
            Thread.sleep(7000);
        }

        Thread.sleep(800000); // 5초간 재생 확인

        //TinySound.shutdown();
    }

    public static void main(String[] args) throws Exception {
        new OffHeapSoundTest();
    }
}