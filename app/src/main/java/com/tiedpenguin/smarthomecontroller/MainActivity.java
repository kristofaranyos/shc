package com.tiedpenguin.smarthomecontroller;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mob41.blapi.BLDevice;
import com.github.mob41.blapi.RM2Device;
import com.github.mob41.blapi.mac.Mac;
import com.github.mob41.blapi.pkt.cmd.rm2.SendDataCmdPayload;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = this.findViewById(R.id.press);
        button.setOnClickListener(view -> new Thread(() -> {
            try {
                BLDevice dev = BLDevice.createInstance(BLDevice.DEV_RM_MINI, "192.168.0.15", new Mac("34:ea:34:b2:fd:5a"));
                boolean success = dev.auth();
                Log.d("device", "Auth status: " + (success ? "Success!" : "Failed!"));
                RM2Device rm2Device = (RM2Device) dev;
                byte[] tv = {38, 0, 80, 0, 0, 1, 40, -110, 18, 18, 19, 18, 19, 54, 19, 18, 18, 18, 19, 18, 18, 18, 19, 18, 19, 54, 19, 54, 19, 18, 18, 55, 18, 55, 18, 55, 19, 54, 19, 54, 19, 18, 18, 18, 19, 18, 18, 55, 19, 17, 19, 18, 19, 18, 18, 18, 19, 54, 19, 54, 19, 54, 19, 18, 19, 54, 19, 54, 19, 54, 19, 54, 19, 0, 5, 35, 0, 1, 40, 72, 19, 0, 13, 5, 0, 0, 0, 0, 0, 0, 0, 0, 79, 124, 67, -17, -50, 43, 34, 106, -29, -126, 84, 7, 74, -105, -8, 59};
                rm2Device.sendCmdPkt(new SendDataCmdPayload(tv));
                byte[] stereo = {38, 0, 80, 0, 0, 1, 48, -107, 18, 19, 19, 56, 18, 19, 19, 18, 20, 18, 19, 19, 18, 19, 19, 19, 18, 56, 19, 19, 18, 56, 20, 55, 19, 56, 18, 56, 19, 56, 19, 55, 19, 55, 20, 56, 18, 56, 19, 19, 18, 56, 18, 20, 18, 56, 19, 19, 19, 18, 19, 19, 18, 20, 18, 56, 18, 20, 18, 56, 19, 19, 18, 56, 19, 0, 5, 34, 0, 1, 48, 75, 18, 0, 13, 5, 0, 0, 0, 0, 0, 0, 0, 0, 93, 71, -48, -48, 93, 76, -48, -54, -55, 36, -36, -73, -117, -19, 25, -108};
                rm2Device.sendCmdPkt(new SendDataCmdPayload(stereo));

                // Code for finding the ir codes of your remote
//                            if (rm2Device.enterLearning()) {
//                                Log.d("device", "Learning succeeded");
//                                while (true) {
//                                    byte[] data = rm2Device.checkData();
//                                    if (data != null) {
//                                        Log.d("device", Arrays.toString(data));
//                                        rm2Device.sendCmdPkt(new SendDataCmdPayload(data));
//                                        break;
//                                    }
//                                    Thread.sleep(100);
//                                }
//                            } else {
//                                Log.d("device", "Learning failed");
//                            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());


    }
}