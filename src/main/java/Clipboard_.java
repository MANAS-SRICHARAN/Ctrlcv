import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clipboard_ {
    static Frame simple_frame;
    Clipboard_(String title){
        simple_frame = new Frame(title);
    }

    public static void main(String[] args) throws IOException, UnsupportedFlavorException, InterruptedException {


         Clipboard_ app = new Clipboard_("ClipApp");
         app.play();
    }

    public void play() throws IOException, UnsupportedFlavorException, InterruptedException {
        simple_frame.setSize(800, 500);

        simple_frame.setResizable(false);
        simple_frame.addWindowListener(new Eventhandler());
        simple_frame.setBackground(Color.BLACK);

        TextArea ta = new TextArea("Following would be the copied data \n");
        ta.setBounds(0,0,800,500);
        ta.setBackground(Color.BLACK);
        ta.setForeground(Color.GRAY);
        ta.setFont(new Font("TimesRoman", Font.BOLD, 24));

        simple_frame.add(ta);
        simple_frame.setVisible(true);
        String last_content="";
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        while(true) {
            Thread.sleep(3000);

             String Data= (String)systemClipboard.getData(DataFlavor.stringFlavor);

             if(!last_content.equals(Data)){
                LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String date_time_string = ldt.format(myFormatObj);

                 ta.append("\n\t*******************"+date_time_string+"*********************\t\n");
                 ta.append("\t"+Data+"\n");
                 ta.append("*********************************************************************");
             }
             last_content = Data;

        }

    }
}

class Eventhandler extends WindowAdapter{
    public void windowClosing(WindowEvent e){
        Clipboard_.simple_frame.dispose();
        System.exit(0);
    }
}
