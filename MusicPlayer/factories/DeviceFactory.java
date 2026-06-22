package MusicPlayer.factories;

import MusicPlayer.device.IAudioOutputDevice;
import MusicPlayer.device.BluetoothSpeakerAdapter;
import MusicPlayer.device.HeadphonesAdapter;
import MusicPlayer.device.WiredSpeakerAdapter;
import MusicPlayer.external.BluetoothSpeakerAPI;
import MusicPlayer.external.HeadphonesAPI;
import MusicPlayer.external.WiredSpeakerAPI;
import MusicPlayer.enums.DeviceType;


public class DeviceFactory {
    public static IAudioOutputDevice createDevice(DeviceType deviceType) {
         switch (deviceType) {
            case BLUETOOTH:
                return new BluetoothSpeakerAdapter(new BluetoothSpeakerAPI());
            case WIRED:
                return new WiredSpeakerAdapter(new WiredSpeakerAPI());
            case HEADPHONES:
            default:
                return new HeadphonesAdapter(new HeadphonesAPI());
        }
    }
}