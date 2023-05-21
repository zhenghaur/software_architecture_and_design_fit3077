package com.team8.backend.ninemanmorris.enums;

/**
 * Enum class for the different movement phases
 * For token movement options, this will set
 * the differnet types of checks based on input
 */
public enum Phase {

    PLACEMENT(0),
    MOVEMENT(1),
    REMOVE(2);

    private final int phase;

    Phase(int phase) {
        this.phase = phase;
    }

    public int getPhase() {
        return phase;
    }
}