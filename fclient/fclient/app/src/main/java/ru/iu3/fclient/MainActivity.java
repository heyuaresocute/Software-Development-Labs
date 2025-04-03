package ru.iu3.fclient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

import ru.iu3.fclient.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("fclient");
        System.loadLibrary("mbedcrypto");
    }

    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        stringFromJNI();
//        int res = initRng();
//        byte[] v = randomBytes(10);
//
//
//        TextView tv = binding.sampleText;
//        tv.setText("res: " + res + "\nbytes: " + java.util.Arrays.toString(v));
        TextView tv = binding.sampleText;

        StringBuilder sb = new StringBuilder();

        int res = initRng();
        sb.append("res: ").append(res).append("\n");

        byte[] key = randomBytes(16);
        sb.append("key: ").append(Arrays.toString(key)).append("\n");

        String original = "Hello World";
        byte[] encrypted = encrypt(key, original.getBytes(StandardCharsets.UTF_8));
        sb.append("encrypted: ").append(Arrays.toString(encrypted)).append("\n");

        byte[] decrypted = decrypt(key, encrypted);
        sb.append("decrypted: ").append(new String(decrypted, StandardCharsets.UTF_8)).append("\n");
        tv.setText(sb.toString());
    }

    public native String stringFromJNI();
    public static native int initRng();
    public static native byte[] randomBytes(int no);
    public static native byte[] encrypt(byte[] key, byte[] data);
    public static native byte[] decrypt(byte[] key, byte[] data);

}
