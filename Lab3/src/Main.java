import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame{
    JPanel panel1;
    JButton button;
    JButton button2;
    JButton button3;
    String path;
    JLabel label;
    JLabel label2;


    public Main(){
        super("Set Picture Into A JLabel Using JFileChooser In Java");
        button = new JButton("Choose image");
        button2 = new JButton("Linear Contrast");
        button3 = new JButton("Adaptive Threshold Processing");
        button.setBounds(250,600,200,40);
        button2.setBounds(250, 660, 200, 40);
        button3.setBounds(225, 720, 250, 40);
        label = new JLabel();
        label2 = new JLabel();
        label2.setBounds(10, 280, 670, 250);
        label.setBounds(10,10,670,250);
        add(button);
        add(label);
        add(label2);
        add(button2);
        add(button3);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                //filter the files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
                file.addChoosableFileFilter(filter);
                int result = file.showSaveDialog(null);
                //if the user click on save in Jfilechooser
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = file.getSelectedFile();
                    path = selectedFile.getAbsolutePath();
                    label.setIcon(ResizeImage(path));
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File imageFile = new File(path);

                BufferedImage originalImage = null;
                try {
                    originalImage = ImageIO.read(imageFile);
                    BufferedImage correctedImage = LinearContrast.whiteBalanceBuffImage(originalImage);
                    label2.setIcon(ResizeImage(correctedImage));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    label2.setIcon(ResizeImage( AdaptiveThresholdProcessing.process(path)));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(720,850);
        setVisible(true);
    }

    // Methode to resize imageIcon with the same size of a Jlabel
    public ImageIcon ResizeImage(BufferedImage ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}
