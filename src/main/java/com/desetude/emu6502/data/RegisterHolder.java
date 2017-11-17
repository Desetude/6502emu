package com.desetude.emu6502.data;

public class RegisterHolder {

    //8-bit accumulator register
    public int regA = 0;

    //8-bit index registers
    public int regX = 0;
    public int regY = 0;

    //8-bit stack pointer
    public int regS = 0xFF;

    //16-bit program counter
    public int regPc = 0x400;

}
