package com.edwinhollen.doremi2.music

/**
 * Created by Edwin on 1/15/2017.
 */

import com.edwinhollen.doremi2.music.ScaleStep.*;
import java.util.*

class Note(val chromatic: Chromatic, val octave: Int);

class Scale(val rootNote: Note, val pattern: Array<out ScaleStep>){
    val notes: LinkedList<Note>;
    init {
        this.notes = LinkedList<Note>();
        this.notes.add(rootNote);

        for (patternIndex in 1..pattern.size - 1) {
            val chromatic: Chromatic = Chromatic.values()[(this.notes.get(this.notes.size - 1).chromatic.ordinal + pattern[patternIndex].integerValue) % Chromatic.values().size];
            val octave: Int = this.notes.get(notes.size - 1).octave + if (this.notes.get(this.notes.size - 1).chromatic.ordinal + pattern[patternIndex].integerValue > Chromatic.values().size - 1) 1 else 0;
            this.notes.add(Note(chromatic, octave));
        }
    }

    constructor( rootNote: Note, pattern: ScalePattern): this(rootNote, pattern.steps);
}

enum class Chromatic{
    C_NATURAL,
    C_SHARP,
    D_NATURAL,
    E_FLAT,
    E_NATURAL,
    F_NATURAL,
    F_SHARP,
    G_NATURAL,
    A_FLAT,
    A_NATURAL,
    B_FLAT,
    B_NATURAL;
}

enum class ScalePattern(vararg val steps: ScaleStep){
    MAJOR(R, W, W, H, W, W, W, H),
    MINOR(R, W, H, W, W, H, WH, H),
    CHROMATIC(R, H, H, H, H, H, H, H, H, H, H, H);
}

enum class ScaleStep(val integerValue: Int){
    WH(3),
    W(2),
    H(1),
    R(0);
}