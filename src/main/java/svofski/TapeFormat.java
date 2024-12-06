package svofski;

import spec.math.Bites;

import java.util.Arrays;

import static svofski.Wav.WAV_HEADER_SIZE;

public class TapeFormat {
    public static final String FORMAT_SPECIALIST = "specialist";
    public static final String FORMAT_KRISTA = "krista";
    public static final String FORMAT_V06C_CAS = "v06c_cas";
    public static final String FORMAT_V06C_ROM = "v06c_rom";
    public static final String FORMAT_NEKROSHA = "nekrosha";
    private String format;
    private String variant;
    private int speed;
    private boolean forFile;
    private byte[] data;

    public TapeFormat(String fmt, Boolean forFile) {
        this.format = null;
        this.variant = null;
        this.speed = 12;
        this.forFile = forFile != null ? forFile : false;

        switch (fmt) {
            case "rk-bin":
            case "rk86-bin":
            case "86rk-bin":
                this.format = FORMAT_NEKROSHA;
                this.variant = "rk";
                this.speed = 12;
                break;
            case "mikrosha-bin":
            case "microsha-bin":
            case "microcha-bin":
            case "necrosha-bin":
            case "nekrosha-bin":
            case "necro-bin":
            case "nekro-bin":
                this.format = FORMAT_NEKROSHA;
                this.variant = "mikrosha";
                this.speed = 12;
                break;
            case "partner-bin":
                this.format = FORMAT_NEKROSHA;
                this.variant = "rk";
                this.speed = 8;
                break;
            case "v06c-rom":
                this.format = FORMAT_V06C_ROM;
                this.speed = 5;
                break;
            case "v06c-cas":
            case "v06c-bload":
            case "v06c-basic":
                this.format = FORMAT_V06C_CAS;
                this.speed = 8;
                break;
            case "krista-rom":
                this.format = FORMAT_KRISTA;
                this.speed = 8;
                break;
            case "специалистъ-rks":
            case "spetsialist-rks":
            case "specialist-rks":
            case "spec-rks":
                this.format = FORMAT_SPECIALIST;
                this.speed = 9;
                this.variant = null;
                break;
            case "специалистъ-mon":
            case "spetsialist-mon":
            case "specialist-mon":
            case "spec-mon":
                this.format = FORMAT_SPECIALIST;
                this.speed = 9;
                this.variant = "name-header";
                break;
        }
    }

    public TapeFormat format(byte[] mem, int org, String name) {
        switch (this.format) {
            case FORMAT_NEKROSHA:
                return nekrosha(mem, org, name);
            case FORMAT_V06C_ROM:
                return v06c_rom(mem, org, name);
            case FORMAT_V06C_CAS:
                return v06c_cas(mem, org, name);
            case FORMAT_KRISTA:
                return krista(mem, org, name);
            case FORMAT_SPECIALIST:
                return specialist(mem, org, name);
        }
        throw new IllegalArgumentException("Unknown format " + this.format);
    }
    
    public TapeFormat nekrosha(byte[] mem, int org, String name) {
        byte[] data = new byte[mem.length + 266 + 5];

        // rk-style checksum
        int cs_hi = 0;
        int cs_lo = 0;

        // microsha-style checksum
        int csm_hi = 0;
        int csm_lo = 0;

        int dptr = 0;
        if (!this.forFile) {
            for (int i = 0; i < 256; ++i) {
                data[dptr++] = 0;
            }
            data[dptr++] = (byte) 0xe6;
        }

        data[dptr++] = (byte) ((org >> 8) & 0xff);
        data[dptr++] = (byte) (org & 0xff);
        data[dptr++] = (byte) (((org + mem.length - 1) >> 8) & 0xff);
        data[dptr++] = (byte) ((org + mem.length - 1) & 0xff);

        for (int i = 0; i < mem.length; ++i) {
            byte octet = mem[i];
            data[dptr++] = octet;
            cs_lo += octet;
            if (i < mem.length - 1) {
                cs_hi += octet + ((cs_lo >> 8) & 0xff);
            }
            cs_lo &= 0xff;

            if (i % 2 == 0) {
                csm_lo ^= octet;
            } else {
                csm_hi ^= octet;
            }
        }

        System.out.println("checksum rk=" + Util.hex8(cs_hi & 0xff) + Util.hex8(cs_lo & 0xff));
        System.out.println("checksum microsha=" + Util.hex8(csm_hi & 0xff) + Util.hex8(csm_lo & 0xff));

        if (this.variant.equals("mikrosha")) {
            data[dptr++] = (byte) (csm_hi & 0xff);
            data[dptr++] = (byte) (csm_lo & 0xff);
        } else {
            data[dptr++] = 0;
            data[dptr++] = 0;
        }
        data[dptr++] = (byte) 0xe6;

        /* rk86 checksum */
        data[dptr++] = (byte) (cs_hi & 0xff);
        data[dptr++] = (byte) (cs_lo & 0xff);
        int end = dptr;
        data[dptr++] = 0;
        data[dptr++] = 0;
        data[dptr++] = 0;
        data[dptr++] = 0;
        data[dptr++] = 0;
        if (this.forFile) {
            byte[] croppedData = new byte[end];
            System.arraycopy(data, 0, croppedData, 0, end);
            this.data = croppedData;
        } else {
            this.data = data;
        }
        return this;
    }

    public Bites makewav() {
        byte[] encoded = biphase(this.data, this.speed != 0 ? this.speed : 12);
        Wav wav = new Wav(11025, 1);
        wav.setBuffer(encoded);
        return wav.getBuffer(encoded.length + WAV_HEADER_SIZE * 2);
    }

    public byte[] biphase(byte[] data, int halfperiod) {
        byte[] w = new byte[data.length * 8 * 2 * halfperiod];
        int period = halfperiod * 2;
        int dptr = 0;
        for (int i = 0, end = data.length; i < end; i++) {
            int octet = data[i] & 0xFF;
            for (int b = 0; b < 8; b++, octet <<= 1) {
                // Converting logic from JavaScript bitwise to Java:
                byte phase = (octet & 0x80) != 0 ? (byte)32 : (byte)(255 - 32);
                for (int q = 0; q < halfperiod; q++) {
                    w[dptr++] = phase;
                }
                phase = (byte)(phase ^ 0xFF); // XOR with 0xFF to invert the phase
                for (int q = 0; q < halfperiod; q++) {
                    w[dptr++] = phase;
                }
            }
        }
        return w;
    }

    public TapeFormat v06c_rom(byte[] mem, int org, String name) {
        int nblocks = (mem.length + 255) / 256;
        byte[] data = new byte[200 + nblocks * 376 + 64];
        int dofs = 0;
        int sofs = 0;

        // Preamble
        for (int i = 0; i < 200; ++i) {
            data[dofs++] = (byte) (((i / 25) % 2) == 0 ? 0x00 : 0x55);
        }

        // Blocks
        for (int block = 0; block < nblocks; ++block) {
            // Checksum of the name subblock
            int cs0 = 0;

            // Block preamble
            for (int i = 0; i < 16; ++i) data[dofs++] = 0;
            // Name subblock id
            for (int i = 0; i < 4; ++i) data[dofs++] = 0x55;
            data[dofs++] = (byte) 0xE6;
            for (int i = 0; i < 4; ++i) data[dofs++] = 0x00;
            // Name
            for (int i = 0; i < 25; ++i) {
                cs0 += data[dofs++] = (byte) (i < name.length() ? name.charAt(i) : 0x20);
            }
            data[dofs++] = data[dofs++] = 0;
            // High nibble of org address
            cs0 += data[dofs++] = (byte) ((0xff & (org >> 8))); /* TODO: fix misaligned org */
            // Block count
            cs0 += data[dofs++] = (byte) nblocks;
            // Block number
            cs0 += data[dofs++] = (byte) (nblocks - block);
            data[dofs++] = (byte) (cs0 & 0xff);

            // Now the actual data: 8x32 octets
            for (int sblk = 0x80; sblk < 0x88; ++sblk) {
                int cs = 0;
                for (int i = 0; i < 4; ++i) data[dofs++] = 0x00;
                data[dofs++] = (byte) 0xE6;
                cs += data[dofs++] = (byte) sblk;
                cs += data[dofs++] = (byte) cs0;
                for (int i = 0; i < 32; ++i) {
                    cs += data[dofs++] = (sofs < mem.length) ? mem[sofs++] : 0;
                }
                data[dofs++] = (byte) (0xff & cs);
            }
        }
        this.data = data;
        return this;
    }

    public TapeFormat v06c_cas(byte[] mem, int org, String name) {
        byte[] data = new byte[65536];
        int dofs = 0;

        // preamble for wav, not included in cas
        // 256[00] [E6]
        if (!forFile) {
            for (int i = 0; i < 256; ++i) data[dofs++] = 0;
            data[dofs++] = (byte) 0xE6;
        }

        // header
        for (int i = 0; i < 4; ++i) data[dofs++] = (byte) 0xD2;

        // file name
        name = name.toUpperCase();
        if (name.length() > 127) {
            name = name.substring(0, 127);
        }
        if (name.endsWith(".CAS")) {
            name = name.substring(0, name.length() - 4);
        }
        for (int i = 0; i < name.length(); ++i) {
            data[dofs++] = (byte) name.charAt(i);
        }
        for (int i = 0; i < 3; ++i) data[dofs++] = 0;

        // data preamble
        for (int i = 0; i < 256; ++i) data[dofs++] = 0;
        data[dofs++] = (byte) 0xE6;

        // msb, lsb start addr
        data[dofs++] = (byte) (0xff & (org >> 8));
        data[dofs++] = (byte) (0xff & org);

        int end = org + mem.length - 1;

        // msb, lsb end addr
        data[dofs++] = (byte) (0xff & (end >> 8));
        data[dofs++] = (byte) (0xff & end);

        int cs = 0;
        for (int i = 0; i < mem.length; ++i) {
            cs += data[dofs++] = (byte) (mem[i] & 0xff);
        }

        data[dofs++] = (byte) (cs & 0xff);

        this.data = Arrays.copyOf(data, dofs);

        return this;
    }

    public TapeFormat krista(byte[] mem, int org, String name) {
        int nblocks = (mem.length + 255) / 256;
        byte[] data = new byte[200 + nblocks * 376 + 64];
        int dofs = 0;
        int sofs = 0;

        /* Preamble */
        for (int i = 0; i < 200; ++i) {
            data[dofs++] = (byte) (((i / 25 % 2 == 0) ? 0x00 : 0x55) & 0xff); // TODO added cast to byte from int
        }

        int cs = 0;
        /* Header block */
        data[dofs++] = (byte) 0xe6;
        data[dofs++] = (byte) 0xff;
        int startblock = 0xff & (org >> 8);
        cs = data[dofs++] = (byte) startblock;
        cs += data[dofs++] = (byte) ((0xff & (startblock + nblocks)));
        data[dofs++] = (byte) cs;

        /* Blocks */
        for (int block = startblock; block < startblock + nblocks; ++block) {
            cs = 0;

            /* Block preamble */
            for (int i = 0; i < 16; ++i) data[dofs++] = 0x55;
            data[dofs++] = (byte) 0xe6;
            data[dofs++] = (byte) block; /* hi byte of block address */
            data[dofs++] = 0;            /* low byte of block address */
            data[dofs++] = 0;            /* payload size + 1 */

            /* Data: 256 bytes */
            for (int i = 0; i < 256; ++i) {
                cs += data[dofs++] = sofs < mem.length ? mem[sofs++] : 0;
            }
            data[dofs++] = (byte) (0xff & cs);
        }

        this.data = Arrays.copyOf(data, dofs + 16);
        return this;
    }

    public TapeFormat specialist(byte[] mem, int org, String name) {
        byte[] data = new byte[mem.length + 1024 + 32 + name.length()];

        // rk-style checksum
        int cs_hi = 0;
        int cs_lo = 0;

        int dptr = 0;
        if (!forFile) {
            if ("name-header".equals(variant)) {
                for (int i = 0; i < 256; ++i) {
                    data[dptr++] = 0;
                }
                data[dptr++] = (byte) 0xe6;
                data[dptr++] = (byte) 0xd9;
                data[dptr++] = (byte) 0xd9;
                data[dptr++] = (byte) 0xd9;

                for (int i = 0; i < name.length(); ++i) {
                    data[dptr++] = (byte) name.charAt(i);
                }
            }

            for (int i = 0; i < 768; ++i) {
                data[dptr++] = 0;
            }
            data[dptr++] = (byte) 0xe6;
        }

        // same as .rk but little endian
        data[dptr++] = (byte) (org & 0xff);
        data[dptr++] = (byte) ((org >> 8) & 0xff);
        data[dptr++] = (byte) ((org + mem.length - 1) & 0xff);
        data[dptr++] = (byte) (((org + mem.length - 1) >> 8) & 0xff);

        for (int i = 0; i < mem.length; ++i) {
            byte octet = mem[i];
            data[dptr++] = octet;
            cs_lo += octet & 0xff;
            if (i < mem.length - 1) {
                cs_hi += octet + ((cs_lo >> 8) & 0xff);
            }
            cs_lo &= 0xff;
        }

        System.out.println("checksum=" + Integer.toHexString(cs_hi & 0xff) + Integer.toHexString(cs_lo & 0xff));

        /* rk86 checksum */
        data[dptr++] = (byte) (cs_lo & 0xff);
        data[dptr++] = (byte) (cs_hi & 0xff);

        int end = dptr;

        for (int i = dptr; i < mem.length; ++i) {
            mem[i] = 0;
        }

        if (forFile) {
            this.data = Arrays.copyOf(data, end);
        } else {
            this.data = data;
        }

        return this;
    }

    public String getFormat() {
        return format;
    }
}