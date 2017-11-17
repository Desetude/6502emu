package com.desetude.emu6502.addressing;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.RegisterHolder;

import java.util.function.Function;

/**
 * Zero Page Indexed Indirect with X Mode - reads/writes the data from the address
 * pointed at by the program + the value of the X register on the first page (0x0000 -> 0x00FF).
 */
public class ZeroPageIndirectXMode implements AddressingMode {

    @Override
    public int read1(MMU mmu, RegisterHolder regHolder) {
        return mmu.memoryRead1(mmu.memoryRead2(mmu.programPop1() + regHolder.regX));
    }

    @Override
    public void write1(MMU mmu, int value) {
        throw new UnsupportedOperationException("ZeroPageIndirectXMode#write1 has not been implemented.");
    }

    @Override
    public void modify1(MMU mmu, RegisterHolder regHolder, Function<Integer, Integer> function) {
        throw new UnsupportedOperationException("ZeroPageIndirectXMode#modify1 has not been implemented.");
    }

}
