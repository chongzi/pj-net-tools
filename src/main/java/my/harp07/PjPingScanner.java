package my.harp07;

import java.util.Arrays;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static my.harp07.GenericPJ.ipv;
import static my.harp07.GenericPJ.pingIp;
import static my.harp07.GenericPJ.su;
import static my.harp07.PjFrame.frame;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.validator.routines.InetAddressValidator;

public class PjPingScanner {
    
    private static int pingtimeout;
    public static String result = "\n Ping-Scanner data:\n";
    public static String resultUP = "\n Ping-Scanner data for UP:\n";
    public static String resultDOWN = "\n Ping-Scanner data for DOWN:\n";

    public static String[] scannerCIDRS_MASKS = {
        //"/22=255.255.252.0",
        "/23=255.255.254.0",
        "/24=255.255.255.0",
        "/25=255.255.255.128",
        "/26=255.255.255.192",
        "/27=255.255.255.224",
        "/28=255.255.255.240",
        "/29=255.255.255.248",
        "/30=255.255.255.252"
    };
    
    public static final String[] scannerTIMEOUTS = {
        "100",
        "200",
        "300"
    }; 
    
    public static final String[] arrayUpDown = {
        "ALL",
        "UP",
        "DOWN"
    };    

    public static String getResult(String ipadr) {
        frame.btnPingScannerRun.setEnabled(false);
        frame.btnPingScannerSave.setEnabled(false);
        frame.btnPingScannerClear.setEnabled(false);
        pingtimeout=Integer.parseInt(PjFrame.comboPingScannerTimeouts.getSelectedItem().toString());
        su = new SubnetUtils(ipadr);
        //su=new SubnetUtils("10.73.2.111/23");
        //su=new SubnetUtils("10.73.2.111", "255.255.254.0");
        Arrays.asList(su.getInfo().getAllAddresses())
                .parallelStream()//.stream()
                .forEach(x -> {
                    if (pingIp(x, pingtimeout)) {
                        result = result + "\n" + x + " = UP";
                        resultUP = resultUP + "\n" + x + " = UP";
                    } else {
                        result = result + "\n" + x + " = DOWN";
                        resultDOWN = resultDOWN + "\n" + x + " = DOWN";                        
                    }
                });
        frame.btnPingScannerRun.setEnabled(true);
        frame.btnPingScannerSave.setEnabled(true);
        frame.btnPingScannerClear.setEnabled(true);        
        return result;
    }

    public static void runGetResult(JTextField ipq, JComboBox maskq, JTextArea taq) {
        String input = ipq.getText().trim() + maskq.getSelectedItem().toString().split("=")[0].trim();
        //System.out.println(input);
        if (!ipv.isValid(ipq.getText().trim())) {
            JOptionPane.showMessageDialog(frame, "Wrong IP !", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            frame.taPingScannerResult.setText("Please Wait !");
            frame.comboPingScannerShow.setSelectedItem("ALL");
            new Thread(()->taq.setText(getResult(input))).start();
        }
    }


}