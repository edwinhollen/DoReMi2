package com.edwinhollen.doremi2.puzzle

import com.edwinhollen.doremi2.Pick
import com.edwinhollen.doremi2.music.Chromatic
import com.edwinhollen.doremi2.music.Note
import com.edwinhollen.doremi2.music.Scale
import com.edwinhollen.doremi2.music.ScalePattern
import java.util.*

/**
 * Created by Edwin on 1/15/2017.
 */

open class FourNotePuzzle(val solutionNotes: Array<out Note>){
    val extraNotes: Array<out Note>;
    init{
        val extraNotes: MutableList<Note> = allNotes.filterNot {allNote -> solutionNotes.any { it.chromatic == allNote.chromatic } }.toMutableList();
        Collections.shuffle(extraNotes);
        this.extraNotes = extraNotes.toTypedArray();

    }
    companion object Constraints{
        val rootNotes: Array<out Note>;
        val allNotes: Array<out Note>;
        init{
            rootNotes = Scale(Note(Chromatic.C_NATURAL, 0), ScalePattern.CHROMATIC).notes.filter {note -> note.chromatic.ordinal <= Chromatic.A_NATURAL.ordinal}.toTypedArray();
            val allNoteList: MutableList<Note> = LinkedList();
            allNoteList.addAll(rootNotes);
            allNoteList.addAll(Scale(Note(Chromatic.B_FLAT, 1), ScalePattern.CHROMATIC).notes.filter { note -> note.chromatic.ordinal <= Chromatic.A_NATURAL.ordinal });
            allNotes = allNoteList.toTypedArray();
        }
    }
}

class EasyFourNotePuzzle : FourNotePuzzle {
    constructor() : super(Scale(Pick.from(Constraints.rootNotes), Pick.from(listOf(ScalePattern.MAJOR, ScalePattern.MINOR))).notes.filterIndexed { i, note ->
        when (i) {
            0, 2, 4, 7 -> true;
            else -> false;
        }
    }.toTypedArray());
};