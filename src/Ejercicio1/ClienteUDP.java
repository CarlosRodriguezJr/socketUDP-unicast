
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class ClienteUDP {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost"); // Dirección del servidor

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String mensaje = "Solicito fecha y hora";
        sendData = mensaje.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        socket.send(sendPacket); // Enviar el mensaje al servidor
        socket.setSoTimeout(5000);

        try {
            //Respuesta del servidor
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String respuesta = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Fecha y hora del servidor: " + respuesta);

        } catch (SocketTimeoutException e) {            
            System.out.println("Error: Tiempo de espera agotado. No se recibió respuesta del servidor.");
        }

        socket.close(); 
    }
}