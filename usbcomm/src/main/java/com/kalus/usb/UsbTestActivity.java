package com.kalus.usb;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/10/26 16:48
 **/
public class UsbTestActivity extends Activity implements View.OnClickListener {


    //设备列表
    private HashMap<String, UsbDevice> deviceList;
    //从设备读数据
    private Button read_btn;
    //给设备写数据（发指令）
    private Button write_btn;
    //USB管理器:负责管理USB设备的类
    private UsbManager manager;
    //找到的USB设备
    private UsbDevice mUsbDevice;
    //代表USB设备的一个接口
    private UsbInterface mInterface;
    private UsbDeviceConnection mDeviceConnection;
    //代表一个接口的某个节点的类:写数据节点
    private UsbEndpoint usbEpOut;
    //代表一个接口的某个节点的类:读数据节点
    private UsbEndpoint usbEpIn;
    //要发送信息字节
    private byte[] sendbytes;
    //接收到的信息字节
    private byte[] receiveytes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb);
        initUsbData();
        initViews();
    }

    private void initUsbData() {

        // 获取USB设备
        manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        //获取到设备列表
        deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while (deviceIterator.hasNext()) {
            //if (mVendorID == usb.getVendorId() && mProductID == usb.getProductId()) {//找到指定设备
            mUsbDevice = deviceIterator.next();
            Log.e("ldm", "vid=" + mUsbDevice.getVendorId() + "---pid=" + mUsbDevice.getProductId());
        }
        //获取设备接口
        if (mUsbDevice == null) {
            showTmsg("No Usb Device");
            return;
        }
        for (int i = 0; i < mUsbDevice.getInterfaceCount(); ) {
            // 一般来说一个设备都是一个接口，你可以通过getInterfaceCount()查看接口的个数
            // 这个接口上有两个端点，分别对应OUT 和 IN
            UsbInterface usbInterface = mUsbDevice.getInterface(i);
            mInterface = usbInterface;
            break;
        }
        //用UsbDeviceConnection 与 UsbInterface 进行端点设置和通讯
        if (mInterface.getEndpoint(1) != null) {
            usbEpOut = mInterface.getEndpoint(1);
        }
        if (mInterface.getEndpoint(0) != null) {
            usbEpIn = mInterface.getEndpoint(0);
        }
        if (mInterface != null) {
            // 判断是否有权限
//            if (manager.hasPermission(mUsbDevice)) {
            // 打开设备，获取 UsbDeviceConnection 对象，连接设备，用于后面的通讯
            tryGetUsbPermission();
            mDeviceConnection = manager.openDevice(mUsbDevice);
            if (mDeviceConnection == null) {
                return;
            }
            if (mDeviceConnection.claimInterface(mInterface, true)) {
                showTmsg("找到设备接口");
            } else {
                mDeviceConnection.close();
            }
//            } else {
//                showTmsg("没有权限");
//            }
        } else {
            showTmsg("没有找到设备接口！");
        }
    }

    private void initViews() {
        this.read_btn = (Button) findViewById(R.id.read_btn);
        this.write_btn = (Button) findViewById(R.id.write_btn);
        this.read_btn.setOnClickListener(this);
        this.write_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.write_btn:
                sendToUsb("按照规则给设备发指令！");
                break;
            case R.id.read_btn:
                readFromUsb();
                break;
        }
    }

    private void sendToUsb(String content) {
        sendbytes = content.getBytes();
        int ret = -1;
        // 发送准备命令
        ret = mDeviceConnection.bulkTransfer(usbEpOut, sendbytes, sendbytes.length, 5000);
        showTmsg("指令已经发送！");
        // 接收发送成功信息(相当于读取设备数据)
        receiveytes = new byte[128];   //根据设备实际情况写数据大小
        ret = mDeviceConnection.bulkTransfer(usbEpIn, receiveytes, receiveytes.length, 10000);
//        result_tv.setText(String.valueOf(ret));
        Toast.makeText(this, String.valueOf(ret), Toast.LENGTH_SHORT).show();
    }

    private void readFromUsb() {
        //读取数据2
        int outMax = usbEpOut.getMaxPacketSize();
        int inMax = usbEpIn.getMaxPacketSize();
        ByteBuffer byteBuffer = ByteBuffer.allocate(inMax);
        UsbRequest usbRequest = new UsbRequest();
        usbRequest.initialize(mDeviceConnection, usbEpIn);
        usbRequest.queue(byteBuffer, inMax);
        if (mDeviceConnection.requestWait() == usbRequest) {
            byte[] retData = byteBuffer.array();
            try {
                showTmsg("收到数据：" + new String(retData, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    //文字提示方法
    private void showTmsg(String msg) {
        Toast.makeText(UsbTestActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";

    private void tryGetUsbPermission() {
        manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        registerReceiver(mUsbPermissionActionReceiver, filter);
        PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
        //here do emulation to ask all connected usb device for permission
        for (final UsbDevice usbDevice : manager.getDeviceList().values()) {
            //add some conditional check if necessary
            //if(isWeCaredUsbDevice(usbDevice)){
            if (manager.hasPermission(usbDevice)) {
                //if has already got permission, just goto connect it
                //that means: user has choose yes for your previously popup window asking for grant perssion for this usb device
                //and also choose option: not ask again
                afterGetUsbPermission(usbDevice);
            } else {
                //this line will let android popup window, ask user whether to allow this app to have permission to operate this usb device
                manager.requestPermission(usbDevice, mPermissionIntent);
            }
            //}
        }
    }

    private void afterGetUsbPermission(UsbDevice usbDevice) {
        //call method to set up device communication
        //Toast.makeText(this, String.valueOf("Got permission for usb device: " + usbDevice), Toast.LENGTH_LONG).show();
        //Toast.makeText(this, String.valueOf("Found USB device: VID=" + usbDevice.getVendorId() + " PID=" + usbDevice.getProductId()), Toast.LENGTH_LONG).show();
        doYourOpenUsbDevice(usbDevice);
    }

    List<UsbSerialDriver> availableDrivers;
    UsbSerialDriver driver;
    UsbSerialPort port;

    private void doYourOpenUsbDevice(UsbDevice usbDevice) {
        //now follow line will NOT show: User has not given permission to device UsbDevice
        availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }
        driver = availableDrivers.get(0);

        mDeviceConnection = manager.openDevice(driver.getDevice());

    }

    private final BroadcastReceiver mUsbPermissionActionReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        //user choose YES for your previously popup window asking for grant perssion for this usb device
                        if (null != usbDevice) {
                            afterGetUsbPermission(usbDevice);
                        }
                    } else {
                        //user choose NO for your previously popup window asking for grant perssion for this usb device
                        Toast.makeText(context, String.valueOf("Permission denied for device" + usbDevice), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    };

}
