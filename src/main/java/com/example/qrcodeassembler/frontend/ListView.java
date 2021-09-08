package com.example.qrcodeassembler.frontend;

import com.example.qrcodeassembler.backend.InitialQrProperties;
import com.example.qrcodeassembler.backend.entity.order.temporarytable.box.BoxContent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

@Route("")
@Push
public class ListView extends VerticalLayout{

    private Grid<BoxContent> grid;
    private Label numberBoxLabel = new Label("Номер короба: ");
    private Label inBoxNeedLabel = new Label("Надо: ");
    private Label inBoxNowLabel = new Label("Собрано: ");
    private TextField numberBox = new TextField();
    private TextField inBoxNeed = new TextField();
    private TextField inBoxNow = new TextField();
    private Dialog dialog = new Dialog();

    private SerialPort scannerQr;
    private String macAddress;

    private final InitialQrProperties qrCodeService;

    private static Logger logger = LoggerFactory.getLogger(ListView.class);

    public ListView(InitialQrProperties qrCodeService) {
        this.qrCodeService = qrCodeService;

        configureGrid();
        add(getToolBar(), grid);
        macAddress = this.qrCodeService.getMacAddress();

        try {
            scannerQr = new SerialPort(this.qrCodeService.getDevicePort());
            try {
                scannerQr.openPort();
                scannerQr.setParams(SerialPort.BAUDRATE_9600,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                scannerQr.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                        SerialPort.FLOWCONTROL_RTSCTS_OUT);
                scannerQr.addEventListener(
                        newQrCode -> {
                            try {
                                StringBuilder readString = new StringBuilder();
                                readString.append(scannerQr.readString(newQrCode.getEventValue()));
                                UI ui = getUI().get();
                                ui.access(
                                        () ->{
                                            String readQrValue = readString.toString();
                                            if (readQrValue.length() <= 255) {
                                                // обработка считанного штрихкода
                                            }
                                            else {
                                                // считайте штрихкод ещё раз
                                            }
                                        }
                                );

                            } catch (SerialPortException e) {
                                // проблема со считыванием значения
                            }
                        }
                        , SerialPort.MASK_RXCHAR);
            }
            catch (SerialPortException e) {
                // проблема с открытием порта. сканер уже занят другой программой
            }
        } catch (FileNotFoundException e) {
            // файл application.properties не лежит в папке с jar
        }
    }
    // конфигурация таблицы
    public void configureGrid () {
        grid = new Grid<>(BoxContent.class);
        grid.setSizeFull();
        grid.removeAllColumns();
        grid.addColumn(BoxContent::getArticle).setHeader("Артикул");
        grid.addColumn(BoxContent::getSize).setHeader("Размер");
        grid.addColumn(BoxContent::getColor).setHeader("Цвет");
        grid.addColumn(BoxContent::getCountNow).setHeader("Количество собрано");
        grid.addColumn(BoxContent::getCountNeed).setHeader("Количество надо");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
    // конфигурация текстовых полей
    public HorizontalLayout getToolBar () {
        HorizontalLayout layout = new HorizontalLayout(numberBoxLabel, numberBox, inBoxNeedLabel, inBoxNeed, inBoxNowLabel, inBoxNow);
        numberBox.setReadOnly(true);
        inBoxNeed.setReadOnly(true);
        inBoxNow.setReadOnly(true);
        return layout;
    }


}
