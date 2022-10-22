
package my.harp07;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.InetAddressValidator;

public class GenericPJ {
    
    public static SubnetUtils su;
    public static InetAddressValidator ipv = InetAddressValidator.getInstance();
    public static DomainValidator dnsv = DomainValidator.getInstance();
    public static String ping_remark="Works well and tested with Root privileges on Linux ! \nYou can check this by iptraf linux utility. \nRun as root/admin user !\n";
    
    // ВИСНЕТ КОГДА НЕ РАБОТАЕТ DNS И БЛОКИ CATCH ПУСТЫЕ !!!
    public static Boolean pingIp(String ipad, int timeout) {
        try {
            return InetAddress.getByName(ipad).isReachable(timeout);
        } 
        catch (UnknownHostException ex) {
            System.out.println("//_ping_DNS-error_UnknownHostException="+ex.getMessage());
        } 
        catch (IOException | NullPointerException ex) {  
            System.out.println("//_ping_IOException/NullPointerException="+ex.getMessage());
        }
        return false;
    }   
    
    public static String about() {
        String msg = "<html><body><p style='margin-left: 50px'>PJ-NET-TOOLS:<br><br>"
                + "\nPure Java Network Tools. Run as root/admin user ! Include:<br><br>"
                + "\n01. ICMP-ping;<br>"
                + "\n02. ICMP-trace;<br>"
                + "\n03. DNS-checker.<br>"
                + "\n04. TCP-scanner.<br>"
                + "\n05. IP-calculator.<br>"
                + "\n06. Syslog-server.<br>"
                + "\n07. Telnet-client.<br>"
                + "\n08. Local ARP request.<br>"
                + "\n09. Network Ping-Scanner.<br>"
                + "\n10. Snmp-Get concrete value utility.<br>"
                + "\n11. ICMP-flood utility.<br>"
                + "\n12. UDP-flood utility.<br>"
                + "\n13. TFTP-server.<br>"
                + "\n14. NTP-server.<br><br>"
                + "\nCreate by Roman Koldaev, Saratov city, Russia.<br>"
                + "\nmail: <A HREF='mailto:harp07@mail.ru'> harp07@mail.ru </A><br>"
                + "\nSourceForge: <a href='https://sf.net/u/harp07/profile/'>https://sf.net/u/harp07/profile/</a><br>"
                + "\nGitHub: <a href='https://github.com/harp077/'>https://github.com/harp077/</a><br>"
                + "\nneed jre-1.8.<br><br></p></body></html>";
                return msg;
    }
    
}
