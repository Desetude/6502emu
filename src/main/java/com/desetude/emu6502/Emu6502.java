package com.desetude.emu6502;

import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;

/**
 * 6502 Architecture emulator
 */
public class Emu6502 {

    private final RegisterHolder regHolder;
    private final FlagHolder flagHolder;
    private final MMU mmu;
    private final CPU cpu;

    public Emu6502() {
        this.regHolder = new RegisterHolder();
        this.flagHolder = new FlagHolder();
        this.mmu = new MMU(this.regHolder);
        this.cpu = new CPU(this.mmu, this.regHolder, this.flagHolder);
    }

    public RegisterHolder getRegHolder() {
        return regHolder;
    }

    public FlagHolder getFlagHolder() {
        return flagHolder;
    }

    public MMU getMmu() {
        return mmu;
    }

    public CPU getCpu() {
        return cpu;
    }

}
