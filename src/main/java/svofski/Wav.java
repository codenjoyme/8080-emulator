package svofski;

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

    public ByteBuffer getBuffer(int len) {
        int requiredLen = Math.min(len, this.buffer.length - this.bufferNeedle);
        byte[] rt = new byte[requiredLen];
        for (int i = 0; i < requiredLen; i++) {
            rt[i] = this.buffer[this.bufferNeedle + i];
        }
        this.bufferNeedle += requiredLen;
        if (this.bufferNeedle >= this.buffer.length) {
            this.eof = true;
        }
        return ByteBuffer.wrap(rt);
    }

    public boolean eof() {
        return this.eof;
    }

    private byte[] getWavUint8Array(byte[] buffer) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(WAV_HEADER_SIZE * 2 + buffer.length);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

        byteBuffer.putShort((short) 0x4952); // "RI"
        byteBuffer.putShort((short) 0x4646); // "FF"

        int riffSize = buffer.length + WAV_HEADER_SIZE * 2 - 8;
        byteBuffer.putInt(riffSize);

        byteBuffer.putShort((short) 0x4157); // "WA"
        byteBuffer.putShort((short) 0x4556); // "VE"

        byteBuffer.putShort((short) 0x6d66); // "fm"
        byteBuffer.putShort((short) 0x2074); // "t "

        byteBuffer.putInt(16); // fmt chunksize: 16
        byteBuffer.putShort((short) 1); // format tag : 1
        byteBuffer.putShort((short) this.channels); // channels:

        byteBuffer.putInt(this.sampleRate); // sample per sec

        int byteRate = this.channels * this.sampleRate;
        byteBuffer.putInt(byteRate); // byte per sec

        byteBuffer.putShort((short) (this.channels * 2)); // block align
        byteBuffer.putShort((short) 16); // bit per sample
        byteBuffer.putShort((short) 0x6164); // "da"
        byteBuffer.putShort((short) 0x6174); // "ta"
        byteBuffer.putInt(buffer.length); // data size[byte]

        byteBuffer.put(buffer);

        return byteBuffer.array();
    }
}