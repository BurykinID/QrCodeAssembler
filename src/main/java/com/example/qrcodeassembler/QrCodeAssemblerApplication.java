package com.example.qrcodeassembler;

import com.example.qrcodeassembler.backend.InitialQrProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class QrCodeAssemblerApplication {
    public static void main(String[] args) {
        SpringApplication.run(QrCodeAssemblerApplication.class, args);
    }
}

