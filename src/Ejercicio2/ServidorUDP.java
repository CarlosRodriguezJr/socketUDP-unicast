import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServidorUDP {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9876); 
        byte[] receiveData = new byte[1024]; 
        byte[] sendData;

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket); 

            String mensajeCliente = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Mensaje recibido: " + mensajeCliente);

            String fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            sendData = fechaHora.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            socket.send(sendPacket);
        }
    }
}