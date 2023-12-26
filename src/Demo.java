import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author conan 2023/12/26
 */

public class Demo {
    public static final String BGM; //bgm文件路径
    static Clip music = null; //声明Clip接口
    static File sourceFile = null; //声明文件变量

    static int index = 0;
    static List<String> list;
    static {
        list = new ArrayList<>();
        list.add("D:\\mianshi\\src\\王杰 - 伤心1999(1).wav");
        list.add("D:\\mianshi\\src\\zxy-rjd.wav");
        BGM = list.get(0);
    }

    public static void playMusic(String path) {
        try {
            music = AudioSystem.getClip(); // 获取可用于播放音频文件或音频流的数据流
            sourceFile = new File(path);//获取文件
            AudioInputStream ais = AudioSystem.getAudioInputStream(sourceFile);//获得指示格式的音频输入流
            music.open(ais); //打开数据流
            System.out.println("frameLength = " + music.getFrameLength());
            music.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void nextMusic() throws InterruptedException {
        index = (index + 1) % list.size();
        music.close();
        playMusic(list.get(index));
    }

    public static void closeMusic(){
        if (music!=null)    //需要判断music是否为null，避免出现空指针异常
            music.stop();//暂停音乐
    }

    public static int getFrameLength(Clip music) {
        return music.getFrameLength();
    }

    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Demo.playMusic(Demo.BGM);
        Scanner scan = new Scanner(System.in);
        while (true){
            nextMusic();
            Thread.sleep(5000);
        }

    }

}
