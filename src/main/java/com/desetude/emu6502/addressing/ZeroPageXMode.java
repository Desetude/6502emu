package com.desetude.emu6502.addressing;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.RegisterHolder;

import java.util.function.Function;

/**
 * Zero Page Indexed with X Mode - reads/writes the data from the
 * address on the first page (i.e. 0x0000 -> 0x00FF)
 * pointed at by the program + the value of the X register.
 */
public class ZeroPageXMode implements AddressingMode {

    @Override
    public int read1(MMU mmu, RegisterHolder regHolder) {
        return mmu.memoryRead1(mmu.programPop1() + regHolder.regX);
    }

    @Override
    public void write1(MMU mmu, int value) {
        throw new UnsupportedOperationException("ZeroPageModeX#write1 has not been implemented.");
    }

    @Override
    public void modify1(MMU mmu, RegisterHolder regHolder, Function<Integer, Integer> function) {
        int address = mmu.programPop1() + regHolder.regX;
        int value = mmu.memoryRead1(address);
        mmu.memoryWrite1(address, function.apply(value));
    }

}
