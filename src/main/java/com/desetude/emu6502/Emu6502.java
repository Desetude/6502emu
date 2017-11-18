package com.desetude.emu6502;

/**
 * 6502 Architecture emulator
 */
public class Emu6502 {

    private final CpuStore store;
    private final Bus bus;
    private final Cpu cpu;

    public Emu6502() {
        this.store = new CpuStore();
        this.bus = new Bus();
        this.cpu = new Cpu(this.bus, this.store);
    }

    public CpuStore getStore() {
        return store;
    }

    public Bus getBus() {
        return bus;
    }

    public Cpu getCpu() {
        return cpu;
    }

}
