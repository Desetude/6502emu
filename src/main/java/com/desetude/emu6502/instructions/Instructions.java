package com.desetude.emu6502.instructions;

/**
 * List of instructions opcodes.
 *
 * L: immediate (literal, normally referred to with a #)
 * a: absolute
 * id: indirect (only used for JMP)
 * i: implied
 * A: accumulator
 * zp: zero page
 * r: relative
 *
 * aX: absolute indexed with X
 * aY: absolute indexed with Y
 *
 * zpX: zero page indexed with X
 * zpY: zero page indexed with Y
 *
 * zpIdX: zero page indirect indexed with X
 * zpIdY: zero page indirect indexed with Y
 */
public class Instructions {

    private Instructions() {}

    public static final int ADC_L = 0x69;
    public static final int ADC_zp = 0x65;
    public static final int ADC_zpX = 0x75;
    public static final int ADC_a = 0x6D;
    public static final int ADC_aX = 0x7D;
    public static final int ADC_aY = 0x79;
    public static final int ADC_zpIdX = 0x61;
    public static final int ADC_zpIdY = 0x71;

    public static final int AND_L = 0x29;
    public static final int AND_zp = 0x25;
    public static final int AND_zpX = 0x35;
    public static final int AND_a = 0x2D;
    public static final int AND_aX = 0x3D;
    public static final int AND_aY = 0x39;
    public static final int AND_zpIdX = 0x21;
    public static final int AND_zpIdY = 0x31;

    public static final int ASL_A = 0x0A;
    public static final int ASL_zp = 0x06;
    public static final int ASL_zpX = 0x16;
    public static final int ASL_a = 0x0E;
    public static final int ASL_aX = 0x1E;

    public static final int BIT_zp = 0x24;
    public static final int BIT_a = 0x2C;

    public static final int BPL_r = 0x10;
    public static final int BMI_r = 0x30;
    public static final int BVC_r = 0x50;
    public static final int BVS_r = 0x70;
    public static final int BCC_r = 0x90;
    public static final int BCS_r = 0xB0;
    public static final int BNE_r = 0xD0;
    public static final int BEQ_r = 0xF0;

    public static final int BRK_i = 0x00;

    public static final int CMP_L = 0xC9;
    public static final int CMP_zp = 0xC5;
    public static final int CMP_zpX = 0xD5;
    public static final int CMP_a = 0xCD;
    public static final int CMP_aX = 0xDD;
    public static final int CMP_aY = 0xD9;
    public static final int CMP_zpIdX = 0xC1;
    public static final int CMP_zpIdY = 0xD1;

    public static final int CPX_L = 0xE0;
    public static final int CPX_zp = 0xE4;
    public static final int CPX_a = 0xEC;

    public static final int CPY_L = 0xC0;
    public static final int CPY_zp = 0xC4;
    public static final int CPY_a = 0xCC;

    public static final int DEC_zp = 0xC6;
    public static final int DEC_zpX = 0xD6;
    public static final int DEC_a = 0xCE;
    public static final int DEC_aX = 0xDE;

    public static final int EOR_L = 0x49;
    public static final int EOR_zp = 0x45;
    public static final int EOR_zpX = 0x55;
    public static final int EOR_a = 0x4D;
    public static final int EOR_aX = 0x5D;
    public static final int EOR_aY = 0x59;
    public static final int EOR_zpIdX = 0x41;
    public static final int EOR_zpIdY = 0x51;

    public static final int CLC_i = 0x18;
    public static final int SEC_i = 0x38;
    public static final int CLI_i = 0x58;
    public static final int SEI_i = 0x78;
    public static final int CLV_i = 0xB8;
    public static final int CLD_i = 0xD8;
    public static final int SED_i = 0xF8;

    public static final int INC_zp = 0xE6;
    public static final int INC_zpX = 0xF6;
    public static final int INC_a = 0xEE;
    public static final int INC_aX = 0xFE;

    public static final int JMP_a = 0x4C;
    public static final int JMP_id = 0x6C;

    public static final int JSR_a = 0x20;

    public static final int LDA_L = 0xA9;
    public static final int LDA_zp = 0xA5;
    public static final int LDA_zpX = 0xB5;
    public static final int LDA_a = 0xAD;
    public static final int LDA_aX = 0xBD;
    public static final int LDA_aY = 0xB9;
    public static final int LDA_zpIdX = 0xA1;
    public static final int LDA_zpIdY = 0xB1;

    public static final int LDX_L = 0xA2;
    public static final int LDX_zp = 0xA6;
    public static final int LDX_zpY = 0xB6;
    public static final int LDX_a = 0xAE;
    public static final int LDX_aY = 0xBE;

    public static final int LDY_L = 0xA0;
    public static final int LDY_zp = 0xA4;
    public static final int LDY_zpX = 0xB4;
    public static final int LDY_a = 0xAC;
    public static final int LDY_aX = 0xBC;

    public static final int LSR_A = 0x4A;
    public static final int LSR_zp = 0x46;
    public static final int LSR_zpX = 0x56;
    public static final int LSR_a = 0x4E;
    public static final int LSR_aX = 0x5E;

    public static final int NOP_i = 0xEA;

    public static final int ORA_L = 0x09;
    public static final int ORA_zp = 0x05;
    public static final int ORA_zpX = 0x15;
    public static final int ORA_a = 0x0D;
    public static final int ORA_aX = 0x1D;
    public static final int ORA_aY = 0x19;
    public static final int ORA_zpIdX = 0x01;
    public static final int ORA_zpIdY = 0x11;

    public static final int TAX_i = 0xAA;
    public static final int TXA_i = 0x8A;
    public static final int DEX_i = 0xCA;
    public static final int INX_i = 0xE8;
    public static final int TAY_i = 0xA8;
    public static final int TYA_i = 0x98;
    public static final int DEY_i = 0x88;
    public static final int INY_i = 0xC8;

    public static final int ROL_A = 0x2A;
    public static final int ROL_zp = 0x26;
    public static final int ROL_zpX = 0x36;
    public static final int ROL_a = 0x2E;
    public static final int ROL_aX = 0x3E;

    public static final int ROR_A = 0x6A;
    public static final int ROR_zp = 0x66;
    public static final int ROR_zpX = 0x76;
    public static final int ROR_a = 0x6E;
    public static final int ROR_aX = 0x7E;

    public static final int RTI_i = 0x40;

    public static final int RTS_i = 0x60;

    public static final int SBC_L = 0xE9;
    public static final int SBC_zp = 0xE5;
    public static final int SBC_zpX = 0xF5;
    public static final int SBC_a = 0xED;
    public static final int SBC_aX = 0xFD;
    public static final int SBC_aY = 0xF9;
    public static final int SBC_zpIdX = 0xE1;
    public static final int SBC_zpIdY = 0xF1;

    public static final int STA_zp = 0x85;
    public static final int STA_zpX = 0x95;
    public static final int STA_a = 0x8D;
    public static final int STA_aX = 0x9D;
    public static final int STA_aY = 0x99;
    public static final int STA_zpIdX = 0x81;
    public static final int STA_zpIdY = 0x91;

    public static final int TXS_i = 0x9A;
    public static final int TSX_i = 0xBA;
    public static final int PHA_i = 0x48;
    public static final int PLA_i = 0x68;
    public static final int PHP_i = 0x08;
    public static final int PLP_i = 0x28;

    public static final int STX_zp = 0x86;
    public static final int STX_zpY = 0x96;
    public static final int STX_a = 0x8E;

    public static final int STY_zp = 0x84;
    public static final int STY_zpY = 0x94;
    public static final int STY_a = 0x8C;

    public static final int HLT_i = 0xFF;

}
