import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class p1 {
    public static void encrypt(int key) {
        JFileChooser chooseFile = new JFileChooser();
        chooseFile.showOpenDialog(null); // open dialog box for choosing file at center
        File file = chooseFile.getSelectedFile();

        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            int i = 0;
            for (byte b : data) {
                data[i] = (byte)(b ^ key);
                i++;
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Image Successfully Encrypted/Decrypted");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("ImageCryptography");
        f.setSize(700, 700);
        f.setLocationRelativeTo(null); // comes at center
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        Font font = new Font("Roboto", Font.BOLD, 25);

        //creating button
        JButton bttn = new JButton();
        bttn.setText("Open Image");
        bttn.setFont(font);
        
        // creating textField
        JTextField tf = new JTextField(15);
        tf.setFont(font);
        
        f.setLayout(new FlowLayout());

        bttn.addActionListener(e->{ // lambda function
            System.out.println("button clicked");
            String keyText = tf.getText();
            int tmp = Integer.parseInt(keyText);
            encrypt(tmp);
        });
        
        f.add(bttn);
        f.add(tf);
        f.setVisible(true);
    }
}