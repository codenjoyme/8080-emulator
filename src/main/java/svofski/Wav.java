package svofski;

import spec.math.Bites;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Wav {

    private int sampleRate;
    private int channels;
    private boolean eof;
    private int bufferNeedle;
    private byte[] buffer;
    private String internalBuffer;
    private boolean hasOutputHeader;
    public static final int WAV_HEADER_SIZE = 22;

    public Wav(Integer sampleRate, Integer channels) {
        this.sampleRate = sampleRate != null ? sampleRate : 44100;
        this.channels = channels != null ? channels : 2;
        this.eof = true;
        this.bufferNeedle = 0;
    }

    public void setBuffer(byte[] inputBuffer) {
        this.buffer = getWavUint8Array(inputBuffer);
        this.bufferNeedle = 0;
        this.internalBuffer = "";
        this.hasOutputHeader = false;
        this.eof = false;
    }

    public Bites getBuffer(int len) {
        int requiredLen = Math.min(len, this.buffer.length - this.bufferNeedle);
        Bites rt = new Bites(requiredLen);
        for (int i = 0; i < requiredLen; i++) {
            rt.set(i, this.buffer[this.bufferNeedle + i]);
        }
        this.bufferNeedle += requiredLen;
        if (this.bufferNeedle >= this.buffer.length) {
            this.eof = true;
        }
        return rt;
    }

    public boolean eof() {
        return this.eof;
    }

    private byte[] getWavUint8Array(byte[] buffer) {
        ByteBuffer result = ByteBuffer.allocate(WAV_HEADER_SIZE * 2 + buffer.length);
        result.order(ByteOrder.LITTLE_ENDIAN);

        result.putShort((short) 0x4952); // "RI"
        result.putShort((short) 0x4646); // "FF"

        int riffSize = buffer.length + WAV_HEADER_SIZE * 2 - 8;
        result.putInt(riffSize);

        result.putShort((short) 0x4157); // "WA"
        result.putShort((short) 0x4556); // "VE"

        result.putShort((short) 0x6d66); // "fm"
        result.putShort((short) 0x2074); // "t "

        result.putInt(16); // fmt chunksize: 16
        result.putShort((short) 1); // format tag : 1
        result.putShort((short) this.channels); // channels:

        result.putInt(this.sampleRate); // sample per sec

        int byteRate = this.channels * this.sampleRate;
        result.putInt(byteRate); // byte per sec

        result.putShort((short) (this.channels * 2)); // block align
        result.putShort((short) 16); // bit per sample
        result.putShort((short) 0x6164); // "da"
        result.putShort((short) 0x6174); // "ta"
        result.putInt(buffer.length); // data size[byte]

        result.put(buffer);

        return result.array();
    }
}