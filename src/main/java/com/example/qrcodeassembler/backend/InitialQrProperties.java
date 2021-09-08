package com.example.qrcodeassembler.backend;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class InitialQrProperties {
    // получение порта сканера
    public String getDevicePort() throws FileNotFoundException {
        BufferedReader fileWithConfig = new BufferedReader(new FileReader(new File(new File(".").getAbsolutePath(), "application.properties")));
        try {
            String s = fileWithConfig.readLine();
            while (s != null) {
                Pattern pattern = Pattern.compile("device.port=(.[^\r\n]*)");
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    return matcher.group(1);
                }
                s = fileWithConfig.readLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException("");
        }
        return "COM3";
    }
    // получение mac адреса компьютера из файла, используется в случае, если не удалось определить mac адрес с помощью метода getMacAddress
    public String getMacInFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(new File(".").getAbsolutePath(), "application.properties")));
            try {
                String s = bufferedReader.readLine();
                while (s != null) {
                    Pattern pattern = Pattern.compile("mac=(.[^\r\n]*)");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.find()) {
                        return matcher.group(1);
                    }
                    s = bufferedReader.readLine();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException exception) {
            throw new IllegalStateException("");
        }
        return "";
    }
    // получение Mac адреса PC сборщика
    public String getMacAddress() {
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
            try {
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                byte[] mac = network.getHardwareAddress();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length-1) ?  "-" : ""));
                }
                String macAddr = sb.toString();
                if (!macAddr.isEmpty()) {
                    return macAddr;
                }
                else {
                    return getMacInFile();
                }
            } catch (SocketException e) {
                return getMacInFile();
            }
        } catch (UnknownHostException e) {
            return getMacInFile();
        }
    }
}
