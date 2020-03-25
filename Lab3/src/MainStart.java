import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MainStart {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadShared();
        new Main();
    }
}
